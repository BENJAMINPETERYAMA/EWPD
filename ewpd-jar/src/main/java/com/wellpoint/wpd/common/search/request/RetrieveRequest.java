/*
 * RetrieveResultRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.search.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: RetrieveRequest.java 24689 2007-06-21 11:42:53Z u12046 $
 */
public class RetrieveRequest extends WPDRequest {
    
    private List objectIdentifiers;
   
    /**
     * @return Returns the objectIdentifiers.
     */
    public List getObjectIdentifiers() {
        return objectIdentifiers;
    }
    /**
     * @param objectIdentifiers The objectIdentifiers to set.
     */
    public void setObjectIdentifiers(List objectIdentifiers) {
        this.objectIdentifiers = objectIdentifiers;
    }    
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }

}
