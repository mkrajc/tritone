package org.mech.tritone.music.context;

import org.mech.tritone.render.RenderingContext;

public interface ContextPrepare {
	
	public static final int DEFAULT_FRET_LENGTH = 21;

	public final static String PITCH_CLASS = "pitchClass";
	public static final String CHORD_PATTERN_NAME = "chordPatterName";
	public static final String TUNING_NAME = "tuningName";
	public static final String FRET_LENGTH = "fretLength";

	public static final String HTML_PATH = "htmlPath";
	
	RenderingContext prepare(Context args);

}
