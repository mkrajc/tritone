package org.mech.tritone.render.context;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FretboardContextTest {
	
	private FretboardContext context;
	
	@Before
	public void setup() {
		context = new FretboardContext();
	}

	@Test
	public void testSetAndGet() {
		final Object test = new Object();
		context.set(0, 0, test);
		assertSame(test, context.get(0, 0));
		context.set(0, 1, test);
		assertSame(test, context.get(0, 1));
	}

}
