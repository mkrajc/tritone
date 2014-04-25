package org.mech.tritone.music.model.instrument.finger;

import org.mech.tritone.music.model.instrument.string.StringedPitch;

/**
 * Pitch played by finger
 */
public class FingeredPitch implements Comparable<FingeredPitch> {

	private final FingerType finger;
	private final StringedPitch pitch;

	public FingeredPitch(final StringedPitch pitch, final FingerType finger) {
		this.finger = finger; 
		this.pitch = pitch;
	}

	public FingerType getFinger() {
		return finger;
	}

	@Override
	public String toString() {
		return pitch.toString() + "," + finger;
	}

	@Override
	public int compareTo(final FingeredPitch fPitch) {
		final int cmp = pitch.compareTo(fPitch.getPitch());
		if (cmp == 0) {
			return finger.compareTo(fPitch.getFinger());
		}
		return cmp;
	}

	public StringedPitch getPitch() {
		return pitch;
	}
	

}
