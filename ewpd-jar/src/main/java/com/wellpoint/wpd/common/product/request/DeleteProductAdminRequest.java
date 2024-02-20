/*
 * DeleteProductBenefitComponentRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;


/**
 * @author U13541
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DeleteProductAdminRequest extends ProductRequest{

	  /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }
	
	/**
	 * @return Returns the adminKey.
	 */
	public int getAdminKey() {
		return adminKey;
	}
	/**
	 * @param adminKey The adminKey to set.
	 */
	public void setAdminKey(int adminKey) {
		this.adminKey = adminKey;
	}
    private int adminKey = 0;
    
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
    private int productId = 0;
	
   

}
