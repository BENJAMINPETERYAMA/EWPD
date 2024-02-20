/*
 * Created on Nov 26, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminoption.vo;

/**
 * @author u16223
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminOptionHideVO {
	
	private int adminOptionId;
	private int adminLevelId;
	private int benefitLevelId;
	private String questionHideFlag;
	private int entityId;
	private String entityType;
	private int benefitComponentId;
	private int admnLvlAsscId;
	private int adminQuestionNumber;
	private int benefitAdminId;
	
	/**
	 * @return Returns the adminLevelId.
	 */
	public int getAdminLevelId() {
		return adminLevelId;
	}
	/**
	 * @param adminLevelId The adminLevelId to set.
	 */
	public void setAdminLevelId(int adminLevelId) {
		this.adminLevelId = adminLevelId;
	}
	/**
	 * @return Returns the adminOptionId.
	 */
	public int getAdminOptionId() {
		return adminOptionId;
	}
	/**
	 * @param adminOptionId The adminOptionId to set.
	 */
	public void setAdminOptionId(int adminOptionId) {
		this.adminOptionId = adminOptionId;
	}
	/**
	 * @return Returns the benefitLevelId.
	 */
	public int getBenefitLevelId() {
		return benefitLevelId;
	}
	/**
	 * @param benefitLevelId The benefitLevelId to set.
	 */
	public void setBenefitLevelId(int benefitLevelId) {
		this.benefitLevelId = benefitLevelId;
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
	 * @return Returns the admnLvlAsscId.
	 */
	public int getAdmnLvlAsscId() {
		return admnLvlAsscId;
	}
	/**
	 * @param admnLvlAsscId The admnLvlAsscId to set.
	 */
	public void setAdmnLvlAsscId(int admnLvlAsscId) {
		this.admnLvlAsscId = admnLvlAsscId;
	}
	/**
	 * @return Returns the benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * @param benefitComponentId The benefitComponentId to set.
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
	 * @return Returns the benefitAdminId.
	 */
	public int getBenefitAdminId() {
		return benefitAdminId;
	}
	/**
	 * @param benefitAdminId The benefitAdminId to set.
	 */
	public void setBenefitAdminId(int benefitAdminId) {
		this.benefitAdminId = benefitAdminId;
	}
}
