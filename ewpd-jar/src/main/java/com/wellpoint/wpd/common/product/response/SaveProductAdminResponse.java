/*
 * SaveProductComponentResponse.java
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
public class SaveProductAdminResponse extends WPDResponse {
	 private boolean valid;
	 private boolean success;	
	/**
	 * @return Returns the productAdminBO.
	 */
	public ProductAdminBO getProductAdminBO() {
		return productAdminBO;
	}
	/**
	 * @param productAdminBO The productAdminBO to set.
	 */
	public void setProductAdminBO(ProductAdminBO productAdminBO) {
		this.productAdminBO = productAdminBO;
	}
	/**
	 * @return Returns the productAdminList.
	 */
	public List getProductAdminList() {
		return productAdminList;
	}
	/**
	 * @param productAdminList The productAdminList to set.
	 */
	public void setProductAdminList(List productAdminList) {
		this.productAdminList = productAdminList;
	}
	 private ProductAdminBO productAdminBO;
	 private List productAdminList = new ArrayList();
	
	
	
	/**
	 * Returns the success.
	 * @return boolean success.
	 */
	public boolean isSuccess() {
		return success;
	}
	
	/**
	 * Sets the success.
	 * @param success.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	/**
	 * Returns the valid.
	 * @return boolean valid.
	 */
	public boolean isValid() {
		return valid;
	}
	
	/**
	 * Sets the valid.
	 * @param valid. 
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	
}
