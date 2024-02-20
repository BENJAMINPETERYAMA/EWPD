/*
 * ContractLocateCriteriaVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.vo;

import java.util.Date;
import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractLocateCriteriaVO {
	
	private String contractId;
	
	private List contractType;
	
	private List lob;
	
	private String productFamily;
	
	private List businessEntity;
	
	private String productCode;
	
	private String headQuarters;
	
	private Date startDate;
	
	private Date endDate;
	
	private String groupSize;
	
	private String fundingArrangement;
	
	private String brandName;
	
	private String baseContractCode;
	
	private String corporatePlanCode;
	
	private String standardPlanCode;
	
	private String createdBy;
	
	private Date createdDateStart;
	
	private Date createdDateEnd;

	private String updatedBy;
	
	private Date updatedDateStart;
	
	private Date updatedDateEnd;

	private String product;
	
	private List groupName;
	
	private List contractBenefitDefinitionsList;
	/*START CARS*/
	private List marketBusinessUnit;
	/*END CARS*/
	


	
	/**
	 * Returns the contractId
	 * @return String contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * Sets the contractId
	 * @param contractId.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
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
	 * Returns the createdDateEnd
	 * @return Date createdDateEnd.
	 */
	public Date getCreatedDateEnd() {
		return createdDateEnd;
	}
	/**
	 * Sets the createdDateEnd
	 * @param createdDateEnd.
	 */
	public void setCreatedDateEnd(Date createdDateEnd) {
		this.createdDateEnd = createdDateEnd;
	}
	/**
	 * Returns the createdDateStart
	 * @return Date createdDateStart.
	 */
	public Date getCreatedDateStart() {
		return createdDateStart;
	}
	/**
	 * Sets the createdDateStart
	 * @param createdDateStart.
	 */
	public void setCreatedDateStart(Date createdDateStart) {
		this.createdDateStart = createdDateStart;
	}
	/**
	 * Returns the endDate
	 * @return Date endDate.
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * Sets the endDate
	 * @param endDate.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * Returns the startDate
	 * @return Date startDate.
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * Sets the startDate
	 * @param startDate.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * Returns the updatedDateEnd
	 * @return Date updatedDateEnd.
	 */
	public Date getUpdatedDateEnd() {
		return updatedDateEnd;
	}
	/**
	 * Sets the updatedDateEnd
	 * @param updatedDateEnd.
	 */
	public void setUpdatedDateEnd(Date updatedDateEnd) {
		this.updatedDateEnd = updatedDateEnd;
	}
	/**
	 * Returns the updatedDateStart
	 * @return Date updatedDateStart.
	 */
	public Date getUpdatedDateStart() {
		return updatedDateStart;
	}
	/**
	 * Sets the updatedDateStart
	 * @param updatedDateStart.
	 */
	public void setUpdatedDateStart(Date updatedDateStart) {
		this.updatedDateStart = updatedDateStart;
	}
    /**
     * Returns the contractBenefitDefinitionsList
     * @return List contractBenefitDefinitionsList.
     */
    public List getContractBenefitDefinitionsList() {
        return contractBenefitDefinitionsList;
    }
    /**
     * Sets the contractBenefitDefinitionsList
     * @param contractBenefitDefinitionsList.
     */
    public void setContractBenefitDefinitionsList(
            List contractBenefitDefinitionsList) {
        this.contractBenefitDefinitionsList = contractBenefitDefinitionsList;
    }
	/**
	 * @return Returns the businessEntity.
	 */
	public List getBusinessEntity() {
		return businessEntity;
	}
	/**
	 * @param businessEntity The businessEntity to set.
	 */
	public void setBusinessEntity(List businessEntity) {
		this.businessEntity = businessEntity;
	}
	/**
	 * @return Returns the contractType.
	 */
	public List getContractType() {
		return contractType;
	}
	/**
	 * @param contractType The contractType to set.
	 */
	public void setContractType(List contractType) {
		this.contractType = contractType;
	}
	/**
	 * @return Returns the groupName.
	 */
	public List getGroupName() {
		return groupName;
	}
	/**
	 * @param groupName The groupName to set.
	 */
	public void setGroupName(List groupName) {
		this.groupName = groupName;
	}
	/**
	 * @return Returns the lob.
	 */
	public List getLob() {
		return lob;
	}
	/**
	 * @param lob The lob to set.
	 */
	public void setLob(List lob) {
		this.lob = lob;
	}
	/*START CARS*/
	/**
	 * @return Returns the marketBusinessUnit.
	 */
	public List getMarketBusinessUnit() {
		return marketBusinessUnit;
	}
	/**
	 * @param marketBusinessUnit The marketBusinessUnit to set.
	 */
	public void setMarketBusinessUnit(List marketBusinessUnit) {
		this.marketBusinessUnit = marketBusinessUnit;
	}
	/*END CARS*/
}
