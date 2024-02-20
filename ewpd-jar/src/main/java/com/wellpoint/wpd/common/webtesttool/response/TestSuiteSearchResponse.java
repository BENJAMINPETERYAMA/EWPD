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
import com.wellpoint.wpd.common.webtesttool.bo.TestSuiteBO;

public class TestSuiteSearchResponse extends WPDResponse{
    
    private List testSuiteResultList;
    private TestSuiteBO testSuiteBO;
    
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
     * @return Returns the testSuiteResultList.
     */
    public List getTestSuiteResultList() {
        return testSuiteResultList;
    }
    /**
     * @param testSuiteResultList The testSuiteResultList to set.
     */
    public void setTestSuiteResultList(List testSuiteResultList) {
        this.testSuiteResultList = testSuiteResultList;
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
}
