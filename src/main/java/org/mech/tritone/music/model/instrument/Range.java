package org.mech.tritone.music.model.instrument;

import org.mech.tritone.music.model.Pitch;

public class Range {

	private Pitch from;
	private Pitch to;

	public Pitch from() {
		return from;
	}

	public Pitch to() {
		return to;
	}

	public void setFrom(final Pitch from) {
		this.from = from;
	}

	public void setTo(final Pitch to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return from + " - " + to;
	}
}
