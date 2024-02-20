/*
 * StringUtil.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: StringUtil.java 3782 2006-09-08 21:01:47Z U10567 $
 */
public class StringUtil {
    
    /**
     * Capitalize the first character of a given string
     * 
     * @param string
     * @return
     */
    public static String capitalizeFirstLetter(String string){
        if(string == null)
            return null;
		char[] strArray = string.toCharArray();
		strArray[0] = Character.toUpperCase(string.charAt(0));
		string = new String(strArray);
		return string;
    }
    
    public static boolean isInteger(String integer){
    	try{
    		Integer.parseInt(integer);
    	}catch(Exception e){
    		return false;
    	}
    	return true;
    }
    public static boolean isDate(String date){
    	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try{ 
			
			if(date.length() != "MM/dd/yyyy".length() ){
				return false;
			}
			sdf.setLenient(false);
			sdf.parse(date);
		}catch (Exception e){
			return false;
		}	  
    	return true;
    }
    public static String removeSpace(String string){
        if(string == null)
            return null;
        String str=string.trim();
        StringBuffer strBuf=new StringBuffer();
        StringTokenizer stringTokenizer=new StringTokenizer(str," ");
        while(stringTokenizer.hasMoreTokens()){
        	String strTok=stringTokenizer.nextToken();
        	if(null!=strTok)
        	strBuf.append(strTok); 
        }
     
		return strBuf.toString();
    }
    /**
     * 
     * @param inputString
     * @return
     */
	public static String singleSpaceSeparation(String inputString){
		inputString.trim();
		String [] inputStringArray= inputString.split(" ");
		StringBuffer buffer = new StringBuffer();
		for (int i= 0; i<inputStringArray.length;i++){
			if(!inputStringArray[i].equals("")){
				buffer.append(inputStringArray[i]);
				buffer.append(" ");
			}
		}
		return buffer.toString().trim();
	}
    public static String lowerCaseFirstLetter(String string){
        if(string == null)
            return null;
		char[] strArray = string.toCharArray();
		strArray[0] = Character.toLowerCase(string.charAt(0));
		string = new String(strArray);
		return string;
    }

    public static boolean isFloat(String floatValue){
    	try{
    		Float.parseFloat(floatValue);
    	}catch(Exception e){
    		return false;
    	}
    	return true;
    }
    
    public static boolean isDouble(String doubleValue){
    	try{
    		Double.parseDouble(doubleValue);
    	}catch(Exception e){
    		return false;
    	}
    	return true;
    }
    
    public static boolean isLong(String longValue){
    	try{
    		Long.parseLong(longValue);
    	}catch(Exception e){
    		return false;
    	}
    	return true;
    }
    
    public static boolean isEmpty(String string){
    	
    	if((null!=string) && (!"".equalsIgnoreCase(string) )){
    		return false;
    	}    	
    	return true;
    }
    /**
     * This method checks whether the object is null or empty.
     * @param obj
     * @return boolean
     */
    public static boolean isEmpty(Object obj){
    	
    	if((null!=obj) && (!"".equals(obj) )){
    		return false;
    	}    	
    	return true;
    }    
    /**
     * This method converts a list of item to a comma seperated string.
     * @param listValue
     * @return commaSeperatedValue
     */	
    public static String commaSeperate(List listValue){
    	StringBuffer commaSeperatedValue = new StringBuffer("");
    	if(listValue!=null){
    	int listSize = listValue.size();
		for(int i=0;i<listSize;i++){
			commaSeperatedValue.append(listValue.get(i).toString());
			if(i == listSize-1)
				break;
			commaSeperatedValue.append(',');
		}	
    	}
    	return commaSeperatedValue.toString();
    }
    /**
     * this method converts comma separated string to a List 
     * @param commaSeperatedString
     * @return list
     */
   public static List convertToList(String commaSeperatedString) {
	   List stringList = new ArrayList();
	   if(null != commaSeperatedString) {
		   StringTokenizer tokens = new StringTokenizer(commaSeperatedString, ",");
		   while(tokens.hasMoreTokens()) {
			   stringList.add(tokens.nextToken());
		   }
	   }
	   return stringList;
   }
   /**
    * function that accept a string and return null if
    * the string is either null or empty.
    * @return string
    */
   public static String trimToNull(String stringToTrim){
   	if(stringToTrim == null) {
   		return null;
   	} else if(stringToTrim.trim().length() == 0) {
   		return null;
   	} else {
   		return stringToTrim.trim();
   	}
   }
   public static boolean compateString(String str1, String str2) {
   	str1 = trimToNull(str1);
   	str2 = trimToNull(str2);
   	if(str1 == null && str2 == null) return true;
   	if(str1 != null && str2 != null){
   		if(str1.compareToIgnoreCase(str2) ==0){
   			return true;
   		} else {
   			return false;
   		}
   	} else {
   		return false; 
   	}
   }
   
   /**
     * Method to validate input parameter using RegEX 
	 * @Input param as String type(regExInput)
	 * @return boolean value
	 */
	public static boolean regExPatterValidation(String regExInput) {
		if (regExInput != null && !regExInput.isEmpty()) {
			Pattern p = Pattern.compile("^[0-9a-zA-Z_\\s/-]+$");
			boolean patternFound = p.matcher(regExInput).find();
			if (patternFound == true) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}
