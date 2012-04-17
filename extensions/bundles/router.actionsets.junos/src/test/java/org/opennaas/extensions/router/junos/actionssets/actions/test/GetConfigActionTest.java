package org.opennaas.extensions.router.junos.actionssets.actions.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opennaas.core.protocols.sessionmanager.ProtocolSessionManager;
import org.opennaas.core.resources.action.ActionException;
import org.opennaas.core.resources.action.ActionResponse;
import org.opennaas.extensions.router.junos.actionssets.ActionConstants;
import org.opennaas.extensions.router.junos.actionssets.actions.GetConfigurationAction;
import org.opennaas.extensions.router.model.ComputerSystem;
import org.opennaas.extensions.router.model.EthernetPort;
import org.opennaas.extensions.router.model.GRETunnelConfiguration;
import org.opennaas.extensions.router.model.GRETunnelEndpoint;
import org.opennaas.extensions.router.model.GRETunnelService;
import org.opennaas.extensions.router.model.IPProtocolEndpoint;
import org.opennaas.extensions.router.model.LogicalDevice;
import org.opennaas.extensions.router.model.LogicalPort;
import org.opennaas.extensions.router.model.LogicalTunnelPort;
import org.opennaas.extensions.router.model.NextHopRoute;
import org.opennaas.extensions.router.model.OSPFArea;
import org.opennaas.extensions.router.model.OSPFAreaConfiguration;
import org.opennaas.extensions.router.model.OSPFProtocolEndpointBase;
import org.opennaas.extensions.router.model.OSPFService;
import org.opennaas.extensions.router.model.ProtocolEndpoint;
import org.opennaas.extensions.router.model.RIPGroup;
import org.opennaas.extensions.router.model.RIPProtocolEndpoint;
import org.opennaas.extensions.router.model.RIPService;
import org.opennaas.extensions.router.model.Service;
import org.opennaas.extensions.router.model.System;
import org.opennaas.extensions.router.model.VLANEndpoint;

public class GetConfigActionTest {

	private static GetConfigurationAction	action;
	private static Log						log	= LogFactory.getLog(GetConfigActionTest.class);
	static ActionTestHelper					helper;
	static ProtocolSessionManager			protocolsessionmanager;

	@BeforeClass
	public static void init() {
		action = new GetConfigurationAction();
		action.setModelToUpdate(new ComputerSystem());
		helper = new ActionTestHelper();
		protocolsessionmanager = helper.getProtocolSessionManager();
	}

	@Test
	public void TestActionID() {
		Assert.assertEquals("Wrong ActionID", ActionConstants.GETCONFIG, action.getActionID());
	}

	@Test
	public void paramsTest() {
		// this action always have null params
		Assert.assertNull("Not accepted param", action.getParams());
	}

	@Test
	public void templateTest() {
		// this action always have this template as a default
		Assert.assertEquals("Not accepted param", "/VM_files/getconfiguration.vm", action.getTemplate());
	}

	private void printTest(org.opennaas.extensions.router.model.System routerModel) {

		List<LogicalDevice> ld = routerModel.getLogicalDevices();

		log.info("Logical devices: " + ld.size());

		for (LogicalDevice device : ld) {
			LogicalPort lp = (LogicalPort) device;

			if (device instanceof LogicalTunnelPort) {
				LogicalTunnelPort lt = (LogicalTunnelPort) device;
				log.info("LogicalTunnelPort: " + lt.getName());
				log.info("Peer unit " + lt.getPeer_unit());
				log.info("Unit " + lt.getPortNumber());
				log.info("Status " + lt.getOperationalStatus());
			} else if (device instanceof EthernetPort) {
				EthernetPort ep = (EthernetPort) device;
				log.info("EthernetPort: " + ep.getName());
				log.info("Unit " + ep.getPortNumber());
			} else {
				log.info("No such class considered ");
			}
			log.info("Status " + device.getOperationalStatus());
			for (ProtocolEndpoint p : lp.getProtocolEndpoint()) {
				if (p instanceof IPProtocolEndpoint) {
					IPProtocolEndpoint ip = (IPProtocolEndpoint) p;
					log.info(ip.getIPv4Address());
					log.info(ip.getSubnetMask());
				} else {
					VLANEndpoint vlan = (VLANEndpoint) p;
					log.info("VLAN " + vlan.getVlanID());
				}
			}
		}

		log.info("Found " + routerModel.getChildren().size() + " logical resources.");
		for (Object systemElement : routerModel.getChildren()) {
			log.info((String) systemElement);
			if (systemElement instanceof ComputerSystem) {
				ComputerSystem logicalrouter = (ComputerSystem) systemElement;
				// check that the element is a Logical Router
				log.info(logicalrouter.getName());
			}
		}

		for (Service service : routerModel.getHostedService()) {
			if (service instanceof GRETunnelService) {
				log.info(" - GRE Tunnel");
				GRETunnelService greService = (GRETunnelService) service;
				log.info("Interface :" + greService.getName());
				GRETunnelConfiguration greConf = greService.getGRETunnelConfiguration();
				log.info("Source : " + greConf.getSourceAddress());
				log.info("Destination : " + greConf.getDestinationAddress());
				for (ProtocolEndpoint pE : greService.getProtocolEndpoint()) {
					GRETunnelEndpoint gE = (GRETunnelEndpoint) pE;
					if (gE.getIPv4Address() == null)
						log.info("ipv6 : " + gE.getIPv6Address());
					else
						log.info("ipv4 : " + gE.getIPv4Address());

					for (NextHopRoute nextHop : gE.getNextHopRoutes()) {
						log.info("next-hop route for: " + nextHop.getDestinationAddress());
					}
				}
			} else if (service instanceof RIPService) {
				// FIXME the netconf-4j has to be modified. The ipconfiguration.xml file has an error.
				// The name of the RIP group should be <group_name> and the one for the interface name
				// <neighbor_name>. With the current version, the group and the interface would print "null"

				RIPService ripService = (RIPService) service;
				log.info(" - RIP Service");
				for (RIPGroup ripGroup : ripService.getRIPGroups()) {
					log.info("Group: " + ripGroup.getName());
					for (RIPProtocolEndpoint endpoint : ripGroup.getRIPProtocolEndpoints()) {
						log.info("Interface: " + endpoint.getName());
					}
				}
			} else if (service instanceof OSPFService) {
				log.info(" - OSPF Service");
				OSPFService ospfService = (OSPFService) service;
				for (OSPFAreaConfiguration ospfConfig : ospfService.getOSPFAreaConfiguration()) {
					OSPFArea ospfArea = ospfConfig.getOSPFArea();
					log.info("Area: " + String.valueOf(ospfArea.getAreaID()));
					for (OSPFProtocolEndpointBase pE : ospfArea.getEndpointsInArea()) {
						log.info("Interface : " + pE.getName());
					}
				}
			}
		}

	}

	@Test
	public void testExecute() throws ActionException {

		ActionResponse response = action.execute(protocolsessionmanager);

		org.opennaas.extensions.router.model.System routerModel = (org.opennaas.extensions.router.model.System) action.getModelToUpdate();
		Assert.assertNotNull(routerModel);

		checkRIPService(routerModel);
		checkOSPFService(routerModel);

		printTest(routerModel);
	}

	// @Test
	// public void testExecuteInLogicalRouter() {
	// try {
	// ActionResponse response = action.execute(protocolsessionmanager);
	// } catch (ActionException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// Assert.fail();
	// }
	// org.opennaas.extensions.router.model.System routerModel = (org.opennaas.extensions.router.model.System) action.getModelToUpdate();
	// Assert.assertNotNull(routerModel);
	// printTest(routerModel);
	// }

	private void checkOSPFService(System routerModel) {

		Assert.assertEquals("Only an OSPF Service should be present at the model.", routerModel.getAllHostedServicesByType(new OSPFService()).size(),
				1);
		OSPFService ospfService = routerModel.getAllHostedServicesByType(new OSPFService()).get(0);
		Assert.assertEquals("Only an OSPF Area Configuration should be present at the model.", ospfService.getOSPFAreaConfiguration().size(), 1);
		OSPFAreaConfiguration ospfConfig = ospfService.getOSPFAreaConfiguration().get(0);
		OSPFArea ospfArea = ospfConfig.getOSPFArea();
		Assert.assertEquals("OSPF Area Id should be 0.0.0.0.", ospfArea.getAreaID(), 0);
		Assert.assertEquals("Only an interface should be present at the OSPF Area", ospfArea.getEndpointsInArea().size(), 1);

		OSPFProtocolEndpointBase pE = ospfArea.getEndpointsInArea().get(0);
		Assert.assertEquals("Interface at the OSPF Area should be fe-0/0/3.45.", pE.getName(), "fe-0/0/3.45");
	}

	private void checkRIPService(System routerModel) {

		Assert.assertEquals("Only a RIP Service should be present at the model.", routerModel.getAllHostedServicesByType(new RIPService()).size(), 1);
		RIPService ripService = routerModel.getAllHostedServicesByType(new RIPService()).get(0);

		Assert.assertEquals("Only a RIP Group should be present at the model.", ripService.getRIPGroups().size(), 1);
		RIPGroup ripGroup = ripService.getRIPGroups().get(0);

		Assert.assertNotNull("A RIP Group Configuration should be present at the model.", ripGroup.getRIPGroupConfiguration());
		Assert.assertEquals("Only one interface should be present at the RIP Group.", ripGroup.getRIPProtocolEndpoints().size(), 1);

		// TODO can't check the name of the interface and the group, because of the netconf4j xml configuration. See comment on line 134 of this file.
	}

	/**
	 * Simple parser. It was used for proves with xml files
	 * 
	 * @param stream
	 * @return
	 */
	public String readStringFromFile(String pathFile) throws Exception {
		String answer = null;
		// InputStream inputFile =
		// ClassLoader.getSystemResourceAsStream(pathFile);
		InputStream inputFile = getClass().getResourceAsStream(pathFile);
		InputStreamReader streamReader = new InputStreamReader(inputFile);
		StringBuffer fileData = new StringBuffer(1000);
		BufferedReader reader = new BufferedReader(streamReader);
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
		reader.close();
		answer = fileData.toString();

		return answer;
	}

}