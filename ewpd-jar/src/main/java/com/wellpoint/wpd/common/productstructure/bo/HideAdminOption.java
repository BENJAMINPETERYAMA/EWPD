/*
 * Created on Dec 14, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.productstructure.bo;

import java.util.Date;
import java.util.List;

/**
 * @author u12573
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HideAdminOption {
	
	private int adminLevelOptionAssnSystemId;
	 
	 private int benefitAdminSystemId;
	 private int adminLevelSystemId;
	 private int benefitLevelSystemId;
	 private int adminOptionSystemId;	 
	 	 
	 private String createdUser;
	 private String lastUpdatedUser;
	 private Date createdTimestamp;
	 private Date lastUpdatedTimestamp; 
	 private String adminOptionReferenceId;
	 
	 private String adminLevelSystemIdForBenefitLevel;
	 private String adminLevelDesc;
	 private String adminOptionDesc;
	 private int adminOptionIdFromMaster;
	 private int benefitLevelSysId;
	 private String benefitLevelDesc;
	 private int benefitLevelSysIdFromMaster;

	 
	 // For retrieving data from ADMIN OPTION TO QUESTION ASSCN 
	 private int adminQuestionNumber;
	 private int sequenceNumber;
	 private int possibleAnswerId;
	 private String answerDescription;	
	 private String isOpen;
	 
	 private List adminList;
	 private String flag;
	 
	 private int benefitCompSysId;
	 private String entityType;
	 private int entityId;
	 
	 // previous value for adminoptionsysid
	 private int adminOptionSysIdForUpdate;
	 
	 private String adminQuestionHideFlag;
	 private String questionHideFlag;
	 private List adminBOList;
	 
	 private int productSysId;
	 
	 

	/**
	 * @return Returns the adminBOList.
	 */
	public List getAdminBOList() {
		return adminBOList;
	}
	/**
	 * @param adminBOList The adminBOList to set.
	 */
	public void setAdminBOList(List adminBOList) {
		this.adminBOList = adminBOList;
	}
	/**
	 * @return Returns the adminLevelDesc.
	 */
	public String getAdminLevelDesc() {
		return adminLevelDesc;
	}
	/**
	 * @param adminLevelDesc The adminLevelDesc to set.
	 */
	public void setAdminLevelDesc(String adminLevelDesc) {
		this.adminLevelDesc = adminLevelDesc;
	}
	/**
	 * @return Returns the adminLevelOptionAssnSystemId.
	 */
	public int getAdminLevelOptionAssnSystemId() {
		return adminLevelOptionAssnSystemId;
	}
	/**
	 * @param adminLevelOptionAssnSystemId The adminLevelOptionAssnSystemId to set.
	 */
	public void setAdminLevelOptionAssnSystemId(int adminLevelOptionAssnSystemId) {
		this.adminLevelOptionAssnSystemId = adminLevelOptionAssnSystemId;
	}
	/**
	 * @return Returns the adminLevelSystemId.
	 */
	public int getAdminLevelSystemId() {
		return adminLevelSystemId;
	}
	/**
	 * @param adminLevelSystemId The adminLevelSystemId to set.
	 */
	public void setAdminLevelSystemId(int adminLevelSystemId) {
		this.adminLevelSystemId = adminLevelSystemId;
	}
	/**
	 * @return Returns the adminLevelSystemIdForBenefitLevel.
	 */
	public String getAdminLevelSystemIdForBenefitLevel() {
		return adminLevelSystemIdForBenefitLevel;
	}
	/**
	 * @param adminLevelSystemIdForBenefitLevel The adminLevelSystemIdForBenefitLevel to set.
	 */
	public void setAdminLevelSystemIdForBenefitLevel(
			String adminLevelSystemIdForBenefitLevel) {
		this.adminLevelSystemIdForBenefitLevel = adminLevelSystemIdForBenefitLevel;
	}
	/**
	 * @return Returns the adminList.
	 */
	public List getAdminList() {
		return adminList;
	}
	/**
	 * @param adminList The adminList to set.
	 */
	public void setAdminList(List adminList) {
		this.adminList = adminList;
	}
	/**
	 * @return Returns the adminOptionDesc.
	 */
	public String getAdminOptionDesc() {
		return adminOptionDesc;
	}
	/**
	 * @param adminOptionDesc The adminOptionDesc to set.
	 */
	public void setAdminOptionDesc(String adminOptionDesc) {
		this.adminOptionDesc = adminOptionDesc;
	}
	/**
	 * @return Returns the adminOptionIdFromMaster.
	 */
	public int getAdminOptionIdFromMaster() {
		return adminOptionIdFromMaster;
	}
	/**
	 * @param adminOptionIdFromMaster The adminOptionIdFromMaster to set.
	 */
	public void setAdminOptionIdFromMaster(int adminOptionIdFromMaster) {
		this.adminOptionIdFromMaster = adminOptionIdFromMaster;
	}
	/**
	 * @return Returns the adminOptionReferenceId.
	 */
	public String getAdminOptionReferenceId() {
		return adminOptionReferenceId;
	}
	/**
	 * @param adminOptionReferenceId The adminOptionReferenceId to set.
	 */
	public void setAdminOptionReferenceId(String adminOptionReferenceId) {
		this.adminOptionReferenceId = adminOptionReferenceId;
	}
	/**
	 * @return Returns the adminOptionSysIdForUpdate.
	 */
	public int getAdminOptionSysIdForUpdate() {
		return adminOptionSysIdForUpdate;
	}
	/**
	 * @param adminOptionSysIdForUpdate The adminOptionSysIdForUpdate to set.
	 */
	public void setAdminOptionSysIdForUpdate(int adminOptionSysIdForUpdate) {
		this.adminOptionSysIdForUpdate = adminOptionSysIdForUpdate;
	}
	/**
	 * @return Returns the adminOptionSystemId.
	 */
	public int getAdminOptionSystemId() {
		return adminOptionSystemId;
	}
	/**
	 * @param adminOptionSystemId The adminOptionSystemId to set.
	 */
	public void setAdminOptionSystemId(int adminOptionSystemId) {
		this.adminOptionSystemId = adminOptionSystemId;
	}
	/**
	 * @return Returns the adminQuestionHideFlag.
	 */
	public String getAdminQuestionHideFlag() {
		return adminQuestionHideFlag;
	}
	/**
	 * @param adminQuestionHideFlag The adminQuestionHideFlag to set.
	 */
	public void setAdminQuestionHideFlag(String adminQuestionHideFlag) {
		this.adminQuestionHideFlag = adminQuestionHideFlag;
	}
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
	 * @return Returns the answerDescription.
	 */
	public String getAnswerDescription() {
		return answerDescription;
	}
	/**
	 * @param answerDescription The answerDescription to set.
	 */
	public void setAnswerDescription(String answerDescription) {
		this.answerDescription = answerDescription;
	}
	/**
	 * @return Returns the benefitAdminSystemId.
	 */
	public int getBenefitAdminSystemId() {
		return benefitAdminSystemId;
	}
	/**
	 * @param benefitAdminSystemId The benefitAdminSystemId to set.
	 */
	public void setBenefitAdminSystemId(int benefitAdminSystemId) {
		this.benefitAdminSystemId = benefitAdminSystemId;
	}
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
	 * @return Returns the benefitLevelDesc.
	 */
	public String getBenefitLevelDesc() {
		return benefitLevelDesc;
	}
	/**
	 * @param benefitLevelDesc The benefitLevelDesc to set.
	 */
	public void setBenefitLevelDesc(String benefitLevelDesc) {
		this.benefitLevelDesc = benefitLevelDesc;
	}
	/**
	 * @return Returns the benefitLevelSysId.
	 */
	public int getBenefitLevelSysId() {
		return benefitLevelSysId;
	}
	/**
	 * @param benefitLevelSysId The benefitLevelSysId to set.
	 */
	public void setBenefitLevelSysId(int benefitLevelSysId) {
		this.benefitLevelSysId = benefitLevelSysId;
	}
	/**
	 * @return Returns the benefitLevelSysIdFromMaster.
	 */
	public int getBenefitLevelSysIdFromMaster() {
		return benefitLevelSysIdFromMaster;
	}
	/**
	 * @param benefitLevelSysIdFromMaster The benefitLevelSysIdFromMaster to set.
	 */
	public void setBenefitLevelSysIdFromMaster(int benefitLevelSysIdFromMaster) {
		this.benefitLevelSysIdFromMaster = benefitLevelSysIdFromMaster;
	}
	/**
	 * @return Returns the benefitLevelSystemId.
	 */
	public int getBenefitLevelSystemId() {
		return benefitLevelSystemId;
	}
	/**
	 * @param benefitLevelSystemId The benefitLevelSystemId to set.
	 */
	public void setBenefitLevelSystemId(int benefitLevelSystemId) {
		this.benefitLevelSystemId = benefitLevelSystemId;
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
	 * @return Returns the entityId.
	 */
	public int getEntityId() {
		return entityId;
	}
	/**
	 * @param entityId The entityId to set.
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
	 * @param entityType The entityType to set.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	/**
	 * @return Returns the flag.
	 */
	public String getFlag() {
		return flag;
	}
	/**
	 * @param flag The flag to set.
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	/**
	 * @return Returns the isOpen.
	 */
	public String getIsOpen() {
		return isOpen;
	}
	/**
	 * @param isOpen The isOpen to set.
	 */
	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
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
	 * @return Returns the questionHideFlag.
	 */
	public String getQuestionHideFlag() {
		return questionHideFlag;
	}
	/**
	 * @param questionHideFlag The questionHideFlag to set.
	 */
	public void setQuestionHideFlag(String questionHideFlag) {
		this.questionHideFlag = questionHideFlag;
	}
	/**
	 * @return Returns the sequenceNumber.
	 */
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	/**
	 * @param sequenceNumber The sequenceNumber to set.
	 */
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	/**
	 * @return Returns the productSysId.
	 */
	public int getProductSysId() {
		return productSysId;
	}
	/**
	 * @param productSysId The productSysId to set.
	 */
	public void setProductSysId(int productSysId) {
		this.productSysId = productSysId;
	}
}
