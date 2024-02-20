/*
 * SelectedQuestionListLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.standardbenefit.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SelectedQuestionListLocateCriteria extends LocateCriteria {
    // variable decalration
    private int adminOptionSysId;
    
    private int adminOptionId;
    /**
     * @return Returns the adminOptionSysId.
     */
    public int getAdminOptionSysId() {
        return adminOptionSysId;
    }
    /**
     * @param adminOptionSysId The adminOptionSysId to set.
     */
    public void setAdminOptionSysId(int adminOptionSysId) {
        this.adminOptionSysId = adminOptionSysId;
    }
	/**
	 * @return Returns the adminOptionId.
	 */
	public int getAdminOptionId() {
		return adminOptionId;
	}
	/**
	 * @param adminOptionId The adminOptionId to set.
	 */
	public void setAdminOptionId(int adminOptionId) {
		this.adminOptionId = adminOptionId;
	}
}
