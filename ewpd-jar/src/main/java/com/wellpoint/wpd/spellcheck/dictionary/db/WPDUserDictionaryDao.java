/*
 * WPDUserDictionaryDao.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.spellcheck.dictionary.db;

import java.util.List;

import javax.naming.NamingException;

import com.wellpoint.wpd.common.framework.security.domain.User;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public interface WPDUserDictionaryDao {
	public List getAllWords()throws NamingException;
	public boolean addWord( String word,User user)throws NamingException;
}
