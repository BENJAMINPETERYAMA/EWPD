/*
 * CreateMandateRequest.java
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
 * Request for creating Mandate
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CreateMandateRequest extends WPDRequest {

    private boolean createFlag;

    private MandateVO mandateVO;


    /**
     * Returns the createFlag
     * 
     * @return boolean createFlag.
     */
    public boolean isCreateFlag() {
        return createFlag;
    }


    /**
     * Sets the createFlag
     * 
     * @param createFlag.
     */
    public void setCreateFlag(boolean createFlag) {
        this.createFlag = createFlag;
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
     * Method to validate the request Returns the void
     * 
     * @return void.
     * @throws ValidationException
     */
    public void validate() throws ValidationException {

        if (null == this.mandateVO.getMandateName()
                || "".equals(this.mandateVO.getMandateName()))
            throw new ValidationException("Mandate Name is missing", null, null);
        if (null == this.mandateVO.getEffectiveDate()
                || "".equals(this.mandateVO.getEffectiveDate()))
            throw new ValidationException("Effective Date is missing", null,
                    null);
        if (null == this.mandateVO.getExpiryDate()
                || "".equals(this.mandateVO.getExpiryDate()))
            throw new ValidationException("Expiry Date is missing", null, null);
        if (null == this.mandateVO.getMandateTypeId()
                || "".equals(this.mandateVO.getMandateTypeId()))
            throw new ValidationException("Mandate Type is missing", null, null);
        if (null == this.mandateVO.getJurisdictionId()
                || "".equals(this.mandateVO.getJurisdictionId()))
            throw new ValidationException("Jurisdiction Id is missing", null,
                    null);
        if (null == this.mandateVO.getFundingArrangementId()
                || "".equals(this.mandateVO.getFundingArrangementId()))
            throw new ValidationException("Funding Arrangement Id is missing",
                    null, null);
        if (null == this.mandateVO.getGroupSizeId()
                || "".equals(this.mandateVO.getGroupSizeId()))
            throw new ValidationException("Group Size is missing", null, null);
        if (null == this.mandateVO.getCitationNumber()
                || "".equals(this.mandateVO.getCitationNumber()))
            throw new ValidationException("Citation Numbers is missing", null,
                    null);
    }
}