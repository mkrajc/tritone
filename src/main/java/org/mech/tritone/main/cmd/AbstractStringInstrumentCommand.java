package org.mech.tritone.main.cmd;

import javax.annotation.Resource;

import org.apache.commons.cli.CommandLine;
import org.mech.tritone.main.arg.Arguments;
import org.mech.tritone.music.model.instrument.string.StringedInstrument;
import org.mech.tritone.music.model.instrument.string.Tuning;

public abstract class AbstractStringInstrumentCommand extends AbstractCommand {

	@Resource(name = "tuningGuitar6")
	private Tuning defaulTtuning;

	private static final String DEFAULT_FRET_LENGTH = "22";

	private static final int MIN_FRET = 0;
	private static final int MAX_FRET = 100;

	protected StringedInstrument prepareInstrument(final CommandLine line) {
		return new StringedInstrument(prepareTuning(line), prepareFretLength(line));
	}

	private Tuning prepareTuning(final CommandLine commandLine) throws CommandException {
		final String tuningValue = commandLine.getOptionValue(Arguments.SI_TUNING);
		final Tuning tuning = (tuningValue == null) ? defaulTtuning : musicService.getTuning(tuningValue);

		if (tuning == null) {
			throw new CommandException(String.format(
					"Tuning '%s' is not valid. Please select one, for all tunings use -%s", tuningValue,
					Arguments.LIST_TUNING));
		}

		return tuning;
	}

	private int prepareFretLength(final CommandLine commandLine) throws CommandException {
		final String fretLengthValue = commandLine.getOptionValue(Arguments.SI_FRET, DEFAULT_FRET_LENGTH);
		try {
			final Integer fret = Integer.valueOf(fretLengthValue);
			if (fret <= MIN_FRET && fret > MAX_FRET) {
				throw new CommandException(String.format(
						"Fret length '%s' is not in scope. Please provide number between %s and %s.", fretLengthValue,
						MIN_FRET, MAX_FRET));
			}
			return fret;
		} catch (final NumberFormatException ex) {
			throw new CommandException(String.format("Fret length '%s' is not valid. Please provide number.",
					fretLengthValue));
		}
	}

}
