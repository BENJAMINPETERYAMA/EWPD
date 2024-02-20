/*
 * Created on Aug 11, 2009
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
public class TestCaseChangeResponse extends WPDResponse{
	private TestCaseBO testCaseBO;
    private boolean success;
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
