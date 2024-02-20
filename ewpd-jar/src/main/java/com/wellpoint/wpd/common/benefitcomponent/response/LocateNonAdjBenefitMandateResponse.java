/*
 * LocateNonAdjBenefitMandateResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.List;


/**
 * Response for Locate NonAdjudication Benefit Mandate.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LocateNonAdjBenefitMandateResponse extends WPDResponse{
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
