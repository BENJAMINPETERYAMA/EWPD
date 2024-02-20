/* 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.webtesttool.vo;

import java.util.Date;
import java.util.List;

public class TestSuiteVO {
    
    /**
     * @param selectedTestCaseList The selectedTestCaseList to set.
     */
    public void setSelectedTestCaseList(List selectedTestCaseList) {
        this.selectedTestCaseList = selectedTestCaseList;
    }
    private String testSuiteId;
	private String testSuiteName;
	private String testSuiteDesc;
	private String contractId;
	private Date startDate;
	private Date endDate;
	private List selectedTestCaseList;
	
    /**
     * @return Returns the contractId.
     */
    public String getContractId() {
        return contractId;
    }
    /**
     * @param contractId The contractId to set.
     */
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    /**
     * @return Returns the endDate.
     */
    public Date getEndDate() {
        return endDate;
    }
    /**
     * @param endDate The endDate to set.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    /**
     * @return Returns the startDate.
     */
    public Date getStartDate() {
        return startDate;
    }
    /**
     * @param startDate The startDate to set.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    /**
     * @return Returns the testSuiteDesc.
     */
    public String getTestSuiteDesc() {
        return testSuiteDesc;
    }
    /**
     * @param testSuiteDesc The testSuiteDesc to set.
     */
    public void setTestSuiteDesc(String testSuiteDesc) {
        this.testSuiteDesc = testSuiteDesc;
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
     * @return Returns the testSuiteName.
     */
    public String getTestSuiteName() {
        return testSuiteName;
    }
    /**
     * @param testSuiteName The testSuiteName to set.
     */
    public void setTestSuiteName(String testSuiteName) {
        this.testSuiteName = testSuiteName;
    }
    /**
     * @return Returns the selectedTestCaseList.
     */
    public List getSelectedTestCaseList() {
        return selectedTestCaseList;
    }
}
