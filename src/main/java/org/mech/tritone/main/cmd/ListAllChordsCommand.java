package org.mech.tritone.main.cmd;

import java.io.PrintWriter;
import java.util.List;

import org.mech.tritone.music.model.ChordPattern;
import org.mech.tritone.music.model.Pattern;
import org.mech.tritone.music.service.MusicDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListAllChordsCommand  {

	@Autowired
	private MusicDataService dataService;

	public void execute() {
		final PrintWriter printWriter = new PrintWriter(System.out);
		printWriter.println("Possible chord names");
		List<ChordPattern> patterns = dataService.getAllChordPatterns();

		for (Pattern pat : patterns) {
			printWriter.println(String.format("'%s' - %s", pat.getKey(),
					pat.getName()));
		}
		printWriter.flush();
	}

}
