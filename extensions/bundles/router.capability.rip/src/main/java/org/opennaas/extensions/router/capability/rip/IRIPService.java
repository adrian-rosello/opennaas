package org.opennaas.extensions.router.capability.rip;

import java.util.List;

import org.opennaas.core.resources.command.Response;
import org.opennaas.extensions.router.model.RIPGroup;
import org.opennaas.extensions.router.model.RIPProtocolEndpoint;
import org.opennaas.extensions.router.model.RIPService;

/**
 * 
 * @author Adrian Rosello
 * 
 */
public interface IRIPService {

	/**
	 * Adds all interfaces of the list to the given RIP group.
	 * 
	 * @param endpointList
	 * @param ripGroup
	 * @return
	 */
	public Response addInterfacesToRIPGroup(List<RIPProtocolEndpoint> endpointList, RIPGroup ripGroup);

	/**
	 * Removes all the RIP configuration.
	 * 
	 * @return
	 */
	public Response clearRIPConfiguration();

	/**
	 * Creates a RIP Service.
	 * 
	 * @param ripService
	 * @return
	 */
	public Response configureRIP(RIPService ripService);

	/**
	 * Creates a RIP Group.
	 * 
	 * @param ripGroup
	 * @return
	 */
	public Response configureRIPGroup(RIPGroup ripGroup);

	/**
	 * Retrieves all the RIP configuration from the router.
	 * 
	 * @return
	 */
	public Response getRIPConfiguration();

	/**
	 * Removes all interfaces of the list from the given RIP Group.
	 * 
	 * @param endpointList
	 * @param ripGroup
	 * @return
	 */
	public Response removeInterfacesFromRIPGroup(List<RIPProtocolEndpoint> endpointList, RIPGroup ripGroup);

	/**
	 * Removes a RIP Group.
	 * 
	 * @param ripGroup
	 * @return
	 */
	public Response removeRIPGroup(RIPGroup ripGroup);

	/**
	 * Retrieves all the RIP configuration from the model.
	 * 
	 * @return
	 */
	public Response showRIPConfiguration();
}
