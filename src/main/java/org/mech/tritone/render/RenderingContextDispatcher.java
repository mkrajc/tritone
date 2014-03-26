package org.mech.tritone.render;

import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.mech.tritone.render.RenderingContext;
import org.mech.tritone.render.RenderingContextImpl;

public class RenderingContextDispatcher extends RenderingContextImpl implements RenderingContext {

	private List<RenderingContext> contexts;
	
	private Writer writer;

	public List<RenderingContext> getContexts() {
		return contexts;
	}

	public void setContexts(List<RenderingContext> contexts) {
		this.contexts = contexts;
		flash();
	}

	public void insertContext(RenderingContext ... contexts) {
		setContexts(Arrays.asList(contexts));
	}

	@Override
	public Writer getWriter() {
		return writer;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
		flash();
	}
	
	private void flash(){
		for (RenderingContext rendContext : contexts) {
			((RenderingContextImpl)rendContext).setWriter(getWriter());
		}
	}
	
	public void append(RenderingContext context){
		if(contexts == null){
			contexts = new ArrayList<RenderingContext>();
		}
		contexts.add(context);
	}
	
	
	

}
