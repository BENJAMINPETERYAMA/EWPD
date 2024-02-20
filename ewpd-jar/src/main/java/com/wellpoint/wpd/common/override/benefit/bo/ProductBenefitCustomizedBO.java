/*
 * Created on Nov 27, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.override.benefit.bo;

import java.util.Date;

public class ProductBenefitCustomizedBO {
	
	private int productSysId;
	
	private int benefitComponentSysId;
	
	private int benefitSysId;
	
	private int benefitLevelId;
	
	private int benefitDefinitionSysId;
	
	private int benefitLineId;
	
	private String benefitValue;
	
	private String benefitHideFlag;
	
	private String levelHideFlag;
	
	private String lineHideFlag;

	private String createdUser;
	
	private Date createdTimestamp;
	
	private String lastUpdatedUser;
	
	private Date lastUpdatedTimestamp;
	
	private int productStructureSysId;
	
	private int productBenefitCustomizedSysId;
	//CARS START
    //DESCRIPTION : integer to hold Frequency value. 
    private int frequencyValue;
    //DESCRIPTION : String to hold Level description value.
    private String levelDesc;
	
	/**
	 * @return Returns the benefitComponentSysId.
	 */
	public int getBenefitComponentSysId() {
		return benefitComponentSysId;
	}
	/**
	 * @param benefitComponentSysId The benefitComponentSysId to set.
	 */
	public void setBenefitComponentSysId(int benefitComponentSysId) {
		this.benefitComponentSysId = benefitComponentSysId;
	}
	/**
	 * @return Returns the benefitDefinitionSysId.
	 */
	public int getBenefitDefinitionSysId() {
		return benefitDefinitionSysId;
	}
	/**
	 * @param benefitDefinitionSysId The benefitDefinitionSysId to set.
	 */
	public void setBenefitDefinitionSysId(int benefitDefinitionSysId) {
		this.benefitDefinitionSysId = benefitDefinitionSysId;
	}
	/**
	 * @return Returns the benefitHideFlag.
	 */
	public String getBenefitHideFlag() {
		return benefitHideFlag;
	}
	/**
	 * @param benefitHideFlag The benefitHideFlag to set.
	 */
	public void setBenefitHideFlag(String benefitHideFlag) {
		this.benefitHideFlag = benefitHideFlag;
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
	 * @return Returns the benefitLineId.
	 */
	public int getBenefitLineId() {
		return benefitLineId;
	}
	/**
	 * @param benefitLineId The benefitLineId to set.
	 */
	public void setBenefitLineId(int benefitLineId) {
		this.benefitLineId = benefitLineId;
	}
	/**
	 * @return Returns the benefitSysId.
	 */
	public int getBenefitSysId() {
		return benefitSysId;
	}
	/**
	 * @param benefitSysId The benefitSysId to set.
	 */
	public void setBenefitSysId(int benefitSysId) {
		this.benefitSysId = benefitSysId;
	}
	/**
	 * @return Returns the benefitValue.
	 */
	public String getBenefitValue() {
		return benefitValue;
	}
	/**
	 * @param benefitValue The benefitValue to set.
	 */
	public void setBenefitValue(String benefitValue) {
		this.benefitValue = benefitValue;
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
	 * @return Returns the productStructureSysId.
	 */
	public int getProductStructureSysId() {
		return productStructureSysId;
	}
	/**
	 * @param productStructureSysId The productStructureSysId to set.
	 */
	public void setProductStructureSysId(int productStructureSysId) {
		this.productStructureSysId = productStructureSysId;
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
	/**
	 * @return Returns the levelHideFlag.
	 */
	public String getLevelHideFlag() {
		return levelHideFlag;
	}
	/**
	 * @param levelHideFlag The levelHideFlag to set.
	 */
	public void setLevelHideFlag(String levelHideFlag) {
		this.levelHideFlag = levelHideFlag;
	}
	/**
	 * @return Returns the lineHideFlag.
	 */
	public String getLineHideFlag() {
		return lineHideFlag;
	}
	/**
	 * @param lineHideFlag The lineHideFlag to set.
	 */
	public void setLineHideFlag(String lineHideFlag) {
		this.lineHideFlag = lineHideFlag;
	}
	/**
	 * @return Returns the productBenefitCustomizedSysId.
	 */
	public int getProductBenefitCustomizedSysId() {
		return productBenefitCustomizedSysId;
	}
	/**
	 * @param productBenefitCustomizedSysId The productBenefitCustomizedSysId to set.
	 */
	public void setProductBenefitCustomizedSysId(
			int productBenefitCustomizedSysId) {
		this.productBenefitCustomizedSysId = productBenefitCustomizedSysId;
	}
	/**
	 * @return Returns the frequencyValue.
	 */
	public int getFrequencyValue() {
		return frequencyValue;
	}
	/**
	 * @param frequencyValue The frequencyValue to set.
	 */
	public void setFrequencyValue(int frequencyValue) {
		this.frequencyValue = frequencyValue;
	}
	/**
	 * @return Returns the levelDesc.
	 */
	public String getLevelDesc() {
		return levelDesc;
	}
	/**
	 * @param levelDesc The levelDesc to set.
	 */
	public void setLevelDesc(String levelDesc) {
		this.levelDesc = levelDesc;
	}
}
