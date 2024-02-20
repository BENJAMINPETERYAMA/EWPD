/*
 * Created on Mar 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.List;


/**
 * @author U13274
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LocateBenefitAdministrationResponse extends WPDResponse{
    
    private List benefitAdministrationsList;
    

    /**
     * Returns the benefitAdministrationsList
     * @return List benefitAdministrationsList.
     */

    public List getBenefitAdministrationsList() {
        return benefitAdministrationsList;
    }
    /**
     * Sets the benefitAdministrationsList
     * @param benefitAdministrationsList.
     */

    public void setBenefitAdministrationsList(List benefitAdministrationsList) {
        this.benefitAdministrationsList = benefitAdministrationsList;
    }
}
