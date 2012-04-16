package org.opennaas.extensions.router.model;

import java.io.Serializable;

public class RIPConfiguration extends LogicalElement implements
		Serializable {

	public RIPConfiguration() {

	}

	public void setRIPGroup(RIPGroup group) {
		if (group != null)
			RIPGroupConfiguration.link(group, this);
	}

	public void unsetRIPGroup(RIPGroup group) {
		if (group != null) {
			Association a = this.getFirstFromAssociationByTypeAndElement(RIPGroupConfiguration.class, group);
			if (a != null)
				a.unlink();
		}
	}

	public RIPGroup getRIPGroup() {

		return (RIPGroup) this.getFirstFromAssociatedElementByType(RIPGroupConfiguration.class);
	}

}
