package org.mech.tritone.music.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.mech.tritone.music.utils.IntervalUtils;

public abstract class Pattern {
	private Integer[] patterns;
	private String name;
	private String abbrv;
	private String key;

	public Integer get(final int position) {
		return patterns[position];
	}

	public Integer length() {
		return patterns.length;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
				.append(name).append(patterns).append(abbrv).toString();
	}

	public Integer[] getPatterns() {
		return patterns;
	}

	public void setPatterns(final Integer[] patterns) {
		this.patterns = patterns;
	}

	public void setIntervals(final String[] intervals) {
		this.patterns = new Integer[intervals.length];
		for (int i = 0; i < intervals.length; i++) {
			patterns[i] = IntervalUtils.toInterval(intervals[i])
					.getPitchDistance();
		}
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

	public void setKey(String key) {
		this.key = key;
	}
}
