package org.mech.tritone.music.model.instrument;

import org.mech.tritone.music.model.Pitch;
import org.mech.tritone.music.utils.PitchUtils;

public class Range {

	private Pitch from;
	private Pitch to;

	public Pitch from() {
		return from;
	}

	public Pitch to() {
		return to;
	}

	public void setFrom(Pitch from) {
		this.from = from;
	}

	public void setTo(Pitch to) {
		this.to = to;
	}
	
	@Override
	public String toString() {
		return PitchUtils.toString(from) + " - " + PitchUtils.toString(to);
	}
}
