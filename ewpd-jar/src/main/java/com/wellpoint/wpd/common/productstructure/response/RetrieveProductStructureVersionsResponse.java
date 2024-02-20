/*
 * RetrieveLatestVersionsResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.product.bo.ProductStructureBO;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveProductStructureVersionsResponse extends WPDResponse {

    private List versionList;

    private ProductStructureBO productStructureBO;


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
     * Returns the versionList
     * 
     * @return List versionList.
     */

    public List getVersionList() {
        return versionList;
    }


    /**
     * Sets the versionList
     * 
     * @param versionList.
     */

    public void setVersionList(List versionList) {
        this.versionList = versionList;
    }
}