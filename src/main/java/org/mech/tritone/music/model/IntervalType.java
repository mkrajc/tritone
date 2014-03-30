package org.mech.tritone.music.model;

public enum IntervalType {

	PRIMA(0), SECUNDA(2), TERTIA(4), QUARTA(5), QUINTA(7), SEXTA(9), SEPTIMA(11), OCTAVA(12), NONA(14), DECIMA(16), UNDECIMA(17), DODECIMA(
			19), TRIDECIMA(21);

	private int distance;

	private IntervalType(final int d) {
		this.distance = d;
	}

	public int getDistance() {
		return distance;
	}

	public int getIntervalBase() {
		return ordinal() + 1;
	}

	public Interval createSharp() {
		return new Interval(this, true, false, false, false);
	}

	public Interval createDoubleSharp() {
		return new Interval(this, true, false, true, false);
	}

	public Interval createDoubleFlat() {
		return new Interval(this, false, true, false, true);
	}

	public Interval createFlat() {
		return new Interval(this, false, true, false, false);
	}

	public Interval create() {
		return new Interval(this, false, false, false, false);
	}

}
