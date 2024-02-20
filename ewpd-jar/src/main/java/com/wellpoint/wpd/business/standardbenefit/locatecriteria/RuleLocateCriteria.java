/*
 * RuleLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.standardbenefit.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RuleLocateCriteria extends LocateCriteria{
	
	private int benefitKey;
	
	private String searchRuleID;
	
	private String searchString;

	/**
	 * Returns the benefitKey
	 * @return int benefitKey.
	 */
	public int getBenefitKey() {
		return benefitKey;
	}
	/**
	 * Sets the benefitKey
	 * @param benefitKey.
	 */
	public void setBenefitKey(int benefitKey) {
		this.benefitKey = benefitKey;
	}

	
	/**
	 * @return Returns the searchRuleID.
	 */
	public String getSearchRuleID() {
		return searchRuleID;
	}
	/**
	 * @param searchRuleID The searchRuleID to set.
	 */
	public void setSearchRuleID(String searchRuleID) {
		this.searchRuleID = searchRuleID;
	}
	/**
	 * @return Returns the searchString.
	 */
	public String getSearchString() {
		return searchString;
	}
	/**
	 * @param searchString The searchString to set.
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
}
