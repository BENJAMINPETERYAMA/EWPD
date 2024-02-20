/*
 * MandateLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.mandate.locateCriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

import java.util.Date;
import java.util.List;


/**
 * Locate Criteria class for Mandate.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MandateLocateCriteria extends LocateCriteria {

    private List citationNumberList;

    private Date effectiveDate;

    private Date expiryDate;

    private List jurisdictionList;

    private String mandateTypeId;

    private String groupSizeId;

    private String fundingArrangementId;
    
    private String action;
    
    private int mandateId;
    
    private String mandateName;
    



	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return Returns the mandateId.
	 */
	public int getMandateId() {
		return mandateId;
	}
	/**
	 * @param mandateId The mandateId to set.
	 */
	public void setMandateId(int mandateId) {
		this.mandateId = mandateId;
	}
	/**
	 * @return Returns the mandateName.
	 */
	public String getMandateName() {
		return mandateName;
	}
	/**
	 * @param mandateName The mandateName to set.
	 */
	public void setMandateName(String mandateName) {
		this.mandateName = mandateName;
	}
    /**
     * Returns the citationNumberList
     * 
     * @return List citationNumberList.
     */
    public List getCitationNumberList() {
        return citationNumberList;
    }


    /**
     * Sets the citationNumberList
     * 
     * @param citationNumberList.
     */
    public void setCitationNumberList(List citationNumberList) {
        this.citationNumberList = citationNumberList;
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
     * Returns the jurisdictionList
     * 
     * @return List jurisdictionList.
     */
    public List getJurisdictionList() {
        return jurisdictionList;
    }


    /**
     * Sets the jurisdictionList
     * 
     * @param jurisdictionList.
     */
    public void setJurisdictionList(List jurisdictionList) {
        this.jurisdictionList = jurisdictionList;
    }


    /**
     * Returns the fundingArrangementId.
     * 
     * @return fundingArrangementId.
     */
    public String getFundingArrangementId() {
        return fundingArrangementId;
    }


    /**
     * Sets the fundingArrangementId.
     * 
     * @param fundingArrangementId
     *  
     */
    public void setFundingArrangementId(String fundingArrangementId) {
        this.fundingArrangementId = fundingArrangementId;
    }


    /**
     * Returns the groupSizeId.
     * 
     * @return groupSizeId
     */
    public String getGroupSizeId() {
        return groupSizeId;
    }


    /**
     * Sets the groupSizeId.
     * 
     * @param groupSizeId
     */
    public void setGroupSizeId(String groupSizeId) {
        this.groupSizeId = groupSizeId;
    }


    /**
     * Returns the mandateTypeId.
     * 
     * @return mandateTypeId
     */
    public String getMandateTypeId() {
        return mandateTypeId;
    }


    /**
     * Sets the mandateTypeId.
     * 
     * @param mandateTypeId
     */
    public void setMandateTypeId(String mandateTypeId) {
        this.mandateTypeId = mandateTypeId;
    }
}