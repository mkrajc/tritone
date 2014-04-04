package org.mech.tritone.main.cmd.export.si;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.mech.tritone.music.model.TonePattern;
import org.mech.tritone.music.model.instrument.string.StringedInstrument;
import org.mech.tritone.music.model.instrument.string.StringedPitch;
import org.mech.tritone.music.service.string.StringInstrumentService;
import org.mech.tritone.render.context.FretboardContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class ExportFretboardCommand extends ExportStringInstrumentCommand<FretboardContext> {

	@Autowired
	private StringInstrumentService siService;

	@Override
	protected FretboardContext createContext(final CommandLine line, final List<String> subList) {
		final StringedInstrument si = prepareInstrument(line);
		final TonePattern tone = prepareTonePattern(subList);

		final List<StringedPitch> findAllPitchs = siService.findAllPitchs(si, tone.toTones());

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

		return context;
	}

}
