/*
 * Created on Sep 13, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class GeneralBenefitAdminMethodValidationResponse extends WPDResponse{
    
    private List spsList;
    private boolean isValidationSuccess = true;
       

    /**
     * @return isValidationSuccess
     * 
     * Returns the isValidationSuccess.
     */
    public boolean isValidationSuccess() {
        return isValidationSuccess;
    }
    /**
     * @param isValidationSuccess
     * 
     * Sets the isValidationSuccess.
     */
    public void setValidationSuccess(boolean isValidationSuccess) {
        this.isValidationSuccess = isValidationSuccess;
    }
    /**
     * Returns the spsList
     * @return List spsList.
     */

    public List getSpsList() {
        return spsList;
    }
    /**
     * Sets the spsList
     * @param spsList.
     */

    public void setSpsList(List spsList) {
        this.spsList = spsList;
    }
}
