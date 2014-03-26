package org.mech.tritone.music.utils;

import org.mech.tritone.music.model.Interval;

public class IntervalUtils {

	public static Interval toInterval(final String notation) {
		return Interval.valueOf("i" + notation);
	}
}
