package org.mech.tritone.render.pure.si;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Test;

public class PureFretboardRendererTest {

	private final PureFretboardRenderer renderer = new PureFretboardRenderer();

	@Test
	public void testRenderStringEnd() {
		final StringWriter w = new StringWriter();
		final PrintWriter writer = new PrintWriter(w);
		renderer.renderStringEnd(writer, null, 1);
		assertEquals("", w.toString());
	}

	@Test
	public void testRenderStringStart() {
		final StringWriter w = new StringWriter();
		final PrintWriter writer = new PrintWriter(w);
		renderer.renderStringStart(writer, null, 1);
		assertEquals("1 ", w.toString());
	}

	@Test
	public void testRenderObject() {
		final StringWriter w = new StringWriter();
		final PrintWriter writer = new PrintWriter(w);
		renderer.renderObject("T", writer, null, 0, 0);
		assertEquals("T", w.toString());
	}

}
