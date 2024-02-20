/*
 * Created on Aug 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.migration.bo;

import java.util.Date;

/**
 * @author U11085
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class MigrationVariableInsert {

	private int bftCompSysId;

	private String variableId;

	private String createdUser;

	private int migDatesegmentID;
	
	private String legacyContractId;

	private Date createdTimestamp;

	private int mappingSysId;
	
	private String lastUpdatedUser;

	private Date lastUpdatedTimestamp ;
	private int bftLineId;
	private String varDetails;
	
	/**
	 * @return Returns the varDetails.
	 */
	public String getVarDetails() {
		return varDetails;
	}
	/**
	 * @param varDetails The varDetails to set.
	 */
	public void setVarDetails(String varDetails) {
		this.varDetails = varDetails;
	}
	/**
	 * @return Returns the mappingSysId.
	 */
	public int getMappingSysId() {
		return mappingSysId;
	}

	/**
	 * @param mappingSysId
	 *            The mappingSysId to set.
	 */
	public void setMappingSysId(int mappingSysId) {
		this.mappingSysId = mappingSysId;
	}

	/**
	 * @return Returns the createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return new Date();
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
	 * @return Returns the lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return new Date();
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
	 * @return Returns the variableId.
	 */
	public String getVariableId() {
		return variableId;
	}

	/**
	 * @param variableId
	 *            The variableId to set.
	 */
	public void setVariableId(String variableId) {
		this.variableId = variableId;
	}

	/**
	 * @return Returns the bftCompSysId.
	 */
	public int getBftCompSysId() {
		return bftCompSysId;
	}

	/**
	 * @param bftCompSysId
	 *            The bftCompSysId to set.
	 */
	public void setBftCompSysId(int bftCompSysId) {
		this.bftCompSysId = bftCompSysId;
	}


	/**
	 * @return Returns the bftLineId.
	 */
	public int getBftLineId() {
		return bftLineId;
	}
	/**
	 * @param bftLineId The bftLineId to set.
	 */
	public void setBftLineId(int bftLineId) {
		this.bftLineId = bftLineId;
	}
	/**
	 * Returns the legacyContractId
	 * @return String legacyContractId.
	 */
	public String getLegacyContractId() {
		return legacyContractId;
	}
	/**
	 * Sets the legacyContractId
	 * @param legacyContractId.
	 */
	public void setLegacyContractId(String legacyContractId) {
		this.legacyContractId = legacyContractId;
	}
	/**
	 * @return Returns the migDatesegmentID.
	 */
	public int getMigDatesegmentID() {
		return migDatesegmentID;
	}
	/**
	 * @param migDatesegmentID The migDatesegmentID to set.
	 */
	public void setMigDatesegmentID(int migDatesegmentID) {
		this.migDatesegmentID = migDatesegmentID;
	}
}