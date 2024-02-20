/*
 * Created on Feb 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;

import java.util.Date;
import java.util.List;


/**
 * @author u13274
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitDefinitionBO{
	private Date effectiveDate = null;
	private Date expiryDate = null;
	private String description = null;
	private int benefitDefinitionMasterKey;
	private int benefitMasterSystemId;
	private String benefitDefinitionMasterType;
	private int mandateMasterId; 
	private String createdUser;
	private String lastUpdatedUser;
	private Date createdTimestamp;
	private Date lastUpdatedTimestamp;
	private AdministrationOptionBO administrationOptionBO;
	private String benefitDefinitionKeys;
	private int benefitAdminId;
	
	private List tierDefinitionIdList;
	private List tierDefinitionsList;
	private String tierDefinitions;
	private String tierDescription;
	private int tierDefinitionId;

	
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
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 * @param object
	 * @return
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
	 * @return
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
	 * @return
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Returns the benefitDefinitionMasterKey
	 * @return int benefitDefinitionMasterKey.
	 */
	public int getBenefitDefinitionMasterKey() {
		return benefitDefinitionMasterKey;
	}
	/**
	 * Sets the benefitDefinitionMasterKey
	 * @param benefitDefinitionMasterKey.
	 */
	public void setBenefitDefinitionMasterKey(int benefitDefinitionMasterKey) {
		this.benefitDefinitionMasterKey = benefitDefinitionMasterKey;
	}
	/**
	 * Returns the benefitDefinitionMasterType
	 * @return String benefitDefinitionMasterType.
	 */
	public String getBenefitDefinitionMasterType() {
		return benefitDefinitionMasterType;
	}
	/**
	 * Sets the benefitDefinitionMasterType
	 * @param benefitDefinitionMasterType.
	 */
	public void setBenefitDefinitionMasterType(
			String benefitDefinitionMasterType) {
		this.benefitDefinitionMasterType = benefitDefinitionMasterType;
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
	 * Returns the mandateMasterId
	 * @return int mandateMasterId.
	 */
	public int getMandateMasterId() {
		return mandateMasterId;
	}
	/**
	 * Sets the mandateMasterId
	 * @param mandateMasterId.
	 */
	public void setMandateMasterId(int mandateMasterId) {
		this.mandateMasterId = mandateMasterId;
	}
	
	/**
	 * Returns the createdTimestamp
	 * @return Date createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * Sets the createdTimestamp
	 * @param createdTimestamp.
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
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
	 * Returns the lastUpdatedTimestamp
	 * @return Date lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * Sets the lastUpdatedTimestamp
	 * @param lastUpdatedTimestamp.
	 */
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
	/**
	 * Returns the lastUpdatedUser
	 * @return String lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * Sets the lastUpdatedUser
	 * @param lastUpdatedUser.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * @return Returns the administrationOptionBO.
	 */
	public AdministrationOptionBO getAdministrationOptionBO() {
		return administrationOptionBO;
	}
	/**
	 * @param administrationOptionBO The administrationOptionBO to set.
	 */
	public void setAdministrationOptionBO(
			AdministrationOptionBO administrationOptionBO) {
		this.administrationOptionBO = administrationOptionBO;
	}
	/**
	 * @return Returns the benefitDefinitionKeys.
	 */
	public String getBenefitDefinitionKeys() {
		return benefitDefinitionKeys;
	}
	/**
	 * @param benefitDefinitionKeys The benefitDefinitionKeys to set.
	 */
	public void setBenefitDefinitionKeys(String benefitDefinitionKeys) {
		this.benefitDefinitionKeys = benefitDefinitionKeys;
	}
	/**
	 * @return Returns the benefitAdminId.
	 */
	public int getBenefitAdminId() {
		return benefitAdminId;
	}
	/**
	 * @param benefitAdminId The benefitAdminId to set.
	 */
	public void setBenefitAdminId(int benefitAdminId) {
		this.benefitAdminId = benefitAdminId;
	}
    /**
     * @return Returns the tierDefinitionIdList.
     */
    public List getTierDefinitionIdList() {
        return tierDefinitionIdList;
    }
    /**
     * @param tierDefinitionIdList The tierDefinitionIdList to set.
     */
    public void setTierDefinitionIdList(List tierDefinitionIdList) {
        this.tierDefinitionIdList = tierDefinitionIdList;
    }
    /**
     * @return Returns the tierDefinitions.
     */
    public String getTierDefinitions() {
        return tierDefinitions;
    }
    /**
     * @param tierDefinitions The tierDefinitions to set.
     */
    public void setTierDefinitions(String tierDefinitions) {
        this.tierDefinitions = tierDefinitions;
    }
    /**
     * @return Returns the tierDescription.
     */
    public String getTierDescription() {
        return tierDescription;
    }
    /**
     * @param tierDescription The tierDescription to set.
     */
    public void setTierDescription(String tierDescription) {
        this.tierDescription = tierDescription;
    }
    /**
     * @return Returns the tierDefinitionsList.
     */
    public List getTierDefinitionsList() {
        return tierDefinitionsList;
    }
    /**
     * @param tierDefinitionsList The tierDefinitionsList to set.
     */
    public void setTierDefinitionsList(List tierDefinitionsList) {
        this.tierDefinitionsList = tierDefinitionsList;
    }
    
    /**
     * @return Returns the tierDefinitionId.
     */
    public int getTierDefinitionId() {
        return tierDefinitionId;
    }
    /**
     * @param tierDefinitionId The tierDefinitionId to set.
     */
    public void setTierDefinitionId(int tierDefinitionId) {
        this.tierDefinitionId = tierDefinitionId;
    }
}
