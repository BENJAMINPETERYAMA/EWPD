/*
 * AdminOptionQuestionLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.adminoption.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * Locate Criteria class for Admin Option Question
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminOptionQuestionLocateCriteria extends LocateCriteria {

    private int adminOptionId;

    private int adminOptionQuestionId;
    
    private String action;


    /**
     * Returns the adminOptionId
     * 
     * @return int adminOptionId.
     */
    public int getAdminOptionId() {
        return adminOptionId;
    }


    /**
     * Sets the adminOptionId
     * 
     * @param adminOptionId.
     */
    public void setAdminOptionId(int adminOptionId) {
        this.adminOptionId = adminOptionId;
    }


    /**
     * Returns the adminOptionQuestionId
     * 
     * @return int adminOptionQuestionId.
     */
    public int getAdminOptionQuestionId() {
        return adminOptionQuestionId;
    }


    /**
     * Sets the adminOptionQuestionId
     * 
     * @param adminOptionQuestionId.
     */
    public void setAdminOptionQuestionId(int adminOptionQuestionId) {
        this.adminOptionQuestionId = adminOptionQuestionId;
    }
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
}