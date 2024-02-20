/*
 * Created on Apr 2, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.benefitlevel.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * @author u13547
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenfitLevelLineDeleteLocateCriteria extends LocateCriteria {
	
	private String benefitLevels = null;
	private String benefitLines = null;
	private int benefitDefnId = 0;

	/**
	 * @return Returns the benefitDefnId.
	 */
	public int getBenefitDefnId() {
		return benefitDefnId;
	}
	/**
	 * @param benefitDefnId The benefitDefnId to set.
	 */
	public void setBenefitDefnId(int benefitDefnId) {
		this.benefitDefnId = benefitDefnId;
	}
	/**
	 * @return Returns the benefitLevels.
	 */
	public String getBenefitLevels() {
		return benefitLevels;
	}
	/**
	 * @param benefitLevels The benefitLevels to set.
	 */
	public void setBenefitLevels(String benefitLevels) {
		this.benefitLevels = benefitLevels;
	}
	/**
	 * @return Returns the benefitLines.
	 */
	public String getBenefitLines() {
		return benefitLines;
	}
	/**
	 * @param benefitLines The benefitLines to set.
	 */
	public void setBenefitLines(String benefitLines) {
		this.benefitLines = benefitLines;
	}
}
