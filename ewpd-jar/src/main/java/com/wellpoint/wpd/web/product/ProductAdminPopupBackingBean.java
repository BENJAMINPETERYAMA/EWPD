/*
 * ProductComponentPopupBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.product;

import java.util.List;

import com.wellpoint.wpd.common.product.request.RetrieveProductAdminRequest;
import com.wellpoint.wpd.common.product.response.ProductAdminResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 *         j
 * @version $Id: $
 */
public class ProductAdminPopupBackingBean extends ProductBackingBean {

    private List productAdminList;

    private boolean adminRetrieved;


    /**
     * Returns the productAdmin
     * 
     * @return List productAdmin.
     */

    public List getProductAdminList() {
        if (!adminRetrieved) {
            RetrieveProductAdminRequest productAdminRequest = new RetrieveProductAdminRequest();
            productAdminRequest
                    .setAction(RetrieveProductAdminRequest.PRODUCT_ADMIN_POPUP);
            ProductAdminResponse productAdminResponse = (ProductAdminResponse) executeService(productAdminRequest);
            if (null != productAdminResponse) {
                this.productAdminList = productAdminResponse.getAdminList();
                adminRetrieved = true;
            }
        }
        return this.productAdminList;
    }


    /**
     * @param productAdminList
     *            The productAdminList to set.
     */
    public void setProductAdminList(List productAdminList) {
        this.productAdminList = productAdminList;
    }
}