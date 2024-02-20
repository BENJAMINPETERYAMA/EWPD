/*
 * Created on Mar 26, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.standardbenefit.vo.StandardBenefitSearchVO;
import com.wellpoint.wpd.common.standardbenefit.vo.StandardBenefitVO;


/**
 * @author U14647
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StandardBenefitDeleteRequest extends WPDRequest  {

    private StandardBenefitVO standardBenefitVO;
    
    private StandardBenefitSearchVO standardBenefitSearchVO;
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
    }
    
    /**
     * @return Returns the standardBenefitVO.
     */
    public StandardBenefitVO getStandardBenefitVO() {
        return standardBenefitVO;
    }
    /**
     * @param standardBenefitVO The standardBenefitVO to set.
     */
    public void setStandardBenefitVO(StandardBenefitVO standardBenefitVO) {
        this.standardBenefitVO = standardBenefitVO;
    }
    /**
     * @return Returns the standardBenefitSearchVO.
     */
    public StandardBenefitSearchVO getStandardBenefitSearchVO() {
        return standardBenefitSearchVO;
    }
    /**
     * @param standardBenefitSearchVO The standardBenefitSearchVO to set.
     */
    public void setStandardBenefitSearchVO(
            StandardBenefitSearchVO standardBenefitSearchVO) {
        this.standardBenefitSearchVO = standardBenefitSearchVO;
    }
}
