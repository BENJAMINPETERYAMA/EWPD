/*
 * Created on Mar 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.benefitlevel.locateresult;

import com.wellpoint.wpd.common.bo.LocateResult;


/**
 * @author u12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitLineLocateResult extends LocateResult implements Comparable{
	private String PVA;
	
	private int benefitLineId;
	
	private int benefitLevelId;
	
	private int dataTypeId;
	
	private int benefitValue;

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
	/**
	 * @return Returns the benefitLineId.
	 */
	public int getBenefitLineId() {
		return benefitLineId;
	}
	/**
	 * @param benefitLineId The benefitLineId to set.
	 */
	public void setBenefitLineId(int benefitLineId) {
		this.benefitLineId = benefitLineId;
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
	 * @return Returns the dataTypeId.
	 */
	public int getDataTypeId() {
		return dataTypeId;
	}
	/**
	 * @param dataTypeId The dataTypeId to set.
	 */
	public void setDataTypeId(int dataTypeId) {
		this.dataTypeId = dataTypeId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		int result = 0;
		BenefitLineLocateResult benefitLineLocateResult2 = (BenefitLineLocateResult)o;
		if(this.getBenefitLineId() > benefitLineLocateResult2.getBenefitLineId()){
			result = 1;
		}
		if(this.getBenefitLineId() < benefitLineLocateResult2.getBenefitLineId()){
			result = -1;
		}
		if(this.getBenefitLineId() == benefitLineLocateResult2.getBenefitLineId()){
			result = 0;
		}
		return result;
	}
	
}
