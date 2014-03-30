package org.mech.tritone.music.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class IntervalTest {

	private void testFlat(final Interval i, final IntervalType intervalType) {
		assertTrue(i.isFlat());
		assertFalse(i.isSharp());
		assertFalse(i.isDoubleFlat());
		assertFalse(i.isDoubleSharp());
		assertEquals(intervalType, i.getIntervalType());
	}

	private void testDoubleFlat(final Interval i, final IntervalType intervalType) {
		assertTrue(i.isFlat());
		assertFalse(i.isSharp());
		assertTrue(i.isDoubleFlat());
		assertFalse(i.isDoubleSharp());
		assertEquals(intervalType, i.getIntervalType());
	}

	private void testDoubleSharp(final Interval i, final IntervalType intervalType) {
		assertTrue(i.isSharp());
		assertTrue(i.isDoubleSharp());
		assertFalse(i.isFlat());
		assertFalse(i.isDoubleFlat());
		assertEquals(intervalType, i.getIntervalType());
	}

	private void testSharp(final Interval i, final IntervalType intervalType) {
		assertTrue(i.isSharp());
		assertFalse(i.isDoubleSharp());
		assertFalse(i.isFlat());
		assertFalse(i.isDoubleFlat());
		assertEquals(intervalType, i.getIntervalType());
	}

	@Test
	public void testIsFlat() {
		final Interval minorThird = IntervalType.TERTIA.createFlat();
		testFlat(minorThird, IntervalType.TERTIA);
	}

	@Test
	public void testIsDoubleSharp() {
		testDoubleSharp(IntervalType.TERTIA.createDoubleSharp(), IntervalType.TERTIA);
	}

	@Test
	public void testIsDoubleFlat() {
		final Interval dsThird = IntervalType.TERTIA.createDoubleFlat();
		testDoubleFlat(dsThird, IntervalType.TERTIA);
	}

	@Test
	public void testString() {
		final Interval minorThird = IntervalType.TERTIA.createFlat();
		final Interval dfThird = IntervalType.TERTIA.createDoubleFlat();
		final Interval dsThird = IntervalType.TERTIA.createDoubleSharp();
		final Interval augFifth = IntervalType.QUINTA.createSharp();
		assertEquals("b3", minorThird.toString());
		assertEquals("bb3", dfThird.toString());
		assertEquals("##3", dsThird.toString());
		assertEquals("#5", augFifth.toString());
	}

	@Test
	public void testEquals() {
		Interval first = Interval.create("1");
		Interval second = IntervalType.PRIMA.create();
		assertTrue(first.equals(second));
	}

	@Test
	public void testGetDistance() {
		Interval unison = IntervalType.PRIMA.create();
		assertEquals(0, unison.getDistance());

		Interval augThird = IntervalType.TERTIA.createSharp();
		Interval fourth = IntervalType.QUARTA.create();
		Interval dfFifth = IntervalType.QUINTA.createDoubleFlat();

		assertEquals(augThird.getDistance(), fourth.getDistance());
		assertEquals(dfFifth.getDistance(), fourth.getDistance());
	}

	@Test
	public void testIsSharp() {
		final Interval augFifth = IntervalType.QUINTA.createSharp();
		testSharp(augFifth, IntervalType.QUINTA);
	}

	@Test
	public void testCreateFromString() {
		assertEquals(IntervalType.PRIMA, Interval.create("1").getIntervalType());
		testFlat(Interval.create("b3"), IntervalType.TERTIA);
		testDoubleFlat(Interval.create("bb7"), IntervalType.SEPTIMA);
		testSharp(Interval.create("#5"), IntervalType.QUINTA);
		testDoubleSharp(Interval.create("##4"), IntervalType.QUARTA);

		Interval _13th = Interval.create("13");
		assertEquals(IntervalType.SEXTA.getDistance() + 12, _13th.getDistance());

	}
}
