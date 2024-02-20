/*
 * LookupAdminQuestionLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.lookup.locateCriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LookupAdminQuestionLocateCriteria extends LocateCriteria{
	
	private String question;
	
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
	 * Returns the question
	 * @return String question.
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * Sets the question
	 * @param question.
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
}
