package org.mech.tritone.music.render.html;

import org.mech.tritone.music.context.MusicRenderingContext;
import org.mech.tritone.render.html.AbstractHtmlRenderer;
import org.springframework.stereotype.Component;

import com.googlecode.jatl.Html;

@Component("rendererHeader")
public class HeaderRenderer extends AbstractHtmlRenderer<MusicRenderingContext> {

	@Override
	public MusicRenderingContext renderHtml(MusicRenderingContext context) {
		final String text = context.get(MusicRenderingContext.TEXT,
				String.class);

		final Html html = new Html(context.getWriter());

		if (text != null) {
			final String clazzname = context.get(MusicRenderingContext.TEXT_CLASSNAME, String.class);
			final String type = context.get(MusicRenderingContext.TEXT_TYPE, String.class);
			if (type == null) {
				html.h1();
			} else if (type.equals("div")){
				html.div();
			}
			
			if(clazzname!=null){
				html.attr("class", clazzname);
			}
			
			html.text(text).end();
			html.done();
		}

		return context;
	}

	@Override
	public boolean supports(MusicRenderingContext context) {
		return context.get(MusicRenderingContext.TEXT, String.class) != null;
	}

}
