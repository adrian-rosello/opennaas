package org.opennaas.extensions.router.model;

import java.io.Serializable;

/**
 * 
 * @author adrian
 * 
 */
public class RIPProtocolEndpoint extends ProtocolEndpoint implements
		Serializable {

	public enum AuthType {
		OTHER,
		NULL_AUTHENTICATION,
		SIMPLE_PASSWORD,
		CRYPTOGRAPHIC_AUTHENTICATION
	}

	private AuthType	authType;
	private String		authKey;
	private long		retransmitInterval;

	public RIPProtocolEndpoint() {
	};

	public void setAuthKey(String key) {
		this.authKey = key;
	}

	public String getAuthKey() {
		return this.authKey;
	}

	public void setAuthType(AuthType type) {
		this.authType = type;
	}

	public AuthType getAuthType() {
		return this.authType;
	}

	public void setRetransmitInterval(long interval) {
		this.retransmitInterval = interval;
	}

	public long getRetransmitInterval() {
		return this.retransmitInterval;
	}

	public void setRIPGroup(RIPGroup group) {
		if (group != null) {
			EndpointInGroup.link(group, this);
		}
	}

	public void unsetRIPGroup(RIPGroup group) {
		if (group != null) {
			Association a = this.getFirstFromAssociationByTypeAndElement(EndpointInGroup.class, group);
			if (a != null)
				a.unlink();
		}
	}

	public RIPGroup getRIPGroup() {
		return (RIPGroup) this.getFirstFromAssociatedElementByType(EndpointInGroup.class);
	}
}
