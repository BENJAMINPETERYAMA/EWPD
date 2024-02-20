/*
 * Created on Mar 26, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.standardbenefit.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;


/**
 * @author U14647
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StandardBenefitDeleteLocateCriteria extends LocateCriteria{
    
    private int standardBenefitKey;
    // setting the action variable to identify if the delete is for CHECK IN 
    private String action;
    
    
    /**
     * @return Returns the standardBenefitKey.
     */
    public int getStandardBenefitKey() {
        return standardBenefitKey;
    }
    /**
     * @param standardBenefitKey The standardBenefitKey to set.
     */
    public void setStandardBenefitKey(int standardBenefitKey) {
        this.standardBenefitKey = standardBenefitKey;
    }
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
}
