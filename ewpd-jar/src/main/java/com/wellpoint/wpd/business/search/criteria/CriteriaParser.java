/*
 * CriteriaParser.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.search.criteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.search.exception.SearchCriteriaValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: CriteriaParser.java 46498 2008-05-23 13:18:50Z U14659 $
 */
public class CriteriaParser {
    /**
     * Parse the criteria string to create a list of strings and operators for
     * creating the query condition
     * 
     * @param criteriaString
     * @return
     * @throws SevereException
     */
    public List parseCriteriaString(String criteriaString)
            throws SearchCriteriaValidationException {
        if (criteriaString == null || criteriaString.trim().equalsIgnoreCase("")){
        	List logParams = new ArrayList();
        	logParams.add(criteriaString);
        	String msg = "The search criteria string is empty";
        	logParams.add(msg);
        	String[] userParams = new String[1];
        	userParams[0] = msg;
            throw new SearchCriteriaValidationException(
                    msg, logParams, null, "basic.search.criteria.error",userParams);
        }



        List firstPassList = firstPass(criteriaString);



        List secondPassList = secondPass(firstPassList, criteriaString);
        
        String firstLiteral = (String)secondPassList.get(0);
        String lastLiteral = (String)secondPassList.get(secondPassList.size()-1);
       
        if (secondPassList.size() == 2 || firstLiteral.equals("+")
				|| firstLiteral.equals("|") || lastLiteral.equals("+")
				|| lastLiteral.equals("|")) {
            List logParams = new ArrayList();
            logParams.add(criteriaString);
            String msg = "Operators not specified correctly in the search string";
            logParams.add(msg);
			String[] userParams = new String[1];
			userParams[0] = msg;
            throw new SearchCriteriaValidationException(
                    msg, logParams, null, "basic.search.criteria.error",userParams);
        }
        

        validateBackSlash(secondPassList, criteriaString);
        validateOperators(secondPassList, criteriaString);
       
        return secondPassList;
    }

    /**
     * @param criteriaString
     * @return
     */
    private List firstPass(String criteriaString) {
        //First pass to create a list that separates ', + , |, (, ) and the
        // search strings
        StringBuffer searchString = new StringBuffer();
        char[] criteriaStringChars = criteriaString.toCharArray();
        long criteriaStringCharsLen = criteriaStringChars.length;
        List firstPassList = new ArrayList();
        for (int charIndx = 0; charIndx < criteriaStringCharsLen; charIndx++) {
            char ch = criteriaStringChars[charIndx];
            switch (ch) {
            case '\\':
                searchString.append(ch);
                if (charIndx + 1 < criteriaStringCharsLen) {
                    searchString.append(criteriaStringChars[++charIndx]);
                }
                break;
            case '\'':
                firstPassList.add(searchString.toString());
                firstPassList.add(new String("\'"));
                searchString = new StringBuffer();
                break;
            case '+':
                firstPassList.add(searchString.toString());
                firstPassList.add(new String("+"));
                searchString = new StringBuffer();
                break;
            case '|':
                firstPassList.add(searchString.toString());
                firstPassList.add(new String("|"));
                searchString = new StringBuffer();
                break;
            case '(':
                firstPassList.add(searchString.toString());
                firstPassList.add(new String("("));
                searchString = new StringBuffer();
                break;
            case ')':
                firstPassList.add(searchString.toString());
                firstPassList.add(new String(")"));
                searchString = new StringBuffer();
                break;
            default:
                searchString.append(ch);
            }
        }
        firstPassList.add(searchString.toString());
        return firstPassList;
    }

    /**
     * @param firstPassList
     * @return
     * @throws SearchCriteriaValidationException
     */
    private List secondPass(List firstPassList, String criteriaString)
            throws SearchCriteriaValidationException {
        StringBuffer searchString;
        //Second pass to remove the empty elements of list returned by the
        // first pass
        // and form the search strings based on the escape characters
        List secondPassList = new ArrayList();
        Iterator iterator = firstPassList.iterator();
        boolean quoteOpened = false;
        boolean quoteFound = false;
        boolean operatorSymbolFound = false;
        searchString = new StringBuffer();
        while (iterator.hasNext()) {
            String str = (String) iterator.next();
            if (!quoteOpened)
                str = str.trim();
            operatorSymbolFound = false;
            if (str.equals("+") || str.equals("|") || str.equals("(")
                    || str.equals(")")|| str.equals(","))
                operatorSymbolFound = true;
            if (!str.equals("")) {
                quoteFound = false;
                if (str.equals("\'"))
                    quoteFound = true;
                if (quoteFound || !operatorSymbolFound) {
                    if (quoteFound) {
                        if (quoteOpened) {
                            quoteOpened = false;
                            searchString.append(str);
                            secondPassList.add(searchString.toString());
                            searchString = new StringBuffer();
                        } else {
                            quoteOpened = true;
                            if (searchString.length() == 0) {
                            } else {
                                secondPassList.add(searchString.toString());
                                searchString = new StringBuffer();
                            }
                            searchString.append(str);
                        }
                    } else
                        searchString.append(str);

                } else {
                    if (operatorSymbolFound && quoteOpened) {
                        Iterator itr = secondPassList.iterator();
                        StringBuffer handledSearchStr = new StringBuffer();
                        while (itr.hasNext()) {
                            handledSearchStr.append((String) itr.next());
                        }
                        List logParams = new ArrayList();
                        logParams.add(criteriaString);
                        String msg = "Operator has to be escaped with a back slash character";
                        logParams.add(msg);
            			String[] userParams = new String[2];
            			userParams[0] = msg;
            			userParams[1] = handledSearchStr.toString()+ searchString.toString();
                        throw new SearchCriteriaValidationException(
                                msg,logParams,null,"basic.search.criteria.error.after",
                                userParams);
                    } else {
                        if (searchString.length() == 0) {
                        } else {
                            secondPassList.add(searchString.toString());
                            searchString = new StringBuffer();
                        }
                        secondPassList.add(str);
                    }
                }
            }
        }

        if (searchString.length() == 0) {
        } else {
            secondPassList.add(searchString.toString());
        }

        //If closing quote is not found the quoteOpened will be set to true
        if (quoteOpened){
            List logParams = new ArrayList();
            logParams.add(criteriaString);
            String msg = "Quote not properly terminated";
            logParams.add(msg);
			String[] userParams = new String[1];
			userParams[0] = msg;
            throw new SearchCriteriaValidationException(
                    msg, logParams, null, "basic.search.criteria.error", userParams);
        }
        return secondPassList;
    }

    /**
     * @param secondPassList
     * @throws SearchCriteriaValidationException
     */
    private void validateBackSlash(List secondPassList, String criteriaString)
    		throws SearchCriteriaValidationException {
    	int secondPassListLen = secondPassList.size();
    	StringBuffer handledSearchStr = new StringBuffer();
    	for (int i = 0; i < secondPassListLen; ++i) {
    		String element = (String) secondPassList.get(i);

    		StringTokenizer tokenizer = new StringTokenizer(element, "\\\'+|)(", true);
    		String elmt1 = "";
    		String elmt2 = "";
    		boolean nonEscapedBackslash = false;
    		while (tokenizer.hasMoreTokens()) {
				if (elmt1.equals("")) {
					elmt1 = tokenizer.nextToken();
				}
				if (tokenizer.hasMoreTokens()) {
					if (elmt2.equals("")) {
						elmt2 = tokenizer.nextToken();
					}
				} else {
					if (elmt1.equals("\\")) {
						nonEscapedBackslash = true;
					}
					break;
				}
				if (elmt1.equals("\\") && elmt2.equals("\\")) {
					handledSearchStr.append(elmt1+elmt2);
					elmt1 = "";
					elmt2 = "";
				} else {
					if (elmt1.equals("\\")
							&& !(elmt2.equals("\\") || elmt2.equals("\'")
									|| elmt2.equals("+") || elmt2.equals("|")
									|| elmt2.equals(")") || elmt2.equals("("))) {
						handledSearchStr.append(elmt1);
						nonEscapedBackslash = true;
						break;
					} else {
						if (!elmt1.equals("\\") && elmt2.equals("\\")) {
							handledSearchStr.append(elmt1);
							elmt1 = elmt2;
							elmt2 = "";
						} else {
							handledSearchStr.append(elmt1+elmt2);
							elmt1 = "";
							elmt2 = "";
						}
					}
				}
			}
    		if(elmt1.equals("\\") && elmt2.equals("")){
    		    nonEscapedBackslash = true;
    		}
    		
    		if(nonEscapedBackslash){
                List logParams = new ArrayList();
                logParams.add(criteriaString);
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
    
    /**
     * @param secondPassList
     * @throws SearchCriteriaValidationException
     */
    private void validateOperators(List secondPassList, String criteriaString)
            throws SearchCriteriaValidationException {
        //Validate whether operators are present one after the another
        int secondPassListLen = secondPassList.size();
        String prevElement = null;
        String nextElement = null;
        for (int i = 0; i < secondPassListLen; ++i) {
            String element = (String) secondPassList.get(i);
            if (element.equals("+") || element.equals("|")) {
                if (i > 0 && i < secondPassListLen - 1) {
                    prevElement = (String) secondPassList.get(i - 1);
                    nextElement = (String) secondPassList.get(i + 1);
                    if (prevElement.equals("+") || prevElement.equals("|")
                            || nextElement.equals("+")
                            || nextElement.equals("|")) {
                        List logParams = new ArrayList();
                        logParams.add(criteriaString);
                        String msg = "Two operators present one after the another";
                        logParams.add(msg);
            			String[] userParams = new String[1];
            			userParams[0] = msg;
                        throw new SearchCriteriaValidationException(
                                msg, logParams, null, "basic.search.criteria.error",userParams);
                    }
                }
            }else{
            	boolean invalidStringBeforeOrAfterParanthesis = false;
            	boolean adjacentLiterals = false;
            	if(element.equals("(")){
	                if (i > 0 && i < secondPassListLen - 1) {
	                    prevElement = (String) secondPassList.get(i - 1);
	                    nextElement = (String) secondPassList.get(i + 1);
	                    if(!prevElement.equals("+") && !prevElement.equals("|") && !prevElement.equals("(") && !prevElement.equals(")")){
	                    	invalidStringBeforeOrAfterParanthesis = true;	                    	
	                    }
	                }
            	}else{
            		if(element.equals(")")){
		                if (i > 0 && i < secondPassListLen - 1) {
		                    prevElement = (String) secondPassList.get(i - 1);
		                    nextElement = (String) secondPassList.get(i + 1);
		                    if(!nextElement.equals("+") && !nextElement.equals("|") && !nextElement.equals("(") && !nextElement.equals(")")){
		                    	invalidStringBeforeOrAfterParanthesis = true;
		                    }
		                }
            		}else{
            			if((!element.equals("+") && !element.equals("|") && !element.equals("(") && !element.equals(")"))){
    		                if (i > 0 && i < secondPassListLen - 1) {
    		                    prevElement = (String) secondPassList.get(i - 1);
    		                    nextElement = (String) secondPassList.get(i + 1);
		            			if((!nextElement.equals("+") && !nextElement.equals("|") && !nextElement.equals("(") && !nextElement.equals(")")) ||
		            			(!prevElement.equals("+") && !prevElement.equals("|") && !prevElement.equals("(") && !prevElement.equals(")"))){
		            				adjacentLiterals = true;
		            			}
    		                }
		                }
            		}
            	}
            	
            	if(invalidStringBeforeOrAfterParanthesis || adjacentLiterals){
	                List logParams = new ArrayList();
	                logParams.add(criteriaString);
	                String msg = null;
	                if (invalidStringBeforeOrAfterParanthesis) msg = "Operators have to be specified between parantheses and search strings";
	                if (adjacentLiterals) msg = "Operator have to be specified between search strings";
	                logParams.add(msg);
	    			String[] userParams = new String[1];
	    			userParams[0] = msg;
	                throw new SearchCriteriaValidationException(
	                        msg, logParams, null, "basic.search.criteria.error",userParams);
            	}
            }
        }
    }
    
    public static void main(String args[]) {
        CriteriaParser cp = new CriteriaParser();
        try {
            List a = cp.parseCriteriaString("(123.15|12)+12.56");

        	//cp.parseCriteriaString("'\\b\\'");
            //cp.parseCriteriaString("(( 'abc\\+') | ('def')");
        } catch (SearchCriteriaValidationException e) {

        }
    }
}