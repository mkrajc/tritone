package org.mech.tritone.music.service.string;

import java.util.ArrayList;
import java.util.List;

import org.mech.tritone.music.model.Pitch;
import org.mech.tritone.music.model.Tone;
import org.mech.tritone.music.model.instrument.string.StringedInstrument;
import org.mech.tritone.music.model.instrument.string.StringedPitch;
import org.springframework.stereotype.Service;

@Service
public class StringInstrumentServiceImpl implements StringInstrumentService {

	@Override
	public List<StringedPitch> findAllPitchs(final StringedInstrument instrument, final List<Tone> tones) {
		final List<StringedPitch> list = new ArrayList<StringedPitch>();

		for (int index = 0; index < instrument.getStringsCount(); index++) {
			list.addAll(findAllPitchs(instrument, tones, 0, instrument.getLength(), index));
		}

		return list;
	}

	@Override
	public List<StringedPitch> findAllPitchs(final StringedInstrument instrument, final List<Tone> tones, final int fretFrom,
			final int fretTo, final int stringIndex) {

		if (fretFrom < 0 || fretTo > instrument.getLength() || (fretTo - fretFrom) <= 0) {
			throw new IllegalArgumentException("Invalid fret arguments {fretFrom=" + fretFrom + ", fretTo=" + fretTo + "}");
		}

		if (stringIndex < 0 || stringIndex >= instrument.getStringsCount()) {
			throw new IllegalArgumentException("Invalid string index argument {index=" + stringIndex + ", stringCount="
					+ instrument.getStringsCount() + "}");
		}

		final List<StringedPitch> list = new ArrayList<StringedPitch>();
		final Pitch naturalStringPitch = instrument.getNaturalStringPitch(stringIndex);

		for (int fretPos = fretFrom; fretPos < fretTo; fretPos++) {
			final List<Pitch> availables = naturalStringPitch.applyDistance(fretPos);
			for (Tone t : tones) {
				for (Pitch available : availables) {
					if (available.getTone().equals(t)) {
						list.add(new StringedPitch(available, stringIndex, fretPos));
						break;
					}
				}
			}
		}

		return list;
	}
}
