/*
 * ProductSearchCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.bo;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductSearchCriteria {

	private String productName;

	private String productDesc;
	
	/**
	 * @return Returns the productDesc.
	 */
	public String getProductDesc() {
		return productDesc;
	}
	/**
	 * @param productDesc The productDesc to set.
	 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	
	/**
	 * @return Returns the productName.
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName The productName to set.
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	 /**
     * @see java.lang.Object#toString()
     * @return
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("productName = ").append(productName);
        buffer.append(", productDesc = ").append(productDesc);
        return buffer.toString();
    }
}
