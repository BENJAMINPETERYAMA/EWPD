/*
 * RetrieveProductFamilyRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveProductFamilyRequest extends ContractRequest {
    
    
    private int productId;
    
    /**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

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
}
