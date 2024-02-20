/* 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.webtesttool.vo;


public class TestCaseRefVO {
	private String testSuiteId;
	private String testCaseId;
	private String testCaseName;

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
     * @return Returns the testCaseName.
     */
    public String getTestCaseName() {
        return testCaseName;
    }
    /**
     * @param testCaseName The testCaseName to set.
     */
    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }
}
