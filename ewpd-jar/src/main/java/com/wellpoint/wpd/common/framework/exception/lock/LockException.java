/*
 * LockException.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.exception.lock;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.SevereException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: LockException.java 17213 2007-04-04 00:27:39Z U11087 $
 */

public class LockException extends SevereException {
	
	public LockException(String logMessage, List logParameters, Throwable cause) {
		super(logMessage,logParameters,cause);
	}
}
