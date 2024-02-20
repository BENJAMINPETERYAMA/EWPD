/*
 * <CategoryVariableMappingVO.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.referencedata.vo;

/**
 * @author UST Global - www.ust-global.com <br />
 * @version $Id: $
 */
public class CategoryVariableMappingVO {
	/**
	 * Comment for <code>categoryCode</code>
	 */
	private String categoryCode = null;
	/**
	 * Comment for <code>variable</code>
	 */
	private String variable = null;
	/**
	 * Comment for <code>variableDesc</code>
	 */
	private String variableDesc = null;

	/**
	 * @return
	 */
	public String getVariableDesc() {
		return variableDesc;
	}

	/**
	 * @param exclusionValidationEnableFlag
	 */
	public void setVariableDesc(String variableDesc) {
		this.variableDesc = variableDesc;
	}

	/**
	 * @return
	 */
	public String getCategoryCode() {
		return categoryCode;
	}

	/**
	 * @param exclusionValidationEnableFlag
	 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	/**
	 * @return
	 */
	public String getVariable() {
		return variable;
	}

	/**
	 * @param exclusionValidationEnableFlag
	 */
	public void setVariable(String variable) {
		this.variable = variable;
	}
}
