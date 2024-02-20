/*
 * DeleteProductStructureResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBO;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DeleteProductStructureResponse extends WPDResponse {

    private ProductStructureBO productStructureBO;

    private List productStructureList;

    boolean success;


    /**
     * @return Returns the productStructureList.
     */
    public List getProductStructureList() {
        return productStructureList;
    }


    /**
     * @param productStructureList
     *            The productStructureList to set.
     */
    public void setProductStructureList(List productStructureList) {
        this.productStructureList = productStructureList;
    }


    /**
     * Returns the productStructureBO
     * 
     * @return ProductStructureBO productStructureBO.
     */

    public ProductStructureBO getProductStructureBO() {
        return productStructureBO;
    }


    /**
     * Sets the productStructureBO
     * 
     * @param productStructureBO.
     */

    public void setProductStructureBO(ProductStructureBO productStructureBO) {
        this.productStructureBO = productStructureBO;
    }


    /**
     * Returns the success
     * 
     * @return boolean success.
     */

    public boolean isSuccess() {
        return success;
    }


    /**
     * Sets the success
     * 
     * @param success.
     */

    public void setSuccess(boolean success) {
        this.success = success;
    }
}