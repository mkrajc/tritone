package org.mech.tritone.main.cmd;

import org.springframework.stereotype.Component;

@Component
public class RenderCommand {
/*
	@Autowired
	private FretboardService fretboardService;

	@Autowired
	private MusicDataService musicDataService;

	@Autowired
	private HtmlRendererEngine htmlRendererEngine;

	public void execute(final Writer writer, final Pattern pattern, final Tuning tuning, final Tone tone) {
		final RenderingContextDispatcher rContext = new RenderingContextDispatcher();

		final StringedInstrument instrument = createFretboard(tuning, ContextPrepare.DEFAULT_FRET_LENGTH);

		rContext.append(prepareHeader(tone, pattern));
		rContext.append(preparePatternHeader(tone, instrument, pattern));
		rContext.append(prepareFullFretboard(tone, instrument, pattern));

		int[] fingerIndexes = new int[] { 1, 2, 4 };
		Set<String> processedFingerings = new HashSet<String>();

		for (int stringIndex = 0; stringIndex < instrument.getStringCount(); stringIndex++) {
			rContext.append(prepareSeparator("[" + tone.format() + pattern.getAbbrv() + "] root (" + tone.format() + ") on "
					+ instrument.getStrings(stringIndex).getRoot().getTone().format() + " string"));
			for (int index : fingerIndexes) {
				final List<FingeredPitch> applyFingering = fretboardService.applyFingering(instrument, pattern, tone, index, stringIndex);
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
					rContext.append(prepareFingeredFretboard(tone, instrument, applyFingering, index, 0));
				}
			}

		}

		rContext.setWriter(new StringWriter());

		htmlRendererEngine.render(writer, rContext);
	}

	private RenderingContext preparePatternHeader(final Tone tone, final StringedInstrument fretboard, final Pattern chordPattern) {
		MusicRenderingContextImpl header = new MusicRenderingContextImpl();

		Set<String> set = new HashSet<String>();

		final List<StringedPitch> findStringedPitchs = fretboardService.findStringedPitchs(fretboard, chordPattern, tone);

		for (StringedPitch p : findStringedPitchs) {
			set.add(p.getPitch().getTone().format());
		}
		header.setText(Arrays.toString(set.toArray()));
		return header;
	}

	private MusicRenderingContext prepareFullFretboard(final Tone tone, final StringedInstrument fretboard, final Pattern pattern) {
		MusicRenderingContextImpl fullFret = new MusicRenderingContextImpl();
		fullFret.setFretboard(fretboard);
		fullFret.setRoot(tone);
		fullFret.setRenders(fretboardService.findStringedPitchs(fretboard, pattern, tone));
		fullFret.setFretboardCaption("Full Fretboard: " + tone.format() + pattern.getAbbrv());
		return fullFret;
	}

	private MusicRenderingContext prepareFingeredFretboard(final Tone tone, final StringedInstrument fretboard,
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

	protected StringedInstrument createFretboard(final Tuning tuning, final int fretLength) {
		final StringedInstrument instrument = new StringedInstrument();
		instrument.setTuning(tuning);
		instrument.setLength(fretLength);
		return instrument;

	}
	*/
}
