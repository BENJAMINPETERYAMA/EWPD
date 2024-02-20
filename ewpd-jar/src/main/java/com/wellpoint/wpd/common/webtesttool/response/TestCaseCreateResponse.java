/*
 * Created on Aug 8, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.webtesttool.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.webtesttool.bo.TestCaseBO;

/**
 * @author U20628
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestCaseCreateResponse extends WPDResponse{
 
	private boolean success;	
	private String testCaseId;
	private TestCaseBO testCaseBO;

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
	
	
	/**
	 * @return Returns the testCaseId.
	 */
	public String getTestCaseId() {
		return testCaseId;
	}
	/**
	 * @param testCaseId The testCaseId to set.
	 */
	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}
	/**
	 * @return Returns the testCaseBO.
	 */
	public TestCaseBO getTestCaseBO() {
		return testCaseBO;
	}
	/**
	 * @param testCaseBO The testCaseBO to set.
	 */
	public void setTestCaseBO(TestCaseBO testCaseBO) {
		this.testCaseBO = testCaseBO;
	}
}
