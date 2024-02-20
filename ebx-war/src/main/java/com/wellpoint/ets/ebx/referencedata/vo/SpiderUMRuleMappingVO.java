package com.wellpoint.ets.ebx.referencedata.vo;

import java.util.Date;

/**
* @author UST-GLOBAL ErrorCodeVO
*
*/
public class SpiderUMRuleMappingVO {


/**
* Comment for <code>errorCode</code>
*/

	
	private String umRuleId;
	private String umRuleDescription;
	private String status;
	private String comments;
	
	
	private String eb03Value;
	private String eb03DefaultValue;
	private String msgValue;
	private String eb03Desc;
	
	private Date createdDate;
	private Date lastUpdatedDate;
	private Number version;
	private String createdBy;
	private String lastModifiedBy;
	private String systemStatus;
	
	public String getSystemStatus() {
		return systemStatus;
	}

	public void setSystemStatus(String systemStatus) {
		this.systemStatus = systemStatus;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public Number getVersion() {
		return version;
	}

	public void setVersion(Number version) {
		this.version = version;
	}

	public String getEb03Desc() {
		return eb03Desc;
	}

	public void setEb03Desc(String eb03Desc) {
		this.eb03Desc = eb03Desc;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getEb03Value() {
	return eb03Value;
	}
	
	public void setEb03Value(String eb03Value) {
	this.eb03Value = eb03Value;
	}
	
	public String getEb03DefaultValue() {
	return eb03DefaultValue;
	}
	
	public void setEb03DefaultValue(String eb03DefaultValue) {
	this.eb03DefaultValue = eb03DefaultValue;
	}
	
	public String getMsgValue() {
	return msgValue;
	}
	
	public void setMsgValue(String msgValue) {
	this.msgValue = msgValue;
	}

	public String getUmRuleId() {
		return umRuleId;
	}

	public void setUmRuleId(String umRuleId) {
		this.umRuleId = umRuleId;
	}

	public String getUmRuleDescription() {
		return umRuleDescription;
	}

	public void setUmRuleDescription(String umRuleDescription) {
		this.umRuleDescription = umRuleDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}