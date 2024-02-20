/*
 * MandateVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.mandate.vo;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.bo.State;


/**
 * Value Object class for Mandate.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MandateVO {
	
	//The unique ID associated with the Mandate. 
    private int mandateId = -1;

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

    //User name of the Mandate created.
    private String createdUser;

    //The created time stamp of the Mandate. 
    private Date createdTimestamp;

    //User name of the last updated Mandate.
    private String lastUpdatedUser;

    //Time stamp of the last updated Mandate
    private Date lastUpdatedTimestamp;

    //Version number of the Mandate.
    private int version;

    //Status of the Mandate.
    private String status;
    
    //List of citaton numbers of the Mandate.
    private List citationNumberList;
    
    //List of jurisdiction of the Mandate
    private List jurisdictionList;
    
    //Boolean value associated with check-in.
    private boolean checkIn;
    
    //Action to distinguish between view and view-all
    private String action;
    
    //The state of the Mandate.
    private State state;


	/**
	 * Returns the state of the mandate.
	 * 
	 * @return  State state
	 */
	public State getState() {
		return state;
	}
	
	/**
	 * The state  of the mandate to set.
	 * 
	 * @param state 
	 */
	public void setState(State state) {
		this.state = state;
	}
	
    /**
     * Returns the citationNumber.
     * 
     * @return String citationNumber.
     */
    public String getCitationNumber() {
        return citationNumber;
    }

    /**
     * Sets the citationNumber
     * 
     * @param citationNumber.
     */
    public void setCitationNumber(String citationNumber) {
        this.citationNumber = citationNumber;
    }

    /**
     * Returns the description
     * 
     * @return String description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description
     * 
     * @param description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the effective date
     * 
     * @return Date effectiveDate.
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Sets the effective date
     * 
     * @param effectiveDate.
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * Returns the expiry date
     * 
     * @return Date expiryDate.
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiry date
     * 
     * @param expiryDate.
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Returns the Id associated with the Mandate.
     * 
     * @return int mandateId.
     */
    public int getMandateId() {
        return mandateId;
    }

    /**
     * Sets the Id associated with the Mandate.
     * 
     * @param mandateId.
     */
    public void setMandateId(int mandateId) {
        this.mandateId = mandateId;
    }

    /**
     * Returns the description of the funding arrangements.
     * 
     * @return String fundingArrangementDesc.
     */
    public String getFundingArrangementDesc() {
        return fundingArrangementDesc;
    }

    /**
     * Sets the description of the funding arrangements.
     * 
     * @param fundingArrangementDesc.
     */
    public void setFundingArrangementDesc(String fundingArrangementDesc) {
        this.fundingArrangementDesc = fundingArrangementDesc;
    }

    /**
     * Returns the id associated with the funding arrangements. 
     * 
     * @return String fundingArrangementId.
     */
    public String getFundingArrangementId() {
        return fundingArrangementId;
    }

    /**
     * Sets the id associated with the funding arrangements. 
     * 
     * @param fundingArrangementId.
     */
    public void setFundingArrangementId(String fundingArrangementId) {
        this.fundingArrangementId = fundingArrangementId;
    }

    /**
     * Returns the description of the group size.
     * 
     * @return String groupSizeDesc.
     */
    public String getGroupSizeDesc() {
        return groupSizeDesc;
    }

    /**
     * Sets the description of the group size.
     * 
     * @param groupSizeDesc.
     */
    public void setGroupSizeDesc(String groupSizeDesc) {
        this.groupSizeDesc = groupSizeDesc;
    }

    /**
     * Returns the Id associated with the group size.
     * 
     * @return String groupSizeId.
     */
    public String getGroupSizeId() {
        return groupSizeId;
    }

    /**
     * Sets the Id associated with the group size.
     * 
     * @param groupSizeId.
     */
    public void setGroupSizeId(String groupSizeId) {
        this.groupSizeId = groupSizeId;
    }

    /**
     * Returns the jurisdiction description.
     * 
     * @return String jurisdictionDesc.
     */
    public String getJurisdictionDesc() {
        return jurisdictionDesc;
    }

    /**
     * Sets the jurisdiction description.
     * 
     * @param jurisdictionDesc.
     */
    public void setJurisdictionDesc(String jurisdictionDesc) {
        this.jurisdictionDesc = jurisdictionDesc;
    }

    /**
     * Returns the Id associated with the jurisdiction.
     * 
     * @return String jurisdictionId.
     */
    public String getJurisdictionId() {
        return jurisdictionId;
    }

    /**
     * Sets the Id associated with the jurisdiction.
     * 
     * @param jurisdictionId.
     */
    public void setJurisdictionId(String jurisdictionId) {
        this.jurisdictionId = jurisdictionId;
    }

    /**
     * Returns the description of the mandate type.
     * 
     * @return String mandateTypeDesc.
     */
    public String getMandateTypeDesc() {
        return mandateTypeDesc;
    }

    /**
     * Sets the description of the mandate type.
     * 
     * @param mandateTypeDesc.
     */
    public void setMandateTypeDesc(String mandateTypeDesc) {
        this.mandateTypeDesc = mandateTypeDesc;
    }

    /**
     * Returns the Id associated with the Mandate type.
     * 
     * @return String mandateTypeId.
     */
    public String getMandateTypeId() {
        return mandateTypeId;
    }

    /**
     * Sets the Id associated with the Mandate type.
     * 
     * @param mandateTypeId.
     */
    public void setMandateTypeId(String mandateTypeId) {
        this.mandateTypeId = mandateTypeId;
    }

    /**
     * Returns the list of citation numbers. 
     * 
     * @return List citationNumberList
     */
    public List getCitationNumberList() {
        return citationNumberList;
    }

    /**
     * Sets the list of citation numbers. 
     * 
     * @param citationNumberList
     */
    public void setCitationNumberList(List citationNumberList) {
        this.citationNumberList = citationNumberList;
    }

    /**
     * Returns the created Timestamp.
     * 
     * @return Date createdTimestamp
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    /**
     * Sets the created Timestamp.
     * 
     * @param createdTimestamp
     */
    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    /**
     * Returns the created User.
     * 
     * @return String createdUser
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * Sets the created User.
     * 
     * @param createdUser
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    /**
     * Returns the jurisdiction List.
     * 
     * @return List jurisdictionList
     */
    public List getJurisdictionList() {
        return jurisdictionList;
    }

    /**
     * Sets the jurisdiction List.
     * 
     * @param jurisdictionList
     */
    public void setJurisdictionList(List jurisdictionList) {
        this.jurisdictionList = jurisdictionList;
    }

    /**
     * Returns the last Updated Timestamp.
     * 
     * @return lastUpdatedTimestamp
     */
    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }

    /**
     * Sets the last Updated Timestamp.
     * 
     * @param lastUpdatedTimestamp
     */
    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }

    /**
     * Returns the last Updated User.
     * 
     * @return String lastUpdatedUser
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }

    /**
     * Sets the last Updated User.
     * 
     * @param lastUpdatedUser
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }

    /**
     * Returns the name of the Mandate.
     * 
     * @return String mandateName
     */
    public String getMandateName() {
        return mandateName;
    }

    /**
     * Sets the name of the Mandate.
     * 
     * @param mandateName
     */
    public void setMandateName(String mandateName) {
        this.mandateName = mandateName;
    }

    /**
     * Returns the status of Mandate.
     * 
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of Mandate.
     * 
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the version.
     * 
     * @return version
     */
    public int getVersion() {
        return version;
    }

    /**
     * Sets the version.
     * 
     * @param version
     */
    public void setVersion(int version) {
        this.version = version;
    }
    
	/**
	 * Returns the check-In status.
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
	 * Returns the action. 
	 * 
	 * @return String action
	 */
	public String getAction() {
		return action;
	}
	
	/**
	 * The action to set.
	 * 
	 * @param action 
	 */
	public void setAction(String action) {
		this.action = action;
	}
}