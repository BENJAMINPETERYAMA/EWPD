/*
 * Created on Aug 26, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.model;

/**
 * @author u20656
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TierCriteriaValue {

	private String criteriaName;
	private int displaySequenceNumber;
	private String value;
	
	/**
	 * @return Returns the criteriaName.
	 */
	public String getCriteriaName() {
		return criteriaName;
	}
	/**
	 * @param criteriaName The criteriaName to set.
	 */
	public void setCriteriaName(String criteriaName) {
		this.criteriaName = criteriaName;
	}
	/**
	 * @return Returns the displaySequenceNumber.
	 */
	public int getDisplaySequenceNumber() {
		return displaySequenceNumber;
	}
	/**
	 * @param displaySequenceNumber The displaySequenceNumber to set.
	 */
	public void setDisplaySequenceNumber(int displaySequenceNumber) {
		this.displaySequenceNumber = displaySequenceNumber;
	}
	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
