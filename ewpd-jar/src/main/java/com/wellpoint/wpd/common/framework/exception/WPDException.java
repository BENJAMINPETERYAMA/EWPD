/*
 * WPDException.java
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
 * @version $Id: WPDException.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class WPDException extends Exception {

    protected List logParameters;

    public WPDException(String logMessage, List logParameters, Throwable cause) {
        super(logMessage, cause);
        this.logParameters = logParameters;
    }

    /**
     * @return Returns the logParameters.
     */
    public List getLogParameters() {
        return logParameters;
    }
    
    
    /**
     * @param logParameters The logParameters to set.
     */
    public void setLogParameters(List logParameters) {
        this.logParameters = logParameters;
    }
}