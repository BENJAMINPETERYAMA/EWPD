/*
 * BenefitLineImpl.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

/**
 * Implementation class for the BenefitLine Interface.
 */
public class BenefitLineImpl implements BenefitLine{
	
	private String benefitTerm;
	
	private String benefitQualifier;
	
	private String PVA;
	
	private String reference;
	
	private String dataType;
	
	private String benefitDesc;
	
	private int benefitValue;

	/**
	 * @return Returns the benefitDesc.
	 */
	public String getBenefitDesc() {
		return benefitDesc;
	}
	/**
	 * @param benefitDesc The benefitDesc to set.
	 */
	public void setBenefitDesc(String benefitDesc) {
		this.benefitDesc = benefitDesc;
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
	 * @return Returns the benefitValue.
	 */
	public int getBenefitValue() {
		return benefitValue;
	}
	/**
	 * @param benefitValue The benefitValue to set.
	 */
	public void setBenefitValue(int benefitValue) {
		this.benefitValue = benefitValue;
	}
	/**
	 * @return Returns the dataType.
	 */
	public String getDataType() {
		return dataType;
	}
	/**
	 * @param dataType The dataType to set.
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
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
	 * @return Returns the reference.
	 */
	public String getReference() {
		return reference;
	}
	/**
	 * @param reference The reference to set.
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}
}
