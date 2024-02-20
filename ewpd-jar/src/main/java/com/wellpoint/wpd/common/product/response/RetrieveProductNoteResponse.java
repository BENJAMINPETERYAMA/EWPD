/*
 * RetrieveProductNoteResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.response;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.product.bo.ProductComponentBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveProductNoteResponse extends WPDResponse {
    
    /**
     * @return productComponentBO
     * 
     * Returns the productComponentBO.
     */
    public ProductComponentBO getProductComponentBO() {
        return productComponentBO;
    }
    /**
     * @param productComponentBO
     * 
     * Sets the productComponentBO.
     */
    public void setProductComponentBO(ProductComponentBO productComponentBO) {
        this.productComponentBO = productComponentBO;
    }
    /**
     * @return productNotetList
     * 
     * Returns the productNotetList.
     */
    public List getProductNotetList() {
        return productNotetList;
    }
    /**
     * @param productNotetList
     * 
     * Sets the productNotetList.
     */
    public void setProductNotetList(List productNotetList) {
        this.productNotetList = productNotetList;
    }
    private ProductComponentBO productComponentBO;
	private List productNotetList = new ArrayList();

   
}
