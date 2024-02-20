/*
 * ErrorMessage.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.messages;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ErrorMessage extends Message {

    protected String logId;

    /**
     * @param id
     */
    public ErrorMessage(String id) {
        super(id);
    }

    public ErrorMessage(String messageId, String logId) {
        super(messageId);
        this.logId = logId;
    }

    /**
     * @return Returns the logId.
     */
    public String getLogId() {
        return logId;
    }

    /**
     * @param logId The logId to set.
     */
    public void setLogId(String logId) {
        this.logId = logId;
    }

    /**
     * @see java.lang.Object#toString()
     * @return
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        sb.append(",logId=").append(logId);
        return sb.toString();
    }
}