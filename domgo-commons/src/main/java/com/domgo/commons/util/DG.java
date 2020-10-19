package com.domgo.commons.util;

import com.domgo.commons.excel.ExcelUtils;

/**
 * Tools
 * @author Mr.Domgo
 * @Date 2020-9-4
 * @Since 1.0
 */
public final class DG {

	public static final class string extends StringUtils {}
	
	public static final class date extends DateUtils {}
	
	public static final class json extends JsonSerializeUtils {}
	
	public static final class xml extends XmlParseUtils {}
	
	public static final class excel extends ExcelUtils {}
	
	public static final class file extends FileUtils {}
	
}
