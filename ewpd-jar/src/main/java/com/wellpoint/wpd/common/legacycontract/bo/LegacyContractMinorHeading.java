/*
 * Created on Apr 3, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.legacycontract.bo;


/**
 * @author U13083
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LegacyContractMinorHeading {
	
	private String contractId;
	
	private String startDate;
	
	private String minorHeadingId;
	
	private String minorHeadingDesc;
	
	private String majorHeadingId;
	
	
	/**
	 * @return Returns the contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * @param contractId The contractId to set.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * @return Returns the minorHeadingDesc.
	 */
	public String getMinorHeadingDesc() {
		return minorHeadingDesc;
	}
	/**
	 * @param minorHeadingDesc The minorHeadingDesc to set.
	 */
	public void setMinorHeadingDesc(String minorHeadingDesc) {
		this.minorHeadingDesc = minorHeadingDesc;
	}
	
	/**
	 * @return Returns the minorHeadingId.
	 */
	public String getMinorHeadingId() {
		return minorHeadingId;
	}
	/**
	 * @param minorHeadingId The minorHeadingId to set.
	 */
	public void setMinorHeadingId(String minorHeadingId) {
		this.minorHeadingId = minorHeadingId;
	}
	/**
	 * @return Returns the startDate.
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * @return Returns the majorHeadingId.
	 */
	public String getMajorHeadingId() {
		return majorHeadingId;
	}
	/**
	 * @param majorHeadingId The majorHeadingId to set.
	 */
	public void setMajorHeadingId(String majorHeadingId) {
		this.majorHeadingId = majorHeadingId;
	}
}
