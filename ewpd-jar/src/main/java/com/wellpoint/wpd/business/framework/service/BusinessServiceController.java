/*
 * BusinessServiceController.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.service;

/**
 *
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: BusinessServiceController.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class BusinessServiceController extends WPDServiceController {

    public BusinessServiceController(){

    }

    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDServiceController#getServiceMappingFileName()
     * @return
     */
    protected String getServiceMappingFileName() {
	        return "com/wellpoint/wpd/common/configfiles/businessServiceMapping.xml";
	    }

	    protected String getContextConfigFileName() {
	        return "com/wellpoint/wpd/common/configfiles/businessServicesBeanFactory.xml";
    }
}
