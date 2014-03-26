package org.mech.tritone.music.service;

import java.util.List;

import org.mech.tritone.music.model.Pattern;
import org.mech.tritone.music.model.instrument.string.StringedPitch;
import org.mech.tritone.music.model.notation.fretboard.FingeredPitch;
import org.mech.tritone.music.model.notation.fretboard.Fretboard;

public interface FretboardService {

	/**
	 * Apply fingering if you know what notes to play, adapted to starting
	 * position and fingerIndex. It serves as filter
	 * 
	 * @param fretboard
	 *            the fretboard
	 * @param notesToPlay
	 *            the notes to play
	 * @param start
	 *            the starting pitch
	 * @param fingerIndex
	 *            the finger index on starting pitch
	 * @return the list of fingered pitch
	 */
	List<FingeredPitch> applyFingering(Fretboard fretboard,
			List<StringedPitch> notesToPlay,
			StringedPitch start,
			int fingerIndex);

	/**
	 * Apply fingering when playing pattern, from fingered pitch
	 * 
	 * @param fretboard
	 *            the fretboard
	 * @param pattern
	 *            the pattern
	 * @param referenced
	 *            the referenced
	 * @return  the list of fingered pitch
	 */
	List<FingeredPitch> applyFingering(Fretboard fretboard,
			Pattern pattern,
			FingeredPitch referenced);

	/**
	 * Apply fingering when playing pattern from pitch class with givent finger
	 * on given string
	 * 
	 * @param fretboard
	 *            the fretboard
	 * @param pattern
	 *            the pattern
	 * @param pitchClass
	 *            the pitch class
	 * @param fingerIndex
	 *            the finger index
	 * @param stringIndex
	 *            the string index
	 * @return the list of fingered pitch
	 */
	List<FingeredPitch> applyFingering(Fretboard fretboard, 
			Pattern pattern,
			int pitchClass, 
			int fingerIndex,
			int stringIndex);

	/**
	 * Find stringed pitchs on all fretboard of given class
	 *
	 * @param fretboard the fretboard
	 * @param pitchClass the pitch class
	 * @return the list
	 */
	List<StringedPitch> findStringedPitchs(Fretboard fretboard, int pitchClass);

	/**
	 * Find stringed pitchs of given class on given string
	 *
	 * @param fretboard the fretboard
	 * @param pitchClass the pitch class
	 * @param stringIndex the string index
	 * @return the list
	 */
	List<StringedPitch> findStringedPitchs(Fretboard fretboard,
			int pitchClass,
			int stringIndex);

	/**
	 * Find stringed pitchs on whole fretboard in given fret range
	 *
	 * @param fretboard the fretboard
	 * @param pitchClass the pitch class
	 * @param fretFrom the fret from
	 * @param fretTo the fret to
	 * @return the list
	 */
	List<StringedPitch> findStringedPitchs(Fretboard fretboard, 
			int pitchClass,
			int fretFrom,
			int fretTo);

	/**
	 * Find stringed pitchs of given pitchClass on fret range and given string
	 *
	 * @param fretboard the fretboard
	 * @param pitchClass the pitch class
	 * @param fretFrom the fret from
	 * @param fretTo the fret to
	 * @param stringIndex the string index
	 * @return the list
	 */
	List<StringedPitch> findStringedPitchs(Fretboard fretboard, 
			int pitchClass,
			int fretFrom, 
			int fretTo, 
			int stringIndex);

	/**
	 * Finds all stringed pitchs from pattern on fretboard from referenced pitch.
	 * 
	 * @param fretboard
	 *            the fretboard
	 * @param pattern
	 *            the pattern
	 * @param pitchClass
	 *            the pitch class
	 * @return the list
	 */
	List<StringedPitch> findStringedPitchs(Fretboard fretboard,
			Pattern pattern, 
			int referencePitchClass);

	/**
	 * Find stringed pitchs from pattern on fretboard on fret range from referenced pitched
	 *
	 * @param fretboard the fretboard
	 * @param pattern the pattern
	 * @param referencePitchClass the reference pitch class
	 * @param fretFrom the fret from
	 * @param fretTo the fret to
	 * @return the list
	 */
	List<StringedPitch> findStringedPitchs(Fretboard fretboard,
			Pattern pattern,
			int referencePitchClass,
			int fretFrom,
			int fretTo);

}
