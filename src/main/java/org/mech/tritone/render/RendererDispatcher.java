package org.mech.tritone.render;

import java.util.List;

public class RendererDispatcher {

	private List<Renderer<?>> renderers;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RenderingContext dispatchRender(RenderingContext context) {
		for (Renderer renderer : renderers) {
			if (renderer.supports(context)) {
				renderer.render(context);
			}
		}
		return context;
	}

	public List<Renderer<?>> getRenderers() {
		return renderers;
	}

	public void setRenderers(List<Renderer<?>> renderers) {
		this.renderers = renderers;
	}

	


}
