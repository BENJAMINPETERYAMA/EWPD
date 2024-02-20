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
public class MigrationVariable {
	private String bftLineDescription;

	private String bftPva;

	private String bftReference;

	private int bftSysId;

	private int bftCompSysId;

	private String variableId;

	private String contractId;

	private String createdUser;

	private int migContractSysId;

	private Date createdTimestamp;

	private int mappingSysId;
	private int levelSeqNo;
	private String lastUpdatedUser;

	private Date lastUpdatedTimestamp;
	private int bftLineId;
	
	private String varValue;
	private int flag;
	private String varDetails;
	private String format;
	private String pva;
	private int dateSegmentId;
	private int bftComponentId;
	private int bftComponentSysId;
	private int prodStrmappingSysId;
	private int productId;
	private int overridebftCompId;
	private String migrateNotesFlag;
	private String varNoteFlag;


	/**
	 * @return Returns the migrateNotesFlag.
	 */
	public String getMigrateNotesFlag() {
		return migrateNotesFlag;
	}
	/**
	 * @param migrateNotesFlag The migrateNotesFlag to set.
	 */
	public void setMigrateNotesFlag(String migrateNotesFlag) {
		this.migrateNotesFlag = migrateNotesFlag;
	}
	/**
	 * @return Returns the prodStrmappingSysId.
	 */
	public int getProdStrmappingSysId() {
		return prodStrmappingSysId;
	}
	/**
	 * @param prodStrmappingSysId The prodStrmappingSysId to set.
	 */
	public void setProdStrmappingSysId(int prodStrmappingSysId) {
		this.prodStrmappingSysId = prodStrmappingSysId;
	}
	/**
	 * @return Returns the bftComponentSysId.
	 */
	public int getBftComponentSysId() {
		return bftComponentSysId;
	}
	/**
	 * @param bftComponentSysId The bftComponentSysId to set.
	 */
	public void setBftComponentSysId(int bftComponentSysId) {
		this.bftComponentSysId = bftComponentSysId;
	}
	/**
	 * @return Returns the bftComponentId.
	 */
	public int getBftComponentId() {
		return bftComponentId;
	}
	/**
	 * @param bftComponentId The bftComponentId to set.
	 */
	public void setBftComponentId(int bftComponentId) {
		this.bftComponentId = bftComponentId;
	}
	/**
	 * @return Returns the dateSegmentId.
	 */
	public int getDateSegmentId() {
		return dateSegmentId;
	}
	/**
	 * @param dateSegmentId The dateSegmentId to set.
	 */
	public void setDateSegmentId(int dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}
	/**
	 * @return Returns the pva.
	 */
	public String getPva() {
		return pva;
	}
	/**
	 * @param pva The pva to set.
	 */
	public void setPva(String pva) {
		this.pva = pva;
	}
	/**
	 * @return Returns the format.
	 */
	public String getFormat() {
		return format;
	}
	/**
	 * @param format The format to set.
	 */
	public void setFormat(String format) {
		this.format = format;
	}
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
	 * @return Returns the flag.
	 */
	public int getFlag() {
		return flag;
	}
	/**
	 * @param flag The flag to set.
	 */
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	/**
	 * @return Returns the varValue.
	 */
	public String getVarValue() {
		return varValue;
	}
	/**
	 * @param varValue The varValue to set.
	 */
	public void setVarValue(String varValue) {
		this.varValue = varValue;
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
	 * @return Returns the migContractSysId.
	 */
	public int getMigContractSysId() {
		return migContractSysId;
	}

	/**
	 * @param migContractSysId
	 *            The migContractSysId to set.
	 */
	public void setMigContractSysId(int migContractSysId) {
		this.migContractSysId = migContractSysId;
	}

	
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
	 * @return Returns the contractId.
	 */
	public String getContractId() {
		return contractId;
	}

	/**
	 * @param contractId
	 *            The contractId to set.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
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
	 * @return Returns the bftLineDescription.
	 */
	public String getBftLineDescription() {
		return bftLineDescription;
	}

	/**
	 * @param bftLineDescription
	 *            The bftLineDescription to set.
	 */
	public void setBftLineDescription(String bftLineDescription) {
		this.bftLineDescription = bftLineDescription;
	}

	/**
	 * @return Returns the bftPva.
	 */
	public String getBftPva() {
		return bftPva;
	}

	/**
	 * @param bftPva
	 *            The bftPva to set.
	 */
	public void setBftPva(String bftPva) {
		this.bftPva = bftPva;
	}

	/**
	 * @return Returns the bftReference.
	 */
	public String getBftReference() {
		return bftReference;
	}

	/**
	 * @param bftReference
	 *            The bftReference to set.
	 */
	public void setBftReference(String bftReference) {
		this.bftReference = bftReference;
	}

	/**
	 * @return Returns the bftSysId.
	 */
	public int getBftSysId() {
		return bftSysId;
	}

	/**
	 * @param bftSysId
	 *            The bftSysId to set.
	 */
	public void setBftSysId(int bftSysId) {
		this.bftSysId = bftSysId;
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
	 * @return Returns the overridebftCompId.
	 */
	public int getOverridebftCompId() {
		return overridebftCompId;
	}
	/**
	 * @param overridebftCompId The overridebftCompId to set.
	 */
	public void setOverridebftCompId(int overridebftCompId) {
		this.overridebftCompId = overridebftCompId;
	}
	/**
	 * @return Returns the productId.
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param productId The productId to set.
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	/**
	 * @return Returns the levelSeqNo.
	 */
	public int getLevelSeqNo() {
		return levelSeqNo;
	}
	/**
	 * @param levelSeqNo The levelSeqNo to set.
	 */
	public void setLevelSeqNo(int levelSeqNo) {
		this.levelSeqNo = levelSeqNo;
	}
	/**
	 * @return Returns the varNoteFlag.
	 */
	public String getVarNoteFlag() {
		return varNoteFlag;
	}
	/**
	 * @param varNoteFlag The varNoteFlag to set.
	 */
	public void setVarNoteFlag(String varNoteFlag) {
		this.varNoteFlag = varNoteFlag;
	}
}