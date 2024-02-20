/*
 * DeleteAdminOptionQuestionRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.adminoption.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.adminoption.vo.AdminOptionVO;
import com.wellpoint.wpd.common.adminoption.vo.AssociatedQuestionVO;

/**
 * Request for deleting assocaited question of Admin Option
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DeleteAdminOptionQuestionRequest extends WPDRequest {

    private AssociatedQuestionVO associatedQuestionVO;

    private AdminOptionVO adminOptionVO;


    /**
     * Returns the adminOptionVO
     * 
     * @return AdminOptionVO adminOptionVO.
     */
    public AdminOptionVO getAdminOptionVO() {
        return adminOptionVO;
    }


    /**
     * Sets the adminOptionVO
     * 
     * @param adminOptionVO.
     */
    public void setAdminOptionVO(AdminOptionVO adminOptionVO) {
        this.adminOptionVO = adminOptionVO;
    }


    /**
     * Returns the associatedQuestionVO
     * 
     * @return AssociatedQuestionVO associatedQuestionVO.
     */
    public AssociatedQuestionVO getAssociatedQuestionVO() {
        return associatedQuestionVO;
    }


    /**
     * Sets the associatedQuestionVO
     * 
     * @param associatedQuestionVO.
     */
    public void setAssociatedQuestionVO(
            AssociatedQuestionVO associatedQuestionVO) {
        this.associatedQuestionVO = associatedQuestionVO;
    }


    /**
     * Method to validate the request Returns the void
     * 
     * @return void.
     */
    public void validate() throws ValidationException {
        if (-1 == this.associatedQuestionVO.getAdminOptionId())
            throw new ValidationException("Admin Option Id is missing", null,
                    null);
        if (-1 == this.associatedQuestionVO.getAssociatedQuestionId())
            throw new ValidationException(
                    "Admin Option Question Id is missing", null, null);

    }
}