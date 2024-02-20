/*
 * BasicSearchRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.search.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.search.criteria.BasicSearchCriteria;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: BasicSearchRequest.java 24689 2007-06-21 11:42:53Z u12046 $
 */
public class BasicSearchRequest extends WPDRequest {
	
	private BasicSearchCriteria basicSearchCriteria;
	/**
	 * @return Returns the basicSearchCriteria.
	 */
	public BasicSearchCriteria getBasicSearchCriteria() {
		return basicSearchCriteria;
	}
	/**
	 * @param basicSearchCriteria The basicSearchCriteria to set.
	 */
	public void setBasicSearchCriteria(BasicSearchCriteria basicSearchCriteria) {
		this.basicSearchCriteria = basicSearchCriteria;
	}
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }
}
