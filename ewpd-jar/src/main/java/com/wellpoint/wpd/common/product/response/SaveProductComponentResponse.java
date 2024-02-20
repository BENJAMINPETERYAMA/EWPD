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

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.product.bo.ProductComponentBO;

import java.util.ArrayList;
import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveProductComponentResponse extends WPDResponse {
	 private boolean valid;
	 private boolean success;	
	 private ProductComponentBO productComponentBO;
	 private List productComponentList = new ArrayList();
	
	/**
	 * Returns the productComponentBO.
	 * @return ProductComponentBO productComponentBO.
	 */
	public ProductComponentBO getProductComponentBO() {
		return productComponentBO;
	}
	
	/**
	 * Sets the productComponentBO.
	 * @param productComponentBO. 
	 */
	public void setProductComponentBO(ProductComponentBO productComponentBO) {
		this.productComponentBO = productComponentBO;
	}
	
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
	
	/**
	 * Returns the productComponentList.
	 * @return List productComponentList.
	 */
	public List getProductComponentList() {
		return productComponentList;
	}
	
	/**
	 * Sets the productComponentList.
	 * @param productComponentList.
	 */
	public void setProductComponentList(List productComponentList) {
		this.productComponentList = productComponentList;
	}
}
