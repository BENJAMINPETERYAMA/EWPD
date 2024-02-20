/*
 * Created on Mar 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * @author U13274
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LocateBenefitAdministrationRequest extends WPDRequest{
    
    private int benefitDefinitionKey;
    private int maxSearchResultSize;
    /**
     * Returns the benefitDefinitionKey
     * @return int benefitDefinitionKey.
     */

    public int getBenefitDefinitionKey() {
        return benefitDefinitionKey;
    }
    /**
     * Sets the benefitDefinitionKey
     * @param benefitDefinitionKey.
     */

    public void setBenefitDefinitionKey(int benefitDefinitionKey) {
        this.benefitDefinitionKey = benefitDefinitionKey;
    }
    /* (non-Javadoc)
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }

	/**
	 * @return Returns the maxSearchResultSet.
	 */
	public int getMaxSearchResultSize() {
		return maxSearchResultSize;
	}
	/**
	 * @param maxSearchResultSet The maxSearchResultSet to set.
	 */
	public void setMaxSearchResultSize(int maxSearchResultSize) {
		this.maxSearchResultSize = maxSearchResultSize;
	}
}
