package org.mech.tritone.music.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mech.tritone.music.model.Pattern;
import org.mech.tritone.music.model.Pitch;
import org.mech.tritone.music.model.instrument.string.StringedPitch;
import org.mech.tritone.music.model.instrument.string.Strings;
import org.mech.tritone.music.model.notation.fretboard.FingeredPitch;
import org.mech.tritone.music.model.notation.fretboard.Fingering;
import org.mech.tritone.music.model.notation.fretboard.Fretboard;
import org.mech.tritone.music.service.FretboardService;
import org.mech.tritone.music.utils.PitchUtils;
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
	public List<StringedPitch> findStringedPitchs(Fretboard fretboard,
			int pitchClass, int fretFrom, int fretTo) {
		final List<StringedPitch> list = new ArrayList<StringedPitch>();

		// find for each string
		for (int i = fretboard.getStringCount() - 1; i >= 0; i--) {
			list.addAll(findStringedPitchs(fretboard, pitchClass, fretFrom, fretTo, i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<StringedPitch> findStringedPitchs(Fretboard fretboard,
			int pitchClass, int fretFrom, int fretTo, int stringIndex) {
		final List<StringedPitch> list = new ArrayList<StringedPitch>();
		final Strings string = fretboard.getStrings(stringIndex);
		
		for (int fretPos = fretFrom; fretPos < fretTo; fretPos++) {
			final Pitch pitch = string.getPitches().get(fretPos);
			if (pitch.getPitchClass() == pitchClass) {
				list.add(new StringedPitch(pitch, string.getIndex(), fretPos));
			}
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<StringedPitch> findStringedPitchs(Fretboard fretboard,
			int pitchClass) {
		return findStringedPitchs(fretboard, pitchClass, 0, fretboard.getLength());
	}

	/**
	 * {@inheritDoc}
	 */
	public List<StringedPitch> findStringedPitchs(Fretboard fretboard,
			int pitchClass, int stringIndex) {
		return findStringedPitchs(fretboard, pitchClass, 0, fretboard.getLength(), stringIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<StringedPitch> findStringedPitchs(Fretboard fretboard,
			Pattern pattern, int referencePitchClass) {
		return findStringedPitchs(fretboard, pattern, referencePitchClass, 0, fretboard.getLength());
	}

	/**
	 * {@inheritDoc}
	 */
	public List<StringedPitch> findStringedPitchs(Fretboard fretboard,
			Pattern pattern, int referencePitchClass, int fretFrom, int fretTo) {
		List<StringedPitch> list = new ArrayList<StringedPitch>();

		for (int i = 0; i < pattern.length(); i++) {
			int pitchClass = (referencePitchClass + pattern.get(i).intValue()) % 12;
			list.addAll(findStringedPitchs(fretboard, pitchClass, fretFrom,
					fretTo));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<FingeredPitch> applyFingering(Fretboard fretboard,
			Pattern pattern, FingeredPitch referenced) {
		return applyFingering(fretboard,
				pattern,
				referenced.getPitch().getPitchClass(),
				referenced.getFingerIndex(),
				referenced.getStringIndex());
	}

	/**
	 * {@inheritDoc}
	 */
	public List<FingeredPitch> applyFingering(Fretboard fretboard,
			List<StringedPitch> notesToPlay, 
			StringedPitch start,
			int fingerIndex) {
		final List<FingeredPitch> result = new ArrayList<FingeredPitch>();
		final Fingering fingering = new Fingering(start.getPosition(),
				fingerIndex, start.getStringIndex());

		final Set<String> alreadyProcessed = new HashSet<String>();
		
		Collections.sort(notesToPlay);
		
		for (StringedPitch stringedPitch : notesToPlay) {
			int fInx;
			if (!alreadyProcessed.contains(PitchUtils.toString(stringedPitch.getPitch()))) {
				if ((fInx = fingering.getFingerIndex(
						stringedPitch.getPosition(),
						stringedPitch.getStringIndex())) > 0
						&& stringedPitch.getPosition() > 0) 
				{
					final FingeredPitch fingeredPitch = new FingeredPitch(	stringedPitch, fInx);
					alreadyProcessed.add(PitchUtils.toString(fingeredPitch.getPitch()));
					result.add(fingeredPitch);
				}
			}

		}

		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<FingeredPitch> applyFingering(Fretboard fretboard,
			Pattern pattern, int pitchClass, int fingerIndex, int stringIndex) {
		final List<StringedPitch> starting = findStringedPitchs(fretboard,
				pitchClass, stringIndex);
		// get first tone
		int index = 0;
		
		if (starting.get(index).getPosition() == 0 && starting.size() > 1) {
			index++;
		}

		if (starting.get(index).getPosition() > 0
				&& (starting.get(index).getPosition() - fingerIndex) <= 0
				&& starting.size() > index + 1) {
			index++;
		}
	
		return applyFingering(fretboard,
				findStringedPitchs(fretboard, pattern, pitchClass),
				starting.get(index),
				fingerIndex);

	}
}
