/*
 * Created on Oct 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.migration.bo;

import java.util.Date;

/**
 * @author u12573
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MigrationPossibleAnswer {
	private int adminQuestionNumber;
	private int entitySysID;
	private int possibleAnswerId;
	private String possibleAnswerDesc;
	private String questionDescription;
	private String referenceID;
	private int adminOptionSysID;
	private String lastUpdatedUser;
	private Date lastUpdatedTimestamp;
	private String createdUser;
	private Date createdTimestamp;
	/**
	 * @return Returns the adminQuestionNumber.
	 */
	public int getAdminQuestionNumber() {
		return adminQuestionNumber;
	}
	/**
	 * @param adminQuestionNumber The adminQuestionNumber to set.
	 */
	public void setAdminQuestionNumber(int adminQuestionNumber) {
		this.adminQuestionNumber = adminQuestionNumber;
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
	 * @return Returns the possibleAnswerId.
	 */
	public int getPossibleAnswerId() {
		return possibleAnswerId;
	}
	/**
	 * @param possibleAnswerId The possibleAnswerId to set.
	 */
	public void setPossibleAnswerId(int possibleAnswerId) {
		this.possibleAnswerId = possibleAnswerId;
	}
	/**
	 * @return Returns the possibleAnswerDesc.
	 */
	public String getPossibleAnswerDesc() {
		return possibleAnswerDesc;
	}
	/**
	 * @param possibleAnswerDesc The possibleAnswerDesc to set.
	 */
	public void setPossibleAnswerDesc(String possibleAnswerDesc) {
		this.possibleAnswerDesc = possibleAnswerDesc;
	}
	/**
	 * @return Returns the questionDescription.
	 */
	public String getQuestionDescription() {
		return questionDescription;
	}
	/**
	 * @param questionDescription The questionDescription to set.
	 */
	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}
	/**
	 * @return Returns the referenceID.
	 */
	public String getReferenceID() {
		return referenceID;
	}
	/**
	 * @param referenceID The referenceID to set.
	 */
	public void setReferenceID(String referenceID) {
		this.referenceID = referenceID;
	}
	/**
	 * @return Returns the adminOptionSysID.
	 */
	public int getAdminOptionSysID() {
		return adminOptionSysID;
	}
	/**
	 * @param adminOptionSysID The adminOptionSysID to set.
	 */
	public void setAdminOptionSysID(int adminOptionSysID) {
		this.adminOptionSysID = adminOptionSysID;
	}
	/**
	 * @return Returns the entitySysID.
	 */
	public int getEntitySysID() {
		return entitySysID;
	}
	/**
	 * @param entitySysID The entitySysID to set.
	 */
	public void setEntitySysID(int entitySysID) {
		this.entitySysID = entitySysID;
	}
}
