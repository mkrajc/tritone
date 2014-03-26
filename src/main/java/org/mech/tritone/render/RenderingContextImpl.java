package org.mech.tritone.render;

import java.io.Writer;

import org.mech.tritone.music.context.impl.ContextImpl;

/**
 * <p>
 * The RenderingContextImpl.
 * </p>
 * <p>
 * Date: 14.2.2012 13:29:38
 * </p>
 * 
 * @author martin.krajc
 */
public class RenderingContextImpl extends ContextImpl implements
		RenderingContext {

	private Writer writer;

	public Writer getWriter() {
		return writer;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}

}
