/*
 * Created on Jun 26, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Contract implements Serializable{
	//String
	private List lineOfBusiness;
//	String
	private List businessEntity;
//	String
	private List businessGroup;
	
	private String contractId;
	
	private String contractType;
	
	private String baseContractId;
	
	private Date baseContractEffectiveDate;
	
	private String groupSize;
	
	private Date effectiveDate;
	
	private Date expiryDate;
	
	private List pricingInformation;//List of PricingInformation
	
	private Note note;//Note
	
	private List adminOptions;//List of adminOptions
	
	private List rules;//List of rules
	
	private Product product;// 
	
	private String productCode;
	
	private String standardPlanCode;
	
	private String benefitPlan;
	
	private String productFamily;
	
	private String corporatePlanCode;
	
	private String brandName;
	
	private String cobAdjudicationIndcator;
	
	private String medicareAdjudicationIndicator;
	
	private String itsAdjudicationIndicator;
	
	private String regulatoryAgency;
	
	private String complianceStatus;
	
	private String productNameCode;
	
	private Date contractTermDate;

	private String multiPlanIndicator;
	
	private String performanceGuarentee;
	
	private String salesMarketDate;
	
	private List membershipInformations;//List of membership info
	
	
	/**
	 * @return Returns the adminOptions.
	 */
	public List getAdminOptions() {
		return adminOptions;
	}
	/**
	 * @param adminOptions The adminOptions to set.
	 */
	public void setAdminOptions(List adminOptions) {
		this.adminOptions = adminOptions;
	}
	/**
	 * @return Returns the baseContractEffectiveDate.
	 */
	public Date getBaseContractEffectiveDate() {
		return baseContractEffectiveDate;
	}
	/**
	 * @param baseContractEffectiveDate The baseContractEffectiveDate to set.
	 */
	public void setBaseContractEffectiveDate(Date baseContractEffectiveDate) {
		this.baseContractEffectiveDate = baseContractEffectiveDate;
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
	 * @return Returns the benefitPlan.
	 */
	public String getBenefitPlan() {
		return benefitPlan;
	}
	/**
	 * @param benefitPlan The benefitPlan to set.
	 */
	public void setBenefitPlan(String benefitPlan) {
		this.benefitPlan = benefitPlan;
	}
	/**
	 * @return Returns the brandName.
	 */
	public String getBrandName() {
		return brandName;
	}
	/**
	 * @param brandName The brandName to set.
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
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
	 * @return Returns the businessGroup.
	 */
	public List getBusinessGroup() {
		return businessGroup;
	}
	/**
	 * @param businessGroup The businessGroup to set.
	 */
	public void setBusinessGroup(List businessGroup) {
		this.businessGroup = businessGroup;
	}
	/**
	 * @return Returns the cobAdjudicationIndcator.
	 */
	public String getCobAdjudicationIndcator() {
		return cobAdjudicationIndcator;
	}
	/**
	 * @param cobAdjudicationIndcator The cobAdjudicationIndcator to set.
	 */
	public void setCobAdjudicationIndcator(String cobAdjudicationIndcator) {
		this.cobAdjudicationIndcator = cobAdjudicationIndcator;
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
	 * @return Returns the corporatePlanCode.
	 */
	public String getCorporatePlanCode() {
		return corporatePlanCode;
	}
	/**
	 * @param corporatePlanCode The corporatePlanCode to set.
	 */
	public void setCorporatePlanCode(String corporatePlanCode) {
		this.corporatePlanCode = corporatePlanCode;
	}
	/**
	 * @return Returns the effectiveDate.
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * @param effectiveDate The effectiveDate to set.
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	/**
	 * @return Returns the expiryDate.
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate The expiryDate to set.
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * @return Returns the groupSize.
	 */
	public String getGroupSize() {
		return groupSize;
	}
	/**
	 * @param groupSize The groupSize to set.
	 */
	public void setGroupSize(String groupSize) {
		this.groupSize = groupSize;
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
	 * @return Returns the lineOfBusiness.
	 */
	public List getLineOfBusiness() {
		return lineOfBusiness;
	}
	/**
	 * @param lineOfBusiness The lineOfBusiness to set.
	 */
	public void setLineOfBusiness(List lineOfBusiness) {
		this.lineOfBusiness = lineOfBusiness;
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
	 * @return Returns the membershipInformations.
	 */
	public List getMembershipInformations() {
		return membershipInformations;
	}
	/**
	 * @param membershipInformations The membershipInformations to set.
	 */
	public void setMembershipInformations(List membershipInformations) {
		this.membershipInformations = membershipInformations;
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
	 * @return Returns the note.
	 */
	public Note getNote() {
		return note;
	}
	/**
	 * @param note The note to set.
	 */
	public void setNote(Note note) {
		this.note = note;
	}
	/**
	 * @return Returns the performanceGuarentee.
	 */
	public String getPerformanceGuarentee() {
		return performanceGuarentee;
	}
	/**
	 * @param performanceGuarentee The performanceGuarentee to set.
	 */
	public void setPerformanceGuarentee(String performanceGuarentee) {
		this.performanceGuarentee = performanceGuarentee;
	}
	/**
	 * @return Returns the pricingInformation.
	 */
	public List getPricingInformation() {
		return pricingInformation;
	}
	/**
	 * @param pricingInformation The pricingInformation to set.
	 */
	public void setPricingInformation(List pricingInformation) {
		this.pricingInformation = pricingInformation;
	}
	/**
	 * @return Returns the product.
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * @param product The product to set.
	 */
	public void setProduct(Product product) {
		this.product = product;
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
	 * @return Returns the productNameCode.
	 */
	public String getProductNameCode() {
		return productNameCode;
	}
	/**
	 * @param productNameCode The productNameCode to set.
	 */
	public void setProductNameCode(String productNameCode) {
		this.productNameCode = productNameCode;
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
	 * @return Returns the rules.
	 */
	public List getRules() {
		return rules;
	}
	/**
	 * @param rules The rules to set.
	 */
	public void setRules(List rules) {
		this.rules = rules;
	}
	/**
	 * @return Returns the salesMarketDate.
	 */
	public String getSalesMarketDate() {
		return salesMarketDate;
	}
	/**
	 * @param salesMarketDate The salesMarketDate to set.
	 */
	public void setSalesMarketDate(String salesMarketDate) {
		this.salesMarketDate = salesMarketDate;
	}
	/**
	 * @return Returns the standardPlanCode.
	 */
	public String getStandardPlanCode() {
		return standardPlanCode;
	}
	/**
	 * @param standardPlanCode The standardPlanCode to set.
	 */
	public void setStandardPlanCode(String standardPlanCode) {
		this.standardPlanCode = standardPlanCode;
	}
}
