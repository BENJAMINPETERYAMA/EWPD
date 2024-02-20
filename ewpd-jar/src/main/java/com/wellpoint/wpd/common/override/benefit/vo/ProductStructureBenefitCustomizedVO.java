/*
 * Created on Nov 30, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.override.benefit.vo;

public class ProductStructureBenefitCustomizedVO {

	private int benefitLineId;
	
	private String benefitLineHideFlag;
	
	private String benefitLevelHideFlag;
	
    private String overridedValue;
    
    private int benefitCustomizedSysId;
    
    private boolean modified = false;
    //CARS START
    //Created to hold level description value.
    private String benefitLevelDescription;
    //Created to hold level frequency value.
    private int benefitLevelFrequency;
    //CARS END
	/**
	 * @return Returns the benefitLineHideFlag.
	 */
	public String getBenefitLineHideFlag() {
		return benefitLineHideFlag;
	}
	/**
	 * @param benefitLineHideFlag The benefitLineHideFlag to set.
	 */
	public void setBenefitLineHideFlag(String benefitLineHideFlag) {
		this.benefitLineHideFlag = benefitLineHideFlag;
	}
	/**
	 * @return Returns the benefitLineId.
	 */
	public int getBenefitLineId() {
		return benefitLineId;
	}
	/**
	 * @param benefitLineId The benefitLineId to set.
	 */
	public void setBenefitLineId(int benefitLineId) {
		this.benefitLineId = benefitLineId;
	}
	/**
	 * @return Returns the overridedValue.
	 */
	public String getOverridedValue() {
		return overridedValue;
	}
	/**
	 * @param overridedValue The overridedValue to set.
	 */
	public void setOverridedValue(String overridedValue) {
		this.overridedValue = overridedValue;
	}
	/**
	 * @return Returns the benefitLevelHideFlag.
	 */
	public String getBenefitLevelHideFlag() {
		return benefitLevelHideFlag;
	}
	/**
	 * @param benefitLevelHideFlag The benefitLevelHideFlag to set.
	 */
	public void setBenefitLevelHideFlag(String benefitLevelHideFlag) {
		this.benefitLevelHideFlag = benefitLevelHideFlag;
	}
	/**
	 * @return Returns the benefitCustomizedSysId.
	 */
	public int getBenefitCustomizedSysId() {
		return benefitCustomizedSysId;
	}
	/**
	 * @param benefitCustomizedSysId The benefitCustomizedSysId to set.
	 */
	public void setBenefitCustomizedSysId(int benefitCustomizedSysId) {
		this.benefitCustomizedSysId = benefitCustomizedSysId;
	}
	/**
	 * @return Returns the modified.
	 */
	public boolean isModified() {
		return modified;
	}
	/**
	 * @param modified The modified to set.
	 */
	public void setModified(boolean modified) {
		this.modified = modified;
	}
	/**
	 * @return Returns the benefitLevelDescription.
	 */
	public String getBenefitLevelDescription() {
		return benefitLevelDescription;
	}
	/**
	 * @param benefitLevelDescription The benefitLevelDescription to set.
	 */
	public void setBenefitLevelDescription(String benefitLevelDescription) {
		this.benefitLevelDescription = benefitLevelDescription;
	}
	/**
	 * @return Returns the benefitLevelFrequency.
	 */
	public int getBenefitLevelFrequency() {
		return benefitLevelFrequency;
	}
	/**
	 * @param benefitLevelFrequency The benefitLevelFrequency to set.
	 */
	public void setBenefitLevelFrequency(int benefitLevelFrequency) {
		this.benefitLevelFrequency = benefitLevelFrequency;
	}
}
