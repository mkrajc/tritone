package org.mech.tritone.music.model;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * Interval defines interval distance between two pitchs
 **/
public class Interval {
	private final boolean sharp, flat, doubleSharp, doubleFlat;
	private final IntervalType intervalType;
	private int distance;
	private final String string;

	public Interval(final IntervalType intervalType, final boolean sharp, final boolean flat, final boolean doubleSharp,
			final boolean doubleFlat) {
		super();
		this.sharp = doubleSharp ? true : sharp;
		this.flat = doubleFlat ? true : flat;
		this.doubleSharp = doubleSharp;
		this.doubleFlat = doubleFlat;
		this.intervalType = intervalType;
		this.distance = intervalType.getDistance();

		final StringBuilder builder = new StringBuilder();
		if (sharp) {
			distance++;
			builder.append("#");
			if (doubleSharp) {
				distance++;
				builder.append("#");
			}
		}

		if (flat) {
			distance--;
			builder.append("b");
			if (doubleFlat) {
				distance--;
				builder.append("b");
			}
		}

		builder.append(intervalType.getIntervalBase());
		this.string = builder.toString();

	}

	public boolean isFlat() {
		return flat;
	}

	public boolean isDoubleSharp() {
		return doubleSharp;
	}

	public boolean isDoubleFlat() {
		return doubleFlat;
	}

	public IntervalType getIntervalType() {
		return intervalType;
	}

	public int getDistance() {
		return distance;
	}

	public boolean isSharp() {
		return sharp;
	}

	@Override
	public String toString() {
		return string;
	}

	@Override
	public boolean equals(final Object obj) {

		if (obj instanceof Interval == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Interval i = (Interval) obj;
		return new EqualsBuilder().append(intervalType, i.getIntervalType()).append(sharp, i.isSharp())
				.append(doubleSharp, i.isDoubleSharp()).append(flat, i.isFlat()).append(doubleFlat, i.isDoubleFlat()).isEquals();
	}

	public static Interval create(final String s) {
		char[] charArray = s.toCharArray();

		boolean flat = false, sharp = false, doubleSharp = false, doubleFlat = false;

		int index = 0;
		for (; index < charArray.length; index++) {
			char ch = charArray[index];

			if (Character.isDigit(ch)) {
				break;
			}

			boolean equalsB = ch == 'b';
			boolean equalsSharp = ch == '#';

			doubleFlat = flat ? true : false;
			flat = equalsB;
			doubleSharp = sharp ? true : false;
			sharp = equalsSharp;
		}

		try {
			final int interval = Integer.parseInt(s.substring(index));
			final IntervalType intervalType = IntervalType.values()[interval - 1];
			return new Interval(intervalType, sharp, flat, doubleSharp, doubleFlat);
		} catch (Exception exception) {
			throw new IllegalArgumentException("Not valid interval string [" + s + "]");
		}

	}
}
