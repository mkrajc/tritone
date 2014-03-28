package org.mech.tritone.render.pure.instrument.string;

import java.io.PrintWriter;

import org.mech.tritone.music.model.instrument.string.StringedPitch;
import org.mech.tritone.render.Format;
import org.mech.tritone.render.instrument.string.StringInstrumentPitchRenderer;

public class PureStringInstrumentPitchRenderer extends StringInstrumentPitchRenderer {

	public PureStringInstrumentPitchRenderer() {
		super(Format.PURE);
	}

	@Override
	public void render(final PrintWriter writer, final StringInstrumentPitchRenderer.Context context) {
		for (final StringedPitch pitch : context.getPitchs()) {
			writer.print(pitch);
			writer.print(" ");
		}
		writer.println();
	}


}
