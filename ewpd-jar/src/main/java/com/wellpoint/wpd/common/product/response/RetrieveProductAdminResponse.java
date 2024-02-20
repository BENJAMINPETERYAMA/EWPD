/*
 * RetrieveProductNoteResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.response;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.product.bo.ProductAdminBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveProductAdminResponse extends WPDResponse {
	private ProductAdminBO productAdminBO;
	
	private List productAdminList = new ArrayList();
	
	private List adminList;
    /**
     * @return productAdminBO
     * 
     * Returns the productAdminBO.
     */
    public ProductAdminBO getProductAdminBO() {
        return productAdminBO;
    }
    /**
     * @param productAdminBO
     * 
     * Sets the productAdminBO.
     */
    public void setProductAdminBO(ProductAdminBO productAdminBO) {
        this.productAdminBO = productAdminBO;
    }
    /**
     * @return productAdminList
     * 
     * Returns the productAdminList.
     */
    public List getProductAdminList() {
        return productAdminList;
    }
    /**
     * @param productAdminList
     * 
     * Sets the productAdminList.
     */
    public void setProductAdminList(List productAdminList) {
        this.productAdminList = productAdminList;
    }
    
	/**
	 * @return Returns the adminList.
	 */
	public List getAdminList() {
		return adminList;
	}
	/**
	 * @param adminList The adminList to set.
	 */
	public void setAdminList(List adminList) {
		this.adminList = adminList;
	}
}
