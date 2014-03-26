package org.mech.tritone.music.context.impl;

import java.util.List;

import org.mech.tritone.music.context.MusicRenderingContext;
import org.mech.tritone.music.model.notation.fretboard.Fretboard;
import org.mech.tritone.render.RenderingContextImpl;

public class MusicRenderingContextImpl extends RenderingContextImpl implements MusicRenderingContext{

	public void setFretboard(Fretboard fretboard) {
		super.put(MusicRenderingContext.FRETBOARD, fretboard);
	}
	
	public void setFretboardCaption(String caption) {
		super.put(MusicRenderingContext.FRETBOARD_CAPTION, caption);
	}
	
	public void setFretFrom(int from) {
		super.put(MusicRenderingContext.PRM_FRET_FROM, from);
	}
	
	public void setFretTo(int to) {
		super.put(MusicRenderingContext.PRM_FRET_TO, to);
	}
	
	public void setFretDisplayStrings(boolean value) {
		super.put(MusicRenderingContext.PRM_FRET_DISPLAY_STRINGS, value);
	}
	
	@SuppressWarnings("rawtypes")
	public void setRenders(List renders) {
		super.put(MusicRenderingContext.FRETBOARD_RENDERS, renders);
	}
	
	public void setRoot(int pitchClass) {
		super.put(MusicRenderingContext.PRM_ROOT, pitchClass);
	}
	
	public void setText(String header) {
		super.put(MusicRenderingContext.TEXT, header);
	}

	
	

}
