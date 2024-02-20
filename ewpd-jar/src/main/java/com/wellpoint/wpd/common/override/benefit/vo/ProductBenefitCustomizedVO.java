/*
 * Created on Nov 27, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.override.benefit.vo;

import com.wellpoint.wpd.web.util.WebConstants;


public class ProductBenefitCustomizedVO {

	private int benefitLineId;
	
	private String benefitLineHideFlag;
	
	private String benefitLevelHideFlag;
	
    private String overridedValue = WebConstants.EMPTY_STRING;
    
    private int benefitCustomizedSysId;
    
    private boolean benefitUpdateFlag;
    //CARS START
    //DESCRIPTION : String for frequency.
    private String overridedFreqValue = WebConstants.EMPTY_STRING;
    //DESCRIPTION : String for level description.
    private String overridedLvlDescValue = WebConstants.EMPTY_STRING;
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
	 * @return Returns the benefitUpdateFlag.
	 */
	public boolean isBenefitUpdateFlag() {
		return benefitUpdateFlag;
	}
	/**
	 * @param benefitUpdateFlag The benefitUpdateFlag to set.
	 */
	public void setBenefitUpdateFlag(boolean benefitUpdateFlag) {
		this.benefitUpdateFlag = benefitUpdateFlag;
	}
	/**
	 * @return Returns the overridedFreqValue.
	 */
	public String getOverridedFreqValue() {
		return overridedFreqValue;
	}
	/**
	 * @param overridedFreqValue The overridedFreqValue to set.
	 */
	public void setOverridedFreqValue(String overridedFreqValue) {
		this.overridedFreqValue = overridedFreqValue;
	}
	/**
	 * @return Returns the overridedLvlDescValue.
	 */
	public String getOverridedLvlDescValue() {
		return overridedLvlDescValue;
	}
	/**
	 * @param overridedLvlDescValue The overridedLvlDescValue to set.
	 */
	public void setOverridedLvlDescValue(String overridedLvlDescValue) {
		this.overridedLvlDescValue = overridedLvlDescValue;
	}
}
