package org.mech.tritone.music.utils;

public class FretboardUtils {

	public enum FretboardMark {
		NONE, DOT, DDOT
	}

	public static FretboardMark getMark(final int fret) {
		if (fret == 3 || fret == 5 || fret == 7 || fret == 9 || fret == 15 || fret == 17 || fret == 19 || fret == 21) {
			return FretboardMark.DOT;
		} else if (fret == 12 || fret == 24) {
			return FretboardMark.DDOT;
		} else {
			return FretboardMark.NONE;
		}
	}
}
