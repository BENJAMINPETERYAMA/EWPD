/*
 * BenefitRetrieveResponse.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.benefitcomponent.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.List;


/**
 * Response for Benefit Retrieve. 
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitRetrieveResponse extends WPDResponse{
	private List benefits;
	/**
	 * @return Returns the benefits.
	 */
	public List getBenefits() {
		return benefits;
	}
	/**
	 * @param benefits The benefits to set.
	 */
	public void setBenefits(List benefits) {
		this.benefits = benefits;
	}
	
}
