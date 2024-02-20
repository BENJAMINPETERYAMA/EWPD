/*
 * SearchProductResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.product.bo.ProductSearchCriteria;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SearchProductResponse extends WPDResponse {
	
	 private boolean valid;
	 private boolean success;
	 
	 private List productList;
	 private List allProductVersionsList;
	 
	 private ProductSearchCriteria productSearchCriteria;   
	/**
	 * Returns the success.
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
	/**
	 * Returns the valid.
	 * @return boolean valid.
	 */
	public boolean isValid() {
		return valid;
	}
	/**
	 * Sets the valid
	 * @param valid.
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	/**
	 * Returns the productList.
	 * @return List productList.
	 */
	public List getProductList() {
		return productList;
	}
	/**
	 * Sets the productList.
	 * @param productList.
	 */
	public void setProductList(List productList) {
		this.productList = productList;
	}
	/**
	 * Returns the productSearchCriteria.
	 * @return ProductSearchCriteria productSearchCriteria.
	 */
	public ProductSearchCriteria getProductSearchCriteria() {
		return productSearchCriteria;
	}
	/**
	 * Sets the productSearchCriteria.
	 * @param productSearchCriteria.
	 */
	public void setProductSearchCriteria(
			ProductSearchCriteria productSearchCriteria) {
		this.productSearchCriteria = productSearchCriteria;
	}
	/**
	 * Returns the allProductVersionsList
	 * @return List allProductVersionsList.
	 */

	public List getAllProductVersionsList() {
		return allProductVersionsList;
	}
	/**
	 * Sets the allProductVersionsList
	 * @param allProductVersionsList.
	 */

	public void setAllProductVersionsList(List allProductVersionsList) {
		this.allProductVersionsList = allProductVersionsList;
	}
}
