/*
 * BenefitLevel.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.override.benefit.bo;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitLevel {
    private int benefitDefenitionId;
    private String levelDesc;
    private int levelId;
    private int termId;
    private String termDesc;
    private int qualifierId;
    private String qualifierDesc;
    private int referenceId;
    private String referenceDesc;
    private List benefitLines;
    private List benefitLevels;
    
    //Change Level/line hide
    private boolean levelHideStatus;
    private boolean lineHideStatus;
    private int tierSysId;

    private String isTierDefExist;
    private String isTierLevelExist;
    
    private int tierDefSysId;
    //CARS START
    //DESCRIPTION : integer to hold frequency value
    private int frequencyId;
    //DESCRIPTION : String to hold frequency value
    private String frequencyDesc;
    //DESCRIPTION : BOOLEAN Validation Flag
    private boolean validationFlag;    
    private String lowerLevelFrequencyValue;    
    private String lowerLevelDescValue;
    //CARS END

	
	/**
	 * @return Returns the tierDefSysId.
	 */
	public int getTierDefSysId() {
		return tierDefSysId;
	}
	/**
	 * @param tierDefSysId The tierDefSysId to set.
	 */
	public void setTierDefSysId(int tierDefSysId) {
		this.tierDefSysId = tierDefSysId;
	}
	/**
	 * @return Returns the lineHideStatus.
	 */
	public boolean isLineHideStatus() {
		return lineHideStatus;
	}
	/**
	 * @param lineHideStatus The lineHideStatus to set.
	 */
	public void setLineHideStatus(boolean lineHideStatus) {
		this.lineHideStatus = lineHideStatus;
	}
	/**
	 * @return Returns the levelHideStatus.
	 */
	public boolean isLevelHideStatus() {
		return levelHideStatus;
	}
	/**
	 * @param levelHideStatus The levelHideStatus to set.
	 */
	public void setLevelHideStatus(boolean levelHideStatus) {
		this.levelHideStatus = levelHideStatus;
	}
	//end
	/**
	 * @return Returns the benefitLevels.
	 */
	public List getBenefitLevels() {
		return benefitLevels;
	}
	/**
	 * @param benefitLevels The benefitLevels to set.
	 */
	public void setBenefitLevels(List benefitLevels) {
		this.benefitLevels = benefitLevels;
	}
    /**
     * Returns the benefitDefenitionId
     * @return int benefitDefenitionId.
     */
    public int getBenefitDefenitionId() {
        return benefitDefenitionId;
    }
    /**
     * Sets the benefitDefenitionId
     * @param benefitDefenitionId.
     */
    public void setBenefitDefenitionId(int benefitDefenitionId) {
        this.benefitDefenitionId = benefitDefenitionId;
    }
    /**
     * Returns the benefitLines
     * @return List benefitLines.
     */
    public List getBenefitLines() {
        return benefitLines;
    }
    /**
     * Sets the benefitLines
     * @param benefitLines.
     */
    public void setBenefitLines(List benefitLines) {
        this.benefitLines = benefitLines;
    }
    /**
     * Returns the levelDesc
     * @return String levelDesc.
     */
    public String getLevelDesc() {
        return levelDesc;
    }
    /**
     * Sets the levelDesc
     * @param levelDesc.
     */
    public void setLevelDesc(String levelDesc) {
        this.levelDesc = levelDesc;
    }
    /**
     * Returns the levelId
     * @return int levelId.
     */
    public int getLevelId() {
        return levelId;
    }
    /**
     * Sets the levelId
     * @param levelId.
     */
    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }
    /**
     * Returns the qualifierDesc
     * @return String qualifierDesc.
     */
    public String getQualifierDesc() {
        return qualifierDesc;
    }
    /**
     * Sets the qualifierDesc
     * @param qualifierDesc.
     */
    public void setQualifierDesc(String qualifierDesc) {
        this.qualifierDesc = qualifierDesc;
    }
    /**
     * Returns the qualifierId
     * @return int qualifierId.
     */
    public int getQualifierId() {
        return qualifierId;
    }
    /**
     * Sets the qualifierId
     * @param qualifierId.
     */
    public void setQualifierId(int qualifierId) {
        this.qualifierId = qualifierId;
    }
    /**
     * Returns the referenceDesc
     * @return String referenceDesc.
     */
    public String getReferenceDesc() {
        return referenceDesc;
    }
    /**
     * Sets the referenceDesc
     * @param referenceDesc.
     */
    public void setReferenceDesc(String referenceDesc) {
        this.referenceDesc = referenceDesc;
    }
    /**
     * Returns the referenceId
     * @return int referenceId.
     */
    public int getReferenceId() {
        return referenceId;
    }
    /**
     * Sets the referenceId
     * @param referenceId.
     */
    public void setReferenceId(int referenceId) {
        this.referenceId = referenceId;
    }
    /**
     * Returns the termDesc
     * @return String termDesc.
     */
    public String getTermDesc() {
        return termDesc;
    }
    /**
     * Sets the termDesc
     * @param termDesc.
     */
    public void setTermDesc(String termDesc) {
        this.termDesc = termDesc;
    }
    /**
     * Returns the termId
     * @return int termId.
     */
    public int getTermId() {
        return termId;
    }
    /**
     * Sets the termId
     * @param termId.
     */
    public void setTermId(int termId) {
        this.termId = termId;
    }
	/**
	 * @return Returns the tierSysId.
	 */
	public int getTierSysId() {
		return tierSysId;
	}
	/**
	 * @param tierSysId The tierSysId to set.
	 */
	public void setTierSysId(int tierSysId) {
		this.tierSysId = tierSysId;
	}
	/**
	 * @return Returns the isTierDefExist.
	 */
	public String getIsTierDefExist() {
		return isTierDefExist;
	}
	/**
	 * @param isTierDefExist The isTierDefExist to set.
	 */
	public void setIsTierDefExist(String isTierDefExist) {
		this.isTierDefExist = isTierDefExist;
	}
	/**
	 * @return Returns the isTierLevelExist.
	 */
	public String getIsTierLevelExist() {
		return isTierLevelExist;
	}
	/**
	 * @param isTierLevelExist The isTierLevelExist to set.
	 */
	public void setIsTierLevelExist(String isTierLevelExist) {
		this.isTierLevelExist = isTierLevelExist;
	}
	/**
	 * @return Returns the frequencyDesc.
	 */
	public String getFrequencyDesc() {
		return frequencyDesc;
	}
	/**
	 * @param frequencyDesc The frequencyDesc to set.
	 */
	public void setFrequencyDesc(String frequencyDesc) {
		this.frequencyDesc = frequencyDesc;
	}
	/**
	 * @return Returns the frequencyId.
	 */
	public int getFrequencyId() {
		return frequencyId;
	}
	/**
	 * @param frequencyId The frequencyId to set.
	 */
	public void setFrequencyId(int frequencyId) {
		this.frequencyId = frequencyId;
	}
	/**
	 * @return Returns the validationFlag.
	 */
	public boolean isValidationFlag() {
		return validationFlag;
	}
	/**
	 * @param validationFlag The validationFlag to set.
	 */
	public void setValidationFlag(boolean validationFlag) {
		this.validationFlag = validationFlag;
	}
	/**
	 * @return Returns the lowerLevelDescValue.
	 */
	public String getLowerLevelDescValue() {
		return lowerLevelDescValue;
	}
	/**
	 * @param lowerLevelDescValue The lowerLevelDescValue to set.
	 */
	public void setLowerLevelDescValue(String lowerLevelDescValue) {
		this.lowerLevelDescValue = lowerLevelDescValue;
	}
	/**
	 * @return Returns the lowerLevelFrequencyValue.
	 */
	public String getLowerLevelFrequencyValue() {
		return lowerLevelFrequencyValue;
	}
	/**
	 * @param lowerLevelFrequencyValue The lowerLevelFrequencyValue to set.
	 */
	public void setLowerLevelFrequencyValue(String lowerLevelFrequencyValue) {
		this.lowerLevelFrequencyValue = lowerLevelFrequencyValue;
	}
}
