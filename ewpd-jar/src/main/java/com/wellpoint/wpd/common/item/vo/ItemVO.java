/*
 * ItemVO.java © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.item.vo;

import java.util.Date;

public class ItemVO {
    
    private int catalogId;

    private String primaryCode;

    private String secondaryCode;

    private String description;

    private String catalogName;
   
    //User name of the Item created.
    private String createdUser;

    //The created time stamp of the Item.
    private Date createdTimestamp;

    //User name of the last updated Item.
    private String lastUpdatedUser;

    //Time stamp of the last updated Item
    private Date lastUpdatedTimestamp;
    
    private String status;
    //CARS START
    //DESCRIPTION : Frequency flag for qulaifier
    private String frequencyRequired;    
    //CARS END
    private String hcsCode;  

    /**
     * @return Returns the createdTimestamp.
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }


    /**
     * @param createdTimestamp
     *            The createdTimestamp to set.
     */
    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }


    /**
     * @return Returns the createdUser.
     */
    public String getCreatedUser() {
        return createdUser;
    }


    /**
     * @param createdUser
     *            The createdUser to set.
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }


    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }


    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * @return Returns the lastUpdatedTimestamp.
     */
    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }


    /**
     * @param lastUpdatedTimestamp
     *            The lastUpdatedTimestamp to set.
     */
    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }


    /**
     * @return Returns the lastUpdatedUser.
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }


    /**
     * @param lastUpdatedUser
     *            The lastUpdatedUser to set.
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }


    /**
     * @return Returns the primaryCode.
     */
    public String getPrimaryCode() {
        return primaryCode;
    }


    /**
     * @param primaryCode
     *            The primaryCode to set.
     */
    public void setPrimaryCode(String primaryCode) {
        this.primaryCode = primaryCode;
    }


    /**
     * @return Returns the secondaryCode.
     */
    public String getSecondaryCode() {
        return secondaryCode;
    }


    /**
     * @param secondaryCode
     *            The secondaryCode to set.
     */
    public void setSecondaryCode(String secondaryCode) {
        this.secondaryCode = secondaryCode;
    }


    /**
     * @return Returns the catalogName.
     */
    public String getCatalogName() {
        return catalogName;
    }


    /**
     * @param catalogName The catalogName to set.
     */
    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }


    /**
     * @return Returns the catalogId.
     */
    public int getCatalogId() {
        return catalogId;
    }


    /**
     * @param catalogId The catalogId to set.
     */
    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }
    
    /**
     * @return Returns the status.
     * @return String status
     */
    public String getStatus() {
        return status;
    }
    /**
     * Sets the status
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }
	/**
	 * @return Returns the frequencyRequired.
	 */
	public String getFrequencyRequired() {
		return frequencyRequired;
	}
	/**
	 * @param frequencyRequired The frequencyRequired to set.
	 */
	public void setFrequencyRequired(String frequencyRequired) {
		this.frequencyRequired = frequencyRequired;
	}


	/**
	 * @return the hcsCode
	 */
	public String getHcsCode() {
		return hcsCode;
	}


	/**
	 * @param hcsCode the hcsCode to set
	 */
	public void setHcsCode(String hcsCode) {
		this.hcsCode = hcsCode;
	}
}