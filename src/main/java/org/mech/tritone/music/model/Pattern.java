package org.mech.tritone.music.model;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.mech.tritone.music.utils.IntervalUtils;

/**
 * Pattern is set of intervals with some name and key. Can be used to identify
 * chords, scales.
 */
public abstract class Pattern {
	private int[] intervals;
	private String name;
	private String abbrv;
	private String key;

	public int get(final int position) {
		return intervals[position];
	}

	public int length() {
		return intervals.length;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(name).append(intervals).append(abbrv).toString();
	}

	public int[] getIntervals() {
		return intervals;
	}

	public void setIntervals(final int[] intervals) {
		this.intervals = intervals;
	}

	public void setIntervalsString(final String[] intervalStrings) {
		this.intervals = new int[intervalStrings.length];
		for (int i = 0; i < intervalStrings.length; i++) {
			final String intervalString = intervalStrings[i];
			try {
				intervals[i] = IntervalUtils.toInterval(intervalString).getDistance();
			} catch (final IllegalArgumentException exception) {
				throw new IllegalArgumentException("Unknown interval " + intervalString);
			}
		}
	}

	public List<Tone> toTones(final Tone root) {
		final List<Tone> set = new LinkedList<Tone>();

		for (int interval : getIntervals()) {
			set.add(root.add(interval));
		}

		return set;

	}

	public String getAbbrv() {
		return abbrv;
	}

	public void setAbbrv(final String abbrv) {
		this.abbrv = abbrv;
	}

	public abstract PatternType getType();

	public String getKey() {
		return key;
	}

	public void setKey(final String key) {
		this.key = key;
	}
}
