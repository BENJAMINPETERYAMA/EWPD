/*
 * Role.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.ets.bx.mapping.application.security;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: Role.java 2458 2006-08-21 19:33:16Z U10347 $
 */
public interface Role {
    public long getId();
    public String getDescription();
    public boolean isAuthorized(String module, String task,String subtask);
}
