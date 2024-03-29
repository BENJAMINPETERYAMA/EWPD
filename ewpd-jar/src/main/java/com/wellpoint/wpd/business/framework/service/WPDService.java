/*
 * WPDService.java
 * 
 * � 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.service;

import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: WPDService.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public abstract class WPDService {

    /**
     * 
     */
    public WPDService() {
        super();
    }
    public abstract WPDResponse execute(WPDRequest request) throws ServiceException;

}
