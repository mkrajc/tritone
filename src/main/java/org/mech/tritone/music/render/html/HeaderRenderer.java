package org.mech.tritone.music.render.html;

import java.io.PrintWriter;

import org.mech.tritone.music.context.MusicRenderingContext;
import org.mech.tritone.music.context.impl.MusicRenderingContextImpl;
import org.mech.tritone.render.Format;
import org.mech.tritone.render.RenderingContext;
import org.mech.tritone.render.html.AbstractHtmlRenderer;

public class HeaderRenderer extends AbstractHtmlRenderer<MusicRenderingContextImpl> {

	@Override
	public MusicRenderingContextImpl renderHtml(final MusicRenderingContextImpl context) {
		final String text = context.get(MusicRenderingContext.TEXT,
				String.class);

//		final Html html = new Html(context.getWriter());

		/** if (text != null) {
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
		} **/

		return context;
	}

	@Override
	public void render(final PrintWriter writer, final MusicRenderingContextImpl context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean supports(final RenderingContext context, final Format format) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public boolean supports(final RenderingContext context) {
//		return context.get(MusicRenderingContext.TEXT, String.class) != null;
//	}

}
