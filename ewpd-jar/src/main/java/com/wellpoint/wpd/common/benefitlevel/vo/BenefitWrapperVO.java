/*
 * Created on Mar 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.benefitlevel.vo;

import java.util.List;


/**
 * @author u12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitWrapperVO {
	private int benefitDefinitionId;
	
	private int standardBenefitKey;
	
	private List benefitLevelsList;
	
	private int masterVersion;
	
	private String benefitIdentifier;
	
	private String masterStatus;
	
	private List businessDomains;
	
	private String selectedBenefitTerm;
	
	private boolean descriptionFlag = false;
	
	private String descriptionValue;
	
	/**
	 * @return Returns the selectedBenefitTerm.
	 */
	public String getSelectedBenefitTerm() {
		return selectedBenefitTerm;
	}
	/**
	 * @param selectedBenefitTerm The selectedBenefitTerm to set.
	 */
	public void setSelectedBenefitTerm(String selectedBenefitTerm) {
		this.selectedBenefitTerm = selectedBenefitTerm;
	}
	private int standardBenefitParentKey;
//	Changes For Production Fix Start
	private boolean deleteFlag = false;
//	Changes For Production Fix End
	/**
	 * @return Returns the standardBenfefitKey.
	 */
	public int getStandardBenefitKey() {
		return standardBenefitKey;
	}
	/**
	 * @param standardBenfefitKey The standardBenfefitKey to set.
	 */
	public void setStandardBenefitKey(int standardBenefitKey) {
		this.standardBenefitKey = standardBenefitKey;
	}
	/**
	 * @return Returns the benefitIdentifier.
	 */
	public String getBenefitIdentifier() {
		return benefitIdentifier;
	}
	/**
	 * @param benefitIdentifier The benefitIdentifier to set.
	 */
	public void setBenefitIdentifier(String benefitIdentifier) {
		this.benefitIdentifier = benefitIdentifier;
	}
	/**
	 * @return
	 */
	public int getMasterVersion() {
		return masterVersion;
	}
	public void setMasterVersion(int masterVersion) {
		this.masterVersion = masterVersion;
	}
	/**
	 * @return Returns the benefitLevelsList.
	 */
	public List getBenefitLevelsList() {
		return benefitLevelsList;
	}
	/**
	 * @param benefitLevelsList The benefitLevelsList to set.
	 */
	public void setBenefitLevelsList(List benefitLevelsList) {
		this.benefitLevelsList = benefitLevelsList;
	}
	
	/**
	 * @return Returns the benefitDefinitionId.
	 */
	public int getBenefitDefinitionId() {
		return benefitDefinitionId;
	}
	/**
	 * @param benefitDefinitionId The benefitDefinitionId to set.
	 */
	public void setBenefitDefinitionId(int benefitDefinitionId) {
		this.benefitDefinitionId = benefitDefinitionId;
	}
	/**
	 * @return Returns the businessDomains.
	 */
	public List getBusinessDomains() {
		return businessDomains;
	}
	/**
	 * @param businessDomains The businessDomains to set.
	 */
	public void setBusinessDomains(List businessDomains) {
		this.businessDomains = businessDomains;
	}
	/**
	 * @return Returns the masterStatus.
	 */
	public String getMasterStatus() {
		return masterStatus;
	}
	/**
	 * @param masterStatus The masterStatus to set.
	 */
	public void setMasterStatus(String masterStatus) {
		this.masterStatus = masterStatus;
	}
	/**
	 * @return Returns the standardBenefitParentKey.
	 */
	public int getStandardBenefitParentKey() {
		return standardBenefitParentKey;
	}
	/**
	 * @param standardBenefitParentKey The standardBenefitParentKey to set.
	 */
	public void setStandardBenefitParentKey(int standardBenefitParentKey) {
		this.standardBenefitParentKey = standardBenefitParentKey;
	}
//	Changes For Production Fix Start
    /**
     * @return Returns the deleteFlag.
     */
    public boolean isDeleteFlag() {
        return deleteFlag;
    }
    /**
     * @param deleteFlag The deleteFlag to set.
     */
    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
//  Changes For Production Fix End
	/**
	 * @return Returns the descriptionFlag.
	 */
	public boolean isDescriptionFlag() {
		return descriptionFlag;
	}
	/**
	 * @param descriptionFlag The descriptionFlag to set.
	 */
	public void setDescriptionFlag(boolean descriptionFlag) {
		this.descriptionFlag = descriptionFlag;
	}
	/**
	 * @return Returns the descriptionValue.
	 */
	public String getDescriptionValue() {
		return descriptionValue;
	}
	/**
	 * @param descriptionValue The descriptionValue to set.
	 */
	public void setDescriptionValue(String descriptionValue) {
		this.descriptionValue = descriptionValue;
	}
}
