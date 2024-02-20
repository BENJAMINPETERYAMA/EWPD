/*
 * RuleDisplayBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RuleDisplayBO extends BusinessObject {
	
	private String ruleId;
	private String grpRuleId;
	private String ruleShortDesc;
	private String ruleVersion;
	private String tag;
	private String ruleTypeDesc;
	private String ruleLongDesc;
	private Map<Integer, RuleSequenceBO> ruleSequenceMap;
	
	
	
	public String getGrpRuleId() {
		return grpRuleId;
	}
	public void setGrpRuleId(String grpRuleId) {
		this.grpRuleId = grpRuleId;
	}
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleShortDesc() {
		return ruleShortDesc;
	}
	public void setRuleShortDesc(String ruleShortDesc) {
		this.ruleShortDesc = ruleShortDesc;
	}
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
	
	public String getRuleTypeDesc() {
		return ruleTypeDesc;
	}
	public void setRuleTypeDesc(String ruleTypeDesc) {
		this.ruleTypeDesc = ruleTypeDesc;
	}
	public Map<Integer, RuleSequenceBO> getRuleSequenceMap() {
		return ruleSequenceMap;
	}
	public void setRuleSequenceMap(Map<Integer, RuleSequenceBO> ruleSequenceMap) {
		ruleSequenceMap = ruleSequenceMap;
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
	
	public RuleDisplayBO(){
		this.ruleSequenceMap = new HashMap<Integer, RuleSequenceBO>();
	}
	public String getRuleLongDesc() {
		return ruleLongDesc;
	}
	public void setRuleLongDesc(String ruleLongDesc) {
		this.ruleLongDesc = ruleLongDesc;
	}

}
