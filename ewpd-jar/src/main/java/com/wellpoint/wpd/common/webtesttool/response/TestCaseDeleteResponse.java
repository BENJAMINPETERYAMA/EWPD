/* 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.webtesttool.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;


public class TestCaseDeleteResponse extends WPDResponse{
	
	private List testCaseResultList;
	private boolean success;

	/**
	 * @return Returns the testCaseResultList.
	 */
	public List getTestCaseResultList() {
		return testCaseResultList;
	}
	/**
	 * @param testCaseResultList The testCaseResultList to set.
	 */
	public void setTestCaseResultList(List testCaseResultList) {
		this.testCaseResultList = testCaseResultList;
	}
	/**
	 * @return Returns the success.
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success The success to set.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
