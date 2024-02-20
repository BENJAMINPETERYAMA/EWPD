/*
 * RetrieveProductStructureResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.response;

import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveProductStructureResponse extends WPDResponse {

    private ProductStructureBO productStructureBO;

    boolean success = true;

    private DomainDetail domainDetail = null;
    
    private boolean lockAquired =true;


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
     * @return Returns the success.
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


    /**
     * Returns the domainDetail
     * 
     * @return DomainDetail domainDetail.
     */

    public DomainDetail getDomainDetail() {
        return domainDetail;
    }


    /**
     * Sets the domainDetail
     * 
     * @param domainDetail.
     */

    public void setDomainDetail(DomainDetail domainDetail) {
        this.domainDetail = domainDetail;
    }
	/**
	 * @return Returns the isLockAquired.
	 */
	public boolean isLockAquired() {
		return lockAquired;
	}
	/**
	 * @param isLockAquired The isLockAquired to set.
	 */
	public void setLockAquired(boolean lockAquired) {
		this.lockAquired = lockAquired;
	}
}