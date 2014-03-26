package org.mech.tritone.music.model.instrument.string;

import java.util.ArrayList;
import java.util.List;

import org.mech.tritone.music.model.Pitch;
import org.mech.tritone.music.utils.PitchUtils;

public class Strings {

	private final List<Pitch> pitches;
	private int index;
	private final Pitch root;

	public Strings(Pitch starting, int fretLength, int stringIndex) {
		pitches = new ArrayList<Pitch>();
		index = stringIndex;
		for (int i = 0; i < fretLength; i++) {
			pitches.add(PitchUtils.aug(starting, i));
		}

		root = pitches.iterator().next();
	}

	public List<Pitch> getPitches() {
		return pitches;
	}

	public Pitch from() {
		return pitches.get(0);
	}

	public Pitch to() {
		return pitches.get(pitches.size() - 1);
	}

	public int getIndex() {
		return index;
	}

	public Pitch getRoot() {
		return root;
	}

}
