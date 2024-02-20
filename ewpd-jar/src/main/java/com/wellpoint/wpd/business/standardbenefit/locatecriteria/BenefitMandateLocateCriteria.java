/*
 * Created on Mar 11, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.standardbenefit.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

import java.util.Date;


/** 
 * LocateCrieria for BenefitMandate. 
 */
public class BenefitMandateLocateCriteria extends LocateCriteria{
	
	/**
	 * benefitMasterSystemId refers to BNFT_SYS_ID in BMST_BNFT_MSTR
	 * benefitMandateId refers to BNFT_MNDT_SYS_ID in BMNDT_BNFT_MNDT
	 */
	
	private int benefitMasterSystemId;		
	private int benefitMandateId;
	private Date effectiveDate;
	private Date expiryDate;
	/**
	 * @return Returns the benefitMasterSystemId.
	 */
	public int getBenefitMasterSystemId() {
		return benefitMasterSystemId;
	}
	/**
	 * @param benefitMasterSystemId The benefitMasterSystemId to set.
	 */
	public void setBenefitMasterSystemId(int benefitMasterSystemId) {
		this.benefitMasterSystemId = benefitMasterSystemId;
	}
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
	 * Returns the benefitMandateId
	 * @return int benefitMandateId.
	 */
	public int getBenefitMandateId() {
		return benefitMandateId;
	}
	/**
	 * Sets the benefitMandateId
	 * @param benefitMandateId.
	 */
	public void setBenefitMandateId(int benefitMandateId) {
		this.benefitMandateId = benefitMandateId;
	}
}
