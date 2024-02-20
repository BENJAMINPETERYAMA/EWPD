/*
 * BenefitLineNote.java
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
public class BenefitLineNote {
	private int migDateSegSysId;
	private int benefitCompSysId;
	private int benefitLineSysId;
	private String migrateNoteFlag;
	private String createdUser;
	private Date createdTimestamp;
	private String lastUpdatedUser;
	private Date lastUpdatedTimestamp;
	private String legacyVariableId;
	private int bnftDefnSysId;
	
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
	 * @return Returns the benefitLineSysId.
	 */
	public int getBenefitLineSysId() {
		return benefitLineSysId;
	}
	/**
	 * @param benefitLineSysId The benefitLineSysId to set.
	 */
	public void setBenefitLineSysId(int benefitLineSysId) {
		this.benefitLineSysId = benefitLineSysId;
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
	 * @return Returns the legacyVariableId.
	 */
	public String getLegacyVariableId() {
		return legacyVariableId;
	}
	/**
	 * @param legacyVariableId The legacyVariableId to set.
	 */
	public void setLegacyVariableId(String legacyVariableId) {
		this.legacyVariableId = legacyVariableId;
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
	 * @return Returns the migrateNoteFlag.
	 */
	public String getMigrateNoteFlag() {
		return migrateNoteFlag;
	}
	/**
	 * @param migrateNoteFlag The migrateNoteFlag to set.
	 */
	public void setMigrateNoteFlag(String migrateNoteFlag) {
		this.migrateNoteFlag = migrateNoteFlag;
	}
	/**
	 * @return Returns the bnftDefnSysId.
	 */
	public int getBnftDefnSysId() {
		return bnftDefnSysId;
	}
	/**
	 * @param bnftDefnSysId The bnftDefnSysId to set.
	 */
	public void setBnftDefnSysId(int bnftDefnSysId) {
		this.bnftDefnSysId = bnftDefnSysId;
	}
}
