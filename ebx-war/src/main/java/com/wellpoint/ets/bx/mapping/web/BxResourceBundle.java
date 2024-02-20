/*
 * Created on May 21, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.web;

import java.io.IOException;
import java.io.InputStream;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author u23641
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BxResourceBundle extends PropertyResourceBundle{
    
    /**
     * @param stream
     * @throws IOException
     */
    public BxResourceBundle(InputStream stream) throws IOException {
        super(stream);
    }

    public static String getResourceBundle(String key, List paramList){

        ResourceBundle  res = PropertyResourceBundle.getBundle("messages_en");
        String value = res.getString(key);
        if(paramList != null && !paramList.isEmpty() && value!= null && !value.trim().equals("")){
            for(int i =0 , j = i; i < paramList.size() ; i++){
            	String param = (String)paramList.get(i);
            	param = handleSpecialCharacters(param);            
                j = i+1;
                value = value.replaceAll("'%"+j+"'", param);
            }
        }
        
        return value;
    }

    public static List getResourceBundle(Map keyParamArrayMap) {
    	Set keySet = keyParamArrayMap.keySet();
    	List messages = new ArrayList();
    	for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			String[] placeHolders = (String[])keyParamArrayMap.get(key);
			List paramList = new ArrayList();
			if(null != placeHolders){
				for (int i = 0; i < placeHolders.length; i++) {
					paramList.add(placeHolders[i]);
				}
			}			
			messages.add(getResourceBundle(key, paramList));
		}
    	return messages;
    }
    public static String handleSpecialCharacters(String param){
        final StringBuffer result = new StringBuffer();

        final StringCharacterIterator iterator = 
          new StringCharacterIterator(param);
        char character =  iterator.current();
        while (character != CharacterIterator.DONE ){
          
          if (character == '\\') {
            result.append("\\\\");
          }         
          else if (character == '$') {
            result.append("\\$");
          }
          else {
            //the char is not a special one
            //add it to the result as is
            result.append(character);
          }
          character = iterator.next();
        }
        return result.toString();
      }

    
}
