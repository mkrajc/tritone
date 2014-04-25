package org.mech.tritone.music.model.instrument.string;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.mech.tritone.music.model.Pitch;

public class StringedPitch implements Comparable<StringedPitch> {
	private Pitch pitch;
	private int stringIndex;
	private int position;

	public StringedPitch(final Pitch pitch, final int stringIndex, final int position) {
		super();
		this.pitch = pitch;
		this.stringIndex = stringIndex;
		this.position = position;
	}

	public Pitch getPitch() {
		return pitch;
	}

	public void setPitch(final Pitch pitch) {
		this.pitch = pitch;
	}

	public int getStringIndex() {
		return stringIndex;
	}

	public void setStringIndex(final int stringIndex) {
		this.stringIndex = stringIndex;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(final int position) {
		this.position = position;
	}

	@Override
	public java.lang.String toString() {
		final ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE);
		builder.append(pitch);
		builder.append(stringIndex);
		builder.append(position);
		return builder.toString();
	}

	@Override
	public int compareTo(final StringedPitch sPitch) {
		final int sIndexCompare = Integer.valueOf(stringIndex).compareTo(sPitch.getStringIndex());
		if (sIndexCompare == 0) {
			return Integer.valueOf(position).compareTo(sPitch.getPosition());
		}
		return sIndexCompare;
	}
}
