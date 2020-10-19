package com.domgo.commons.excel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.domgo.commons.util.DG;

/**
 * ExcelUtils-封装easyExcel
 * @author Mr.Domgo
 * @Date 2020-9-16
 * @Since 1.0
 * @param <T>
 */
@SuppressWarnings("rawtypes")
public class ExcelUtils{

	private static final Logger log = LoggerFactory.getLogger(ExcelUtils.class);
	
	/**
	 * readExcel is the same with 
	 * @see com.domgo.commons.util.ExcelUtils#readExcel01
	 * @param fullPathfileName excel绝对路径
	 * @param clazz 解析的实体对象
	 * @param excelListener
	 */
	public static void readExcel(String fullPathfileName, Class<? extends Excel> clazz, ExcelListener<? extends Excel> excelListener) {
		EasyExcel.read(fullPathfileName, clazz, excelListener).sheet().doRead();
	}
	
	/**
	 * readExcel
	 * @param inputStream 文件流
	 * @param clazz 解析的实体对象
	 * @param excelListener
	 */
	public static void readExcel(InputStream inputStream, Class<? extends Excel> clazz, ExcelListener<Excel> excelListener) {
		EasyExcel.read(inputStream, clazz, excelListener).sheet().doRead();
	}
	
	/**
	 * readExcel01 is the same with 
	 * @see com.domgo.commons.util.ExcelUtils#readExcel
	 * @param fullPathfileName excel绝对路径
	 * @param clazz 解析的实体对象
	 */
	public static void readExcel01(String fullPathfileName, Class<? extends Excel> clazz, ExcelListener<? extends Excel> excelListener){
		ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fullPathfileName, clazz, excelListener).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        } finally {
            if (excelReader != null) {
                //Complete the entire read file.Release the cache and close stream
                excelReader.finish();
            }
        }
	}
	
	/**
	 * writeExcel is the same with 
	 * @see com.domgo.commons.util.ExcelUtils#writeExcel01
	 * @param fullPathfileName excel绝对路径
	 * @param sheetName sheet页名称
	 * @param data 需要写入的数据
	 * @param clazz 需要写入的数据的类
	 */
	public static void writeExcel(String fullPathfileName, String sheetName, List<? extends Excel> data, Class<? extends Excel> clazz) {
		sheetName = (sheetName == null ? "sheet": sheetName);
		EasyExcel.write(fullPathfileName, clazz).sheet(sheetName).doWrite(data);
	}
	
	/**
	 * writeExcel01 is the same with 
	 * @see com.domgo.commons.util.ExcelUtils#writeExcel
	 * @param fullPathfileName excel绝对路径
	 * @param sheetName sheet页名称
	 * @param data 需要写入的数据
	 * @param clazz 需要写入的数据的类
	 */
	public static void writeExcel01(String fullPathfileName, String sheetName, List<? extends Excel> data, Class<? extends Excel> clazz) {
		sheetName = (sheetName == null ? "sheet": sheetName);
		ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fullPathfileName, clazz).build();
            WriteSheet writeSheet = EasyExcel.writerSheet(sheetName).build();
            excelWriter.write(data, writeSheet);
        } finally {
            //Complete the entire read file.Release the cache and close stream
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
	}
	
	/**
	 * Inner father class example, Mark Listener, sub class listener extends this
	 * @author Mr.Domgo
	 * @Date 2020-9-16
	 * @Since 1.0
	 * @param <T>
	 */
	@SuppressWarnings({"unchecked", "unused"})
	public static class ExcelListener<T> extends AnalysisEventListener<T>{
		/**
		 * max storage size , real ready to 3000 size
		 */
		private static final int BATCH_COUNT = 100;
		List<T> list = new ArrayList<T>();
		/**
		 * <p>DataDao layer generic, if you use spring framework, pass in through the constructor.</p>
		 */
		private GenericDao genericDao;
		
		public ExcelListener() {
			super();
		}
		public ExcelListener(GenericDao genericDao) {
			this.genericDao = genericDao;
		}

		/**
		 * <p>every data be analysis also call this method</p>
		 * @param data
		 * one row value. Is is same as {@link AnalysisContext#readRowHolder()}
		 */
		@Override
		public void invoke(T data, AnalysisContext context) {
			log.info("解析到一条数据data:{}", DG.json.serialize(data));
			list.add(data);
			if(list.size() >= BATCH_COUNT) {
				//saveData(); delegate subclass implement
				list.clear();
			}
		}

		/**
		 * <p>all Data be analysis completely call this method</p>
		 */
		@Override
		public void doAfterAllAnalysed(AnalysisContext context) {
			//saveData(); delegate subclass implement
			log.info("所有数据解析完成!");
		}
		
		/**
		 * <p>data storage into database</p>
		 */
		private void saveData() {
			log.info("{}条数据开始存储数据库", list.size());
			genericDao.save(list);
		}
		
	}
}