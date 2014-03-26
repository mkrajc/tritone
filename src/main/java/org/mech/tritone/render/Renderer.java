package org.mech.tritone.render;

public interface Renderer<T extends RenderingContext> {

	T render(T context);
	
	boolean supports(T context);
}
