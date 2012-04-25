package org.opennaas.extensions.router.junos.actionssets.actions.rip;

import java.util.HashMap;
import java.util.Map;

import org.opennaas.core.resources.action.ActionException;
import org.opennaas.core.resources.action.ActionResponse;
import org.opennaas.core.resources.command.Response;
import org.opennaas.core.resources.protocol.IProtocolSession;
import org.opennaas.extensions.router.junos.actionssets.ActionConstants;
import org.opennaas.extensions.router.junos.actionssets.actions.JunosAction;
import org.opennaas.extensions.router.junos.commandsets.commands.EditNetconfCommand;
import org.opennaas.extensions.router.model.ComputerSystem;
import org.opennaas.extensions.router.model.RIPGroup;

/**
 * 
 * @author Adrian Rosello
 * 
 */
public class RemoveRIPGroupAction extends JunosAction {

	private static final String	VELOCITY_TEMPLATE	= "/VM_files/ripRemoveGroup.vm";

	private static final String	PROTOCOL_NAME		= "netconf";

	public RemoveRIPGroupAction() {
		setActionID(ActionConstants.RIP_REMOVE_GROUP);
		setTemplate(VELOCITY_TEMPLATE);
		this.protocolName = PROTOCOL_NAME;
	}

	@Override
	public void executeListCommand(ActionResponse actionResponse, IProtocolSession protocol) throws ActionException {
		try {
			EditNetconfCommand command = new EditNetconfCommand(getVelocityMessage());
			command.initialize();
			Response response = sendCommandToProtocol(command, protocol);
			actionResponse.addResponse(response);
		} catch (Exception e) {
			throw new ActionException(this.actionID, e);
		}
		validateAction(actionResponse);
	}

	@Override
	public void parseResponse(Object responseMessage, Object model) throws ActionException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkParams(Object params) throws ActionException {
		if ((params == null) || !(params instanceof RIPGroup))
			throw new ActionException("Invalid param for " + this.actionID + "action.");
		return true;
	}

	@Override
	public void prepareMessage() throws ActionException {
		// TODO Auto-generated method stub
		checkParams(params);
		checkTemplate(template);
		try {
			Map<String, Object> extraParams = new HashMap<String, Object>();
			String elementName = "";
			if (((ComputerSystem) modelToUpdate).getElementName() != null) {
				// is logicalRouter, add LRName param
				elementName = ((ComputerSystem) modelToUpdate).getElementName();
			}
			extraParams.put("elementName", elementName);
			setVelocityMessage(prepareVelocityCommand(params, template, extraParams));

		} catch (Exception e) {
			throw new ActionException(e);
		}
	}

	private void checkTemplate(String template) throws ActionException {

		// The template can not be null or empty
		if (template == null || !(template.equals(VELOCITY_TEMPLATE)))
			throw new ActionException("Invalid template for " + this.actionID + "action.");

	}

}