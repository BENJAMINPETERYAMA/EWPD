/*
 * Created on Aug 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U16012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveProductAdminOptionResponse extends WPDResponse {
	
	 private List benefitAdministrationList;
	    
	    

	    /**
	     * Returns the benefitAdministrationList
	     * @return List benefitAdministrationList.
	     */
	    public List getBenefitAdministrationList() {
	        return benefitAdministrationList;
	    }
	    /**
	     * Sets the benefitAdministrationList
	     * @param benefitAdministrationList.
	     */
	    public void setBenefitAdministrationList(List benefitAdministrationList) {
	        this.benefitAdministrationList = benefitAdministrationList;

	    }
}
