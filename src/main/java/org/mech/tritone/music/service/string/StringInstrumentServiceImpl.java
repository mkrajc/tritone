package org.mech.tritone.music.service.string;

import java.util.ArrayList;
import java.util.List;

import org.mech.tritone.music.model.Pitch;
import org.mech.tritone.music.model.Tone;
import org.mech.tritone.music.model.instrument.string.StringedInstrument;
import org.mech.tritone.music.model.instrument.string.StringedPitch;
import org.mech.tritone.music.utils.PitchUtils;
import org.springframework.stereotype.Service;

@Service
public class StringInstrumentServiceImpl implements StringInstrumentService {

	@Override
	public List<StringedPitch> findAllPitchs(final StringedInstrument instrument, final List<Tone> tones) {

		return null;
	}

	@Override
	public List<StringedPitch> findAllPitchs(final StringedInstrument instrument, final Tone tone, final int fretFrom,
			final int fretTo, final int stringIndex) {

		if (fretFrom < 0 || fretTo > instrument.getLength() || (fretFrom - fretTo) <= 0) {
			throw new IllegalArgumentException("Invalid fret arguments {fretFrom=" + fretFrom + ", fretTo=" + fretTo
					+ "}");
		}

		if (stringIndex < 0 || stringIndex >= instrument.getStringsCount()) {
			throw new IllegalArgumentException("Invalid string index argument {index=" + stringIndex + ", stringCount="
					+ instrument.getStringsCount() + "}");
		}

		final List<StringedPitch> list = new ArrayList<StringedPitch>();
		final Pitch naturalStringPitch = instrument.getNaturalStringPitch(stringIndex);

		for (int fretPos = fretFrom; fretPos < fretTo; fretPos++) {
			final Pitch pitchToCompare = PitchUtils.aug(naturalStringPitch, fretPos);
			if (naturalStringPitch.getTone().equals(pitchToCompare.getTone())) {
				list.add(new StringedPitch(pitchToCompare, stringIndex, fretPos));
			}
		}

		return list;
	}

}
