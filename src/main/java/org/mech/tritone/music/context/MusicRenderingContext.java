package org.mech.tritone.music.context;

import org.mech.tritone.render.RenderingContext;

public interface MusicRenderingContext extends RenderingContext {
	public final static String FRETBOARD = "fretboard";
	public final static String FRETBOARD_CAPTION = "fretboardCaption";
	public final static String FRETBOARD_RENDERS = "fretRenders";
	public final static String PRM_FRET_FROM = "fretFrom";
	public final static String PRM_FRET_TO = "fretTo";
	public final static String PRM_FRET_DISPLAY_STRINGS = "fretDisplayStrings";
	public static final String PRM_ROOT = "fretRoot";
	
	public static final String TEXT = "text";
	public static final String TEXT_TYPE = "textType";
	public static final String TEXT_CLASSNAME = "textClassname";
	
	public static final String HTML_PATH = "htmlPath";
	
	<T> T get(String key, Class<T> clazz);
	
	
}


