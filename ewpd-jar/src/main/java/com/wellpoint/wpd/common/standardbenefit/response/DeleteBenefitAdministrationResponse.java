/*
 * Created on Mar 17, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitAdministrationBO;


/**
 * @author U13274
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DeleteBenefitAdministrationResponse extends WPDResponse{
    private BenefitAdministrationBO benefitAdministrationBO;
    
    
    /**
     * Returns the benefitAdministrationBO
     * @return BenefitAdministrationBO benefitAdministrationBO.
     */

    public BenefitAdministrationBO getBenefitAdministrationBO() {
        return benefitAdministrationBO;
    }
    /**
     * Sets the benefitAdministrationBO
     * @param benefitAdministrationBO.
     */

    public void setBenefitAdministrationBO(
            BenefitAdministrationBO benefitAdministrationBO) {
        this.benefitAdministrationBO = benefitAdministrationBO;
    }
}
