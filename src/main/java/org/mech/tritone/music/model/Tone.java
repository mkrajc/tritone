package org.mech.tritone.music.model;

import java.util.ArrayList;
import java.util.List;

import org.mech.tritone.main.cmd.CommandLineUtils;
import org.mech.tritone.music.utils.Notation;

/**
 * Tone enumaration, has collection of all tones in octave, distinguishing
 * between enharmonic ones. So Cb and B are different, although it has same tone
 * class but different tone group. Included double sharps and double flats.
 * 
 **/
public enum Tone {
	CBB(10, ToneGroup.C), CB(11, ToneGroup.C), C(0, ToneGroup.C), CS(1, ToneGroup.C), CSS(2, ToneGroup.C), DBB(0,
			ToneGroup.D), DB(1, ToneGroup.D), D(2, ToneGroup.D), DS(3, ToneGroup.D), DSS(4, ToneGroup.D), EBB(2,
			ToneGroup.E), EB(3, ToneGroup.E), E(4, ToneGroup.E), ES(5, ToneGroup.E), ESS(6, ToneGroup.E), FBB(3,
			ToneGroup.F), FB(4, ToneGroup.F), F(5, ToneGroup.F), FS(6, ToneGroup.F), FSS(7, ToneGroup.F), GBB(5,
			ToneGroup.G), GB(6, ToneGroup.G), G(7, ToneGroup.G), GS(8, ToneGroup.G), GSS(9, ToneGroup.G), ABB(7,
			ToneGroup.A), AB(8, ToneGroup.A), A(9, ToneGroup.A), AS(10, ToneGroup.A), ASS(11, ToneGroup.A), BBB(9,
			ToneGroup.B), BB(10, ToneGroup.B), B(11, ToneGroup.B), BS(0, ToneGroup.B), BSS(1, ToneGroup.B);

	// tone class Db and Cs has same tone class;
	private int tClass;

	// group represent same tone group C, C#, Cb has same C group 0
	private ToneGroup group;

	private Tone(final int tClass, final ToneGroup group) {
		this.tClass = tClass;
		this.group = group;
	}

	public int getToneClass() {
		return tClass;
	}

	public ToneGroup getToneGroup() {
		return group;
	}

	public String format() {
		return format(CommandLineUtils.getNotation());
	}

	public String format(final Notation notation) {
		return Notation.toString(this, notation);
	}

	public static Tone fromString(final String toneString, final Notation notation) {
		final Tone t = Notation.getFromString(toneString, notation);
		
		if(t == null){
			throw new IllegalArgumentException("Illegal string to get tone. [" + toneString + "]");
		}
		
		return t;
	}

	public static Tone fromString(final String toneString) {
		return fromString(toneString, CommandLineUtils.getNotation());
	}
	
	public Tone add(final int offset) {
		final int newTClass = (tClass + offset) % 12;
		return Tone.fromToneClass(newTClass);
	}

	public Tone applyInterval(final Interval interval) {
		final int newToneClass = (tClass + interval.getDistance()) % 12;
		final int newGroupIndex = (group.ordinal() + interval.getIntervalType().ordinal()) % ToneGroup.values().length;
		final ToneGroup newGroup = ToneGroup.values()[newGroupIndex];

		return Tone.fromToneClass(newToneClass, newGroup);
	}

	public List<Tone> applyDistance(final int distance) {
		final List<Tone> list = new ArrayList<Tone>();
		final int toneClass = (tClass + distance) % 12;
		for (final Tone t : values()) {
			if (t.getToneClass() == toneClass) {
				list.add(t);
			}
		}
		return list;
	}

	public static Tone fromToneClass(final int toneClass) {
		for (final Tone t : values()) {
			if (t.getToneClass() == toneClass) {
				return t;
			}
		}
		throw new IllegalArgumentException("Illegal tone class " + toneClass);
	}

	public static Tone fromToneClass(final int toneClass, final ToneGroup group) {
		for (final Tone t : values()) {
			if (t.getToneClass() == toneClass && t.getToneGroup() == group) {
				return t;
			}
		}

		return fromToneClass(toneClass);
	}
	
	public static String toString(final Tone[] tones) {
		final StringBuilder builder = new StringBuilder();

		if (tones == null) {
			return "";
		}

		final int last = tones.length - 1;
		for (int i = 0; i < last; i++) {
			builder.append(tones[i].format());
			builder.append(',');
		}

		builder.append(tones[last].format());

		return builder.toString();
	}

}
