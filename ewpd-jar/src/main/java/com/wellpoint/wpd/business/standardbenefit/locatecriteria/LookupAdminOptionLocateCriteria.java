/*
 * Created on Mar 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.standardbenefit.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;


/**
 * @author u13664
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LookupAdminOptionLocateCriteria extends LocateCriteria {

	private int benefitLevelSystemId;
	
	private int benefitAdminSystemId;
	
	
	/**
	 * @return Returns the benefitLevelSystemId.
	 */
	public int getBenefitLevelSystemId() {
		return benefitLevelSystemId;
	}
	/**
	 * @param benefitLevelSystemId The benefitLevelSystemId to set.
	 */
	public void setBenefitLevelSystemId(int benefitLevelSystemId) {
		this.benefitLevelSystemId = benefitLevelSystemId;
	}
		
	/**
	 * @return Returns the benefitAdminSystemId.
	 */
	public int getBenefitAdminSystemId() {
		return benefitAdminSystemId;
	}
	
	/**
	 * @param benefitAdminSystemId The benefitAdminSystemId to set.
	 */
	public void setBenefitAdminSystemId(int benefitAdminSystemId) {
		this.benefitAdminSystemId = benefitAdminSystemId;
	}
}
