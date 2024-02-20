/*
 * MajorHeading.java
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
public class MajorHeading extends LegacyObject {
	private String structureId;
	private String majorHeading;
	private String majorHeadingText;
	private int displaySequence;
    private String createdUser;
    private Date createdTimestamp;
    private String lastUpdatedUser;
    private Date lastUpdatedTimestamp;
    
    public MajorHeading(){
    	
    }
    public MajorHeading(String system) {
    	super(system);
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
	/**
	 * Returns the majorHeading.
	 * @return String majorHeading.
	 */
	public String getMajorHeading() {
		return majorHeading;
	}
	/**
	 * Sets the majorHeading.
	 * @param majorHeading.
	 */
	
	public void setMajorHeading(String majorHeading) {
		this.majorHeading = majorHeading;
	}
	/**
	 * Returns the majorHeadingText.
	 * @return String majorHeadingText.
	 */
	public String getMajorHeadingText() {
		return majorHeadingText;
	}
	/**
	 * Sets the majorHeadingText.
	 * @param majorHeadingText.
	 */
	
	public void setMajorHeadingText(String majorHeadingText) {
		this.majorHeadingText = majorHeadingText;
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
}
