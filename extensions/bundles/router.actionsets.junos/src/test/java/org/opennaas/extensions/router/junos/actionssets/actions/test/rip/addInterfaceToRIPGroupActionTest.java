package org.opennaas.extensions.router.junos.actionssets.actions.test.rip;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opennaas.core.protocols.sessionmanager.ProtocolSessionManager;
import org.opennaas.core.resources.action.ActionException;
import org.opennaas.core.resources.action.ActionResponse;
import org.opennaas.extensions.router.junos.actionssets.ActionConstants;
import org.opennaas.extensions.router.junos.actionssets.actions.rip.AddInterfaceToRIPGroupAction;
import org.opennaas.extensions.router.junos.actionssets.actions.test.ActionTestHelper;
import org.opennaas.extensions.router.model.ComputerSystem;
import org.opennaas.extensions.router.model.RIPGroup;
import org.opennaas.extensions.router.model.RIPProtocolEndpoint;

/**
 * 
 * @author Adrian Rosello
 * 
 */
public class addInterfaceToRIPGroupActionTest {

	Log											log	= LogFactory.getLog(addInterfaceToRIPGroupActionTest.class);
	private static AddInterfaceToRIPGroupAction	action;
	static ActionTestHelper						helper;
	static ProtocolSessionManager				protocolsessionmanager;

	@BeforeClass
	public static void init() {

		action = new AddInterfaceToRIPGroupAction();
		action.setModelToUpdate(new ComputerSystem());
		helper = new ActionTestHelper();
		action.setParams(getRIPGroup());
		helper = new ActionTestHelper();
		protocolsessionmanager = helper.getProtocolSessionManager();
	}

	@Test
	public void actionIDTest() {

		Assert.assertEquals("Wrong ActionID", ActionConstants.RIP_ADD_INTERFACE_TO_GROUP,
				action.getActionID());
	}

	@Test
	public void paramsTest() {
		// this action always have null params
		Assert.assertNotNull("Parameters are null", action.getParams());
		assert (action.getParams() instanceof RIPGroup);
	}

	@Test
	public void templateTest() {
		// this action always have this template as a default
		log.info(action.getTemplate());
		Assert.assertEquals("Not accepted template", "/VM_files/ripAddInterfaceToGroup.vm", action.getTemplate());
	}

	@Test
	public void executeActionTest() {
		action.setModelToUpdate(new ComputerSystem());

		// Add params
		RIPGroup ripGroup = getRIPGroup();
		action.setParams(ripGroup);

		try {
			ActionResponse response = action.execute(protocolsessionmanager);
			Assert.assertTrue(response.getActionID()
					.equals(ActionConstants.RIP_ADD_INTERFACE_TO_GROUP));
		} catch (ActionException e) {
			log.error(e.getMessage());
			Assert.fail();
		}

		ComputerSystem computerSystem = (ComputerSystem) action.getModelToUpdate();
		Assert.assertNotNull(computerSystem);
	}

	private static RIPGroup getRIPGroup() {

		RIPGroup ripGroup = new RIPGroup();
		ripGroup.setName("RIP-test-group");

		ripGroup.addEndpointToRIPGroup(getRIPProtocolEndpoint("fe-0/0/2", "1"));
		ripGroup.addEndpointToRIPGroup(getRIPProtocolEndpoint("fe-0/0/2", "43"));

		return ripGroup;
	}

	private static RIPProtocolEndpoint getRIPProtocolEndpoint(String logicalPortName, String logicalPortNumber) {

		RIPProtocolEndpoint endpoint = new RIPProtocolEndpoint();
		endpoint.setName(logicalPortName + "." + logicalPortNumber);
		return endpoint;
	}
}
