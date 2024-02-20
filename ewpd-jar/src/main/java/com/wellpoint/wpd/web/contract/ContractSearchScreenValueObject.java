/*
 * ContractScreenValueObject.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;

import java.util.HashMap;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractSearchScreenValueObject {
	
	private String contractId;
	
	private String contractType;
	
	private String lob;
	
	private String productFamily;
	
	private String businessEntity;
	
	private String productCode;
	
	private String headQuarters;
	
	private String startDate;
	
	private String endDate;
	
	private String groupSize;
	
	private String fundingArrangement;
	
	private String brandName;
	
	private String baseContractCode;
	
	private String corporatePlanCode;
	
	private String standardPlanCode;
	
	private String createdBy;
	
	private String createdDateStart;
	
	private String createdDateEnd;
	
	private String updatedBy;
	
	private String updatedDateStart;
	
	private String updatedDateEnd;
	
	private String product;
	
	private String groupName;
	
	private HashMap benefitDefinitionsMap;
	
	


	/**
	 * @return Returns the businessEntity.
	 */
	public String getBusinessEntity() {
		return businessEntity;
	}
	/**
	 * @param businessEntity The businessEntity to set.
	 */
	public void setBusinessEntity(String businessEntity) {
		this.businessEntity = businessEntity;
	}
	/**
	 * @return Returns the contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * @param contractId The contractId to set.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * @return Returns the contractType.
	 */
	public String getContractType() {
		return contractType;
	}
	/**
	 * @param contractType The contractType to set.
	 */
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	/**
	 * @return Returns the endDate.
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return Returns the headQuarters.
	 */
	public String getHeadQuarters() {
		return headQuarters;
	}
	/**
	 * @param headQuarters The headQuarters to set.
	 */
	public void setHeadQuarters(String headQuarters) {
		this.headQuarters = headQuarters;
	}
	/**
	 * @return Returns the lob.
	 */
	public String getLob() {
		return lob;
	}
	/**
	 * @param lob The lob to set.
	 */
	public void setLob(String lob) {
		this.lob = lob;
	}
	/**
	 * @return Returns the productCode.
	 */
	public String getProductCode() {
		return productCode;
	}
	/**
	 * @param productCode The productCode to set.
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	/**
	 * @return Returns the productFamily.
	 */
	public String getProductFamily() {
		return productFamily;
	}
	/**
	 * @param productFamily The productFamily to set.
	 */
	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}
	/**
	 * @return Returns the startDate.
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * Returns the baseContractCode
	 * @return String baseContractCode.
	 */
	public String getBaseContractCode() {
		return baseContractCode;
	}
	/**
	 * Sets the baseContractCode
	 * @param baseContractCode.
	 */
	public void setBaseContractCode(String baseContractCode) {
		this.baseContractCode = baseContractCode;
	}
	/**
	 * Returns the brandName
	 * @return String brandName.
	 */
	public String getBrandName() {
		return brandName;
	}
	/**
	 * Sets the brandName
	 * @param brandName.
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	/**
	 * Returns the corporatePlanCode
	 * @return String corporatePlanCode.
	 */
	public String getCorporatePlanCode() {
		return corporatePlanCode;
	}
	/**
	 * Sets the corporatePlanCode
	 * @param corporatePlanCode.
	 */
	public void setCorporatePlanCode(String corporatePlanCode) {
		this.corporatePlanCode = corporatePlanCode;
	}
	/**
	 * Returns the createdBy
	 * @return String createdBy.
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * Sets the createdBy
	 * @param createdBy.
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * Returns the createdDateEnd
	 * @return String createdDateEnd.
	 */
	public String getCreatedDateEnd() {
		return createdDateEnd;
	}
	/**
	 * Sets the createdDateEnd
	 * @param createdDateEnd.
	 */
	public void setCreatedDateEnd(String createdDateEnd) {
		this.createdDateEnd = createdDateEnd;
	}
	/**
	 * Returns the createdDateStart
	 * @return String createdDateStart.
	 */
	public String getCreatedDateStart() {
		return createdDateStart;
	}
	/**
	 * Sets the createdDateStart
	 * @param createdDateStart.
	 */
	public void setCreatedDateStart(String createdDateStart) {
		this.createdDateStart = createdDateStart;
	}
	/**
	 * Returns the fundingArrangement
	 * @return String fundingArrangement.
	 */
	public String getFundingArrangement() {
		return fundingArrangement;
	}
	/**
	 * Sets the fundingArrangement
	 * @param fundingArrangement.
	 */
	public void setFundingArrangement(String fundingArrangement) {
		this.fundingArrangement = fundingArrangement;
	}
	/**
	 * Returns the groupName
	 * @return String groupName.
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * Sets the groupName
	 * @param groupName.
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * Returns the groupSize
	 * @return String groupSize.
	 */
	public String getGroupSize() {
		return groupSize;
	}
	/**
	 * Sets the groupSize
	 * @param groupSize.
	 */
	public void setGroupSize(String groupSize) {
		this.groupSize = groupSize;
	}
	/**
	 * Returns the product
	 * @return String product.
	 */
	public String getProduct() {
		return product;
	}
	/**
	 * Sets the product
	 * @param product.
	 */
	public void setProduct(String product) {
		this.product = product;
	}
	/**
	 * Returns the standardPlanCode
	 * @return String standardPlanCode.
	 */
	public String getStandardPlanCode() {
		return standardPlanCode;
	}
	/**
	 * Sets the standardPlanCode
	 * @param standardPlanCode.
	 */
	public void setStandardPlanCode(String standardPlanCode) {
		this.standardPlanCode = standardPlanCode;
	}
	/**
	 * Returns the updatedBy
	 * @return String updatedBy.
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * Sets the updatedBy
	 * @param updatedBy.
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * Returns the updatedDateEnd
	 * @return String updatedDateEnd.
	 */
	public String getUpdatedDateEnd() {
		return updatedDateEnd;
	}
	/**
	 * Sets the updatedDateEnd
	 * @param updatedDateEnd.
	 */
	public void setUpdatedDateEnd(String updatedDateEnd) {
		this.updatedDateEnd = updatedDateEnd;
	}
	/**
	 * Returns the updatedDateStart
	 * @return String updatedDateStart.
	 */
	public String getUpdatedDateStart() {
		return updatedDateStart;
	}
	/**
	 * Sets the updatedDateStart
	 * @param updatedDateStart.
	 */
	public void setUpdatedDateStart(String updatedDateStart) {
		this.updatedDateStart = updatedDateStart;
	}
    /**
     * Returns the benefitDefinitionsMap
     * @return HashMap benefitDefinitionsMap.
     */
    public HashMap getBenefitDefinitionsMap() {
        return benefitDefinitionsMap;
    }
    /**
     * Sets the benefitDefinitionsMap
     * @param benefitDefinitionsMap.
     */
    public void setBenefitDefinitionsMap(HashMap benefitDefinitionsMap) {
        this.benefitDefinitionsMap = benefitDefinitionsMap;
    }
}
