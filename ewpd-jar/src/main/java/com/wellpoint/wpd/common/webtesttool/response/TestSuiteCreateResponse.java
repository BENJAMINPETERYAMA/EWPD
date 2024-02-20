/* 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.webtesttool.response;

import com.wellpoint.wpd.common.framework.messages.Message;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.webtesttool.bo.TestSuiteBO;

public class TestSuiteCreateResponse  extends WPDResponse{
    
    private TestSuiteBO testSuiteBO;
    private Message message;
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
}
