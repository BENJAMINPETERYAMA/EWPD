/*
 * BenefitComponentSearchRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.request;

import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitComponentLocateCriteriaVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * Class for benefit component search request
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentSearchRequest extends WPDRequest {

	private BenefitComponentLocateCriteriaVO benefitComponentLocateCriteriaVO;

	/**
	 *  
	 */
	public BenefitComponentSearchRequest() {
		super();
	}

	/**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {

	}

	/**
	 * @return Returns the benefitComponentLocateCriteriaVO.
	 */
	public BenefitComponentLocateCriteriaVO getBenefitComponentLocateCriteriaVO() {
		return benefitComponentLocateCriteriaVO;
	}

	/**
	 * @param benefitComponentLocateCriteriaVO
	 *            The benefitComponentLocateCriteriaVO to set.
	 */
	public void setBenefitComponentLocateCriteriaVO(
			BenefitComponentLocateCriteriaVO benefitComponentLocateCriteriaVO) {
		this.benefitComponentLocateCriteriaVO = benefitComponentLocateCriteriaVO;
	}
}