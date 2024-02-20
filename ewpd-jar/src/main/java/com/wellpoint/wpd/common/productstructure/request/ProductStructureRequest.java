/*
 * ProductStructureRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.web.productstructure.ProductStructureSessionObject;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureRequest extends WPDRequest {

    private ProductStructureSessionObject productStructureSessionObject;


    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }


    /**
     * Returns the productStructureSessionObject
     * 
     * @return ProductStructureSessionObject productStructureSessionObject.
     */

    public ProductStructureSessionObject getProductStructureSessionObject() {
        return productStructureSessionObject;
    }


    /**
     * Sets the productStructureSessionObject
     * 
     * @param productStructureSessionObject.
     */

    public void setProductStructureSessionObject(
            ProductStructureSessionObject productStructureSessionObject) {
        this.productStructureSessionObject = productStructureSessionObject;
    }
}