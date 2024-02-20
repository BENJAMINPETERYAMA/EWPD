/*
 * ContractRuleBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;

import java.util.Date;
import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractRuleBO {

	
	private List ruleList;
	private String createdUser;
    private Date createdTimeStamp;
    private String lastUpdatedUser;
    private Date lastUpdatedTimeStamp; 
	private int dateSegmentId;
	private String ruleId;
	
	private String ruleTypeDescn;
	private String generatedId;

	private int productRuleSysId;
	
	private String ruleOverRidValue;
	
	private String flag;
	
	/**
	 * @return Returns the ruleList.
	 */
	public List getRuleList() {
		return ruleList;
	}
	/**
	 * @param ruleList The ruleList to set.
	 */
	public void setRuleList(List ruleList) {
		this.ruleList = ruleList;
	}
	public ContractRuleBO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the createdTimeStamp.
	 */
	public Date getCreatedTimeStamp() {
		return createdTimeStamp;
	}
	/**
	 * @param createdTimeStamp The createdTimeStamp to set.
	 */
	public void setCreatedTimeStamp(Date createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
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
	 * @return Returns the lastUpdatedTimeStamp.
	 */
	public Date getLastUpdatedTimeStamp() {
		return lastUpdatedTimeStamp;
	}
	/**
	 * @param lastUpdatedTimeStamp The lastUpdatedTimeStamp to set.
	 */
	public void setLastUpdatedTimeStamp(Date lastUpdatedTimeStamp) {
		this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
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
	 * @return Returns the dateSegmentId.
	 */
	public int getDateSegmentId() {
		return dateSegmentId;
	}
	/**
	 * @param dateSegmentId The dateSegmentId to set.
	 */
	public void setDateSegmentId(int dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
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
	 * @return Returns the ruleTypeDescn.
	 */
	public String getRuleTypeDescn() {
		return ruleTypeDescn;
	}
	/**
	 * @param ruleTypeDescn The ruleTypeDescn to set.
	 */
	public void setRuleTypeDescn(String ruleTypeDescn) {
		this.ruleTypeDescn = ruleTypeDescn;
	}
	/**
	 * @return Returns the generatedId.
	 */
	public String getGeneratedId() {
		return generatedId;
	}
	/**
	 * @param generatedId The generatedId to set.
	 */
	public void setGeneratedId(String generatedId) {
		this.generatedId = generatedId;
	}
	
	/**
	 * @return Returns the productRuleSysId.
	 */
	public int getProductRuleSysId() {
		return productRuleSysId;
	}
	/**
	 * @param productRuleSysId The productRuleSysId to set.
	 */
	public void setProductRuleSysId(int productRuleSysId) {
		this.productRuleSysId = productRuleSysId;
	}
	/**
	 * @return Returns the ruleOverRidValue.
	 */
	public String getRuleOverRidValue() {
		return ruleOverRidValue;
	}
	/**
	 * @param ruleOverRidValue The ruleOverRidValue to set.
	 */
	public void setRuleOverRidValue(String ruleOverRidValue) {
		this.ruleOverRidValue = ruleOverRidValue;
	}
	/**
	 * @return Returns the flag.
	 */
	public String getFlag() {
		return flag;
	}
	/**
	 * @param flag The flag to set.
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
}
