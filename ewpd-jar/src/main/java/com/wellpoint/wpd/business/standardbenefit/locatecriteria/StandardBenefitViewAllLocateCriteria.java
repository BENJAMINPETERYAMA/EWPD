/*
 * Created on Apr 2, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.standardbenefit.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;


/**
 * @author U10109
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StandardBenefitViewAllLocateCriteria extends LocateCriteria {

	private String name;
	private int benefitkey;
	/**
	 * @return Returns the benefitkey.
	 */
	public int getBenefitkey() {
		return benefitkey;
	}
	/**
	 * @param benefitkey The benefitkey to set.
	 */
	public void setBenefitkey(int benefitkey) {
		this.benefitkey = benefitkey;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
}
