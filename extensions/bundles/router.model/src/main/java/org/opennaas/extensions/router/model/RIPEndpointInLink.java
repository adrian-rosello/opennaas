package org.opennaas.extensions.router.model;

import java.io.Serializable;

public class RIPEndpointInLink extends MemberOfCollection implements Serializable {

	public RIPEndpointInLink() {
	};

	public static EndpointInLink link(RIPLink antecedent, RIPProtocolEndpoint dependent) {
		return (EndpointInLink) Association.link(EndpointInLink.class, antecedent, dependent);
	}// RIP link
}