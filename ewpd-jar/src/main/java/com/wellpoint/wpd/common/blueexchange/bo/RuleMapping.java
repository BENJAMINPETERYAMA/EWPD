/*
 * RuleMapping.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.blueexchange.bo;

import java.util.Date;
import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RuleMapping {
	private String ruleId;
	private String ruleShortDesc;
	private String applicableToBX;
    private String createdUser;
    private Date createdTimestamp;
    private String lastUpdatedUser;
    private Date lastUpdatedTimestamp;	
    
    private List serviceTypeCodes;
    private String serviceCodesString;
    
    private String sendDynamicInfo;
    
    
	/**
	 * @return Returns the serviceCodesString.
	 */
	public String getServiceCodesString() {
		return serviceCodesString;
	}
	/**
	 * @param serviceCodesString The serviceCodesString to set.
	 */
	public void setServiceCodesString(String serviceCodesString) {
		this.serviceCodesString = serviceCodesString;
	}
	/**
	 * @return Returns the applicableToBX.
	 */
	public String getApplicableToBX() {
		return applicableToBX;
	}
	/**
	 * @param applicableToBX The applicableToBX to set.
	 */
	public void setApplicableToBX(String applicableToBX) {
		this.applicableToBX = applicableToBX;
	}
	/**
	 * @return Returns the createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * @param createdTimestamp The createdTimestamp to set.
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
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
	 * @return Returns the lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * @param lastUpdatedTimestamp The lastUpdatedTimestamp to set.
	 */
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
	/**
	 * @return Returns the lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * @param lastUpdatedUser The lastUpdatedUser to set.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * @return Returns the ruleId.
	 */
	public String getRuleId() {
		return ruleId;
	}
	/**
	 * @param ruleId The ruleId to set.
	 */
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	/**
	 * @return Returns the ruleShortDesc.
	 */
	public String getRuleShortDesc() {
		return ruleShortDesc;
	}
	/**
	 * @param ruleShortDesc The ruleShortDesc to set.
	 */
	public void setRuleShortDesc(String ruleShortDesc) {
		this.ruleShortDesc = ruleShortDesc;
	}
	/**
	 * @return Returns the serviceTypeCodes.
	 */
	public List getServiceTypeCodes() {
		return serviceTypeCodes;
	}
	/**
	 * @param serviceTypeCodes The serviceTypeCodes to set.
	 */
	public void setServiceTypeCodes(List serviceTypeCodes) {
		this.serviceTypeCodes = serviceTypeCodes;
	}
	/**
	 * @return Returns the sendDynamicInfo.
	 */
	public String getSendDynamicInfo() {
		return sendDynamicInfo;
	}
	/**
	 * @param sendDynamicInfo The sendDynamicInfo to set.
	 */
	public void setSendDynamicInfo(String sendDynamicInfo) {
		this.sendDynamicInfo = sendDynamicInfo;
	}
}
