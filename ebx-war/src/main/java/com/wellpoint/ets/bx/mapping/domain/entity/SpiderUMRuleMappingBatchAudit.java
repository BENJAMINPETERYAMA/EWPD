package com.wellpoint.ets.bx.mapping.domain.entity;

import java.util.Date;
import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.vo.User;

public class SpiderUMRuleMappingBatchAudit {

	
	
	
	private Integer umRuleId;
	
	private String umStatus;

	
	private String region;
	
		
	private String version;
	
	
	private Date createdDate;

	private String createdBy;

	private Date lastModifiedDate;

	private String lastModifiedBy;
	
	
		
	public SpiderUMRuleMappingBatchAudit() {
	
	}



	public Integer getUmRuleId() {
		return umRuleId;
	}



	public void setUmRuleId(Integer umRuleId) {
		this.umRuleId = umRuleId;
	}



	public String getUmStatus() {
		return umStatus;
	}



	public void setUmStatus(String umStatus) {
		this.umStatus = umStatus;
	}



	public String getRegion() {
		return region;
	}



	public void setRegion(String region) {
		this.region = region;
	}



	public String getVersion() {
		return version;
	}



	public void setVersion(String version) {
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
		
	
	
	
}
