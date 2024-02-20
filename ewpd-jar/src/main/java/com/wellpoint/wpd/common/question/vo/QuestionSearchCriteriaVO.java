/*
 * QuestionSearchCriteriaVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.question.vo;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class QuestionSearchCriteriaVO {

    private String questionDesc;

    private List dataTypeList;
    
    private List functionalDomainList;

    private int maxLocateResultSize;
    
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
	public void setFunctioanlDomainList(List functionalDomainList) {
		this.functionalDomainList = functionalDomainList;
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
     * Returns the maxLocateResultSize
     * 
     * @return int maxLocateResultSize.
     */

    public int getMaxLocateResultSize() {
        return maxLocateResultSize;
    }


    /**
     * Sets the maxLocateResultSize
     * 
     * @param maxLocateResultSize.
     */

    public void setMaxLocateResultSize(int maxLocateResultSize) {
        this.maxLocateResultSize = maxLocateResultSize;
    }

	public List getSpsReference() {
		return spsReference;
	}
	public void setSpsReference(List spsReference) {
		this.spsReference = spsReference;
	}
}