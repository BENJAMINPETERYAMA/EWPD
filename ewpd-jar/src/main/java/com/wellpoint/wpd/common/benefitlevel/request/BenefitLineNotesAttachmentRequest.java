/*
 * BenefitLineNotesAttachmentRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitlevel.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.standardbenefit.vo.StandardBenefitVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitLineNotesAttachmentRequest extends WPDRequest{
    
    private StandardBenefitVO standardBenefitVO;
    
    private String benefitLineId;

    /**
     * Returns the benefitLineId
     * @return String benefitLineId.
     */

    public String getBenefitLineId() {
        return benefitLineId;
    }
    /**
     * Sets the benefitLineId
     * @param benefitLineId.
     */

    public void setBenefitLineId(String benefitLineId) {
        this.benefitLineId = benefitLineId;
    }
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
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }

}
