/*
 * BenefitComponentViewRequest.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.benefitcomponent.request;

import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitComponentVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * Request for View Benefit Component.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentViewRequest extends WPDRequest{

	private BenefitComponentVO benefitComponentVO;
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return Returns the benefitComponentVO.
	 */
	public BenefitComponentVO getBenefitComponentVO() {
		return benefitComponentVO;
	}
	/**
	 * @param benefitComponentVO The benefitComponentVO to set.
	 */
	public void setBenefitComponentVO(BenefitComponentVO benefitComponentVO) {
		this.benefitComponentVO = benefitComponentVO;
	}
}
