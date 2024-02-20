/*
 * ContractBenefitHeadings.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;

import java.util.Date;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractBenefitHeadings {
    
   
    int dateSegmentId;
    
    //Level Info
    private int levelSysId;
  
    
    //Line Info
    private int lineSysId;
    

    private String createdUser;
    private Date createdTimestamp;
    private String lastUpdatedUser;
    private Date lastUpdatedTimestamp;

    private int benefitComponentId;
    private String benefitComponentName;
    
    private int standardBenefitId;
    private String standardBenefitName;
    
    private int benefitLineId;
    
    private String benefitValue;
    
    private int productId;
    
    private int sequenceNumber;
    
    private boolean lineHideStatus;

	private boolean levelHideStatus;
    
	/*START CARS*/
    //DESCRIPTION : Variable to hold frequnecy value.
    private int frequencyValue;
    //DESCRIPTION : Variable to hold level description value.
    private String levelDesc;
    /*END CARS*/
    
    private String bnftHideStatus;
    private String bnftCmpntHideStatus;
    private int benefitDefnSysId;

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
     * Returns the benefitComponentName
     * @return String benefitComponentName.
     */
    public String getBenefitComponentName() {
        return benefitComponentName;
    }
    /**
     * Sets the benefitComponentName
     * @param benefitComponentName.
     */
    public void setBenefitComponentName(String benefitComponentName) {
        this.benefitComponentName = benefitComponentName;
    }
    /**
     * Returns the createdTimestamp
     * @return Date createdTimestamp.
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }
    /**
     * Sets the createdTimestamp
     * @param createdTimestamp.
     */
    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
    /**
     * Returns the createdUser
     * @return String createdUser.
     */
    public String getCreatedUser() {
        return createdUser;
    }
    /**
     * Sets the createdUser
     * @param createdUser.
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
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
    /**
     * Returns the lastUpdatedTimestamp
     * @return Date lastUpdatedTimestamp.
     */
    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }
    /**
     * Sets the lastUpdatedTimestamp
     * @param lastUpdatedTimestamp.
     */
    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }
    /**
     * Returns the lastUpdatedUser
     * @return String lastUpdatedUser.
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }
    /**
     * Sets the lastUpdatedUser
     * @param lastUpdatedUser.
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }
    /**
     * Returns the levelSysId
     * @return int levelSysId.
     */
    public int getLevelSysId() {
        return levelSysId;
    }
    /**
     * Sets the levelSysId
     * @param levelSysId.
     */
    public void setLevelSysId(int levelSysId) {
        this.levelSysId = levelSysId;
    }
    /**
     * Returns the lineSysId
     * @return int lineSysId.
     */
    public int getLineSysId() {
        return lineSysId;
    }
    /**
     * Sets the lineSysId
     * @param lineSysId.
     */
    public void setLineSysId(int lineSysId) {
        this.lineSysId = lineSysId;
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
     * Returns the standardBenefitName
     * @return String standardBenefitName.
     */
    public String getStandardBenefitName() {
        return standardBenefitName;
    }
    /**
     * Sets the standardBenefitName
     * @param standardBenefitName.
     */
    public void setStandardBenefitName(String standardBenefitName) {
        this.standardBenefitName = standardBenefitName;
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
	 * @return Returns the benefitValue.
	 */
	public String getBenefitValue() {
		return benefitValue;
	}
	/**
	 * @param benefitValue The benefitValue to set.
	 */
	public void setBenefitValue(String benefitValue) {
		this.benefitValue = benefitValue;
	}
	/**
	 * @return Returns the productId.
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param productId The productId to set.
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
    /**
     * Returns the sequenceNumber
     * @return int sequenceNumber.
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }
    /**
     * Sets the sequenceNumber
     * @param sequenceNumber.
     */
    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
	/**
	 * @return Returns the frequencyValue.
	 */
	public int getFrequencyValue() {
		return frequencyValue;
	}
	/**
	 * @param frequencyValue The frequencyValue to set.
	 */
	public void setFrequencyValue(int frequencyValue) {
		this.frequencyValue = frequencyValue;
	}
	/**
	 * @return Returns the levelDesc.
	 */
	public String getLevelDesc() {
		return levelDesc;
	}
	/**
	 * @param levelDesc The levelDesc to set.
	 */
	public void setLevelDesc(String levelDesc) {
		this.levelDesc = levelDesc;
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
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("CNTRCT_BNFT_CSTMZD_SYS_ID : ").append(sequenceNumber);
		sb.append("DT_SGMNT_SYS_ID : ").append(dateSegmentId);
		sb.append("PROD_SYS_ID : ").append(productId);
		sb.append("BNFT_CMPNT_SYS_ID : ").append(benefitComponentId);
		sb.append("BNFT_SYS_ID : ").append(standardBenefitId);
		sb.append("BNFT_DEFN_SYS_ID : ").append(benefitDefnSysId);
		sb.append("BLVL_BNFT_LVL_ID : ").append(levelSysId);
		sb.append("BLN_BNFT_LINE_ID : ").append(benefitLineId);
		sb.append("BVAL_BNFT_VAL : ").append(benefitValue);
		sb.append("BNFT_HIDE_FLAG : ").append(bnftHideStatus);
		sb.append("LST_CHG_TMS : ").append(lastUpdatedTimestamp);
		sb.append("LST_CHG_USR : ").append(lastUpdatedUser);
		sb.append("CREATD_TM_STMP : ").append(createdTimestamp);
		sb.append("CREATD_USER_ID : ").append(createdUser);
		sb.append("BNFT_CMPNT_HIDE_FLAG : ").append(bnftCmpntHideStatus);
		sb.append("BLVL_BNFT_FRQNCY_QLFR : ").append(frequencyValue);
		sb.append("BLVL_BNFT_LVL_DESC :").append(levelDesc);
		return sb.toString();
		}
	/**
	 * @return Returns the bnftHideStatus.
	 */
	public String getBnftHideStatus() {
		return bnftHideStatus;
	}
	/**
	 * @param bnftHideStatus The bnftHideStatus to set.
	 */
	public void setBnftHideStatus(String bnftHideStatus) {
		this.bnftHideStatus = bnftHideStatus;
	}
	/**
	 * @return Returns the bnftCmpntHideStatus.
	 */
	public String getBnftCmpntHideStatus() {
		return bnftCmpntHideStatus;
	}
	/**
	 * @param bnftCmpntHideStatus The bnftCmpntHideStatus to set.
	 */
	public void setBnftCmpntHideStatus(String bnftCmpntHideStatus) {
		this.bnftCmpntHideStatus = bnftCmpntHideStatus;
	}
	/**
	 * @return Returns the benefitDefnSysId.
	 */
	public int getBenefitDefnSysId() {
		return benefitDefnSysId;
	}
	/**
	 * @param benefitDefnSysId The benefitDefnSysId to set.
	 */
	public void setBenefitDefnSysId(int benefitDefnSysId) {
		this.benefitDefnSysId = benefitDefnSysId;
	}
}
