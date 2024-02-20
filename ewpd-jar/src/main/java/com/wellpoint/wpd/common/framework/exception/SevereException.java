/*
 * SevereException.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.exception;

import java.util.List;

/**
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: SevereException.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class SevereException extends WPDException {

    protected String logId;

    public SevereException(String logMessage, List logParameters, Throwable cause) {
        super(logMessage, logParameters, cause);
    }

    /**
     * @return Returns the logId.
     */
    public String getLogId() {
        return logId;
    }

    /**
     * @param logId
     *            The logId to set.
     */
    public void setLogId(String logId) {
        this.logId = logId;
    }
    
    public String getMessage() {
        if(logId == null){
            return super.getMessage();
        }else{
            return super.getMessage() + " Refer Log ID " + logId;
        }
    }

}