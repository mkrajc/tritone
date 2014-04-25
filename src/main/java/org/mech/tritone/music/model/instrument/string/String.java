package org.mech.tritone.music.model.instrument.string;

import java.util.ArrayList;
import java.util.List;

import org.mech.tritone.music.model.Pitch;
import org.mech.tritone.music.model.Tone;
import org.springframework.util.CollectionUtils;

public class String {

	private final Pitch open;
	private final int stringIndex;
	private final int length;

	public String(final Pitch open, final int stringIndex, final int length) {
		super();
		this.open = open;
		this.stringIndex = stringIndex;
		this.length = length;
	}

	public List<StringedPitch> getFromTone(final Tone tone) {
		return getFromTone(tone, 0, length);
	}

	public List<StringedPitch> getFromTone(final Tone tone, final int from, final int to) {
		final List<StringedPitch> result = new ArrayList<StringedPitch>();
		for (int fret = from; fret < to; fret++) {
			final List<Pitch> availables = open.applyDistance(fret);
			final Pitch filterPitchByTone = filterPitchByTone(availables, tone);

			if (filterPitchByTone != null) {
				result.add(new StringedPitch(filterPitchByTone, stringIndex, fret));
			}
		}

		return result;
	}

	public StringedPitch getFirstFromTone(final Tone tone, final int from, final int to) {
		final List<StringedPitch> fromTone = getFromTone(tone, from, to);
		return CollectionUtils.isEmpty(fromTone) ? null : fromTone.get(0);
	}

	private Pitch filterPitchByTone(final List<Pitch> availables, final Tone t) {
		for (final Pitch available : availables) {
			if (available.getTone().equals(t)) {
				return available;
			}
		}
		return null;
	}

	public Pitch getOpen() {
		return open;
	}

	public int getStringIndex() {
		return stringIndex;
	}

	public int getLength() {
		return length;
	}

}
