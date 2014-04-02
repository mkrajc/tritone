package org.mech.tritone.main.cmd;


public abstract class AbstractCommand implements Command {

	protected void write(final String s){
		CommandLineUtils.getWriter().print(s);
	}
	
	protected void writeln(final String s){
		CommandLineUtils.getWriter().println(s);
	}
}
