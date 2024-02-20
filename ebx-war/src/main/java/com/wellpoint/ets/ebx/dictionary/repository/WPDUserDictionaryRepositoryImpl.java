package com.wellpoint.ets.ebx.dictionary.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.object.MappingSqlQuery;

import com.wellpoint.ets.ebx.dictionary.util.EWPDContextUtil;

public class WPDUserDictionaryRepositoryImpl implements
		WPDUserDictionaryRepository, ApplicationContextAware {
	
	private DataSource dataSource;
	
	/**
	 * 
	 * @return dataSource
	 */	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	/**
	 * 
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List getAllWords() {
		
		String sql = "SELECT WORD FROM USR_DICT";		
		WPDUserDictionaryRowMapper wpdUserDictionaryRowMapper = new WPDUserDictionaryRowMapper( dataSource ,sql );
		List listResult = wpdUserDictionaryRowMapper.execute();
		return listResult;
	}
	
	private class WPDUserDictionaryRowMapper extends MappingSqlQuery {

		private int COLUMN_WORD = 1;
		
		public WPDUserDictionaryRowMapper( DataSource dataSource,String sql ){
			super( dataSource,sql );
			
			
		}
		/**
		 * Method to retrieve the word from the result set.
		 */
		protected Object mapRow(ResultSet resultSet, int argument) throws SQLException {
			String word = null;
			word = resultSet.getString( COLUMN_WORD );
			return word;
		}
		
	}	
	
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		EWPDContextUtil.setContext(context);		
	}

}
