/*
 * WPDUserDictionary.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.ets.ebx.dictionary.util;

import java.util.ArrayList;
import java.util.List;


import com.keyoti.rapidSpell.UserDictionary;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.ebx.dictionary.repository.WPDUserDictionaryRepository;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class WPDUserDictionary extends UserDictionary {
	
	boolean valid = true;	
	
	//Default Constructor.
	public WPDUserDictionary(){
	}
	/**
	 * Method to read all the words from the user dicionary.
	 */
	public int readAll( ArrayList list ){
		
		if(list == null)
			return 0;
		List wordList =null;
		try {
			
			
			WPDUserDictionaryRepository userDictionaryRepo = 
				(WPDUserDictionaryRepository)EWPDContextUtil.getContext().getBean("userDictionaryRepository");
			wordList = userDictionaryRepo.getAllWords();
			if(wordList != null)
			{
				for(int i=0; i<wordList.size(); i++){
					String wordString = (String)wordList.get(i);
					if(BxUtil.hasText(wordString)){
						list.add(wordString);
					}
				}
			}
			
		} catch (Exception e) {
			//com.wellpoint.wpd.common.framework.logging.Logger.logError(e);
		}
		return list.size(); 
	}
	
	/**
	 * Method to add a word to the user dictionary.
	 */
	public boolean addWord(String word,User user)
	{		
		try {
			return true;
		} catch (Exception e) {
		}	
		return false;
	}
	
	public boolean isValid(){
		return valid;
	}
}
