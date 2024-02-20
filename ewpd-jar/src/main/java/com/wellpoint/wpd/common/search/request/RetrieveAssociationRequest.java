/*
 * RetrieveAssociationRequest.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.search.request;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.search.result.ObjectIdentifier;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: RetrieveAssociationRequest.java 24689 2007-06-21 11:42:53Z u12046 $
 */
public class RetrieveAssociationRequest extends WPDRequest {

    private ObjectIdentifier objectIdentifier;
    private int resultCountLimit;
    
    /**
     * @return Returns the objectIdentifier.
     */
    public ObjectIdentifier getObjectIdentifier() {
        return objectIdentifier;
    }
    /**
     * @param objectIdentifier The objectIdentifier to set.
     */
    public void setObjectIdentifier(ObjectIdentifier objectIdentifier) {
        this.objectIdentifier = objectIdentifier;
    }
    
    /**
     * @return Returns the resultCountLimit.
     */
    public int getResultCountLimit() {
        return resultCountLimit;
    }
    /**
     * @param resultCountLimit The resultCountLimit to set.
     */
    public void setResultCountLimit(int resultCountLimit) {
        this.resultCountLimit = resultCountLimit;
    }
    /**
     * 
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        if(objectIdentifier == null){
            List l = new ArrayList();
            l.add(this);
            throw new ValidationException("objectIdentifier not set",l,null);
        }
    }

}
