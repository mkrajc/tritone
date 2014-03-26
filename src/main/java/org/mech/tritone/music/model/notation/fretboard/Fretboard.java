package org.mech.tritone.music.model.notation.fretboard;

import org.mech.tritone.music.model.instrument.string.StringedInstrument;
import org.mech.tritone.music.model.instrument.string.Strings;

public class Fretboard {

	private StringedInstrument instrument;

	public StringedInstrument getInstrument() {
		return instrument;
	}

	public void setInstrument(StringedInstrument instrument) {
		this.instrument = instrument;
	}

	public int getLength() {
		return instrument.getPitchRange();
	}


	public int getStringCount() {
		return instrument.getStringsCount();
	}

	public Strings getStrings(int i) {
		return instrument.getStrings(i);
	}

}
