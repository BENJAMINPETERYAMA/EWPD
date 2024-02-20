/*
 * Created on Apr 4, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author u12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SpellcheckProperties {
	
	private static SpellcheckProperties props;

	private static HashMap spellCheckProperties;
	
	private SpellcheckProperties() {
		PropertyResourceBundle rs = (PropertyResourceBundle) ResourceBundle
			.getBundle("spellcheck");
		spellCheckProperties = loadHashMap(rs);
	}
	/**
	 * Returns a HashMap of strings containing the datafeed character codes.
	 * 
	 * @return cgDataFeedProperties
	 */
	public static HashMap getSpellCheckProperties() {
	    if (props == null) {
			props = new SpellcheckProperties();
		}
		return spellCheckProperties;
	}

	/**
	 * Loads a new Hashmap containing all the keys from the
	 * PropertyResourceBundle. The key names in the hashmap map the key names
	 * in the PropertyResourceBundle.
	 * 
	 * @param rs
	 * @return hmProperties
	 */
	private static HashMap loadHashMap(PropertyResourceBundle rs) {
		HashMap hmProperties = new HashMap();
		Enumeration keys = rs.getKeys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			hmProperties.put(key, rs.getString(key));
		}
		return hmProperties;
	}

}
