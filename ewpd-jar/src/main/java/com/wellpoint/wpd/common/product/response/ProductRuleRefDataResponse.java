/*
 * ProductRuleRefDataResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductRuleRefDataResponse extends WPDResponse {

	List ruleList;
	int ruleCount;
	/**
	 * @return Returns the pageNum.
	 */
	public int getPageNum() {
		return pageNum;
	}
	/**
	 * @param pageNum The pageNum to set.
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	int pageNum;
	
	/**
	 * Returns the ruleList
	 * @return List ruleList.
	 */
	public List getRuleList() {
		return ruleList;
	}
	/**
	 * Sets the ruleList
	 * @param ruleList.
	 */
	public void setRuleList(List ruleList) {
		this.ruleList = ruleList;
	}
	/**
	 * @return Returns the ruleCount.
	 */
	public int getRuleCount() {
		return ruleCount;
	}
	/**
	 * @param ruleCount The ruleCount to set.
	 */
	public void setRuleCount(int ruleCount) {
		this.ruleCount = ruleCount;
	}

}
