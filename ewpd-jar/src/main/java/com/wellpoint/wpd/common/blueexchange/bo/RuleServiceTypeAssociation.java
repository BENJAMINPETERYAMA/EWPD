/*
 * RuleServiceTypeAssociation.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.blueexchange.bo;

import java.util.Date;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RuleServiceTypeAssociation {
	private String ruleId;
	private String serviceTypeCode;
	private String serviceTypeSecCode;
	private String serviceTypeDescription;
	private String ruleShortDesc;
	private String applicableToBX;
	private String sendDynamicInfo;
    private String createdUser;
    private Date createdTimestamp;
    private String lastUpdatedUser;
    private Date lastUpdatedTimestamp;	
    private String placeOfService;
    
    /* HIPAA 5010 Changes*/
    private String umRuleId;
    
    // BXNI CR29
    private String preAuthExlInd;
    
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
	 * @return Returns the serviceTypeCode.
	 */
	public String getServiceTypeCode() {
		return serviceTypeCode;
	}
	/**
	 * @param serviceTypeCode The serviceTypeCode to set.
	 */
	public void setServiceTypeCode(String serviceTypeCode) {
		this.serviceTypeCode = serviceTypeCode;
	}
	/**
	 * @return Returns the serviceTypeDescription.
	 */
	public String getServiceTypeDescription() {
		return serviceTypeDescription;
	}
	/**
	 * @param serviceTypeDescription The serviceTypeDescription to set.
	 */
	public void setServiceTypeDescription(String serviceTypeDescription) {
		this.serviceTypeDescription = serviceTypeDescription;
	}
	/**
	 * @return Returns the serviceTypeSecCode.
	 */
	public String getServiceTypeSecCode() {
		return serviceTypeSecCode;
	}
	/**
	 * @param serviceTypeSecCode The serviceTypeSecCode to set.
	 */
	public void setServiceTypeSecCode(String serviceTypeSecCode) {
		this.serviceTypeSecCode = serviceTypeSecCode;
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
	/**
	 * @return Returns the placeOfService.
	 */
	public String getPlaceOfService() {
		return placeOfService;
	}
	/**
	 * @param placeOfService The placeOfService to set.
	 */
	public void setPlaceOfService(String placeOfService) {
		this.placeOfService = placeOfService;
	}
	/**
	 * @return Returns the umRuleId.
	 */
	public String getUmRuleId() {
		return umRuleId;
	}
	/**
	 * @param umRuleId The umRuleId to set.
	 */
	public void setUmRuleId(String umRuleId) {
		this.umRuleId = umRuleId;
	}
	/**
	 * @return the preAuthExlInd
	 */
	public String getPreAuthExlInd() {
		return preAuthExlInd;
	}
	/**
	 * @param preAuthExlInd the preAuthExlInd to set
	 */
	public void setPreAuthExlInd(String preAuthExlInd) {
		this.preAuthExlInd = preAuthExlInd;
	}
}
