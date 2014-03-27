package org.mech.tritone.music.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mech.tritone.music.model.Pattern;
import org.mech.tritone.music.model.Pitch;
import org.mech.tritone.music.model.Tone;
import org.mech.tritone.music.model.instrument.string.StringedPitch;
import org.mech.tritone.music.model.instrument.string.Strings;
import org.mech.tritone.music.model.notation.fretboard.FingeredPitch;
import org.mech.tritone.music.model.notation.fretboard.Fingering;
import org.mech.tritone.music.model.notation.fretboard.Fretboard;
import org.mech.tritone.music.service.FretboardService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * The FretboardServiceImpl.
 * </p>
 * <p>
 * Date: 9.2.2012 17:17:10
 * </p>
 * 
 * @author martin.krajc
 */
@Service("fretboardService")
public class FretboardServiceImpl implements FretboardService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<StringedPitch> findStringedPitchs(final Fretboard fretboard, final Tone tone, final int fretFrom, final int fretTo) {
		final List<StringedPitch> list = new ArrayList<StringedPitch>();

		// find for each string
		for (int i = fretboard.getStringCount() - 1; i >= 0; i--) {
			list.addAll(findStringedPitchs(fretboard, tone, fretFrom, fretTo, i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<StringedPitch> findStringedPitchs(final Fretboard fretboard, final Tone tone, final int fretFrom, final int fretTo,
			final int stringIndex) {
		final List<StringedPitch> list = new ArrayList<StringedPitch>();
		final Strings string = fretboard.getStrings(stringIndex);

		for (int fretPos = fretFrom; fretPos < fretTo; fretPos++) {
			final Pitch pitch = string.getPitches().get(fretPos);
			if (pitch.getTone() == tone) {
				list.add(new StringedPitch(pitch, string.getIndex(), fretPos));
			}
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<StringedPitch> findStringedPitchs(final Fretboard fretboard, final Tone tone) {
		return findStringedPitchs(fretboard, tone, 0, fretboard.getLength());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<StringedPitch> findStringedPitchs(final Fretboard fretboard, final Tone tone, final int stringIndex) {
		return findStringedPitchs(fretboard, tone, 0, fretboard.getLength(), stringIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<StringedPitch> findStringedPitchs(final Fretboard fretboard, final Pattern pattern, final Tone referenceTone) {
		return findStringedPitchs(fretboard, pattern, referenceTone, 0, fretboard.getLength());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<StringedPitch> findStringedPitchs(final Fretboard fretboard, final Pattern pattern, final Tone referenceTone,
			final int fretFrom, final int fretTo) {
		List<StringedPitch> list = new ArrayList<StringedPitch>();

		for (int i = 0; i < pattern.length(); i++) {
			int pitchClass = (referenceTone.getToneClass() + pattern.get(i).intValue()) % 12;
			list.addAll(findStringedPitchs(fretboard, Tone.fromToneClass(pitchClass), fretFrom, fretTo));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FingeredPitch> applyFingering(final Fretboard fretboard, final Pattern pattern, final FingeredPitch referenced) {
		return applyFingering(fretboard, pattern, referenced.getPitch().getTone(), referenced.getFingerIndex(), referenced.getStringIndex());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FingeredPitch> applyFingering(final Fretboard fretboard, final List<StringedPitch> notesToPlay, final StringedPitch start,
			final int fingerIndex) {
		final List<FingeredPitch> result = new ArrayList<FingeredPitch>();
		final Fingering fingering = new Fingering(start.getPosition(), fingerIndex, start.getStringIndex());

		final Set<String> alreadyProcessed = new HashSet<String>();

		Collections.sort(notesToPlay);

		for (StringedPitch stringedPitch : notesToPlay) {
			int fInx;
			if (!alreadyProcessed.contains(stringedPitch.getPitch().toString())) {
				if ((fInx = fingering.getFingerIndex(stringedPitch.getPosition(), stringedPitch.getStringIndex())) > 0
						&& stringedPitch.getPosition() > 0) {
					final FingeredPitch fingeredPitch = new FingeredPitch(stringedPitch, fInx);
					alreadyProcessed.add(fingeredPitch.getPitch().toString());
					result.add(fingeredPitch);
				}
			}

		}

		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FingeredPitch> applyFingering(final Fretboard fretboard, final Pattern pattern, final Tone tone, final int fingerIndex,
			final int stringIndex) {
		final List<StringedPitch> starting = findStringedPitchs(fretboard, tone, stringIndex);
		// get first tone
		int index = 0;

		if (starting.get(index).getPosition() == 0 && starting.size() > 1) {
			index++;
		}

		if (starting.get(index).getPosition() > 0 && (starting.get(index).getPosition() - fingerIndex) <= 0 && starting.size() > index + 1) {
			index++;
		}

		return applyFingering(fretboard, findStringedPitchs(fretboard, pattern, tone), starting.get(index), fingerIndex);

	}
}
