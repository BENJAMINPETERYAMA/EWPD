/*
 * SaveTestDataResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.Date;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveTestDataResponse extends ContractResponse {
	
	private Date testDate; 
	
	
	/**
	 * Returns the testDate
	 * @return Date testDate.
	 */
	public Date getTestDate() {
		return testDate;
	}
	/**
	 * Sets the testDate
	 * @param testDate.
	 */
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
}
