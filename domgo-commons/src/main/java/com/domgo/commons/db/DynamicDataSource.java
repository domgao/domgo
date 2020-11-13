package com.domgo.commons.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.domgo.commons.enums.DataBaseMode;

public class DynamicDataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		DataBaseMode dataSource = DynamicDataSourceContextHolder.getDataSourceKey();
		DynamicDataSourceContextHolder.removeMode();
		return dataSource;
	}

}
