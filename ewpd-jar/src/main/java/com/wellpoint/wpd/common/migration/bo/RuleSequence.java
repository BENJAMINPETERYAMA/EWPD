/*
 * RuleDetail.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public class RuleSequence {
	private String ruleId;
	private int ruleSeqNumber;
	private String specialityCode;
	private String serviceCode;
	private String serviceClassLow;
	private String serviceClassHigh;
	private String limitClassLow;
	private String limitClassHigh;
	private String deleteFlag;
	/**
	 * Returns the ruleId.
	 * @return String ruleId.
	 */
	public String getRuleId() {
		return ruleId;
	}
	/**
	 * Sets the ruleId.
	 * @param ruleId.
	 */
	
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	/**
	 * Returns the specialityCode.
	 * @return String specialityCode.
	 */
	public String getSpecialityCode() {
		return specialityCode;
	}
	/**
	 * Sets the specialityCode.
	 * @param specialityCode.
	 */
	
	public void setSpecialityCode(String specialityCode) {
		this.specialityCode = specialityCode;
	}
	/**
	 * Returns the serviceCode.
	 * @return String serviceCode.
	 */
	public String getServiceCode() {
		return serviceCode;
	}
	/**
	 * Sets the serviceCode.
	 * @param serviceCode.
	 */
	
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	/**
	 * Returns the serviceClassLow.
	 * @return String serviceClassLow.
	 */
	public String getServiceClassLow() {
		return serviceClassLow;
	}
	/**
	 * Sets the serviceClassLow.
	 * @param serviceClassLow.
	 */
	
	public void setServiceClassLow(String serviceClassLow) {
		this.serviceClassLow = serviceClassLow;
	}
	/**
	 * Returns the serviceClassHigh.
	 * @return String serviceClassHigh.
	 */
	public String getServiceClassHigh() {
		return serviceClassHigh;
	}
	/**
	 * Sets the serviceClassHigh.
	 * @param serviceClassHigh.
	 */
	
	public void setServiceClassHigh(String serviceClassHigh) {
		this.serviceClassHigh = serviceClassHigh;
	}
	/**
	 * Returns the limitClassLow.
	 * @return String limitClassLow.
	 */
	public String getLimitClassLow() {
		return limitClassLow;
	}
	/**
	 * Sets the limitClassLow.
	 * @param limitClassLow.
	 */
	
	public void setLimitClassLow(String limitClassLow) {
		this.limitClassLow = limitClassLow;
	}
	/**
	 * Returns the limitClassHigh.
	 * @return String limitClassHigh.
	 */
	public String getLimitClassHigh() {
		return limitClassHigh;
	}
	/**
	 * Sets the limitClassHigh.
	 * @param limitClassHigh.
	 */
	
	public void setLimitClassHigh(String limitClassHigh) {
		this.limitClassHigh = limitClassHigh;
	}
	/**
	 * Returns the deleteFlag.
	 * @return String deleteFlag.
	 */
	public String getDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * Sets the deleteFlag.
	 * @param deleteFlag.
	 */
	
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	/**
	 * Returns the ruleSeqNumber.
	 * @return int ruleSeqNumber.
	 */
	public int getRuleSeqNumber() {
		return ruleSeqNumber;
	}
	/**
	 * Sets the ruleSeqNumber.
	 * @param ruleSeqNumber.
	 */
	
	public void setRuleSeqNumber(int ruleSeqNumber) {
		this.ruleSeqNumber = ruleSeqNumber;
	}
}
