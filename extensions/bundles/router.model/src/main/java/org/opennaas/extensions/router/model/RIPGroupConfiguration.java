package org.opennaas.extensions.router.model;

import java.io.Serializable;

/**
 * 
 * @author adrian
 * 
 */
public class RIPGroupConfiguration extends Dependency implements Serializable {

	public RIPGroupConfiguration() {
	};

	/**
	 * This method creates an association between a RIP group and its configuration.
	 * 
	 * @param antecedent
	 * @param dependent
	 * @return
	 */
	public static RIPGroupConfiguration link(RIPGroup antecedent, RIPConfiguration dependent) {
		return (RIPGroupConfiguration) Association.link(RIPGroupConfiguration.class, antecedent, dependent);
	}
}