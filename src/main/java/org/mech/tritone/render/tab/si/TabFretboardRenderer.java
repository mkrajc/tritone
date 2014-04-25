package org.mech.tritone.render.tab.si;

import java.io.PrintWriter;

import org.mech.tritone.music.model.Pitch;
import org.mech.tritone.music.model.instrument.string.StringedPitch;
import org.mech.tritone.music.utils.FretboardUtils;
import org.mech.tritone.music.utils.FretboardUtils.FretboardMark;
import org.mech.tritone.render.Format;
import org.mech.tritone.render.context.FretboardContext;
import org.mech.tritone.render.si.FretboardRenderer;
import org.springframework.stereotype.Component;

@Component
public class TabFretboardRenderer extends FretboardRenderer {

	public TabFretboardRenderer() {
		super(Format.TAB);
	}

	@Override
	protected void renderObject(final Object renderable, final PrintWriter writer, final FretboardContext context,
			final int str, final int fret) {
		writer.print("|-");
		final String r = renderObject(renderable);

		writer.print(r);

		final int x = 4 - r.length();

		for (int i = 0; i < x; i++) {
			writer.print("-");
		}
	}

	private String renderObject(final Object renderable) {
		if (renderable instanceof StringedPitch) {
			final StringedPitch pitch = (StringedPitch) renderable;
			return pitch.getPitch().getTone().format();
		} else {
			return renderable.toString();
		}
	}

	@Override
	protected void renderStart(final PrintWriter writer, final FretboardContext context) {
	}

	@Override
	protected void renderFret(final PrintWriter writer, final FretboardContext context, final int string, final int fret) {
		writer.print("|-----");
	}

	@Override
	protected void renderEnd(final PrintWriter writer, final FretboardContext context) {
	}

	@Override
	protected void renderStringStart(final PrintWriter writer, final FretboardContext context, final int string) {

	}

	@Override
	protected void renderStringEnd(final PrintWriter writer, final FretboardContext context, final int string) {
		if (string < 0) {
			writer.println();
		} else {
			writer.println("|");
		}
	}

	@Override
	protected void renderStringTuning(final PrintWriter writer, final Pitch pitch, final int fret,
			final Object renderable) {
		if (renderable == null) {
			writer.print(" ");
		} else {
			writer.print("<");
		}
		writer.print(pitch.getTone().format());
		if (renderable == null) {
			writer.print(" ");
		} else {
			writer.print(">");
		}
	}

	@Override
	protected void renderZeroFret(final PrintWriter writer, final FretboardContext context, final int fret) {
		if (fret == 0 && context.isRenderTuning()) {
			writer.print("   ");
		} else {
			final FretboardMark mark = FretboardUtils.getMark(fret);
			switch (mark) {
			case NONE:
				writer.print("      ");
				break;
			case DDOT:
				writer.print("  *** ");
				break;
			case DOT:
				writer.print("   *  ");
				break;
			}

		}
	}

}
