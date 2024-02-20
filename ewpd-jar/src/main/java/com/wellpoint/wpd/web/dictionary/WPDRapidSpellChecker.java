/*
 * WPDRapidSpellChecker.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.dictionary;

import java.util.Vector;

import com.keyoti.rapidSpell.RapidSpellChecker;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class WPDRapidSpellChecker extends RapidSpellChecker {
	private static Vector filtered = null;
	public WPDRapidSpellChecker( String licenseKey ){
		super( licenseKey );
	}
	public boolean checkMainDictionary(String query){
		return super.lookUpMainDictionary(query);
	}
	
	/*protected boolean lookUpUserDictionary(String query){
		boolean result = super.lookUpUserDictionary(query.toLowerCase());
		return result;
	}

	public void check(String query) throws NullPointerException{

		super.check( query);
	}
	public synchronized Vector findSuggestions(String query) throws NullPointerException{
		
		Vector vector = super.findSuggestions(query);

		filtered = new Vector();
		String suggestion = null;
		Iterator iterator = vector.iterator();

		while(iterator.hasNext()){
			Object nextSuggestion = iterator.next();
			if(null != nextSuggestion){
				suggestion = (String)nextSuggestion;

				if(!filtered.contains(suggestion.CASE_INSENSITIVE_ORDER))
					filtered.add(suggestion);
			}
		}

		return vector;
	}*/	
}
