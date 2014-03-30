package org.mech.tritone.music.service.string;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mech.tritone.music.model.Pitch;
import org.mech.tritone.music.model.Tone;
import org.mech.tritone.music.model.instrument.string.StringedInstrument;
import org.mech.tritone.music.model.instrument.string.StringedPitch;
import org.mech.tritone.music.model.instrument.string.Tuning;

public class StringInstrumentServiceImplTest {

	private StringInstrumentService instrumentService;

	@Before
	public void setup() {
		instrumentService = new StringInstrumentServiceImpl();
	}

	@Test
	public void testFindRoot() {
		List<StringedPitch> ret = instrumentService.findAllPitchs(createTestGuitar(1, Tone.A), Collections.singletonList(Tone.A));
		assertNotNull(ret);
		assertEquals(1, ret.size());
		assertEquals(Tone.A, ret.get(0).getPitch().getTone());
		assertEquals(1, ret.get(0).getPitch().getOctave());
		assertEquals(0, ret.get(0).getPosition());
		assertEquals(0, ret.get(0).getStringIndex());
	}

	@Test
	public void testFindRootAndOctaves() {
		List<StringedPitch> ret = instrumentService.findAllPitchs(createTestGuitar(13, Tone.A), Collections.singletonList(Tone.A));
		assertNotNull(ret);
		assertEquals(2, ret.size());
		assertEquals(12, ret.get(1).getPosition());
	}

	@Test
	public void testFindChromatic() {
		List<StringedPitch> ret = instrumentService.findAllPitchs(createTestGuitar(4, Tone.A), Arrays.asList(Tone.A, Tone.AS, Tone.B));
		assertNotNull(ret);
		assertEquals(3, ret.size());
	}

	@Test
	public void testFindSameTone() {
		List<StringedPitch> ret = instrumentService.findAllPitchs(createTestGuitar(4, Tone.A), Arrays.asList(Tone.A, Tone.A, Tone.A));
		assertNotNull(ret);
		assertEquals(3, ret.size());
	}

	private StringedInstrument createTestGuitar(final int fretLength, final Tone t) {
		StringedInstrument i = new StringedInstrument();
		i.setLength(fretLength);
		i.setName("test one " + t + " string guitar");

		Tuning tuning = new Tuning();
		tuning.setKey("test");
		tuning.setName("test name");
		tuning.setPitchs(Collections.singletonList(new Pitch(t, 1)));
		i.setTuning(tuning);

		return i;
	}
}
