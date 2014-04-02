package org.mech.tritone.music.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mech.tritone.music.utils.Notation;

public class ToneTest {

	@Test
	public void testApplyInterval() {
		final Interval unison = Interval.create("1");
		final Interval octava = IntervalType.OCTAVA.create();
		final Interval fifth = IntervalType.QUINTA.create();

		assertSame(Tone.A, Tone.A.applyInterval(unison));
		assertSame(Tone.A, Tone.A.applyInterval(octava));
		assertSame(Tone.G, Tone.C.applyInterval(fifth));
		assertSame(Tone.EB, Tone.C.applyInterval(IntervalType.TERTIA.createFlat()));
		assertSame(Tone.A, Tone.C.applyInterval(IntervalType.TRIDECIMA.create()));
		assertSame(Tone.CS, Tone.A.applyInterval(IntervalType.TERTIA.create()));
		assertSame(Tone.DB, Tone.A.applyInterval(IntervalType.QUARTA.createFlat()));
	}
	
	@Test
	public void testToString() {
		assertEquals("C#", Tone.CS.format());
		assertEquals("C#", Tone.CS.format(Notation.US));
		assertEquals("Cis", Tone.CS.format(Notation.EU));
		
		assertEquals("Cb", Tone.CB.format());
		assertEquals("Cb", Tone.CB.format(Notation.US));
		assertEquals("Ces", Tone.CB.format(Notation.EU));
		
		assertEquals("C##", Tone.CSS.format());
		assertEquals("C##", Tone.CSS.format(Notation.US));
		assertEquals("Cisis", Tone.CSS.format(Notation.EU));
		
		assertEquals("Cbb", Tone.CBB.format());
		assertEquals("Cbb", Tone.CBB.format(Notation.US));
		assertEquals("Ceses", Tone.CBB.format(Notation.EU));
		
		assertEquals("B", Tone.BB.format(Notation.EU));
		assertEquals("Bb", Tone.BB.format(Notation.US));
		assertEquals("H", Tone.B.format(Notation.EU));
		assertEquals("B", Tone.B.format(Notation.US));
	}
	
	@Test
	public void testFromString() {
		assertEquals(Tone.CS, Tone.fromString("c#"));
		assertEquals(Tone.CB, Tone.fromString("cb"));
		assertEquals(Tone.CS, Tone.fromString("cis", Notation.EU));
		assertEquals(Tone.CB, Tone.fromString("ces", Notation.EU));
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testFromStringInvalidUs() {
		Tone.fromString("css", Notation.US);
	}
	

	@Test(expected= IllegalArgumentException.class)
	public void testFromStringInvalidEu() {
		Tone.fromString("cb", Notation.EU);
	}
	
	@Test
	public void testFromStringBandH() {
		assertEquals(Tone.BB, Tone.fromString("B", Notation.EU));
		assertEquals(Tone.B, Tone.fromString("H", Notation.EU));
		assertEquals(Tone.B, Tone.fromString("B", Notation.US));
	}
	
	@Test
	public void testToStringAll() {
		final Tone[] values = Tone.values();
		
		for(final Tone t : values){
			assertNotNull(Notation.toString(t, Notation.EU));
		}
		
		for(final Tone t : values){
			assertNotNull(Notation.toString(t, Notation.US));
		}
	}

}
