package org.mech.tritone.main.cmd;

import java.util.List;

import org.apache.commons.cli.CommandLine;

public interface Command {
	void execute(CommandLine commandLine, List<String> subList);
}
