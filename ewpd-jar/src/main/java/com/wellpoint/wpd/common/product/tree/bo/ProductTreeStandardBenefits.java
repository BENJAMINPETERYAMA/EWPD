/*
 * ProductTreeStandardBenefits.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.tree.bo;

import java.util.Date;
import java.util.List;
//import java.util.Map;
import java.util.Map;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductTreeStandardBenefits {

	private int sequenceNumber;

	private int count;

	private int migratedContractSysId;

	private int benefitComponentId;

	private int dateSegmentId;

	private int productId;

	private int standardBenefitId;

	private String standardBenefitDesc;

	private List benefitLines;

	private List benefitAdminstrations;

	private String standardBenefitStatus;

	private int standardBenefitVersion;

	private int standardBenefitParentSysId;

	private boolean benefitVisibilityStatus;

	private List benefitDetailsList;

	private String benefitHideFlag;

	private boolean showHiddenStatus;

	private String benefitLineFlag;

	private String benefitLevelFlag;

	private String adminOptionFlag;

	private String questionsFlag;

	private Date lastUpdatedTime;

	private String lastUpdatedUser;

	private String isHidden;

	private String benefitCmpntDesc;

	private String productType;

	private String baseProductFamily;

	private int mgrtdDatesegmentId;
	
	private String headerRuleId;
	
	private String benefitCategoryCode;
	
	// This is created specifically for admin method
	// processing including tiered line PVAs
	private String adminMethodBaseProductFamily;
	
	/** Object for setting the flag for hide unhide benefits :: eWPD Stabilization 2011 **/
	private Map benefitComponentHideMap;

	/**
	 * @return Returns the standardBenefitParentSysId.
	 */
	public int getStandardBenefitParentSysId() {
		return standardBenefitParentSysId;
	}

	/**
	 * @param standardBenefitParentSysId The standardBenefitParentSysId to set.
	 */
	public void setStandardBenefitParentSysId(int standardBenefitParentSysId) {
		this.standardBenefitParentSysId = standardBenefitParentSysId;
	}

	/**
	 * @return Returns the standardBenefitVersion.
	 */
	public int getStandardBenefitVersion() {
		return standardBenefitVersion;
	}

	/**
	 * @param standardBenefitVersion The standardBenefitVersion to set.
	 */
	public void setStandardBenefitVersion(int standardBenefitVersion) {
		this.standardBenefitVersion = standardBenefitVersion;
	}

	/**
	 * @return Returns the standardBenefitStatus.
	 */
	public String getStandardBenefitStatus() {
		return standardBenefitStatus;
	}

	/**
	 * @param standardBenefitStatus The standardBenefitStatus to set.
	 */
	public void setStandardBenefitStatus(String standardBenefitStatus) {
		this.standardBenefitStatus = standardBenefitStatus;
	}

	/**
	 * @return Returns the benefitLines.
	 */
	public List getBenefitLines() {
		return benefitLines;
	}

	/**
	 * @param benefitLines The benefitLines to set.
	 */
	public void setBenefitLines(List benefitLines) {
		this.benefitLines = benefitLines;
	}

	/**
	 * Returns the benefitComponentId
	 * @return int benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}

	/**
	 * Sets the benefitComponentId
	 * @param benefitComponentId.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}

	/**
	 * Returns the standardBenefitDesc
	 * @return String standardBenefitDesc.
	 */
	public String getStandardBenefitDesc() {
		return standardBenefitDesc;
	}

	/**
	 * Sets the standardBenefitDesc
	 * @param standardBenefitDesc.
	 */
	public void setStandardBenefitDesc(String standardBenefitDesc) {
		this.standardBenefitDesc = standardBenefitDesc;
	}

	/**
	 * Returns the standardBenefitId
	 * @return int standardBenefitId.
	 */
	public int getStandardBenefitId() {
		return standardBenefitId;
	}

	/**
	 * Sets the standardBenefitId
	 * @param standardBenefitId.
	 */
	public void setStandardBenefitId(int standardBenefitId) {
		this.standardBenefitId = standardBenefitId;
	}

	public List getBenefitAdminstrations() {
		return benefitAdminstrations;
	}

	public void setBenefitAdminstrations(List benefitAdminstrations) {
		this.benefitAdminstrations = benefitAdminstrations;
	}

	/**
	 * @return Returns the migratedContractSysId.
	 */
	public int getMigratedContractSysId() {
		return migratedContractSysId;
	}

	/**
	 * @param migratedContractSysId The migratedContractSysId to set.
	 */
	public void setMigratedContractSysId(int migratedContractSysId) {
		this.migratedContractSysId = migratedContractSysId;
	}

	/**
	 * @return Returns the count.
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count The count to set.
	 */
	public void setCount(int count) {
		this.count = count;
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
	 * @return Returns the dateSegmentId.
	 */
	public int getDateSegmentId() {
		return dateSegmentId;
	}

	/**
	 * @param dateSegmentId The dateSegmentId to set.
	 */
	public void setDateSegmentId(int dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}

	/**
	 * @return Returns the isHidden.
	 */
	public String getIsHidden() {
		return isHidden;
	}

	/**
	 * @param isHidden The isHidden to set.
	 */
	public void setIsHidden(String isHidden) {
		this.isHidden = isHidden;
	}

	/**
	 * @return Returns the lastUpdatedTime.
	 */
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	/**
	 * @param lastUpdatedTime The lastUpdatedTime to set.
	 */
	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
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
	 * @return Returns the benefitVisibilityStatus.
	 */
	public boolean isBenefitVisibilityStatus() {
		return benefitVisibilityStatus;
	}

	/**
	 * @param benefitVisibilityStatus The benefitVisibilityStatus to set.
	 */
	public void setBenefitVisibilityStatus(boolean benefitVisibilityStatus) {
		this.benefitVisibilityStatus = benefitVisibilityStatus;
	}

	/**
	 * @return Returns the showHiddenStatus.
	 */
	public boolean isShowHiddenStatus() {
		return showHiddenStatus;
	}

	/**
	 * @param showHiddenStatus The showHiddenStatus to set.
	 */
	public void setShowHiddenStatus(boolean showHiddenStatus) {
		this.showHiddenStatus = showHiddenStatus;
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
	 * @return Returns the productType.
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * @param productType The productType to set.
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * @return Returns the sequenceNumber.
	 */
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * @param sequenceNumber The sequenceNumber to set.
	 */
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	/**
	 * @return Returns the baseProductFamily.
	 */
	public String getBaseProductFamily() {
		return baseProductFamily;
	}

	/**
	 * @param baseProductFamily The baseProductFamily to set.
	 */
	public void setBaseProductFamily(String baseProductFamily) {
		this.baseProductFamily = baseProductFamily;
	}

	/**
	 * @return Returns the mgrtdDatesegmentId.
	 */
	public int getMgrtdDatesegmentId() {
		return mgrtdDatesegmentId;
	}

	/**
	 * @param mgrtdDatesegmentId The mgrtdDatesegmentId to set.
	 */
	public void setMgrtdDatesegmentId(int mgrtdDatesegmentId) {
		this.mgrtdDatesegmentId = mgrtdDatesegmentId;
	}

	/** Getter and Setter for benefit hide unhide Map declared :: eWPD Stabilization 2011**/
	public Map getBenefitComponentHideMap() {
		return benefitComponentHideMap;
	}
	public void setBenefitComponentHideMap(Map benefitComponentHideMap) {
		this.benefitComponentHideMap = benefitComponentHideMap;
	}
	/**end :: eWPD Stabilization 2011**/

	public String getHeaderRuleId() {
		return headerRuleId;
	}

	public void setHeaderRuleId(String headerRuleId) {
		this.headerRuleId = headerRuleId;
	}
	
	/**
	 * 
	 * @return benefitCategoryCode
	 */
	public String getBenefitCategoryCode() {
		return benefitCategoryCode;
	}

	/**
	 * 
	 * @param benefitCategoryCode
	 */
	public void setBenefitCategoryCode(String benefitCategoryCode) {
		this.benefitCategoryCode = benefitCategoryCode;
	}

	public String getAdminMethodBaseProductFamily() {
		return adminMethodBaseProductFamily;
	}

	public void setAdminMethodBaseProductFamily(String adminMethodBaseProductFamily) {
		this.adminMethodBaseProductFamily = adminMethodBaseProductFamily;
	}

}