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

	public void setRIPLink(RIPLink ripLink) {
		if (ripLink != null)
			RIPEndpointInLink.link(ripLink, this);
	}

	public void unsetRIPLink(RIPLink ripLink) {
		if (ripLink != null) {
			Association a = this.getFirstToAssociationByTypeAndElement(RIPEndpointInLink.class, ripLink);
			if (a != null)
				a.unlink();
		}
	}

}