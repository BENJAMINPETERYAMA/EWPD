/*
 * RuleBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RuleBO extends BusinessObject {
	
	private int benefitKey;
	private String ruleId;
	private String ruleDesc;
	
	private String flag;
	
	private String entityType;
	private String groupIndicator;
	private String searchWord1;
	private String searchWord2;
	private String searchWord3;
	
	//adding TAG and RULE VERSION
	private String ruleVersion;
	private String tag;
	
	public String getRuleVersion() {
		return ruleVersion;
	}
	public void setRuleVersion(String ruleVersion) {
		this.ruleVersion = ruleVersion;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	/**
	 * @return Returns the searchWord3.
	 */
	public String getSearchWord3() {
		return searchWord3;
	}
	/**
	 * @param searchWord3 The searchWord3 to set.
	 */
	public void setSearchWord3(String searchWord3) {
		this.searchWord3 = searchWord3;
	}
	/**
	 * @return Returns the groupIndicator.
	 */
	public String getGroupIndicator() {
		return groupIndicator;
	}
	/**
	 * @param groupIndicator The groupIndicator to set.
	 */
	public void setGroupIndicator(String groupIndicator) {
		this.groupIndicator = groupIndicator;
	}
	/**
	 * Returns the benefitKey
	 * @return int benefitKey.
	 */
	public int getBenefitKey() {
		return benefitKey;
	}
	/**
	 * Sets the benefitKey
	 * @param benefitKey.
	 */
	public void setBenefitKey(int benefitKey) {
		this.benefitKey = benefitKey;
	}
	/**
	 * Returns the ruleDesc
	 * @return String ruleDesc.
	 */
	public String getRuleDesc() {
		return ruleDesc;
	}
	/**
	 * Sets the ruleDesc
	 * @param ruleDesc.
	 */
	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}
	/**
	 * Returns the ruleId
	 * @return String ruleId.
	 */
	public String getRuleId() {
		return ruleId;
	}
	/**
	 * Sets the ruleId
	 * @param ruleId.
	 */
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	/**
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 * @param object
	 * @return
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 * @return
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
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
	/**
	 * @return Returns the entityType.
	 */
	public String getEntityType() {
		return entityType;
	}
	/**
	 * @param entityType The entityType to set.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	/**
	 * @return Returns the searchWord2.
	 */
	public String getSearchWord2() {
		return searchWord2;
	}
	/**
	 * @param searchWord2 The searchWord2 to set.
	 */
	public void setSearchWord2(String searchWord2) {
		this.searchWord2 = searchWord2;
	}
	/**
	 * @return Returns the serchWord1.
	 */
	public String getSearchWord1() {
		return searchWord1;
	}
	/**
	 * @param serchWord1 The serchWord1 to set.
	 */
	public void setSearchWord1(String searchWord1) {
		this.searchWord1 = searchWord1;
	}
}
