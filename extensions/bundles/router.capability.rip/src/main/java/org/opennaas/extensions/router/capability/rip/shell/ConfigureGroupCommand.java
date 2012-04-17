package org.opennaas.extensions.router.capability.rip.shell;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.gogo.commands.Option;
import org.opennaas.core.resources.shell.GenericKarafCommand;

/**
 * @author Adrian Rosello
 */
@Command(scope = "rip", name = "configureGroup", description = "Configure a RIP Group")
public class ConfigureGroupCommand extends GenericKarafCommand {

	@Argument(index = 0, name = "resourceType:resourceName", description = "Name of the router to apply this command on", required = true, multiValued = false)
	private String	resourceId;

	@Argument(index = 1, name = "groupId", description = "RIP Group id.", required = true, multiValued = false)
	private String	areaId;

	@Option(name = "--delete", aliases = { "-d" }, description = "Delete given group, instead of creating it.")
	boolean			delete;

	@Override
	protected Object doExecute() throws Exception {
		// TODO implementation
		return null;
	}
}