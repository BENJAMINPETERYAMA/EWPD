/*
 * <Item.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/
package com.wellpoint.ets.bx.mapping.domain.entity;

/**
 * @author UST-GLOBAL
 * This is an entity class to represent a item i.e a hippacode value and description
 */

public class Item {

	private String primaryCode;
	
	private String secondaryCode;
	
	private String codeDesc;

	/**
	 * @return
	 */
	public String getCodeDesc() {
		return codeDesc;
	}

	/**
	 * @param codeDesc
	 */
	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	/**
	 * @return
	 */
	public String getPrimaryCode() {
		return primaryCode;
	}

	/**
	 * @param primaryCode
	 */
	public void setPrimaryCode(String primaryCode) {
		this.primaryCode = primaryCode;
	}

	/**
	 * @return
	 */
	public String getSecondaryCode() {
		return secondaryCode;
	}

	/**
	 * @param secondaryCode
	 */
	public void setSecondaryCode(String secondaryCode) {
		this.secondaryCode = secondaryCode;
	}
	
	
}
