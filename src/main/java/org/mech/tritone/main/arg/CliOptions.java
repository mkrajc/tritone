package org.mech.tritone.main.arg;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

public class CliOptions extends Options {

	private static final long serialVersionUID = -247731228373332586L;

	@SuppressWarnings("static-access")
	public CliOptions() {
		super();
		addOption(new Option("h", "help", false,  "print this message"));
		
		
		addOption(new Option(Arguments.LIST_CHORDS, Arguments.LIST_CHORDS_LONG, false, "list all chord pattern names"));
		addOption(new Option(Arguments.LIST_SCALE, Arguments.LIST_SCALE_LONG, false, "list all scale pattern names"));
		addOption(new Option(Arguments.LIST_TUNING, Arguments.LIST_TUNING_LONG, false, "list all tuning names"));
		
		addOption(OptionBuilder.withLongOpt(Arguments.TUNING_LONG)
				.withDescription("tuning definition").hasArg()
				.withArgName("TUNING").create(Arguments.TUNING));

		addOption(OptionBuilder.withLongOpt(Arguments.PATTERN_LONG)
				.withDescription("pattern definition").hasArg()
				.withArgName("CHORD|SCALE").create(Arguments.PATTERN));
		
		addOption(OptionBuilder.withLongOpt(Arguments.TONE_LONG)
				.withDescription("letter representing tone ").hasArg()
				.withArgName("TONE").create(Arguments.TONE));
		
		addOption(new Option(Arguments.HTML_PATH, true, "path to generated html file"));
		addOption(new Option(Arguments.EXPORT_PATERN_HTML, Arguments.EXPORT_PATERN_HTML_LONG, false, "export pattern html"));
	}

}
