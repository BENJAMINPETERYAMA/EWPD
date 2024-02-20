/* 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.webtesttool.bo;


public class BenefitRuleIDBO {

    private String benefitName;
    private String benefitCategory;
    private String benefitRuleId;
	private String contractId;
	private String startDate;
	private String endDate;
	
   
    /**
     * @return Returns the benefitCategory.
     */
    public String getBenefitCategory() {
        return benefitCategory;
    }
    /**
     * @param benefitCategory The benefitCategory to set.
     */
    public void setBenefitCategory(String benefitCategory) {
        this.benefitCategory = benefitCategory;
    }
    /**
     * @return Returns the benefitName.
     */
    public String getBenefitName() {
        return benefitName;
    }
    /**
     * @param benefitName The benefitName to set.
     */
    public void setBenefitName(String benefitName) {
        this.benefitName = benefitName;
    }
    /**
     * @return Returns the benefitRuleId.
     */
    public String getBenefitRuleId() {
        return benefitRuleId;
    }
    /**
     * @param benefitRuleId The benefitRuleId to set.
     */
    public void setBenefitRuleId(String benefitRuleId) {
        this.benefitRuleId = benefitRuleId;
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
