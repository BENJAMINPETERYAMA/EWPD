/*
 * RetrieveModuleAssociationRequest.java
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

public class RetrieveModuleAssociationRequest extends WPDRequest {
    private int moduleId;
    
    private String subEntityType;
    
    private String srdaFlag;
    
    private int associatedTaskId;
    
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }

    /**
     * @return associatedTaskId
     * Returns the associatedTaskId.
     */
    public int getAssociatedTaskId() {
        return associatedTaskId;
    }
    /**
     * @param associatedTaskId
     * Sets the associatedTaskId.
     */
    public void setAssociatedTaskId(int associatedTaskId) {
        this.associatedTaskId = associatedTaskId;
    }
    /**
     * @return subEntityType
     * Returns the subEntityType.
     */
    public String getSubEntityType() {
        return subEntityType;
    }
    /**
     * @param subEntityType
     * Sets the subEntityType.
     */
    public void setSubEntityType(String subEntityType) {
        this.subEntityType = subEntityType;
    }
    /**
     * @return Returns the moduleId.
     * @return int moduleId
     */
    public int getModuleId() {
        return moduleId;
    }
    /**
     * Sets the moduleId
     * @param moduleId
     */
    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

	public String getSrdaFlag() {
		return srdaFlag;
	}

	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}
}
