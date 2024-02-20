/*
 * BenefitComponentApproveRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.benefitcomponent.request;

import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitComponentVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * Request for Benefit Component Approval.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentApproveRequest extends WPDRequest {

    private BenefitComponentVO benefitComponentVO;

    private BenefitComponentBO benefitComponentBO;


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


    /**
     * Method to validate the request Returns the void
     * 
     * @return void.
     */
    public void validate() throws ValidationException {
        if (-1 == this.benefitComponentVO.getBenefitComponentId())
            throw new ValidationException("Benefit Component Id is missing",
                    null, null);
    }


    /**
     * @return Returns the benefitComponentBO.
     */
    public BenefitComponentBO getBenefitComponentBO() {
        return benefitComponentBO;
    }


    /**
     * @param benefitComponentBO
     *            The benefitComponentBO to set.
     */
    public void setBenefitComponentBO(BenefitComponentBO benefitComponentBO) {
        this.benefitComponentBO = benefitComponentBO;
    }
}