/*
 * Task.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.security.domain;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: Task.java 2458 2006-08-21 19:33:16Z U10347 $
 */
public interface Task {
    public long getId();
    public String getName();
    public boolean isAuthorized(String subtask);
}
