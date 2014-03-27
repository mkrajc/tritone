package org.mech.tritone.music.context.impl;

import java.util.List;

import org.mech.tritone.music.context.MusicRenderingContext;
import org.mech.tritone.music.model.Tone;
import org.mech.tritone.music.model.notation.fretboard.Fretboard;
import org.mech.tritone.render.RenderingContextImpl;

public class MusicRenderingContextImpl extends RenderingContextImpl implements MusicRenderingContext {

	public void setFretboard(final Fretboard fretboard) {
		super.put(MusicRenderingContext.FRETBOARD, fretboard);
	}

	public void setFretboardCaption(final String caption) {
		super.put(MusicRenderingContext.FRETBOARD_CAPTION, caption);
	}

	public void setFretFrom(final int from) {
		super.put(MusicRenderingContext.PRM_FRET_FROM, from);
	}

	public void setFretTo(final int to) {
		super.put(MusicRenderingContext.PRM_FRET_TO, to);
	}

	public void setFretDisplayStrings(final boolean value) {
		super.put(MusicRenderingContext.PRM_FRET_DISPLAY_STRINGS, value);
	}

	@SuppressWarnings("rawtypes")
	public void setRenders(final List renders) {
		super.put(MusicRenderingContext.FRETBOARD_RENDERS, renders);
	}

	public void setRoot(final Tone tone) {
		super.put(MusicRenderingContext.PRM_ROOT, tone);
	}

	public void setText(final String header) {
		super.put(MusicRenderingContext.TEXT, header);
	}

}
