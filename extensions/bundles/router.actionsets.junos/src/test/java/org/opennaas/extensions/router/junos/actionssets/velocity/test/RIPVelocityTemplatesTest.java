package org.opennaas.extensions.router.junos.actionssets.velocity.test;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.opennaas.core.resources.helpers.XmlHelper;
import org.opennaas.extensions.router.model.RIPGroup;
import org.opennaas.extensions.router.model.RIPProtocolEndpoint;

/**
 * 
 * @author Adrian Rosello
 * 
 */
public class RIPVelocityTemplatesTest extends VelocityTemplatesTest {

	private final Log	log	= LogFactory.getLog(RIPVelocityTemplatesTest.class);

	@Test
	public void testConfigureRIPGroupTemplate() throws Exception {

		template = "/VM_files/ripConfigureGroup.vm";

		Map<String, Object> extraParams = new HashMap<String, Object>();
		extraParams.put("elementName", "");

		String message = callVelocity(template, getRIPGroup(), extraParams);

		Assert.assertNotNull(message);
		Assert.assertTrue(message.contains("<group_name>RIP-test-group</group_name>"));
		Assert.assertTrue(message.contains("<neighbor_name>fe-0/0/2.1</neighbor_name>"));
		Assert.assertTrue(message.contains("<neighbor_name>fe-0/0/2.43</neighbor_name>"));

		log.info(XmlHelper.formatXML(message));
	}

	private RIPGroup getRIPGroup() {

		RIPGroup ripGroup = new RIPGroup();
		ripGroup.setName("RIP-test-group");

		ripGroup.addEndpointToRIPGroup(getRIPProtocolEndpoint("fe-0/0/2", "1"));
		ripGroup.addEndpointToRIPGroup(getRIPProtocolEndpoint("fe-0/0/2", "43"));

		return ripGroup;
	}

	private RIPProtocolEndpoint getRIPProtocolEndpoint(String logicalPortName, String logicalPortNumber) {

		RIPProtocolEndpoint endpoint = new RIPProtocolEndpoint();
		endpoint.setName(logicalPortName + "." + logicalPortNumber);
		return endpoint;
	}
}
