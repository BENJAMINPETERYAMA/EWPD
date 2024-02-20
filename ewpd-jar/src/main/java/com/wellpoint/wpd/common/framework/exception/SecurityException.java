/*
 * SecurityException.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.exception;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SecurityException extends SevereException {

    /**
     * @param logMessage
     * @param logParameters
     * @param cause
     */
    public SecurityException(String logMessage, List logParameters, Throwable cause) {
        super(logMessage, logParameters, cause);
        // TODO Auto-generated constructor stub
    }

}
