/*
 * ContractDFBenefit.java
 * 
 * © 2008 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;

public class ContractDFBenefit {
	private int rowNum;
	private int dateSegmentSysId;
	private int componentSysId;
	private String componentName;
	private int benefitSysId;
	private String benefitName;
	private int benefitDefnSysId;
	private int benefitAdmnistrationId;
	
	private String componentRule;
	private String benefitHeaderRule;
	private String componentNote;
	private String benefitNote;
	
	private String benefitCategoryCode;
	
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public int getDateSegmentSysId() {
		return dateSegmentSysId;
	}
	public void setDateSegmentSysId(int dateSegmentSysId) {
		this.dateSegmentSysId = dateSegmentSysId;
	}
	public int getComponentSysId() {
		return componentSysId;
	}
	public void setComponentSysId(int componentSysId) {
		this.componentSysId = componentSysId;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public int getBenefitSysId() {
		return benefitSysId;
	}
	public void setBenefitSysId(int benefitSysId) {
		this.benefitSysId = benefitSysId;
	}
	public String getBenefitName() {
		return benefitName;
	}
	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
	}
	public int getBenefitDefnSysId() {
		return benefitDefnSysId;
	}
	public void setBenefitDefnSysId(int benefitDefnSysId) {
		this.benefitDefnSysId = benefitDefnSysId;
	}
	public int getBenefitAdmnistrationId() {
		return benefitAdmnistrationId;
	}
	public void setBenefitAdmnistrationId(int benefitAdmnistrationId) {
		this.benefitAdmnistrationId = benefitAdmnistrationId;
	}
	public String getComponentRule() {
		return componentRule;
	}
	public void setComponentRule(String componentRule) {
		this.componentRule = componentRule;
	}
	public String getBenefitHeaderRule() {
		return benefitHeaderRule;
	}
	public void setBenefitHeaderRule(String benefitHeaderRule) {
		this.benefitHeaderRule = benefitHeaderRule;
	}
	public String getComponentNote() {
		return componentNote;
	}
	public void setComponentNote(String componentNote) {
		this.componentNote = componentNote;
	}
	public String getBenefitNote() {
		return benefitNote;
	}
	public void setBenefitNote(String benefitNote) {
		this.benefitNote = benefitNote;
	}
	public String getBenefitCategoryCode() {
		return benefitCategoryCode;
	}
	public void setBenefitCategoryCode(String benefitCategoryCode) {
		this.benefitCategoryCode = benefitCategoryCode;
	}
	
}
