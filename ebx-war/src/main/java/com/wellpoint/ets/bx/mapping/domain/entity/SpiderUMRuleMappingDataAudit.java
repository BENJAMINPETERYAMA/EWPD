package com.wellpoint.ets.bx.mapping.domain.entity;

import java.util.Date;
import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.vo.User;

public class SpiderUMRuleMappingDataAudit {

	private Number umRuleMappingAuditSystemId;

	private Number umRuleSystemId;

	private String umEB03;
	private String umEB03Default;
	private String umMessage;

	private Integer version;

	private Date createdDate;

	private String createdBy;

	private Date lastModifiedDate;

	private String lastModifiedBy;

	public SpiderUMRuleMappingDataAudit() {

	}

	public Number getUmRuleSystemId() {
		return umRuleSystemId;
	}

	public void setUmRuleSystemId(Number umRuleSystemId) {
		this.umRuleSystemId = umRuleSystemId;
	}

	public String getUmEB03() {
		return umEB03;
	}

	public void setUmEB03(String umEB03) {
		this.umEB03 = umEB03;
	}

	public String getUmEB03Default() {
		return umEB03Default;
	}

	public void setUmEB03Default(String umEB03Default) {
		this.umEB03Default = umEB03Default;
	}

	

	public String getUmMessage() {
		return umMessage;
	}

	public void setUmMessage(String umMessage) {
		this.umMessage = umMessage;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Number getUmRuleMappingAuditSystemId() {
		return umRuleMappingAuditSystemId;
	}

	public void setUmRuleMappingAuditSystemId(Number umRuleMappingAuditSystemId) {
		this.umRuleMappingAuditSystemId = umRuleMappingAuditSystemId;
	}

	@Override
	public String toString() {
		return "SpiderUMRuleMappingDataAudit [umRuleMappingAuditSystemId=" + umRuleMappingAuditSystemId
				+ ", umRuleSystemId=" + umRuleSystemId + ", umEB03=" + umEB03 + ", umEB03Default=" + umEB03Default
				+ ", umMessage=" + umMessage + ", version=" + version + ", createdDate=" + createdDate + ", createdBy="
				+ createdBy + ", lastModifiedDate=" + lastModifiedDate + ", lastModifiedBy=" + lastModifiedBy + "]";
	}

	
	
}
