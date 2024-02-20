/*
 * ContractTierInformationBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractTierInformationBO {
	
	private int contractTierSysId;
	
	private int tierCriteriaSysId;
	
	private String criteriaValue;

	private String tierCode;
	
	private String tierDescription;
	
	private String tierCriteriaName;
	
	private String refrenceId;
	
	private String tierFormat;
	
	private String tierCriteriaIndicator;
	
	/**
	 * @return Returns the contractTierSysId.
	 */
	public int getContractTierSysId() {
		return contractTierSysId;
	}
	/**
	 * @param contractTierSysId The contractTierSysId to set.
	 */
	public void setContractTierSysId(int contractTierSysId) {
		this.contractTierSysId = contractTierSysId;
	}
	/**
	 * @return Returns the criteriaValue.
	 */
	public String getCriteriaValue() {
		return criteriaValue;
	}
	/**
	 * @param criteriaValue The criteriaValue to set.
	 */
	public void setCriteriaValue(String criteriaValue) {
		this.criteriaValue = criteriaValue;
	}
	/**
	 * @return Returns the refrenceId.
	 */
	public String getRefrenceId() {
		return refrenceId;
	}
	/**
	 * @param refrenceId The refrenceId to set.
	 */
	public void setRefrenceId(String refrenceId) {
		this.refrenceId = refrenceId;
	}
	/**
	 * @return Returns the tierCode.
	 */
	public String getTierCode() {
		return tierCode;
	}
	/**
	 * @param tierCode The tierCode to set.
	 */
	public void setTierCode(String tierCode) {
		this.tierCode = tierCode;
	}
	/**
	 * @return Returns the tierCriteriaName.
	 */
	public String getTierCriteriaName() {
		return tierCriteriaName;
	}
	/**
	 * @param tierCriteriaName The tierCriteriaName to set.
	 */
	public void setTierCriteriaName(String tierCriteriaName) {
		this.tierCriteriaName = tierCriteriaName;
	}
	/**
	 * @return Returns the tierCriteriaSysId.
	 */
	public int getTierCriteriaSysId() {
		return tierCriteriaSysId;
	}
	/**
	 * @param tierCriteriaSysId The tierCriteriaSysId to set.
	 */
	public void setTierCriteriaSysId(int tierCriteriaSysId) {
		this.tierCriteriaSysId = tierCriteriaSysId;
	}
	/**
	 * @return Returns the tierDescription.
	 */
	public String getTierDescription() {
		return tierDescription;
	}
	/**
	 * @param tierDescription The tierDescription to set.
	 */
	public void setTierDescription(String tierDescription) {
		this.tierDescription = tierDescription;
	}
	/**
	 * @return Returns the tierFormat.
	 */
	public String getTierFormat() {
		return tierFormat;
	}
	/**
	 * @param tierFormat The tierFormat to set.
	 */
	public void setTierFormat(String tierFormat) {
		this.tierFormat = tierFormat;
	}
	public String getTierCriteriaIndicator() {
		return tierCriteriaIndicator;
	}
	public void setTierCriteriaIndicator(String tierCriteriaIndicator) {
		this.tierCriteriaIndicator = tierCriteriaIndicator;
	}
}
