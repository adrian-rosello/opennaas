package org.opennaas.extensions.router.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author adrian
 * 
 */
public class RIPGroup extends RoutingProtocolDomain implements Serializable {

	private String	groupID;

	public void setGroupID(String name) {
		this.groupID = name;
	}

	public String getGroupID() {
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