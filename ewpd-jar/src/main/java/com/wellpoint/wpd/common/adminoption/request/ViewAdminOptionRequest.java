/*
 * ViewAdminOptionRequest.java
 * 
 * � 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.adminoption.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.adminoption.vo.AdminOptionVO;

/**
 * Request for Admin Option View
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ViewAdminOptionRequest extends WPDRequest {

    private AdminOptionVO adminOptionVO;

    private String action;

    private boolean createFlag;


    /**
     * Returns the createFlag.
     * 
     * @return createFlag
     */
    public boolean isCreateFlag() {
        return createFlag;
    }


    /**
     * The createFlag to set.
     * 
     * @param createFlag
     */
    public void setCreateFlag(boolean createFlag) {
        this.createFlag = createFlag;
    }


    /**
     * Returns the action.
     * 
     * @return action
     */
    public String getAction() {
        return action;
    }


    /**
     * The action to set.
     * 
     * @param action
     */
    public void setAction(String action) {
        this.action = action;
    }


    /**
     * Returns the adminOptionVO.
     * 
     * @return adminOptionVO
     */
    public AdminOptionVO getAdminOptionVO() {
        return adminOptionVO;
    }


    /**
     * The adminOptionVO to set.
     * 
     * @param adminOptionVO
     */
    public void setAdminOptionVO(AdminOptionVO adminOptionVO) {
        this.adminOptionVO = adminOptionVO;
    }


    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {

    }

}