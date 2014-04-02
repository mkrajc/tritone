package org.mech.tritone.main.cmd.show;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.ParseException;
import org.mech.tritone.main.arg.CliOptions;
import org.mech.tritone.main.cmd.AbstractCommand;
import org.mech.tritone.main.cmd.Command;
import org.mech.tritone.main.cmd.CommandException;
import org.mech.tritone.music.model.Pattern;
import org.mech.tritone.music.model.Tone;
import org.mech.tritone.music.model.TonePattern;
import org.mech.tritone.music.service.MusicDataService;
import org.mech.tritone.music.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component("showCmd")
public class ShowCommand extends AbstractCommand implements Command {

	@Autowired
	private MusicDataService musicService;

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
			final String toneString = argList.get(0);
			final Tone tone = getTone(toneString);
			
			final String pttrn = argList.get(1);
			final Pattern pattern = getPattern(pttrn);
			
			final TonePattern tp = new TonePattern(pattern, tone);
			
			final List<Tone> tones = tp.toTones();
			writeln(StringUtils.toString(tones.toArray()));
		}

	}

	private Tone getTone(final String toneString) {
		Tone t;
		try {
			t = musicService.getTone(toneString);
		} catch (final IllegalArgumentException argumentException) {
			throw new CommandException("Unknown tone '" + toneString + "'");
		}
		return t;
	}

	private Pattern getPattern(final String pttrn) {
		// must be pattern
		final Pattern pattern = musicService.getPattern(pttrn);

		if (pattern == null) {
			throw new CommandException(String.format("Pattern '%s' is unknown", pttrn));
		}

		return pattern;
	}

	public static void main(final String... args) throws ParseException {
		final CliOptions options = new CliOptions();
		final CommandLineParser parser = new GnuParser();
		final CommandLine line = parser.parse(options, new String[] { "-f", "10", "show", "A", "major" });

		final String[] args2 = line.getArgs();
		for (final String s : args2) {
			System.out.println(s);
		}

		System.out.println(line.getOptionValue("f", "1"));
	}

}
