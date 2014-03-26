package org.mech.tritone.render.html;

import org.mech.tritone.render.RenderingContext;

public abstract class AbstractHtmlRenderer<T extends RenderingContext>
		implements HtmlRenderer<T> {

	public T render(T context) {
		return renderHtml(context);
	};

	public abstract T renderHtml(T context);

	

}
