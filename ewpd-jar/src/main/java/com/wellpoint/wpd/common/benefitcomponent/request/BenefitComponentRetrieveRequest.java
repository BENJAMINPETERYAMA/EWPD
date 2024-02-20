/*
 * BenefitComponentRetrieveRequest.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.benefitcomponent.request;

import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitComponentVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * Request for Benefit Component Retrieve
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentRetrieveRequest extends WPDRequest {

    private BenefitComponentVO benefitComponentVO;

    private int mainObjVersion;


    /**
     * @return Returns the benefitComponentVO.
     */
    public BenefitComponentVO getBenefitComponentVO() {
        return benefitComponentVO;
    }


    /**
     * @param benefitComponentVO
     *            The benefitComponentVO to set.
     */
    public void setBenefitComponentVO(BenefitComponentVO benefitComponentVO) {
        this.benefitComponentVO = benefitComponentVO;
    }


    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {

    }


    /**
     * @return Returns the mainObjVersion.
     */
    public int getMainObjVersion() {
        return mainObjVersion;
    }


    /**
     * @param mainObjVersion
     *            The mainObjVersion to set.
     */
    public void setMainObjVersion(int mainObjVersion) {
        this.mainObjVersion = mainObjVersion;
    }
}