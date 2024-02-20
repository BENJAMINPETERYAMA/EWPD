/*
 * LocateProductResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LocateProductResponse extends ContractResponse {
    private List products;
    /**
     * Returns the products
     * @return List products.
     */
    public List getProducts() {
        return products;
    }
    /**
     * Sets the products
     * @param products.
     */
    public void setProducts(List products) {
        this.products = products;
    }
}
