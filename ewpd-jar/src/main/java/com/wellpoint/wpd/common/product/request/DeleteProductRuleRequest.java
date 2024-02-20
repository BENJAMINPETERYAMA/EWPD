/*
 * DeleteProductRuleRequest.java
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
public class DeleteProductRuleRequest extends ProductRequest {

	private int productRuleSysID;
 	private String ewpdGenRuleID;

//	--------------------------------- getters/setters -----------------------	
	/**
	 * Returns the productRuleSysID
	 * @return int productRuleSysID.
	 */
	public int getProductRuleSysID() {
		return productRuleSysID;
	}
	/**
	 * Sets the productRuleSysID
	 * @param productRuleSysID.
	 */
	public void setProductRuleSysID(int productRuleSysID) {
		this.productRuleSysID = productRuleSysID;
	}
	/**
	 * Returns the ewpdGenRuleID
	 * @return String ewpdGenRuleID.
	 */
	public String getEwpdGenRuleID() {
		return ewpdGenRuleID;
	}
	/**
	 * Sets the ewpdGenRuleID
	 * @param ewpdGenRuleID.
	 */
	public void setEwpdGenRuleID(String ewpdGenRuleID) {
		this.ewpdGenRuleID = ewpdGenRuleID;
	}
}
