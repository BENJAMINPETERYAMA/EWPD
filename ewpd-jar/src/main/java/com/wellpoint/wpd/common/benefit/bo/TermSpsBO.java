/*
 * Created on Apr 6, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.benefit.bo;

/**
 * @author U18739
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TermSpsBO {

	private String spsName; // Corresponding SPS to the Term
	
	private String termDesc;  // Coma seperated in the case of aggregate
	
	
	
	
	/**
	 * @return Returns the spsName.
	 */
	public String getSpsName() {
		return spsName;
	}
	/**
	 * @param spsName The spsName to set.
	 */
	public void setSpsName(String spsName) {
		this.spsName = spsName;
	}
	/**
	 * @return Returns the termDesc.
	 */
	public String getTermDesc() {
		return termDesc;
	}
	/**
	 * @param termDesc The termDesc to set.
	 */
	public void setTermDesc(String termDesc) {
		this.termDesc = termDesc;
	}
}
