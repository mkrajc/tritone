package org.mech.tritone.music.context;


public interface Context {
	
	<T> T get(String key, Class<T> clazz);
	
	<T> T get(String key, Class<T> clazz , T defaultValue);
	

}
