/*
 * MigratedDateSegmentInfo.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.bo;

import java.util.Date;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.wpd.common.bo.Audit;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MigratedDateSegmentInfo {
	private int migDateSegSysId;
	private int ewpdDateSegSysId;
	private Date legacyStartDate;
	private Date ewpdStartDate;
	private int migContractSysId;
	private int ewpdContractSysId;
	private String contractId;
	private String migrateContractNoteFlag; 
	
	private Audit audit;
	private AdapterServicesAccess access;
	
	/**
	 * @return Returns the contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * @param contractId The contractId to set.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * @return Returns the ewpdContractSysId.
	 */
	public int getEwpdContractSysId() {
		return ewpdContractSysId;
	}
	/**
	 * @param ewpdContractSysId The ewpdContractSysId to set.
	 */
	public void setEwpdContractSysId(int ewpdContractSysId) {
		this.ewpdContractSysId = ewpdContractSysId;
	}
	/**
	 * @return Returns the ewpdDateSegSysId.
	 */
	public int getEwpdDateSegSysId() {
		return ewpdDateSegSysId;
	}
	/**
	 * @param ewpdDateSegSysId The ewpdDateSegSysId to set.
	 */
	public void setEwpdDateSegSysId(int ewpdDateSegSysId) {
		this.ewpdDateSegSysId = ewpdDateSegSysId;
	}
	/**
	 * @return Returns the migContractSysId.
	 */
	public int getMigContractSysId() {
		return migContractSysId;
	}
	/**
	 * @param migContractSysId The migContractSysId to set.
	 */
	public void setMigContractSysId(int migContractSysId) {
		this.migContractSysId = migContractSysId;
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
	 * @return Returns the ewpdStartDate.
	 */
	public Date getEwpdStartDate() {
		return ewpdStartDate;
	}
	/**
	 * @param ewpdStartDate The ewpdStartDate to set.
	 */
	public void setEwpdStartDate(Date ewpdStartDate) {
		this.ewpdStartDate = ewpdStartDate;
	}
	/**
	 * @return Returns the legacyStartDate.
	 */
	public Date getLegacyStartDate() {
		return legacyStartDate;
	}
	/**
	 * @param legacyStartDate The legacyStartDate to set.
	 */
	public void setLegacyStartDate(Date legacyStartDate) {
		this.legacyStartDate = legacyStartDate;
	}
	/**
	 * @return Returns the access.
	 */
	public AdapterServicesAccess getAccess() {
		return access;
	}
	/**
	 * @param access The access to set.
	 */
	public void setAccess(AdapterServicesAccess access) {
		this.access = access;
	}
	/**
	 * @return Returns the audit.
	 */
	public Audit getAudit() {
		return audit;
	}
	/**
	 * @param audit The audit to set.
	 */
	public void setAudit(Audit audit) {
		this.audit = audit;
	}
	/**
	 * @return Returns the migrateContractNoteFlag.
	 */
	public String getMigrateContractNoteFlag() {
		return migrateContractNoteFlag;
	}
	/**
	 * @param migrateContractNoteFlag The migrateContractNoteFlag to set.
	 */
	public void setMigrateContractNoteFlag(String migrateContractNoteFlag) {
		this.migrateContractNoteFlag = migrateContractNoteFlag;
	}
}
