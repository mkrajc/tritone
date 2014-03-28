package org.mech.tritone.music.model.instrument.finger;

import org.mech.tritone.music.model.Pitch;

/**
 * Pitch played by finger
 */
public class FingeredPitch implements Comparable<FingeredPitch> {

	private final FingerType finger;
	private final Pitch pitch;

	public FingeredPitch(final Pitch pitch, final FingerType finger) {
		this.finger = finger;
		this.pitch = pitch;
	}

	public FingerType getFinger() {
		return finger;
	}
 
	@Override
	public String toString() {
		return super.toString() + "," + finger.ordinal();
	}

	@Override
	public int compareTo(final FingeredPitch fPitch) {
		final int cmp = pitch.compareTo(fPitch.getPitch());
		if (cmp == 0) {
			return finger.compareTo(fPitch.getFinger());
		}
		return cmp;
	}

	public Pitch getPitch() {
		return pitch;
	}

}
