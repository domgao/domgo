package com.domgo.commons.example;

import java.util.ArrayList;
import java.util.List;

import com.domgo.commons.excel.ExcelUtils;

public class EasyExcelTest {

	public static void main(String[] args) {
		List<DemoData> list = new ArrayList<DemoData>();
		for (int i = 0 ; i < 100; i++) {
			DemoData data = new DemoData();
			data.setColone("colOne" + i);
			data.setColtwo("colTwo" + i);
			list.add(data);
		}
		String fullPathfileName = "C:\\Users\\domgao\\Desktop\\testexcel.xls";
		String fullPathfileName01 = "C:\\Users\\domgao\\Desktop\\testexcel01.xls";
		String fullPathfileName02 = "C:\\Users\\domgao\\Desktop\\testexcel02.xls";
		ExcelUtils.readExcel(fullPathfileName, DemoData.class, new DemoListener<DemoData>());
		ExcelUtils.readExcel01(fullPathfileName, DemoData.class, new DemoListener<DemoData>());
		ExcelUtils.writeExcel(fullPathfileName01, null, list, DemoData.class);
		ExcelUtils.writeExcel01(fullPathfileName02, null, list, DemoData.class);
	}
}