package org.mech.tritone.music.model;

import java.util.Arrays;
import java.util.List;

public class Chord {
	private List<Pitch> pitches;

	public List<Pitch> getPitches() {
		return pitches;
	}

	public void setPitches(List<Pitch> pitches) {
		this.pitches = pitches;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(pitches.toArray());
	}
}
