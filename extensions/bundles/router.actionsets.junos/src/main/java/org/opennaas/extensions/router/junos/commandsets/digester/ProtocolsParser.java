package org.opennaas.extensions.router.junos.commandsets.digester;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;
import org.opennaas.extensions.router.junos.commandsets.commons.IPUtilsHelper;
import org.opennaas.extensions.router.model.EnabledLogicalElement.EnabledState;
import org.opennaas.extensions.router.model.NetworkPort;
import org.opennaas.extensions.router.model.OSPFArea;
import org.opennaas.extensions.router.model.OSPFAreaConfiguration;
import org.opennaas.extensions.router.model.OSPFProtocolEndpoint;
import org.opennaas.extensions.router.model.OSPFService;
import org.opennaas.extensions.router.model.RIPConfiguration;
import org.opennaas.extensions.router.model.RIPGroup;
import org.opennaas.extensions.router.model.RIPProtocolEndpoint;
import org.opennaas.extensions.router.model.RIPService;
import org.opennaas.extensions.router.model.System;
import org.opennaas.extensions.router.model.utils.ModelHelper;

public class ProtocolsParser extends DigesterEngine {

	private System		model;

	private boolean		serviceDisabledFlag	= false;
	private OSPFService	ospfService;

	private RIPService	ripService;
	private String		groupID				= null;

	class ParserRuleSet extends RuleSetBase {
		private String	prefix	= "";

		protected ParserRuleSet() {

		}

		protected ParserRuleSet(String prefix) {
			this.prefix = prefix;
		}

		@Override
		public void addRuleInstances(Digester arg0) {
			addOSPFRuleInstances(arg0);
			// TODO add other protocol rules (e.g. bgp)
		}

		private void addOSPFRuleInstances(Digester digester) {
			// FIXME the path pattern can't be global , must distinguish between
			// routers
			addMyRule("*/protocols/ospf/disable", "setDisabledFlag", 0);
			addObjectCreate("*/protocols/ospf/area", OSPFArea.class);
			addMyRule("*/protocols/ospf/area/name", "transformAndSetAreaID", 0); // AreaID required a format transformation
			addObjectCreate("*/protocols/ospf/area/interface", OSPFProtocolEndpoint.class);
			addMyRule("*/protocols/ospf/area/interface/name", "configureOSPFProtocolEndpoint", 0);
			addMyRule("*/protocols/ospf/area/interface/disable", "disableOSPFProtocolEndpoint", 0);
			addSetNext("*/protocols/ospf/area/interface", "addEndpointInArea");
			addSetNext("*/protocols/ospf/area/", "addOSPFArea", OSPFArea.class.getName());
			// OSPFService should be created also when addOSPFArea has not been called
			addMyRule("*/protocols/ospf", "obtainOSPFService", -1); // -1 specifies we want no parameters

			// RIP Protocol

			addObjectCreate("*/protocols/rip/group", RIPGroup.class);
			addMyRule("*/protocols/rip/group/group_name", "setGroupName", 0);
			addObjectCreate("*/protocols/rip/group/neighbor", RIPProtocolEndpoint.class);
			addMyRule("*/protocols/rip/group/neighbor/address", "configureRIPProtocolEndpoint", 0);
			addSetNext("*/protocols/rip/group/neighbor", "addEndpointToRIPGroup");
			addSetNext("*/protocols/rip/group", "addRIPGroup");
			addMyRule("*/protocols/rip", "obtainRIPService", -1);

		}
	}

	public ProtocolsParser(System routerModel) {
		ruleSet = new ParserRuleSet();
		setModel(routerModel);
	}

	public ProtocolsParser(String prefix, System routerModel) {
		ruleSet = new ParserRuleSet(prefix);
		setModel(routerModel);
	}

	public System getModel() {
		return model;
	}

	public void setModel(System model) {
		this.model = model;
	}

	@Override
	public void init() {
		push(this); // bottom of the stack
		mapElements = new HashMap<String, Object>();
	}

	public void enableOSPFService() {
		Object obj = peek(0);
		assert (obj instanceof OSPFService);

		((OSPFService) obj).setEnabledState(EnabledState.ENABLED);
	}

	public void disableOSPFService() {
		Object obj = peek(0);
		assert (obj instanceof OSPFService);

		((OSPFService) obj).setEnabledState(EnabledState.DISABLED);
	}

	public void configureOSPFProtocolEndpoint(String interfaceNameShort) {

		enableOSPFProtocolEndpoint("enable");

		Object obj = peek(0);
		assert (obj instanceof OSPFProtocolEndpoint);

		OSPFProtocolEndpoint ospfEndpoint = (OSPFProtocolEndpoint) obj;
		ospfEndpoint.setName(interfaceNameShort);

		// get interface with given name from model
		NetworkPort matchingInterface = null;
		boolean isSubInterface = interfaceNameShort.contains(".");
		if (isSubInterface) {
			String ifaceName = interfaceNameShort.split("\\.")[0];
			int ifacePortNumber = Integer.parseInt(interfaceNameShort.split("\\.")[1]);
			for (NetworkPort iface : ModelHelper.getInterfaces(this.getModel())) {
				if (iface.getName().equals(ifaceName) && iface.getPortNumber() == ifacePortNumber) {
					matchingInterface = iface;
					break;
				}
			}
		} else {
			for (NetworkPort iface : ModelHelper.getInterfaces(this.getModel())) {
				if (iface.getName().equals(interfaceNameShort)) {
					matchingInterface = iface;
					break;
				}
			}
		}

		if (matchingInterface != null) {
			matchingInterface.addProtocolEndpoint(ospfEndpoint);
		}

		// FIXME cannot get what IPProtocolEndpoints is ospfEndpoint bindedTo.
		// should we use all IPProtocolEndpoints in matchingInterface???
		// I guess not. That would cause a multiple dependency
		// and we want to say that ospf depends on an IPProtocolEndpoint in that interface
		// (any of them, but at least one).
	}

	public void enableOSPFProtocolEndpoint(String enable) {
		Object obj = peek(0);
		assert (obj instanceof OSPFProtocolEndpoint);

		((OSPFProtocolEndpoint) obj).setEnabledState(EnabledState.ENABLED);
	}

	public void disableOSPFProtocolEndpoint(String disable) {
		Object obj = peek(0);
		assert (obj instanceof OSPFProtocolEndpoint);

		((OSPFProtocolEndpoint) obj).setEnabledState(EnabledState.DISABLED);
	}

	/**
	 * 
	 * @param dottedAreaId
	 * @throws IOException
	 */
	public void transformAndSetAreaID(String dottedAreaId) throws IOException {
		assert (dottedAreaId != null);

		long areaId = IPUtilsHelper.ipv4StringToLong(dottedAreaId);

		// setAreaId
		Object obj = peek(0);
		assert (obj instanceof OSPFArea);
		((OSPFArea) obj).setAreaID(areaId);
	}

	public void setDisabledFlag(String disabled) {
		this.serviceDisabledFlag = true;
	}

	/**
	 * Creates required structure in model to store OSPFArea.
	 * 
	 * @param ospfArea
	 */
	public void addOSPFArea(OSPFArea ospfArea) {

		OSPFService ospfService = obtainOSPFService();

		OSPFAreaConfiguration areaConfig = new OSPFAreaConfiguration();
		areaConfig.setOSPFArea(ospfArea);

		ospfService.addOSPFAreaConfiguration(areaConfig);
	}

	/**
	 * Get ospfService or creates it if necessary.
	 * 
	 * Created OSPFService has its state setted up, and has already been included in model.
	 * 
	 * @return
	 */
	public OSPFService obtainOSPFService() {

		groupID = null;

		if (ospfService != null)
			return ospfService;

		ospfService = new OSPFService();
		if (serviceDisabledFlag)
			ospfService.setEnabledState(EnabledState.DISABLED);
		else
			ospfService.setEnabledState(EnabledState.ENABLED);

		model.addHostedService(ospfService);

		return ospfService;
	}

	/**
	 * Get ripService or creates it if necessary.
	 * 
	 * Created RIPService has its state setted up, and has already been included in model.
	 * 
	 * @return
	 */
	public RIPService obtainRIPService() {
		if (ripService != null)
			return ripService;

		ripService = new RIPService();
		model.addHostedService(ripService);
		return ripService;
	}

	public void setGroupName(String name) {
		assert (name != null);

		Object obj = peek(0);
		assert (obj instanceof RIPGroup);
		((RIPGroup) obj).setName(name);
		groupID = name;
	}

	public void configureRIPProtocolEndpoint(String interfaceName) {

		Object obj = peek(0);
		assert (obj instanceof RIPProtocolEndpoint);

		RIPProtocolEndpoint ripEndpoint = (RIPProtocolEndpoint) obj;
		ripEndpoint.setName(interfaceName);
		NetworkPort matchingInterface = null;
		boolean isSubInterface = interfaceName.contains(".");
		if (isSubInterface) {
			String ifaceName = interfaceName.split("\\.")[0];
			int ifacePortNumber = Integer.parseInt(interfaceName.split("\\.")[1]);

			for (NetworkPort iface : ModelHelper.getInterfaces(this.getModel())) {
				if (iface.getName().equals(ifaceName) && iface.getPortNumber() == ifacePortNumber) {
					matchingInterface = iface;
					break;
				}
			}
		} else {
			for (NetworkPort iface : ModelHelper.getInterfaces(this.getModel())) {
				if (iface.getName().equals(interfaceName)) {
					matchingInterface = iface;
					break;
				}
			}
		}
		if (matchingInterface != null) {
			matchingInterface.addProtocolEndpoint(ripEndpoint);
		}
	}

	public void addRIPGroup(RIPGroup ripGroup) {

		Object obj = peek(0);
		assert (obj instanceof RIPGroup);
		RIPService ripService = obtainRIPService();

		RIPConfiguration ripConfig = new RIPConfiguration();
		ripConfig.setRIPGroup(ripGroup);

		ripService.addRIPGroup(ripGroup);

	}

}
