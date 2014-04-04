package org.mech.tritone.render;


public abstract class AbstractFormatRenderer<T extends RenderingContext> implements Renderer<T> {

	private final Format format;

	public AbstractFormatRenderer(final Format format) {
		this.format = format;
	}

	public Format getSupportedFormat() {
		return format;
	}

	@Override
	public boolean supports(final RenderingContext context, final Format format) {
		return getSupportedFormat().equals(format) && doSupport(context);
	}

	protected abstract boolean doSupport(RenderingContext context);

}
