/*
 * WPDRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.security.domain.User;

/**
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: WPDRequest.java 236 2006-07-15 14:56:16Z U10347 $
 */
public abstract class WPDRequest {

    private User user;
    /**
     * @return Returns the user.
     */
    public User getUser() {
        return user;
    }
    /**
     * @param user The user to set.
     */
    public void setUser(User user) {
        this.user = user;
    }
    /**
     * 
     */
    public WPDRequest() {
        super();
    }
    
    public abstract void validate() throws ValidationException;

}
