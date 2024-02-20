/*
 * ValidationServiceController.java
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
 * @version $Id: ValidationServiceController.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class ValidationServiceController extends WPDServiceController {

    public ValidationServiceController() {

    }

    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDServiceController#getServiceMappingFileName()
     * @return
     */
    protected String getServiceMappingFileName() {
        return "com/wellpoint/wpd/common/configfiles/validationServiceMapping.xml";
    }

    protected String getContextConfigFileName() {
        return "com/wellpoint/wpd/common/configfiles/validationServicesBeanFactory.xml";
    }

}