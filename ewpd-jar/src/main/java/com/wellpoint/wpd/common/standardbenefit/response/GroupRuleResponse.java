/*
 * Created on Apr 25, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import com.wellpoint.wpd.common.bo.GroupRule;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author aa81327
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GroupRuleResponse extends WPDResponse {
	private GroupRule rule;
	

	/**
	 * @return Returns the rule.
	 */
	public GroupRule getRule() {
		return rule;
	}
	/**
	 * @param rule The rule to set.
	 */
	public void setRule(GroupRule rule) {
		this.rule = rule;
	}
}
