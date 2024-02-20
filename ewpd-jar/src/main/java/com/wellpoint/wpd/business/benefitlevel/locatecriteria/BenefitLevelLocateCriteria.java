/*
 * Created on Mar 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.benefitlevel.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;


/**
 * @author u12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitLevelLocateCriteria extends LocateCriteria{
	
	private int benefitLevelId;
	
	private String benefitTerm;
	
	private String benefitQualifier;
	
	private String PVA;
	// modified to include benefitDefinitionId on 12thDec 2007
	private int benefitDefinitionId;
    /**
     * @return benefitDefinitionId
     * 
     * Returns the benefitDefinitionId.
     */
    public int getBenefitDefinitionId() {
        return benefitDefinitionId;
    }
    /**
     * @param benefitDefinitionId
     * 
     * Sets the benefitDefinitionId.
     */
    public void setBenefitDefinitionId(int benefitDefinitionId) {
        this.benefitDefinitionId = benefitDefinitionId;
    }
	/**
	 * @return Returns the pVA.
	 */
	public String getPVA() {
		return PVA;
	}
	/**
	 * @param pva The pVA to set.
	 */
	public void setPVA(String pva) {
		PVA = pva;
	}
	/**
	 * @return Returns the benefitQualifier.
	 */
	public String getBenefitQualifier() {
		return benefitQualifier;
	}
	/**
	 * @param benefitQualifier The benefitQualifier to set.
	 */
	public void setBenefitQualifier(String benefitQualifier) {
		this.benefitQualifier = benefitQualifier;
	}
	/**
	 * @return Returns the benefitTerm.
	 */
	public String getBenefitTerm() {
		return benefitTerm;
	}
	/**
	 * @param benefitTerm The benefitTerm to set.
	 */
	public void setBenefitTerm(String benefitTerm) {
		this.benefitTerm = benefitTerm;
	}
	/**
	 * @return Returns the benefitLevelId.
	 */
	public int getBenefitLevelId() {
		return benefitLevelId;
	}
	/**
	 * @param benefitLevelId The benefitLevelId to set.
	 */
	public void setBenefitLevelId(int benefitLevelId) {
		this.benefitLevelId = benefitLevelId;
	}
}
