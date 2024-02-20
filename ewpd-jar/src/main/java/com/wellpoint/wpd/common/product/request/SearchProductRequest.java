/*
 * SearchProductRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.product.vo.ProductSearchCriteriaVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SearchProductRequest extends ProductRequest {

	private int action;
	private int productId;
	public static final int SEARCH_ALL_VERSION = 2;
	public static final int SEARCH_PRODUCT = 1;
	
	private ProductSearchCriteriaVO productSearchCriteriaVO;
	
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

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
	 * @return Returns the productSearchCriteriaVO.
	 */
	public ProductSearchCriteriaVO getProductSearchCriteriaVO() {
		return productSearchCriteriaVO;
	}
	/**
	 * @param productSearchCriteriaVO The productSearchCriteriaVO to set.
	 */
	public void setProductSearchCriteriaVO(
			ProductSearchCriteriaVO productSearchCriteriaVO) {
		this.productSearchCriteriaVO = productSearchCriteriaVO;
	}
	/**
	 * Returns the productId
	 * @return int productId.
	 */

	public int getProductId() {
		return productId;
	}
	/**
	 * Sets the productId
	 * @param productId.
	 */

	public void setProductId(int productId) {
		this.productId = productId;
	}
}
