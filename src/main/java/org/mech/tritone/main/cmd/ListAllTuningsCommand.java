package org.mech.tritone.main.cmd;

import java.io.PrintWriter;
import java.util.List;

import org.mech.tritone.music.model.Pitch;
import org.mech.tritone.music.model.instrument.Tuning;
import org.mech.tritone.music.service.MusicDataService;
import org.mech.tritone.music.utils.PitchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListAllTuningsCommand {

	@Autowired
	private MusicDataService dataService;

	public void execute() {
		final PrintWriter printWriter = new PrintWriter(System.out);
		printWriter.println("Possible tunings names");

		List<Tuning> tunnings = dataService.getAllTunings();

		for (Tuning t : tunnings) {
			printWriter.println(String.format("'%s' - %s", t.getKey(),
					t.getName() + " (" + PitchUtils.format(t.get().toArray(new Pitch[] {})) + ")"));
		}
		printWriter.flush();
	}
}
