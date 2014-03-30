package org.mech.tritone.music.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ToneTest {

	@Test
	public void testApplyInterval() {
		Interval unison = Interval.create("1");
		Interval octava = IntervalType.OCTAVA.create();
		Interval fifth = IntervalType.QUINTA.create();

		assertSame(Tone.A, Tone.A.applyInterval(unison));
		assertSame(Tone.A, Tone.A.applyInterval(octava));
		assertSame(Tone.G, Tone.C.applyInterval(fifth));
		assertSame(Tone.EB, Tone.C.applyInterval(IntervalType.TERTIA.createFlat()));
		assertSame(Tone.A, Tone.C.applyInterval(IntervalType.TRIDECIMA.create()));
		assertSame(Tone.CS, Tone.A.applyInterval(IntervalType.TERTIA.create()));
		assertSame(Tone.DB, Tone.A.applyInterval(IntervalType.QUARTA.createFlat()));
	}

}
