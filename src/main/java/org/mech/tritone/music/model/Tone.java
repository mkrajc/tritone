package org.mech.tritone.music.model;

import org.apache.commons.lang.StringUtils;

/**
 * Tone enumaration, has collection of all tones in octave, distinguishing
 * between enharmonic ones. So Cb and B are different, although it has same tone
 * class but different tone group.
 * 
 **/
public enum Tone {
	C(0, ToneGroup.C), CS(1, ToneGroup.C), DB(1, ToneGroup.D), D(2, ToneGroup.D), DS(3, ToneGroup.D), EB(3, ToneGroup.E), E(4, ToneGroup.E), ES(
			5, ToneGroup.E), FB(4, ToneGroup.F), F(5, ToneGroup.F), FS(6, ToneGroup.F), GB(6, ToneGroup.G), G(7, ToneGroup.G), GS(8,
			ToneGroup.G), AB(8, ToneGroup.A), A(9, ToneGroup.A), AS(10, ToneGroup.A), BB(10, ToneGroup.B), B(11, ToneGroup.B), BS(0,
			ToneGroup.B), CB(11, ToneGroup.C);

	// tone class Db and Cs has same tone class;
	private int tClass;

	// group represent same tone group C, C#, Cb has same C group 0
	private ToneGroup group;

	private Tone(final int tClass, final ToneGroup group) {
		this.tClass = tClass;
		this.group = group;
	}

	public int getToneClass() {
		return tClass;
	}

	public ToneGroup getToneGroup() {
		return group;
	}

	public String format() {
		return StringUtils.capitalize(name().toLowerCase());
	}

	public Tone add(final int offset) {
		int newTClass = (tClass + offset) % 12;
		return Tone.fromToneClass(newTClass);
	}

	public static Tone fromToneClass(final int toneClass) {
		for (Tone t : values()) {
			if (t.getToneClass() == toneClass) {
				return t;
			}
		}
		throw new IllegalArgumentException();
	}

	public static Tone fromToneClass(final int toneClass, final ToneGroup group) {
		for (Tone t : values()) {
			if (t.getToneClass() == toneClass && t.getToneGroup() == group) {
				return t;
			}
		}
		throw new IllegalArgumentException();
	}
}
