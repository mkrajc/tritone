package org.mech.tritone.main.cmd.render;

import java.io.PrintWriter;
import java.util.List;

import org.mech.tritone.music.model.TonePattern;
import org.mech.tritone.music.model.instrument.string.StringedInstrument;
import org.mech.tritone.music.model.instrument.string.StringedPitch;
import org.mech.tritone.music.model.instrument.string.Tuning;
import org.mech.tritone.music.service.string.StringInstrumentService;
import org.mech.tritone.render.Format;
import org.mech.tritone.render.RendererDispatcher;
import org.mech.tritone.render.instrument.string.StringInstrumentPitchRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RenderAllNotesOnStringCommand {

	@Autowired
	private StringInstrumentService stringInstrumentService;

	@Autowired
	private RendererDispatcher rendererDispatcher;

	public void execute(final PrintWriter writer, final TonePattern pattern, final Tuning tuning, final int length) {
		final StringedInstrument instrument = new StringedInstrument(tuning, length);
		final List<StringedPitch> allTones = stringInstrumentService.findAllPitchs(instrument, pattern.toTones());
		final StringInstrumentPitchRenderer.Context context = new StringInstrumentPitchRenderer.Context();
		context.setPitchs(allTones);
		context.setInstrument(instrument);
		rendererDispatcher.dispatchRender(Format.TAB, writer, context);
	}

}
