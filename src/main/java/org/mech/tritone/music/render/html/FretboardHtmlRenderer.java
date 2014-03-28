package org.mech.tritone.music.render.html;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mech.tritone.music.context.MusicRenderingContext;
import org.mech.tritone.music.context.impl.MusicRenderingContextImpl;
import org.mech.tritone.music.model.Pitch;
import org.mech.tritone.music.model.Tone;
import org.mech.tritone.music.model.instrument.finger.FingeredPitch;
import org.mech.tritone.music.model.instrument.string.StringedInstrument;
import org.mech.tritone.music.model.instrument.string.StringedPitch;
import org.mech.tritone.render.Format;
import org.mech.tritone.render.RenderingContext;
import org.mech.tritone.render.html.AbstractHtmlRenderer;
import org.springframework.util.CollectionUtils;

import com.googlecode.jatl.Html;

//@Component("rendererFretboard")
public class FretboardHtmlRenderer extends AbstractHtmlRenderer<MusicRenderingContextImpl> {

	@SuppressWarnings("rawtypes")
	@Override
	public MusicRenderingContextImpl renderHtml(final MusicRenderingContextImpl context) {
		int from, to;
		boolean displayStrings;
		List objects;
		String caption;

		final StringedInstrument instrument = context.get(MusicRenderingContext.FRETBOARD, StringedInstrument.class);

		if (instrument == null) {
			throw new IllegalArgumentException("fretboard is not in the context");
		}

		from = context.get(MusicRenderingContext.PRM_FRET_FROM, Integer.class, 0);
		to = context.get(MusicRenderingContext.PRM_FRET_TO, Integer.class, instrument.getLength());
		displayStrings = context.get(MusicRenderingContext.PRM_FRET_DISPLAY_STRINGS, Boolean.class, true);
		objects = context.get(MusicRenderingContext.FRETBOARD_RENDERS, List.class);
		final Tone root = context.get(MusicRenderingContext.PRM_ROOT, Tone.class);
		caption = context.get(MusicRenderingContext.FRETBOARD_CAPTION, String.class);

		final Map<Integer, Map<Integer, List>> renders = prepareRenders(objects);

		createFretboardHtml(null, instrument, from, to, displayStrings, renders, root, caption);

		return context;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map<Integer, Map<Integer, List>> prepareRenders(final List objects) {
		final Map<Integer, Map<Integer, List>> map = new HashMap<Integer, Map<Integer, List>>();

		if (!CollectionUtils.isEmpty(objects)) {
			for (final Object object : objects) {
				Integer string = null, fret = null;
				if (StringedPitch.class.isInstance(object)) {
					final StringedPitch pitch = (StringedPitch) object;
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

	protected void createFretboardHtml(final Writer writer, final StringedInstrument fretboard, final int fromFret, final int toFret,
			final boolean displayStrings, @SuppressWarnings("rawtypes") final Map<Integer, Map<Integer, List>> renders, final Tone root,
			final String caption) {

		final Html html = new Html(writer);
		html.table().attr("class", "fretTable");

		if (caption != null) {
			html.caption().attr("class", "fretTableCaption").text(caption).end();
		}

		html.tr();
		for (int i = fromFret; i < toFret; i++) {
			html.td().attr("class", "null").div();
			if (i == 3 || i == 5 || i == 7 || i == 10 || i == 12 || i == 15 || i == 17 || i == 19) {
				html.text(Integer.toString(i));
			}
			html.end(2);
		}
		html.end();

		for (int x = fretboard.getStringsCount() - 1; x >= 0; x--) {
			final Pitch strng = fretboard.getTuning().get(x);

			html.tr();
			for (int i = fromFret; i < toFret; i++) {
				String clazz = "fret fret_" + i;

				if (i == 3 || i == 5 || i == 7 || i == 10 || i == 12 || i == 15 || i == 17 || i == 19) {
					clazz += " fretSpec";
				}

				html.td().attr("class", clazz);

				if (renders.containsKey(x) && renders.get(x).containsKey(i)) {
					for (final Object o : renders.get(x).get(i)) {
						if (FingeredPitch.class.isInstance(o)) {
							final FingeredPitch pitch = (FingeredPitch) o;
							html.div();
							if (pitch.getPitch().getTone() == root) {
								html.attr("class", "fretroot");
							} else {
								html.attr("class", "fretpitch");
							}
							html.text(Integer.toString(pitch.getFinger().ordinal()) + " " + pitch.getPitch().getTone()).end();
						} else if (StringedPitch.class.isInstance(o)) {
							final StringedPitch pitch = (StringedPitch) o;
							html.div();
							if (pitch.getPitch().getTone() == root) {
								html.attr("class", "fretroot");
							} else {
								html.attr("class", "fretpitch");
							}
							html.text(pitch.getPitch().getTone().format()).end();
						}
					}
				} else if (i == 0 && displayStrings) {
					html.div();
					html.text(strng.getTone().format()).end();
				}

				html.end();
			}
		}
		html.end();
		html.done();
	}

	@Override
	public void render(final PrintWriter writer, final MusicRenderingContextImpl context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Format getSupportedFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(final RenderingContext context, final Format format) {
		// TODO Auto-generated method stub
		return false;
	}


}
