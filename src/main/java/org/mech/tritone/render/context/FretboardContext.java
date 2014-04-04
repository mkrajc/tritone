package org.mech.tritone.render.context;

import java.util.HashMap;
import java.util.Map;

import org.mech.tritone.music.model.instrument.string.Tuning;
import org.mech.tritone.render.RenderingContext;

public class FretboardContext extends RenderingContext {
	private int fretStart, fretEnd;
	private boolean renderTuning;
	private int stringCount;
	private Tuning tuning;

	private final Map<Integer, Map<Integer, Object>> renderableMap = new HashMap<Integer, Map<Integer,Object>>();

	public Object get(final int string, final int fretPosition) {
		final Map<Integer, Object> stringMap = renderableMap.get(string);

		if (stringMap != null) {
			return stringMap.get(fretPosition);
		}

		return null;
	}

	public void set(final int string, final int fret, final Object renderable) {
		Map<Integer, Object> stringMap = renderableMap.get(string);

		if (stringMap == null) {
			stringMap = new HashMap<Integer, Object>();
			renderableMap.put(string, stringMap);
		}

		stringMap.put(fret, renderable);
	}

	public int getFretStart() {
		return fretStart;
	}

	public void setFretStart(final int fretStart) {
		this.fretStart = fretStart;
	}

	public int getFretEnd() {
		return fretEnd;
	}

	public void setFretEnd(final int fretEnd) {
		this.fretEnd = fretEnd;
	}

	public boolean isRenderTuning() {
		return renderTuning;
	}

	public void setRenderTuning(final boolean renderTuning) {
		this.renderTuning = renderTuning;
	}

	public int getStringCount() {
		return stringCount;
	}

	public void setStringCount(final int stringCount) {
		this.stringCount = stringCount;
	}

	public Tuning getTuning() {
		return tuning;
	}

	public void setTuning(final Tuning tuning) {
		this.tuning = tuning;
	}

}
