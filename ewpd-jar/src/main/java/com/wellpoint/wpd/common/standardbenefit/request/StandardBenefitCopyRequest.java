/*
 * Created on Mar 29, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.standardbenefit.vo.StandardBenefitVO;


/**
 * @author u13274
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StandardBenefitCopyRequest extends WPDRequest{
    
    private StandardBenefitVO standardBenefitVO;
    
    /**
     * Returns the standardBenefitVO
     * @return StandardBenefitVO standardBenefitVO.
     */

    public StandardBenefitVO getStandardBenefitVO() {
        return standardBenefitVO;
    }
    /**
     * Sets the standardBenefitVO
     * @param standardBenefitVO.
     */

    public void setStandardBenefitVO(StandardBenefitVO standardBenefitVO) {
        this.standardBenefitVO = standardBenefitVO;
    }
    /* (non-Javadoc)
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }
}
