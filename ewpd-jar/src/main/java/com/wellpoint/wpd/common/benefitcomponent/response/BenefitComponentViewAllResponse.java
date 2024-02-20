/*
 * BenefitComponentViewAllResponse.java
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
 * Response FOr Benefit Component View All Versions
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentViewAllResponse extends WPDResponse {

	private List benefitComponentViewAllList;
	/**
	 * @return Returns the benefitComponentViewAllList.
	 */
	public List getBenefitComponentViewAllList() {
		return benefitComponentViewAllList;
	}
	/**
	 * @param benefitComponentViewAllList The benefitComponentViewAllList to set.
	 */
	public void setBenefitComponentViewAllList(List benefitComponentViewAllList) {
		this.benefitComponentViewAllList = benefitComponentViewAllList;
	}
}
