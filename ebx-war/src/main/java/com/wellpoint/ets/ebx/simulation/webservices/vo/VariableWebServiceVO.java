package com.wellpoint.ets.ebx.simulation.webservices.vo;

import java.util.Date;

public class VariableWebServiceVO {
    private String variableId;
    
    private String description;
    
    private String majorHeadings;
    
    private String minorHeadings;
    
    private String pva;
    
    private String dataType;
    
    private boolean isMappingRequired;
    
    private Date createdDate;
    
    private String variableSystem;

	private String variableFormat;
	
	private String variableDescription;
	
	private boolean unmappedVariable = true;
	
	private boolean mappedVariable = true;
	
	private boolean notApplicable = true;
	
	private boolean codedStatus = false;
	
	private String  variableValue;
	
	private String  sensitiveBenefitIndicator;
	
	private String isAdvanceSearch;
	// variable category - section 3 validation
	
	private String isgCatagory;
	
	private String lgCatagory;
	
	private String variableStatus;
	
	private String message;
	
	private boolean auditLock;

	public String getVariableId() {
		return variableId;
	}

	public void setVariableId(String variableId) {
		this.variableId = variableId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMajorHeadings() {
		return majorHeadings;
	}

	public void setMajorHeadings(String majorHeadings) {
		this.majorHeadings = majorHeadings;
	}

	public String getMinorHeadings() {
		return minorHeadings;
	}

	public void setMinorHeadings(String minorHeadings) {
		this.minorHeadings = minorHeadings;
	}

	public String getPva() {
		return pva;
	}

	public void setPva(String pva) {
		this.pva = pva;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public boolean isMappingRequired() {
		return isMappingRequired;
	}

	public void setMappingRequired(boolean isMappingRequired) {
		this.isMappingRequired = isMappingRequired;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getVariableSystem() {
		return variableSystem;
	}

	public void setVariableSystem(String variableSystem) {
		this.variableSystem = variableSystem;
	}

	public String getVariableFormat() {
		return variableFormat;
	}

	public void setVariableFormat(String variableFormat) {
		this.variableFormat = variableFormat;
	}

	public String getVariableDescription() {
		return variableDescription;
	}

	public void setVariableDescription(String variableDescription) {
		this.variableDescription = variableDescription;
	}

	public boolean isUnmappedVariable() {
		return unmappedVariable;
	}

	public void setUnmappedVariable(boolean unmappedVariable) {
		this.unmappedVariable = unmappedVariable;
	}

	public boolean isMappedVariable() {
		return mappedVariable;
	}

	public void setMappedVariable(boolean mappedVariable) {
		this.mappedVariable = mappedVariable;
	}

	public boolean isNotApplicable() {
		return notApplicable;
	}

	public void setNotApplicable(boolean notApplicable) {
		this.notApplicable = notApplicable;
	}

	public boolean isCodedStatus() {
		return codedStatus;
	}

	public void setCodedStatus(boolean codedStatus) {
		this.codedStatus = codedStatus;
	}

	public String getVariableValue() {
		return variableValue;
	}

	public void setVariableValue(String variableValue) {
		this.variableValue = variableValue;
	}

	public String getSensitiveBenefitIndicator() {
		return sensitiveBenefitIndicator;
	}

	public void setSensitiveBenefitIndicator(String sensitiveBenefitIndicator) {
		this.sensitiveBenefitIndicator = sensitiveBenefitIndicator;
	}

	public String getIsAdvanceSearch() {
		return isAdvanceSearch;
	}

	public void setIsAdvanceSearch(String isAdvanceSearch) {
		this.isAdvanceSearch = isAdvanceSearch;
	}

	public String getIsgCatagory() {
		return isgCatagory;
	}

	public void setIsgCatagory(String isgCatagory) {
		this.isgCatagory = isgCatagory;
	}

	public String getLgCatagory() {
		return lgCatagory;
	}

	public void setLgCatagory(String lgCatagory) {
		this.lgCatagory = lgCatagory;
	}

	public String getVariableStatus() {
		return variableStatus;
	}

	public void setVariableStatus(String variableStatus) {
		this.variableStatus = variableStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isAuditLock() {
		return auditLock;
	}

	public void setAuditLock(boolean auditLock) {
		this.auditLock = auditLock;
	}

}
