package com.domgo.commons.example;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.excel.context.AnalysisContext;
import com.domgo.commons.excel.ExcelUtils;
import com.domgo.commons.excel.ExcelUtils.ExcelListener;
import com.domgo.commons.util.DG;

/**
 * Demo Listener
 * @see com.domgo.commons.excel.ExcelUtils.ExcelListener
 * @author Mr.Domgo
 * @Date 2020-9-16
 * @Since 1.0
 * @param <T>
 */
public class DemoListener<T> extends ExcelListener<T>{

	private static final Logger log = LoggerFactory.getLogger(ExcelUtils.class);
	List<T> list = new ArrayList<T>();
	public DemoListener() {
		super();
	}

	/**
	 * <p>every data be analysis also call this method</p>
	 * @param data
	 * one row value. Is is same as {@link AnalysisContext#readRowHolder()}
	 */
	@Override
	public void invoke(T data, AnalysisContext context) {
		log.info("具体问题具体分析，解析到一条数据data:{}", DG.json.serialize(data));
		list.add(data);
	}

	/**
	 * <p>all Data be analysis completely call this method</p>
	 */
	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		log.info("具体问题具体分析，所有数据解析完成!");
		System.err.println(list);
	}
	
	
}
