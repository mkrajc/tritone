package org.mech.tritone.render.tab.instrument.string;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mech.tritone.music.model.Pitch;
import org.mech.tritone.music.model.instrument.string.StringedInstrument;
import org.mech.tritone.music.model.instrument.string.StringedPitch;
import org.mech.tritone.music.utils.PitchUtils;
import org.mech.tritone.render.Format;
import org.mech.tritone.render.instrument.string.StringInstrumentPitchRenderer;
import org.springframework.stereotype.Component;

@Component
public class TabStringInstrumentPitchRenderer extends StringInstrumentPitchRenderer {

	public TabStringInstrumentPitchRenderer() {
		super(Format.TAB);
	}

	@Override
	public void render(final PrintWriter writer, final StringInstrumentPitchRenderer.Context context) {
		final StringedInstrument instrument = context.getInstrument();
		List<StringedPitch> pitchs = context.getPitchs();

		Map<Integer, List<StringedPitch>> stringMap = new HashMap<Integer, List<StringedPitch>>();
		for (StringedPitch p : pitchs) {
			List<StringedPitch> list = stringMap.get(p.getStringIndex());
			if (list == null) {
				list = new ArrayList<StringedPitch>();
				stringMap.put(p.getStringIndex(), list);
			}
			list.add(p);
		}

		for (int i = instrument.getStringsCount() - 1; i >= 0; i--) {
			Pitch naturalStringPitch = instrument.getNaturalStringPitch(i);
			writer.print(naturalStringPitch.getTone().format());
			writer.print("|");

			Pitch onFret = PitchUtils.aug(naturalStringPitch, 0);
			if (shouldRenderPitch(onFret, stringMap, i)) {
				writer.print("x");
			} else {
				writer.print(" ");
			}
			writer.print("|");

			for (int fret = 1; fret < instrument.getLength(); fret++) {
				writer.print("-");
				onFret = PitchUtils.aug(naturalStringPitch, fret);

				if (shouldRenderPitch(onFret, stringMap, i)) {
					writer.print(onFret.getTone().format());
				} else {
					writer.print("-");
				}

				writer.print("-|");
			}
			writer.println();
		}
		writer.println();
	}

	private boolean shouldRenderPitch(final Pitch currentOnFret, final Map<Integer, List<StringedPitch>> stringMap, final int stringIndex) {
		List<StringedPitch> list = stringMap.get(stringIndex);
		if (list != null) {
			for (StringedPitch sp : list) {
				if (sp.getPitch().getTone().equals(currentOnFret.getTone())) {
					return true;
				}
			}
		}
		return false;
	}
}
