package org.mech.tritone.main.cmd.export.si;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.mech.tritone.main.arg.Arguments;
import org.mech.tritone.main.cmd.CommandException;
import org.mech.tritone.music.model.TonePattern;
import org.mech.tritone.music.model.instrument.finger.FingerType;
import org.mech.tritone.music.model.instrument.finger.FingeredPitch;
import org.mech.tritone.music.model.instrument.string.StringedInstrument;
import org.mech.tritone.music.service.string.StringInstrumentService;
import org.mech.tritone.render.context.FretboardContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component("fingerCmd")
public class FingerCommand extends ExportStringInstrumentCommand<FretboardContext> {

	@Autowired
	private StringInstrumentService siService;

	@Override
	protected FretboardContext createContext(final CommandLine line, final List<String> subList) {
		final StringedInstrument si = prepareInstrument(line);
		final TonePattern tonePattern = prepareTonePattern(subList);
		final FingerType finger = prepareFinger(line);

		final List<FingeredPitch> findAllPitchs = siService.findFingering(si, tonePattern, finger, 0);

		final FretboardContext context = new FretboardContext();
		if (!CollectionUtils.isEmpty(findAllPitchs)) {
			for (final FingeredPitch pitch : findAllPitchs) {
				context.set(pitch.getPitch().getStringIndex(), pitch.getPitch().getPosition(), pitch);
			}
		}

		context.setFretEnd(si.getLength());
		context.setFretStart(0);
		context.setRenderTuning(true);
		context.setStringCount(si.getStringsCount());
		context.setTuning(si.getTuning());
		context.setName(tonePattern.getRoot().format() + " " + tonePattern.getPattern().getName());

		return context;
	}

	private FingerType prepareFinger(final CommandLine commandLine) {
		final String fValue = commandLine.getOptionValue(Arguments.SI_FINGER);
		final FingerType finger = (fValue == null) ? FingerType.INDEX : FingerType.values()[Integer.valueOf(fValue)];
		return finger;
	}

	@Override
	protected void doExecute(final CommandLine commandLine, final List<String> subList) {
		if (CollectionUtils.isEmpty(subList)) {
			throw new CommandException("Command 'export' needs arguments. Use 'help export'");
		}
	}

}
