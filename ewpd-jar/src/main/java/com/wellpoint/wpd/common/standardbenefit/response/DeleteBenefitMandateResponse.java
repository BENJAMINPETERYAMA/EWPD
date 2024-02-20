/*
 * Created on Mar 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitMandateBO;


/**
 * @author u13592
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DeleteBenefitMandateResponse extends WPDResponse{
	
	private BenefitMandateBO benefitMandateBO;
	
	

	/**
	 * @return Returns the benefitMandateBO.
	 */
	public BenefitMandateBO getBenefitMandateBO() {
		return benefitMandateBO;
	}
	/**
	 * @param benefitMandateBO The benefitMandateBO to set.
	 */
	public void setBenefitMandateBO(BenefitMandateBO benefitMandateBO) {
		this.benefitMandateBO = benefitMandateBO;
	}
}
