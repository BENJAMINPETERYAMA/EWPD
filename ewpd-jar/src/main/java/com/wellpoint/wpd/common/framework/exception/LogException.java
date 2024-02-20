/*
 * Created on Oct 3, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.framework.exception;

import java.util.List;

/**
 * @author u18739
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LogException extends SevereException {

	public LogException(String logMessage, List logParameters, Throwable cause) {
		super(logMessage,logParameters,cause);
	}
	
}
