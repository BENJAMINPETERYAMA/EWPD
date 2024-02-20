/*
 * <XwalkResourceBundle.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/
package com.wellpoint.ets.ebx.simulation.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

/**
 * @author UST-Global
 * 
 * Resource Bundle Class to read the property file
 * 
 */
public class SimulationResourceBundle extends PropertyResourceBundle{
	
    
    /**
     * Constructor
     * 
     * @param stream
     * @throws IOException
     */
    public SimulationResourceBundle(InputStream stream) throws IOException {
        super(stream);
    }

    /**
     * This method reads the property file , the key is defined in the
     * property file with the corresponding message and the paramlist
     * is replaced in the placeholders in the property file
     * 
     * @param key
     * @param paramList
     * @return
     */
    public static List getResourceBundle(String key, String fileName){
    	
    	List variableList = new ArrayList();
    	
    	 ResourceBundle rb = ResourceBundle.getBundle(fileName, Locale.getDefault());
    	 String value = rb.getString(key);
         
		 StringTokenizer token= new StringTokenizer(value);
		 
         while(token.hasMoreTokens()){
        	 variableList.add(token.nextToken(","));

         }

         return variableList;
    }
    
}
