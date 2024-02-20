/*
 * Created on Nov 29, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.List;

/**
 * @author U14609
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetreiveBenefitsContractResponse extends ContractResponse{
	
	private List BenefitList;
	

	/**
	 * @return Returns the benefitList.
	 */
	public List getBenefitList() {
		return BenefitList;
	}
	/**
	 * @param benefitList The benefitList to set.
	 */
	public void setBenefitList(List benefitList) {
		BenefitList = benefitList;
	}
}
