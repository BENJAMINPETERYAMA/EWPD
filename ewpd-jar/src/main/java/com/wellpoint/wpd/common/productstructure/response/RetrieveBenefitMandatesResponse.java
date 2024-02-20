/*
 * ProductStructureBenefitMandatesResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.response;

import java.util.List;

import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.productstructure.bo.MandatesBO;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBO;
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

    private ProductStructureBO productStructureBO;


    /**
     * @return mandatesBO
     * 
     * Returns the mandatesBO.
     */
    public MandatesBO getMandatesBO() {
        return mandatesBO;
    }


    /**
     * Sets the mandatesBO
     * 
     * @param mandatesBO.
     */

    public void setMandatesBO(MandatesBO mandatesBO) {
        this.mandatesBO = mandatesBO;
    }


    /**
     * Returns the mandateList
     * 
     * @return List mandateList.
     */

    public List getMandateList() {
        return mandateList;
    }


    /**
     * Sets the mandateList
     * 
     * @param mandateList.
     */

    public void setMandateList(List mandateList) {
        this.mandateList = mandateList;
    }


    /**
     * Returns the productStructureBO
     * 
     * @return ProductStructureBO productStructureBO.
     */

    public ProductStructureBO getProductStructureBO() {
        return productStructureBO;
    }


    /**
     * Sets the productStructureBO
     * 
     * @param productStructureBO.
     */

    public void setProductStructureBO(ProductStructureBO productStructureBO) {
        this.productStructureBO = productStructureBO;
    }


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
}