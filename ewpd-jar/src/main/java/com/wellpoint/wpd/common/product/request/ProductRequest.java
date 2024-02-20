/*
 * ProductRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.product.vo.ProductKeyObject;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductRequest extends WPDRequest {
    
    private ProductKeyObject productKeyObject;

    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }

    /**
     * Returns the productKeyObject
     * @return ProductKeyObject productKeyObject.
     */
    public ProductKeyObject getProductKeyObject() {
        return productKeyObject;
    }
    
    /**
     * Sets the productKeyObject
     * @param productKeyObject.
     */
    public void setProductKeyObject(ProductKeyObject productKeyObject) {
        this.productKeyObject = productKeyObject;
    }
}
