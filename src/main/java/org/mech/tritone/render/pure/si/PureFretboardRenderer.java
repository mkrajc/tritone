package org.mech.tritone.render.pure.si;

import java.io.PrintWriter;

import org.mech.tritone.music.model.Pitch;
import org.mech.tritone.render.Format;
import org.mech.tritone.render.context.FretboardContext;
import org.mech.tritone.render.si.FretboardRenderer;
import org.springframework.stereotype.Component;

@Component
public class PureFretboardRenderer extends FretboardRenderer {

	public PureFretboardRenderer() {
		super(Format.PURE);
	}

	@Override
	protected void renderStart(final PrintWriter writer, final FretboardContext context) {
	}

	@Override
	protected void renderFret(final PrintWriter writer, final FretboardContext context, final int string, final int fret) {
	}

	@Override
	protected void renderEnd(final PrintWriter writer, final FretboardContext context) {
	}

	@Override
	protected void renderStringStart(final PrintWriter writer, final FretboardContext context, final int string) {
	}

	@Override
	protected void renderStringEnd(final PrintWriter writer, final FretboardContext context, final int string) {

	}
	
	@Override
	protected void renderObject(final Object renderable, final PrintWriter writer, final FretboardContext context, final int str, final int fret) {
		writer.print(renderable);
		writer.print(" ");
	}


	@Override
	protected void renderStringTuning(final PrintWriter writer, final Pitch pitch, final int fret, final Object renderable) {
		
	}

	@Override
	protected void renderZeroFret(final PrintWriter writer, final FretboardContext context, final int fret) {
		
	}

}
