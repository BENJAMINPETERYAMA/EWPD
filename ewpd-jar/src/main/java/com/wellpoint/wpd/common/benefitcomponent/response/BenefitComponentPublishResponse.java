/*
 * BenefitComponentPublishResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.benefitcomponent.response;

import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitComponentVO;
import com.wellpoint.wpd.common.framework.response.WPDResponse;


/**
 * Response for Check Out Benefit Component
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentPublishResponse extends WPDResponse {

    private boolean errorFlag;

    private BenefitComponentVO benefitComponentVO;


    /**
     * Returns the errorFlag.
     * 
     * @return errorFlag
     */
    public boolean isErrorFlag() {
        return errorFlag;
    }


    /**
     * Sets the errorFlag.
     * 
     * @param errorFlag
     */
    public void setErrorFlag(boolean errorFlag) {
        this.errorFlag = errorFlag;
    }


    /**
     * Returns the benefitComponentVO
     * 
     * @return BenefitComponentVO benefitComponentVO.
     */
    public BenefitComponentVO getBenefitComponentVO() {
        return benefitComponentVO;
    }


    /**
     * Sets the benefitComponentVO
     * 
     * @param benefitComponentVO.
     */
    public void setBenefitComponentVO(BenefitComponentVO benefitComponentVO) {
        this.benefitComponentVO = benefitComponentVO;
    }

}