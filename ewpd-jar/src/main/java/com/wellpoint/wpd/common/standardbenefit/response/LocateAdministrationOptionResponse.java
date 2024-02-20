/*
 * LocateAdministrationOptionResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LocateAdministrationOptionResponse extends WPDResponse {
	private List associatedBenefitAdministrationOptionList;
	
	private int hiddenAdminOptionCount;
	

	/**
	 * Returns the associatedBenefitAdministrationOptionList
	 * @return List associatedBenefitAdministrationOptionList.
	 */
	public List getAssociatedBenefitAdministrationOptionList() {
		return associatedBenefitAdministrationOptionList;
	}
	/**
	 * Sets the associatedBenefitAdministrationOptionList
	 * @param associatedBenefitAdministrationOptionList.
	 */
	public void setAssociatedBenefitAdministrationOptionList(
			List associatedBenefitAdministrationOptionList) {
		this.associatedBenefitAdministrationOptionList = associatedBenefitAdministrationOptionList;
	}
	/**
	 * @return Returns the hiddenAdminOptionCount.
	 */
	public int getHiddenAdminOptionCount() {
		return hiddenAdminOptionCount;
	}
	/**
	 * @param hiddenAdminOptionCount The hiddenAdminOptionCount to set.
	 */
	public void setHiddenAdminOptionCount(int hiddenAdminOptionCount) {
		this.hiddenAdminOptionCount = hiddenAdminOptionCount;
	}
}
