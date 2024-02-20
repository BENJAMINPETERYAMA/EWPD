/*
 * Created on Aug 07, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.standardbenefit.vo.BenefitTierDefinitionVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveProductTierDefnResponse extends WPDResponse{
    
    private BenefitTierDefinitionVO benefitTierDefinitionVO;

    /**
     * @return Returns the benefitTierDefinitionVO.
     */
    public BenefitTierDefinitionVO getBenefitTierDefinitionVO() {
        return benefitTierDefinitionVO;
    }
    /**
     * @param benefitTierDefinitionVO The benefitTierDefinitionVO to set.
     */
    public void setBenefitTierDefinitionVO(
            BenefitTierDefinitionVO benefitTierDefinitionVO) {
        this.benefitTierDefinitionVO = benefitTierDefinitionVO;
    }
    private boolean validationSuccess;
    /**
     * @return Returns the validationSuccess.
     */
    public boolean isValidationSuccess() {
        return validationSuccess;
    }
    /**
     * @param validationSuccess The validationSuccess to set.
     */
    public void setValidationSuccess(boolean validationSuccess) {
        this.validationSuccess = validationSuccess;
    }
}
