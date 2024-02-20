/*
 * ProductRuleAssociationBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductRuleAssociationBO {

	private int productRuleSysID;
	private int productKey;
	private String ruleID;
	private String ruleDescription;
	private String ruleType;
	private String providerArrangement;
	private String genRuleID;
	private String ruleComment;
	private String flag;
	private String lastUpdatedUser;
    private String createdUser;
    private java.util.Date createdTimestamp;
    private java.util.Date lastUpdatedTimestamp;
    private int ruleCount;
    private boolean deleteFlag = false;
	
   
    
//  --------------------------------- getters/setters -----------------------	    
	/**
	 * Returns the createdTimestamp
	 * @return java.util.Date createdTimestamp.
	 */
	public java.util.Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * Sets the createdTimestamp
	 * @param createdTimestamp.
	 */
	public void setCreatedTimestamp(java.util.Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	/**
	 * Returns the createdUser
	 * @return String createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * Sets the createdUser
	 * @param createdUser.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * Returns the flag
	 * @return String flag.
	 */
	public String getFlag() {
		return flag;
	}
	/**
	 * Sets the flag
	 * @param flag.
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	/**
	 * Returns the genRuleID
	 * @return String genRuleID.
	 */
	public String getGenRuleID() {
		return genRuleID;
	}
	/**
	 * Sets the genRuleID
	 * @param genRuleID.
	 */
	public void setGenRuleID(String genRuleID) {
		this.genRuleID = genRuleID;
	}
	/**
	 * Returns the lastUpdatedTimestamp
	 * @return java.util.Date lastUpdatedTimestamp.
	 */
	public java.util.Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * Sets the lastUpdatedTimestamp
	 * @param lastUpdatedTimestamp.
	 */
	public void setLastUpdatedTimestamp(java.util.Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
	/**
	 * Returns the lastUpdatedUser
	 * @return String lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * Sets the lastUpdatedUser
	 * @param lastUpdatedUser.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * Returns the productKey
	 * @return int productKey.
	 */
	public int getProductKey() {
		return productKey;
	}
	/**
	 * Sets the productKey
	 * @param productKey.
	 */
	public void setProductKey(int productKey) {
		this.productKey = productKey;
	}
	/**
	 * Returns the productRuleSysID
	 * @return int productRuleSysID.
	 */
	public int getProductRuleSysID() {
		return productRuleSysID;
	}
	/**
	 * Sets the productRuleSysID
	 * @param productRuleSysID.
	 */
	public void setProductRuleSysID(int productRuleSysID) {
		this.productRuleSysID = productRuleSysID;
	}
	/**
	 * Returns the providerArrangement
	 * @return String providerArrangement.
	 */
	public String getProviderArrangement() {
		return providerArrangement;
	}
	/**
	 * Sets the providerArrangement
	 * @param providerArrangement.
	 */
	public void setProviderArrangement(String providerArrangement) {
		this.providerArrangement = providerArrangement;
	}
	/**
	 * Returns the ruleComment
	 * @return String ruleComment.
	 */
	public String getRuleComment() {
		return ruleComment;
	}
	/**
	 * Sets the ruleComment
	 * @param ruleComment.
	 */
	public void setRuleComment(String ruleComment) {
		this.ruleComment = ruleComment;
	}
	/**
	 * Returns the ruleDescription
	 * @return String ruleDescription.
	 */
	public String getRuleDescription() {
		return ruleDescription;
	}
	/**
	 * Sets the ruleDescription
	 * @param ruleDescription.
	 */
	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}
	/**
	 * Returns the ruleID
	 * @return String ruleID.
	 */
	public String getRuleID() {
		return ruleID;
	}
	/**
	 * Sets the ruleID
	 * @param ruleID.
	 */
	public void setRuleID(String ruleID) {
		this.ruleID = ruleID;
	}
	/**
	 * Returns the ruleType
	 * @return String ruleType.
	 */
	public String getRuleType() {
		return ruleType;
	}
	/**
	 * Sets the ruleType
	 * @param ruleType.
	 */
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	/**
	 * @return Returns the ruleCount.
	 */
	public int getRuleCount() {
		return ruleCount;
	}
	/**
	 * @param ruleCount The ruleCount to set.
	 */
	public void setRuleCount(int ruleCount) {
		this.ruleCount = ruleCount;
	}
	/**
	 * @return Returns the deleteFlag.
	 */
	public boolean isDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * @param deleteFlag The deleteFlag to set.
	 */
	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
