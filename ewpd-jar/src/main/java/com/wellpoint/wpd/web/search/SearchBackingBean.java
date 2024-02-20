/*
 * SearchBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.search;

import java.util.List;

import com.wellpoint.wpd.web.framework.WPDBackingBean;

/**
 * This is the parent backing bean for search functionality. 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SearchBackingBean extends WPDBackingBean {
    
    /**
     * List of BusinessDomain objects
     */
    protected List  businessDomains;
    
    /**
     * @return Returns the businessDomains.
     */
    public List getBusinessDomains() {
        return businessDomains;
    }
    /**
     * @param businessDomains The businessDomains to set.
     */
    public void setBusinessDomains(List businessDomains) {
        this.businessDomains = businessDomains;
    }
    
}
