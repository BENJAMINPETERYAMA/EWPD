/*
 * <BxUtil.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/package com.wellpoint.ets.bx.mapping.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.CharacterIterator;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.wellpoint.ets.bx.mapping.domain.entity.AuditTrail;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Rule;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSId;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.exception.DomainException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.MappingResult;
import com.wellpoint.ets.bx.mapping.domain.vo.SearchCriteria;
import com.wellpoint.ets.bx.mapping.domain.vo.SearchResult;
import com.wellpoint.ets.ebx.referencedata.vo.ReferenceDataValueVO;
import com.wellpoint.ets.ebx.referencedata.vo.StandardMessageVO;


public class BxUtil {	

	private static final String SEPERATOR = " ";	
	/**
	 * Convert List of Strings into comma separated String. 
	 * @param stringList List of String
	 * @return Comma separated form of values within the list.
	 * @throws ClassCastException if not a string list.
	 */
	public static String getAsCSV(List stringList) {
		if(stringList == null)return null;
		StringBuffer csv = new StringBuffer();
		int listSize = stringList.size();
		int count = 0;
		for (Iterator iterator = stringList.iterator(); iterator.hasNext();) {
			String value = (String) iterator.next();
			csv.append(value);
			count++;
			if(count != listSize) {
				csv.append(", ");
			}
		}
		return csv.toString();
	}
	public static String getFormattedDate(Date createdDate){
		
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:s a ");
		String convertedDate = format.format(createdDate);
		
		return convertedDate;
	}
	public static String getFormattedDateWithOutTime(Date createdDate){
		
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		String convertedDate = format.format(createdDate);
		
		return convertedDate;
	}
	//get the formated date in the format yyyyMMdd
	public static String getFormattedDateinYYYYMMDDFormat(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String convertedDate = format.format(date);
		
		return convertedDate;
	}
		
	public static String appendBreak(String toModify, int LAST_INDEX){
		 		StringBuffer newDesc = new StringBuffer();
		        int BEGIN_INDEX = 0; 
		        int END_INDEX = LAST_INDEX;	
		        int incrementor = LAST_INDEX + 1;
		            if(toModify!=null){
		                int charLength = toModify.length();
		                if(charLength > incrementor){
		                    String subString = "";
		                    String[] subStringArray = toModify.split(SEPERATOR);
		                   
		                    if(subStringArray!=null){
		                        for(int i = 0; i<subStringArray.length;i++){
		                            if(subStringArray[i].length() > incrementor){
		                                while(END_INDEX <= subStringArray[i].length()){
		    		                        subString = subStringArray[i].substring(BEGIN_INDEX, END_INDEX);
		    		                        newDesc.append(subString);
		    		                        newDesc.append("<BR>");
		    		                        BEGIN_INDEX = END_INDEX;
		    		                        END_INDEX = BEGIN_INDEX + incrementor;
		    		                    }
		                                if(BEGIN_INDEX <= subStringArray[i].length() && END_INDEX >= subStringArray[i].length()){
		                                	newDesc.append(subStringArray[i].substring(BEGIN_INDEX, subStringArray[i].length()));
		                                }
			                        } else{
			                        	newDesc.append(subStringArray[i]+SEPERATOR);
			                        }
		                        }
		                    }
		                    
		                }
		                else{
		                	
		                	newDesc = new StringBuffer(toModify);
		                }
		            }
		      
	    return newDesc.toString(); 
	}
	
	public static String getAuditStatus(String mappingStatus){
		
		String auditStatus = null;
		if (mappingStatus.equals(DomainConstants.STATUS_BUILDING)) {
			auditStatus = DomainConstants.AUDIT_STATUS_ADDED;			
		}
		else if (mappingStatus.equals(DomainConstants.STATUS_BEING_MODIFIED)){
			auditStatus = DomainConstants.AUDIT_STATUS_BEING_MODIFIED;
		}else if (mappingStatus.equals(DomainConstants.STATUS_SCHEDULED_TO_TEST)){
			auditStatus = DomainConstants.AUDIT_STATUS_SEND_TO_TEST;
		}else if (mappingStatus
				.equals(DomainConstants.AUDIT_STATUS_DISCARD_CHANGES)){
			auditStatus = DomainConstants.AUDIT_STATUS_DISCARD_CHANGES;
		}else if (mappingStatus
				.equals(DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION)){
			auditStatus = DomainConstants.AUDIT_STATUS_APPROVE;
		}else if (mappingStatus.equals(DomainConstants.STATUS_NOT_APPLICABLE)){
			auditStatus = DomainConstants.AUDIT_STATUS_NOT_APPLICABLE;
		}else if (mappingStatus.equals(DomainConstants.STATUS_PUBLISHED)){
			auditStatus = DomainConstants.AUDIT_STATUS_PUBLISHED;
		}else if (mappingStatus.equals(DomainConstants.STATUS_OBJECT_TRANSFERRED)){
			auditStatus = DomainConstants.AUDIT_STATUS_OBJECT_TRANSFERRED;
		}else {
			throw new DomainException(
					"Cannot log Mapping! Invalid Mapping Status");
		}
		
		return auditStatus;
	}
	/*
	 * Sets system comment as mapping status if system comment is null.
	 */
	public static List setSystemComments(List auditTrailList){
		
		for(int i=0;i<auditTrailList.size();i++){
			
			AuditTrail audit = (AuditTrail) auditTrailList.get(i);
			
			if(null == audit.getSystemComments()){
				
				audit.setSystemComments(audit.getMappingStatus());
			}
		}
		
		return auditTrailList;
	}
	
 public static HippaSegment createHippaSegment(List items, String hippaSegmentName){
		 
		 HippaSegment hippaSegment = new HippaSegment();
		 
		 hippaSegment.setName(hippaSegmentName);
		 List hippacodePossibleList = new ArrayList();
		 if(null != items) {
				for(Iterator rsltIterator = items.iterator();rsltIterator.hasNext();){
					HippaCodeValue hippaCodeValue = new HippaCodeValue();
					HippaCodeValue item = (HippaCodeValue)rsltIterator.next();
					hippaCodeValue.setValue(item.getValue());
					hippaCodeValue.setDescription(item.getDescription());
					hippacodePossibleList.add(hippaCodeValue);
				}
			}
		 hippaSegment.setHippaCodePossibleValues(hippacodePossibleList);
		 
		 return hippaSegment;
	 }
 
 /**
  * Escape characters for text appearing in HTML markup
 * @param aText
 * @return String
 */
public static String escapeSpecialCharacters(String aText){
     final StringBuffer result = new StringBuffer();
     final StringCharacterIterator iterator = new StringCharacterIterator(aText);
     char character =  iterator.current();
     while (character != CharacterIterator.DONE ){
       if (character == '<') {
         result.append("&lt;");
       }
       else if (character == '>') {
         result.append("&gt;");
       }
       else if (character == '&') {
         result.append("&amp;");
      }
       else if (character == '\"') {
         result.append("&quot;");
       }
       else if (character == '\t') {
         addCharEntity(9, result);
       }
       else if (character == '!') {
         addCharEntity(33, result);
       }
       else if (character == '#') {
         addCharEntity(35, result);
       }
       else if (character == '$') {
         addCharEntity(36, result);
       }
       else if (character == '%') {
         addCharEntity(37, result);
       }
       else if (character == '\'') {
         addCharEntity(39, result);
       }
       else if (character == '(') {
         addCharEntity(40, result);
       }
       else if (character == ')') {
         addCharEntity(41, result);
       }
       else if (character == '*') {
         addCharEntity(42, result);
       }
       else if (character == '+') {
         addCharEntity(43, result);
       }
       else if (character == ',') {
         addCharEntity(44, result);
       }
       else if (character == '-') {
         addCharEntity(45, result);
       }
       else if (character == '.') {
         addCharEntity(46, result);
       }
       else if (character == '/') {
         addCharEntity(47, result);
       }
       else if (character == ':') {
         addCharEntity(58, result);
       }
       else if (character == ';') {
         addCharEntity(59, result);
       }
       else if (character == '=') {
         addCharEntity(61, result);
       }
       else if (character == '?') {
         addCharEntity(63, result);
       }
       else if (character == '@') {
         addCharEntity(64, result);
       }
       else if (character == '[') {
         addCharEntity(91, result);
       }
       else if (character == '\\') {
         addCharEntity(92, result);
       }
       else if (character == ']') {
         addCharEntity(93, result);
       }
       else if (character == '^') {
         addCharEntity(94, result);
       }
       else if (character == '_') {
         addCharEntity(95, result);
       }
       else if (character == '`') {
         addCharEntity(96, result);
       }
       else if (character == '{') {
         addCharEntity(123, result);
       }
       else if (character == '|') {
         addCharEntity(124, result);
       }
       else if (character == '}') {
         addCharEntity(125, result);
       }
       else if (character == '~') {
         addCharEntity(126, result);
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
  
 private static void addCharEntity(int aIdx, StringBuffer aBuffer){
	    String padding = "";
	    if( aIdx <= 9 ){
	       padding = "00";
	    }
	    else if( aIdx <= 99 ){
	      padding = "0";
	    }
	   
	    String number = padding + Integer.toString(aIdx);
	    aBuffer.append("&#" + number + ";");
	  }
 private static String wrapText(String desc, int charLength){
	 int BEGIN_INDEX = 0;
     int END_INDEX = charLength;
	 String subString = "";
     String[] subStringArray = desc.split(SEPERATOR);
     StringBuffer newDesc=new StringBuffer();
     if(subStringArray!=null){
         for(int i = 0; i<subStringArray.length;i++){
             if(subStringArray[i].length() > charLength){
                 while(END_INDEX <= subStringArray[i].length()){
                     subString = subStringArray[i].substring(BEGIN_INDEX, END_INDEX);
                     newDesc.append(subString+"<BR>");
                     BEGIN_INDEX = END_INDEX;
                     END_INDEX = BEGIN_INDEX + charLength;
                 }
                 if(BEGIN_INDEX <= subStringArray[i].length() && END_INDEX >= subStringArray[i].length()){
                     newDesc.append(subStringArray[i].substring(BEGIN_INDEX, subStringArray[i].length()));
                 }
             } else{
                   newDesc.append(subStringArray[i]+SEPERATOR);
             }
         }
     }
     return newDesc.toString();
 }
 
 private static String wrapTextSpider(String desc, int charLength){
	 int BEGIN_INDEX = 0;
     int END_INDEX = charLength;
	 String subString = "";
     String[] subStringArray = desc.split(SEPERATOR);
     StringBuffer newDesc=new StringBuffer();
     if(subStringArray!=null){
         for(int i = 0; i<subStringArray.length;i++){
             if(subStringArray[i].length() > charLength){
                 while(END_INDEX <= subStringArray[i].length()){
                     subString = subStringArray[i].substring(BEGIN_INDEX, END_INDEX);
                     //newDesc.append(subString+"<BR>");
                     newDesc.append(subString);
                     BEGIN_INDEX = END_INDEX;
                     END_INDEX = BEGIN_INDEX + charLength;
                 }
                 if(BEGIN_INDEX <= subStringArray[i].length() && END_INDEX >= subStringArray[i].length()){
                     newDesc.append(subStringArray[i].substring(BEGIN_INDEX, subStringArray[i].length()));
                 }
             } else{
                   newDesc.append(subStringArray[i]+SEPERATOR);
             }
         }
     }
     return newDesc.toString();
 }
 
 /**
  * Method to get value of a key from a property file 
  * */
 public static int getValueFromProperty(String propertyFileName,
		 String propertyName) {
		Properties configFile = new Properties();
		InputStream input = null;
		int name = 0;
		try {
			input = new FileInputStream(propertyFileName);
			configFile.load(input);
			name = Integer.parseInt(configFile.getProperty(propertyName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return name;
	}
 
	public static List concatBreak(List mappingList){
	
     int charLength = 0;
	    if(mappingList!=null && !mappingList.isEmpty()){
		    for(Iterator itr = mappingList.iterator();itr.hasNext();){
		        Mapping obj = (Mapping)itr.next();
		        if(null != obj.getVariable()){		        	
		        	Variable variable = obj.getVariable();
		        	if(null != variable.getDescription()){
		                charLength = variable.getDescription().length();
		                if(charLength > 10){		                	
		                	variable.setDescription((wrapText(variable.getDescription(),10)));	
		                }			           
		        	}
		        	if(null != obj.getUser() && null != obj.getUser().getLastUpdatedUserName()){		        		
		        		obj.getUser().setLastUpdatedUserName(obj.getUser().getLastUpdatedUserName().toLowerCase());
		        	}
	        }
	        if(null != obj.getSpsId() && (null == obj.getRule())){		        	
		        	SPSId spsId = obj.getSpsId();
		        	if(null != spsId.getSpsDesc()){
		                charLength = spsId.getSpsDesc().length();
		                if(charLength > 17){		                	
		                	spsId.setSpsDesc(wrapText(spsId.getSpsDesc(),17));	
		                }			           
		        	}
	        }
	        if(null == obj.getSpsId() && (null != obj.getRule())){		        	
	        	Rule rule = obj.getRule();
	        	if(null != rule.getRuleDesc()){
	                charLength = rule.getRuleDesc().length();
	                if(charLength > 17){		                	
	                	rule.setRuleDesc(wrapText(rule.getRuleDesc(),17));	
	                }			           
	        	}
	        }
	        else if(null != obj.getSpsId() && (null != obj.getRule())
	        		&& (null != obj.getMessage())){
	        	
	        	charLength = obj.getMessage().length();
	        	if(charLength > 17){		                	
	        		obj.setMessage(wrapText(obj.getMessage(),17));	
                }
	        }
	    }
	}
    return mappingList; 
}
	
	/**
	 * Return the duplicate values from list except if value is blank.
	 * @param hippaCodeSelectedValues List
	 * @return List.
	 */
	public static List checkHippaCodeValueDuplicates(List hippaCodeSelectedValues){
		if (null != hippaCodeSelectedValues) {
			List value = new ArrayList();
			List duplicateValues = new ArrayList();
			Iterator itr = hippaCodeSelectedValues.iterator();
			while (itr.hasNext()) {
				HippaCodeValue hippaCodeValue = (HippaCodeValue) itr.next();
				String hippaValue = hippaCodeValue.getValue();
				if (null != hippaValue && !"".equals(hippaValue.trim())) {
					hippaValue = hippaValue.trim();
					if (!value.contains(hippaValue)) {
						value.add(hippaValue);
					} else {
						if(!duplicateValues.contains(hippaValue)){
							duplicateValues.add(hippaValue);
						}
					}
				}
			}
			return duplicateValues;
		}
		return hippaCodeSelectedValues;
	}
	
	//	remove blank entries from the hippaCodeValuelist
    public static List removeBlankfromHippaCodeValueList(List hippaCodeValuelist){
        List newList = new ArrayList();
        if(hippaCodeValuelist !=null){
            for(Iterator itr = hippaCodeValuelist.iterator();itr.hasNext();){
            	HippaCodeValue hippaCodeValue = (HippaCodeValue) itr.next();
				String hippaValue = hippaCodeValue.getValue();
                if(null != hippaValue && !hippaValue.trim().equals("")){
                    newList.add((HippaCodeValue)hippaCodeValue);
                }
            }
            if(newList != null && !newList.isEmpty()){
                return newList;
            }
        }
        return null;
    }
    
    // Remove Blank from String list
	public static List removeBlankfromStringList(List valueList) {
		if (valueList != null) {
			for (Iterator itr = valueList.iterator(); itr.hasNext();) {
				String value = (String) itr.next();
				if (null == value || value.trim().equals("")) {
					itr.remove();
				}
			}
			if (valueList != null && !valueList.isEmpty()) {
				return valueList;
			}
		}
		return null;
	}
	
	/**
	 * Remove duplicate entry from list except if value is blank.
	 * @param hippaCodeUMRuleList List
	 * @return List.
	 */
	public static List <HippaCodeValue>removeDuplicates(List <HippaCodeValue>hippaCodeUMRuleList){
		if (null != hippaCodeUMRuleList) {
			List <String> value = new ArrayList<String>();
			Iterator<HippaCodeValue> itr = hippaCodeUMRuleList.iterator();
			while (itr.hasNext()) {
				HippaCodeValue hippaCodeValue = (HippaCodeValue) itr.next();
				if(null != hippaCodeValue){
					String hippaValue = hippaCodeValue.getValue();
					if (!"".equals(hippaValue)) {
						if (!value.contains(hippaValue)) {
							value.add(hippaValue);
						} else {						
							hippaCodeValue.setValue("");
						}
					}
				}	
			}
		}
		return hippaCodeUMRuleList;
	}

  public static List getMessages(List messagesList){
	  List messages = new ArrayList();
	  if(null != messagesList){
			for(Iterator itrerator = messagesList.iterator();itrerator.hasNext();){
				Object value = itrerator.next();
				if(value instanceof String){
					
					String errorMessage = (String) value;
					messages.add(errorMessage);
				}
				else if(value instanceof List){
				    List warningMsg = (List)value;
				    if(warningMsg!=null){
				        for(Iterator itr = warningMsg.iterator();itr.hasNext();){
				            String msg = (String)itr.next();
				            messages.add(msg);
				        }
				    }
				}
			}
		}
	  return messages;
  }
  public static String arrayToString(String[] a, String separator) {
	    StringBuffer result = new StringBuffer();
	    if (a.length > 0) {
	        result.append(a[0]);
	        for (int i=1; i<a.length; i++) {
	            result.append(separator);
	            result.append(a[i].trim());
	        }
	    }
	    return result.toString();
	}
	public static boolean listCompare(List list1, List list2){
		Iterator iter = list1.iterator();

		while (iter.hasNext()) {
			Iterator iterator = list2.iterator();
			String iterValue = iter.next().toString();
			while (iterator.hasNext()) {
				String iteratorValue = iterator.next().toString();
				if (iterValue.equals(iteratorValue)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * method to check whether a string has value other than blank.
	 * @param s
	 * @return
	 */
	public static boolean hasText(String s){
		 if (s == null || "".equalsIgnoreCase(s.trim())) {
			return false;
		} 
		return true;
		
	}
	
	/**
	 * This method is to replace ' to ''(double single quote), if this is not done some times query may throw sql grammer exception
	 * @param searchCriteria
	 */
	public static void replaceSingleQuote(SearchCriteria searchCriteria){
		if(BxUtil.hasText(searchCriteria.getEB03())){
			searchCriteria.setEB03(searchCriteria.getEB03().replaceAll("'", "''"));
		}
		if(BxUtil.hasText(searchCriteria.getHeaderRuleId())){
			searchCriteria.setHeaderRuleId(searchCriteria.getHeaderRuleId().replaceAll("'", "''"));
		}
		if(BxUtil.hasText(searchCriteria.getVariableId())){
			searchCriteria.setVariableId(searchCriteria.getVariableId().replaceAll("'", "''"));
		}
		if(BxUtil.hasText(searchCriteria.getMessageText())){
			searchCriteria.setMessageText(searchCriteria.getMessageText().replaceAll("'", "''"));
		}
	}
	
	/**
	 * This method returns formatted string and it is used in where condition as ebo3 values in 
	 * @param commaSeperatedEB03
	 * @return
	 */
	public static String getFormattedEB03(String commaSeperatedEB03){
		StringBuffer formattedEB03 = new StringBuffer();
		if(!BxUtil.hasText(commaSeperatedEB03)){
			return formattedEB03.toString();
		}
		String[] eb03s = commaSeperatedEB03.split(",");
	    for(int i= 0; i< eb03s.length; i++){
	       if(BxUtil.hasText(formattedEB03.toString()) && BxUtil.hasText(eb03s[i])){
	    	   formattedEB03.append(",");
	       }
	       
	       if(BxUtil.hasText(eb03s[i])){
	    	   formattedEB03.append("'").append(eb03s[i].trim()).append("'");
	       }
	    }
		return formattedEB03.toString();
	}
	
	/**
	 * This method returns formatted string and it is used in where condition as ebo3 values in 
	 * @param commaSeperatedEB03
	 * @return
	 */
	public static String getFormattedRuleIds(String commaSeperatedRuleId){
		StringBuffer formattedRuleId = new StringBuffer();;
		if(!BxUtil.hasText(commaSeperatedRuleId)){
			return formattedRuleId.toString();
		}
		String[] ruleIds = commaSeperatedRuleId.split(",");
	    for(int i= 0; i< ruleIds.length; i++){
	       if(BxUtil.hasText(formattedRuleId.toString()) && BxUtil.hasText(ruleIds[i])){
	    	   formattedRuleId.append(",");
	       }
	       if(BxUtil.hasText(ruleIds[i])){
	    	   formattedRuleId .append("'").append(ruleIds[i].trim()).append("'");
	    	   
	       }
	    }
		return formattedRuleId.toString();
	}

	/**
	 * 
	 * @param mapping
	 */
	public static void doStatusChangeForApprove(Mapping mapping){
		if(hasText(mapping.getVariableMappingStatus()) && mapping.getVariableMappingStatus().trim().equalsIgnoreCase(DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION)){
			mapping.setVariableMappingStatus(DomainConstants.STATUS_SCHEDULED_TO_TEST);
			mapping.setDataFeedInd("Y");
		}else{
			mapping.setDataFeedInd("N");
		}
	}
	
	/**
	 * 
	 * @param mapping
	 */
	public static void doStatusChangeAfterTest(Mapping mapping){
		if(hasText(mapping.getVariableMappingStatus()) && mapping.getVariableMappingStatus().trim().equalsIgnoreCase(DomainConstants.STATUS_SCHEDULED_TO_TEST)){
			if(hasText(mapping.getDataFeedInd()) && "Y".equalsIgnoreCase(mapping.getDataFeedInd().trim())){
				mapping.setVariableMappingStatus(DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION);
			}else{
				mapping.setVariableMappingStatus(DomainConstants.STATUS_OBJECT_TRANSFERRED);
			}
		}
		
		mapping.setDataFeedInd("N");
	}

	
	
	public static MappingResult getExceptionResult(Exception e, Mapping mapping){
		 MappingResult mappingResult = new MappingResult();
		 mappingResult.setStatus(false);
		 List list = new ArrayList(1);
		 list.add(e.getMessage());
		 mappingResult.setErrorMsgsList(list);	
		 mappingResult.setMapping(mapping);
		 return mappingResult;
	}

	/**
	 * 
	 * @param list
	 * @param seperator
	 * @return
	 */
	public static String listToString(List list, String seperator){
		StringBuffer combinedString = new StringBuffer();
		if(list != null && list.size() > 0 && seperator != null && seperator.length() > 0){
			for(int i = 0; i < list.size(); i++){
				combinedString.append((String)list.get(i));
				if(i != list.size() - 1){
					combinedString.append(seperator);
				}
			}
		}
		return combinedString.toString();
	}
	
	
	public static String listToStringForQuery(List list, String seperator){
		StringBuffer combinedString = new StringBuffer();
		if(list != null && list.size() > 0 && seperator != null && seperator.length() > 0){
			for(int i = 0; i < list.size(); i++){
				combinedString.append(" '");
				combinedString.append((String)list.get(i));
				combinedString.append("' ");
				if(i != list.size() - 1){
					combinedString.append(seperator);
				}
			}
		}
		return combinedString.toString();
	}


	 /**
     * @param integer
     * @return
     * Function to check whether integer or not.
     */
    public static boolean isInteger(String integer){
    	try {
    		Integer.parseInt(integer);
    	} catch(Exception e){
    		return false;
    	}
    	return true;
    }
    
    /**
     * @param hippaSegmentName
     * @param hippaSegmentValue
     * @return
     */
    public static HippaSegment setHipaaSegmentValue(String hippaSegmentName, String hippaSegmentValue) {
    	HippaSegment hippaSegment = new HippaSegment();
    	hippaSegment.setHippaSegmentName(hippaSegmentName);
    	hippaSegment.getHippaCodeSelectedValues().add(hippaSegmentValue);
    	return hippaSegment;
    }
    
    /**
     * @param hippaSegment Hippa segment value.
     * @return
     */
    public static String getHipaaSegmentValue(HippaSegment hippaSegment) {
    	String hipaaValue = "";
    	if (null != hippaSegment && null != hippaSegment.getHippaCodePossibleValues() && hippaSegment.getHippaCodeSelectedValues().size() > 0) {
    		hipaaValue = (String) hippaSegment.getHippaCodeSelectedValues().get(0);
    	}
    	return hipaaValue;
    }

    /**
     * Accepts a comma seperated users and spilt them to use in query
     * @param commaSperatedUsers
     * @return a string
     */
    public static String splitCommaSeperatedUser(String commaSperatedUsers){
    	StringBuffer userAsString = new StringBuffer();
    	if(null != commaSperatedUsers && commaSperatedUsers.length() >0){
    		String[] userArray = commaSperatedUsers.split(",");
    		for(int i=0;i<userArray.length;i++){
    			String user = userArray[i];
    			userAsString.append("'");
    			if(null!= user && user.trim().length()>0)
    				user = user.toUpperCase().trim();
    			userAsString.append(user);
    			userAsString.append("'");
    			//System.out.println(" i+1: "+i+1 +" Length: "+userArray.length);
    			if(!(i+1 == userArray.length))
    				userAsString.append(",");
    		}
    		//System.out.println("Comma seperated users:"+userAsString.toString());
    	}
    	return userAsString.toString();
    	
    }
    /**
     * Removing the leading and tailing comma from string
     * @param eb03
     * @return
     */
    public static String removeCommaFromEB03(String eb03){
    	String formattedEb03= null;    	
    	if (null != eb03) {
    		if(eb03.charAt(0) == ','){
    		formattedEb03 = eb03.trim().substring(1,eb03.length()-1).trim();
    		}else{
    			formattedEb03 = eb03;	
    		}
    	}    	
    	return formattedEb03;    	
    }
    
    
    public static String removeFirstCommaFromEB03Assn(String string){
    	String formattedString= null;    	
    	if (null != string) {
    		if(string.charAt(0) == ','){
    			formattedString = string.trim().substring(1).trim();
    		}else{
    			formattedString = string;	
    		}
    	}    	
    	return formattedString;    	
    }
    
    /**
     * isWordExists looks for exact matching of the word in the 
     * description
     * @param description
     * @param wordsToBeLooked
     * @return
     */
    public static boolean isWordExists(String description,String[] wordsToBeLooked){
		boolean exists=false;
		String expression = "[^\\w]";
		String[] arr = description.split(expression);
		for (int i = 0; i < arr.length; i++) {
			for(int j=0;j<wordsToBeLooked.length;j++) {
				if(arr[i].equalsIgnoreCase(wordsToBeLooked[j])){
					return true;
				}
			}
		}
		return exists; 
	}
    /**
     * The method returns true if the input string has numeric value.
     * @param input
     * @return boolean
     */
    public static boolean isNumeric(String input)   
    {   
       try {
          //Integer.parseInt(input); 
          Double.parseDouble(input);
          return true;   
       } catch(NumberFormatException e) {   
          return false;   
       }   
    }  
    /**
     * This method will convert the Comma Seperated Values into an Array List.
     * 
     * @param csvString - Comma Seperated Values
     * @return
     */
    public static List convertCSVToArrayList(String csvString) {
    	List result = new ArrayList();
    	final String[] tempArray = csvString.split(DomainConstants.COMMA);

    	String inputValue;
    	for (int i = 0; i < tempArray.length; i++) {
    		inputValue = tempArray[i];
    		if (null != inputValue) {
    			result.add(inputValue.trim());
    		}
    	}
    	return result;
    }
    /**
	 * This method will convert the Array List to Comma Seperated Values. Each
	 * value will be enclosed inside a single quotes.
	 * 
	 * @param array - An Array of Values which need to be converted to CSV
	 * @return
	 */
    public static String convertArrayToCSVwithSingleQuote(List array) {
    	StringBuffer result = new StringBuffer();    	
    	if (null != array) {
    		Iterator iterator = array.iterator();
    		int count = 0;
    		while(iterator.hasNext()) {
    			String value = (String)iterator.next();
    			if (null != value) {
    				if(count==0) {
    					result.append(DomainConstants.SINGLE_QUOTATION);
    					result.append(value.trim());
    					result.append(DomainConstants.SINGLE_QUOTATION);
    				}
    				else {
    					result.append(DomainConstants.COMMA);
    					result.append(DomainConstants.SINGLE_QUOTATION);
    					result.append(value.trim());
    					result.append(DomainConstants.SINGLE_QUOTATION);
    				}
    				count++;
    			}

    		}
    	}
    	return result.toString();
    }
    
    
    /**
     * This method will convert the Comma Separated Values into
     * Comma Separated Values enclosed inside a single quotes(sql query IN format).
     * @param csvString - Comma Separated Values
     * @return csvStringWithSingleQuote.
     */
    public static String convertCSVToCSVwithSingleQuote(String csvString) {
    	//convert the csv to an array
    	StringBuffer result = new StringBuffer();    	
    	List array = new ArrayList();
    	final String[] tempArray = csvString.split(DomainConstants.COMMA);

    	String inputValue;
    	for (int i = 0; i < tempArray.length; i++) {
    		inputValue = tempArray[i];
    		if (null != inputValue) {
    			array.add(inputValue.trim());
    		}
    	}
    	if (null != array) {
    		Iterator iterator = array.iterator();
    		int count = 0;
    		while(iterator.hasNext()) {
    			String value = (String)iterator.next();
    			if (null != value) {
    				if(count==0) {
    					result.append(DomainConstants.SINGLE_QUOTATION);
    					result.append(value.trim());
    					result.append(DomainConstants.SINGLE_QUOTATION);
    				}
    				else {
    					result.append(DomainConstants.COMMA);
    					result.append(DomainConstants.SINGLE_QUOTATION);
    					result.append(value.trim());
    					result.append(DomainConstants.SINGLE_QUOTATION);
    				}
    				count++;
    			}

    		}
    	}
    	return result.toString();
    }
	public static List splitListByTargetSize(List list) {
		int count = 0, fromIndex = 0, toIndex = 0;
		List result = new ArrayList();
		do {

			fromIndex = calculateFromIndex(count);
			toIndex = calculateToIndex(count, list.size());	
			List subList = list.subList(fromIndex, toIndex);
			result.add(subList);
			count++;
		} while (list.size() > toIndex);
		return result;
	}
	private static int calculateToIndex(int count, int listSize) {
		int toIndex = (count + 1) * 500;
		if (listSize < toIndex) {
			toIndex = listSize;
		}
		return toIndex;
	}


	private static int calculateFromIndex(int count) {
		return (count * 500);
	}
	/**
	 * This method will create the Hippasegment value
	 * @param hippaSegmentName
	 * @param hippaSegmentValue
	 * @return
	 */
	public static HippaSegment createHippaSegment(String hippaSegmentName, String hippaSegmentValue) {
		List selectedHippaSegmentValues = new ArrayList();
		HippaSegment hippaSegment = new HippaSegment(hippaSegmentName);
		HippaCodeValue codeValue = new HippaCodeValue();
		
		codeValue.setValue(hippaSegmentValue);
		selectedHippaSegmentValues.add(codeValue);
		hippaSegment.setHippaCodeSelectedValues(selectedHippaSegmentValues);
		return hippaSegment;
		
	}
	public static void encodeMappingsList(
			List<Mapping> ... mappings) {
		for (List<Mapping> mappingsList : mappings) {			
			for (Mapping mapping : mappingsList) {
				encodeMapping(mapping);
			}
		}
	}
	public static void encodeMapping(Mapping mapping) {
		if (mapping != null && mapping.getVariable() != null) {
			encodeVariable(mapping.getVariable());
		}
	}


	public static void encodeVariable(Variable variable) {
		if (null != variable && null != variable.getDescription()) {

			variable.setDescription(variable.getDescription().replaceAll("'",
					"&#039;"));
		}
	}
	//the method encodes the catalog description, if single quote(') is found, it is encoded as &#039;  -- BXNI June Release Fix
	public static void encodeCatalogDescription(ReferenceDataValueVO referenceDataValueVO) {
		if (null != referenceDataValueVO && null != referenceDataValueVO.getDescription()) {
			referenceDataValueVO.setDescription(referenceDataValueVO.getDescription().replaceAll(DomainConstants.SINGLE_QUOTATION,
					DomainConstants.ENCODED_SINGLE_QUOTATION));
		}
	}

	public static void encodeAdvanceSearchResult(
	List<SearchResult> searchResultList) {
		for (SearchResult searchResult : searchResultList) {
			if (null != searchResult && null != searchResult.getDescription()) {
				searchResult.setDescription(searchResult
				.getDescription().replaceAll("'", "&#039;"));
			}
		}
	}
	/**
	 * This method checks whether any of the list is empty.
	 * @param lists
	 * @return true if the list is empty
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(List... lists) {
		boolean isEmpty = false;
		for (List list : lists) {
			isEmpty = (null == list || list.isEmpty()) ? true : false;
			if (isEmpty) {
				break;
			}
		}
		return isEmpty;
	}
	
	/**
	 * method to encode the standard message.
	 * @param stdMsgList
	 */
	public static void encodeStandardMessage(List<StandardMessageVO> stdMsgList) {
		if (null != stdMsgList && !stdMsgList.isEmpty()) {
			for(StandardMessageVO messageVO : stdMsgList) {
				if(null != messageVO && null != messageVO.getStandardMessage()) {
					messageVO.setStandardMessage(messageVO.getStandardMessage().replaceAll("'", "&#039;"));
					messageVO.setStandardMessage(messageVO.getStandardMessage().replaceAll("\"", "&quot;"));
				}
				
			}
		}
		
	}
	
	// SSCR19537 April04 - Start
	/**
	 * Method to convert the string value to HippaCodeValue object.
	 * @param input
	 * @return
	 */
	public static HippaCodeValue covertToHippaCodeValue(String value) {
		HippaCodeValue hippaCodeValue = new HippaCodeValue();
		if (null != value) {
			hippaCodeValue.setValue(value.trim().toUpperCase());			
		} else {
			hippaCodeValue.setValue(DomainConstants.EMPTY);
		}

		return hippaCodeValue;
	}
	
	/**
	 * Method to convert the string value to HippaCodeValue object along with the description.
	 * @param value
	 * @param description
	 * @return
	 */
	public static HippaCodeValue covertToHippaCodeValue(String value,
			String description) {
		HippaCodeValue hippaCodeValue = new HippaCodeValue();
		if (null != value) {
			hippaCodeValue.setValue(value.trim().toUpperCase());
			if (null != description) {
				hippaCodeValue.setDescription(description.trim().toUpperCase());
			}
		} else {
			hippaCodeValue.setValue(DomainConstants.EMPTY);
			hippaCodeValue.setDescription(DomainConstants.EMPTY);
		}
		return hippaCodeValue;
	}
	
	
	
	/**
	 * Method to get the non overlap values from the given two lists.
	 * @param <T>
	 * @param coll1
	 * @param coll2
	 * @return
	 */
	public static <T> Set<T> nonOverLap(final Set<T> coll1, final Set<T> coll2)
    {
        final Set<T> result = union(coll1, coll2);
        result.removeAll(intersect(coll1, coll2));
        return result;
    }
	
	/**
	 * Method to get the union of the given two lists.
	 * @param <T>
	 * @param coll1
	 * @param coll2
	 * @return
	 */
	public static <T> Set<T> union(final Set<T> coll1, final Set<T> coll2)
    {
        final Set<T> union = new HashSet<T>(coll1);
        union.addAll(new HashSet<T>(coll2));
        return union;
    }

    /**
     * Method to get the common values of a given two lists.
     * @param <T>
     * @param coll1
     * @param coll2
     * @return
     */
    public static <T>Set<T> intersect(final Set<T> coll1, final Set<T> coll2)
    {
        final Set<T> intersection = new HashSet<T>(coll1);
        intersection.retainAll(new HashSet<T>(coll2));
        return intersection;
    }
    public static boolean checkIsEmpty(final List<HippaCodeValue> codeList) {
    	boolean empty = true;
    	if (null != codeList && !codeList.isEmpty()) {
    		for (HippaCodeValue code: codeList) {
    			if (null != code && !StringUtils.isEmpty(code.getValue())) {
    				empty = false;
    				break;
    			}
        	}
    	}
    	
    	return empty;
    }
    
    public static String removeEscapedCharacters(String input) {
    	String output = input;
    	
    	if (output.contains("%25")) {
			output = output.replaceAll("%25", "%");
		}
		if (output.contains("%26")) {
			output = output.replaceAll("%26", "&");
		}
		if (output.contains("%20")) {
			output = output.replaceAll("%20", " ");
		}
		return output;
    }
	// SSCR19537 April04 - Start
    
	//Splitting list in less than 1000, so that it can be passed to the IN clause.
    @SuppressWarnings("rawtypes")
	public static HashMap<Integer, String> getListAsCSVMap(List stringList) {

		HashMap<Integer, String> csvStringMap = new HashMap<Integer, String>();

		if (null != stringList && !stringList.isEmpty()) {
			StringBuffer csvString = new StringBuffer();
			
			int listSize = stringList.size();
			int count = 0;
			int totalCount = 0;
			int mapCounter = 1;
			Iterator iterator = stringList.iterator();
			while (iterator.hasNext()){
				String value = String.valueOf(iterator.next());
				count++;
				totalCount++;
				
				if( count <= 998 && totalCount != listSize){
					csvString.append(value);
					csvString.append(", ");
				} else {
					csvString.append(value);
					csvStringMap.put(mapCounter, csvString.toString());
					mapCounter++;
					csvString = new StringBuffer();
					count = 0;
				}
			}
		}
		return csvStringMap;
	}

	public static String variablesFromBldngStts(List<String> VarBldSttsList) {
		
		StringBuilder varBldStts = new StringBuilder();
		if (VarBldSttsList.size() == 1 && null != VarBldSttsList && !VarBldSttsList.isEmpty()) {
			varBldStts.append(VarBldSttsList.get(0).trim());

		} else if (null != VarBldSttsList && !VarBldSttsList.isEmpty()) {
			for (String Var : VarBldSttsList) {
				varBldStts.append(Var.trim());
				varBldStts.append("','");
			}
			varBldStts.delete(varBldStts.length() - 2,
					varBldStts.length());
		}
		return varBldStts.toString();
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
	
	public static void validationVariable(String inputVariable) {
		if (inputVariable == null || inputVariable.isEmpty()) {
			inputVariable = inputVariable;
		} else {
			if (inputVariable.startsWith(DomainConstants.forward) || inputVariable.endsWith(DomainConstants.forward)
					|| inputVariable.startsWith(DomainConstants.redirect)
					|| inputVariable.endsWith(DomainConstants.redirect)
					|| inputVariable.startsWith(DomainConstants.FORWARD)
					|| inputVariable.endsWith(DomainConstants.FORWARD)
					|| inputVariable.startsWith(DomainConstants.REDIRECT)
					|| inputVariable.endsWith(DomainConstants.REDIRECT)) {
				if (!BxUtil.regExPatterValidation(inputVariable)) {
					inputVariable = null;
				}
			}

		}
	}
	
	//Start: Added for EBX Spider
	public static void encodeSpiderMappingsList(
			List<SpiderUMRuleMapping> ... spiderUMRuleMapping) {
		for (List<SpiderUMRuleMapping> spiderUMRuleMappingList : spiderUMRuleMapping) {			
			for (SpiderUMRuleMapping spiderUMRuleMapping1 : spiderUMRuleMappingList) {
				encodeSpiderMapping(spiderUMRuleMapping1);
			}
		}
	}
	
	public static void encodeSpiderMapping(SpiderUMRuleMapping spiderUMRuleMapping) {
		if (null != spiderUMRuleMapping && null != spiderUMRuleMapping.getUmRuleDesc()) {
			spiderUMRuleMapping.setUmRuleDesc(spiderUMRuleMapping.getUmRuleDesc().replaceAll("'",
					"&#039;"));
		}
	}
	
	public static List concatBreakSpider(List mappingList){
		
	     int charLength = 0;
		    if(mappingList!=null && !mappingList.isEmpty()){
			    for(Iterator itr = mappingList.iterator();itr.hasNext();){
			        SpiderUMRuleMapping obj = (SpiderUMRuleMapping)itr.next();
			        if(null != obj.getUmRuleId()){		        	
			        	
			        	if(null != obj.getUmRuleDesc()){
			                charLength = obj.getUmRuleDesc().length();
			                if(charLength > 10){		                	
			                	obj.setUmRuleDesc((wrapTextSpider(obj.getUmRuleDesc(),10)));	
			                }			           
			        	}
			        	if(null != obj.getUser() && null != obj.getUser().getLastUpdatedUserName()){		        		
			        		obj.getUser().setLastUpdatedUserName(obj.getUser().getLastUpdatedUserName().toLowerCase());
			        	}
		        }		       
		    }
		}
	    return mappingList; 
	}

	//End: Added for EBX Spider
}