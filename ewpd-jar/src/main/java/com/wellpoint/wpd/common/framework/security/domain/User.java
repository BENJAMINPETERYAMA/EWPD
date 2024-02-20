/*
 * User.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.security.domain;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: User.java 2458 2006-08-21 19:33:16Z U10347 $
 */
public interface User {
    public String getUserId();
    public boolean isAuthorized(String module);
    public boolean isAuthorized(String module, String task);
    public boolean isAuthorized(String module, String task, String subtask);
    public List getRoles();
}
