package org.opennaas.extensions.router.capability.rip.shell;

import java.util.List;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.gogo.commands.Option;
import org.opennaas.core.resources.shell.GenericKarafCommand;

/**
 * @author Adrian Rosello
 */
@Command(scope = "rip", name = "configureInterfaceInGroup", description = "Configure interface in RIP group")
public class ConfigureInterfaceInAreaCommand extends GenericKarafCommand {

	@Argument(index = 0, name = "resourceType:resourceName", description = "Name of the router to apply this command on", required = true, multiValued = false)
	private String			resourceId;

	@Argument(index = 1, name = "groupId", description = "RIP Group where to configure given interfaces.", required = true, multiValued = false)
	private String			areaId;

	@Argument(index = 2, name = "interfaceNames", description = "Interfaces to configure.", required = true, multiValued = true)
	private List<String>	interfaceNames;

	@Option(name = "--delete", aliases = { "-d" }, description = "Delete interfaces from given group, instead of adding them.")
	boolean					delete;

	@Override
	protected Object doExecute() throws Exception {
		// TODO implement command
		return null;
	}

}
