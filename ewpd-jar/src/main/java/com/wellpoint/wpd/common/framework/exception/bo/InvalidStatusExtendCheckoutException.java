/*
 * InvalidStatusExtendCheckoutException.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.exception.bo;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.SevereException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: InvalidStatusExtendCheckoutException.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class InvalidStatusExtendCheckoutException extends SevereException {
    public InvalidStatusExtendCheckoutException(String message,List logParameters, Throwable cause) {
		super(message, logParameters, cause);
	}
}
