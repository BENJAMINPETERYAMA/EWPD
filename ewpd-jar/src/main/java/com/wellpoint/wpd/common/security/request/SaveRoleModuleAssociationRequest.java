/*
 * SaveRoleModuleAssociationRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.security.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.security.vo.RoleVO;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveRoleModuleAssociationRequest extends WPDRequest {
    private RoleVO roleVO;
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }

    /**
     * @return Returns the roleVO.
     * @return RoleVO roleVO
     */
    public RoleVO getRoleVO() {
        return roleVO;
    }
    /**
     * Sets the roleVO
     * @param roleVO
     */
    public void setRoleVO(RoleVO roleVO) {
        this.roleVO = roleVO;
    }
}
