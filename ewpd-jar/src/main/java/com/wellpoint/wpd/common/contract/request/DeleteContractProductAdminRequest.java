/*
 * DeleteContractProductAdminRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DeleteContractProductAdminRequest extends ContractRequest {

    private List adminKey = null;
    private int productId = 0;
	
	/**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}
//	--------------------------------- getters/setters -----------------------	

	/**
	 * Returns the productId
	 * @return int productId.
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * Sets the productId
	 * @param productId.
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	/**
	 * @return Returns the adminKey.
	 */
	public List getAdminKey() {
		return adminKey;
	}
	/**
	 * @param adminKey The adminKey to set.
	 */
	public void setAdminKey(List adminKey) {
		this.adminKey = adminKey;
	}
}
