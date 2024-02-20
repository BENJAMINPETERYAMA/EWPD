/*
 * RetrieveLatestVersionsRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.request;

import com.wellpoint.wpd.business.productstructure.locatecriteria.ViewAllVersionsLocateCriteria;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveProductStructureVersionsRequest extends WPDRequest {

    private int productStructureId;

    private ViewAllVersionsLocateCriteria viewAllVersionsLocateCriteria;


    /**
     * Returns the productStructureId
     * 
     * @return int productStructureId.
     */

    public int getProductStructureId() {
        return productStructureId;
    }


    /**
     * Sets the productStructureId
     * 
     * @param productStructureId.
     */

    public void setProductStructureId(int productStructureId) {
        this.productStructureId = productStructureId;
    }


    /**
     * Returns the viewAllVersionsLocateCriteria
     * 
     * @return ViewAllVersionsLocateCriteria viewAllVersionsLocateCriteria.
     */

    public ViewAllVersionsLocateCriteria getViewAllVersionsLocateCriteria() {
        return viewAllVersionsLocateCriteria;
    }


    /**
     * Sets the viewAllVersionsLocateCriteria
     * 
     * @param viewAllVersionsLocateCriteria.
     */

    public void setViewAllVersionsLocateCriteria(
            ViewAllVersionsLocateCriteria viewAllVersionsLocateCriteria) {
        this.viewAllVersionsLocateCriteria = viewAllVersionsLocateCriteria;
    }


    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }

}