package org.mech.tritone.music.service.string;

import java.util.List;

import org.mech.tritone.music.model.Tone;
import org.mech.tritone.music.model.TonePattern;
import org.mech.tritone.music.model.instrument.finger.FingerType;
import org.mech.tritone.music.model.instrument.finger.FingeredPitch;
import org.mech.tritone.music.model.instrument.string.StringedInstrument;
import org.mech.tritone.music.model.instrument.string.StringedPitch;

public interface StringInstrumentService {
	List<StringedPitch> findAllPitchs(StringedInstrument instrument, TonePattern pattern);
	List<StringedPitch> findAllPitchs(StringedInstrument instrument, List<Tone> tones);

	List<FingeredPitch> findFingering(final StringedInstrument instrument, TonePattern pattern, FingerType finger, int stringIndex);
	
	
}
