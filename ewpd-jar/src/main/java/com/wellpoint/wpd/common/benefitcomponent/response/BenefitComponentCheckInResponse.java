/*
 * BenefitComponentCheckInResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.response;

import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.framework.response.WPDResponse;


/**
 * Rsponse for Benefit Component Response 
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentCheckInResponse extends WPDResponse{
	
	private BenefitComponentBO BenefitComponentBO;
	
	private boolean  isValidationSuccess ;
	
	

	/**
	 * Returns the benefitComponentBO
	 * @return BenefitComponentBO benefitComponentBO.
	 */
	public BenefitComponentBO getBenefitComponentBO() {
		return BenefitComponentBO;
	}
	/**
	 * Sets the benefitComponentBO
	 * @param benefitComponentBO.
	 */
	public void setBenefitComponentBO(BenefitComponentBO benefitComponentBO) {
		BenefitComponentBO = benefitComponentBO;
	}
	/**
	 * Returns the isValidationSuccess
	 * @return boolean isValidationSuccess.
	 */
	public boolean isValidationSuccess() {
		return isValidationSuccess;
	}
	/**
	 * Sets the isValidationSuccess
	 * @param isValidationSuccess.
	 */
	public void setValidationSuccess(boolean isValidationSuccess) {
		this.isValidationSuccess = isValidationSuccess;
	}
}
