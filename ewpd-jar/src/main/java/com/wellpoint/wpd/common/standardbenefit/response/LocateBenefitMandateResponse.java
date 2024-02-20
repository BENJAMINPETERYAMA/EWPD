/*
 * Created on Mar 11, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.List;


/**
 * @author U13592
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LocateBenefitMandateResponse extends WPDResponse{
	
	List benefitMandateList;
	

	/**
	 * @return Returns the benefitMandateList.
	 */
	public List getBenefitMandateList() {
		return benefitMandateList;
	}
	/**
	 * @param benefitMandateList The benefitMandateList to set.
	 */
	public void setBenefitMandateList(List benefitMandateList) {
		this.benefitMandateList = benefitMandateList;
	}
}
