package org.mech.tritone.music.context.impl;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.mech.tritone.music.context.Context;
import org.mech.tritone.music.context.ContextPrepare;
import org.mech.tritone.music.context.MusicRenderingContext;
import org.mech.tritone.music.model.Pattern;
import org.mech.tritone.music.model.instrument.Tuning;
import org.mech.tritone.music.model.instrument.string.StringedInstrument;
import org.mech.tritone.music.model.instrument.string.StringedPitch;
import org.mech.tritone.music.model.notation.fretboard.FingeredPitch;
import org.mech.tritone.music.model.notation.fretboard.Fretboard;
import org.mech.tritone.music.service.FretboardService;
import org.mech.tritone.music.service.MusicDataService;
import org.mech.tritone.music.utils.PitchUtils;
import org.mech.tritone.music.utils.PitchUtils.FormatingType;
import org.mech.tritone.render.RenderingContext;
import org.mech.tritone.render.RenderingContextDispatcher;
import org.springframework.stereotype.Component;

@Component("chordContextPrepare")
public class ChordContextPrepare implements ContextPrepare {

	@Resource(name = "fretboardService")
	private FretboardService fretboardService;

	@Resource(name = "musicDataService")
	private MusicDataService musicDataService;

	@Override
	public RenderingContext prepare(Context context) {
		RenderingContextDispatcher rDispatcher = new RenderingContextDispatcher();
		int pitchClass;
		String chordPatternName;
		Pattern chordPattern;

		Fretboard fretboard;

		pitchClass = context.get(ContextPrepare.PITCH_CLASS, Integer.class);
		chordPatternName = context.get(ContextPrepare.CHORD_PATTERN_NAME,
				String.class);
		rDispatcher.put(MusicRenderingContext.HTML_PATH,context.get(ContextPrepare.HTML_PATH, String.class));

		// get chord pattern
		chordPattern = musicDataService.getPattern(chordPatternName);

		fretboard = createFretboard(context);

		rDispatcher.append(prepareHeader(context, pitchClass, chordPattern));
		rDispatcher.append(preparePatternHeader(context, pitchClass, fretboard, chordPattern));
		rDispatcher.append(prepareFullFretboard(context, pitchClass, fretboard, chordPattern));

		int[] fingerIndexes = new int[] { 1, 2, 4 };
		Set<String> processedFingerings = new HashSet<String>();
		
		for (int stringIndex = 0; stringIndex < fretboard.getStringCount(); stringIndex++) {
			rDispatcher.append(prepareSeparator("[" + PitchUtils.format(pitchClass, FormatingType.LETTER_US) + chordPattern.getAbbrv() + "] root ("
					+ PitchUtils.format(pitchClass, FormatingType.LETTER_US)
					+ ") on "
					+ PitchUtils.format(fretboard.getStrings(stringIndex)
							.getRoot(), FormatingType.LETTER_US) + " string"));
			for (int index : fingerIndexes) {
				final List<FingeredPitch> applyFingering = fretboardService.applyFingering(fretboard, chordPattern, pitchClass, index, stringIndex);
				boolean prepareFretboard = false;
				for(FingeredPitch fingeredPitch : applyFingering){
					if(fingeredPitch.getPitch().getPitchClass() == pitchClass){
						String s = fingeredPitch.getFingerIndex() + "-"
								+ fingeredPitch.getStringIndex();
						if(!processedFingerings.contains(s)){
							processedFingerings.add(s);
							prepareFretboard = true;
						}
					}
				}
				if(prepareFretboard){
					rDispatcher.append(prepareFingeredFretboard(context, pitchClass, fretboard, applyFingering, index, 0));
				}
			}
			
		}
		
		rDispatcher.setWriter(new StringWriter());

		return rDispatcher;
	}

	private RenderingContext preparePatternHeader(Context context,
			int pitchClass, Fretboard fretboard, Pattern chordPattern) {
		MusicRenderingContextImpl header = new MusicRenderingContextImpl();
		
		Set<String> set = new HashSet<String>();
		
		final List<StringedPitch> findStringedPitchs = fretboardService.findStringedPitchs(fretboard, chordPattern, pitchClass);
		
		for(StringedPitch p : findStringedPitchs){
			set.add(PitchUtils.format(p.getPitch(), FormatingType.LETTER_US));
		}
		header.setText(Arrays.toString(set.toArray()));
		return header;
	}

	private MusicRenderingContext prepareFullFretboard(Context args,
			int pitchClass, Fretboard fretboard, Pattern pattern) {
		MusicRenderingContextImpl fullFret = new MusicRenderingContextImpl();
		fullFret.setFretboard(fretboard);
		fullFret.setRoot(pitchClass);
		fullFret.setRenders(fretboardService.findStringedPitchs(fretboard, pattern,
				pitchClass));
		fullFret.setFretboardCaption("Full Fretboard: "
				+ PitchUtils.format(pitchClass, FormatingType.LETTER_US) + pattern.getAbbrv());
		return fullFret;
	}

	private MusicRenderingContext prepareFingeredFretboard(Context args,
			int pitchClass, Fretboard fretboard, List<FingeredPitch> applyFingering, int fingerIndex, int position) {
		MusicRenderingContextImpl fullFret = new MusicRenderingContextImpl();
		fullFret.setFretboard(fretboard);
		fullFret.setRoot(pitchClass);
		fullFret.setRenders(applyFingering);
		fullFret.setFretboardCaption("Position with finger " + fingerIndex + "");
		return fullFret;

	}

	private MusicRenderingContext prepareHeader(Context args, int pitchClass,
			Pattern chordPattern) {
		MusicRenderingContextImpl header = new MusicRenderingContextImpl();
		header.setText(PitchUtils.format(pitchClass, FormatingType.LETTER_US)
				+ " " + chordPattern.getName());
		return header;

	}
	
	private MusicRenderingContext prepareSeparator(String s) {
		MusicRenderingContextImpl separator = new MusicRenderingContextImpl();
		separator.put(MusicRenderingContext.TEXT_TYPE, "div");
		separator.put(MusicRenderingContext.TEXT_CLASSNAME, "headerFretGroup");
		separator.setText(s);
		return separator;

	}

	protected Fretboard createFretboard(Context args) {
		Fretboard fretboard;
		String tuningName;
		int fretLength;
		Tuning tuning;

		tuningName = args.get(ContextPrepare.TUNING_NAME, String.class);
		fretLength = args.get(ContextPrepare.FRET_LENGTH, Integer.class,
				ContextPrepare.DEFAULT_FRET_LENGTH);

		tuning = musicDataService.getTuning(tuningName);

		StringedInstrument instrument = new StringedInstrument();
		instrument.setPitchRange(fretLength);
		instrument.setTuning(tuning);
		instrument.setStringsCount(tuning.get().size());
		try {
			instrument.afterPropertiesSet();
		} catch (Exception e) {
			// nothing to handle
		}

		fretboard = new Fretboard();
		fretboard.setInstrument(instrument);

		return fretboard;
	}

}
