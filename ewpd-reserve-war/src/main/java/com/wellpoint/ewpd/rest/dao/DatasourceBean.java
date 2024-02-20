package com.wellpoint.ewpd.rest.dao;

import javax.sql.DataSource;

public class DatasourceBean {
	
	
	private DataSource  dataSource;

	/**
	 * @return the dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	

}
