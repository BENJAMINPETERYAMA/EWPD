/*
 * TreeBenefitDefinition.java
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
public class TreeBenefitDefinition {
	
	private int standardBenefitId;
	
	private int benefitDefinitionId;
	
	private  String effectiveDate;
	
	private String expiryDate;
	
	private String benefitDefinitionType;
	
	

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
	 * Returns the standardBenefitId
	 * @return int standardBenefitId.
	 */
	public int getStandardBenefitId() {
		return standardBenefitId;
	}
	/**
	 * Sets the standardBenefitId
	 * @param standardBenefitId.
	 */
	public void setStandardBenefitId(int standardBenefitId) {
		this.standardBenefitId = standardBenefitId;
	}
	/**
	 * Returns the benefitDefinitionType
	 * @return String benefitDefinitionType.
	 */
	public String getBenefitDefinitionType() {
		return benefitDefinitionType;
	}
	/**
	 * Sets the benefitDefinitionType
	 * @param benefitDefinitionType.
	 */
	public void setBenefitDefinitionType(String benefitDefinitionType) {
		this.benefitDefinitionType = benefitDefinitionType;
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
