/*
 * Created on May 17, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.domain.entity;

import java.util.Date;

/**
 * @author u22093
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MappingRetrieveResult {

	private String variableId;
    
    private String description;
    
    private String Message;
    
    private String msg_type_required;
    
	private String sensitiveBenefitIndicator;
	
	private String variableMappingStatus;
	
	private Long seq_num;
	
	private String hippaSegment;
	
	private String hippaValues;
	
	private String EB03Association;
	
	private String hippaValuesDesc;
	
	private Long variableMappingSysId;
	
	private String inTempTab;
	
	private Long hippaCodeValueSysid;
	
	private String createdUser;
	
	private String lastChangedUser;
	
	private Date createdTime;
	
	private Date lastUpdatedTime;
	
	private boolean finalized;
	
	private String ruleShortDesc;
	
	private String dataFeedInd = "N";
	
	// CR29
	private String procedureExcludedInd;
	
	// SSCR19537 April04
	/** Attribute to define whether the mapping initiated at Individual level*/
	private String indvdlEb03AssnIndicator;
	
	//Audit Lock Status  -- October Release
	private String auditLock;
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the hippaSegment.
	 */
	public String getHippaSegment() {
		return hippaSegment;
	}
	/**
	 * @param hippaSegment The hippaSegment to set.
	 */
	public void setHippaSegment(String hippaSegment) {
		this.hippaSegment = hippaSegment;
	}
	/**
	 * @return Returns the hippaValues.
	 */
	public String getHippaValues() {
		return hippaValues;
	}
	/**
	 * @param hippaValues The hippaValues to set.
	 */
	public void setHippaValues(String hippaValues) {
		this.hippaValues = hippaValues;
	}
	/**
	 * @return Returns the hippaValuesDesc.
	 */
	public String getHippaValuesDesc() {
		return hippaValuesDesc;
	}
	/**
	 * @param hippaValuesDesc The hippaValuesDesc to set.
	 */
	public void setHippaValuesDesc(String hippaValuesDesc) {
		this.hippaValuesDesc = hippaValuesDesc;
	}
	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return Message;
	}
	/**
	 * @param message The message to set.
	 */
	public void setMessage(String message) {
		Message = message;
	}
	/**
	 * @return Returns the msg_type_required.
	 */
	public String getMsg_type_required() {
		return msg_type_required;
	}
	/**
	 * @param msg_type_required The msg_type_required to set.
	 */
	public void setMsg_type_required(String msg_type_required) {
		this.msg_type_required = msg_type_required;
	}
	/**
	 * @return Returns the sensitiveBenefitIndicator.
	 */
	public String getSensitiveBenefitIndicator() {
		return sensitiveBenefitIndicator;
	}
	/**
	 * @param sensitiveBenefitIndicator The sensitiveBenefitIndicator to set.
	 */
	public void setSensitiveBenefitIndicator(String sensitiveBenefitIndicator) {
		this.sensitiveBenefitIndicator = sensitiveBenefitIndicator;
	}
	/**
	 * @return Returns the seq_num.
	 */
	public Long getSeq_num() {
		return seq_num;
	}
	/**
	 * @param seq_num The seq_num to set.
	 */
	public void setSeq_num(Long seq_num) {
		this.seq_num = seq_num;
	}
	/**
	 * @return Returns the variableId.
	 */
	public String getVariableId() {
		return variableId;
	}
	/**
	 * @param variableId The variableId to set.
	 */
	public void setVariableId(String variableId) {
		this.variableId = variableId;
	}
	/**
	 * @return Returns the variableMappingStatus.
	 */
	public String getVariableMappingStatus() {
		return variableMappingStatus;
	}
	/**
	 * @param variableMappingStatus The variableMappingStatus to set.
	 */
	public void setVariableMappingStatus(String variableMappingStatus) {
		this.variableMappingStatus = variableMappingStatus;
	}
	/**
	 * @return Returns the variableMappingSysId.
	 */
	public Long getVariableMappingSysId() {
		return variableMappingSysId;
	}
	/**
	 * @param variableMappingSysId The variableMappingSysId to set.
	 */
	public void setVariableMappingSysId(Long variableMappingSysId) {
		this.variableMappingSysId = variableMappingSysId;
	}
	/**
	 * @return Returns the inTempTab.
	 */
	public String getInTempTab() {
		return inTempTab;
	}
	/**
	 * @param inTempTab The inTempTab to set.
	 */
	public void setInTempTab(String inTempTab) {
		this.inTempTab = inTempTab;
	}
	/**
	 * @return Returns the hippaCodeValueSysid.
	 */
	public Long getHippaCodeValueSysid() {
		return hippaCodeValueSysid;
	}
	/**
	 * @param hippaCodeValueSysid The hippaCodeValueSysid to set.
	 */
	public void setHippaCodeValueSysid(Long hippaCodeValueSysid) {
		this.hippaCodeValueSysid = hippaCodeValueSysid;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getLastChangedUser() {
		return lastChangedUser;
	}
	public void setLastChangedUser(String lastChangedUser) {
		this.lastChangedUser = lastChangedUser;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	public boolean isFinalized() {
		return finalized;
	}
	public void setFinalized(boolean finalized) {
		this.finalized = finalized;
	}
	public String getRuleShortDesc() {
		return ruleShortDesc;
	}
	public void setRuleShortDesc(String ruleShortDesc) {
		this.ruleShortDesc = ruleShortDesc;
	}
	public String getDataFeedInd() {
		return dataFeedInd;
	}
	public void setDataFeedInd(String dataFeedInd) {
		this.dataFeedInd = dataFeedInd;
	}
	public void setAuditLock(String auditLock) {
		this.auditLock = auditLock;
	}
	public String getAuditLock() {
		return auditLock;
	}
	/**
	 * @return the procedureExcludedInd
	 */
	public String getProcedureExcludedInd() {
		return procedureExcludedInd;
	}
	/**
	 * @param procedureExcludedInd the procedureExcludedInd to set
	 */
	public void setProcedureExcludedInd(String procedureExcludedInd) {
		this.procedureExcludedInd = procedureExcludedInd;
	}
	public String getEB03Association() {
		return EB03Association;
	}
	public void setEB03Association(String association) {
		EB03Association = association;
	}
	public String getIndvdlEb03AssnIndicator() {
		return indvdlEb03AssnIndicator;
	}
	public void setIndvdlEb03AssnIndicator(String indvdlEb03AssnIndicator) {
		this.indvdlEb03AssnIndicator = indvdlEb03AssnIndicator;
	}
	
}
