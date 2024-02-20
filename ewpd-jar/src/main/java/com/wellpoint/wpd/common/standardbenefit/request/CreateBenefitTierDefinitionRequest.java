/*
 * Created on Aug 03, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.request;


import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.standardbenefit.vo.BenefitTierDefinitionVO;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CreateBenefitTierDefinitionRequest extends WPDRequest{
    
    private BenefitTierDefinitionVO benefitTierDefinitionVO;
   
	
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }  	
   
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
   
}
