/*
 * Created on Apr 25, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.bo;

import java.util.List;

import com.wellpoint.wpd.common.standardbenefit.bo.RuleBO;

/**
 * @author aa81327
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GroupRule extends RuleBO {
	private List rules;
	
	/**
	 * @return Returns the rules.
	 */
	public List getRules() {
		return rules;
	}
	/**
	 * @param rules The rules to set.
	 */
	public void setRules(List rules) {
		this.rules = rules;
	}
}
