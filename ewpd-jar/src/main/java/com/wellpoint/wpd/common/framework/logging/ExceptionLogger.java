/*
 * ExceptionLogger.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;


import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: ExceptionLogger.java 2458 2006-08-21 19:33:16Z U10347 $
 */
public abstract class ExceptionLogger {

    private static String serverName;
    protected int count;    
    /**
     * 
     */
    public ExceptionLogger() {
        super();
        if(serverName == null){
            try{
                /*AdminService as = AdminServiceFactory.getAdminService();*/
                serverName = System.getProperty("NODE");
            }catch(Exception e){
                Exception ex = new Exception("Error retrieving cell and node name in ExceptionLogger()",e);
                Logger.logError(ex);
                serverName = "UNKNOWN";
            }
        }
    }
    
    public void log(WPDException exception) throws Exception{
        if (exception != null) {
            String parametersString = retrieveInfoAsString(exception
                    .getLogParameters());
            String stackTrace = retrieveStackTraceAsString(exception);
            LogEntry le = new LogEntry();
            if (exception.getMessage() != null)
                le.setMessage(exception.getMessage());
            if (parametersString != null)
                le.setParameters(parametersString);
            if (stackTrace != null)
                le.setStackTrace(stackTrace);
            le.setLoggingEntity(LoggerConfiguration.loggingEntity);
            le.setServerName(serverName);
            AuditFactory auditFactory = (AuditFactory) ObjectFactory
            .getFactory(ObjectFactory.AUDIT);
            Audit audit = auditFactory.getAudit();
            le.setUpdateTimestamp(audit.getTime());
            log(le);
            if(exception instanceof SevereException){
                ((SevereException)exception).setLogId(Integer.toString(le.getId()));
            }
        }
    }
    
    protected String retrieveInfoAsString(List logParams) {
        if (logParams != null && logParams.size() > 0) {
            StringBuffer buffer = null;
            Iterator paramsIter = logParams.iterator();
            while (paramsIter.hasNext()) {
                if (buffer == null) {
                    buffer = new StringBuffer();
                } else {
                    buffer.append("\n");
                }
                Object obj = paramsIter.next();
                buffer.append("-------------------------");
                if (obj != null) {
                     buffer.append(obj.getClass().getName()).append("-------------------------\n");
                    buffer.append(obj.toString());
                }else{
                    buffer.append("NULL").append("-------------------------\n");
                }
            }
            return buffer.toString();
        }
        return "";
    }

    protected String retrieveStackTraceAsString(Throwable exception) {
        if (exception != null) {
            StringBuffer buffer = new StringBuffer();
            StringWriter sw = new StringWriter();
            exception.printStackTrace(new PrintWriter(sw));
            buffer.append(sw.toString());
            return buffer.toString();
        }
        return "";
    }
    
    protected abstract void log(LogEntry le) throws Exception;

}
