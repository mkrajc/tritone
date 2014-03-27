package org.mech.tritone.music.utils;

import org.apache.commons.lang.StringUtils;
import org.mech.tritone.music.model.Pitch;
import org.mech.tritone.music.model.Tone;

public class PitchUtils {

	public static Pitch toPitch(final String string) {
		final String[] array = StringUtils.split(string.toUpperCase(), ".", 2);
		final Tone t = Tone.valueOf(array != null ? array[0] : string.toUpperCase());
		final int octave = array != null ? Integer.parseInt(array[1]) : 0;

		return new Pitch(t, octave);
	}

	public static Pitch aug(final Pitch pitch, final int offset) {
		if (offset < 0) {
			throw new IllegalArgumentException("offset cannot be lesser than zero");
		}

		int pitchClass2 = (pitch.getTone().getToneClass() + offset);
		int octOffset = (int) Math.floor(pitchClass2 / 12.0);
		int pitchClass = (pitch.getTone().getToneClass() + offset) % 12;

		return new Pitch(Tone.fromToneClass(pitchClass), pitch.getOctave() + octOffset);
	}

	public static String format(final Pitch[] pitchs) {
		StringBuilder builder = new StringBuilder();

		for (Pitch pitch : pitchs) {
			builder.append(pitch.getTone().format());
			builder.append(", ");
		}

		builder.setLength(builder.length() - 1);

		return builder.toString();
	}

}
