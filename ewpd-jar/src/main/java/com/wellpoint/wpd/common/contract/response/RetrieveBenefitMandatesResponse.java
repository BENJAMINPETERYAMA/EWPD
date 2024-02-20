/*
 * RetrieveBenefitMandatesResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.List;

import com.wellpoint.wpd.common.contract.bo.Contract;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.productstructure.bo.MandatesBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitMandateBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveBenefitMandatesResponse extends WPDResponse {
    
    private MandatesBO mandatesBO;

    private List mandateList;

    private BenefitMandateBO benefitMandateBO;

    private DomainDetail domainDetail;

    private Contract contractBO;
    
    
    

    /**
     * @return benefitMandateBO
     * 
     * Returns the benefitMandateBO.
     */
    public BenefitMandateBO getBenefitMandateBO() {
        return benefitMandateBO;
    }
    /**
     * @param benefitMandateBO
     * 
     * Sets the benefitMandateBO.
     */
    public void setBenefitMandateBO(BenefitMandateBO benefitMandateBO) {
        this.benefitMandateBO = benefitMandateBO;
    }
    /**
     * @return contractBO
     * 
     * Returns the contractBO.
     */
    public Contract getContractBO() {
        return contractBO;
    }
    /**
     * @param contractBO
     * 
     * Sets the contractBO.
     */
    public void setContractBO(Contract contractBO) {
        this.contractBO = contractBO;
    }
    /**
     * @return domainDetail
     * 
     * Returns the domainDetail.
     */
    public DomainDetail getDomainDetail() {
        return domainDetail;
    }
    /**
     * @param domainDetail
     * 
     * Sets the domainDetail.
     */
    public void setDomainDetail(DomainDetail domainDetail) {
        this.domainDetail = domainDetail;
    }
    /**
     * @return mandateList
     * 
     * Returns the mandateList.
     */
    public List getMandateList() {
        return mandateList;
    }
    /**
     * @param mandateList
     * 
     * Sets the mandateList.
     */
    public void setMandateList(List mandateList) {
        this.mandateList = mandateList;
    }
    /**
     * @return mandatesBO
     * 
     * Returns the mandatesBO.
     */
    public MandatesBO getMandatesBO() {
        return mandatesBO;
    }
    /**
     * @param mandatesBO
     * 
     * Sets the mandatesBO.
     */
    public void setMandatesBO(MandatesBO mandatesBO) {
        this.mandatesBO = mandatesBO;
    }
}
