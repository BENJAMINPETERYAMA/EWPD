/*
 * WPDRapidSpellChecker.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.ets.ebx.dictionary.util;

import com.keyoti.rapidSpell.RapidSpellChecker;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class WPDRapidSpellChecker extends RapidSpellChecker {
	public WPDRapidSpellChecker( String licenseKey ){
		super( licenseKey );
	}
	public boolean checkMainDictionary(String query){
		return super.lookUpMainDictionary(query);
	}
	
}
