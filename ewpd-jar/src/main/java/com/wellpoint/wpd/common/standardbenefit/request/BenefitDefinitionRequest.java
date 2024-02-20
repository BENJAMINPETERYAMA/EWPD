/*
 * BenefitDefinitionRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.standardbenefit.vo.BenefitDefinitionVO;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitDefinitionRequest extends WPDRequest {

	private BenefitDefinitionVO benefitDefinitionVO;
	/**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Returns the benefitDefinitionVO
	 * @return BenefitDefinitionVO benefitDefinitionVO.
	 */
	public BenefitDefinitionVO getBenefitDefinitionVO() {
		return benefitDefinitionVO;
	}
	/**
	 * Sets the benefitDefinitionVO
	 * @param benefitDefinitionVO.
	 */
	public void setBenefitDefinitionVO(BenefitDefinitionVO benefitDefinitionVO) {
		this.benefitDefinitionVO = benefitDefinitionVO;
	}
}
