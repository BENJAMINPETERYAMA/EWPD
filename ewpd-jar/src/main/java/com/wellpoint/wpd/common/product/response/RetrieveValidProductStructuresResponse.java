/*
 * RetrieveValidProductStructuresResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveValidProductStructuresResponse extends WPDResponse {
    private List validProductStructureList;
    
    /**
     * Returns the validProductStructureList
     * @return List validProductStructureList.
     */
    public List getValidProductStructureList() {
        return validProductStructureList;
    }
    
    /**
     * Sets the validProductStructureList
     * @param validProductStructureList.
     */
    public void setValidProductStructureList(List validProductStructureList) {
        this.validProductStructureList = validProductStructureList;
    }
}
