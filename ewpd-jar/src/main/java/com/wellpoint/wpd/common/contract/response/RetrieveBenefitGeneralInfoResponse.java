/*
 * RetrieveBenefitGeneralInfoResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import com.wellpoint.wpd.common.contract.bo.RuleIdBO;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveBenefitGeneralInfoResponse extends ContractResponse {
    
    private StandardBenefitBO standardBenefitBO = null;
	private DomainDetail domainDetail ;
	private RuleIdBO ruleIdBO;
    private String defaultRuleID;    
	/**
	 * Returns the defaultRuleDesc
	 * @return String defaultRuleDesc.
	 */
	public String getDefaultRuleDesc() {
		return defaultRuleDesc;
	}
	/**
	 * Sets the defaultRuleDesc
	 * @param defaultRuleDesc.
	 */
	public void setDefaultRuleDesc(String defaultRuleDesc) {
		this.defaultRuleDesc = defaultRuleDesc;
	}
	/**
	 * Returns the defaultRuleID
	 * @return String defaultRuleID.
	 */
	public String getDefaultRuleID() {
		return defaultRuleID;
	}
	/**
	 * Sets the defaultRuleID
	 * @param defaultRuleID.
	 */
	public void setDefaultRuleID(String defaultRuleID) {
		this.defaultRuleID = defaultRuleID;
	}
    private String defaultRuleDesc;

    /**
     * Returns the domainDetail
     * @return DomainDetail domainDetail.
     */
    public DomainDetail getDomainDetail() {
        return domainDetail;
    }
    /**
     * Sets the domainDetail
     * @param domainDetail.
     */
    public void setDomainDetail(DomainDetail domainDetail) {
        this.domainDetail = domainDetail;
    }
    /**
     * Returns the standardBenefitBO
     * @return StandardBenefitBO standardBenefitBO.
     */
    public StandardBenefitBO getStandardBenefitBO() {
        return standardBenefitBO;
    }
    /**
     * Sets the standardBenefitBO
     * @param standardBenefitBO.
     */
    public void setStandardBenefitBO(StandardBenefitBO standardBenefitBO) {
        this.standardBenefitBO = standardBenefitBO;
    }
	/**
	 * @return Returns the ruleIdBO.
	 */
	public RuleIdBO getRuleIdBO() {
		return ruleIdBO;
	}
	/**
	 * @param ruleIdBO The ruleIdBO to set.
	 */
	public void setRuleIdBO(RuleIdBO ruleIdBO) {
		this.ruleIdBO = ruleIdBO;
	}
}
