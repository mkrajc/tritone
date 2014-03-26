package org.mech.tritone.music.context.impl;

import java.util.HashMap;
import java.util.Map;

import org.mech.tritone.music.context.Context;

public class ContextImpl implements Context {

	private Map<String, Object> map = new HashMap<String, Object>();

	@Override
	public <T> T get(String key, Class<T> clazz) {
		return get(key, clazz, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String key, Class<T> clazz, T defaultValue) {
		return map.get(key) == null ? defaultValue : (T) map.get(key);
	}

	public void put(String key, Object value) {
		map.put(key, value);
	}

}
