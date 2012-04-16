package org.opennaas.extensions.router.model;

import java.io.Serializable;

/**
 * 
 * @author adrian
 * 
 */
public class RIPHostedGroup extends Dependency implements Serializable {

	public void RIPHostedGroup() {
	};

	/**
	 * This method creates an association between a RIPService and a RIPGroup.
	 * 
	 * @param antecedent
	 * @param dependent
	 * @return
	 */
	public static RIPHostedGroup link(RIPService antecedent, RIPGroup dependent) {
		return (RIPHostedGroup) Association.link(RIPHostedGroup.class, antecedent, dependent);
	}
}
