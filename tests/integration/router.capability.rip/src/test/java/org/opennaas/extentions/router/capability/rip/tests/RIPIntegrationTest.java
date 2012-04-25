package org.opennaas.extentions.router.capability.rip.tests;

import static org.openengsb.labs.paxexam.karaf.options.KarafDistributionOption.keepRuntimeFolder;
import static org.opennaas.extensions.nexus.tests.helper.OpennaasExamOptions.includeFeatures;
import static org.opennaas.extensions.nexus.tests.helper.OpennaasExamOptions.noConsole;
import static org.opennaas.extensions.nexus.tests.helper.OpennaasExamOptions.opennaasDistributionConfiguration;
import static org.ops4j.pax.exam.CoreOptions.options;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.opennaas.core.resources.CorruptStateException;
import org.opennaas.core.resources.IResourceIdentifier;
import org.opennaas.core.resources.IncorrectLifecycleStateException;
import org.opennaas.core.resources.ResourceException;
import org.opennaas.core.resources.ResourceIdentifier;
import org.opennaas.core.resources.capability.CapabilityException;
import org.opennaas.core.resources.capability.ICapability;
import org.opennaas.core.resources.capability.ICapabilityFactory;
import org.opennaas.core.resources.descriptor.ResourceDescriptor;
import org.opennaas.core.resources.helpers.ResourceDescriptorFactory;
import org.opennaas.core.resources.mock.MockResource;
import org.opennaas.core.resources.protocol.IProtocolManager;
import org.opennaas.core.resources.protocol.ProtocolException;
import org.opennaas.core.resources.protocol.ProtocolSessionContext;
import org.opennaas.extensions.router.model.ComputerSystem;
import org.opennaas.extensions.router.model.RIPGroup;
import org.opennaas.extensions.router.model.RIPProtocolEndpoint;
import org.opennaas.extensions.router.repository.MantychoreBootstrapper;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.util.Filter;
import org.osgi.framework.BundleContext;
import org.osgi.service.blueprint.container.BlueprintContainer;

/**
 * 
 * @author adrian
 * 
 */
@RunWith(JUnit4TestRunner.class)
public abstract class RIPIntegrationTest {

	private static MockResource	mockResource;
	protected ICapability		queueCapability;
	protected ICapability		ripCapability;
	private final Log			log	= LogFactory
											.getLog(RIPIntegrationTest.class);
	@Inject
	private BundleContext		bundleContext;

	@Inject
	@Filter("(capability=queue)")
	private ICapabilityFactory	queueManagerFactory;

	@Inject
	@Filter("(capability=rip)")
	private ICapabilityFactory	ripFactory;

	@Inject
	private IProtocolManager	protocolManager;

	@Inject
	@Filter("(osgi.blueprint.container.symbolicname=org.opennaas.extensions.router.repository)")
	private BlueprintContainer	routerRepositoryService;

	@Inject
	@Filter("(osgi.blueprint.container.symbolicname=org.opennaas.extensions.queuemanager)")
	private BlueprintContainer	queueService;

	@Inject
	@Filter("(osgi.blueprint.container.symbolicname=org.opennaas.extensions.router.capability.rip)")
	private BlueprintContainer	ripService;

	@Configuration
	public static Option[] configuration() {
		return options(opennaasDistributionConfiguration(),
				includeFeatures("opennaas-router"),
				noConsole(),
				keepRuntimeFolder());
		// , new VMOption("-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"));

	}

	@Before
	public void initBundles()
			throws CapabilityException, IncorrectLifecycleStateException,
			ResourceException, CorruptStateException, ProtocolException
	{
		initResource();
		initCapability();
	}

	private void initResource() {
		mockResource = new MockResource();
		mockResource.setModel(new ComputerSystem());
		mockResource.setBootstrapper(new MantychoreBootstrapper());

		List<String> capabilities = new ArrayList<String>();

		capabilities.add("rip");
		capabilities.add("queue");

		ResourceDescriptor resourceDescriptor = ResourceDescriptorFactory.newResourceDescriptor("mockresource", "router", capabilities);

		mockResource.setResourceDescriptor(resourceDescriptor);

		IResourceIdentifier resourceIdentifier = new ResourceIdentifier(resourceDescriptor.getInformation().getType(), resourceDescriptor.getId());
		mockResource.setResourceIdentifier(resourceIdentifier);
	}

	/**
	 * Initialize the Queue and GRETunnel capabilies
	 */
	private void initCapability()
			throws CapabilityException, IncorrectLifecycleStateException,
			ResourceException, CorruptStateException, ProtocolException
	{
		log.info("INFO: Before Test, getting queue...");
		queueCapability = queueManagerFactory.create(mockResource);
		queueCapability.initialize();

		protocolManager.getProtocolSessionManagerWithContext(mockResource.getResourceId(), newSessionContextNetconf());

		log.info("Creating gretunnel capability");
		ripCapability = ripFactory.create(mockResource);
		ripCapability.initialize();

		mockResource.addCapability(queueCapability);
		mockResource.addCapability(ripCapability);
	}

	/**
	 * Configure the protocol to connect
	 */
	private ProtocolSessionContext newSessionContextNetconf() {
		String uri = System.getProperty("protocol.uri");
		if (uri == null || uri.equals("${protocol.uri}") || uri.isEmpty()) {
			uri = "mock://user:pass@host.net:2212/mocksubsystem";
		}
		log.debug("FFFF test setup uri: " + uri + "Length: " + uri.length());
		ProtocolSessionContext protocolSessionContext = new ProtocolSessionContext();

		protocolSessionContext.addParameter(ProtocolSessionContext.PROTOCOL_URI, uri);
		protocolSessionContext.addParameter(ProtocolSessionContext.PROTOCOL,
				"netconf");
		// ADDED
		return protocolSessionContext;
	}

	protected RIPGroup getRIPGroup(String groupName) {

		RIPGroup ripGroup = new RIPGroup();
		ripGroup.setName(groupName);
		ripGroup.addEndpointToRIPGroup(getInterface("fe-0/0/1", "5"));
		ripGroup.addEndpointToRIPGroup(getInterface("fe-0/1/1", "2"));

		return ripGroup;

	}

	private RIPProtocolEndpoint getInterface(String name, String port) {
		RIPProtocolEndpoint endpoint = new RIPProtocolEndpoint();
		endpoint.setName(name + "." + port);
		return endpoint;
	}
}