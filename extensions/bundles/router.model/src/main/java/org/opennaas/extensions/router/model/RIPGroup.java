package org.opennaas.extensions.router.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author adrian
 * 
 */
public class RIPGroup extends RoutingProtocolDomain implements Serializable {

	private long	groupID;

	public void setGroupID(long id) {
		this.groupID = id;
	}

	public long getGroupID() {
		return this.groupID;
	}

	public void setRIPService(RIPService service) {
		if (service != null)
			RIPHostedGroup.link(service, this);
	}

	public void unsetRIPService(RIPService service) {
		if (service != null) {
			Association a = this.getFirstFromAssociationByTypeAndElement(RIPHostedGroup.class, service);
			if (a != null)
				a.unlink();
		}

	}

	public RIPService getRIPService() {
		return (RIPService) this.getFirstFromAssociatedElementByType(RIPHostedGroup.class);
	}

	public void setRIPGroupConfiguration(RIPConfiguration config) {
		if (config != null)
			RIPGroupConfiguration.link(this, config);
	}

	public void unsetRIPGroupConfiguration(RIPConfiguration config) {
		if (config != null) {
			Association a = this.getFirstToAssociationByTypeAndElement(RIPGroupConfiguration.class, config);
			if (a != null)
				a.unlink();
		}
	}

	public RIPConfiguration getRIPGroupConfiguration() {
		return (RIPConfiguration) this.getFirstToAssociatedElementByType(RIPGroupConfiguration.class);
	}

	public void addEndpointToRIPGroup(RIPProtocolEndpoint endpoint) {
		if (endpoint != null)
			EndpointInGroup.link(this, endpoint);
	}

	public void removeEndpointFromRIPGroup(RIPProtocolEndpoint endpoint) {

		if (endpoint != null) {
			Association a = this.getFirstFromAssociationByTypeAndElement(EndpointInGroup.class, endpoint);
			if (a != null)
				a.unlink();
		}
	}

	public List<RIPProtocolEndpoint> getRIPProtocolEndpoints() {
		return (List<RIPProtocolEndpoint>) this.getToAssociatedElementsByType(EndpointInGroup.class);
	}

}