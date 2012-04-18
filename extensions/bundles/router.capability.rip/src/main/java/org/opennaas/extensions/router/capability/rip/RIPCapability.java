package org.opennaas.extensions.router.capability.rip;

import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.opennaas.core.resources.ActivatorException;
import org.opennaas.core.resources.action.IAction;
import org.opennaas.core.resources.action.IActionSet;
import org.opennaas.core.resources.capability.AbstractCapability;
import org.opennaas.core.resources.capability.CapabilityException;
import org.opennaas.core.resources.command.Response;
import org.opennaas.core.resources.descriptor.CapabilityDescriptor;
import org.opennaas.core.resources.descriptor.ResourceDescriptorConstants;
import org.opennaas.extensions.queuemanager.IQueueManagerService;
import org.opennaas.extensions.router.junos.actionssets.ActionConstants;
import org.opennaas.extensions.router.model.RIPGroup;
import org.opennaas.extensions.router.model.RIPProtocolEndpoint;
import org.opennaas.extensions.router.model.RIPService;

/**
 * 
 * @author Adrian Rosello
 * 
 */
public class RIPCapability extends AbstractCapability implements IRIPService {

	public static String	CAPABILITY_NAME	= "rip";

	Log						log				= LogFactory.getLog(RIPCapability.class);

	private String			resourceId		= "";

	public RIPCapability(CapabilityDescriptor descriptor, String resourceId) {
		super(descriptor);
		this.resourceId = resourceId;
		log.debug("Built new RIP Capability");

	}

	@Override
	public Object sendMessage(String idOperation, Object params) {

		// log.debug("Sending message to RIP Capability");
		try {
			IQueueManagerService queueManager = Activator.getQueueManagerService(resourceId);
			IAction action = createAction(idOperation);
			action.setParams(params);
			action.setModelToUpdate(resource.getModel());
			queueManager.queueAction(action);

		} catch (Exception e) {
			Vector<String> errorMsgs = new Vector<String>();
			errorMsgs
					.add(e.getMessage() + ":" + '\n' + e.getLocalizedMessage());
			return Response.errorResponse(idOperation, errorMsgs);
		}

		return Response.queuedResponse(idOperation);
	}

	@Override
	public Response addInterfacesToRIPGroup(List<RIPProtocolEndpoint> endpointList, RIPGroup ripGroup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response clearRIPConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response configureRIP(RIPService ripService) {
		// TODO Auto-generated method stub
		return null;
	}

	public Response configureRIPGroup(RIPGroup ripGroup) throws CapabilityException {

		return (Response) sendMessage(ActionConstants.RIP_CONFIGURE_GROUP, ripGroup);
	}

	@Override
	public Response removeInterfacesFromRIPGroup(List<RIPProtocolEndpoint> endpointList, RIPGroup ripGroup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response removeRIPGroup(RIPGroup ripGroup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response showRIPConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void activateCapability() throws CapabilityException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void deactivateCapability() throws CapabilityException {
		// TODO Auto-generated method stub

	}

	/**
	 * Return the RIP ActionSet
	 */
	public IActionSet getActionSet() throws CapabilityException {

		String name = this.descriptor.getPropertyValue(ResourceDescriptorConstants.ACTION_NAME);
		String version = this.descriptor.getPropertyValue(ResourceDescriptorConstants.ACTION_VERSION);

		try {
			return Activator.getRIPActionSetService(name, version);
		} catch (ActivatorException e) {
			throw new CapabilityException(e);
		}
	}

	@Override
	protected void initializeCapability() throws CapabilityException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void shutdownCapability() throws CapabilityException {
		// TODO Auto-generated method stub

	}
}