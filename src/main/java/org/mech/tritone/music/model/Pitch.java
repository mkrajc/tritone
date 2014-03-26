package org.mech.tritone.music.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.mech.tritone.music.utils.PitchUtils;

public class Pitch {
	private int pitchClass;
	private int octave;

	public int getPitchClass() {
		return pitchClass;
	}

	public Pitch(int pitchClass, int octave) {
		super();
		this.pitchClass = pitchClass;
		this.octave = octave;
	}

	public void setPitchClass(final int pitchClass) {
		this.pitchClass = pitchClass;
	}

	public int getOctave() {
		return octave;
	}

	public void setOctave(final int octave) {
		this.octave = octave;
	}

	@Override
	public String toString() {
		return PitchUtils.toString(this);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Pitch == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Pitch pitch = (Pitch) obj;
		return new EqualsBuilder().appendSuper(super.equals(obj))
				.append(pitchClass, pitch.getPitchClass())
				.append(octave, pitch.getOctave()).isEquals();
	}
	
	@Override
	public int hashCode() {
		 return new HashCodeBuilder(17, 37).
	       append(pitchClass).
	       append(octave).
	       toHashCode();
	}
}
