/*
 * EntityBenefitAdministration.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.override.benefit.bo;

import java.util.Date;
import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class EntityBenefitAdministration {
	private int entityId;

	private int benefitComponentId;

	private String entityType;

	private int benefitAdmSysId;

	private int questionNumber;

	private String questionDesc;

	private String dataTypeId;

	private String dataTypeDesc;

	private String dataTypeLegend;

	private int answerId;

	private String answerDesc;

	private List answers;

	private int referenceId;

	private String referenceDesc;

	private int beneftComponentId;

	private int sequenceId;

	private int adminLevelOptionAssnId;

	//  added the question hide flag for enhancement
	private String qstnHideFlag;

	private String adminOptionHideFlag;

	private int ansOvrdCustmzdSysId;

	//modified for solving performance issue on 14th Dec 2007
	private boolean isModified;

	private String lastUpdatedUser;

	private Date lastUpdatedTimestamp;

	private int parentAdminOptionId;

	//modification ends

	private String providerArrangement;

	private String displayIndicator;

	private String adminOptionName;

	private int questionnaireId;
	
	private int tierSysId;

	private int tierIndicator;

	private String tierTypeIndicator;
	
	private int benefitId;
	
	private String noteText;


	/**
	 * @return isModified
	 * 
	 * Returns the isModified.
	 */
	public boolean isModified() {
		return isModified;
	}

	/**
	 * @param isModified
	 * 
	 * Sets the isModified.
	 */
	public void setModified(boolean isModified) {
		this.isModified = isModified;
	}

	/**
	 * @return Returns the sequenceId.
	 */
	public int getSequenceId() {
		return sequenceId;
	}

	/**
	 * @param sequenceId
	 *            The sequenceId to set.
	 */
	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}

	/**
	 * @return Returns the benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}

	/**
	 * @param benefitComponentId
	 *            The benefitComponentId to set.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}

	/**
	 * @return Returns the entityId.
	 */
	public int getEntityId() {
		return entityId;
	}

	/**
	 * @param entityId
	 *            The entityId to set.
	 */
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	/**
	 * @return Returns the entityType.
	 */
	public String getEntityType() {
		return entityType;
	}

	/**
	 * @param entityType
	 *            The entityType to set.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	/**
	 * @return Returns the answers.
	 */
	public List getAnswers() {
		return answers;
	}

	/**
	 * @param answers
	 *            The answers to set.
	 */
	public void setAnswers(List answers) {
		this.answers = answers;
	}

	/**
	 * Returns the answerDesc
	 * 
	 * @return String answerDesc.
	 */
	public String getAnswerDesc() {
		return answerDesc;
	}

	/**
	 * Sets the answerDesc
	 * 
	 * @param answerDesc.
	 */
	public void setAnswerDesc(String answerDesc) {
		this.answerDesc = answerDesc;
	}

	/**
	 * Returns the answerId
	 * 
	 * @return int answerId.
	 */
	public int getAnswerId() {
		return answerId;
	}

	/**
	 * Sets the answerId
	 * 
	 * @param answerId.
	 */
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	/**
	 * Returns the benefitAdmSysId
	 * 
	 * @return int benefitAdmSysId.
	 */
	public int getBenefitAdmSysId() {
		return benefitAdmSysId;
	}

	/**
	 * Sets the benefitAdmSysId
	 * 
	 * @param benefitAdmSysId.
	 */
	public void setBenefitAdmSysId(int benefitAdmSysId) {
		this.benefitAdmSysId = benefitAdmSysId;
	}

	/**
	 * Returns the dataTypeDesc
	 * 
	 * @return String dataTypeDesc.
	 */
	public String getDataTypeDesc() {
		return dataTypeDesc;
	}

	/**
	 * Sets the dataTypeDesc
	 * 
	 * @param dataTypeDesc.
	 */
	public void setDataTypeDesc(String dataTypeDesc) {
		this.dataTypeDesc = dataTypeDesc;
	}

	/**
	 * Returns the dataTypeId
	 * 
	 * @return String dataTypeId.
	 */
	public String getDataTypeId() {
		return dataTypeId;
	}

	/**
	 * Sets the dataTypeId
	 * 
	 * @param dataTypeId.
	 */
	public void setDataTypeId(String dataTypeId) {
		this.dataTypeId = dataTypeId;
	}

	/**
	 * Returns the questionDesc
	 * 
	 * @return String questionDesc.
	 */
	public String getQuestionDesc() {
		return questionDesc;
	}

	/**
	 * Sets the questionDesc
	 * 
	 * @param questionDesc.
	 */
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

	/**
	 * Returns the questionNumber
	 * 
	 * @return int questionNumber.
	 */
	public int getQuestionNumber() {
		return questionNumber;
	}

	/**
	 * Sets the questionNumber
	 * 
	 * @param questionNumber.
	 */
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}

	/**
	 * Returns the referenceDesc
	 * 
	 * @return String referenceDesc.
	 */
	public String getReferenceDesc() {
		return referenceDesc;
	}

	/**
	 * Sets the referenceDesc
	 * 
	 * @param referenceDesc.
	 */
	public void setReferenceDesc(String referenceDesc) {
		this.referenceDesc = referenceDesc;
	}

	/**
	 * Returns the referenceId
	 * 
	 * @return int referenceId.
	 */
	public int getReferenceId() {
		return referenceId;
	}

	/**
	 * Sets the referenceId
	 * 
	 * @param referenceId.
	 */
	public void setReferenceId(int referenceId) {
		this.referenceId = referenceId;
	}

	/**
	 * Returns the beneftComponentId
	 * 
	 * @return int beneftComponentId.
	 */
	public int getBeneftComponentId() {
		return beneftComponentId;
	}

	/**
	 * Sets the beneftComponentId
	 * 
	 * @param beneftComponentId.
	 */
	public void setBeneftComponentId(int beneftComponentId) {
		this.beneftComponentId = beneftComponentId;
	}

	public String getDataTypeLegend() {
		return dataTypeLegend;
	}

	public void setDataTypeLegend(String dataTypeLegend) {
		this.dataTypeLegend = dataTypeLegend;
	}

	/**
	 * Returns the adminLevelOptionAssnId
	 * 
	 * @return int adminLevelOptionAssnId.
	 */
	public int getAdminLevelOptionAssnId() {
		return adminLevelOptionAssnId;
	}

	/**
	 * Sets the adminLevelOptionAssnId
	 * 
	 * @param adminLevelOptionAssnId.
	 */
	public void setAdminLevelOptionAssnId(int adminLevelOptionAssnId) {
		this.adminLevelOptionAssnId = adminLevelOptionAssnId;
	}

	/**
	 * @return Returns the qstnHideFlag.
	 */
	public String getQstnHideFlag() {
		return qstnHideFlag;
	}

	/**
	 * @param qstnHideFlag
	 *            The qstnHideFlag to set.
	 */
	public void setQstnHideFlag(String qstnHideFlag) {
		this.qstnHideFlag = qstnHideFlag;
	}

	/**
	 * @return Returns the adminOptionHideFlag.
	 */
	public String getAdminOptionHideFlag() {
		return adminOptionHideFlag;
	}

	/**
	 * @param adminOptionHideFlag
	 *            The adminOptionHideFlag to set.
	 */
	public void setAdminOptionHideFlag(String adminOptionHideFlag) {
		this.adminOptionHideFlag = adminOptionHideFlag;
	}

	/**
	 * @return Returns the ansOvrdCustmzdSysId.
	 */
	public int getAnsOvrdCustmzdSysId() {
		return ansOvrdCustmzdSysId;
	}

	/**
	 * @param ansOvrdCustmzdSysId
	 *            The ansOvrdCustmzdSysId to set.
	 */
	public void setAnsOvrdCustmzdSysId(int ansOvrdCustmzdSysId) {
		this.ansOvrdCustmzdSysId = ansOvrdCustmzdSysId;
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
	 * @return Returns the parentAdminOptionId.
	 */
	public int getParentAdminOptionId() {
		return parentAdminOptionId;
	}

	/**
	 * @param parentAdminOptionId
	 *            The parentAdminOptionId to set.
	 */
	public void setParentAdminOptionId(int parentAdminOptionId) {
		this.parentAdminOptionId = parentAdminOptionId;
	}

	/**
	 * @return Returns the providerArrangement.
	 */
	public String getProviderArrangement() {
		return providerArrangement;
	}

	/**
	 * @param providerArrangement
	 *            The providerArrangement to set.
	 */
	public void setProviderArrangement(String providerArrangement) {
		this.providerArrangement = providerArrangement;
	}

	/**
	 * @return Returns the displayIndicator.
	 */
	public String getDisplayIndicator() {
		return displayIndicator;
	}

	/**
	 * @param displayIndicator
	 *            The displayIndicator to set.
	 */
	public void setDisplayIndicator(String displayIndicator) {
		this.displayIndicator = displayIndicator;
	}

	/**
	 * @return Returns the adminOptionName.
	 */
	public String getAdminOptionName() {
		return adminOptionName;
	}

	/**
	 * @param adminOptionName
	 *            The adminOptionName to set.
	 */
	public void setAdminOptionName(String adminOptionName) {
		this.adminOptionName = adminOptionName;
	}

	/**
	 * @return Returns the questionnaireId.
	 */
	public int getQuestionnaireId() {
		return questionnaireId;
	}

	/**
	 * @param questionnaireId
	 *            The questionnaireId to set.
	 */
	public void setQuestionnaireId(int questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	/**
	 * @return Returns the tierIndicator.
	 */
	public int getTierIndicator() {
		return tierIndicator;
	}
	/**
	 * @param tierIndicator The tierIndicator to set.
	 */
	public void setTierIndicator(int tierIndicator) {
		this.tierIndicator = tierIndicator;
	}
	/**
	 * @return Returns the tierSysId.
	 */
	public int getTierSysId() {
		return tierSysId;
	}
	/**
	 * @param tierSysId The tierSysId to set.
	 */
	public void setTierSysId(int tierSysId) {
		this.tierSysId = tierSysId;
	}
	/**
	 * @return Returns the tierTypeIndicator.
	 */
	public String getTierTypeIndicator() {
		return tierTypeIndicator;
	}
	/**
	 * @param tierTypeIndicator The tierTypeIndicator to set.
	 */
	public void setTierTypeIndicator(String tierTypeIndicator) {
		this.tierTypeIndicator = tierTypeIndicator;
	}

	public int getBenefitId() {
		return benefitId;
	}

	public void setBenefitId(int benefitId) {
		this.benefitId = benefitId;
	}

	public String getNoteText() {
		return noteText;
	}

	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}
}