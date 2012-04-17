package org.opennaas.extensions.router.capability.rip.shell;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.gogo.commands.Option;
import org.opennaas.core.resources.shell.GenericKarafCommand;

/**
 * @author Adrian Rosello
 */
@Command(scope = "rip", name = "configure", description = "Configure a RIP service.")
public class ConfigureCommand extends GenericKarafCommand {

	@Argument(index = 0, name = "resourceType:resourceName", description = "Name of the router to apply this command on", required = true, multiValued = false)
	private String	resourceId;

	@Option(name = "--delete", aliases = { "-d" }, description = "Delete RIP service, instead of creating it.")
	boolean			delete;

	@Override
	protected Object doExecute() throws Exception {
		// TODO implement karaf command
		return null;
	}

}