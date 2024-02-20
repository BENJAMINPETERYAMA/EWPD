/*
 * QuestionLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.question.locateCriteria;

import java.util.List;
import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * Locate criteria class for questions
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class QuestionLocateCriteria extends LocateCriteria {

    private String questionDesc;

    private List dataTypeList;
    
    private List functionalDomainList;

    private int questionNumber;

    private String action;
    
    private List spsReference;


	/**
	 * @return Returns the functionalDomainList.
	 */
	public List getFunctionalDomainList() {
		return functionalDomainList;
	}
	/**
	 * @param functionalDomainList The functionalDomainList to set.
	 */
	public void setFunctionalDomainList(List functionalDomainList) {
		this.functionalDomainList = functionalDomainList;
	}
    /**
     * Returns the questionNumber.
     * 
     * @return questionNumber
     */
    public int getQuestionNumber() {
        return questionNumber;
    }


    /**
     * The questionNumber to set.
     * 
     * @param questionNumber
     */
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }


    /**
     * Returns the dataTypeList.
     * 
     * @return dataTypeList
     */
    public List getDataTypeList() {
        return dataTypeList;
    }


    /**
     * The dataTypeList to set.
     * 
     * @param dataTypeList
     */
    public void setDataTypeList(List dataTypeList) {
        this.dataTypeList = dataTypeList;
    }


    /**
     * Returns the questionDesc.
     * 
     * @return questionDesc
     */
    public String getQuestionDesc() {
        return questionDesc;
    }


    /**
     * The questionDesc to set.
     * 
     * @param questionDesc
     */
    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }


    /**
     * Returns the action.
     * 
     * @return action
     */
    public String getAction() {
        return action;
    }


    /**
     * The action to set.
     * 
     * @param action
     */
    public void setAction(String action) {
        this.action = action;
    }
	
	public List getSpsReference() {
		return spsReference;
	}
	public void setSpsReference(List spsReference) {
		this.spsReference = spsReference;
	}
}