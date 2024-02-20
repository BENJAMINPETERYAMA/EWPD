package com.wellpoint.ets.bx.mapping.domain.entity;

import java.util.Date;

import com.wellpoint.ets.bx.mapping.domain.vo.HippaSegmentMappingVO;

public class AuditTrail {

	private String variableId;
	private Date createdTmStamp;
	private String createdUser;
	private String systemComments;
	private String userComments = "";
	private String variableDesc;
	private String mappingStatus;
	private HippaSegmentMappingVO hippaSegmentMappingVO;
	private String createdAuditDate;
	private String ruleId;
	private String spsId;
	//private String finalized;
	private Integer variableAuditStatus;
	
	
	/**
	 * @return the variableAuditStatus
	 */
	public Integer getVariableAuditStatus() {
		return variableAuditStatus;
	}
	/**
	 * @param variableAuditStatus the variableAuditStatus to set
	 */
	public void setVariableAuditStatus(Integer variableAuditStatus) {
		this.variableAuditStatus = variableAuditStatus;
	}
	public String getVariableId() {
		return variableId;
	}
	public void setVariableId(String variableId) {
		this.variableId = variableId;
	}
	/**
	 * @return Returns the createdTmStamp.
	 */
	public Date getCreatedTmStamp() {
		return createdTmStamp;
	}
	/**
	 * @param createdTmStamp The createdTmStamp to set.
	 */
	public void setCreatedTmStamp(Date createdTmStamp) {
		this.createdTmStamp = createdTmStamp;
	}
	/**
	 * @return Returns the createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * @param createdUser The createdUser to set.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * @return Returns the systemComments.
	 */
	public String getSystemComments() {
		return systemComments;
	}
	/**
	 * @param systemComments The systemComments to set.
	 */
	public void setSystemComments(String systemComments) {
		this.systemComments = systemComments;
	}
	/**
	 * @return Returns the userComments.
	 */
	public String getUserComments() {
		return userComments;
	}
	/**
	 * @param userComments The userComments to set.
	 */
	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}
	/**
	 * @return Returns the mappingStatus.
	 */
	public String getMappingStatus() {
		return mappingStatus;
	}
	/**
	 * @param mappingStatus The mappingStatus to set.
	 */
	public void setMappingStatus(String mappingStatus) {
		this.mappingStatus = mappingStatus;
	}
	/**
	 * @return Returns the variableDesc.
	 */
	public String getVariableDesc() {
		return variableDesc;
	}
	/**
	 * @param variableDesc The variableDesc to set.
	 */
	public void setVariableDesc(String variableDesc) {
		this.variableDesc = variableDesc;
	}
	public HippaSegmentMappingVO getHippaSegmentMappingVO() {
		return hippaSegmentMappingVO;
	}
	public void setHippaSegmentMappingVO(HippaSegmentMappingVO hippaSegmentMappingVO) {
		this.hippaSegmentMappingVO = hippaSegmentMappingVO;
	}
	
	
	/**
	 * @return Returns the createdAuditDate.
	 */
	public String getCreatedAuditDate() {
		return createdAuditDate;
	}
	/**
	 * @param createdAuditDate The createdAuditDate to set.
	 */
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
}
