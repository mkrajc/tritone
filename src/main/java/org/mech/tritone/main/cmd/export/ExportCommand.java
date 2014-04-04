package org.mech.tritone.main.cmd.export;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.cli.CommandLine;
import org.mech.tritone.main.cmd.Command;
import org.mech.tritone.main.cmd.CommandException;
import org.mech.tritone.main.cmd.export.si.ExportFretboardCommand;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component("exportCmd")
public class ExportCommand implements Command{
	
	@Resource
	private ExportFretboardCommand exportFretboardCommand;

	@Override
	public void execute(final CommandLine commandLine, final List<String> argList) {
		if (CollectionUtils.isEmpty(argList)) {
			throw new CommandException("Command 'export' needs arguments. Use 'help export'");
		}
		
		exportFretboardCommand.execute(commandLine, argList);
	}

}
