/*
 * LegacyContract.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.legacycontract.bo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public class LegacyContract extends LegacyObject {
	private String contractId;
	private Date startDate;
	private Date endDate;
	private String benefitYearAccumInd;
	private String productCode;
	private String productFamily;
	private String benefitPlan;
	private String standardPlanCode;
	private String corporatePlanCode;
	private String groupName;
	private String headQuarterState;
	private String benefitStructure;
	private String businessEntity;
	private String contractStat;
	private String createdUser;
	private Date createdTimestamp;
	private String lastUpdatedUser;
	private Date lastUpdatedTimestamp;
	private String ModelIndicator;
	private String note;
    /**
     * @return Returns the modelIndicator.
     */
    public String getModelIndicator() {
        return ModelIndicator;
    }
    /**
     * @param modelIndicator The modelIndicator to set.
     */
    public void setModelIndicator(String modelIndicator) {
        ModelIndicator = modelIndicator;
    }

	
	// Associated Information
	// Pricing
	private List pricing;
	// Exclusions
	private List limitClassExclusions;
	private List serviceClassExclusions;
	private List serviceCodeExclusions;
	private List specialityCodeExclusions;
	// Coded Variables
	private List codedVariables;

	
	public LegacyContract(String system){
		super.setSystem(system);
	}
	
	public LegacyContract(){
		
	}
	
	public LegacyContract(LegacyContract contract) {
		copyFrom(contract);
	}
	protected void copyFrom(LegacyContract contract){
		super.setSystem(contract.getSystem());
		this.contractId = contract.getContractId();
		this.startDate = contract.getStartDate();
		this.endDate = contract.getEndDate();
		this.productCode = contract.getProductCode();
		this.productFamily = contract.getProductFamily();
		this.benefitPlan = contract.getBenefitPlan();
		this.standardPlanCode = contract.getStandardPlanCode();
		this.corporatePlanCode = contract.getCorporatePlanCode();
		this.groupName = contract.getGroupName();
		this.headQuarterState = contract.getHeadQuarterState();
		this.benefitStructure = contract.getBenefitStructure();
		this.businessEntity = contract.getBusinessEntity();
		this.contractStat = contract.getContractStat();
		this.createdUser = contract.getCreatedUser();
		this.createdTimestamp = contract.getCreatedTimestamp();
		this.lastUpdatedUser = contract.getLastUpdatedUser();
		this.lastUpdatedTimestamp = contract.getLastUpdatedTimestamp();
	}
	
    public String getEndDateString() {
    	String dateString = null;
        if (this.endDate !=  null) {
        	SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        	dateString = df.format(this.endDate);
        }
        return dateString;
	}
    public void setEndDateString(String str) {
    	
    }

	public String getStartDateString() {
    	String dateString = null;
        if (this.startDate !=  null) {
        	SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        	dateString = df.format(this.startDate);
        }
        return dateString;
	}
	public void setStartDateString(String str) {
		
	}
	/**
	 * Returns the contractId.
	 * 
	 * @return String contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * Sets the contractId.
	 * @param contractId.
	 */
	
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * Returns the startDate.
	 * @return Date startDate.
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * Sets the startDate.
	 * @param startDate.
	 */
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * Returns the endDate.
	 * @return Date endDate.
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * Sets the endDate.
	 * @param endDate.
	 */
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * Returns the productCode.
	 * @return String productCode.
	 */
	public String getProductCode() {
		return productCode;
	}
	/**
	 * Sets the productCode.
	 * @param productCode.
	 */
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	/**
	 * Returns the productFamily.
	 * @return String productFamily.
	 */
	public String getProductFamily() {
		return productFamily;
	}
	/**
	 * Sets the productFamily.
	 * @param productFamily.
	 */
	
	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}
	/**
	 * Returns the benefitPlan.
	 * @return String benefitPlan.
	 */
	public String getBenefitPlan() {
		return benefitPlan;
	}
	/**
	 * Sets the benefitPlan.
	 * @param benefitPlan.
	 */
	
	public void setBenefitPlan(String benefitPlan) {
		this.benefitPlan = benefitPlan;
	}
	/**
	 * Returns the standardPlanCode.
	 * @return String standardPlanCode.
	 */
	public String getStandardPlanCode() {
		return standardPlanCode;
	}
	/**
	 * Sets the standardPlanCode.
	 * @param standardPlanCode.
	 */
	
	public void setStandardPlanCode(String standardPlanCode) {
		this.standardPlanCode = standardPlanCode;
	}
	/**
	 * Returns the corporatePlanCode.
	 * @return String corporatePlanCode.
	 */
	public String getCorporatePlanCode() {
		return corporatePlanCode;
	}
	/**
	 * Sets the corporatePlanCode.
	 * @param corporatePlanCode.
	 */
	
	public void setCorporatePlanCode(String corporatePlanCode) {
		this.corporatePlanCode = corporatePlanCode;
	}
	/**
	 * Returns the groupName.
	 * @return String groupName.
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * Sets the groupName.
	 * @param groupName.
	 */
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * Returns the headQuarterState.
	 * @return String headQuarterState.
	 */
	public String getHeadQuarterState() {
		return headQuarterState;
	}
	/**
	 * Sets the headQuarterState.
	 * @param headQuarterState.
	 */
	
	public void setHeadQuarterState(String headQuarterState) {
		this.headQuarterState = headQuarterState;
	}

	/**
	 * Returns the benefitStructure.
	 * @return String benefitStructure.
	 */
	public String getBenefitStructure() {
		return benefitStructure;
	}
	/**
	 * Sets the benefitStructure.
	 * @param benefitStructure.
	 */
	
	public void setBenefitStructure(String benefitStructure) {
		this.benefitStructure = benefitStructure;
	}
	/**
	 * Returns the businessEntity.
	 * @return String businessEntity.
	 */
	public String getBusinessEntity() {
		return businessEntity;
	}
	/**
	 * Sets the businessEntity.
	 * @param businessEntity.
	 */
	
	public void setBusinessEntity(String businessEntity) {
		this.businessEntity = businessEntity;
	}
	/**
	 * Returns the contractStat.
	 * @return String contractStat.
	 */
	public String getContractStat() {
		return contractStat;
	}
	/**
	 * Sets the contractStat.
	 * @param contractStat.
	 */
	
	public void setContractStat(String contractStat) {
		this.contractStat = contractStat;
	}
	/**
	 * Returns the createdUser.
	 * @return String createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * Sets the createdUser.
	 * @param createdUser.
	 */
	
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * Returns the createdTimestamp.
	 * @return Date createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * Sets the createdTimestamp.
	 * @param createdTimestamp.
	 */
	
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	/**
	 * Returns the lastUpdatedUser.
	 * @return String lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * Sets the lastUpdatedUser.
	 * @param lastUpdatedUser.
	 */
	
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * Returns the lastUpdatedTimestamp.
	 * @return Date lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * Sets the lastUpdatedTimestamp.
	 * @param lastUpdatedTimestamp.
	 */
	
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
	/**
	 * Returns the pricing.
	 * @return List pricing.
	 */
	public List getPricing() {
		return pricing;
	}
	/**
	 * Sets the pricing.
	 * @param pricing.
	 */
	
	public void setPricing(List pricing) {
		this.pricing = pricing;
	}
	/**
	 * Returns the limitClassExclusions.
	 * @return List limitClassExclusions.
	 */
	public List getLimitClassExclusions() {
		return limitClassExclusions;
	}
	/**
	 * Sets the limitClassExclusions.
	 * @param limitClassExclusions.
	 */
	
	public void setLimitClassExclusions(List limitClassExclusions) {
		this.limitClassExclusions = limitClassExclusions;
	}
	/**
	 * Returns the serviceClassExclusions.
	 * @return List serviceClassExclusions.
	 */
	public List getServiceClassExclusions() {
		return serviceClassExclusions;
	}
	/**
	 * Sets the serviceClassExclusions.
	 * @param serviceClassExclusions.
	 */
	
	public void setServiceClassExclusions(List serviceClassExclusions) {
		this.serviceClassExclusions = serviceClassExclusions;
	}
	/**
	 * Returns the serviceCodeExclusions.
	 * @return List serviceCodeExclusions.
	 */
	public List getServiceCodeExclusions() {
		return serviceCodeExclusions;
	}
	/**
	 * Sets the serviceCodeExclusions.
	 * @param serviceCodeExclusions.
	 */
	
	public void setServiceCodeExclusions(List serviceCodeExclusions) {
		this.serviceCodeExclusions = serviceCodeExclusions;
	}
	/**
	 * Returns the codedVariables.
	 * @return List codedVariables.
	 */
	public List getCodedVariables() {
		return codedVariables;
	}
	/**
	 * Sets the codedVariables.
	 * @param codedVariables.
	 */
	
	public void setCodedVariables(List codedVariables) {
		this.codedVariables = codedVariables;
	}

	/**
	 * Returns the benefitYearAccumInd.
	 * @return String benefitYearAccumInd.
	 */
	public String getBenefitYearAccumInd() {
		return benefitYearAccumInd;
	}

	/**
	 * Sets the benefitYearAccumInd.
	 * @param benefitYearAccumInd.
	 */
	
	public void setBenefitYearAccumInd(String benefitYearAccumInd) {
		this.benefitYearAccumInd = benefitYearAccumInd;
	}

	/**
	 * Returns the specialityCodeExclusions.
	 * @return List specialityCodeExclusions.
	 */
	public List getSpecialityCodeExclusions() {
		return specialityCodeExclusions;
	}

	/**
	 * Sets the specialityCodeExclusions.
	 * @param specialityCodeExclusions.
	 */
	
	public void setSpecialityCodeExclusions(List specialityCodeExclusions) {
		this.specialityCodeExclusions = specialityCodeExclusions;
	}
	
	/**
	 * @return Returns the note.
	 */
	public String getNote() {
		return note;
	}
	
	/**
	 * @param note The note to set.
	 */
	public void setNote(String note) {
		this.note = note;
	}
}
