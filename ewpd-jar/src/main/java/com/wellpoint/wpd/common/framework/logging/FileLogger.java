/*
 * FileLogger.java
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
 * @version $Id: FileLogger.java 2458 2006-08-21 19:33:16Z U10347 $
 */
public class FileLogger extends ExceptionLogger {

    /**
     * 
     */
    public FileLogger() {
        super();
    }

    /**
     * @see com.wellpoint.wpd.common.framework.logging.ExceptionLogger#log(com.wellpoint.wpd.common.framework.logging.LogEntry)
     * @param le
     * @throws Exception
     */
    protected void log(LogEntry le) throws Exception {
        StringBuffer buffer = new StringBuffer();
        buffer.append("MESSAGE: ").append(le.getMessage()).append("\n");
        buffer.append("SERVER NAME: ").append(le.getServerName()).append("\n");
        buffer.append("PARAMETERS: ").append(le.getParameters()).append("\n");
        buffer.append("STACK TRACE: ").append(le.getStackTrace()).append("\n");
        buffer.append("LOGGING ENTITY: ").append(le.getLoggingEntity()).append("\n");
        Logger.logError(buffer.toString());
    }

}
