package org.mech.tritone.main.cmd;

import java.io.PrintWriter;
import java.util.List;

import org.mech.tritone.music.model.Pattern;
import org.mech.tritone.music.model.ScalePattern;
import org.mech.tritone.music.service.MusicDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListAllScalesCommand {

	@Autowired
	private MusicDataService dataService;

	public void execute() {
		final PrintWriter printWriter = new PrintWriter(System.out);
		printWriter.println("Possible chord names");
		List<ScalePattern> patterns = dataService.getAllScalePatterns();

		for (Pattern pat : patterns) {
			printWriter.println(String.format("'%s' - %s", pat.getKey(),
					pat.getName()));
		}
		printWriter.flush();
	}

}
