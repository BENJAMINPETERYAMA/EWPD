/*
 * Created on Jul 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.bo;


/**
 * @author U13541
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RuleIdBO {
	
	private int benefitId;
	
	private String primaryCode ;

	private String benefitMeaning;
	
	private String ruleDesc;
	
	private int benefitComponentId;
	
	private int dateSegmentId;
	
	private String secondaryCode;
	
	private String strRuleType;
	
	/**
	 * @return Returns the strRuleType.
	 */
	public String getStrRuleType() {
		return strRuleType;
	}
	/**
	 * @param strRuleType The strRuleType to set.
	 */
	public void setStrRuleType(String strRuleType) {
		this.strRuleType = strRuleType;
	}
	/**
	 * @return Returns the secondaryCode.
	 */
	public String getSecondaryCode() {
		return secondaryCode;
	}
	/**
	 * @param secondaryCode The secondaryCode to set.
	 */
	public void setSecondaryCode(String secondaryCode) {
		this.secondaryCode = secondaryCode;
	}
	/**
	 * @return Returns the benefitId.
	 */
	public int getBenefitId() {
		return benefitId;
	}
	/**
	 * @param benefitId The benefitId to set.
	 */
	public void setBenefitId(int benefitId) {
		this.benefitId = benefitId;
	}
	/**
	 * @return Returns the benefitMeaning.
	 */
	public String getBenefitMeaning() {
		return benefitMeaning;
	}
	/**
	 * @param benefitMeaning The benefitMeaning to set.
	 */
	public void setBenefitMeaning(String benefitMeaning) {
		this.benefitMeaning = benefitMeaning;
	}
	/**
	 * @return Returns the primaryCode.
	 */
	public String getPrimaryCode() {
		return primaryCode;
	}
	/**
	 * @param primaryCode The primaryCode to set.
	 */
	public void setPrimaryCode(String primaryCode) {
		this.primaryCode = primaryCode;
	}
	/**
	 * @return Returns the ruleDesc.
	 */
	public String getRuleDesc() {
		return ruleDesc;
	}
	/**
	 * @param ruleDesc The ruleDesc to set.
	 */
	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}
    /**
     * Returns the benefitComponentId
     * @return int benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }
    /**
     * Sets the benefitComponentId
     * @param benefitComponentId.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }
    /**
     * Returns the dateSegmentId
     * @return int dateSegmentId.
     */
    public int getDateSegmentId() {
        return dateSegmentId;
    }
    /**
     * Sets the dateSegmentId
     * @param dateSegmentId.
     */
    public void setDateSegmentId(int dateSegmentId) {
        this.dateSegmentId = dateSegmentId;
    }
}
