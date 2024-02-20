/*
 * CreateMandateResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.mandate.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.mandate.bo.MandateBO;
import com.wellpoint.wpd.common.mandate.vo.MandateVO;


/**
 * Response for creating Admin Option
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CreateMandateResponse extends WPDResponse {

    private MandateBO mandateBO;

    private MandateVO mandateVO;

    private boolean errorFlag;


    /**
     * Returns the mandateBO
     * 
     * @return MandateBO mandateBO.
     */
    public MandateBO getMandateBO() {
        return mandateBO;
    }


    /**
     * Sets the mandateBO
     * 
     * @param mandateBO.
     */
    public void setMandateBO(MandateBO mandateBO) {
        this.mandateBO = mandateBO;
    }


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
     * Returns the errorFlag
     * 
     * @return boolean errorFlag.
     */
    public boolean isErrorFlag() {
        return errorFlag;
    }


    /**
     * Sets the errorFlag
     * 
     * @param errorFlag.
     */
    public void setErrorFlag(boolean errorFlag) {
        this.errorFlag = errorFlag;
    }
}