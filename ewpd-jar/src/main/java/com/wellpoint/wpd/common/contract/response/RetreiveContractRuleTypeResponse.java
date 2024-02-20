/*
 * Created on Oct 16, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author u16223
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetreiveContractRuleTypeResponse extends WPDResponse {
	
	private List contractRuleList;

	/**
	 * @return Returns the contractRuleList.
	 */
	public List getContractRuleList() {
		return contractRuleList;
	}
	/**
	 * @param contractRuleList The contractRuleList to set.
	 */
	public void setContractRuleList(List contractRuleList) {
		this.contractRuleList = contractRuleList;
	}
}
