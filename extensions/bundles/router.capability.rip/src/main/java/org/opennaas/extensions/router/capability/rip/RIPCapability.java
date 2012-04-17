package org.opennaas.extensions.router.capability.rip;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.opennaas.core.resources.action.IActionSet;
import org.opennaas.core.resources.capability.AbstractCapability;
import org.opennaas.core.resources.capability.CapabilityException;
import org.opennaas.core.resources.command.Response;
import org.opennaas.core.resources.descriptor.CapabilityDescriptor;
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
	public Object sendMessage(String arg0, Object arg1) throws CapabilityException {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public Response configureRIPGroup(RIPGroup ripGroup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getRIPConfiguration() {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public IActionSet getActionSet() throws CapabilityException {
		// TODO Auto-generated method stub
		return null;
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