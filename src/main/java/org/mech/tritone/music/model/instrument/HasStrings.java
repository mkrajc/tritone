package org.mech.tritone.music.model.instrument;

import org.mech.tritone.music.model.instrument.string.Strings;

public interface HasStrings extends HasTuning {
	int getStringsCount();
	Strings getStrings(int index);
	
	
}
