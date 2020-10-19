package com.domgo.commons.util;

import com.domgo.commons.exception.ProcessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlParseUtils {

	private static final ObjectMapper xmlMapper = new XmlMapper();
	
	static {
	}
	
	public static String parseObjectToXml(Object originalObject) {
		try {
			if(originalObject == null) {
				return null;
			}
			return xmlMapper.writeValueAsString(originalObject);
		} catch (Exception e) {
			throw new ProcessException(String.format("对象%s解析为Xml失败", new Object[] {originalObject.getClass().getName()}), e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T reParseObjectToXml(String xmlStr, Class<T> clazz) {
		try {
			if(xmlStr == null) {
				return null;
			}
			if(clazz == null) {
				return (T) xmlStr;
			}
			return xmlMapper.readValue(xmlStr, clazz);
		} catch (Exception e) {
			throw new ProcessException(String.format("字符串[%.50s]反序列化失败", new Object[] {xmlStr}), e);
		}
	}
	
}
