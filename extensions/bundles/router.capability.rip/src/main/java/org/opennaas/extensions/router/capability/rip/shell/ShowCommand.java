package org.opennaas.extensions.router.capability.rip.shell;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.opennaas.core.resources.shell.GenericKarafCommand;

/**
 * @author Adrian Rosello
 */
@Command(scope = "rip", name = "show", description = "Shows full RIP configuration.")
public class ShowCommand extends GenericKarafCommand {

	@Argument(index = 0, name = "resourceType:resourceName", description = "Name of the router to activate RIP on", required = true, multiValued = false)
	private String	resourceId;

	@Override
	protected Object doExecute() throws Exception {
		// TODO implementation
		return null;
	}

}