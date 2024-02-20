/*
 * TreeBenefitAdministration.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.tree.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class TreeBenefitAdministration {
	
	private int benefitDefinitionId;
	
	private int benefitAdministrationId;
	
	private String effectiveDate;
	
	private String expiryDate;
	
	
	
	

	/**
	 * Returns the benefitAdministrationId
	 * @return int benefitAdministrationId.
	 */
	public int getBenefitAdministrationId() {
		return benefitAdministrationId;
	}
	/**
	 * Sets the benefitAdministrationId
	 * @param benefitAdministrationId.
	 */
	public void setBenefitAdministrationId(int benefitAdministrationId) {
		this.benefitAdministrationId = benefitAdministrationId;
	}
	/**
	 * Returns the benefitDefinitionId
	 * @return int benefitDefinitionId.
	 */
	public int getBenefitDefinitionId() {
		return benefitDefinitionId;
	}
	/**
	 * Sets the benefitDefinitionId
	 * @param benefitDefinitionId.
	 */
	public void setBenefitDefinitionId(int benefitDefinitionId) {
		this.benefitDefinitionId = benefitDefinitionId;
	}
	/**
	 * Returns the effectiveDate
	 * @return String effectiveDate.
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * Sets the effectiveDate
	 * @param effectiveDate.
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	/**
	 * Returns the expiryDate
	 * @return String expiryDate.
	 */
	public String getExpiryDate() {
		return expiryDate;
	}
	/**
	 * Sets the expiryDate
	 * @param expiryDate.
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
}
