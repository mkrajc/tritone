package org.mech.tritone.main.cmd;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.cli.CommandLine;
import org.mech.tritone.main.arg.Arguments;

public class CommandLineUtils {
	private static PrintWriter writer;
	
	public static PrintWriter getWriter(){
		return writer;
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
}
