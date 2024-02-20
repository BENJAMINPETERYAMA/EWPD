/*
 * BenefitComponentSearchResponse.java
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
 * Class for benefit component search response
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentSearchResponse extends WPDResponse {

	private List benefitComponentList;

	/**
	 * Constructor
	 */
	public BenefitComponentSearchResponse() {
		super();
	}

	/**
	 * @return Returns the benefitComponentList.
	 */
	public List getBenefitComponentList() {
		return benefitComponentList;
	}

	/**
	 * @param benefitComponentList
	 *            The benefitComponentList to set.
	 */
	public void setBenefitComponentList(List benefitComponentList) {
		this.benefitComponentList = benefitComponentList;
	}
}