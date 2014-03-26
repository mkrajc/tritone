package org.mech.tritone.render;

import java.io.Writer;

import org.mech.tritone.music.context.Context;

public interface RenderingContext extends Context {
	
	Writer getWriter();

}
