package org.mech.tritone.main.cmd.show;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.mech.tritone.main.cmd.AbstractCommand;
import org.mech.tritone.main.cmd.Command;
import org.mech.tritone.main.cmd.CommandException;
import org.mech.tritone.music.model.Pattern;
import org.mech.tritone.music.model.Tone;
import org.mech.tritone.music.model.TonePattern;
import org.mech.tritone.music.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component("showCmd")
public class ShowCommand extends AbstractCommand implements Command {

	@Override
	public void execute(final CommandLine commandLine, final List<String> argList) {
		if (CollectionUtils.isEmpty(argList)) {
			throw new CommandException("Command 'show' needs arguments. Use 'help show'");
		}
		
		if (argList.size() > 2) {
			throw new CommandException("Unsupported amount of parameters. Use 'help show'");
		}

		if (argList.size() == 1) {
			// must be pattern
			final String pttrn = argList.get(0);
			final Pattern pattern = getPattern(pttrn);
			writeln(StringUtils.toString(pattern.getIntervals()));
		}

		if (argList.size() == 2) {
			final TonePattern tp = prepareTonePattern(argList);
			final List<Tone> tones = tp.toTones();
			writeln(Tone.toString(tones.toArray(new Tone[]{})));
		}

	}


}
