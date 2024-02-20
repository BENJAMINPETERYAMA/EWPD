/*
 * CustomMessageSessionObject.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.blueexchange;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class CustomMessageSessionObject {

	private String headerRuleId;
	
	private String spsParameterId;
	
	
	/**
	 * @return Returns the headerRuleId.
	 */
	public String getHeaderRuleId() {
		return headerRuleId;
	}
	/**
	 * @param headerRuleId The headerRuleId to set.
	 */
	public void setHeaderRuleId(String headerRuleId) {
		this.headerRuleId = headerRuleId;
	}
	/**
	 * @return Returns the spsParameterId.
	 */
	public String getSpsParameterId() {
		return spsParameterId;
	}
	/**
	 * @param spsParameterId The spsParameterId to set.
	 */
	public void setSpsParameterId(String spsParameterId) {
		this.spsParameterId = spsParameterId;
	}
}
