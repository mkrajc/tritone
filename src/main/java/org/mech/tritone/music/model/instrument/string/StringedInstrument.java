package org.mech.tritone.music.model.instrument.string;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.mech.tritone.music.model.Pitch;
import org.mech.tritone.music.model.instrument.Instrument;
import org.mech.tritone.music.model.instrument.Range;

/**
 * <p>
 * The StringInstrument represent string music instrument (guitar, violin,
 * ukulele...)
 * </p>
 * <p>
 * Date: 8.2.2012 13:25:38
 * </p>
 * 
 * @author martin.krajc
 */
public class StringedInstrument implements Instrument, HasStrings {

	private final Tuning tuning;
	private final String[] strings;
	/**
	 * The pitch range length. How many pitches can be played on one string
	 **/
	private final int length;
	private java.lang.String name;
	private Range range;

	public StringedInstrument(final Tuning t, final int length) {
		this.tuning = t;
		this.length = length;
		this.strings = new String[tuning.size()];

		for (int i = 0; i < tuning.size(); i++) {
			strings[i] = new String(tuning.get(i), i, length);
		}
	}

	@Override
	public Tuning getTuning() {
		return tuning;
	}

	@Override
	public java.lang.String toString() {
		final ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE);
		builder.append(name);
		builder.append(tuning);
		builder.append(length);
		return builder.toString();
	}

	@Override
	public java.lang.String getName() {
		return name;
	}

	public void setName(final java.lang.String name) {
		this.name = name;
	}

	@Override
	public int getStringsCount() {
		return strings.length;
	}

	@Override
	public Pitch getNaturalStringPitch(final int stringIndex) {
		return tuning.get(stringIndex);
	}

	public int getLength() {
		return length;
	}

	@Override
	public Range getRange() {
		if (range == null) {
			range = new Range();
			range.setFrom(tuning.getRange().from());
			final Pitch lastPitch = null; // PitchUtils.aug(tuning.getRange().to(),
											// length);
			range.setTo(lastPitch);
		}
		return range;
	}

	@Override
	public String getString(final int index) {
		return strings[index];
	}

}
