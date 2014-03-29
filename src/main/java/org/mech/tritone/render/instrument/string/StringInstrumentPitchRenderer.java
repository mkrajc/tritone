package org.mech.tritone.render.instrument.string;

import java.util.List;

import org.mech.tritone.music.model.instrument.string.StringedInstrument;
import org.mech.tritone.music.model.instrument.string.StringedPitch;
import org.mech.tritone.render.AbstractFormatRenderer;
import org.mech.tritone.render.Format;
import org.mech.tritone.render.RenderingContext;

public abstract class StringInstrumentPitchRenderer extends AbstractFormatRenderer<StringInstrumentPitchRenderer.Context> {

	public StringInstrumentPitchRenderer(final Format format) {
		super(format);
	}

	@Override
	protected boolean doSupport(final RenderingContext context) {
		return context instanceof StringInstrumentPitchRenderer.Context;
	}

	public static class Context extends RenderingContext {
		private List<StringedPitch> pitchs;
		private StringedInstrument instrument;

		public StringedInstrument getInstrument() {
			return instrument;
		}

		public void setInstrument(final StringedInstrument instrument) {
			this.instrument = instrument;
		}

		public List<StringedPitch> getPitchs() {
			return pitchs;
		}

		public void setPitchs(final List<StringedPitch> pitchs) {
			this.pitchs = pitchs;
		}
	}
}
