/*
 * Created on Apr 4, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.spellcheck.dictionary.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.object.MappingSqlQuery;

/**
 * @author U13226
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WPDUserDictionaryRowMapper extends MappingSqlQuery {

	private static int COLUMN_WORD = 1;
	
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
