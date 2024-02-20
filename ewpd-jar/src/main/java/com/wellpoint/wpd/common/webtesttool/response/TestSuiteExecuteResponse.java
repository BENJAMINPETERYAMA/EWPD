/* 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.webtesttool.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.messages.Message;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.webtesttool.bo.TestSuiteBO;


public class TestSuiteExecuteResponse extends WPDResponse{
    
    private TestSuiteBO testSuiteBO;
    private List testResults;
    private Message message;
    private boolean status;
    /**
     * @return Returns the testResults.
     */
    public List getTestResults() {
        return testResults;
    }
    /**
     * @param testResults The testResults to set.
     */
    public void setTestResults(List testResults) {
        this.testResults = testResults;
    }
    /**
     * @return Returns the testSuiteBO.
     */
    public TestSuiteBO getTestSuiteBO() {
        return testSuiteBO;
    }
    /**
     * @param testSuiteBO The testSuiteBO to set.
     */
    public void setTestSuiteBO(TestSuiteBO testSuiteBO) {
        this.testSuiteBO = testSuiteBO;
    }
	/**
	 * @return Returns the message.
	 */
	public Message getMessage() {
		return message;
	}
	/**
	 * @param message The message to set.
	 */
	public void setMessage(Message message) {
		this.message = message;
	}
	/**
	 * @return Returns the status.
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
}
