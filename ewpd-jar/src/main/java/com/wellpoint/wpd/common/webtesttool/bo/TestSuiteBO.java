/* 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.webtesttool.bo;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.bo.BusinessObject;

public class TestSuiteBO{

    private String testSuiteId;
	private String testSuiteName;
	private String testSuiteDesc;
	private String contractId;
	private Date startDate;
	private Date endDate;
    private String createdUser;
    private Date createdTimestamp;
    private String lastUpdatedUser;
    private Date lastUpdatedTimestamp;
    private List testCaseList;
    private String testCaseRefStr;
    private int testSuiteCount;
    
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
    /* (non-Javadoc)
     * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
     */
    public boolean equals(BusinessObject object) {
        // TODO Auto-generated method stub
        return false;
    }
    /* (non-Javadoc)
     * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
     */
    public int hashCode() {
        // TODO Auto-generated method stub
        return 0;
    }
    /* (non-Javadoc)
     * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
     */
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }
    /**
     * @return Returns the createdTimestamp.
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }
    /**
     * @param createdTimestamp The createdTimestamp to set.
     */
    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
    /**
     * @return Returns the createdUser.
     */
    public String getCreatedUser() {
        return createdUser;
    }
    /**
     * @param createdUser The createdUser to set.
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
    /**
     * @return Returns the lastUpdatedTimestamp.
     */
    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }
    /**
     * @param lastUpdatedTimestamp The lastUpdatedTimestamp to set.
     */
    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }
    /**
     * @return Returns the lastUpdatedUser.
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }
    /**
     * @param lastUpdatedUser The lastUpdatedUser to set.
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }
    /**
     * @return Returns the testCaseList.
     */
    public List getTestCaseList() {
        return testCaseList;
    }
    /**
     * @param testCaseList The testCaseList to set.
     */
    public void setTestCaseList(List testCaseList) {
        this.testCaseList = testCaseList;
    }
	/**
	 * @return Returns the testCaseRefStr.
	 */
	public String getTestCaseRefStr() {
		return testCaseRefStr;
	}
	/**
	 * @param testCaseRefStr The testCaseRefStr to set.
	 */
	public void setTestCaseRefStr(String testCaseRefStr) {
		this.testCaseRefStr = testCaseRefStr;
	}
	/**
	 * @return Returns the testSuiteCount.
	 */
	public int getTestSuiteCount() {
		return testSuiteCount;
	}
	/**
	 * @param testSuiteCount The testSuiteCount to set.
	 */
	public void setTestSuiteCount(int testSuiteCount) {
		this.testSuiteCount = testSuiteCount;
	}
 }
