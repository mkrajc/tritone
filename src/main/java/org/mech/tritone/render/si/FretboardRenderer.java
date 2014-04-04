package org.mech.tritone.render.si;

import java.io.PrintWriter;

import org.mech.tritone.music.model.Pitch;
import org.mech.tritone.render.AbstractFormatRenderer;
import org.mech.tritone.render.Format;
import org.mech.tritone.render.RenderingContext;
import org.mech.tritone.render.context.FretboardContext;

public abstract class FretboardRenderer extends AbstractFormatRenderer<FretboardContext> {

	public FretboardRenderer(final Format format) {
		super(format);
	}

	@Override
	protected boolean doSupport(final RenderingContext context) {
		return context instanceof FretboardContext;
	}

	@Override
	public void render(final PrintWriter writer, final FretboardContext context) {
		renderStart(writer, context);

		renderZeroString(writer, context);

		for (int str = context.getStringCount() - 1; str >= 0; str--) {

			renderStringStart(writer, context, str);
			for (int fret = context.getFretStart(); fret < context.getFretEnd(); fret++) {
				final Object renderable = context.get(str, fret);

				if (context.isRenderTuning() && fret == context.getFretStart()) {
					renderStringTuning(writer, context.getTuning().get(str), fret, renderable);
				}

				if (fret > 0) {
					if (renderable == null) {
						renderFret(writer, context, str, fret);
					} else {
						renderObject(renderable, writer, context, str, fret);
					}
				}
			}
			renderStringEnd(writer, context, str);
		}
		renderEnd(writer, context);
	}

	private void renderZeroString(final PrintWriter writer, final FretboardContext context) {
		final int string = -1;
		renderStringStart(writer, context, string);
		for (int fret = context.getFretStart(); fret < context.getFretEnd(); fret++) {
			renderZeroFret(writer, context, fret);
		}
		renderStringEnd(writer, context, string);

	}

	protected abstract void renderZeroFret(PrintWriter writer, FretboardContext context, int fret);

	protected abstract void renderObject(Object renderable, PrintWriter writer, FretboardContext context, int str,
			int fret);

	protected abstract void renderStringTuning(PrintWriter writer, Pitch pitch, int fret, Object renderable);

	protected abstract void renderStart(PrintWriter writer, FretboardContext context);

	protected abstract void renderFret(PrintWriter writer, FretboardContext context, int string, int fret);

	protected abstract void renderEnd(PrintWriter writer, FretboardContext context);

	protected abstract void renderStringStart(PrintWriter writer, FretboardContext context, int string);

	protected abstract void renderStringEnd(PrintWriter writer, FretboardContext context, int string);

}
