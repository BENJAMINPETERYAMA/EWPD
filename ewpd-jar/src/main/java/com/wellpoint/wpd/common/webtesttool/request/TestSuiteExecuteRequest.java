/* 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.webtesttool.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


public class TestSuiteExecuteRequest extends WPDRequest{

    private String testSuiteId;
    private List testCaseIdList;
    /**
     * @return Returns the testSuiteId.
     */
    public String getTestSuiteId() {
        return testSuiteId;
    }
    /**
     * @param testSuiteId The testSuiteId to set.
     */
    public void setTestSuiteId(String testSuiteId) {
        this.testSuiteId = testSuiteId;
    }
    /**
     * @return Returns the testCaseIdList.
     */
    public List getTestCaseIdList() {
        return testCaseIdList;
    }
    /**
     * @param testCaseIdList The testCaseIdList to set.
     */
    public void setTestCaseIdList(List testCaseIdList) {
        this.testCaseIdList = testCaseIdList;
    }
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }

}
