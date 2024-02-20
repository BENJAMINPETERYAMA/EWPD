/*
 * BenefitTierBindingObject.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.override.benefit.bo;

import java.util.Date;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 * Class to bind the Benefit tier attributes to the adapter XML, since adapter XML doesn't support more than one update.
 */
public class BenefitTierBindingObject {
	
	private int benefitLineId;
	
	private int benefitLevelId;
	
	private String lineValue;
	
	private int tierDefinitionId;
	
	private int benefitTierId;
	
	private int tierCriteriaId;
	
	private String criteriaValue;
	
	private String lastUpdatedUser;

	private Date lastUpdatedTimestamp;
	//CARS START
	private String levelDescription;
	
	private int frequencyValue;
	//CARS END

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

	public int getBenefitLineId() {
		return benefitLineId;
	}

	public void setBenefitLineId(int benefitLineId) {
		this.benefitLineId = benefitLineId;
	}

	public String getLineValue() {
		return lineValue;
	}

	public void setLineValue(String lineValue) {
		this.lineValue = lineValue;
	}

	public int getBenefitLevelId() {
		return benefitLevelId;
	}

	public void setBenefitLevelId(int benefitLevelId) {
		this.benefitLevelId = benefitLevelId;
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
}

