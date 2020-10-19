package com.domgo.commons.util;

import com.domgo.commons.exception.ProcessException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializeUtils {

	private static ObjectMapper mapper = new ObjectMapper();
	
	static {
		
	}
	
	public static String serialize(Object originalObject) {
		try {
			if(originalObject == null) {
				return null;
			}
			if(originalObject instanceof String) {
				return (String) originalObject;
			}
			return mapper.writeValueAsString(originalObject);
		} catch (Exception e) {
			throw new ProcessException(String.format("对象%s序列化失败", new Object[] {originalObject.getClass().getName()}), e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T reSerialize(String jsonStr, Class<T> clazz) {
		try {
			if(jsonStr == null) {
				return null;
			}
			if(clazz == String.class) {
				return (T) jsonStr;
			}
			return mapper.readValue(jsonStr, clazz);
		} catch (Exception e) {
			throw new ProcessException(String.format("字符串[%.15s]反序列化失败", new Object[] {jsonStr}), e);
		}
	}
	
}
