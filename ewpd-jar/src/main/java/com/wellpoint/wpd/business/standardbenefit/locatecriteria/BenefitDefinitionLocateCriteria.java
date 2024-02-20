/*
 * BenefitDefinitionLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.standardbenefit.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.framework.exception.ValidationException;

import java.util.Date;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitDefinitionLocateCriteria extends LocateCriteria {
	private int benefitMasterSystemId;
	private Date effectiveDate;
	private Date expiryDate;
	private int benefitDefinitionMasterKey;
	private String benefitDefinitionKeys;
	private boolean adminPresent;
	
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
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

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
	 * @return Returns the adminPresent.
	 */
	public boolean getAdminPresent() {
		return adminPresent;
	}
	/**
	 * @param adminPresent The adminPresent to set.
	 */
	public void setAdminPresent(boolean adminPresent) {
		this.adminPresent = adminPresent;
	}
}
