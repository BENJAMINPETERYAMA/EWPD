package com.wellpoint.wpd.common.override.benefit.bo;

import java.util.Date;

public class ContractTierBindingObject {
	
	private int benefitLineId;
	
	private int benefitLevelId;
	
	private String lineValue;
	
	private int tierDefinitionId;
	
	private int benefitTierId;
	
	private int tierCriteriaId;
	
	private String criteriaValue;
	
	private String lastUpdatedUser;

	private Date lastUpdatedTimestamp;
	
	/* Start CARS */
	private String levelDescription;
	
	private int frequencyValue;
	/* End CARS */	

	public int getBenefitLevelId() {
		return benefitLevelId;
	}

	public void setBenefitLevelId(int benefitLevelId) {
		this.benefitLevelId = benefitLevelId;
	}

	public int getBenefitLineId() {
		return benefitLineId;
	}

	public void setBenefitLineId(int benefitLineId) {
		this.benefitLineId = benefitLineId;
	}

	public int getBenefitTierId() {
		return benefitTierId;
	}

	public void setBenefitTierId(int benefitTierId) {
		this.benefitTierId = benefitTierId;
	}

	public String getCriteriaValue() {
		return criteriaValue;
	}

	public void setCriteriaValue(String criteriaValue) {
		this.criteriaValue = criteriaValue;
	}

	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}

	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}

	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}

	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}

	public String getLineValue() {
		return lineValue;
	}

	public void setLineValue(String lineValue) {
		this.lineValue = lineValue;
	}

	public int getTierCriteriaId() {
		return tierCriteriaId;
	}

	public void setTierCriteriaId(int tierCriteriaId) {
		this.tierCriteriaId = tierCriteriaId;
	}

	public int getTierDefinitionId() {
		return tierDefinitionId;
	}

	public void setTierDefinitionId(int tierDefinitionId) {
		this.tierDefinitionId = tierDefinitionId;
	}
/* Start CARS */		
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
	 * @return Returns the levelDescription.
	 */
	public String getLevelDescription() {
		return levelDescription;
	}
	/**
	 * @param levelDescription The levelDescription to set.
	 */
	public void setLevelDescription(String levelDescription) {
		this.levelDescription = levelDescription;
	}
/* End CARS */	
}
