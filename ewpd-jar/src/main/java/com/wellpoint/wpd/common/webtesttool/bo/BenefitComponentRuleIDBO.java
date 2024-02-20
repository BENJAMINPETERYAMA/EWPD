/* 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.webtesttool.bo;

public class BenefitComponentRuleIDBO {

    private String beneftCompName;
    private String beneftCompId;
    private String beneftCompRuleId;
	private String contractId;
	private String startDate;
	private String endDate;
	
    /**
     * @return Returns the beneftCompId.
     */
    public String getBeneftCompId() {
        return beneftCompId;
    }
    /**
     * @param beneftCompId The beneftCompId to set.
     */
    public void setBeneftCompId(String beneftCompId) {
        this.beneftCompId = beneftCompId;
    }
    /**
     * @return Returns the beneftCompName.
     */
    public String getBeneftCompName() {
        return beneftCompName;
    }
    /**
     * @param beneftCompName The beneftCompName to set.
     */
    public void setBeneftCompName(String beneftCompName) {
        this.beneftCompName = beneftCompName;
    }
    /**
     * @return Returns the beneftCompRuleId.
     */
    public String getBeneftCompRuleId() {
        return beneftCompRuleId;
    }
    /**
     * @param beneftCompRuleId The beneftCompRuleId to set.
     */
    public void setBeneftCompRuleId(String beneftCompRuleId) {
        this.beneftCompRuleId = beneftCompRuleId;
    }
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
    public String getEndDate() {
        return endDate;
    }
    /**
     * @param endDate The endDate to set.
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    /**
     * @return Returns the startDate.
     */
    public String getStartDate() {
        return startDate;
    }
    /**
     * @param startDate The startDate to set.
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
