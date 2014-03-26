package org.mech.tritone.music.model.notation.fretboard;

import org.mech.tritone.music.model.Pitch;
import org.mech.tritone.music.model.instrument.string.StringedPitch;

public class FingeredPitch extends StringedPitch {

	private final int fingerIndex;

	public FingeredPitch(Pitch pitch, int stringIndex, int position,
			int fingerIndex) {
		super(pitch, stringIndex, position);
		this.fingerIndex = fingerIndex;
	}

	public FingeredPitch(StringedPitch pitch, int fingerIndex) {
		super(pitch.getPitch(), pitch.getStringIndex(), pitch.getPosition());
		this.fingerIndex = fingerIndex;
	}

	public int getFingerIndex() {
		return fingerIndex;
	}
	
	@Override
	public String toString() {
		return super.toString() + "," + fingerIndex;
	}

}
