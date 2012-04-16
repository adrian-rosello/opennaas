package org.opennaas.extensions.router.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author adrian
 * 
 */
public class RIPLink extends ConnectivityCollection implements Serializable
{

	public enum LinkType {
		MCAST,
		BROADCAST,
		RIPv1,
		NONE,
	}

	private LinkType	linkType;
	private long		routerDeadInterval;
	private long		updateInterval;

	public RIPLink() {
	};

	public void setLinkType(LinkType type) {
		this.linkType = type;
	}

	public LinkType getLinkType() {
		return this.linkType;
	}

	public void setRouterDeadInterval(long interval) {
		this.routerDeadInterval = interval;
	}

	public long getRouterDeadInterval() {
		return this.routerDeadInterval;
	}

	public void setUpdateInterval(long interval) {
		this.updateInterval = interval;
	}

	public long getUpdateInterval() {
		return this.updateInterval;
	}

	public void addProtocolEndpoint(RIPProtocolEndpoint endpoint) {
		if (endpoint != null)
			RIPEndpointInLink.link(this, endpoint);
	}

	public void removeProtocolEndpoint(RIPProtocolEndpoint endpoint) {
		if (endpoint != null) {
			Association a = this.getFirstToAssociationByTypeAndElement(RIPEndpointInLink.class, endpoint);
			if (a != null)
				a.unlink();
		}
	}

	public List<RIPProtocolEndpoint> getProtocolEndpoints() {
		return (List<RIPProtocolEndpoint>) this.getFromAssociatedElementsByType(RIPEndpointInLink.class);
	}

}