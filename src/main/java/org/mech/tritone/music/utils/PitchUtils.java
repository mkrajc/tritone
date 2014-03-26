package org.mech.tritone.music.utils;

import org.apache.commons.lang.StringUtils;
import org.mech.tritone.music.model.Pitch;

public class PitchUtils {
	private enum Type {
		C(0), DB(1), CS(1), D(2), EB(3), DS(3), E(4), F(5), GB(6), FS(6), G(7), AB(
				8), GS(8), A(9), BB(10), AS(10), B(11), CB(11);

		private final int pitchClass;

		private Type(int pClass) {
			this.pitchClass = pClass;
		}
	}

	public enum FormatingType {
		LETTER_US
	}

	public static Pitch toPitch(String string) {
		int index = string.toUpperCase().indexOf(".");
		String[] array = null;

		if (index > 0) {
			array = StringUtils.split(string.toUpperCase(), ".", 2);
		}

		final Type valueOf = Type.valueOf(array != null ? array[0] : string
				.toUpperCase());
		final int octave = array != null ? Integer.parseInt(array[1]) : 0;

		Pitch pitch = new Pitch(valueOf.pitchClass, octave);

		return pitch;
	}
	
	public static int toPitchClass(String string) {
		final Type valueOf = Type.valueOf(string.toUpperCase());
		Pitch pitch = new Pitch(valueOf.pitchClass, 0);
		return pitch.getPitchClass();
	}

	public static String toString(Pitch pitch) {
		StringBuilder sb = new StringBuilder();

		for (Type t : Type.values()) {
			if (t.pitchClass == pitch.getPitchClass()) {
				sb.append(t.toString());
				break;
			}
		}

		sb.append(".").append(pitch.getOctave());

		return sb.toString();
	}

	public static Pitch aug(final Pitch pitch, final int offset) {
		if (offset < 0) {
			throw new IllegalArgumentException(
					"offset cannot be lesser than zero");
		}

		int pitchClass2 = (pitch.getPitchClass() + offset);
		int octOffset = (int) Math.floor((double) pitchClass2 / 12.0);
		int pitchClass = (pitch.getPitchClass() + offset) % 12;

		final Pitch pitch2 = new Pitch(pitchClass, pitch.getOctave() + octOffset);
		return pitch2;
	}

	public static String format(Pitch pitch, FormatingType type) {
		return format(pitch.getPitchClass(), type);
	}
	
	public static String format(Pitch[] pitchs, FormatingType type) {
		StringBuilder builder = new StringBuilder();
	
		for(Pitch pitch : pitchs ){
			builder.append(format(pitch.getPitchClass(), type));
			builder.append(", ");
		}
		
		builder.setLength(builder.length() - 1);
		
		return builder.toString();
	}
	
	public static String format(int pitchClass, FormatingType type) {
		Type pType = null;
		for (Type t : Type.values()) {
			if (t.pitchClass == pitchClass) {
				pType = t;
				break;
			}
		}
		StringBuilder sb = new StringBuilder();
		
		switch (type) {
		case LETTER_US:
			sb.append(pType.toString());
			break;
		default:
			
			break;
		}

		return sb.toString();
	}

}
