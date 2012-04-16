package org.opennaas.extensions.router.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author adrian
 * 
 */
public class RIPService extends Service implements Serializable {

	public RIPService() {
	};

	public void addRIPGroup(RIPGroup group) {

		if (group != null) {
			RIPHostedGroup.link(this, group);
		}
	}

	public void removeRIPGroup(RIPGroup group) {

		if (group != null) {
			Association a = this.getFirstToAssociationByTypeAndElement(RIPHostedGroup.class, group);
			if (a != null)
				a.unlink();
		}
	}

	public List<RIPGroup> getRIPGroups() {

		return (List<RIPGroup>) this.getToAssociatedElementsByType(RIPHostedGroup.class);
	}
}