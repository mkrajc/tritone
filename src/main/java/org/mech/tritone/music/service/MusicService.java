package org.mech.tritone.music.service;

import java.util.List;

import org.mech.tritone.music.model.Tone;
import org.mech.tritone.music.model.TonePattern;

public interface MusicService {
	List<Tone> convertPatternToTones(TonePattern pattern);
}
