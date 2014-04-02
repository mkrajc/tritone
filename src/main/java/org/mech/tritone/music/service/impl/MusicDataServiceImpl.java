package org.mech.tritone.music.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mech.tritone.music.model.ChordPattern;
import org.mech.tritone.music.model.Pattern;
import org.mech.tritone.music.model.PatternType;
import org.mech.tritone.music.model.ScalePattern;
import org.mech.tritone.music.model.instrument.string.Tuning;
import org.mech.tritone.music.service.MusicDataService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service("musicDataService")
public class MusicDataServiceImpl implements MusicDataService, ApplicationContextAware {

	private final Map<String, Pattern> patternMap = new HashMap<String, Pattern>();

	private final Map<String, Tuning> tuningMap = new HashMap<String, Tuning>();

	@Override
	public Pattern getPattern(final String patternName) {
		return patternMap.get(patternName);
	}

	@Override
	public Tuning getTuning(final String tuningName) {
		return tuningMap.get(tuningName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setApplicationContext(final ApplicationContext context) throws BeansException {
		final Map<String, Pattern> patternsMap = context.getBeansOfType(Pattern.class);
		final Map<String, Tuning> tMap = context.getBeansOfType(Tuning.class);

		for (final Pattern pattern : patternsMap.values()) {
			patternMap.put(pattern.getKey(), pattern);
		}

		for (final Tuning t : tMap.values()) {
			tuningMap.put(t.getKey(), t);
		}
	}

	@Override
	public List<Pattern> getAllPatterns() {
		return new ArrayList<Pattern>(patternMap.values());
	}

	@Override
	public List<ChordPattern> getAllChordPatterns() {
		final List<ChordPattern> chordPatterns = new ArrayList<ChordPattern>();
		for (final Pattern p : patternMap.values()) {
			if (PatternType.CHORD.equals(p.getType())) {
				chordPatterns.add((ChordPattern) p);
			}
		}

		Collections.sort(chordPatterns, new Comparator<ChordPattern>() {
			@Override
			public int compare(final ChordPattern o1, final ChordPattern o2) {
				return o1.getKey().compareTo(o2.getKey());
			}
		});
		return chordPatterns;
	}

	@Override
	public List<ScalePattern> getAllScalePatterns() {
		final List<ScalePattern> scalePatterns = new ArrayList<ScalePattern>();
		for (final Pattern p : patternMap.values()) {
			if (PatternType.SCALE.equals(p.getType())) {
				scalePatterns.add((ScalePattern) p);
			}
		}
		return scalePatterns;
	}

	@Override
	public List<Tuning> getAllTunings() {
		return new ArrayList<Tuning>(tuningMap.values());
	}

}
