package org.mech.tritone.main.cmd;

import java.util.List;

import org.mech.tritone.music.model.Pattern;
import org.mech.tritone.music.model.Tone;
import org.mech.tritone.music.model.TonePattern;
import org.mech.tritone.music.service.MusicDataService;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class AbstractCommand implements Command {
	
	@Autowired
	protected MusicDataService musicService;

	protected void write(final String s){
		CommandLineUtils.getWriter().print(s);
	}
	
	protected void writeln(final String s){
		CommandLineUtils.getWriter().println(s);
	}
	
	protected Tone getTone(final String toneString) {
		try {
			return Tone.fromString(toneString);
		} catch (final IllegalArgumentException argumentException) {
			throw new CommandException("Unknown tone '" + toneString + "'");
		}
	}

	protected Pattern getPattern(final String pttrn) {
		// must be pattern
		final Pattern pattern = musicService.getPattern(pttrn);

		if (pattern == null) {
			throw new CommandException(String.format("Pattern '%s' is unknown", pttrn));
		}

		return pattern;
	}
	
	protected TonePattern prepareTonePattern(final List<String> argList){
		if (argList.size() == 2) {
			final String toneString = argList.get(0);
			final Tone tone = getTone(toneString);
			
			final String pttrn = argList.get(1);
			final Pattern pattern = getPattern(pttrn);
			
			return new TonePattern(pattern, tone);
		} else {
			throw new CommandException("Tone pattern needs exactly 2 arguments");
		}
	}
	
}
