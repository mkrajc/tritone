package org.mech.tritone.main;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.mech.tritone.main.arg.Arguments;
import org.mech.tritone.main.arg.CliOptions;
import org.mech.tritone.music.context.ContextPrepare;
import org.mech.tritone.music.context.impl.ContextImpl;
import org.mech.tritone.music.model.ChordPattern;
import org.mech.tritone.music.model.Pattern;
import org.mech.tritone.music.model.Pitch;
import org.mech.tritone.music.model.ScalePattern;
import org.mech.tritone.music.model.instrument.Instrument;
import org.mech.tritone.music.model.instrument.Tuning;
import org.mech.tritone.music.model.instrument.string.StringedInstrument;
import org.mech.tritone.music.model.notation.fretboard.FingeredPitch;
import org.mech.tritone.music.model.notation.fretboard.Fretboard;
import org.mech.tritone.music.service.FretboardService;
import org.mech.tritone.music.service.MusicDataService;
import org.mech.tritone.music.utils.PitchUtils;
import org.mech.tritone.music.utils.PitchUtils.FormatingType;
import org.mech.tritone.render.html.HtmlRendererEngine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	private final static PrintWriter P_WRITER = new PrintWriter(System.out);
	
	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/context.xml");
		
		final MusicDataService dataService = (MusicDataService) applicationContext.getBean("musicDataService");
//		final FretboardService fretboardService = (FretboardService) applicationContext.getBean("fretboardService");
		final HtmlRendererEngine htmlRendererEngine = (HtmlRendererEngine) applicationContext.getBean("renderingEngine");
		final ContextPrepare contextPrepare = (ContextPrepare) applicationContext.getBean("chordContextPrepare");
		
		CliOptions options = new CliOptions();
		
		CommandLineParser parser = new GnuParser();
	    try {
	        // parse the command line arguments
	        CommandLine line = parser.parse( options, args );
	        
	        if(line.getOptions().length == 0){
	        	help(options);
	        }
	        
		    if( line.hasOption(Arguments.HELP) ) {
		    	help(options);
		    }
		    
		    if(line.hasOption(Arguments.LIST_CHORDS)){
		    	P_WRITER.println("Possible chord names");
		    	List<ChordPattern> patterns = dataService.getAllChordPatterns();
		    	
		    	for(Pattern pat : patterns){
		    		P_WRITER.println(String.format("'%s' - %s", pat.getKey(), pat.getName()));
		    	}
		    	P_WRITER.flush();
		    	System.exit(0);;
		    }
		    
		    if(line.hasOption(Arguments.LIST_SCALE)){
		    	P_WRITER.println("Possible scale names");
		    	List<ScalePattern> patterns = dataService.getAllScalePatterns();
		    	
		    	for(Pattern pat : patterns){
		    		P_WRITER.println(String.format("'%s' - %s", pat.getKey(), pat.getName()));
		    	}
		    	P_WRITER.flush();
		    	System.exit(0);;
		    }
		    
		    
		    if(line.hasOption(Arguments.LIST_TUNING)){
		    	P_WRITER.println("Possible tunings names");
		    	
		    	List<Tuning> tunnings = dataService.getAllTunings();
		    	
		    	for(Tuning t : tunnings){
					P_WRITER.println(String.format(
							"'%s' - %s",
							t.getKey(),
							t.getName()
									+ " ("
									+ PitchUtils.format(
											t.get().toArray(new Pitch[] {}),
											FormatingType.LETTER_US) + ")"));
		    	}
		    	P_WRITER.flush();
		    	System.exit(0);;
		    }
		    
		    if(line.hasOption(Arguments.EXPORT_PATERN_HTML)){
		    	String path = line.getOptionValue(Arguments.HTML_PATH,"./index.html");
		    	Tuning tuning = null;
		    	Pattern pattern = null;
		    	Pitch pitch = null;
		    	boolean valid = true;
		    	
		    	if(!line.hasOption(Arguments.TUNING)){
		    		System.err.println(String.format("Tuning argument '-%s' is not present in arguments. Please select one, for all tunings use -%s", 
		    				Arguments.TUNING,
		    				Arguments.LIST_TUNING));
		    		valid = false;
		    	}else if ((tuning = dataService.getTuning(line.getOptionValue(Arguments.TUNING))) == null){
		    		System.err
					.println(String
							.format("Tuning '%s' is not valid. Please select one, for all tunings use -%s",
									line.getOptionValue(Arguments.TUNING),
									Arguments.TUNING));
		    		valid = false;
		    	}
		    	if(!line.hasOption(Arguments.PATTERN)){
					System.err
							.println(String
									.format("Pattern argument '-%s' is not present in arguments. Please select one, for all chords use -%s and for all scales -%s ",
											Arguments.PATTERN,
											Arguments.LIST_CHORDS,
											Arguments.LIST_SCALE));
					valid = false;
		    	}else if((pattern = dataService.getPattern(line.getOptionValue(Arguments.PATTERN))) == null){
		    		System.err
					.println(String
							.format("Pattern '%s' is not valid. Please select one, for all chords use -%s and for all scales -%s",
									line.getOptionValue(Arguments.PATTERN),
									Arguments.LIST_CHORDS,
									Arguments.LIST_SCALE));
		    		valid = false;
		    	}
				if (!line.hasOption(Arguments.TONE)) {
					System.err.println(String.format(
							"Tone argument '-%s' is not present in arguments.",
							Arguments.TONE));
					valid = false;
				} else {
					try {
						pitch = PitchUtils.toPitch(line
								.getOptionValue(Arguments.TONE));
					} catch (Exception e) {
						System.err.println(String.format(
								"Tone '%s' is not valid.",
								line.getOptionValue(Arguments.TONE)));
						valid = false;
					}

				}
		    	
		    	if(!valid){
		    		System.exit(0);
		    	}
		    	
		    	System.out.println("exporting to html: " + path);
		    	if(!path.startsWith(".")){
		    		path = ".\\" + path;
		    	}
		    	
		    	ContextImpl argums = new ContextImpl();
				argums.put(ContextPrepare.PITCH_CLASS, pitch.getPitchClass());
				argums.put(ContextPrepare.CHORD_PATTERN_NAME, pattern.getKey());
				argums.put(ContextPrepare.TUNING_NAME, tuning.getKey());
				argums.put(ContextPrepare.HTML_PATH, path );
				
				htmlRendererEngine.render(contextPrepare.prepare(argums));
				
				System.out.println("done");
		    	return;
		    }
		    
	    }
	    catch( ParseException exp ) {
	        // oops, something went wrong
	        System.err.println( "Parsing failed.  Reason: " + exp.getMessage());
	    }
	    
	

	}
	
	private static void help(Options options){
		HelpFormatter formatter = new HelpFormatter();
    	formatter.printHelp("tritone", options);
    	System.exit(0);
	}
}
