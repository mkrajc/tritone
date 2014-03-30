package org.mech.tritone.main.cmd.show;

import java.io.PrintWriter;
import java.util.List;

import org.mech.tritone.music.model.Tone;
import org.mech.tritone.music.model.TonePattern;
import org.springframework.stereotype.Component;

@Component
public class ShowPatternNotesCommand {

	public void execute(final PrintWriter writer, final TonePattern pattern) {
		List<Tone> tones = pattern.toTones();

		for (final Tone t : tones) {
			writer.print(t.format());
			writer.print(" ");
		}
		writer.println();
		writer.flush();

	}
}
