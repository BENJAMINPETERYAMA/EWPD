/*
 * RetrieveMandateRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.mandate.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.mandate.vo.MandateVO;


/**
 * Request for retrieving Mandate.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveMandateRequest extends WPDRequest {

    private MandateVO mandateVO;


    /**
     * Returns the mandateVO
     * 
     * @return MandateVO mandateVO.
     */
    public MandateVO getMandateVO() {
        return mandateVO;
    }


    /**
     * Sets the mandateVO
     * 
     * @param mandateVO.
     */
    public void setMandateVO(MandateVO mandateVO) {
        this.mandateVO = mandateVO;
    }


    /**
     * Method to validate the request Returns the void
     * 
     * @return void.
     */
    public void validate() throws ValidationException {
        if (-1 == this.mandateVO.getMandateId())
            throw new ValidationException("Mandate Id is missing", null, null);
    }
}