/*
 * MetadataParserException.java
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
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: MetadataParserException.java 10586 2007-02-15 19:54:29Z U10567 $
 */
public class MetadataParserException extends WPDException{
	
    /**
     * Constructor with parameters
     * 
     * @param message
     * @param logParameters
     * @param cause
     */
	public MetadataParserException(String message,List logParameters, Throwable cause) {
		super(message, logParameters, cause);
	}
}
