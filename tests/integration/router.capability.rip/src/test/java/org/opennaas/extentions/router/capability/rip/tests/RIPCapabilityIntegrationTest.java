package org.opennaas.extentions.router.capability.rip.tests;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.opennaas.core.resources.capability.CapabilityException;
import org.opennaas.core.resources.command.Response;
import org.opennaas.core.resources.queue.QueueConstants;
import org.opennaas.core.resources.queue.QueueResponse;
import org.opennaas.extensions.router.capability.rip.IRIPService;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.EagerSingleStagedReactorFactory;

@ExamReactorStrategy(EagerSingleStagedReactorFactory.class)
public class RIPCapabilityIntegrationTest extends RIPIntegrationTest {

	private static Log		log			= LogFactory
												.getLog(RIPCapabilityIntegrationTest.class);

	private static String	GROUP_NAME	= "Test Group";

	@Test
	public void configureGroupTest() throws CapabilityException {
		log.info("Test CongigureRIPGroup method");
		IRIPService iRIPService = (IRIPService) ripCapability;
		Response resp = iRIPService.configureRIPGroup(getRIPGroup(GROUP_NAME));
		Assert.assertTrue(resp.getStatus() == Response.Status.QUEUED);
		Assert.assertTrue(resp.getErrors().size() == 0);
		QueueResponse queueResponse = (QueueResponse) queueCapability.sendMessage(QueueConstants.EXECUTE, null);
		Assert.assertTrue(queueResponse.isOk());
	}

}