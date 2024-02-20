package com.wellpoint.wpd.business.search.criteria;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wellpoint.wpd.common.search.criteria.AdvancedAttribute;
import com.wellpoint.wpd.common.search.criteria.AdvancedSearchCriteria;
import com.wellpoint.wpd.common.search.criteria.AdvancedSearchObject;
import com.wellpoint.wpd.common.search.criteria.BasicAttribute;
import com.wellpoint.wpd.common.search.criteria.BasicSearchCriteria;
import com.wellpoint.wpd.common.search.criteria.SearchCriteria;
import com.wellpoint.wpd.common.search.exception.SearchCriteriaValidationException;

public class SearchCriteriaValidator {
	public boolean validate(SearchCriteria sc) throws SearchCriteriaValidationException{
		
		if(sc instanceof BasicSearchCriteria){
			BasicSearchCriteria basicSearchCriteria = (BasicSearchCriteria) sc ;
			BasicAttribute basicAttribute = basicSearchCriteria.getBasicAttribute();
	        if ((basicAttribute == null || basicAttribute.getCriteria() == null
	         		|| basicAttribute.getCriteria().trim() == "")&& (basicSearchCriteria.getLimitedTo().getBusinessEntity().size()==0&&
	         				basicSearchCriteria.getLimitedTo().getBusinessGroup().size()==0&&
							basicSearchCriteria.getLimitedTo().getLineOfBusiness().size()==0)){
	        	List logParams = new ArrayList();
		        logParams.add("");
		        String msg = "The search criteria string is empty";
		        logParams.add(msg);
		        String[] userParams = new String[1];
		        userParams[0] = msg;
		        throw new SearchCriteriaValidationException(
		                msg, logParams, null, "basic.search.criteria.error",userParams);
	        }
	        validateCriteria(basicAttribute.getCriteria());
	        
		}else if(sc instanceof AdvancedSearchCriteria){
			boolean notChecked=true;
			AdvancedSearchCriteria advancedSearchCriteria = (AdvancedSearchCriteria) sc ;
			List advancedSearchObjects = advancedSearchCriteria.getAdvancedSearchObjects();
			for(int i=0;i<advancedSearchObjects.size();i++){
				AdvancedSearchObject advancedSearchObject=(AdvancedSearchObject)advancedSearchObjects.get(i);
				if(advancedSearchObject.isChecked()){
					
					List advancedAttributes = advancedSearchObject.getAdvancedAttributes();
					for(int j=0;j<advancedAttributes.size();j++){
						
						AdvancedAttribute advancedAttribute=(AdvancedAttribute)advancedAttributes.get(j);
						if(advancedAttribute.isChecked()){
							notChecked=false;
							if ( advancedAttribute.getCriteria() == null
					         		|| "".equalsIgnoreCase(advancedAttribute.getCriteria().trim())){
					        	List logParams = new ArrayList();
						        logParams.add("");
						        String msg = "The search criteria string is empty";
						        logParams.add(msg);
						        String[] userParams = new String[1];
						        userParams[0] = msg;
						        throw new SearchCriteriaValidationException(
						                msg, logParams, null, "advanced.search.criteria.error",userParams);
					        }
						}
					}
						
				}
			}
	    if(notChecked &&(advancedSearchCriteria.getLimitedTo().getBusinessEntity().size()==0 &&
	    		advancedSearchCriteria.getLimitedTo().getBusinessGroup().size()==0 &&
				advancedSearchCriteria.getLimitedTo().getLineOfBusiness().size()==0 &&
				/*START CARS*/
				advancedSearchCriteria.getLimitedTo().getMarketBusinessUnit().size()==0/*END CARS*/)){
				List logParams = new ArrayList();
				logParams.add("");
				String msg = "The search attribute list is empty";
				logParams.add(msg);
				String[] userParams = new String[1];
				userParams[0] = msg;
	        throw new SearchCriteriaValidationException(
	                msg, logParams, null, "advanced.search.criteria.validation.error",userParams);
		}
			validateAdvancedCriteria((AdvancedSearchCriteria)sc);
		}
		return true;
	}
	
	private boolean validateAdvancedCriteria(AdvancedSearchCriteria asc)throws SearchCriteriaValidationException{
		List advancedObjects = asc.getAdvancedSearchObjects();
		for(int advncdObjCnt = 0 ;advncdObjCnt <advancedObjects.size(); advncdObjCnt++){
			AdvancedSearchObject aso = (AdvancedSearchObject) advancedObjects.get(advncdObjCnt);
			if(aso.isChecked()){
			List attributes = aso.getAdvancedAttributes();
			for(int attrCnt=0;attrCnt<attributes.size();attrCnt++){
				AdvancedAttribute attr = (AdvancedAttribute)attributes.get(attrCnt);
				if(attr.isChecked()){
				if(attrCnt < attributes.size()-1){
					if(attr.getRelation()== null || "".equals(attr.getRelation())){
						List logParams = new ArrayList();
				        logParams.add(attr);
						String msg = "Logical operator ( AND/OR ) needs to be specified for criteria ,"+attr.getName();
						String[] userParams = new String[1];
				        userParams[0] = attr.getName();
						throw new SearchCriteriaValidationException(
				                msg, logParams, null, "advanced.search.criteria.error.logical",userParams);
						
					}
				}
				validateCriteria(attr.getCriteria());
						//validateType(attr.getType(),attr.getCriteria(),attr.getSign(),attr.getName());
						if(null!=attr.getSign()&&!"".equalsIgnoreCase(attr.getSign().trim())){
							validateForSign(attr.getType(),attr.getSign(),attr.getCriteria());
						}
				}
			}
		  }
		}
		return true;
	}

	private boolean validateCriteria(String criteria) throws SearchCriteriaValidationException{
		validateMatchingBrackets(criteria);
		checkInvalidOperaratorCombinations(criteria);
		return true;
	}
	private boolean validateMatchingBrackets(String criteria) throws SearchCriteriaValidationException{
		Stack openBrackets = new Stack();
    	char searchWorldChars []= criteria.toCharArray();
    	for(int i=0;i<searchWorldChars.length;i++){
    		if(openBrackets.size() >3){
    			List logParams = new ArrayList();
    			logParams.add(criteria);
    			String msg = "Nesting of more than 3 levels is not supported";
    			logParams.add(msg);
    			String[] userParams = new String[2];
    			userParams[0] = msg;
    			userParams[1] = "" + i;
    			throw new SearchCriteriaValidationException(msg,logParams,null,"basic.search.criteria.error.at",userParams);
    		}
    		char ch =  searchWorldChars[i];
    		char prevChar =' ';
    		switch(ch){
    			case '(':
 
    				if(i!=0){
    					prevChar = searchWorldChars[i-1];
    				}
    				if(prevChar!='\\'){
    					openBrackets.push(")");
    				}
    				break;
    			case ')':
    				try{
        				if(i!=0){
        					prevChar = searchWorldChars[i-1];
        				}
        				if(prevChar!='\\'){
        					openBrackets.pop();
        				}
    	
    				}catch(EmptyStackException ese){
    	    			List logParams = new ArrayList();
    	    			logParams.add(criteria);
    	    			String msg = "Mismatching brackets at position : ";
    	    			logParams.add(msg);
    	    			String[] userParams = new String[2];
    	    			userParams[0] = msg;
    	    			userParams[1] = "" + (i + 1);
    	    			throw new SearchCriteriaValidationException(msg,logParams,ese,"basic.search.criteria.error.at",userParams);
    				}
    				break;
    		}
    	}
    	if(openBrackets.size()!=0){
			List logParams = new ArrayList();
			logParams.add(criteria);
			String msg = "Mismatching brackets";
			logParams.add(msg);
			String[] userParams = new String[1];
			userParams[0] = msg;
    		throw new SearchCriteriaValidationException(msg, logParams, null,
					"basic.search.criteria.error", userParams);
    	}
    	return true;
	}
	private boolean checkInvalidOperaratorCombinations(String criteria) throws SearchCriteriaValidationException{
		Pattern p = Pattern.compile("\\)\\s*\\(|"+
                					"\\(\\s*\\)|"+
									"\\+\\s*\\)|"+
									"\\(\\s*\\+|"+
									"\\(\\s*\\||"+
									"\\|\\s*\\)|"+
									"\\|\\s*\\+|"+
									"\\+\\s*\\)"
									);
		Matcher m = p.matcher(criteria);
		if(m.find()){
			List logParams = new ArrayList();
			logParams.add(criteria);
			String msg = "Invalid operator combination ";
			logParams.add(msg);
			String[] userParams = new String[1];
			userParams[0] = msg;				
			throw new SearchCriteriaValidationException(msg, logParams,
					null, "basic.search.criteria.error", userParams);
		}
		return true;
	}
	
/*	private boolean validateType(String type,String criteria,String sign,String name)throws SearchCriteriaValidationException{
		boolean ignoreOperatorCheck=false;
		ignoreOperatorCheck=(null==sign)?true:(" ".equalsIgnoreCase(sign)?true:false);
		
		if("Integer".equalsIgnoreCase(type)){
		    if(!StringUtil.isInteger(criteria,ignoreOperatorCheck)){
		    	List logParams = new ArrayList();
				logParams.add(criteria);
				String msg = "Invalid criteria ";
				logParams.add(msg);
				String[] userParams = new String[2];
				userParams[0] = name;
				userParams[1] = type;	
				throw new SearchCriteriaValidationException(msg, logParams,
						null, "advanced.search.criteria.type.error", userParams);
		    	
		    }
		    
		}else if("Date".equalsIgnoreCase(type)){
			ignoreOperatorCheck=(null==sign)?false:(" ".equalsIgnoreCase(sign)?false:"[]".equalsIgnoreCase(sign)?true:false);
		    if(!StringUtil.isDate(criteria,ignoreOperatorCheck)){
		    	List logParams = new ArrayList();
				logParams.add(criteria);
				String msg = "Invalid criteria ";
				logParams.add(msg);
				String[] userParams = new String[2];
				userParams[0] = name;
				userParams[1] = type+" ,Format: MM/DD/YY eg: 03/21/07 ";				
				throw new SearchCriteriaValidationException(msg, logParams,
						null, "advanced.search.criteria.type.error", userParams);
		    	
		    }
		   
		}else if("String".equalsIgnoreCase(type)){
			return true;
		}else if("Long".equalsIgnoreCase(type)){
		    if(!StringUtil.isLong(criteria,ignoreOperatorCheck)){
		    	List logParams = new ArrayList();
				logParams.add(criteria);
				String msg = "Invalid criteria ";
				logParams.add(msg);
				String[] userParams = new String[2];
				userParams[0] = name;
				userParams[1] = type;					
				throw new SearchCriteriaValidationException(msg, logParams,
						null, "advanced.search.criteria.type.error", userParams);
		    	
		    }
		   
		}else if("Double".equalsIgnoreCase(type)){
		    if(!StringUtil.isDouble(criteria,ignoreOperatorCheck)){
		    	List logParams = new ArrayList();
				logParams.add(criteria);
				String msg = "Invalid criteria ";
				logParams.add(msg);
				String[] userParams = new String[2];
				userParams[0] = name;
				userParams[1] = type;					
				throw new SearchCriteriaValidationException(msg, logParams,
						null, "advanced.search.criteria.type.error", userParams);
		    	
		    }
		   
		}else if("Float".equalsIgnoreCase(type)){
		    if(!StringUtil.isFloat(criteria,ignoreOperatorCheck)){
		    	List logParams = new ArrayList();
				logParams.add(criteria);
				String msg = "Invalid criteria ";
				logParams.add(msg);
				String[] userParams = new String[2];
				userParams[0] = name;
				userParams[1] = type;				
				throw new SearchCriteriaValidationException(msg, logParams,
						null, "advanced.search.criteria.type.error", userParams);
		    	
		    }
		   
		}else{
		  	List logParams = new ArrayList();
			logParams.add(criteria);
			String msg = "Invalid DataType for the Criteria";
			logParams.add(msg);
			String[] userParams = new String[2];
			userParams[0] = name;
			userParams[1] = type;					
			throw new SearchCriteriaValidationException(msg, logParams,
					null, "advanced.search.criteria.type.error", userParams);
		}
		return true;
		
	}
	*/
	/**
	 * @param type
	 * @param sign
	 */
	private boolean validateForSign(String type, String sign,String criteria)throws SearchCriteriaValidationException  {
		if(!validateSign(criteria)){
			List logParams = new ArrayList();
			logParams.add(type);
			String msg = "Invalid criteria for the sign ";
			logParams.add(msg);
			String[] userParams = new String[2];
			userParams[0] = criteria;		
			throw new SearchCriteriaValidationException(msg, logParams,
					null, "advanced.search.criteria.sign.error", userParams);
	    }else{
			if(!"Date".equalsIgnoreCase(type)){
				StringBuffer handledSearchStr=new StringBuffer();
				if(!validateBackSlash(criteria,handledSearchStr)){
					 List logParams = new ArrayList();
		                logParams.add(criteria);
		                String msg = "Backslash character has to be escaped with a backslash character";
		                logParams.add(msg);
		    			String[] userParams = new String[2];
		    			userParams[0] = msg;
		    			userParams[1] = handledSearchStr.toString();
		                throw new SearchCriteriaValidationException(
		                        msg,logParams,null,"basic.search.criteria.error.at",
		                        userParams);
				}
			}
	    }
		if("[]".equalsIgnoreCase(sign)){	
			
			if(!validateBetween(criteria)){
	    		List logParams = new ArrayList();
				logParams.add(type);
				String msg = "Invalid criteria for the sign";
				logParams.add(msg);
				String[] userParams = new String[3];
				userParams[0] = criteria;
				userParams[1] = " VALUE , VALUE ";			
				userParams[2] = sign;			
				throw new SearchCriteriaValidationException(msg, logParams,
						null, "advanced.search.criteria.sign.between.error", userParams);
	    	}
		}   	
		return true;
		
	}
	private boolean validateBetween(String criteria){
		boolean found=false;
		char strArr[]=criteria.toCharArray();
		for(int i=0;i<strArr.length;i++){
			if(strArr[i]==','){
				if(i-1==-1)
					return false;
				if(strArr[i-1]=='\\'?false:true&&found){
					return false;
				}else{
						if(i==strArr.length-1&&strArr[i-1]!='\\')
							return false;
						if(strArr[i-1]!='\\')
							found=true;
				}
			}
		}
		return found;
	}
	private boolean validateSign(String criteria){
		char strArr[]=criteria.toCharArray();
		for(int i=0;i<strArr.length;i++){
			if(strArr[i]=='('||strArr[i]==')'||strArr[i]=='+'||strArr[i]=='|'||strArr[i]=='\''){
				if(i-1>=0){
					if(!(strArr[i-1]=='\\')){
						return false;
					}
				}else{
					return false;
				}
			}
		}
		return true;
	}
	private boolean validateBackSlash(String criteria,StringBuffer handledSearchStr){
		char strArr[]=criteria.toCharArray();
		for(int i=0;i<strArr.length;i++){
			if(strArr[i]=='\\'){
				if(i+1<strArr.length){
					if(!(strArr[i+1]=='\\')){
						if(i+1<strArr.length){
							if(!(strArr[i+1]=='(')&&!(strArr[i+1]==')')&&!(strArr[i+1]=='+')&&!(strArr[i+1]=='|')&&!(strArr[i+1]=='\'')){
								handledSearchStr.append(criteria.substring(0,i));
								return false;
							}else{
								i++;
								continue;
							}
						}
						handledSearchStr.append(criteria.substring(0,i));
						return false;
					}i++;
					}else{
				handledSearchStr.append(criteria.substring(0,i));
				return false;
			
			   }
			}
		}
		return true;
	}



}
