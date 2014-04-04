package org.mech.tritone.render;

import java.io.PrintWriter;


public interface Renderer<T extends RenderingContext> {

	void render(PrintWriter writer, T context);

	boolean supports(RenderingContext context, Format format);
}
