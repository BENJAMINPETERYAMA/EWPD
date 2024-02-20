/*
 * BenefitLine.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

/**
 * Business Object for Benefit Line.
 */
public interface BenefitLine{
	/**
	 * 
	 * @return
	 */
	public String getBenefitTerm();
	/**
	 * 
	 * @return
	 */
	public void setBenefitTerm(String benefitTerm);
	/**
	 * 
	 * @return
	 */
	public String getBenefitQualifier();
	/**
	 * 
	 * @return
	 */
	public void setBenefitQualifier(String benefitQualifier);
	/**
	 * 
	 * @return
	 */
	public String getBenefitDesc();
	/**
	 * 
	 * @return
	 */
	public void setBenefitDesc(String benefitDesc);
	/**
	 * 
	 * @return
	 */
	public int getBenefitValue();
	/**
	 * 
	 * @return
	 */
	public void setBenefitValue(int benefitValue);
	/**
	 * 
	 * @return
	 */
	public String getDataType();
	/**
	 * 
	 * @return
	 */
	public void setDataType(String dataType);
	/**
	 * 
	 * @return
	 */
	public String getPVA();
	/**
	 * 
	 * @return
	 */
	public void setPVA(String pva);
	/**
	 * 
	 * @return
	 */
	public String getReference();
	/**
	 * 
	 * @return
	 */
	public void setReference(String reference);
	
}
