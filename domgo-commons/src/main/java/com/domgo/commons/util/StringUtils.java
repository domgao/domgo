package com.domgo.commons.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StringUtils
 * @author Mr.Domgo
 * @Date 2020-9-22
 * @Since 1.0
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils{
	
	/**
	 * \t \r \n字符
	 */
	public static final String SPECIAL_CHAR = "\\s*|\t|\r|\n";
	
	/**
	 * Special Char regex expression
	 */
	public static Pattern pattern = Pattern.compile(SPECIAL_CHAR);
	
	public static String replaceSpecialChar(String str) {
		if(str != null) {
			Matcher matcher = pattern.matcher(str);
			return matcher.replaceAll(EMPTY);
		}
		return EMPTY;
	}
	
}
