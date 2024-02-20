/*
 * AdvancedSearchRequest.java
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
import com.wellpoint.wpd.common.search.criteria.AdvancedSearchCriteria;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: AdvancedSearchRequest.java 24689 2007-06-21 11:42:53Z u12046 $
 */
public class AdvancedSearchRequest extends WPDRequest {
	
	private AdvancedSearchCriteria advancedSearchCriteria;   
    
	/**
	 * @return Returns the advancedSearchCriteria.
	 */
	public AdvancedSearchCriteria getAdvancedSearchCriteria() {
		return advancedSearchCriteria;
	}
	/**
	 * @param advancedSearchCriteria The advancedSearchCriteria to set.
	 */
	public void setAdvancedSearchCriteria(
			AdvancedSearchCriteria advancedSearchCriteria) {
		this.advancedSearchCriteria = advancedSearchCriteria;
	}
	/**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }
}
