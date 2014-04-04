package org.mech.tritone.music.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.mech.tritone.music.model.Tone;

public enum Notation {
	US, EU;

	private static Map<String, Tone> euToneMap = new HashMap<String, Tone>();
	private static Map<String, Tone> usToneMap = new HashMap<String, Tone>();

	static {
		euToneMap.put("c", Tone.C);
		euToneMap.put("cis", Tone.CS);
		euToneMap.put("ces", Tone.CB);
		euToneMap.put("dis", Tone.DS);
		euToneMap.put("d", Tone.D);
		euToneMap.put("des", Tone.DB);
		euToneMap.put("es", Tone.EB);
		euToneMap.put("e", Tone.E);
		euToneMap.put("eis", Tone.ES);
		euToneMap.put("fis", Tone.FS);
		euToneMap.put("f", Tone.F);
		euToneMap.put("fes", Tone.FB);
		euToneMap.put("gis", Tone.GS);
		euToneMap.put("g", Tone.G);
		euToneMap.put("ges", Tone.GB);
		euToneMap.put("as", Tone.AB);
		euToneMap.put("a", Tone.A);
		euToneMap.put("ais", Tone.AS);
		euToneMap.put("b", Tone.BB);
		euToneMap.put("h", Tone.B);
		euToneMap.put("his", Tone.BS);

		euToneMap.put("cisis", Tone.CSS);
		euToneMap.put("disis", Tone.DSS);
		euToneMap.put("eisis", Tone.ESS);
		euToneMap.put("fisis", Tone.FSS);
		euToneMap.put("gisis", Tone.GSS);
		euToneMap.put("asis", Tone.ASS);
		euToneMap.put("hisis", Tone.BSS);

		euToneMap.put("ceses", Tone.CBB);
		euToneMap.put("deses", Tone.DBB);
		euToneMap.put("eses", Tone.EBB);
		euToneMap.put("feses", Tone.FBB);
		euToneMap.put("geses", Tone.GBB);
		euToneMap.put("ases", Tone.ABB);
		euToneMap.put("bes", Tone.BBB);

		usToneMap.put("cb", Tone.CB);
		usToneMap.put("c", Tone.C);
		usToneMap.put("c#", Tone.CS);
		usToneMap.put("db", Tone.DB);
		usToneMap.put("d", Tone.D);
		usToneMap.put("d#", Tone.DS);
		usToneMap.put("eb", Tone.EB);
		usToneMap.put("e", Tone.E);
		usToneMap.put("e#", Tone.ES);
		usToneMap.put("fb", Tone.FB);
		usToneMap.put("f", Tone.F);
		usToneMap.put("f#", Tone.FS);
		usToneMap.put("gb", Tone.GB);
		usToneMap.put("g", Tone.G);
		usToneMap.put("g#", Tone.GS);
		usToneMap.put("ab", Tone.AB);
		usToneMap.put("a", Tone.A);
		usToneMap.put("a#", Tone.AS);
		usToneMap.put("bb", Tone.BB);
		usToneMap.put("b", Tone.B);
		usToneMap.put("b#", Tone.BS);

		usToneMap.put("c##", Tone.CSS);
		usToneMap.put("d##", Tone.DSS);
		usToneMap.put("e##", Tone.ESS);
		usToneMap.put("f##", Tone.FSS);
		usToneMap.put("g##", Tone.GSS);
		usToneMap.put("a##", Tone.ASS);
		usToneMap.put("b##", Tone.BSS);

		usToneMap.put("cbb", Tone.CBB);
		usToneMap.put("dbb", Tone.DBB);
		usToneMap.put("ebb", Tone.EBB);
		usToneMap.put("fbb", Tone.FBB);
		usToneMap.put("gbb", Tone.GBB);
		usToneMap.put("abb", Tone.ABB);
		usToneMap.put("bbb", Tone.BBB);
	}

	public static Tone getFromString(final String tone, final Notation notation) {
		final Map<String, Tone> map = notation == US ? usToneMap : euToneMap;
		return map.get(tone.toLowerCase());
	}

	public static String toString(final Tone tone, final Notation notation) {
		final Map<String, Tone> map = notation == US ? usToneMap : euToneMap;

		for (final Entry<String, Tone> t : map.entrySet()) {
			if (t.getValue().equals(tone)) {
				return StringUtils.capitalize(t.getKey());
			}
		}

		throw new IllegalArgumentException("Cannot format tone. [" + tone + "] in notation [" + notation + "]");
	}
}
