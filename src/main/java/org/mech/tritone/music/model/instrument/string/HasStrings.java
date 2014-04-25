package org.mech.tritone.music.model.instrument.string;

import org.mech.tritone.music.model.Pitch;

public interface HasStrings extends HasTuning {
	int getStringsCount();

	Pitch getNaturalStringPitch(int stringIndex);
	
	String getString(int index);

}
