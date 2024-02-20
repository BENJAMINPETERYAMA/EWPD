/*
 * RetrieveBenefitDefinitionResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitDefinitionBO;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveBenefitDefinitionResponse extends WPDResponse {
	
	BenefitDefinitionBO benefitDefinitionBO;
	

	/**
	 * Returns the benefitDefinitionBO
	 * @return BenefitDefinitionBO benefitDefinitionBO.
	 */
	public BenefitDefinitionBO getBenefitDefinitionBO() {
		return benefitDefinitionBO;
	}
	/**
	 * Sets the benefitDefinitionBO
	 * @param benefitDefinitionBO.
	 */
	public void setBenefitDefinitionBO(BenefitDefinitionBO benefitDefinitionBO) {
		this.benefitDefinitionBO = benefitDefinitionBO;
	}
}
