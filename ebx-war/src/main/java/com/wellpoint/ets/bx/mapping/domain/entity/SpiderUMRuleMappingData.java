package com.wellpoint.ets.bx.mapping.domain.entity;

import java.util.Date;
import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.vo.User;

public class SpiderUMRuleMappingData {

	private Number umRuleMappingSystemId;

	private Number umRuleSystemId;

	private String umEB03;
	private String umEB03Default;
	private String umMessage;

	private Integer version;

	private Date createdDate;

	private String createdBy;

	private Date lastModifiedDate;

	private String lastModifiedBy;
	
	private User user = new User();

	public SpiderUMRuleMappingData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SpiderUMRuleMappingData(Number umRuleMappingSystemId, Number umRuleSystemId, String umEB03,
			String umEB03Default, String umMapessage, Integer version, Date createdDate, String createdBy,
			Date lastModifiedDate, String lastModifiedBy) {
		super();
		this.umRuleMappingSystemId = umRuleMappingSystemId;
		this.umRuleSystemId = umRuleSystemId;
		this.umEB03 = umEB03;
		this.umEB03Default = umEB03Default;
		this.umMessage = umMessage;
		this.version = version;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.lastModifiedDate = lastModifiedDate;
		this.lastModifiedBy = lastModifiedBy;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Number getUmRuleMappingSystemId() {
		return umRuleMappingSystemId;
	}

	public void setUmRuleMappingSystemId(Number umRuleMappingSystemId) {
		this.umRuleMappingSystemId = umRuleMappingSystemId;
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

	@Override
	public String toString() {
		return "SpiderUMRuleMappingData [umRuleMappingSystemId=" + umRuleMappingSystemId + ", umRuleSystemId="
				+ umRuleSystemId + ", umEB03=" + umEB03 + ", umEB03Default=" + umEB03Default + ", umMessage="
				+ umMessage + ", version=" + version + ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ ", lastModifiedDate=" + lastModifiedDate + ", lastModifiedBy=" + lastModifiedBy + ", user=" + user
				+ "]";
	}

	
	
	
}
