package org.mech.tritone.music.render.html;

import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mech.tritone.music.context.MusicRenderingContext;
import org.mech.tritone.music.model.instrument.string.StringedPitch;
import org.mech.tritone.music.model.instrument.string.Strings;
import org.mech.tritone.music.model.notation.fretboard.FingeredPitch;
import org.mech.tritone.music.model.notation.fretboard.Fretboard;
import org.mech.tritone.music.utils.PitchUtils;
import org.mech.tritone.music.utils.PitchUtils.FormatingType;
import org.mech.tritone.render.html.AbstractHtmlRenderer;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.googlecode.jatl.Html;

@Component("rendererFretboard")
public class FretboardHtmlRenderer extends
		AbstractHtmlRenderer<MusicRenderingContext> {

	@SuppressWarnings("rawtypes")
	@Override
	public MusicRenderingContext renderHtml(final MusicRenderingContext context) {
		Fretboard fretboard;
		int from, to, root;
		boolean displayStrings;
		List objects;
		String caption;

		fretboard = context.get(MusicRenderingContext.FRETBOARD,
				Fretboard.class);

		if (fretboard == null) {
			throw new IllegalArgumentException(
					"fretboard is not in the context");
		}

		from = context.get(MusicRenderingContext.PRM_FRET_FROM, Integer.class, 0);
		to = context.get(MusicRenderingContext.PRM_FRET_TO, Integer.class, fretboard.getLength());
		displayStrings = context.get(MusicRenderingContext.PRM_FRET_DISPLAY_STRINGS, Boolean.class, true);
		objects = context.get(MusicRenderingContext.FRETBOARD_RENDERS, List.class);
		root = context.get(MusicRenderingContext.PRM_ROOT, Integer.class, -1);
		caption = context.get(MusicRenderingContext.FRETBOARD_CAPTION, String.class);
		

		final Map<Integer, Map<Integer, List>> renders = prepareRenders(objects);

		createFretboardHtml(context.getWriter(), fretboard, from, to,
				displayStrings, renders, root, caption);

		return context;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map<Integer, Map<Integer, List>> prepareRenders(List objects) {
		Map<Integer, Map<Integer, List>> map = new HashMap<Integer, Map<Integer, List>>();

		if (!CollectionUtils.isEmpty(objects)) {
			for (Object object : objects) {
				Integer string = null, fret = null;
				if (StringedPitch.class.isInstance(object)) {
					StringedPitch pitch = (StringedPitch) object;
					string = pitch.getStringIndex();
					fret = pitch.getPosition();
				}

				if (string != null) {
					if (!map.containsKey(string)) {
						map.put(string, new HashMap<Integer, List>());
					}

					if (fret != null) {
						if (!map.get(string).containsKey(fret)) {
							map.get(string).put(fret, new ArrayList());
						}

						map.get(string).get(fret).add(object);
					}
				}
			}
		}

		return map;
	}

	protected void createFretboardHtml(
			Writer writer,
			final Fretboard fretboard,
			final int fromFret,
			final int toFret,
			final boolean displayStrings,
			@SuppressWarnings("rawtypes") final Map<Integer, Map<Integer, List>> renders,
			final int root,
			final String caption) {

		final Html html = new Html(writer);
		html.table().attr("class", "fretTable");
		
		if(caption != null){
			html.caption().attr("class", "fretTableCaption").text(caption).end();
		}
		
		html.tr();
		for (int i = fromFret; i < toFret; i++) {
			html.td().attr("class", "null").div();
			if (i == 3 || i == 5 || i == 7 || i == 10 || i == 12 || i == 15
					|| i == 17 || i == 19) {
				html.text(Integer.toString(i));
			}
			html.end(2);
		}
		html.end();

		for (int x = fretboard.getStringCount() - 1; x >= 0; x--) {
			Strings strng = fretboard.getStrings(x);
					
			html.tr();
			for (int i = fromFret; i < toFret; i++) {
				String clazz = "fret fret_" + i;

				if (i == 3 || i == 5 || i == 7 || i == 10 || i == 12 || i == 15
						|| i == 17 || i == 19) {
					clazz += " fretSpec";
				}

				html.td().attr("class", clazz);

				if (renders.containsKey(x) && renders.get(x).containsKey(i)) {
					for (Object o : renders.get(x).get(i)) {
						if (FingeredPitch.class.isInstance(o)) {
							FingeredPitch pitch = (FingeredPitch) o;
							html.div();
							if (pitch.getPitch().getPitchClass() == root) {
								html.attr("class", "fretroot");
							} else {
								html.attr("class", "fretpitch");
							}
							html.text(Integer.toString(pitch.getFingerIndex()) + " " + 
									PitchUtils.format(pitch.getPitch(), FormatingType.LETTER_US))
									.end();
						} else if (StringedPitch.class.isInstance(o)) {
							StringedPitch pitch = (StringedPitch) o;
							html.div();
							if (pitch.getPitch().getPitchClass() == root) {
								html.attr("class", "fretroot");
							} else {
								html.attr("class", "fretpitch");
							}
							html.text(
									PitchUtils.format(pitch.getPitch(),FormatingType.LETTER_US))
									.end();
						}
					}
				} else if (i == 0 && displayStrings) {
					html.div();
					html.text(
							PitchUtils.format(strng.getRoot(),
									FormatingType.LETTER_US)).end();
				}

				html.end();
			}
		}
		html.end();
		html.done();
	}

	@Override
	public boolean supports(MusicRenderingContext context) {
		return context.get(MusicRenderingContext.FRETBOARD, Fretboard.class) != null;
	}

}
