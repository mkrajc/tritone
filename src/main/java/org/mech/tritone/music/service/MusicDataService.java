package org.mech.tritone.music.service;

import java.util.List;

import org.mech.tritone.music.model.ChordPattern;
import org.mech.tritone.music.model.Pattern;
import org.mech.tritone.music.model.ScalePattern;
import org.mech.tritone.music.model.Tone;
import org.mech.tritone.music.model.instrument.string.Tuning;

public interface MusicDataService {

	Pattern getPattern(String patternName);

	Tuning getTuning(String tuningName);
	
	List<Pattern> getAllPatterns();
	
	List<ChordPattern> getAllChordPatterns();
	
	List<ScalePattern> getAllScalePatterns();
	
	List<Tuning> getAllTunings();

	Tone getTone(String toneString);

}
