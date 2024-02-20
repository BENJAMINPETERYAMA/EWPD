/*
 * Created on Mar 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.standardbenefit.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

import java.util.Date;


/**
 * @author U13274
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitAdministrationLocateCriteria extends LocateCriteria{
    
    private int benefitDefinitionKey;
    private Date effectiveDate;
	private Date expiryDate;
	private int benefitAdministrationKey;
    

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
	 * @return Returns the benefitAdministrationKey.
	 */
	public int getBenefitAdministrationKey() {
		return benefitAdministrationKey;
	}
	/**
	 * @param benefitAdministrationKey The benefitAdministrationKey to set.
	 */
	public void setBenefitAdministrationKey(int benefitAdministrationKey) {
		this.benefitAdministrationKey = benefitAdministrationKey;
	}
}
