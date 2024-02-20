/*
 * ServiceManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.framework.service;

import com.wellpoint.wpd.business.framework.service.BusinessServiceController;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: BusinessServiceManager.java 231 2006-07-15 14:53:38Z U10347 $
 */
public class BusinessServiceManager {

    private BusinessServiceManager() {
        super();
    }

    public static WPDResponse execute(WPDRequest request)
            throws ServiceException {
        return new BusinessServiceController().execute(request);
    }

}