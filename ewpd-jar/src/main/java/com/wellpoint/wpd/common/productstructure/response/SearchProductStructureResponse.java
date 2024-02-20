/*
 * SearchProductStructureResponse.java
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
public class SearchProductStructureResponse extends WPDResponse {

    private List ProductStructureList;


    /**
     * Returns the productStructureList
     * 
     * @return List productStructureList.
     */

    public List getProductStructureList() {
        return ProductStructureList;
    }


    /**
     * Sets the productStructureList
     * 
     * @param productStructureList.
     */

    public void setProductStructureList(List productStructureList) {
        ProductStructureList = productStructureList;
    }
}