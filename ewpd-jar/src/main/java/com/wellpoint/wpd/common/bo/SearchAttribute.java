/*
 * SearchAttribute.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SearchAttribute {
    
    public static final String OPERATOR_EQUALS = "=";
    public static final String OPERATOR_GREATER_THAN = ">";
    public static final String OPERATOR_LESS_THAN = "<";
    public static final String OPERATOR_NOT_EQUAL_TO = "<>";
    public static final String OPERATOR_RANGE = "[]";
    
    public static final String REL_AND = "AND";
    public static final String REL_OR = "OR";
    
    private String searchWord;
    
    private String attributeName;
    
    private String operator;
    
    private String relationOperator;
    
    

    /**
     * @return Returns the attributeName.
     */
    public String getAttributeName() {
        return attributeName;
    }
    /**
     * @param attributeName The attributeName to set.
     */
    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
    /**
     * @return Returns the operator.
     */
    public String getOperator() {
        return operator;
    }
    /**
     * @param operator The operator to set.
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }
    /**
     * @return Returns the relationOperator.
     */
    public String getRelationOperator() {
        return relationOperator;
    }
    /**
     * @param relationOperator The relationOperator to set.
     */
    public void setRelationOperator(String relationOperator) {
        this.relationOperator = relationOperator;
    }
    /**
     * @return Returns the searchWord.
     */
    public String getSearchWord() {
        return searchWord;
    }
    /**
     * @param searchWord The searchWord to set.
     */
    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }
}
