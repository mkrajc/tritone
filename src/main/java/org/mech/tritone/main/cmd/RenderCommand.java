package org.mech.tritone.main.cmd;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mech.tritone.music.context.ContextPrepare;
import org.mech.tritone.music.context.MusicRenderingContext;
import org.mech.tritone.music.context.impl.MusicRenderingContextImpl;
import org.mech.tritone.music.model.Pattern;
import org.mech.tritone.music.model.Tone;
import org.mech.tritone.music.model.instrument.Tuning;
import org.mech.tritone.music.model.instrument.string.StringedInstrument;
import org.mech.tritone.music.model.instrument.string.StringedPitch;
import org.mech.tritone.music.model.notation.fretboard.FingeredPitch;
import org.mech.tritone.music.model.notation.fretboard.Fretboard;
import org.mech.tritone.music.service.FretboardService;
import org.mech.tritone.music.service.MusicDataService;
import org.mech.tritone.render.RenderingContext;
import org.mech.tritone.render.RenderingContextDispatcher;
import org.mech.tritone.render.html.HtmlRendererEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RenderCommand {

	@Autowired
	private FretboardService fretboardService;

	@Autowired
	private MusicDataService musicDataService;

	@Autowired
	private HtmlRendererEngine htmlRendererEngine;

	public void execute(final Writer writer, final Pattern pattern, final Tuning tuning, final Tone tone) {
		final RenderingContextDispatcher rContext = new RenderingContextDispatcher();

		final Fretboard fretboard = createFretboard(tuning, ContextPrepare.DEFAULT_FRET_LENGTH);

		rContext.append(prepareHeader(tone, pattern));
		rContext.append(preparePatternHeader(tone, fretboard, pattern));
		rContext.append(prepareFullFretboard(tone, fretboard, pattern));

		int[] fingerIndexes = new int[] { 1, 2, 4 };
		Set<String> processedFingerings = new HashSet<String>();

		for (int stringIndex = 0; stringIndex < fretboard.getStringCount(); stringIndex++) {
			rContext.append(prepareSeparator("[" + tone.format() + pattern.getAbbrv() + "] root (" + tone.format() + ") on "
					+ fretboard.getStrings(stringIndex).getRoot().getTone().format() + " string"));
			for (int index : fingerIndexes) {
				final List<FingeredPitch> applyFingering = fretboardService.applyFingering(fretboard, pattern, tone, index, stringIndex);
				boolean prepareFretboard = false;
				for (FingeredPitch fingeredPitch : applyFingering) {
					if (fingeredPitch.getPitch().getTone() == tone) {
						String s = fingeredPitch.getFingerIndex() + "-" + fingeredPitch.getStringIndex();
						if (!processedFingerings.contains(s)) {
							processedFingerings.add(s);
							prepareFretboard = true;
						}
					}
				}
				if (prepareFretboard) {
					rContext.append(prepareFingeredFretboard(tone, fretboard, applyFingering, index, 0));
				}
			}

		}

		rContext.setWriter(new StringWriter());

		htmlRendererEngine.render(writer, rContext);
	}

	private RenderingContext preparePatternHeader(final Tone tone, final Fretboard fretboard, final Pattern chordPattern) {
		MusicRenderingContextImpl header = new MusicRenderingContextImpl();

		Set<String> set = new HashSet<String>();

		final List<StringedPitch> findStringedPitchs = fretboardService.findStringedPitchs(fretboard, chordPattern, tone);

		for (StringedPitch p : findStringedPitchs) {
			set.add(p.getPitch().getTone().format());
		}
		header.setText(Arrays.toString(set.toArray()));
		return header;
	}

	private MusicRenderingContext prepareFullFretboard(final Tone tone, final Fretboard fretboard, final Pattern pattern) {
		MusicRenderingContextImpl fullFret = new MusicRenderingContextImpl();
		fullFret.setFretboard(fretboard);
		fullFret.setRoot(tone);
		fullFret.setRenders(fretboardService.findStringedPitchs(fretboard, pattern, tone));
		fullFret.setFretboardCaption("Full Fretboard: " + tone.format() + pattern.getAbbrv());
		return fullFret;
	}

	private MusicRenderingContext prepareFingeredFretboard(final Tone tone, final Fretboard fretboard,
			final List<FingeredPitch> applyFingering, final int fingerIndex, final int position) {
		MusicRenderingContextImpl fullFret = new MusicRenderingContextImpl();
		fullFret.setFretboard(fretboard);
		fullFret.setRoot(tone);
		fullFret.setRenders(applyFingering);
		fullFret.setFretboardCaption("Position with finger " + fingerIndex + "");
		return fullFret;

	}

	private MusicRenderingContext prepareHeader(final Tone tone, final Pattern pattern) {
		MusicRenderingContextImpl header = new MusicRenderingContextImpl();
		header.setText(tone.format() + " " + pattern.getName());
		return header;

	}

	private MusicRenderingContext prepareSeparator(final String s) {
		MusicRenderingContextImpl separator = new MusicRenderingContextImpl();
		separator.put(MusicRenderingContext.TEXT_TYPE, "div");
		separator.put(MusicRenderingContext.TEXT_CLASSNAME, "headerFretGroup");
		separator.setText(s);
		return separator;

	}

	protected Fretboard createFretboard(final Tuning tuning, final int fretLength) {
		final StringedInstrument instrument = new StringedInstrument();
		instrument.setPitchRange(fretLength);
		instrument.setTuning(tuning);
		instrument.setStringsCount(tuning.get().size());
		instrument.setup();

		final Fretboard fretboard = new Fretboard();
		fretboard.setInstrument(instrument);

		return fretboard;
	}
}
