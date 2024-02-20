/*
 * WPDUserDictionaryDaoImpl.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.spellcheck.dictionary.db;

import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.web.dictionary.WPDRapidSpellChecker;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class WPDUserDictionaryDaoImpl extends JdbcDaoSupport implements WPDUserDictionaryDao {

	private static int ZERO_VALUE = 0;
	
	private static Context ctx = null;
	
	/*public WPDUserDictionaryDaoImpl(){
		
		try{			
			JndiDataSourceLoader loader= new JndiDataSourceLoader();
			loader.bindDataSources();
		}
		catch(NamingException ne){
			ne.printStackTrace();
		}
		getJdbcTemplate().setDataSource( getDataSource() );
	}
	
	*/
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.spellcheck.dictionary.db.WPDUserDictionaryDao#getAllWords()
	 */
	
	
	/*public List getAllWords() {
		String sql = "SELECT WORD FROM USR_DICT";
		WPDUserDictionaryRowMapper wpdUserDictionaryRowMapper = new WPDUserDictionaryRowMapper( getDataSource(),sql );
		List listResult = wpdUserDictionaryRowMapper.execute();
		return listResult;
	}*/

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.spellcheck.dictionary.db.WPDUserDictionaryDao#addWord(java.lang.String)
	 */
	/*public boolean addWord(String word) {
		WPDRapidSpellChecker rapidSpellChecker = new WPDRapidSpellChecker( WebConstants.licensekey ); 
		boolean status = rapidSpellChecker.checkMainDictionary( word );	
		String retrieve ="SELECT  WORD FROM USR_DICT WHERE UPPER(WORD) =UPPER('"+word+"')";			
		WPDUserDictionaryRowMapper wpdUserDictionaryRowMapper = new WPDUserDictionaryRowMapper( getDataSource(),retrieve );
		List listResult = wpdUserDictionaryRowMapper.execute();	
		if(null!=listResult){
			if(listResult.size()==ZERO_VALUE && status==false){
				getJdbcTemplate().update("insert into USR_DICT (WORD) values (?)", new Object[] {word});
				return true;
			}else{
				return false;
			}
		}
		return false;
	}*/
	
	
	public WPDUserDictionaryDaoImpl(){	
	
	}

	/**
	 * Method to get the datasuorce object by looking up with JNDI name.
	 * @return
	 * @throws NamingException
	 */
	private DataSource getDS()throws NamingException
	{
		DataSource dataSource = null;
		//Creates an intial context for looking up the datasource.
		ctx = new InitialContext();
		//Looking up on the database using the JDNI name.
		dataSource = (DataSource) ctx.lookup(WebConstants.jndiName);	
		//Set datasource to JDBCdaoSupport.
		this.setDataSource(dataSource);
		return dataSource;

	}
	/**
	 * 
	 * @throws NamingException
	 */
	private static void closeContext() throws NamingException{
		
		if(null != ctx)
			ctx.close();
	}
	/**
	 * Method to get all the words from theuser dictionary table.
	 */
	public List getAllWords() throws NamingException
	{
		String sql = "SELECT WORD FROM USR_DICT";		
		WPDUserDictionaryRowMapper wpdUserDictionaryRowMapper = new WPDUserDictionaryRowMapper( getDS(),sql );
		List listResult = wpdUserDictionaryRowMapper.execute();
		closeContext();
		return listResult;
	}
	/**
	 * Method to add a new word to the user dictionary.
	 */
	public boolean addWord(String word,User user)throws NamingException{
		String userId = null;
		Date createdDate = null;
		WPDRapidSpellChecker rapidSpellChecker = new WPDRapidSpellChecker( WebConstants.licensekey ); 
		Audit audit = getAudit(user);
		if(null != audit){
			userId = audit.getUser();
			createdDate = audit.getTime();
		}		
		boolean status = rapidSpellChecker.checkMainDictionary( word );	
		String retrieve ="SELECT  WORD FROM USR_DICT WHERE WORD ='"+word+"'";			
		WPDUserDictionaryRowMapper wpdUserDictionaryRowMapper = new WPDUserDictionaryRowMapper( getDS(),retrieve );
		List listResult = wpdUserDictionaryRowMapper.execute();	
		if(null != listResult){
			if(listResult.size() == ZERO_VALUE && !status){
				this.getJdbcTemplate().update("insert into USR_DICT (WORD,CREATD_USER_ID,CREATD_TM_STMP) values (?,?,?)",
						new Object[] {word,userId,createdDate});
				return true;
			}else{
				return false;
			}
		}
		closeContext();
		return false;
	}


	/**
	 * This method is used to build the audit object with user
	 * @param user
	 * @return audit
	 */
	private Audit getAudit(User user){
		Audit audit = null;
		if(null != user){
			AuditFactory auditFactory = (AuditFactory) ObjectFactory
	        	.getFactory(ObjectFactory.AUDIT);
			audit = auditFactory.getAudit(user);
		}
		return audit;
	}
}
