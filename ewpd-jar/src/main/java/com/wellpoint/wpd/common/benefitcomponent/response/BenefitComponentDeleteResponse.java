/*
 * BenefitComponentDeleteResponse.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.benefitcomponent.response;

import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.framework.response.WPDResponse;


/**
 * Response for Benefit Component Delete
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentDeleteResponse extends WPDResponse{
	private BenefitComponentBO benefitComponentBO;
	private boolean success;
	
	

	/**
	 * @return Returns the success.
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success The success to set.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return Returns the benefitComponentBO.
	 */
	public BenefitComponentBO getBenefitComponentBO() {
		return benefitComponentBO;
	}
	/**
	 * @param benefitComponentBO The benefitComponentBO to set.
	 */
	public void setBenefitComponentBO(BenefitComponentBO benefitComponentBO) {
		this.benefitComponentBO = benefitComponentBO;
	}
}
