/*
 * Created on Aug 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.List;

/**
 * @author u13657
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveBenefitLinesResponse extends ContractResponse {

	private List benefitLinesList;
	
	
	
	/**
	 * @return Returns the benefitLinesList.
	 */
	public List getBenefitLinesList() {
		return benefitLinesList;
	}
	/**
	 * @param benefitLinesList The benefitLinesList to set.
	 */
	public void setBenefitLinesList(List benefitLinesList) {
		this.benefitLinesList = benefitLinesList;
	}
}
