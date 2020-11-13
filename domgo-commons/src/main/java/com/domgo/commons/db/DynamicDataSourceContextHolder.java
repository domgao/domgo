package com.domgo.commons.db;

import com.domgo.commons.enums.DataBaseMode;

public class DynamicDataSourceContextHolder {

	private static final ThreadLocal<DataBaseMode> contextHolder = new ThreadLocal<DataBaseMode>();
	
	public static void putDataSourceKey(DataBaseMode key) {
		if(key == null) throw new NullPointerException();
		contextHolder.set(key);
	}
	
	public static DataBaseMode getDataSourceKey() {
		return contextHolder.get();
	}
	
	public static void markWriter() {
		putDataSourceKey(DataBaseMode.WRITE);
	}
	
	public static void markRead() {
		putDataSourceKey(DataBaseMode.READ);
	}
	
	public static void removeMode() {
		contextHolder.remove();
	}
	
}
