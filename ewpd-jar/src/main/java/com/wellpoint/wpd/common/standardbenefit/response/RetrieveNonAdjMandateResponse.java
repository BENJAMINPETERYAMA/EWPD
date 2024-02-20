/*
 * RetrieveNonAdjMandateResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitMandateBO;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveNonAdjMandateResponse extends WPDResponse {
    // variable declarations
    BenefitMandateBO benefitMandateBO; 
    
    
    /**
     * @return Returns the benefitMandateBO.
     */
    public BenefitMandateBO getBenefitMandateBO() {
        return benefitMandateBO;
    }
    /**
     * @param benefitMandateBO The benefitMandateBO to set.
     */
    public void setBenefitMandateBO(BenefitMandateBO benefitMandateBO) {
        this.benefitMandateBO = benefitMandateBO;
    }
}
