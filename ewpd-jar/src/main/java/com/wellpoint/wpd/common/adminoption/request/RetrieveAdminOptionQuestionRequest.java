/*
 * RetrieveAdminOptionQuestionRequest.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.adminoption.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * Request for Retrieving Admin Option Question
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class RetrieveAdminOptionQuestionRequest extends WPDRequest {

    private int adminOptionId;
    
    private int adminLevelOptSysId;
    
    private int action;
    
    private int productId;
    
    private int contractSysId;
    /**
     * Method to validate the request Returns the void
     * 
     * @return void.
     */
    public void validate() throws ValidationException {
        if (-1 == this.getAdminOptionId())
            throw new ValidationException("Admin Option Id is missing", null,
                    null);

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
	/**
	 * @return Returns the adminLevelOptSysId.
	 */
	public int getAdminLevelOptSysId() {
		return adminLevelOptSysId;
	}
	/**
	 * @param adminLevelOptSysId The adminLevelOptSysId to set.
	 */
	public void setAdminLevelOptSysId(int adminLevelOptSysId) {
		this.adminLevelOptSysId = adminLevelOptSysId;
	}
	/**
	 * @return Returns the action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	/**
	 * @return Returns the productId.
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param productId The productId to set.
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * @return Returns the contractSysId.
	 */
	public int getContractSysId() {
		return contractSysId;
	}
	/**
	 * @param contractSysId The contractSysId to set.
	 */
	public void setContractSysId(int contractSysId) {
		this.contractSysId = contractSysId;
	}
}