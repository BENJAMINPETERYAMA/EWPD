/* 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.webtesttool.bo;

import java.util.List;

import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.webtesttool.vo.ClaimHeaderVO;
import com.wellpoint.wpd.common.webtesttool.vo.TestCaseVO;

public class TestCaseBO extends BusinessObject{

    private String testCaseId;
	private String testCaseName;
	private String testCaseDesc;
	private int testCaseCount;

	private ClaimHeaderBO claimHeaderBO;
	private List claimLineDetailList;
    
	private TestCaseVO testCaseVO;
	private ClaimHeaderVO claimHeaderVO;
	
    /**
     * @return Returns the testCaseDesc.
     */
    public String getTestCaseDesc() {
        return testCaseDesc;
    }
    /**
     * @param testCaseDesc The testCaseDesc to set.
     */
    public void setTestCaseDesc(String testCaseDesc) {
        this.testCaseDesc = testCaseDesc;
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
	 * @return Returns the claimHeaderBO.
	 */
	public ClaimHeaderBO getClaimHeaderBO() {
		return claimHeaderBO;
	}
	/**
	 * @param claimHeaderBO The claimHeaderBO to set.
	 */
	public void setClaimHeaderBO(ClaimHeaderBO claimHeaderBO) {
		this.claimHeaderBO = claimHeaderBO;
	}
	/**
	 * @return Returns the claimLineDetailList.
	 */
	public List getClaimLineDetailList() {
		return claimLineDetailList;
	}
	/**
	 * @param claimLineDetailList The claimLineDetailList to set.
	 */
	public void setClaimLineDetailList(List claimLineDetailList) {
		this.claimLineDetailList = claimLineDetailList;
	}
	/**
	 * @return Returns the claimHeaderVO.
	 */
	public ClaimHeaderVO getClaimHeaderVO() {
		return claimHeaderVO;
	}
	/**
	 * @param claimHeaderVO The claimHeaderVO to set.
	 */
	public void setClaimHeaderVO(ClaimHeaderVO claimHeaderVO) {
		this.claimHeaderVO = claimHeaderVO;
	}
	/**
	 * @return Returns the testCaseVO.
	 */
	public TestCaseVO getTestCaseVO() {
		return testCaseVO;
	}
	/**
	 * @param testCaseVO The testCaseVO to set.
	 */
	public void setTestCaseVO(TestCaseVO testCaseVO) {
		this.testCaseVO = testCaseVO;
	}

	/**
	 * @return Returns the testCaseCount.
	 */
	public int getTestCaseCount() {
		return testCaseCount;
	}
	/**
	 * @param testCaseCount The testCaseCount to set.
	 */
	public void setTestCaseCount(int testCaseCount) {
		this.testCaseCount = testCaseCount;
	}
 }
