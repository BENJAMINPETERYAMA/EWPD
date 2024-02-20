/*
 * SaveProductBenefitAdministrationResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveProductBenefitAdministrationResponse extends WPDResponse {
    
    private List productAdminList;
    
    


    /**
     * Returns the productAdminList
     * @return List productAdminList.
     */
    public List getProductAdminList() {
        return productAdminList;
    }
    /**
     * Sets the productAdminList
     * @param productAdminList.
     */
    public void setProductAdminList(List productAdminList) {
        this.productAdminList = productAdminList;
    }
}
