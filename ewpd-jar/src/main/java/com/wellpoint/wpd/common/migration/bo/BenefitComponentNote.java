/*
 * BenefitComponentNote.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.bo;

import java.util.Date;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentNote {
	private int migDateSegSysId;
	private int benefitCompSysId;
	private String majorHeadingId;
	// migrate Y/N?
	private String componentNoteMigrateFlag;
	private String createdUser;
	private Date createdTimestamp;
	private String lastUpdatedUser;
	private Date lastUpdatedTimestamp;	
	
	/**
	 * @return Returns the benefitCompSysId.
	 */
	public int getBenefitCompSysId() {
		return benefitCompSysId;
	}
	/**
	 * @param benefitCompSysId The benefitCompSysId to set.
	 */
	public void setBenefitCompSysId(int benefitCompSysId) {
		this.benefitCompSysId = benefitCompSysId;
	}
	/**
	 * @return Returns the createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * @param createdTimestamp The createdTimestamp to set.
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
	 * @param createdUser The createdUser to set.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * @return Returns the lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * @param lastUpdatedTimestamp The lastUpdatedTimestamp to set.
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
	 * @param lastUpdatedUser The lastUpdatedUser to set.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * @return Returns the majorHeadingId.
	 */
	public String getMajorHeadingId() {
		return majorHeadingId;
	}
	/**
	 * @param majorHeadingId The majorHeadingId to set.
	 */
	public void setMajorHeadingId(String majorHeadingId) {
		this.majorHeadingId = majorHeadingId;
	}
	/**
	 * @return Returns the migDateSegSysId.
	 */
	public int getMigDateSegSysId() {
		return migDateSegSysId;
	}
	/**
	 * @param migDateSegSysId The migDateSegSysId to set.
	 */
	public void setMigDateSegSysId(int migDateSegSysId) {
		this.migDateSegSysId = migDateSegSysId;
	}
	/**
	 * @return Returns the componentNoteMigrateFlag.
	 */
	public String getComponentNoteMigrateFlag() {
		return componentNoteMigrateFlag;
	}
	/**
	 * @param componentNoteMigrateFlag The componentNoteMigrateFlag to set.
	 */
	public void setComponentNoteMigrateFlag(String componentNoteMigrateFlag) {
		this.componentNoteMigrateFlag = componentNoteMigrateFlag;
	}
}
