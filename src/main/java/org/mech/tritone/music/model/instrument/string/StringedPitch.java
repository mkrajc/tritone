package org.mech.tritone.music.model.instrument.string;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.mech.tritone.music.model.Pitch;

public class StringedPitch implements Comparable<StringedPitch> {
	private Pitch pitch;
	private int stringIndex;
	private int position;

	public StringedPitch(Pitch pitch, int stringIndex, int position) {
		super();
		this.pitch = pitch;
		this.stringIndex = stringIndex;
		this.position = position;
	}

	public Pitch getPitch() {
		return pitch;
	}

	public void setPitch(Pitch pitch) {
		this.pitch = pitch;
	}

	public int getStringIndex() {
		return stringIndex;
	}

	public void setStringIndex(int stringIndex) {
		this.stringIndex = stringIndex;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this,
				ToStringStyle.SIMPLE_STYLE);
		builder.append(pitch);
		builder.append(stringIndex);
		builder.append(position);
		return builder.toString();
	}
	
	

	@Override
	public int compareTo(StringedPitch sPitch) {
		int sIndexCompare = stringIndex < sPitch.getStringIndex() ? -1
				: (stringIndex == sPitch.getStringIndex() ? 0 : 1);
		if (sIndexCompare == 0) {
			return position < sPitch.getPosition() ? -1 : (position == sPitch
					.getPosition() ? 0 : 1);
		}

		return sIndexCompare;
	}
}
