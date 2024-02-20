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

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureLifeCycleResponse extends WPDResponse {

    private List ProductStructureList;

    boolean success = true;


    //Constructor
    public ProductStructureLifeCycleResponse() {
        super();
    }


    /**
     * @return Returns the productStructureList.
     */
    public List getProductStructureList() {
        return ProductStructureList;
    }


    /**
     * @param productStructureList
     *            The productStructureList to set.
     */
    public void setProductStructureList(List productStructureList) {
        ProductStructureList = productStructureList;
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
     * @param success
     *            The success to set.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
}