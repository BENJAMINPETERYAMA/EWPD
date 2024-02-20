/*
 * WPDRequestFactory.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.request;

import com.wellpoint.wpd.common.framework.exception.ServiceException;

/**
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: WPDRequestFactory.java 74834 2011-10-11 12:57:17Z u23599 $
 */
public interface WPDRequestFactory {
	
	WPDRequest getRequest(String serviceName) throws ServiceException;
}
