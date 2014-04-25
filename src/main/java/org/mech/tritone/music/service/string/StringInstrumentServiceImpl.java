package org.mech.tritone.music.service.string;

import java.util.ArrayList;
import java.util.List;

import org.mech.tritone.music.model.Tone;
import org.mech.tritone.music.model.TonePattern;
import org.mech.tritone.music.model.instrument.finger.FingerType;
import org.mech.tritone.music.model.instrument.finger.FingeredPitch;
import org.mech.tritone.music.model.instrument.finger.Fingering;
import org.mech.tritone.music.model.instrument.string.String;
import org.mech.tritone.music.model.instrument.string.StringedInstrument;
import org.mech.tritone.music.model.instrument.string.StringedPitch;
import org.springframework.stereotype.Service;

@Service
public class StringInstrumentServiceImpl implements StringInstrumentService {

	@Override
	public List<StringedPitch> findAllPitchs(final StringedInstrument instrument, final TonePattern pattern) {
		return findAllPitchs(instrument, pattern.toTones());
	}

	@Override
	public List<StringedPitch> findAllPitchs(final StringedInstrument instrument, final List<Tone> tones) {
		final List<StringedPitch> list = new ArrayList<StringedPitch>();

		for (int index = 0; index < instrument.getStringsCount(); index++) {
			list.addAll(findAllPitchs(instrument, tones, index));
		}

		return list;
	}

	private List<StringedPitch> findAllPitchs(final StringedInstrument instrument, final List<Tone> tones,
			final int stringIndex) {
		final List<StringedPitch> list = new ArrayList<StringedPitch>();
		final String str = instrument.getString(stringIndex);

		for (final Tone t : tones) {
			list.addAll(str.getFromTone(t));
		}

		return list;
	}

	@Override
	public List<FingeredPitch> findFingering(final StringedInstrument instrument, final TonePattern pattern,
			final FingerType finger, final int stringIndex) {

		if (stringIndex < 0 || stringIndex >= instrument.getStringsCount()) {
			throw new IllegalArgumentException("String index [" + stringIndex + "] is out of bounds for instrument ["
					+ instrument + "]");
		}

		final List<Tone> tones = pattern.toTones();

		final String string = instrument.getString(stringIndex);
		final List<StringedPitch> allRoots = string.getFromTone(pattern.getRoot());

		final List<FingeredPitch> result = new ArrayList<FingeredPitch>();

		for (final StringedPitch rootPitch : allRoots) {
			final Fingering fingering = new Fingering(new FingeredPitch(rootPitch, finger));
			final int to = Math.min(instrument.getLength(), fingering.to());
			final int from = fingering.from();

			for (int stIdx = 0; stIdx < instrument.getStringsCount(); stIdx++) {
				for (final Tone t : tones) {
					final StringedPitch fromTone = instrument.getString(stIdx).getFirstFromTone(t, from, to);
					if (fromTone != null) {
						final FingerType fingerOnFret = fingering.getFinger(fromTone.getPosition(),
								string.getStringIndex());
						if (fingerOnFret != null) {
							result.add(new FingeredPitch(fromTone, fingerOnFret));
						}
					}
				}
			}

		}

		return result;
	}

}
