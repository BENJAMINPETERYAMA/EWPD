package com.wellpoint.ets.bx.mapping.domain.entity;

import java.util.Date;
import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.vo.User;

public class SpiderUMRuleMapping {

	
	
	private Number umRuleIdSystemId;
	private String umRuleId;
	private String umStatus;
	private String umRuleDesc;
	private String umRuleType;
	private Number defaultVersion;
	private String comment;
	private Date createdDate;
	private String createdBy;
	private Date lastModifiedDate;
	private String lastModifiedBy;
	private String systemStatus;
		
	private User user = new User();
	private SpiderUMRuleMappingData spiderUMRuleMappingData = new SpiderUMRuleMappingData();
	
		
	public SpiderUMRuleMapping() {
	
	}
		
	public String getSystemStatus() {
		return systemStatus;
	}

	public void setSystemStatus(String systemStatus) {
		this.systemStatus = systemStatus;
	}

	public SpiderUMRuleMapping(String umRuleId2, String eb03, Object object, String string, String string2, Date date) {
		// TODO Auto-generated constructor stub
	}

	public SpiderUMRuleMappingData getSpiderUMRuleMappingData() {
		return spiderUMRuleMappingData;
	}

	public void setSpiderUMRuleMappingData(SpiderUMRuleMappingData spiderUMRuleMappingData) {
		this.spiderUMRuleMappingData = spiderUMRuleMappingData;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Number getUmRuleIdSystemId() {
		return umRuleIdSystemId;
	}

	public void setUmRuleIdSystemId(Number umRuleIdSystemId) {
		this.umRuleIdSystemId = umRuleIdSystemId;
	}

	public String getUmRuleId() {
		return umRuleId;
	}

	public void setUmRuleId(String umRuleId) {
		this.umRuleId = umRuleId;
	}

	public String getUmStatus() {
		return umStatus;
	}

	public void setUmStatus(String umStatus) {
		this.umStatus = umStatus;
	}

	public String getUmRuleDesc() {
		return umRuleDesc;
	}

	public void setUmRuleDesc(String umRuleDesc) {
		this.umRuleDesc = umRuleDesc;
	}

	public String getUmRuleType() {
		return umRuleType;
	}

	public void setUmRuleType(String umRuleType) {
		this.umRuleType = umRuleType;
	}

	public Number getDefaultVersion() {
		return defaultVersion;
	}

	public void setDefaultVersion(Number defaultVersion) {
		this.defaultVersion = defaultVersion;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
	
}
