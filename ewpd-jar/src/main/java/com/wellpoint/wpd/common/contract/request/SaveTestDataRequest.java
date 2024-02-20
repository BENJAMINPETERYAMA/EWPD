/*
 * SaveTestDataRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import java.util.Date;

import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveTestDataRequest extends ContractRequest{
	
	private Date testDate;
	
	private String action;
	
	private boolean insertFlag = false;
	
	public static final String RETRIEVE_TEST_DATA = "retrieve";
	
	public static final String PERSIST_TEST_DATA ="persist"; 
	
	

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
	
    /**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	/**
	 * Returns the insertFlag
	 * @return boolean insertFlag.
	 */
	public boolean isInsertFlag() {
		return insertFlag;
	}
	/**
	 * Sets the insertFlag
	 * @param insertFlag.
	 */
	public void setInsertFlag(boolean insertFlag) {
		this.insertFlag = insertFlag;
	}

	
	/**
	 * Returns the action
	 * @return String action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * Sets the action
	 * @param action.
	 */
	public void setAction(String action) {
		this.action = action;
	}
}
