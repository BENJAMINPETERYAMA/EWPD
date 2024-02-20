/*
 * MandateLocateResults.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.mandate.locateResult;

import com.wellpoint.wpd.common.bo.LocateResult;

import java.util.Date;


/**
 * Locate Result class for Mandate.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MandateLocateResult extends LocateResult {

    private int mandateId;

    private int versionNo;

    private String citationNumber;    
   
    private Date effectiveDate;

    private Date expiryDate;

    private String mandateTypeId;

    private String mandateTypeDesc;

    private String jurisdictionId;

    private String jurisdictionDesc;

    private String groupSizeId;

    private String groupSizeDesc;

    private String fundingArrangementId;

    private String fundingArrangementDesc;

    private String description;
    
    private String mandateName;
    
    private String status;
    
    private int totalResultsCount;


	/**
	 * Returns the status.
	 * 
	 * @return status String
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * The status to set.
	 * 
	 * @param status String
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
    /**
     * Returns the mandateId.
     * 
     * @return int mandateId int
     */
    public int getMandateId() {
        return mandateId;
    }


    /**
     * Sets the mandateId.
     * 
     * @param mandateId int
     */
    public void setMandateId(int mandateId) {
        this.mandateId = mandateId;
    }


    /**
     * Returns the mandateTypeDesc.
     * 
     * @return mandateTypeDesc String 
     */
    public String getMandateTypeDesc() {
        return mandateTypeDesc;
    }


    /**
     * Sets the mandateTypeDesc.
     * 
     * @param mandateTypeDesc String.
     */
    public void setMandateTypeDesc(String mandateTypeDesc) {
        this.mandateTypeDesc = mandateTypeDesc;
    }


    /**
     * Returns the mandateTypeId.
     * 
     * @return mandateTypeId String 
     */
    public String getMandateTypeId() {
        return mandateTypeId;
    }


    /**
     * Sets the mandateTypeId.
     * 
     * @param mandateTypeId String
     */
    public void setMandateTypeId(String mandateTypeId) {
        this.mandateTypeId = mandateTypeId;
    }


    /**
     * Returns the effectiveDate.
     * 
     * @return effectiveDate Date 
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }


    /**
     * Sets the effectiveDate.
     * 
     * @param effectiveDate Date
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }


    /**
     * Returns the expiryDate.
     * 
     * @return expiryDate Date 
     */
    public Date getExpiryDate() {
        return expiryDate;
    }


    /**
     * Sets the expiryDate.
     * 
     * @param expiryDate Date
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }


    /**
     * Returns the jurisdictionDesc.
     * 
     * @return jurisdictionDesc String
     */
    public String getJurisdictionDesc() {
        return jurisdictionDesc;
    }


    /**
     * Sets the jurisdictionDesc.
     * 
     * @param jurisdictionDesc String
     */
    public void setJurisdictionDesc(String jurisdictionDesc) {
        this.jurisdictionDesc = jurisdictionDesc;
    }


    /**
     * Returns the jurisdictionId.
     * 
     * @return jurisdictionId String
     */
    public String getJurisdictionId() {
        return jurisdictionId;
    }


    /**
     * Sets the jurisdictionId.
     * 
     * @param jurisdictionId String
     */
    public void setJurisdictionId(String jurisdictionId) {
        this.jurisdictionId = jurisdictionId;
    }


    /**
     * Returns the versionNo.
     * 
     * @return versionNo int 
     */
    public int getVersionNo() {
        return versionNo;
    }


    /**
     * Sets the versionNo.
     * 
     * @param versionNo int
     */
    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }


    /**
     * Returns the citationNumber.
     * 
     * @return citationNumber String
     */
    public String getCitationNumber() {
        return citationNumber;
    }


    /**
     * Sets the citationNumber.
     * 
     * @param citationNumber String
     */
    public void setCitationNumber(String citationNumber) {
        this.citationNumber = citationNumber;
    }


    /**
     * Returns the description.
     * 
     * @return description String
     */
    public String getDescription() {
        return description;
    }


    /**
     * Sets the description.
     * 
     * @param description String
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Returns the fundingArrangementDesc.
     * 
     * @return fundingArrangementDesc String 
     */
    public String getFundingArrangementDesc() {
        return fundingArrangementDesc;
    }


    /**
     * Sets the fundingArrangementDesc
     * 
     * @param fundingArrangementDesc.
     */
    public void setFundingArrangementDesc(String fundingArrangementDesc) {
        this.fundingArrangementDesc = fundingArrangementDesc;
    }


    /**
     * Returns the fundingArrangementId.
     * 
     * @return fundingArrangementId String
     */
    public String getFundingArrangementId() {
        return fundingArrangementId;
    }


    /**
     * Sets the fundingArrangementId.
     * 
     * @param fundingArrangementId String
     */
    public void setFundingArrangementId(String fundingArrangementId) {
        this.fundingArrangementId = fundingArrangementId;
    }


    /**
     * Returns the groupSizeDesc.
     * 
     * @return groupSizeDesc String
     */
    public String getGroupSizeDesc() {
        return groupSizeDesc;
    }


    /**
     * Sets the groupSizeDesc.
     * 
     * @param groupSizeDesc String
     */
    public void setGroupSizeDesc(String groupSizeDesc) {
        this.groupSizeDesc = groupSizeDesc;
    }


    /**
     * Returns the groupSizeId.
     * 
     * @return groupSizeId String
     */
    public String getGroupSizeId() {
        return groupSizeId;
    }


    /**
     * Sets the groupSizeId.
     * 
     * @param groupSizeId String
     */
    public void setGroupSizeId(String groupSizeId) {
        this.groupSizeId = groupSizeId;
    }
    
	/**
	 * Returns the mandateName.
	 * 
	 * @return mandateName String
	 */
	public String getMandateName() {
		return mandateName;
	}
	/**
	 * The mandateName to set.
	 * 
	 * @param mandateName  String
	 */
	public void setMandateName(String mandateName) {
		this.mandateName = mandateName;
	}

	/**
	 * Returns the totalResultsCount.
	 * 
	 * @return totalResultsCount int
	 */
	public int getTotalResultsCount() {
		return totalResultsCount;
	}
	/**
	 * The totalResultsCount to set.
	 * 
	 * @param totalResultsCount int
	 */
	public void setTotalResultsCount(int totalResultsCount) {
		this.totalResultsCount = totalResultsCount;
	}
}