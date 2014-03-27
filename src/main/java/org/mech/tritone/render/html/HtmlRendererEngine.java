package org.mech.tritone.render.html;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.commons.io.IOUtils;
import org.mech.tritone.render.RendererDispatcher;
import org.mech.tritone.render.RenderingContext;
import org.mech.tritone.render.RenderingContextDispatcher;

import com.googlecode.jatl.Html;

public class HtmlRendererEngine {

	private RendererDispatcher dispatcher;

	public void render(final Writer writer, final RenderingContext context) {
		RenderingContext renderHtmlContext = context;

		try {
			// start building html
			final Html html = new Html(context.getWriter());
			html.head();
			html.style().attr("type", "text/css").text(css()).end();
			html.end();
			html.body();
			//
			// render content
			//
			if (RenderingContextDispatcher.class.isInstance(context)) {
				for (RenderingContext mContext : ((RenderingContextDispatcher) context).getContexts()) {
					dispatcher.dispatchRender(mContext);
				}
			} else {
				renderHtmlContext = dispatcher.dispatchRender(context);
			}

			// close all
			html.endAll();
			html.done();

			writer.write(renderHtmlContext.getWriter().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	protected String css() throws IOException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(getClass().getResourceAsStream("/html/tritone.css"), writer, "utf-8");
		return writer.toString();
	}

	public RendererDispatcher getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(final RendererDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}
}
