/*
 * Created on Mar 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.vo;

import java.util.Date;
import java.util.List;


/**
 * @author U13274
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitAdministrationVO {
	
	private int benefitAdministrationKey;
	private int benefitDefinitionKey;
	private Date effectiveDate = null;
	private Date expiryDate = null;
	private String description = null;

	private int standardBenefitKey;
	private String standardBenefitName;
	private int standardBenefitVersion;
	private String standardBenefitStatus;
	private String standardBenefitCreatedUser;
	private int standardBenefitParentKey;
	private List businessDomains;
	private String benefitAdministrationKeysForDelete = null;
    /**
     * Returns the benefitAdministrationKey
     * @return int benefitAdministrationKey.
     */

    public int getBenefitAdministrationKey() {
        return benefitAdministrationKey;
    }
    /**
     * Sets the benefitAdministrationKey
     * @param benefitAdministrationKey.
     */

    public void setBenefitAdministrationKey(int benefitAdministrationKey) {
        this.benefitAdministrationKey = benefitAdministrationKey;
    }
    
    
    /**
     * Returns the benefitDefinitionKey
     * @return int benefitDefinitionKey.
     */

    public int getBenefitDefinitionKey() {
        return benefitDefinitionKey;
    }
    /**
     * Sets the benefitDefinitionKey
     * @param benefitDefinitionKey.
     */

    public void setBenefitDefinitionKey(int benefitDefinitionKey) {
        this.benefitDefinitionKey = benefitDefinitionKey;
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
     * Returns the standardBenefitKey
     * @return int standardBenefitKey.
     */
    public int getStandardBenefitKey() {
        return standardBenefitKey;
    }
    /**
     * Sets the standardBenefitKey
     * @param standardBenefitKey.
     */
    public void setStandardBenefitKey(int standardBenefitKey) {
        this.standardBenefitKey = standardBenefitKey;
    }
    /**
     * Returns the standardBenefitName
     * @return String standardBenefitName.
     */
    public String getStandardBenefitName() {
        return standardBenefitName;
    }
    /**
     * Sets the standardBenefitName
     * @param standardBenefitName.
     */
    public void setStandardBenefitName(String standardBenefitName) {
        this.standardBenefitName = standardBenefitName;
    }
    /**
     * Returns the standardBenefitVersion
     * @return int standardBenefitVersion.
     */
    public int getStandardBenefitVersion() {
        return standardBenefitVersion;
    }
    /**
     * Sets the standardBenefitVersion
     * @param standardBenefitVersion.
     */
    public void setStandardBenefitVersion(int standardBenefitVersion) {
        this.standardBenefitVersion = standardBenefitVersion;
    }
    
	/**
	 * @return Returns the standardBenefitCreatedUser.
	 */
	public String getStandardBenefitCreatedUser() {
		return standardBenefitCreatedUser;
	}
	/**
	 * @param standardBenefitCreatedUser The standardBenefitCreatedUser to set.
	 */
	public void setStandardBenefitCreatedUser(String standardBenefitCreatedUser) {
		this.standardBenefitCreatedUser = standardBenefitCreatedUser;
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
	/**
	 * @return Returns the benefitAdministrationKeysForDelete.
	 */
	public String getBenefitAdministrationKeysForDelete() {
		return benefitAdministrationKeysForDelete;
	}
	/**
	 * @param benefitAdministrationKeysForDelete The benefitAdministrationKeysForDelete to set.
	 */
	public void setBenefitAdministrationKeysForDelete(
			String benefitAdministrationKeysForDelete) {
		this.benefitAdministrationKeysForDelete = benefitAdministrationKeysForDelete;
	}
}
