/*
 * LocateNonAdjBenefitMandateRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * Requst for Locate Non Adjudication Benefit Mandate.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LocateNonAdjBenefitMandateRequest extends WPDRequest {
	private int benefitSystemId;
	/**
	 * @return Returns the benefitSystemId.
	 */
	public int getBenefitSystemId() {
		return benefitSystemId;
	}
	/**
	 * @param benefitSystemId The benefitSystemId to set.
	 */
	public void setBenefitSystemId(int benefitSystemId) {
		this.benefitSystemId = benefitSystemId;
	}
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
}
