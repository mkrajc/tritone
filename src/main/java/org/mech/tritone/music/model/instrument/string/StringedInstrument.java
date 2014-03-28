package org.mech.tritone.music.model.instrument.string;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.mech.tritone.music.model.Pitch;
import org.mech.tritone.music.model.instrument.Instrument;
import org.mech.tritone.music.model.instrument.Range;
import org.mech.tritone.music.utils.PitchUtils;

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

	private Tuning tuning;
	/**
	 * The pitch range length. How many pitches can be played on one string
	 **/
	private int length;
	private String name;
	private Range range;

	@Override
	public Tuning getTuning() {
		return tuning;
	}

	public void setTuning(final Tuning tuning) {
		this.tuning = tuning;
	}

	@Override
	public String toString() {
		final ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE);
		builder.append(name);
		builder.append(tuning);
		builder.append(length);
		return builder.toString();
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public int getStringsCount() {
		return tuning.size();
	}

	@Override
	public Pitch getNaturalStringPitch(final int stringIndex) {
		return tuning.get(stringIndex);
	}

	public int getLength() {
		return length;
	}

	public void setLength(final int length) {
		this.length = length;
	}

	@Override
	public Range getRange() {
		if (range == null) {
			range = new Range();
			range.setFrom(tuning.getRange().from());
			final Pitch lastPitch = PitchUtils.aug(tuning.getRange().to(), length);
			range.setTo(lastPitch);
		}
		return range;
	}

}
