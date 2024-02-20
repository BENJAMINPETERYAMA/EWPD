/*
 * BenefitLineVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.override.benefit.vo;

import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitLineVO {
    private int benefitLineId;
    private int benefitLvlId;
    private int benefitDefnId;
    private String overridedValue;
    private String benefitLineHideFlag;
    //CARS START
    //DESCRIPTION : String for frequency.
    private String overridedFreqValue = WebConstants.EMPTY_STRING;
    //DESCRIPTION : String for level description.
    private String overridedLvlDescValue = WebConstants.EMPTY_STRING;
    //CARS END
    /**
     * Returns the benefitLineId
     * @return int benefitLineId.
     */
    public int getBenefitLineId() {
        return benefitLineId;
    }
    /**
     * Sets the benefitLineId
     * @param benefitLineId.
     */
    public void setBenefitLineId(int benefitLineId) {
        this.benefitLineId = benefitLineId;
    }
    /**
     * Returns the overridedValue
     * @return String overridedValue.
     */
    public String getOverridedValue() {
        return overridedValue;
    }
    /**
     * Sets the overridedValue
     * @param overridedValue.
     */
    public void setOverridedValue(String overridedValue) {
        this.overridedValue = overridedValue;
    }
	

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
	/**
	 * @return Returns the benefitLvlId.
	 */
	public int getBenefitLvlId() {
		return benefitLvlId;
	}
	/**
	 * @param benefitLvlId The benefitLvlId to set.
	 */
	public void setBenefitLvlId(int benefitLvlId) {
		this.benefitLvlId = benefitLvlId;
	}
	/**
	 * @return Returns the benefitDefnId.
	 */
	public int getBenefitDefnId() {
		return benefitDefnId;
	}
	/**
	 * @param benefitDefnId The benefitDefnId to set.
	 */
	public void setBenefitDefnId(int benefitDefnId) {
		this.benefitDefnId = benefitDefnId;
	}
}
