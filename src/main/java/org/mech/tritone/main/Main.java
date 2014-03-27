package org.mech.tritone.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.mech.tritone.main.arg.Arguments;
import org.mech.tritone.main.arg.CliOptions;
import org.mech.tritone.main.cmd.ListAllChordsCommand;
import org.mech.tritone.main.cmd.ListAllScalesCommand;
import org.mech.tritone.main.cmd.ListAllTonesCommand;
import org.mech.tritone.main.cmd.ListAllTuningsCommand;
import org.mech.tritone.main.cmd.RenderCommand;
import org.mech.tritone.music.model.Pattern;
import org.mech.tritone.music.model.Tone;
import org.mech.tritone.music.model.instrument.Tuning;
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
	private MusicDataService dataService;

	public void process(final String... args) {
		final CliOptions options = new CliOptions();
		final CommandLineParser parser = new GnuParser();

		try {
			final CommandLine line = parser.parse(options, args);

			if (line.hasOption(Arguments.HELP)) {
				help(options);
			}

			if (line.hasOption(Arguments.LIST_CHORDS)) {
				listAllChordsCommand.execute();
				System.exit(0);
			}

			if (line.hasOption(Arguments.LIST_SCALE)) {
				listAllScalesCommand.execute();
				System.exit(0);
			}

			if (line.hasOption(Arguments.LIST_TUNING)) {
				listAllTuningsCommand.execute();
				System.exit(0);
			}

			if (line.hasOption(Arguments.LIST_TONE)) {
				listAllTonesCommand.execute();
				System.exit(0);
			}

			renderCommand.execute(prepareWriter(line), preparePattern(line), prepareTuning(line), prepareTone(line));
			System.exit(0);

		} catch (ParseException exp) {
			System.err.println("Parsing failed.  Reason: " + exp.getMessage());
			System.exit(1);
		} catch (IOException e) {
			System.err.println("IO exception.  Reason: " + e.getMessage());
			System.exit(1);
		}
	}

	private Writer prepareWriter(final CommandLine commandLine) throws IOException {
		final String path = commandLine.getOptionValue(Arguments.FILE_PATH);

		if (path != null) {
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			return new FileWriter(file);
		}
		return new PrintWriter(System.out);
	}

	private Tuning prepareTuning(final CommandLine commandLine) throws ParseException {
		final String tuningValue = commandLine.getOptionValue(Arguments.TUNING, DEFAULT_TUNING);

		final Tuning tuning = dataService.getTuning(tuningValue);

		if (tuning == null) {
			throw new ParseException(String.format("Tuning '%s' is not valid. Please select one, for all tunings use -%s", tuningValue,
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
		} catch (IllegalArgumentException ex) {
			throw new ParseException(String.format("Tone '%s' is not valid. Please select one, for all tones use -%s", toneValue,
					Arguments.LIST_TONE));
		}
	}

	private void help(final Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("tritone", options);
		System.exit(0);
	}

	public static void main(final String[] args) throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/context.xml");

		final Main main = (Main) applicationContext.getBean("Main");
		main.process(args);

	}

}
