/*
 * ContractQuestUniqueReferenceBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;

import java.util.Date;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractQuestUniqueReferenceBO extends BusinessObject{
    
    private int adminLevelOptAssnSysid;
    
    private int refId;
    
    private int questionNumber;
    
    private int admnSysId;
    
    private int entitySysId;
	
    private int benefitComponentId;
	
    private int benefitSysId;
    
    private int benefitLineId;
    
    private int benefitLevelId;
    
    private String benefitLevelDesc;
    
    private String benefitLinePva;
    
    private String benefitComponentName;
    
    private int benefitComponentParentId;
    
    private int benefitComponentVersionNum;
    
    private String benefitSysName;
    
    private int benefitSysParentId;
    
    private int benefitSysVersionNum;
    
    private String adminDesc;
    
    private String questionDesc;
    
    private String referenceDesc;
    
    private int dateSegmentSysId;
    
    private String dateSegmentSysName;
    
    private Date startDate;
    
    private Date endDate;
    
    private int contractdateSegmentSysId;
    
    private String dateRange;
    
    private String productName;
    
    private String noteID;
    
    private int noteVersion;
    
    private String noteName;
    
    private String tierDesc;
    
	/**
	 * @return Returns the dateSegmentSysName.
	 */
	public String getDateSegmentSysName() {
		return dateSegmentSysName;
	}
	/**
	 * @param dateSegmentSysName The dateSegmentSysName to set.
	 */
	public void setDateSegmentSysName(String dateSegmentSysName) {
		this.dateSegmentSysName = dateSegmentSysName;
	}

    
    /**
     * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
     * @param object
     * @return
     */
    public boolean equals(BusinessObject object) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
     * @return
     */
    public int hashCode() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
     * @return
     */
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return Returns the adminLevelOptAssnSysid.
     */
    public int getAdminLevelOptAssnSysid() {
        return adminLevelOptAssnSysid;
    }
    /**
     * @param adminLevelOptAssnSysid The adminLevelOptAssnSysid to set.
     */
    public void setAdminLevelOptAssnSysid(int adminLevelOptAssnSysid) {
        this.adminLevelOptAssnSysid = adminLevelOptAssnSysid;
    }
    /**
     * @return Returns the adminDesc.
     */
    public String getAdminDesc() {
        return adminDesc;
    }
    /**
     * @param adminDesc The adminDesc to set.
     */
    public void setAdminDesc(String adminDesc) {
        this.adminDesc = adminDesc;
    }   
    /**
     * @return Returns the admnSysId.
     */
    public int getAdmnSysId() {
        return admnSysId;
    }
    /**
     * @param admnSysId The admnSysId to set.
     */
    public void setAdmnSysId(int admnSysId) {
        this.admnSysId = admnSysId;
    }
    /**
     * @return Returns the benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }
    /**
     * @param benefitComponentId The benefitComponentId to set.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }
    /**
     * @return Returns the benefitComponentName.
     */
    public String getBenefitComponentName() {
        return benefitComponentName;
    }
    /**
     * @param benefitComponentName The benefitComponentName to set.
     */
    public void setBenefitComponentName(String benefitComponentName) {
        this.benefitComponentName = benefitComponentName;
    }
    /**
     * @return Returns the benefitComponentParentId.
     */
    public int getBenefitComponentParentId() {
        return benefitComponentParentId;
    }
    /**
     * @param benefitComponentParentId The benefitComponentParentId to set.
     */
    public void setBenefitComponentParentId(int benefitComponentParentId) {
        this.benefitComponentParentId = benefitComponentParentId;
    }
    /**
     * @return Returns the benefitComponentVersionNum.
     */
    public int getBenefitComponentVersionNum() {
        return benefitComponentVersionNum;
    }
    /**
     * @param benefitComponentVersionNum The benefitComponentVersionNum to set.
     */
    public void setBenefitComponentVersionNum(int benefitComponentVersionNum) {
        this.benefitComponentVersionNum = benefitComponentVersionNum;
    }
    /**
     * @return Returns the benefitLevelDesc.
     */
    public String getBenefitLevelDesc() {
        return benefitLevelDesc;
    }
    /**
     * @param benefitLevelDesc The benefitLevelDesc to set.
     */
    public void setBenefitLevelDesc(String benefitLevelDesc) {
        this.benefitLevelDesc = benefitLevelDesc;
    }
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
     * @return Returns the benefitLinePva.
     */
    public String getBenefitLinePva() {
        return benefitLinePva;
    }
    /**
     * @param benefitLinePva The benefitLinePva to set.
     */
    public void setBenefitLinePva(String benefitLinePva) {
        this.benefitLinePva = benefitLinePva;
    }
    /**
     * @return Returns the benefitSysId.
     */
    public int getBenefitSysId() {
        return benefitSysId;
    }
    /**
     * @param benefitSysId The benefitSysId to set.
     */
    public void setBenefitSysId(int benefitSysId) {
        this.benefitSysId = benefitSysId;
    }
    /**
     * @return Returns the benefitSysName.
     */
    public String getBenefitSysName() {
        return benefitSysName;
    }
    /**
     * @param benefitSysName The benefitSysName to set.
     */
    public void setBenefitSysName(String benefitSysName) {
        this.benefitSysName = benefitSysName;
    }
    /**
     * @return Returns the benefitSysParentId.
     */
    public int getBenefitSysParentId() {
        return benefitSysParentId;
    }
    /**
     * @param benefitSysParentId The benefitSysParentId to set.
     */
    public void setBenefitSysParentId(int benefitSysParentId) {
        this.benefitSysParentId = benefitSysParentId;
    }
    /**
     * @return Returns the benefitSysVersionNum.
     */
    public int getBenefitSysVersionNum() {
        return benefitSysVersionNum;
    }
    /**
     * @param benefitSysVersionNum The benefitSysVersionNum to set.
     */
    public void setBenefitSysVersionNum(int benefitSysVersionNum) {
        this.benefitSysVersionNum = benefitSysVersionNum;
    }
    /**
     * @return Returns the entitySysId.
     */
    public int getEntitySysId() {
        return entitySysId;
    }
    /**
     * @param entitySysId The entitySysId to set.
     */
    public void setEntitySysId(int entitySysId) {
        this.entitySysId = entitySysId;
    }
    /**
     * @return Returns the questionDesc.
     */
    public String getQuestionDesc() {
        return questionDesc;
    }
    /**
     * @param questionDesc The questionDesc to set.
     */
    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }
    /**
     * @return Returns the questionNumber.
     */
    public int getQuestionNumber() {
        return questionNumber;
    }
    /**
     * @param questionNumber The questionNumber to set.
     */
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }
    /**
     * @return Returns the referenceDesc.
     */
    public String getReferenceDesc() {
        return referenceDesc;
    }
    /**
     * @param referenceDesc The referenceDesc to set.
     */
    public void setReferenceDesc(String referenceDesc) {
        this.referenceDesc = referenceDesc;
    }
    /**
     * @return Returns the refId.
     */
    public int getRefId() {
        return refId;
    }
    /**
     * @param refId The refId to set.
     */
    public void setRefId(int refId) {
        this.refId = refId;
    }
	
	/**
	 * @return Returns the dateSegmentSysId.
	 */
	public int getDateSegmentSysId() {
		return dateSegmentSysId;
	}
	/**
	 * @param dateSegmentSysId The dateSegmentSysId to set.
	 */
	public void setDateSegmentSysId(int dateSegmentSysId) {		
		this.dateSegmentSysId = dateSegmentSysId;
	}
	
    /**
     * @return Returns the endDate.
     */
    public Date getEndDate() {
        return endDate;
    }
    /**
     * @param endDate The endDate to set.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    /**
     * @return Returns the startDate.
     */
    public Date getStartDate() {
        return startDate;
    }
    /**
     * @param startDate The startDate to set.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    /**
     * @return Returns the contractdateSegmentSysId.
     */
    public int getContractdateSegmentSysId() {
        return contractdateSegmentSysId;
    }
    /**
     * @param contractdateSegmentSysId The contractdateSegmentSysId to set.
     */
    public void setContractdateSegmentSysId(int contractdateSegmentSysId) {
        this.contractdateSegmentSysId = contractdateSegmentSysId;
    }
	/**
	 * @return Returns the dateRange.
	 */
	public String getDateRange() {
		return dateRange;
	}
	/**
	 * @param dateRange The dateRange to set.
	 */
	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}
	/**
	 * @return Returns the productName.
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName The productName to set.
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return Returns the noteID.
	 */
	public String getNoteID() {
		return noteID;
	}
	/**
	 * @param noteID The noteID to set.
	 */
	public void setNoteID(String noteID) {
		this.noteID = noteID;
	}
	/**
	 * @return Returns the noteVersion.
	 */
	public int getNoteVersion() {
		return noteVersion;
	}
	/**
	 * @param noteVersion The noteVersion to set.
	 */
	public void setNoteVersion(int noteVersion) {
		this.noteVersion = noteVersion;
	}
	/**
	 * @return Returns the noteName.
	 */
	public String getNoteName() {
		return noteName;
	}
	/**
	 * @param noteName The noteName to set.
	 */
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	public String getTierDesc() {
		return tierDesc;
	}
	public void setTierDesc(String tierDesc) {
		this.tierDesc = tierDesc;
	}
}
