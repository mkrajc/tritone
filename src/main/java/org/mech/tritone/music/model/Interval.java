package org.mech.tritone.music.model;

public enum Interval {
	
	i1(0), ib2(1), i2(2), ib3(3), i3(4), i4(5), ib5(6), i5(7), iaug5(8), ib6(8), i6(9),ibb7(9), ib7(10), i7(11),ib9(1), i9(2), i11(5);

	Interval(int pDistanche) {
		this.pitchDistance = pDistanche;
	}

	private int pitchDistance;

	public int getPitchDistance() {
		return pitchDistance;
	}
}
