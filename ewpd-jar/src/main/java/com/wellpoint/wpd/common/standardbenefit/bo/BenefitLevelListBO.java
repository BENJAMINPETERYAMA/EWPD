/*
 * Created on Mar 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

/**
 * @author u13592
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitLevelListBO {
	
	private int benefitLevelId;
	private int benefitsystemId;
	private String description;
	private String externalrefId;
	

	/**
	 * @return Returns the benefitLevelId.
	 */
	public int getBenefitLevelId() {
		return benefitLevelId;
	}
	/**
	 * @param benefitLevelId The benefitLevelId to set.
	 */
	public void setBenefitLevelId(int benefitLevelId) {
		this.benefitLevelId = benefitLevelId;
	}
	/**
	 * @return Returns the benefitsysId.
	 */
	public int getBenefitsystemId() {
		return benefitsystemId;
	}
	/**
	 * @param benefitsysId The benefitsysId to set.
	 */
	public void setBenefitsystemId(int benefitsysId) {
		this.benefitsystemId = benefitsysId;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	/**
	 * @return Returns the externalrefId.
	 */
	public String getExternalrefId() {
		return externalrefId;
	}
	/**
	 * @param externalrefId The externalrefId to set.
	 */
	public void setExternalrefId(String externalrefId) {
		this.externalrefId = externalrefId;
	}
}
