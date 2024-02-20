/*
 * Created on Oct 14, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author u16223
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetreiveProductRuleTypeResponse extends WPDResponse{
	
	private List productRuleList;
	
	/**
	 * @return Returns the productRuleList.
	 */
	public List getProductRuleList() {
		return productRuleList;
	}
	/**
	 * @param productRuleList The productRuleList to set.
	 */
	public void setProductRuleList(List productRuleList) {
		this.productRuleList = productRuleList;
	}
}
