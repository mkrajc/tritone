package org.mech.tritone.main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.mech.tritone.main.arg.Arguments;
import org.mech.tritone.main.arg.CliOptions;
import org.mech.tritone.main.cmd.Command;
import org.mech.tritone.main.cmd.CommandException;
import org.mech.tritone.main.cmd.CommandLineUtils;
import org.mech.tritone.main.cmd.ListAllChordsCommand;
import org.mech.tritone.main.cmd.ListAllScalesCommand;
import org.mech.tritone.main.cmd.ListAllTonesCommand;
import org.mech.tritone.main.cmd.ListAllTuningsCommand;
import org.mech.tritone.main.cmd.RenderCommand;
import org.mech.tritone.main.cmd.render.RenderAllNotesOnStringCommand;
import org.mech.tritone.main.cmd.show.ShowPatternNotesCommand;
import org.mech.tritone.music.model.Pattern;
import org.mech.tritone.music.model.Tone;
import org.mech.tritone.music.model.instrument.string.Tuning;
import org.mech.tritone.music.service.MusicDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component("Main")
public class Main {

	private static final String DEFAULT_TUNING = "guitar";
	private static final String DEFAULT_PATTERN = "major";
	private static final String DEFAULT_TONE = "C";
	private static final String DEFAULT_FRET_LENGTH = "21";

	@Autowired
	private ListAllChordsCommand listAllChordsCommand;

	@Autowired
	private ListAllScalesCommand listAllScalesCommand;

	@Autowired
	private ListAllTuningsCommand listAllTuningsCommand;

	@Autowired
	private ListAllTonesCommand listAllTonesCommand;

	@Autowired
	private RenderCommand renderCommand;

	@Autowired
	private ShowPatternNotesCommand command;

	@Autowired
	private RenderAllNotesOnStringCommand renderAllNotesOnStringCommand;

	@Autowired
	private MusicDataService dataService;

	@Resource(name = "commandMap")
	private Map<String, Command> commandDispatcher;

	public void process(final String... args) {
		final CliOptions options = new CliOptions();
		final CommandLineParser parser = new GnuParser();

		if (args.length == 0) {
			help(options);
		}

		final Command command = commandDispatcher.get(args[0]);

		if (command == null) {
			System.err.println("Unknown command '" + args[0] + "'. Use help for help.");
			System.exit(1);
		}

		try {
			final CommandLine line = parser.parse(options, args);

			// get rid of first command
			final List<String> subList = line.getArgList().subList(1, line.getArgList().size());

			// prepare stuff
			CommandLineUtils.prepareWriter(line);

			command.execute(line, subList);
			
			CommandLineUtils.getWriter().close();

			// if (line.hasOption(Arguments.HELP)) {
			// help(options);
			// }
			//
			// if (line.hasOption(Arguments.LIST_CHORDS)) {
			// listAllChordsCommand.execute();
			// System.exit(0);
			// }
			//
			// if (line.hasOption(Arguments.LIST_SCALE)) {
			// listAllScalesCommand.execute();
			// System.exit(0);
			// }
			//
			// if (line.hasOption(Arguments.LIST_TUNING)) {
			// listAllTuningsCommand.execute();
			// System.exit(0);
			// }
			//
			// if (line.hasOption(Arguments.LIST_TONE)) {
			// listAllTonesCommand.execute();
			// System.exit(0);
			// }
			// command.execute(prepareWriter(line), new
			// TonePattern(preparePattern(line), prepareTone(line)));
			// renderAllNotesOnStringCommand.execute(prepareWriter(line), new
			// TonePattern(preparePattern(line), prepareTone(line)),
			// prepareTuning(line), prepareFretLength(line));
			System.exit(0);

		} catch (final ParseException exp) {
			System.err.println("Parsing failed.  Reason: " + exp.getMessage());
			System.exit(1);
		} catch (final IOException e) {
			System.err.println("IO exception.  Reason: " + e.getMessage());
			System.exit(1);
		} catch (final CommandException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

	private PrintWriter prepareWriter(final CommandLine commandLine) throws IOException {
		final String path = commandLine.getOptionValue(Arguments.FILE_PATH);

		if (path != null) {
			final File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			return new PrintWriter(file);
		}
		return new PrintWriter(System.out);
	}

	private Tuning prepareTuning(final CommandLine commandLine) throws ParseException {
		final String tuningValue = commandLine.getOptionValue(Arguments.TUNING, DEFAULT_TUNING);

		final Tuning tuning = dataService.getTuning(tuningValue);

		if (tuning == null) {
			throw new ParseException(String.format(
					"Tuning '%s' is not valid. Please select one, for all tunings use -%s", tuningValue,
					Arguments.LIST_TUNING));
		}
		return tuning;
	}

	private Pattern preparePattern(final CommandLine commandLine) throws ParseException {
		final String pttrnValue = commandLine.getOptionValue(Arguments.PATTERN, DEFAULT_PATTERN);

		final Pattern pattern = dataService.getPattern(pttrnValue);

		if (pttrnValue == null) {
			throw new ParseException(String.format(
					"Pattern '%s' is not valid. Please select one, for all chords use -%s and for all scales -%s",
					commandLine.getOptionValue(Arguments.PATTERN), Arguments.LIST_CHORDS, Arguments.LIST_SCALE));
		}
		return pattern;
	}

	private Tone prepareTone(final CommandLine commandLine) throws ParseException {
		final String toneValue = commandLine.getOptionValue(Arguments.TONE, DEFAULT_TONE);
		try {
			return Tone.valueOf(toneValue.toUpperCase());
		} catch (final IllegalArgumentException ex) {
			throw new ParseException(String.format("Tone '%s' is not valid. Please select one, for all tones use -%s",
					toneValue, Arguments.LIST_TONE));
		}
	}

	private int prepareFretLength(final CommandLine commandLine) throws ParseException {
		final String fretLengthValue = commandLine.getOptionValue(Arguments.FRET, DEFAULT_FRET_LENGTH);
		try {
			return Integer.valueOf(fretLengthValue);
		} catch (final NumberFormatException ex) {
			throw new ParseException(String.format("Fret length '%s' is not valid. Please provide number.",
					fretLengthValue));
		}
	}

	private void help(final Options options) {
		final HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("tritone", options);
		System.exit(0);
	}

	public static void main(final String[] args) throws Exception {
		final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/context.xml");

		final Main main = (Main) applicationContext.getBean("Main");
		main.process(args);

	}

}
