package org.mech.tritone.music.service;


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
//	List<FingeredPitch> applyFingering(Fretboard fretboard, List<StringedPitch> notesToPlay, StringedPitch start, int fingerIndex);

	/**
	 * Apply fingering when playing pattern, from fingered pitch
	 * 
	 * @param fretboard
	 *            the fretboard
	 * @param pattern
	 *            the pattern
	 * @param referenced
	 *            the referenced
	 * @return the list of fingered pitch
	 */
//	List<FingeredPitch> applyFingering(Fretboard fretboard, Pattern pattern, FingeredPitch referenced);

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
//	List<FingeredPitch> applyFingering(Fretboard fretboard, Pattern pattern, Tone tone, int fingerIndex, int stringIndex);

	/**
	 * Find stringed pitchs on all fretboard of given class
	 * 
	 * @param fretboard
	 *            the fretboard
	 * @param tone
	 *            tone
	 * @return the list
	 */
//	List<StringedPitch> findStringedPitchs(Fretboard fretboard, Tone tone);

	/**
	 * Find stringed pitchs of given class on given string
	 * 
	 * @param fretboard
	 *            the fretboard
	 * @param pitchClass
	 *            the pitch class
	 * @param stringIndex
	 *            the string index
	 * @return the list
	 */
//	List<StringedPitch> findStringedPitchs(Fretboard fretboard, Tone tone, int stringIndex);

	/**
	 * Find stringed pitchs on whole fretboard in given fret range
	 * 
	 * @param fretboard
	 *            the fretboard
	 * @param pitchClass
	 *            the pitch class
	 * @param fretFrom
	 *            the fret from
	 * @param fretTo
	 *            the fret to
	 * @return the list
	 */
//	List<StringedPitch> findStringedPitchs(Fretboard fretboard, Tone tone, int fretFrom, int fretTo);

	/**
	 * Find stringed pitchs of given pitchClass on fret range and given string
	 * 
	 * @param fretboard
	 *            the fretboard
	 * @param pitchClass
	 *            the pitch class
	 * @param fretFrom
	 *            the fret from
	 * @param fretTo
	 *            the fret to
	 * @param stringIndex
	 *            the string index
	 * @return the list
	 */
//	List<StringedPitch> findStringedPitchs(Fretboard fretboard, Tone tone, int fretFrom, int fretTo, int stringIndex);

	/**
	 * Finds all stringed pitchs from pattern on fretboard from referenced
	 * pitch.
	 * 
	 * @param fretboard
	 *            the fretboard
	 * @param pattern
	 *            the pattern
	 * @param pitchClass
	 *            the pitch class
	 * @return the list
	 */
//	CList<StringedPitch> findStringedPitchs(Fretboard fretboard, Pattern pattern, Tone referenceTone);

	/**
	 * Find stringed pitchs from pattern on fretboard on fret range from
	 * referenced pitched
	 * 
	 * @param fretboard
	 *            the fretboard
	 * @param pattern
	 *            the patternW
	 * @param referencePitchClass
	 *            the reference pitch class
	 * @param fretFrom
	 *            the fret from
	 * @param fretTo
	 *            the fret to
	 * @return the list
	 */
//	List<StringedPitch> findStringedPitchs(Fretboard fretboard, Pattern pattern, Tone referenceTone, int fretFrom, int fretTo);

}
