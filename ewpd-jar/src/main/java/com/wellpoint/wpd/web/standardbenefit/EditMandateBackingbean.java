/*
 * Created on Feb 23, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.standardbenefit;

import com.wellpoint.wpd.web.framework.WPDBackingBean;

import java.util.List;


/**
 * @author U11648
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EditMandateBackingbean extends WPDBackingBean {
	private String selectedLob;
	private String selectedBusinessGroup;
	private String selectedBusinessEntity;
	private String mandateType;
	private String selectedJurisdiction;
	private String groupSize;
	private String fundingArrangement;
	private String description;
	private String selectedCitationNumber;
	private String effectiveDate;
	private String expiryDate;

	private boolean disabledCitationsNums;
	private boolean descriptionDisabled;
	private boolean fundingArrangementDisabled;
	private boolean disabledJurisdiction;
	private boolean disabledBusinessDomains;
	private boolean mandateInfoDisabled;
	private boolean groupSizeDisabled;
	private boolean requiredLob;
	private boolean requiredBusinessEntity;
	private boolean requiredBusinessGroup;
	private boolean requiredEffectiveDate;
	private boolean requiredExpiryDate;
	
	private List mandateTypeList;
	private List fundingList;
	private List groupSizeList;

	private String createdUser;
	private String lastChangedUser;
	private String createdDate;
	private String lastChangedDate;
	
	
	
    /**
     * Returns the createdDate
     * @return String createdDate.
     */
    public String getCreatedDate() {
        return createdDate;
    }
    /**
     * Sets the createdDate
     * @param createdDate.
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    /**
     * Returns the createdUser
     * @return String createdUser.
     */
    public String getCreatedUser() {
        return createdUser;
    }
    /**
     * Sets the createdUser
     * @param createdUser.
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
    /**
     * Returns the description
     * @return String description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description
     * @param description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Returns the descriptionDisabled
     * @return boolean descriptionDisabled.
     */
    public boolean isDescriptionDisabled() {
        return descriptionDisabled;
    }
    /**
     * Sets the descriptionDisabled
     * @param descriptionDisabled.
     */
    public void setDescriptionDisabled(boolean descriptionDisabled) {
        this.descriptionDisabled = descriptionDisabled;
    }
    /**
     * Returns the disabledBusinessDomains
     * @return boolean disabledBusinessDomains.
     */
    public boolean isDisabledBusinessDomains() {
        return disabledBusinessDomains;
    }
    /**
     * Sets the disabledBusinessDomains
     * @param disabledBusinessDomains.
     */
    public void setDisabledBusinessDomains(boolean disabledBusinessDomains) {
        this.disabledBusinessDomains = disabledBusinessDomains;
    }
    /**
     * Returns the disabledCitationsNums
     * @return boolean disabledCitationsNums.
     */
    public boolean isDisabledCitationsNums() {
        return disabledCitationsNums;
    }
    /**
     * Sets the disabledCitationsNums
     * @param disabledCitationsNums.
     */
    public void setDisabledCitationsNums(boolean disabledCitationsNums) {
        this.disabledCitationsNums = disabledCitationsNums;
    }
    /**
     * Returns the disabledJurisdiction
     * @return boolean disabledJurisdiction.
     */
    public boolean isDisabledJurisdiction() {
        return disabledJurisdiction;
    }
    /**
     * Sets the disabledJurisdiction
     * @param disabledJurisdiction.
     */
    public void setDisabledJurisdiction(boolean disabledJurisdiction) {
        this.disabledJurisdiction = disabledJurisdiction;
    }
    /**
     * Returns the effectiveDate
     * @return String effectiveDate.
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }
    /**
     * Sets the effectiveDate
     * @param effectiveDate.
     */
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    /**
     * Returns the expiryDate
     * @return String expiryDate.
     */
    public String getExpiryDate() {
        return expiryDate;
    }
    /**
     * Sets the expiryDate
     * @param expiryDate.
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
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
     * Returns the fundingArrangementDisabled
     * @return boolean fundingArrangementDisabled.
     */
    public boolean isFundingArrangementDisabled() {
        return fundingArrangementDisabled;
    }
    /**
     * Sets the fundingArrangementDisabled
     * @param fundingArrangementDisabled.
     */
    public void setFundingArrangementDisabled(boolean fundingArrangementDisabled) {
        this.fundingArrangementDisabled = fundingArrangementDisabled;
    }
    /**
     * Returns the fundingList
     * @return List fundingList.
     */
    public List getFundingList() {
        return fundingList;
    }
    /**
     * Sets the fundingList
     * @param fundingList.
     */
    public void setFundingList(List fundingList) {
        this.fundingList = fundingList;
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
     * Returns the groupSizeDisabled
     * @return boolean groupSizeDisabled.
     */
    public boolean isGroupSizeDisabled() {
        return groupSizeDisabled;
    }
    /**
     * Sets the groupSizeDisabled
     * @param groupSizeDisabled.
     */
    public void setGroupSizeDisabled(boolean groupSizeDisabled) {
        this.groupSizeDisabled = groupSizeDisabled;
    }
    /**
     * Returns the groupSizeList
     * @return List groupSizeList.
     */
    public List getGroupSizeList() {
        return groupSizeList;
    }
    /**
     * Sets the groupSizeList
     * @param groupSizeList.
     */
    public void setGroupSizeList(List groupSizeList) {
        this.groupSizeList = groupSizeList;
    }
    /**
     * Returns the lastChangedDate
     * @return String lastChangedDate.
     */
    public String getLastChangedDate() {
        return lastChangedDate;
    }
    /**
     * Sets the lastChangedDate
     * @param lastChangedDate.
     */
    public void setLastChangedDate(String lastChangedDate) {
        this.lastChangedDate = lastChangedDate;
    }
    /**
     * Returns the lastChangedUser
     * @return String lastChangedUser.
     */
    public String getLastChangedUser() {
        return lastChangedUser;
    }
    /**
     * Sets the lastChangedUser
     * @param lastChangedUser.
     */
    public void setLastChangedUser(String lastChangedUser) {
        this.lastChangedUser = lastChangedUser;
    }
    /**
     * Returns the mandateInfoDisabled
     * @return boolean mandateInfoDisabled.
     */
    public boolean isMandateInfoDisabled() {
        return mandateInfoDisabled;
    }
    /**
     * Sets the mandateInfoDisabled
     * @param mandateInfoDisabled.
     */
    public void setMandateInfoDisabled(boolean mandateInfoDisabled) {
        this.mandateInfoDisabled = mandateInfoDisabled;
    }
    /**
     * Returns the mandateType
     * @return String mandateType.
     */
    public String getMandateType() {
        return mandateType;
    }
    /**
     * Sets the mandateType
     * @param mandateType.
     */
    public void setMandateType(String mandateType) {
        this.mandateType = mandateType;
    }
    /**
     * Returns the mandateTypeList
     * @return List mandateTypeList.
     */
    public List getMandateTypeList() {
        return mandateTypeList;
    }
    /**
     * Sets the mandateTypeList
     * @param mandateTypeList.
     */
    public void setMandateTypeList(List mandateTypeList) {
        this.mandateTypeList = mandateTypeList;
    }
    /**
     * Returns the requiredBusinessEntity
     * @return boolean requiredBusinessEntity.
     */
    public boolean isRequiredBusinessEntity() {
        return requiredBusinessEntity;
    }
    /**
     * Sets the requiredBusinessEntity
     * @param requiredBusinessEntity.
     */
    public void setRequiredBusinessEntity(boolean requiredBusinessEntity) {
        this.requiredBusinessEntity = requiredBusinessEntity;
    }
    /**
     * Returns the requiredBusinessGroup
     * @return boolean requiredBusinessGroup.
     */
    public boolean isRequiredBusinessGroup() {
        return requiredBusinessGroup;
    }
    /**
     * Sets the requiredBusinessGroup
     * @param requiredBusinessGroup.
     */
    public void setRequiredBusinessGroup(boolean requiredBusinessGroup) {
        this.requiredBusinessGroup = requiredBusinessGroup;
    }
    /**
     * Returns the requiredEffectiveDate
     * @return boolean requiredEffectiveDate.
     */
    public boolean isRequiredEffectiveDate() {
        return requiredEffectiveDate;
    }
    /**
     * Sets the requiredEffectiveDate
     * @param requiredEffectiveDate.
     */
    public void setRequiredEffectiveDate(boolean requiredEffectiveDate) {
        this.requiredEffectiveDate = requiredEffectiveDate;
    }
    /**
     * Returns the requiredExpiryDate
     * @return boolean requiredExpiryDate.
     */
    public boolean isRequiredExpiryDate() {
        return requiredExpiryDate;
    }
    /**
     * Sets the requiredExpiryDate
     * @param requiredExpiryDate.
     */
    public void setRequiredExpiryDate(boolean requiredExpiryDate) {
        this.requiredExpiryDate = requiredExpiryDate;
    }
    /**
     * Returns the requiredLob
     * @return boolean requiredLob.
     */
    public boolean isRequiredLob() {
        return requiredLob;
    }
    /**
     * Sets the requiredLob
     * @param requiredLob.
     */
    public void setRequiredLob(boolean requiredLob) {
        this.requiredLob = requiredLob;
    }
    /**
     * Returns the selectedBusinessEntity
     * @return String selectedBusinessEntity.
     */
    public String getSelectedBusinessEntity() {
        return selectedBusinessEntity;
    }
    /**
     * Sets the selectedBusinessEntity
     * @param selectedBusinessEntity.
     */
    public void setSelectedBusinessEntity(String selectedBusinessEntity) {
        this.selectedBusinessEntity = selectedBusinessEntity;
    }
    /**
     * Returns the selectedBusinessGroup
     * @return String selectedBusinessGroup.
     */
    public String getSelectedBusinessGroup() {
        return selectedBusinessGroup;
    }
    /**
     * Sets the selectedBusinessGroup
     * @param selectedBusinessGroup.
     */
    public void setSelectedBusinessGroup(String selectedBusinessGroup) {
        this.selectedBusinessGroup = selectedBusinessGroup;
    }
    /**
     * Returns the selectedCitationNumber
     * @return String selectedCitationNumber.
     */
    public String getSelectedCitationNumber() {
        return selectedCitationNumber;
    }
    /**
     * Sets the selectedCitationNumber
     * @param selectedCitationNumber.
     */
    public void setSelectedCitationNumber(String selectedCitationNumber) {
        this.selectedCitationNumber = selectedCitationNumber;
    }
    /**
     * Returns the selectedJurisdiction
     * @return String selectedJurisdiction.
     */
    public String getSelectedJurisdiction() {
        return selectedJurisdiction;
    }
    /**
     * Sets the selectedJurisdiction
     * @param selectedJurisdiction.
     */
    public void setSelectedJurisdiction(String selectedJurisdiction) {
        this.selectedJurisdiction = selectedJurisdiction;
    }
    /**
     * Returns the selectedLob
     * @return String selectedLob.
     */
    public String getSelectedLob() {
        return selectedLob;
    }
    /**
     * Sets the selectedLob
     * @param selectedLob.
     */
    public void setSelectedLob(String selectedLob) {
        this.selectedLob = selectedLob;
    }
    
	public String updateMandate() {
		return "success";
	}
}
