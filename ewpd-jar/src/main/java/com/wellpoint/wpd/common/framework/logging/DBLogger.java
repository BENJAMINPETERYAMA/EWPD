/*
 * DBLogger.java
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
 * @version $Id: DBLogger.java 2458 2006-08-21 19:33:16Z U10347 $
 */
public class DBLogger extends ExceptionLogger{

    private LoggerDao loggerDao;

    /**
     *  
     */
    public DBLogger() {
        super();
    }
    
    protected void log(LogEntry le) throws Exception {
        //create a LogEntry object here
        //call LoggerDaoImpl.insertLog();
        loggerDao.insertLog(le);
    }

    /**
     * @return Returns the loggerDao.
     */
    public LoggerDao getLoggerDao() {
        return loggerDao;
    }
    /**
     * @param loggerDao The loggerDao to set.
     */
    public void setLoggerDao(LoggerDao loggerDao) {
        this.loggerDao = loggerDao;
    }
}

