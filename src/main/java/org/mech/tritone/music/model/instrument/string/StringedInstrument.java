package org.mech.tritone.music.model.instrument.string;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.mech.tritone.music.model.Pitch;
import org.mech.tritone.music.model.instrument.HasStrings;
import org.mech.tritone.music.model.instrument.Instrument;
import org.mech.tritone.music.model.instrument.Range;
import org.mech.tritone.music.model.instrument.Tuning;
import org.mech.tritone.music.utils.PitchUtils;
import org.springframework.beans.factory.InitializingBean;

/**
 * <p>
 * The StringInstrument.
 * </p>
 * <p>
 * Date: 8.2.2012 13:25:38
 * </p>
 * 
 * @author martin.krajc
 */
public class StringedInstrument implements Instrument, HasStrings,
		InitializingBean {

	/** The strings count. */
	private int stringsCount;

	/** The tuning. */
	private Tuning tuning;

	private List<Strings> strings;

	/** The range. */
	private Range range;

	/**
	 * The pitch range. How many pitches can be played on one string
	 **/
	private int pitchRange;

	/** The name. */
	private String name;

	public StringedInstrument() {
		range = new Range();
	}

	public int getStringsCount() {
		return stringsCount;
	}

	public Tuning getTuning() {
		return tuning;
	}

	public void setTuning(final Tuning tuning) {
		this.tuning = tuning;
	}

	public Range getRange() {
		return range;
	}

	public void setRange(final Range range) {
		this.range = range;
	}

	public void setRangeFrom(final String from) {
		range.setFrom(PitchUtils.toPitch(from));
	}

	public void setRangeTo(final String to) {
		range.setTo(PitchUtils.toPitch(to));
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this,
				ToStringStyle.SIMPLE_STYLE);
		builder.append(name);
		builder.append(tuning);
		builder.append(range);
		return builder.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStringsCount(int stringsCount) {
		this.stringsCount = stringsCount;
	}

	public int getPitchRange() {
		return pitchRange;
	}

	public void setPitchRange(int pitchRange) {
		this.pitchRange = pitchRange;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (strings == null && tuning != null) {
			strings = new ArrayList<Strings>();
			for (int i = 0; i < stringsCount; i++) {
				strings.add(new Strings(tuning.get(i), pitchRange, i));
			}
		}
		if (pitchRange > 0 && (range.from() == null || range.to() == null)) {
			range.setFrom(tuning.get(0));
			Pitch pitchTo = tuning.get(tuning.get().size() - 1);
			range.setTo(PitchUtils.aug(pitchTo, pitchRange));
		}

	}

	@Override
	public Strings getStrings(int index) {
		return strings.get(index);
	}

}
