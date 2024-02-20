/*
 * RetrieveProductBenefitComponentRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */


public class RetrieveContractProductAdminRequest extends ContractRequest{
	
	public static final int PRODUCT_ADMIN_ADDED = 1;
    
    public static final int PRODUCT_ADMIN_POPUP = 2;
    
    public static final int PRODUCT_ADMIN_RETRIEVE = 3;
    
    public static final int  CONTRACT_ADJUDICATION = 4;
    
    public static final int  MEMBER_SPEC_INDICATOR = 444;
    
    private int action;

    private int productKey;
    
    private String referenceId;
    
	/**
	 * @return Returns the adminId.
	 */
	public int getAdminId() {
		return adminId;
	}
	/**
	 * @param adminId The adminId to set.
	 */
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
    private int adminId;
	
   
	  /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
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
	 * Returns the productKey
	 * @return int productKey.
	 */
	public int getProductKey() {
		return productKey;
	}
	/**
	 * Sets the productKey
	 * @param productKey.
	 */
	public void setProductKey(int productKey) {
		this.productKey = productKey;
	}
	/**
	 * @return Returns the referenceId.
	 */
	public String getReferenceId() {
		return referenceId;
	}
	/**
	 * @param referenceId The referenceId to set.
	 */
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
}
