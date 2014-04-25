package org.mech.tritone.main.cmd.export.si;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.mech.tritone.main.cmd.CommandException;
import org.mech.tritone.music.model.TonePattern;
import org.mech.tritone.music.model.instrument.string.StringedInstrument;
import org.mech.tritone.music.model.instrument.string.StringedPitch;
import org.mech.tritone.music.service.string.StringInstrumentService;
import org.mech.tritone.render.context.FretboardContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component("fretboardCmd")
public class FretboardCommand extends ExportStringInstrumentCommand<FretboardContext> {

	@Autowired
	private StringInstrumentService siService;

	@Override
	protected FretboardContext createContext(final CommandLine line, final List<String> subList) {
		final StringedInstrument si = prepareInstrument(line);
		final TonePattern tonePattern = prepareTonePattern(subList);

		final List<StringedPitch> findAllPitchs = siService.findAllPitchs(si, tonePattern);

		final FretboardContext context = new FretboardContext();
		if (!CollectionUtils.isEmpty(findAllPitchs)) {
			for (final StringedPitch pitch : findAllPitchs) {
				context.set(pitch.getStringIndex(), pitch.getPosition(), pitch);
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

	@Override
	protected void doExecute(final CommandLine commandLine, final List<String> subList) {
		if (CollectionUtils.isEmpty(subList)) {
			throw new CommandException("Command 'export' needs arguments. Use 'help export'");
		}
	}

}
