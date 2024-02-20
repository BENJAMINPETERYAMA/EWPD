/*
 * Created on Mar 31, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.benefitlevel.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;


/**
 * @author u12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitPopUpLocateCriteria extends  LocateCriteria{
	
	private int standardBenefitId;
	
	private int popUpId;
	/**
	 * @return Returns the popUpId.
	 */
	public int getPopUpId() {
		return popUpId;
	}
	/**
	 * @param popUpId The popUpId to set.
	 */
	public void setPopUpId(int popUpId) {
		this.popUpId = popUpId;
	}
	/**
	 * @return Returns the standardBenefitId.
	 */
	public int getStandardBenefitId() {
		return standardBenefitId;
	}
	/**
	 * @param standardBenefitId The standardBenefitId to set.
	 */
	public void setStandardBenefitId(int standardBenefitId) {
		this.standardBenefitId = standardBenefitId;
	}
}
