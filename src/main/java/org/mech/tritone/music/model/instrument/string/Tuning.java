package org.mech.tritone.music.model.instrument.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.mech.tritone.music.model.Pitch;
import org.mech.tritone.music.model.instrument.HasRange;
import org.mech.tritone.music.model.instrument.Range;
import org.mech.tritone.music.utils.PitchUtils;

public class Tuning implements HasRange {

	private String name;
	private String key;

	public void setPitchs(final List<Pitch> pitchs) {
		this.pitchs = pitchs;
	}

	private List<Pitch> pitchs;
	private Range range;

	public Pitch get(final int index) {
		return pitchs.get(index);
	}

	public List<Pitch> get() {
		return pitchs;
	}

	public int size() {
		return pitchs.size();
	}

	public void setTuning(final List<String> pitchsNotations) {
		this.pitchs = new ArrayList<Pitch>();
		for (final String s : pitchsNotations) {
			pitchs.add(PitchUtils.toPitch(s));
		}
	}

	@Override
	public String toString() {
		return Arrays.toString(pitchs.toArray());
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(final String key) {
		this.key = key;
	}

	@Override
	public Range getRange() {
		if (range == null) {
			range = new Range();
			range.setFrom(pitchs.get(0));
			range.setTo(pitchs.get(pitchs.size()));
		}
		return range;
	}
}
