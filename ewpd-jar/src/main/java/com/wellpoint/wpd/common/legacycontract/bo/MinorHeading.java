/*
 * MinorHeading.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.legacycontract.bo;

import java.util.Date;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public class MinorHeading extends LegacyObject {
	private String structureId;
	private String majorHeadingId;
	private String minorHeading;
	private String minorHeadingText;
	private int displaySequence;
	private String svcFlags;
    private String createdUser;
    private Date createdTimestamp;
    private String lastUpdatedUser;
    private Date lastUpdatedTimestamp;

    public MinorHeading(){
    	
    }
    public MinorHeading(String system) {
    	super(system);
    }
	/**
	 * Returns the majorHeadingId.
	 * @return String majorHeadingId.
	 */
	public String getMajorHeadingId() {
		return majorHeadingId;
	}
	/**
	 * Sets the majorHeadingId.
	 * @param majorHeadingId.
	 */
	
	public void setMajorHeadingId(String majorHeadingId) {
		this.majorHeadingId = majorHeadingId;
	}
	/**
	 * Returns the minorHeading.
	 * @return String minorHeading.
	 */
	public String getMinorHeading() {
		return minorHeading;
	}
	/**
	 * Sets the minorHeading.
	 * @param minorHeading.
	 */
	
	public void setMinorHeading(String minorHeading) {
		this.minorHeading = minorHeading;
	}
	/**
	 * Returns the minorHeadingText.
	 * @return String minorHeadingText.
	 */
	public String getMinorHeadingText() {
		return minorHeadingText;
	}
	/**
	 * Sets the minorHeadingText.
	 * @param minorHeadingText.
	 */
	
	public void setMinorHeadingText(String minorHeadingText) {
		this.minorHeadingText = minorHeadingText;
	}
	/**
	 * Returns the displaySequence.
	 * @return int displaySequence.
	 */
	public int getDisplaySequence() {
		return displaySequence;
	}
	/**
	 * Sets the displaySequence.
	 * @param displaySequence.
	 */
	
	public void setDisplaySequence(int displaySequence) {
		this.displaySequence = displaySequence;
	}
	/**
	 * Returns the svcFlags.
	 * @return String svcFlags.
	 */
	public String getSvcFlags() {
		return svcFlags;
	}
	/**
	 * Sets the svcFlags.
	 * @param svcFlags.
	 */
	
	public void setSvcFlags(String svcFlags) {
		this.svcFlags = svcFlags;
	}
	/**
	 * Returns the createdUser.
	 * @return String createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * Sets the createdUser.
	 * @param createdUser.
	 */
	
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * Returns the createdTimestamp.
	 * @return Date createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * Sets the createdTimestamp.
	 * @param createdTimestamp.
	 */
	
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	/**
	 * Returns the lastUpdatedUser.
	 * @return String lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * Sets the lastUpdatedUser.
	 * @param lastUpdatedUser.
	 */
	
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * Returns the lastUpdatedTimestamp.
	 * @return Date lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * Sets the lastUpdatedTimestamp.
	 * @param lastUpdatedTimestamp.
	 */
	
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
	/**
	 * Returns the structureId.
	 * @return String structureId.
	 */
	public String getStructureId() {
		return structureId;
	}
	/**
	 * Sets the structureId.
	 * @param structureId.
	 */
	
	public void setStructureId(String structureId) {
		this.structureId = structureId;
	}
	
}
