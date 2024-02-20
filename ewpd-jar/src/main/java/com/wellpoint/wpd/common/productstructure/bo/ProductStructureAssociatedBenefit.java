/*
 * Created on Nov 26, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.productstructure.bo;

import java.util.List;

/**
 * @author U14631
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProductStructureAssociatedBenefit {
	
	 private int benefitComponentId;

	 private int productStructureId;
	 
	 private int standardBenefitId;

	 private int componentSeqNo;
	 
	 private int benefitSeqNo;
		
	 private String standardBenefitDesc;
	 
	 private boolean showHiddenFlag;
	 
	 private String benefitHideFlag;
	 
	 private boolean BenefitVisibilityStatus;
	 
	 private List benefitDetailsList;
	 
	 private String benefitLineFlag;
	 
	 private String benefitLevelFlag;
	 
	 private String adminOptionFlag;
	 
	 private String questionsFlag;
	 
	 private String benefitCmpntDesc;
	 
	 private String entityType;
	 
	 
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
	 * @return Returns the benefitVisibilityStatus.
	 */
	public boolean isBenefitVisibilityStatus() {
		return BenefitVisibilityStatus;
	}
	/**
	 * @param benefitVisibilityStatus The benefitVisibilityStatus to set.
	 */
	public void setBenefitVisibilityStatus(boolean benefitVisibilityStatus) {
		BenefitVisibilityStatus = benefitVisibilityStatus;
	}
	/**
	 * @return Returns the productStructureId.
	 */
	public int getProductStructureId() {
		return productStructureId;
	}
	/**
	 * @param productStructureId The productStructureId to set.
	 */
	public void setProductStructureId(int productStructureId) {
		this.productStructureId = productStructureId;
	}
	/**
	 * @return Returns the showHiddenFlag.
	 */
	public boolean isShowHiddenFlag() {
		return showHiddenFlag;
	}
	/**
	 * @param showHiddenFlag The showHiddenFlag to set.
	 */
	public void setShowHiddenFlag(boolean showHiddenFlag) {
		this.showHiddenFlag = showHiddenFlag;
	}
	/**
	 * @return Returns the standardBenefitDesc.
	 */
	public String getStandardBenefitDesc() {
		return standardBenefitDesc;
	}
	/**
	 * @param standardBenefitDesc The standardBenefitDesc to set.
	 */
	public void setStandardBenefitDesc(String standardBenefitDesc) {
		this.standardBenefitDesc = standardBenefitDesc;
	}
	/**
	 * @return Returns the standardBenefitId.
	 */
	public int getStandardBenefitId() {
		return standardBenefitId;
	}
	/**
	 * @param standardBenefitId The standardBenefitId to set.
	 */
	public void setStandardBenefitId(int standardBenefitId) {
		this.standardBenefitId = standardBenefitId;
	}
	
	
	/**
	 * @return Returns the benefitDetailsList.
	 */
	public List getBenefitDetailsList() {
		return benefitDetailsList;
	}
	/**
	 * @param benefitDetailsList The benefitDetailsList to set.
	 */
	public void setBenefitDetailsList(List benefitDetailsList) {
		this.benefitDetailsList = benefitDetailsList;
	}
	
	
	/**
	 * @return Returns the adminOptionFlag.
	 */
	public String getAdminOptionFlag() {
		return adminOptionFlag;
	}
	/**
	 * @param adminOptionFlag The adminOptionFlag to set.
	 */
	public void setAdminOptionFlag(String adminOptionFlag) {
		this.adminOptionFlag = adminOptionFlag;
	}
	
	/**
	 * @return Returns the benefitLevelFlag.
	 */
	public String getBenefitLevelFlag() {
		return benefitLevelFlag;
	}
	/**
	 * @param benefitLevelFlag The benefitLevelFlag to set.
	 */
	public void setBenefitLevelFlag(String benefitLevelFlag) {
		this.benefitLevelFlag = benefitLevelFlag;
	}
	/**
	 * @return Returns the benefitLineFlag.
	 */
	public String getBenefitLineFlag() {
		return benefitLineFlag;
	}
	/**
	 * @param benefitLineFlag The benefitLineFlag to set.
	 */
	public void setBenefitLineFlag(String benefitLineFlag) {
		this.benefitLineFlag = benefitLineFlag;
	}
	/**
	 * @return Returns the questionsFlag.
	 */
	public String getQuestionsFlag() {
		return questionsFlag;
	}
	/**
	 * @param questionsFlag The questionsFlag to set.
	 */
	public void setQuestionsFlag(String questionsFlag) {
		this.questionsFlag = questionsFlag;
	}
	
	
	/**
	 * @return Returns the benefitCmpntDesc.
	 */
	public String getBenefitCmpntDesc() {
		return benefitCmpntDesc;
	}
	/**
	 * @param benefitCmpntDesc The benefitCmpntDesc to set.
	 */
	public void setBenefitCmpntDesc(String benefitCmpntDesc) {
		this.benefitCmpntDesc = benefitCmpntDesc;
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
	 * @return Returns the benefitSeqNo.
	 */
	public int getBenefitSeqNo() {
		return benefitSeqNo;
	}
	/**
	 * @param benefitSeqNo The benefitSeqNo to set.
	 */
	public void setBenefitSeqNo(int benefitSeqNo) {
		this.benefitSeqNo = benefitSeqNo;
	}
	/**
	 * @return Returns the componentSeqNo.
	 */
	public int getComponentSeqNo() {
		return componentSeqNo;
	}
	/**
	 * @param componentSeqNo The componentSeqNo to set.
	 */
	public void setComponentSeqNo(int componentSeqNo) {
		this.componentSeqNo = componentSeqNo;
	}
}
