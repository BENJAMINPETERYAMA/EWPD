/*
 * LogEntry.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.logging;

import java.util.Date;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: LogEntry.java 2458 2006-08-21 19:33:16Z U10347 $
 */
public class LogEntry {

    private static final String DEFAULT_ENTRY = "NOT SPECIFIED";
    private int id;
    private String serverName;
    private String message;
    private String parameters;
    private String stackTrace;
    private Date updateTimestamp;
    private String loggingEntity;
    /**
     * @return Returns the loggingEntity.
     */
    public String getLoggingEntity() {
        return loggingEntity;
    }
    /**
     * @param userId The loggingEntity to set.
     */
    public void setLoggingEntity(String userId) {
        this.loggingEntity = userId;
    }
    /**
     * @return Returns the message.
     */
    public String getMessage() {
        return message;
    }
    /**
     * @param message The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * @return Returns the parameters.
     */
    public String getParameters() {
        return parameters;
    }
    /**
     * @param parameters The parameters to set.
     */
    public void setParameters(String parameters) {
        this.parameters = parameters;
    }
    /**
     * @return Returns the serverName.
     */
    public String getServerName() {
        return serverName;
    }
    /**
     * @param serverName The serverName to set.
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
    /**
     * @return Returns the stackTrace.
     */
    public String getStackTrace() {
        return stackTrace;
    }
    /**
     * @param stackTrace The stackTrace to set.
     */
    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
    /**
     * @return Returns the updateTimestamp.
     */
    public Date getUpdateTimestamp() {
        return updateTimestamp;
    }
    /**
     * @param updateTimestamp The updateTimestamp to set.
     */
    public void setUpdateTimestamp(Date updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }
    
    /**
     * @return Returns the id.
     */
    public int getId() {
        return id;
    }
    /**
     * @param id The id to set.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * 
     */
    public LogEntry() {
        super();
        serverName = DEFAULT_ENTRY;
        message = DEFAULT_ENTRY;
        parameters = DEFAULT_ENTRY;
        stackTrace = DEFAULT_ENTRY;   
    }
}
