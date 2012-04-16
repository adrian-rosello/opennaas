package org.opennaas.extensions.router.model;

import java.io.Serializable;

/**
 * 
 * @author adrian
 * 
 */
public class EndpointInGroup extends Component implements Serializable {

	public EndpointInGroup() {
	};

	public static EndpointInGroup link(RIPGroup antecedent, RIPProtocolEndpoint dependent) {
		return (EndpointInGroup) Association.link(EndpointInGroup.class, antecedent, dependent);
	}

}
