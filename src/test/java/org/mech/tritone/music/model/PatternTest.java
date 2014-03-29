package org.mech.tritone.music.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class PatternTest {

	private Pattern pattern;

	@Before
	public void setup() {
		pattern = new Pattern() {

			@Override
			public PatternType getType() {
				return null;
			}
		};
	}

	@Test
	public void testGet() {
		pattern.setIntervals(new int[] { 1, 2 });
		assertEquals(1, pattern.get(0));
		assertEquals(2, pattern.get(1));
	}

	@Test
	public void testLength() {
		pattern.setIntervals(new int[] { 1, 2 });
		assertEquals(2, pattern.length());
	}

	@Test
	public void testGetName() {
		pattern.setName("test");
		assertEquals("test", pattern.getName());
	}

	@Test
	public void testToString() {
		assertNotNull(pattern.toString());
	}

	@Test
	public void testGetIntervals() {
		final int[] intervals = new int[] { 1, 2 };
		pattern.setIntervals(intervals);
		assertSame(intervals, pattern.getIntervals());
	}

	@Test
	public void testSetIntervalsStringOk() {
		pattern.setIntervalsString(new String[] { "1", "b3", "aug5" });
		assertNotNull(pattern.getIntervals());
		assertEquals(0, pattern.get(0));
		assertEquals(3, pattern.get(1));
		assertEquals(8, pattern.get(2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetIntervalsStringBad() {
		pattern.setIntervalsString(new String[] { "test" });
	}

	@Test
	public void testGetAbbrv() {
		pattern.setAbbrv("test");
		assertEquals("test", pattern.getAbbrv());
	}

	@Test
	public void testGetType() {
		assertNull(pattern.getType());
	}

	@Test
	public void testGetKey() {
		pattern.setKey("test");
		assertEquals("test", pattern.getKey());
	}

	@Test
	public void testGetTones() {
		final String[] majorChordIntervals = new String[] { "1", "3", "5" };
		pattern.setIntervalsString(majorChordIntervals);

		Collection<Tone> tones = pattern.toTones(Tone.C);

		assertEquals(3, tones.size());
		final Iterator<Tone> iterator = tones.iterator();
		assertEquals(Tone.C, iterator.next());
		assertEquals(Tone.E, iterator.next());
		assertEquals(Tone.G, iterator.next());
	}

}
