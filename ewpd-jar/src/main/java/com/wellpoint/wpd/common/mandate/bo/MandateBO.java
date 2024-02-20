/*
 * MandateBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.mandate.bo;

import java.util.Date;
import com.wellpoint.wpd.common.bo.BusinessObject;


/**
 * Business Object class for Mandate.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MandateBO extends BusinessObject {
	
	//Id associated with the Mandate.
    private int mandateId;

    //Citation number of the Mandate.
    private String citationNumber;

    //Effective date with the start of Mandate.
    private Date effectiveDate;
    
    //Expiry Date of the Mandate.
    private Date expiryDate;

    //ID associated with the Mandate Type.
    private String mandateTypeId;
    
    //Description of the Mandate Type.
    private String mandateTypeDesc;
    
    //Id associated with the jurisdiction.
    private String jurisdictionId;
    
    //Description of the jurisdiction.
    private String jurisdictionDesc;

    //ID associated with the group size.
    private String groupSizeId;

    //Description of the group size.
    private String groupSizeDesc;

    //ID associated with the funding arrangements.
    private String fundingArrangementId;
    
    //Description associated with the funding arrangements
    private String fundingArrangementDesc;

    //Description of the Mandate
    private String description;

    //Name associated with the Mandate.
    private String mandateName;

    //The Check-in status of the mandate    
    private boolean checkIn;
    
    //Version of the mandate.
    private int version;
  
    
	/**
	 * Returns the version.
	 * 
	 * @return int version
	 */
	public int getVersion() {
		return version;
	}
	
	/**
	 * The version to set.
	 * 
	 * @param version 
	 */
	public void setVersion(int version) {
		this.version = version;
	}

    /**
     * Returns the Id associated with the mandate.
     * 
     * @return int mandateId.
     */
    public int getMandateId() {
        return mandateId;
    }

    /**
     * Sets the Id associated with the mandate.
     * 
     * @param mandateId.
     */
    public void setMandateId(int mandateId) {
        this.mandateId = mandateId;
    }

    /**
     * Returns the citation number
     * 
     * @return String citationNumber.
     */
    public String getCitationNumber() {
        return citationNumber;
    }

    /**
     * Sets the citation number
     * 
     * @param citationNumber.
     */
    public void setCitationNumber(String citationNumber) {
        this.citationNumber = citationNumber;
    }

    /**
     * Returns the description.
     * 
     * @return String description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the mandate name.
     * 
     * @return String mandateName
     */
    public String getMandateName() {
        return mandateName;
    }

    /**
     * Sets the mandate name.
     * 
     * @param mandateName 
     */
    public void setMandateName(String mandateName) {
        this.mandateName = mandateName;
    }

    /**
     * Returns the effectiveDate
     * 
     * @return Date effectiveDate.
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Sets the effectiveDate
     * 
     * @param effectiveDate.
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * Returns the expiryDate
     * 
     * @return Date expiryDate.
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiryDate
     * 
     * @param expiryDate.
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Returns the description about the funding arrangements.
     *  
     * @return String fundingArrangementDesc.
     */
    public String getFundingArrangementDesc() {
        return fundingArrangementDesc;
    }

    /**
     * Sets the description about the funding arrangements.
     * 
     * @param fundingArrangementDesc.
     */
    public void setFundingArrangementDesc(String fundingArrangementDesc) {
        this.fundingArrangementDesc = fundingArrangementDesc;
    }

    /**
     * Returns the Id associated with the funding arrangements.
     * 
     * @return String fundingArrangementId.
     */
    public String getFundingArrangementId() {
        return fundingArrangementId;
    }

    /**
     * Sets the Id associated with the funding arrangements.
     * 
     * @param fundingArrangementId.
     */
    public void setFundingArrangementId(String fundingArrangementId) {
        this.fundingArrangementId = fundingArrangementId;
    }

    /**
     * Returns the description about the group size.
     * 
     * @return String groupSizeDesc.
     */
    public String getGroupSizeDesc() {
        return groupSizeDesc;
    }

    /**
     * Sets the description about the group size.
     * 
     * @param groupSizeDesc.
     */
    public void setGroupSizeDesc(String groupSizeDesc) {
        this.groupSizeDesc = groupSizeDesc;
    }

    /**
     * Returns the Id associated with the group size
     * 
     * @return String groupSizeId.
     */
    public String getGroupSizeId() {
        return groupSizeId;
    }

    /**
     * Sets the Id associated with the group size
     * 
     * @param groupSizeId.
     */
    public void setGroupSizeId(String groupSizeId) {
        this.groupSizeId = groupSizeId;
    }

    /**
     * Returns the description about the mandate type.
     * 
     * @return String mandateTypeDesc.
     */
    public String getMandateTypeDesc() {
        return mandateTypeDesc;
    }

    /**
     * Sets the description about the mandate type
     * 
     * @param mandateTypeDesc.
     */
    public void setMandateTypeDesc(String mandateTypeDesc) {
        this.mandateTypeDesc = mandateTypeDesc;
    }

    /**
     * Returns the Id associated with the mandate type.
     * 
     * @return String mandateTypeId.
     */
    public String getMandateTypeId() {
        return mandateTypeId;
    }

    /**
     * Sets the Id associated with the mandate type.
     * 
     * @param mandateTypeId.
     */
    public void setMandateTypeId(String mandateTypeId) {
        this.mandateTypeId = mandateTypeId;
    }

    /**
     * Returns the description about the jurisdiction.
     * 
     * @return String jurisdictionDesc.
     */
    public String getJurisdictionDesc() {
        return jurisdictionDesc;
    }

    /**
     * Sets the description about the jurisdiction.
     * 
     * @param jurisdictionDesc
     */
    public void setJurisdictionDesc(String jurisdictionDesc) {
        this.jurisdictionDesc = jurisdictionDesc;
    }

    /**
     * Returns the Id associated with the jurisdiction
     * 
     * @return String jurisdictionId.
     */
    public String getJurisdictionId() {
        return jurisdictionId;
    }

    /**
     * Sets the Id associated with the jurisdiction
     * 
     * @param jurisdictionId.
     */
    public void setJurisdictionId(String jurisdictionId) {
        this.jurisdictionId = jurisdictionId;
    }
    
	/**
	 * Returns the checkIn.
	 * 
	 * @return boolean checkIn
	 */
	public boolean isCheckIn() {
		return checkIn;
	}
	
	/**
	 * The checkIn to set.
	 * 
	 * @param checkIn 
	 */
	public void setCheckIn(boolean checkIn) {
		this.checkIn = checkIn;
	}

    /**
     * Implementation of the abstract method of the super class.
     * 
     * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
     * 
     * @param object
     * 
     * @return boolean true
     */
    public boolean equals(BusinessObject object) {
        return true;
    }

    /**
     * Implementation of the abstract method of the super class.
     * 
     * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
     * 
     * @return int 1
     */
    public int hashCode() {
        return 1;
    }

    /**
     * Implementation of the abstract method of the super class.
     * 
     * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
     * 
     * @return String 
     */
    public String toString() {
        return "";
    }
	
}