/* 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.webtesttool.vo;

import java.util.List;

public class TestCaseVO {
    
    private String testCaseId;
	private String testCaseName;
	private String testCaseDesc;
	private String benefitComponent;
	private String benefit;
	private List claimLineDetailList;
	
    private ClaimHeaderVO claimHeaderVO;
   
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
	/**
	 * @return Returns the benefit.
	 */
	public String getBenefit() {
		return benefit;
	}
	/**
	 * @param benefit The benefit to set.
	 */
	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}
	/**
	 * @return Returns the benefitComponent.
	 */
	public String getBenefitComponent() {
		return benefitComponent;
	}
	/**
	 * @param benefitComponent The benefitComponent to set.
	 */
	public void setBenefitComponent(String benefitComponent) {
		this.benefitComponent = benefitComponent;
	}
}
