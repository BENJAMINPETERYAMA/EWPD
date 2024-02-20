/*
 * LocateSelectedQuestionListRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LocateSelectedQuestionListRequest extends WPDRequest {
    // variable declarations
    public int adminOptionSysId;
    private int maxSearchResultSize;
//Enhancement
    // Refers to ADMIN_OPT_SYS_ID	
    private int adminOptionId;
//End - Enhancement    
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
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }
	/**
	 * @return Returns the maxSearchResultSet.
	 */
	public int getMaxSearchResultSize () {
		return maxSearchResultSize ;
	}
	/**
	 * @param maxSearchResultSet The maxSearchResultSet to set.
	 */
	public void setMaxSearchResultSize (int maxSearchResultSize ) {
		this.maxSearchResultSize = maxSearchResultSize ;
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
