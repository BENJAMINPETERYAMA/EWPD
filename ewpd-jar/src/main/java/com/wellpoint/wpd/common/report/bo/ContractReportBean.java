/*
 * ContractReportBean.java
 * 
 * © 2008 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.report.bo;

public class ContractReportBean {
	private int rowId;
	private String contractId;
	private String version;
	private String startDate;
	/*pravinth changed here
	 * added endDate and getter and setter methods.
	 */
	private String endDate;
	private String benefitComponent;
	private String benefit;
	private String type;
	private String tier;
	private String headerRule;
	private String Line;
	private String qualFreeq;
	private String value;
	private String refId;
	private String refDesc;
	private String noteId;
	private String noteText;
	
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getBenefitComponent() {
		return benefitComponent;
	}
	public void setBenefitComponent(String benefitComponent) {
		this.benefitComponent = benefitComponent;
	}
	public String getBenefit() {
		return benefit;
	}
	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTier() {
		return tier;
	}
	public void setTier(String tier) {
		this.tier = tier;
	}
	public String getHeaderRule() {
		return headerRule;
	}
	public void setHeaderRule(String headerRule) {
		this.headerRule = headerRule;
	}
	public String getLine() {
		return Line;
	}
	public void setLine(String line) {
		Line = line;
	}
	public String getQualFreeq() {
		return qualFreeq;
	}
	public void setQualFreeq(String qualFreeq) {
		this.qualFreeq = qualFreeq;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public String getRefDesc() {
		return refDesc;
	}
	public void setRefDesc(String refDesc) {
		this.refDesc = refDesc;
	}
	public String getNoteId() {
		return noteId;
	}
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	public String getNoteText() {
		return noteText;
	}
	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}
	public int getRowId() {
		return rowId;
	}
	public void setRowId(int rowId) {
		this.rowId = rowId;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEndDate() {
		return endDate;
	}
}
