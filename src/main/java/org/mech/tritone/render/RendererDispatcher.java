package org.mech.tritone.render;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class RendererDispatcher implements ApplicationContextAware {

	private Collection<Renderer<RenderingContext>> renderers;

	public <C extends RenderingContext> void dispatchRender(final Format format, final PrintWriter w,
			final RenderingContext context) {
		boolean found = false;
		for (final Renderer<RenderingContext> renderer : renderers) {
			if (renderer.supports(context, format)) {
				found = true;
				renderer.render(w, context);
				break;
			}
		}

		if (!found) {
			w.println("No renderere found for context. {context=" + context + ", format=" + format + "}");
		}

		w.flush();
		w.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setApplicationContext(final ApplicationContext context) throws BeansException {
		final Map rendererMap = context.getBeansOfType(Renderer.class);
		this.renderers = rendererMap.values();
		
		if(CollectionUtils.isEmpty(renderers)){
			throw new IllegalArgumentException("no renderers found");
		} else {
			System.out.println("renderers: " + rendererMap.size());
		}
		
		
		
	}

}
