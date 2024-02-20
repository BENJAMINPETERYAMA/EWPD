/*
 * SaveAdminOptionQuestionResponse.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.adminoption.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.adminoption.vo.AdminOptionVO;
import com.wellpoint.wpd.common.adminoption.vo.AssociatedQuestionVO;

/**
 * Response for Saving Admin Option Question
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveAdminOptionQuestionResponse extends WPDResponse {

    private AdminOptionVO adminOptionVO;

    private boolean errorFlag = false;

    private AssociatedQuestionVO associatedQuestionVO;


    /**
     * constructor
     */
    public SaveAdminOptionQuestionResponse() {
        adminOptionVO = new AdminOptionVO();
        associatedQuestionVO = new AssociatedQuestionVO();
    }


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
}