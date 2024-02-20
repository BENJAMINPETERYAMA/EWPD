/*
 * RetrieveProductResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.product.bo.ProductBO;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveContractProductResponse extends WPDResponse {
    
    private ProductBO productBO;
    private DomainDetail domainDetail;
    private boolean success;

    /**
     * Returns the productBO
     * @return ProductBO productBO.
     */
    public ProductBO getProductBO() {
        return productBO;
    }
    /**
     * Sets the productBO
     * @param productBO.
     */
    public void setProductBO(ProductBO productBO) {
        this.productBO = productBO;
    }
    /**
     * Returns the domainDetail
     * @return DomainDetail domainDetail.
     */
    public DomainDetail getDomainDetail() {
        return domainDetail;
    }
    /**
     * Sets the domainDetail
     * @param domainDetail.
     */
    public void setDomainDetail(DomainDetail domainDetail) {
        this.domainDetail = domainDetail;
    }
    /**
     * Returns the success
     * @return boolean success.
     */
    public boolean isSuccess() {
        return success;
    }
    /**
     * Sets the success
     * @param success.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
