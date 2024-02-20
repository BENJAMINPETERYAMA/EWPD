/*
 * ServiceTypeLocateCriteriaBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.blueexchange.bo;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ServiceTypeLocateCriteriaBO {
	private List eb03Identifier;
	private List headerRule;
	private String applicableToBX;

	
	/**
	 * @return Returns the eb03Identifier.
	 */
	public List getEb03Identifier() {
		return eb03Identifier;
	}
	/**
	 * @param eb03Identifier The eb03Identifier to set.
	 */
	public void setEb03Identifier(List eb03Identifier) {
		this.eb03Identifier = eb03Identifier;
	}
	/**
	 * @return Returns the headerRule.
	 */
	public List getHeaderRule() {
		return headerRule;
	}
	/**
	 * @param headerRule The headerRule to set.
	 */
	public void setHeaderRule(List headerRule) {
		this.headerRule = headerRule;
	}
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
}
