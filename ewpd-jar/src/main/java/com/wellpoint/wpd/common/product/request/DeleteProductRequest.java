/*
 * DeleteProductRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.request;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DeleteProductRequest extends ProductRequest {

	 private int productKey;
	 private int action;
	 
	 
	
	
	/**
	 * @return Returns the action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	/**
	 * @return Returns the productKey.
	 */
	public int getProductKey() {
		return productKey;
	}
	/**
	 * @param productKey The productKey to set.
	 */
	public void setProductKey(int productKey) {
		this.productKey = productKey;
	}
}
