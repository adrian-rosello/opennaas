package org.opennaas.extensions.router.junos.actionssets;

import java.util.ArrayList;
import java.util.List;

import org.opennaas.core.resources.action.ActionSet;
import org.opennaas.extensions.router.junos.actionssets.actions.GetConfigurationAction;
import org.opennaas.extensions.router.junos.actionssets.actions.rip.ConfigureRIPGroupAction;

public class RIPActionSet extends ActionSet {

	public RIPActionSet() {
		super.setActionSetId("ripActionSet");

		this.putAction(ActionConstants.GETCONFIG, GetConfigurationAction.class);
		this.putAction(ActionConstants.RIP_CONFIGURE_GROUP, ConfigureRIPGroupAction.class);

		/* add refresh actions */
		this.refreshActions.add(ActionConstants.GETCONFIG);
	}

	@Override
	public List<String> getActionNames() {
		List<String> actionNames = new ArrayList<String>();

		actionNames.add(ActionConstants.GETCONFIG);
		actionNames.add(ActionConstants.RIP_CONFIGURE_GROUP);
		return actionNames;
	}
}