package org.mech.tritone.music.service.string;

import java.util.List;

import org.mech.tritone.music.model.Tone;
import org.mech.tritone.music.model.instrument.string.StringedInstrument;
import org.mech.tritone.music.model.instrument.string.StringedPitch;

public interface StringInstrumentService {
	List<StringedPitch> findAllPitchs(StringedInstrument instrument, List<Tone> tones);

	List<StringedPitch> findAllPitchs(StringedInstrument instrument, List<Tone> tones, int fretFrom, int fretTo, int stringIndex);
}
