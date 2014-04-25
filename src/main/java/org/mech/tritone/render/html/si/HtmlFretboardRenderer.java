package org.mech.tritone.render.html.si;

import java.io.PrintWriter;

import org.mech.tritone.music.model.Pitch;
import org.mech.tritone.music.model.instrument.finger.FingeredPitch;
import org.mech.tritone.music.model.instrument.string.StringedPitch;
import org.mech.tritone.music.utils.FretboardUtils;
import org.mech.tritone.music.utils.FretboardUtils.FretboardMark;
import org.mech.tritone.render.Format;
import org.mech.tritone.render.context.FretboardContext;
import org.mech.tritone.render.si.FretboardRenderer;
import org.springframework.stereotype.Component;

@Component
public class HtmlFretboardRenderer extends FretboardRenderer {

	private static int TUNING_WIDTH = 25;
	private static int FRET_WIDTH = 50;

	public HtmlFretboardRenderer() {
		super(Format.HTML);
	}

	@Override
	protected void renderStart(final PrintWriter writer, final FretboardContext context) {
		writer.println("<!DOCTYPE HTML>");
		writer.println("<html>");
		createHead(writer, context);
		writer.println("<body>");
		writer.println("<table style=\"border-collapse:collapse;\">");
		writer.println("<caption>" + context.getName() + "</caption>");

	}

	private void createHead(final PrintWriter writer, final FretboardContext context) {
		writer.println("<head>");
		writer.println("<title>Tritone " + context.getName() + "</title>");
		writer.println("<meta name=\"description\" content=\"Description\">");
		writer.println("</head>");

		writer.println("<style>");
		writer.println(".fret {border:1px solid gray;text-align:center;}");
		writer.println(".z_fret {text-align:center;}");
		writer.println(".tuning {text-align:center;width:" + TUNING_WIDTH + "px;}");
		writer.println(".tone {color:white;background-color:rgb(42, 54, 189); padding: 4px;border-radius: 30px;font-size: 11px;}");
		writer.println(".z_tuning {text-align:center;width:" + TUNING_WIDTH + "px;}");
		writer.println("</style>");

	}

	@Override
	protected void renderFret(final PrintWriter writer, final FretboardContext context, final int string, final int fret) {
		writer.println("<td class=\"fret\">&nbsp;</td>");
	}

	@Override
	protected void renderEnd(final PrintWriter writer, final FretboardContext context) {
		writer.println("</table>");
		writer.println("</body>");
		writer.println("</html>");
	}

	@Override
	protected void renderStringStart(final PrintWriter writer, final FretboardContext context, final int string) {
		writer.println("<tr>");
	}

	@Override
	protected void renderStringEnd(final PrintWriter writer, final FretboardContext context, final int string) {
		writer.println("</tr>");
	}

	@Override
	protected void renderObject(final Object renderable, final PrintWriter writer, final FretboardContext context,
			final int str, final int fret) {
		writer.print("<td class=\"fret\" style=\"width:" + getFretWidth(fret) + "px\" >");
		writer.print("<span class=\"tone\" >");
		writer.print(renderObject(renderable));
		writer.print("</span>");
		writer.println("</td>");
	}

	@Override
	protected void renderStringTuning(final PrintWriter writer, final Pitch pitch, final int fret,
			final Object renderable) {
		writer.print("<td class=\"tuning\" >");

		if (renderable != null) {
			writer.print("<span class=\"tone\" >");
		}

		writer.print(pitch.getTone().format());

		if (renderable != null) {
			writer.print("</span>");
		}

		writer.println("</td>");
	}

	@Override
	protected void renderZeroFret(final PrintWriter writer, final FretboardContext context, final int fret) {
		final FretboardMark mark = FretboardUtils.getMark(fret);
		if (fret == 0) {
			writer.print("<td class=\"z_tuning\">");
		} else {
			writer.print("<td class=\"z_fret\" style=\"width:" + getFretWidth(fret) + "px\" >");
		}
		switch (mark) {
		case DDOT:
			writer.print("**");
			break;
		case DOT:
			writer.print("*");
			break;
		default:
			break;
		}
		writer.println("</td>");
	}

	private String renderObject(final Object renderable) {
		if (renderable instanceof StringedPitch) {
			final StringedPitch pitch = (StringedPitch) renderable;
			return pitch.getPitch().getTone().format();
		} if (renderable instanceof FingeredPitch) {
			final FingeredPitch pitch = (FingeredPitch) renderable;
			return pitch.getFinger().ordinal() + 1 + "";
		} else {
			return renderable.toString();
		}
	}

	private int getFretWidth(final int fret) {
		return Math.max(TUNING_WIDTH, FRET_WIDTH - fret);
	}

}
