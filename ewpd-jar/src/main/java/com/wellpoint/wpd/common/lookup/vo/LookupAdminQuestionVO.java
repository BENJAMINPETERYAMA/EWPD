/*
 * LookupAdminQuestionVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.lookup.vo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LookupAdminQuestionVO {
	
	private String criteriaQuestion;
	
	private int adminOptionId;

	
    /**
     * @return adminOptionId
     * 
     * Returns the adminOptionId.
     */
    public int getAdminOptionId() {
        return adminOptionId;
    }
    /**
     * @param adminOptionId
     * 
     * Sets the adminOptionId.
     */
    public void setAdminOptionId(int adminOptionId) {
        this.adminOptionId = adminOptionId;
    }
	/**
	 * Returns the criteriaQuestion
	 * @return String criteriaQuestion.
	 */
	public String getCriteriaQuestion() {
		return criteriaQuestion;
	}
	/**
	 * Sets the criteriaQuestion
	 * @param criteriaQuestion.
	 */
	public void setCriteriaQuestion(String criteriaQuestion) {
		this.criteriaQuestion = criteriaQuestion;
	}
}
