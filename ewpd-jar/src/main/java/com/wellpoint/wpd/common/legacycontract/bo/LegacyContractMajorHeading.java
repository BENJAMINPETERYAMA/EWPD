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
public class LegacyContractMajorHeading {
	
	private String contractId;
	
	private String startDate;
	
	private String majorHeadingId;
	
	private String majorHeadingDesc;
	
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
	 * @return Returns the majorHeadingDesc.
	 */
	public String getMajorHeadingDesc() {
		return majorHeadingDesc;
	}
	/**
	 * @param majorHeadingDesc The majorHeadingDesc to set.
	 */
	public void setMajorHeadingDesc(String majorHeadingDesc) {
		this.majorHeadingDesc = majorHeadingDesc;
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
}