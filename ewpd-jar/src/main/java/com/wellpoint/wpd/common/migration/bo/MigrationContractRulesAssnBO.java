/*
 * MigrationContractRulesBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MigrationContractRulesAssnBO {

	private int productRuleSysID;
	private int contractDateSegmentSysId;
	private String ruleComment;
	private String lastUpdatedUser;
    private java.util.Date lastUpdatedTimestamp;
    
	    
	/**
	 * Returns the contractDateSegmentSysId
	 * @return int contractDateSegmentSysId.
	 */
	public int getContractDateSegmentSysId() {
		return contractDateSegmentSysId;
	}
	/**
	 * Sets the contractDateSegmentSysId
	 * @param contractDateSegmentSysId.
	 */
	public void setContractDateSegmentSysId(int contractDateSegmentSysId) {
		this.contractDateSegmentSysId = contractDateSegmentSysId;
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
}
