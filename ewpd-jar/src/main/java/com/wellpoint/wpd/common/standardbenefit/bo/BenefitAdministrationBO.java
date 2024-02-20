/*
 * Created on Mar 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

import java.util.Date;


/**
 * @author U13274
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitAdministrationBO {
    
    private int benefitAdministrationKey;
    
    private int benefitDefinitionKey;
	
	private Date effectiveDate = null;
	
	private Date expiryDate = null;
	
	private String description = null;
	
	private String createdUser;
	private Date createdTimestamp;
	private String lastUpdatedUser;
	private Date lastUpdatedTimestamp;
	
	private String effectiveDateStandDef;
	
	private String expiryDateStandDef;
	
	private String benefitAdministrationKeysForDelete = null;
	
	
    /**
     * @return effectiveDateStandDef
     * 
     * Returns the effectiveDateStandDef.
     */
    public String getEffectiveDateStandDef() {
        return effectiveDateStandDef;
    }
    /**
     * @param effectiveDateStandDef
     * 
     * Sets the effectiveDateStandDef.
     */
    public void setEffectiveDateStandDef(String effectiveDateStandDef) {
        this.effectiveDateStandDef = effectiveDateStandDef;
    }
    /**
     * @return expiryDateStandDef
     * 
     * Returns the expiryDateStandDef.
     */
    public String getExpiryDateStandDef() {
        return expiryDateStandDef;
    }
    /**
     * @param expiryDateStandDef
     * 
     * Sets the expiryDateStandDef.
     */
    public void setExpiryDateStandDef(String expiryDateStandDef) {
        this.expiryDateStandDef = expiryDateStandDef;
    }
    /**
     * Returns the benefitAdministrationKey.
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
     * Returns the createdTimestamp
     * @return String createdTimestamp.
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
     * Returns the lastUpdatedTimestamp
     * @return String lastUpdatedTimestamp.
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
