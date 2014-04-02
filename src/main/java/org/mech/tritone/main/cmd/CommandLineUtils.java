package org.mech.tritone.main.cmd;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.mech.tritone.main.arg.Arguments;
import org.mech.tritone.music.utils.Notation;
import org.mech.tritone.music.utils.StringUtils;

public class CommandLineUtils {
	private static final String DEFAULT_NOTATION = Notation.US.name();

	private static Notation notation = Notation.US;
	private static PrintWriter writer;

	public static PrintWriter getWriter() {
		return writer;
	}

	public static Notation getNotation() {
		return notation;
	}

	public static void prepareWriter(final CommandLine commandLine) throws IOException {
		final String path = commandLine.getOptionValue(Arguments.FILE_PATH);

		if (path != null) {
			final File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			CommandLineUtils.writer = new PrintWriter(file);
		}
		CommandLineUtils.writer = new PrintWriter(System.out);
	}

	public static void prepareNotation(final CommandLine commandLine) throws ParseException {
		final String notationValue = commandLine.getOptionValue(Arguments.NOTATION, DEFAULT_NOTATION);
		try {
			notation = Notation.valueOf(notationValue.toUpperCase());
		} catch (final IllegalArgumentException ex) {
			throw new ParseException(String.format("Notation '%s' is not valid. Valid values are: %s", notationValue,
					StringUtils.toString(Notation.values()).toLowerCase()));
		}
	}

}
