/*
 * WPDUserDictionary.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.dictionary;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.keyoti.rapidSpell.UserDictionary;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.spellcheck.dictionary.db.WPDUserDictionaryDao;
import com.wellpoint.wpd.spellcheck.dictionary.db.WPDUserDictionaryDaoImpl;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class WPDUserDictionary extends UserDictionary {
	
	boolean valid = true;	
	WPDUserDictionaryDao wpdUserDictionaryDao = null;
	
	//Default Constructor.
	public WPDUserDictionary(){
		wpdUserDictionaryDao = new WPDUserDictionaryDaoImpl();
	}
	/**
	 * Method to read all the words from the user dicionary.
	 */
	public int readAll( ArrayList list ){
		if(list == null)
			return 0;
		List wordList =null;
		try {
			wordList = wpdUserDictionaryDao.getAllWords();
			if(wordList != null)
			{
				for(int i=0; i<wordList.size(); i++){
					list.add( wordList.get(i) );
				}
			}
			
		} catch (NamingException e) {
			com.wellpoint.wpd.common.framework.logging.Logger.logError(e);
		}
		return list.size(); 
	}
	
	/**
	 * Method to add a word to the user dictionary.
	 */
	public boolean addWord(String word,User user)
	{		
		try {			
			return wpdUserDictionaryDao.addWord(word,user);
		} catch (NamingException e) {
			com.wellpoint.wpd.common.framework.logging.Logger.logError(e);
		}	
		return false;
	}
	
	public boolean isValid(){
		return valid;
	}
}
