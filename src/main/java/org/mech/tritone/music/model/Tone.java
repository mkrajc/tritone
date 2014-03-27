package org.mech.tritone.music.model;

import org.apache.commons.lang.StringUtils;

public enum Tone {
	C(0), DB(1), CS(1), D(2), EB(3), DS(3), E(4), F(5), GB(6), FS(6), G(7), AB(8), GS(8), A(9), BB(10), AS(10), B(11), CB(11);

	private int tClass;

	private Tone(final int tClass) {
		this.tClass = tClass;
	}

	public int getToneClass() {
		return tClass;
	}

	public String format() {
		return StringUtils.capitalize(name().toLowerCase());
	}

	public static Tone fromToneClass(final int toneClass) {
		for (Tone t : values()) {
			if (t.getToneClass() == toneClass) {
				return t;
			}
		}
		throw new IllegalArgumentException();
	}
}
