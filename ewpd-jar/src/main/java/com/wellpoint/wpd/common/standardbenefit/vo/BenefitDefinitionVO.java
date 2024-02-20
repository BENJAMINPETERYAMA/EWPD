/*
 * Created on Mar 3, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.vo;

import java.util.Date;
import java.util.List;


/**
 * @author u12046
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitDefinitionVO {

	private Date effectiveDate;
	private Date expiryDate;
	private String description = "";
	private int masterKeyUsedForUpdate; 
	private int benefitMasterSystemId;
	private int masterVersion;
	private String benefitIdentifier;
	
	private int standardBenefitParentKey;
	private String standardBenefitState;
	private String standardBenefitStatus;
	private String standardBenefitMode;
	private List businessDomains;
	
	private List tierDefinitions;
	private String tierDefinitionAndId;	
	
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
	 * Returns the masterKeyUsedForUpdate
	 * @return int masterKeyUsedForUpdate.
	 */
	public int getMasterKeyUsedForUpdate() {
		return masterKeyUsedForUpdate;
	}
	/**
	 * Sets the masterKeyUsedForUpdate
	 * @param masterKeyUsedForUpdate.
	 */
	public void setMasterKeyUsedForUpdate(int masterKeyUsedForUpdate) {
		this.masterKeyUsedForUpdate = masterKeyUsedForUpdate;
	}
	/**
	 * Returns the benefitMasterSystemId
	 * @return int benefitMasterSystemId.
	 */
	public int getBenefitMasterSystemId() {
		return benefitMasterSystemId;
	}
	/**
	 * Sets the benefitMasterSystemId
	 * @param benefitMasterSystemId.
	 */
	public void setBenefitMasterSystemId(int benefitMasterSystemId) {
		this.benefitMasterSystemId = benefitMasterSystemId;
	}
	/**
	 * Returns the masterVersion
	 * @return int masterVersion.
	 */
	public int getMasterVersion() {
		return masterVersion;
	}
	/**
	 * Sets the masterVersion
	 * @param masterVersion.
	 */
	public void setMasterVersion(int masterVersion) {
		this.masterVersion = masterVersion;
	}
	/**
	 * Returns the benefitIdentifier
	 * @return String benefitIdentifier.
	 */
	public String getBenefitIdentifier() {
		return benefitIdentifier;
	}
	/**
	 * Sets the benefitIdentifier
	 * @param benefitIdentifier.
	 */
	public void setBenefitIdentifier(String benefitIdentifier) {
		this.benefitIdentifier = benefitIdentifier;
	}
	/**
	 * Returns the businessDomains
	 * @return List businessDomains.
	 */
	public List getBusinessDomains() {
		return businessDomains;
	}
	/**
	 * Sets the businessDomains
	 * @param businessDomains.
	 */
	public void setBusinessDomains(List businessDomains) {
		this.businessDomains = businessDomains;
	}
	/**
	 * Returns the standardBenefitMode
	 * @return String standardBenefitMode.
	 */
	public String getStandardBenefitMode() {
		return standardBenefitMode;
	}
	/**
	 * Sets the standardBenefitMode
	 * @param standardBenefitMode.
	 */
	public void setStandardBenefitMode(String standardBenefitMode) {
		this.standardBenefitMode = standardBenefitMode;
	}
	/**
	 * Returns the standardBenefitParentKey
	 * @return int standardBenefitParentKey.
	 */
	public int getStandardBenefitParentKey() {
		return standardBenefitParentKey;
	}
	/**
	 * Sets the standardBenefitParentKey
	 * @param standardBenefitParentKey.
	 */
	public void setStandardBenefitParentKey(int standardBenefitParentKey) {
		this.standardBenefitParentKey = standardBenefitParentKey;
	}
	/**
	 * Returns the standardBenefitState
	 * @return String standardBenefitState.
	 */
	public String getStandardBenefitState() {
		return standardBenefitState;
	}
	/**
	 * Sets the standardBenefitState
	 * @param standardBenefitState.
	 */
	public void setStandardBenefitState(String standardBenefitState) {
		this.standardBenefitState = standardBenefitState;
	}
	/**
	 * Returns the standardBenefitStatus
	 * @return String standardBenefitStatus.
	 */
	public String getStandardBenefitStatus() {
		return standardBenefitStatus;
	}
	/**
	 * Sets the standardBenefitStatus
	 * @param standardBenefitStatus.
	 */
	public void setStandardBenefitStatus(String standardBenefitStatus) {
		this.standardBenefitStatus = standardBenefitStatus;
	}
    /**
     * @return Returns the tierDefinitions.
     */
    public List getTierDefinitions() {
        return tierDefinitions;
    }
    /**
     * @param tierDefinitions The tierDefinitions to set.
     */
    public void setTierDefinitions(List tierDefinitions) {
        this.tierDefinitions = tierDefinitions;
    }
    /**
     * @return Returns the tierDefinitionAndId.
     */
    public String getTierDefinitionAndId() {
        return tierDefinitionAndId;
    }
    /**
     * @param tierDefinitionAndId The tierDefinitionAndId to set.
     */
    public void setTierDefinitionAndId(String tierDefinitionAndId) {
        this.tierDefinitionAndId = tierDefinitionAndId;
    }
   
}
