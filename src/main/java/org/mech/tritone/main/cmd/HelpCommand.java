package org.mech.tritone.main.cmd;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.mech.tritone.main.arg.CliOptions;
import org.springframework.stereotype.Component;

@Component("helpCmd")
public class HelpCommand implements Command {

	@Override
	public void execute(final CommandLine commandLine, final List<String> list) {
		final HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("tritone", new CliOptions());
		System.exit(0);
	}

}
