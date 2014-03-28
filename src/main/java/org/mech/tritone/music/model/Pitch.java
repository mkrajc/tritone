package org.mech.tritone.music.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Pitch represent tone with some pitch level. So C4 is concrete pitch and
 * physicaly can be represented by frequency of pitch
 * 
 **/
public class Pitch implements Comparable<Pitch> {
	private Tone tone;
	private int octave;

	public Pitch(final Tone tone, final int octave) {
		super();
		this.tone = tone;
		this.octave = octave;
	}

	public Tone getTone() {
		return tone;
	}

	public void setTone(final Tone tone) {
		this.tone = tone;
	}

	public int getOctave() {
		return octave;
	}

	public void setOctave(final int octave) {
		this.octave = octave;
	}

	@Override
	public String toString() {
		return getTone().format() + octave;
	}

	@Override
	public boolean equals(final Object obj) {

		if (obj instanceof Pitch == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Pitch pitch = (Pitch) obj;
		return new EqualsBuilder().appendSuper(super.equals(obj)).append(tone, pitch.getTone())
				.append(octave, pitch.getOctave()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(tone).append(octave).toHashCode();
	}

	@Override
	public int compareTo(Pitch p) {
		int cmp = tone.compareTo(p.getTone());
		if (cmp == 0) {
			return Integer.valueOf(octave).compareTo(p.getOctave());
		}
		return cmp;
	}
}
