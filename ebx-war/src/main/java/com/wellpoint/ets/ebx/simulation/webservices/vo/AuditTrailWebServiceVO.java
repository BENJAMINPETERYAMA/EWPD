package com.wellpoint.ets.ebx.simulation.webservices.vo;

import java.util.Date;

public class AuditTrailWebServiceVO {
	private String variableId;
	private Date createdTmStamp;
	private String createdUser;
	private String systemComments;
	private String userComments = "";
	private String variableDesc;
	private String mappingStatus;
	private HippaSegmentMappingWebServiceVO hippaSegmentMappingVO;
	private String createdAuditDate;
	private String ruleId;
	private String spsId;
	private Integer variableAuditStatus;
	public String getVariableId() {
		return variableId;
	}
	public void setVariableId(String variableId) {
		this.variableId = variableId;
	}
	public Date getCreatedTmStamp() {
		return createdTmStamp;
	}
	public void setCreatedTmStamp(Date createdTmStamp) {
		this.createdTmStamp = createdTmStamp;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getSystemComments() {
		return systemComments;
	}
	public void setSystemComments(String systemComments) {
		this.systemComments = systemComments;
	}
	public String getUserComments() {
		return userComments;
	}
	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}
	public String getVariableDesc() {
		return variableDesc;
	}
	public void setVariableDesc(String variableDesc) {
		this.variableDesc = variableDesc;
	}
	public String getMappingStatus() {
		return mappingStatus;
	}
	public void setMappingStatus(String mappingStatus) {
		this.mappingStatus = mappingStatus;
	}
	public HippaSegmentMappingWebServiceVO getHippaSegmentMappingVO() {
		return hippaSegmentMappingVO;
	}
	public void setHippaSegmentMappingVO(
			HippaSegmentMappingWebServiceVO hippaSegmentMappingVO) {
		this.hippaSegmentMappingVO = hippaSegmentMappingVO;
	}
	public String getCreatedAuditDate() {
		return createdAuditDate;
	}
	public void setCreatedAuditDate(String createdAuditDate) {
		this.createdAuditDate = createdAuditDate;
	}
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getSpsId() {
		return spsId;
	}
	public void setSpsId(String spsId) {
		this.spsId = spsId;
	}
	public Integer getVariableAuditStatus() {
		return variableAuditStatus;
	}
	public void setVariableAuditStatus(Integer variableAuditStatus) {
		this.variableAuditStatus = variableAuditStatus;
	}
	
	
}
