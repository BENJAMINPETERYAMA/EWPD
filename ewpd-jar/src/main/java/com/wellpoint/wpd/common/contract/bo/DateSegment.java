/*
 * DateSegment.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.product.bo.ProductBO;
import com.wellpoint.wpd.web.util.WPDStringUtil;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DateSegment extends BusinessObject {

	

	private String contractId;

	private String dateSegmentType;

	private String comments;

	private int dateSegmentSysId;

	private int oldDateSegmentId;

	private int contractSysId;

	private Date effectiveDate;

	private Date expiryDate;

	private String groupSize;

	private String productCode;

	private String productCodeDesc;

	private String standardPlanCode;

	private String standardPlanCodeDesc;

	private String benefitPlan;

	private String corporatePlanCode;

	private String corporatePlanCodeDesc;

	private String brandName;

	private String fundingArrangement;

	private String productFamily;

	private List pricingInfoList;

	private List commentsList;

	private int productId;

	private String productDesc;

	private String fundingArrangementDesc;

	private String groupSizeDesc;

	private ProductBO productBO;

	private String headQuartersState;

	private String headQuartersStateDesc;

	private String cobAdjudicationIndicator;

	private String medicareAdjudicationIndicator;

	private String itsAdjudicationIndicator;

	private String regulatoryAgency;

	private String complianceStatus;

	private String projectNameCode;

	private Date contractTermDate;

	private Date salesMarketDate;

	private String multiPlanIndicator;

	private String performanceGuarantee;

	private String contractAdjudicationIndicator;

	private String baseContractId;

	private int baseContractSysId;

	private Date baseContractDate;

	private int baseDateSegmentSysId;

	private String isModified;

	private String effectiveDateStr;
	
	private String LastUpdatedUser;
	
	private Date LastUpdatedTimeStamp;

	private List providerSpecCodeList;

	private String blzRuleIndicator;
	
	private String productStatus;
	
	private String isProductLatest;
	
	private ContractStatusBo contractStatusBo;
	
	private String imageRWDAFlag;
	
	private String daterange;
    
    public String getDaterange() {
		return daterange;
	}
	public void setDaterange(String daterange) {
		this.daterange = daterange;
	}	

	public ContractStatusBo getContractStatusBo() {
		return contractStatusBo;
	}

	public void setContractStatusBo(ContractStatusBo contractStatusBo) {
		this.contractStatusBo = contractStatusBo;
	}

	//  -------------------------------- method overriding -----------------------	    
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	//	---------------------------------getters/setters-----------------------	
	/**
	 * Returns the benefitPlan
	 * @return String benefitPlan.
	 */
	public String getBenefitPlan() {
		return benefitPlan;
	}

	/**
	 * Sets the benefitPlan
	 * @param benefitPlan.
	 */
	public void setBenefitPlan(String benefitPlan) {
		this.benefitPlan = benefitPlan;
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
	 * Returns the dateSegmentSysId
	 * @return int dateSegmentSysId.
	 */
	public int getDateSegmentSysId() {
		return dateSegmentSysId;
	}

	/**
	 * Sets the dateSegmentSysId
	 * @param dateSegmentSysId.
	 */
	public void setDateSegmentSysId(int dateSegmentSysId) {
		this.dateSegmentSysId = dateSegmentSysId;
	}

	/**
	 * Returns the effectiveDate
	 * @return Date effectiveDate.
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * Sets the effectiveDate
	 * @param effectiveDate.
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * Returns the expiryDate
	 * @return Date expiryDate.
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * Sets the expiryDate
	 * @param expiryDate.
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * Returns the groupSize
	 * @return List groupSize.
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
	 * Returns the productCode
	 * @return String productCode.
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * Sets the productCode
	 * @param productCode.
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
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
	 * Returns the contractSysId
	 * @return int contractSysId.
	 */
	public int getContractSysId() {
		return contractSysId;
	}

	/**
	 * Sets the contractSysId
	 * @param contractSysId.
	 */
	public void setContractSysId(int contractSysId) {
		this.contractSysId = contractSysId;
	}

	/**
	 * Returns the commentsList
	 * @return List commentsList.
	 */
	public List getCommentsList() {
		return commentsList;
	}

	/**
	 * Sets the commentsList
	 * @param commentsList.
	 */
	public void setCommentsList(List commentsList) {
		this.commentsList = commentsList;
	}

	/**
	 * Returns the pricingInfoList
	 * @return List pricingInfoList.
	 */
	public List getPricingInfoList() {
		return pricingInfoList;
	}

	/**
	 * Sets the pricingInfoList
	 * @param pricingInfoList.
	 */
	public void setPricingInfoList(List pricingInfoList) {
		this.pricingInfoList = pricingInfoList;
	}

	/**
	 * Returns the corporatePlanCodeDesc
	 * @return String corporatePlanCodeDesc.
	 */
	public String getCorporatePlanCodeDesc() {
		return corporatePlanCodeDesc;
	}

	/**
	 * Sets the corporatePlanCodeDesc
	 * @param corporatePlanCodeDesc.
	 */
	public void setCorporatePlanCodeDesc(String corporatePlanCodeDesc) {
		this.corporatePlanCodeDesc = corporatePlanCodeDesc;
	}

	/**
	 * Returns the productCodeDesc
	 * @return String productCodeDesc.
	 */
	public String getProductCodeDesc() {
		return productCodeDesc;
	}

	/**
	 * Sets the productCodeDesc
	 * @param productCodeDesc.
	 */
	public void setProductCodeDesc(String productCodeDesc) {
		if(productCodeDesc != null ) {
			this.productCodeDesc = productCodeDesc.trim().toUpperCase();
		}
	}

	/**
	 * Returns the standardPlanCodeDesc
	 * @return String standardPlanCodeDesc.
	 */
	public String getStandardPlanCodeDesc() {
		return standardPlanCodeDesc;
	}

	/**
	 * Sets the standardPlanCodeDesc
	 * @param standardPlanCodeDesc.
	 */
	public void setStandardPlanCodeDesc(String standardPlanCodeDesc) {
		this.standardPlanCodeDesc = standardPlanCodeDesc;
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
	 * Returns the productFamily
	 * @return String productFamily.
	 */
	public String getProductFamily() {
		return productFamily;
	}

	/**
	 * Sets the productFamily
	 * @param productFamily.
	 */
	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}

	/**
	 * Returns the productDesc
	 * @return int productDesc.
	 */
	public String getProductDesc() {
		return productDesc;
	}

	/**
	 * Sets the productDesc
	 * @param productDesc.
	 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	/**
	 * Returns the productId
	 * @return int productId.
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * Sets the productId
	 * @param productId.
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * Returns the fundingArrangementDesc
	 * @return String fundingArrangementDesc.
	 */
	public String getFundingArrangementDesc() {
		return fundingArrangementDesc;
	}

	/**
	 * Sets the fundingArrangementDesc
	 * @param fundingArrangementDesc.
	 */
	public void setFundingArrangementDesc(String fundingArrangementDesc) {
		this.fundingArrangementDesc = fundingArrangementDesc;
	}

	/**
	 * Returns the groupSizeDesc
	 * @return String groupSizeDesc.
	 */
	public String getGroupSizeDesc() {
		return groupSizeDesc;
	}

	/**
	 * Sets the groupSizeDesc
	 * @param groupSizeDesc.
	 */
	public void setGroupSizeDesc(String groupSizeDesc) {
		this.groupSizeDesc = groupSizeDesc;
	}

	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Returns the productBO
	 * @return ProductBO productBO.
	 */
	public ProductBO getProductBO() {
		return productBO;
	}

	/**
	 * Sets the productBO
	 * @param productBO.
	 */
	public void setProductBO(ProductBO productBO) {
		this.productBO = productBO;
	}

	/**
	 * @return Returns the headQuartersState.
	 */
	public String getHeadQuartersState() {
		return headQuartersState;
	}

	/**
	 * @param headQuartersState The headQuartersState to set.
	 */
	public void setHeadQuartersState(String headQuartersState) {
		this.headQuartersState = headQuartersState;
	}

	/**
	 * @return Returns the headQuartersStateDesc.
	 */
	public String getHeadQuartersStateDesc() {
		return headQuartersStateDesc;
	}

	/**
	 * @param headQuartersStateDesc The headQuartersStateDesc to set.
	 */
	public void setHeadQuartersStateDesc(String headQuartersStateDesc) {
		this.headQuartersStateDesc = headQuartersStateDesc;
	}

	/**
	 * @return Returns the dateSegmentType.
	 */
	public String getDateSegmentType() {
		return dateSegmentType;
	}

	/**
	 * @param dateSegmentType The dateSegmentType to set.
	 */
	public void setDateSegmentType(String dateSegmentType) {
		this.dateSegmentType = dateSegmentType;
	}

	/**
	 * @return Returns the oldDateSegmentId.
	 */
	public int getOldDateSegmentId() {
		return oldDateSegmentId;
	}

	/**
	 * @param oldDateSegmentId The oldDateSegmentId to set.
	 */
	public void setOldDateSegmentId(int oldDateSegmentId) {
		this.oldDateSegmentId = oldDateSegmentId;
	}

	/**
	 * @return Returns the cobAdjudicationIndicator.
	 */
	public String getCobAdjudicationIndicator() {
		return cobAdjudicationIndicator;
	}

	/**
	 * @param cobAdjudicationIndicator The cobAdjudicationIndicator to set.
	 */
	public void setCobAdjudicationIndicator(String cobAdjudicationIndicator) {
		this.cobAdjudicationIndicator = cobAdjudicationIndicator;
	}

	/**
	 * @return Returns the medicareAdjudicationIndicator.
	 */
	public String getMedicareAdjudicationIndicator() {
		return medicareAdjudicationIndicator;
	}

	/**
	 * @param medicareAdjudicationIndicator The medicareAdjudicationIndicator to set.
	 */
	public void setMedicareAdjudicationIndicator(
			String medicareAdjudicationIndicator) {
		this.medicareAdjudicationIndicator = medicareAdjudicationIndicator;
	}

	/**
	 * @return Returns the itsAdjudicationIndicator.
	 */
	public String getItsAdjudicationIndicator() {
		return itsAdjudicationIndicator;
	}

	/**
	 * @param itsAdjudicationIndicator The itsAdjudicationIndicator to set.
	 */
	public void setItsAdjudicationIndicator(String itsAdjudicationIndicator) {
		this.itsAdjudicationIndicator = itsAdjudicationIndicator;
	}

	/**
	 * @return Returns the complianceStatus.
	 */
	public String getComplianceStatus() {
		return complianceStatus;
	}

	/**
	 * @param complianceStatus The complianceStatus to set.
	 */
	public void setComplianceStatus(String complianceStatus) {
		this.complianceStatus = complianceStatus;
	}

	/**
	 * @return Returns the contractTermDate.
	 */
	public Date getContractTermDate() {
		return contractTermDate;
	}

	/**
	 * @param contractTermDate The contractTermDate to set.
	 */
	public void setContractTermDate(Date contractTermDate) {
		this.contractTermDate = contractTermDate;
	}

	/**
	 * @return Returns the multiPlanIndicator.
	 */
	public String getMultiPlanIndicator() {
		return multiPlanIndicator;
	}

	/**
	 * @param multiPlanIndicator The multiPlanIndicator to set.
	 */
	public void setMultiPlanIndicator(String multiPlanIndicator) {
		this.multiPlanIndicator = multiPlanIndicator;
	}

	/**
	 * @return Returns the performanceGuarantee.
	 */
	public String getPerformanceGuarantee() {
		return performanceGuarantee;
	}

	/**
	 * @param performanceGuarantee The performanceGuarantee to set.
	 */
	public void setPerformanceGuarantee(String performanceGuarantee) {
		this.performanceGuarantee = performanceGuarantee;
	}

	/**
	 * @return Returns the projectNameCode.
	 */
	public String getProjectNameCode() {
		return projectNameCode;
	}

	/**
	 * @param projectNameCode The projectNameCode to set.
	 */
	public void setProjectNameCode(String projectNameCode) {
		this.projectNameCode = projectNameCode;
	}

	/**
	 * @return Returns the regulatoryAgency.
	 */
	public String getRegulatoryAgency() {
		return regulatoryAgency;
	}

	/**
	 * @param regulatoryAgency The regulatoryAgency to set.
	 */
	public void setRegulatoryAgency(String regulatoryAgency) {
		this.regulatoryAgency = regulatoryAgency;
	}

	/**
	 * @return Returns the salesMarketDate.
	 */
	public Date getSalesMarketDate() {
		return salesMarketDate;
	}

	/**
	 * @param salesMarketDate The salesMarketDate to set.
	 */
	public void setSalesMarketDate(Date salesMarketDate) {
		this.salesMarketDate = salesMarketDate;
	}

	/**
	 * @return Returns the contractAdjudicationIndicator.
	 */
	public String getContractAdjudicationIndicator() {
		return contractAdjudicationIndicator;
	}

	/**
	 * @param contractAdjudicationIndicator The contractAdjudicationIndicator to set.
	 */
	public void setContractAdjudicationIndicator(
			String contractAdjudicationIndicator) {
		this.contractAdjudicationIndicator = contractAdjudicationIndicator;
	}

	/**
	 * @return Returns the baseContractDate.
	 */
	public Date getBaseContractDate() {
		return baseContractDate;
	}

	/**
	 * @param baseContractDate The baseContractDate to set.
	 */
	public void setBaseContractDate(Date baseContractDate) {
		this.baseContractDate = baseContractDate;
	}

	/**
	 * @return Returns the baseContractId.
	 */
	public String getBaseContractId() {
		return baseContractId;
	}

	/**
	 * @param baseContractId The baseContractId to set.
	 */
	public void setBaseContractId(String baseContractId) {
		this.baseContractId = baseContractId;
	}

	/**
	 * @return Returns the baseContractSysId.
	 */
	public int getBaseContractSysId() {
		return baseContractSysId;
	}

	/**
	 * @param baseContractSysId The baseContractSysId to set.
	 */
	public void setBaseContractSysId(int baseContractSysId) {
		this.baseContractSysId = baseContractSysId;
	}

	/**
	 * @return Returns the baseDateSegmentSysId.
	 */
	public int getBaseDateSegmentSysId() {
		return baseDateSegmentSysId;
	}

	/**
	 * @param baseDateSegmentSysId The baseDateSegmentSysId to set.
	 */
	public void setBaseDateSegmentSysId(int baseDateSegmentSysId) {
		this.baseDateSegmentSysId = baseDateSegmentSysId;
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
	 * @return Returns the isModified.
	 */
	public String getIsModified() {
		return isModified;
	}

	/**
	 * @param isModified The isModified to set.
	 */
	public void setIsModified(String isModified) {
		this.isModified = isModified;
	}

	/**
	 * @return Returns the effectiveDateStr.
	 */
	public String getEffectiveDateStr() {
		if (null == this.effectiveDate && null != this.contractId
				&& null != this.dateSegmentType) { // this is done for shell contracts having effective date as null
			Calendar c = Calendar.getInstance();
			c.set(9999, 11, 31);
			return WPDStringUtil.getStringDate(c.getTime());
		}
		return WPDStringUtil.getStringDate(this.effectiveDate);
	}

	/**
	 * @param effectiveDateStr The effectiveDateStr to set.
	 */
	public void setEffectiveDateStr(String effectiveDateStr) {
		this.effectiveDateStr = effectiveDateStr;
	}

	/**
	 * @return Returns the providerSpecCodeList.
	 */
	public List getProviderSpecCodeList() {
		return providerSpecCodeList;
	}

	/**
	 * @param providerSpecCodeList The providerSpecCodeList to set.
	 */
	public void setProviderSpecCodeList(List providerSpecCodeList) {
		this.providerSpecCodeList = providerSpecCodeList;
	}
	
	/**
	 * @return Returns the blzRuleIndicator.
	 */
	public String getBlzRuleIndicator() {
		return blzRuleIndicator;
	}

	/**
	 * @param blzRuleIndicator The blzRuleIndicator to set.
	 */
	public void setBlzRuleIndicator(String blzRuleIndicator) {
		this.blzRuleIndicator = blzRuleIndicator;
	}

	/**
	 * @return Returns the lastUpdatedTimeStamp.
	 */
	public Date getLastUpdatedTimeStamp() {
		return LastUpdatedTimeStamp;
	}
	/**
	 * @param lastUpdatedTimeStamp The lastUpdatedTimeStamp to set.
	 */
	public void setLastUpdatedTimeStamp(Date lastUpdatedTimeStamp) {
		LastUpdatedTimeStamp = lastUpdatedTimeStamp;
	}
	/**
	 * @return Returns the lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return LastUpdatedUser;
	}
	/**
	 * @param lastUpdatedUser The lastUpdatedUser to set.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		LastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * @return Returns the productStatus.
	 */
	public String getProductStatus() {
		return productStatus;
	}
	/**
	 * @param productStatus The productStatus to set.
	 */
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	/**
	 * @return Returns the isProductLatest.
	 */
	public String getIsProductLatest() {
		return isProductLatest;
	}
	/**
	 * @param isProductLatest The isProductLatest to set.
	 */
	public void setIsProductLatest(String isProductLatest) {
		this.isProductLatest = isProductLatest;
	}

	public String getImageRWDAFlag() {
		return imageRWDAFlag;
	}

	public void setImageRWDAFlag(String imageRWDAFlag) {
		this.imageRWDAFlag = imageRWDAFlag;
	}
	
}
