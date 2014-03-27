package org.mech.tritone.main.cmd;

import java.io.PrintWriter;

import org.mech.tritone.music.model.Tone;
import org.springframework.stereotype.Component;

@Component
public class ListAllTonesCommand {

	public void execute() {
		final PrintWriter printWriter = new PrintWriter(System.out);
		printWriter.println("Possible tone names");

		for (Tone t : Tone.values()) {
			printWriter.print(t.format() + " ");
		}
		printWriter.println();
		printWriter.flush();
	}
}
