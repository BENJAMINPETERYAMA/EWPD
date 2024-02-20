/*
 * Created on Mar 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

/**
 * @author u13664
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LookupAdminOptionBO {
	
	private int adminOptionSystemId;
	private String adminOptDescText;
	private String adminOptRefId;
	private String benefitTerms;
	private String benefitQualifiers;

	/**
	 * @return Returns the adminOptDescText.
	 */
	public String getAdminOptDescText() {
		return adminOptDescText;
	}
	/**
	 * @param adminOptDescText The adminOptDescText to set.
	 */
	public void setAdminOptDescText(String adminOptDescText) {
		this.adminOptDescText = adminOptDescText;
	}
	/**
	 * @return Returns the adminOptionSystemId.
	 */
	public int getAdminOptionSystemId() {
		return adminOptionSystemId;
	}
	/**
	 * @param adminOptionSystemId The adminOptionSystemId to set.
	 */
	public void setAdminOptionSystemId(int adminOptionSystemId) {
		this.adminOptionSystemId = adminOptionSystemId;
	}
	/**
	 * @return Returns the adminOptRefId.
	 */
	public String getAdminOptRefId() {
		return adminOptRefId;
	}
	/**
	 * @param adminOptRefId The adminOptRefId to set.
	 */
	public void setAdminOptRefId(String adminOptRefId) {
		this.adminOptRefId = adminOptRefId;
	}
    /**
     * Returns the benefitTerms
     * @return String benefitTerms.
     */

    public String getBenefitTerms() {
        return benefitTerms;
    }
    /**
     * Sets the benefitTerms
     * @param benefitTerms.
     */

    public void setBenefitTerms(String benefitTerms) {
        this.benefitTerms = benefitTerms;
    }
    
	/**
	 * @return Returns the benefitQualifiers.
	 */
	public String getBenefitQualifiers() {
		return benefitQualifiers;
	}
	/**
	 * @param benefitQualifiers The benefitQualifiers to set.
	 */
	public void setBenefitQualifiers(String benefitQualifiers) {
		this.benefitQualifiers = benefitQualifiers;
	}
}
