/*
 * RetrieveRoleModuleAssociationRequest.java
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
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveRoleModuleAsssociationRequest extends WPDRequest {
    private int roleId;
    
    private String srdaFlag;
    
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }

    /**
     * @return Returns the roleId.
     * @return int roleId
     */
    public int getRoleId() {
        return roleId;
    }
    /**
     * Sets the roleId
     * @param roleId
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

	public String getSrdaFlag() {
		return srdaFlag;
	}

	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}
}
