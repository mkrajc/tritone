package org.mech.tritone.music.utils;

public class StringUtils {
	
	public static String toString(final Object[] objs){
		final StringBuilder builder = new StringBuilder();
		
		if(objs == null){
			return "";
		}
		
		final int last = objs.length - 1;
		for(int i = 0;i < last;i++){
			builder.append(objs[i].toString());
			builder.append(',');
		}
		
		builder.append(objs[last].toString());
		
		return builder.toString();
	}

}
