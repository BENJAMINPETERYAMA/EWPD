/*
 * LoggerDao.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.logging;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: LoggerDao.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public interface LoggerDao {
    void insertLog(LogEntry entry) throws Exception;
}
