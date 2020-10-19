package com.domgo.commons.example;

import com.alibaba.excel.annotation.ExcelProperty;
import com.domgo.commons.excel.Excel;

public class DemoData extends Excel{

	@ExcelProperty("第一列")
	private String colone;
	@ExcelProperty("第二列")
	private String coltwo;
	public String getColone() {
		return colone;
	}
	public void setColone(String colone) {
		this.colone = colone;
	}
	public String getColtwo() {
		return coltwo;
	}
	public void setColtwo(String coltwo) {
		this.coltwo = coltwo;
	}
	@Override
	public String toString() {
		return "DemoData [colone=" + colone + ", coltwo=" + coltwo + "]";
	}
	
}
