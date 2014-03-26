package org.mech.tritone.music.model.instrument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.mech.tritone.music.model.Pitch;
import org.mech.tritone.music.utils.PitchUtils;

public class Tuning {
	
	private String name;
	private String key;

	private List<Pitch> pitchs;

	public Pitch get(int index) {
		return pitchs.get(index);
	}

	public List<Pitch> get() {
		return pitchs;
	}

	public void setTuning(final List<String> pitchsNotations) {
		this.pitchs = new ArrayList<Pitch>();
		for(String s : pitchsNotations){
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

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
