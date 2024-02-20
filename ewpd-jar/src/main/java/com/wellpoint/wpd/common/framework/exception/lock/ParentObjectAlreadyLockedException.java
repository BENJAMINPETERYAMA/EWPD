/*
 * ParentObjectAlreadyLockedException.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.exception.lock;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: ParentObjectAlreadyLockedException.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class ParentObjectAlreadyLockedException extends LockException {
    public ParentObjectAlreadyLockedException(String message,List logParameters, Throwable cause) {
		super(message, logParameters, cause);
	}
}
