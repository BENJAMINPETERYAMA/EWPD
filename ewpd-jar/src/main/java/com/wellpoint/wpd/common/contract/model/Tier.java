/*
 * Created on Aug 26, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.model;

import java.util.List;

/**
 * @author u20656
 * Objects of this class will hold a Criteria (for eg: Start Age =0 End Age =20), all Lines in it and all questions in it
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Tier {
	
	private int id;
	private List tierCriteriaValues;
	private List benefitLines;
	private List adminOptions;

	/**
	 * @return Returns the adminOptions.
	 */
	public List getAdminOptions() {
		return adminOptions;
	}
	/**
	 * @param adminOptions The adminOptions to set.
	 */
	public void setAdminOptions(List adminOptions) {
		this.adminOptions = adminOptions;
	}
	/**
	 * @return Returns the benefitLines.
	 */
	public List getBenefitLines() {
		return benefitLines;
	}
	/**
	 * @param benefitLines The benefitLines to set.
	 */
	public void setBenefitLines(List benefitLines) {
		this.benefitLines = benefitLines;
	}
	/**
	 * @return Returns the tierCriteriaValues.
	 */
	public List getTierCriteriaValues() {
		return tierCriteriaValues;
	}
	/**
	 * @param tierCriteriaValues The tierCriteriaValues to set.
	 */
	public void setTierCriteriaValues(List tierCriteriaValues) {
		this.tierCriteriaValues = tierCriteriaValues;
	}
	
	
	/**
	 * @return Returns the id.
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(int id) {
		this.id = id;
	}
}
