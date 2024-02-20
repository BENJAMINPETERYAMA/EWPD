/*
 * DateSegmentUpdateAfterDeleteBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DateSegmentUpdateAfterDeleteBO {

	private int dateSegmentSysId;
	
	private java.util.Date effectiveDate;
	
	private java.util.Date expiryDate;
	
	private String lastUpdatedUser;
	
	private java.util.Date lastUpdatedTimeStamp;

	
	/**
	 * Returns the dateSegmentSysId
	 * @return int dateSegmentSysId.
	 */
	public int getDateSegmentSysId() {
		return dateSegmentSysId;
	}
	/**
	 * Sets the dateSegmentSysId
	 * @param dateSegmentSysId.
	 */
	public void setDateSegmentSysId(int dateSegmentSysId) {
		this.dateSegmentSysId = dateSegmentSysId;
	}
	/**
	 * Returns the effectiveDate
	 * @return java.util.Date effectiveDate.
	 */
	public java.util.Date getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * Sets the effectiveDate
	 * @param effectiveDate.
	 */
	public void setEffectiveDate(java.util.Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	/**
	 * Returns the expiryDate
	 * @return java.util.Date expiryDate.
	 */
	public java.util.Date getExpiryDate() {
		return expiryDate;
	}
	/**
	 * Sets the expiryDate
	 * @param expiryDate.
	 */
	public void setExpiryDate(java.util.Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * Returns the lastUpdatedTimeStamp
	 * @return java.util.Date lastUpdatedTimeStamp.
	 */
	public java.util.Date getLastUpdatedTimeStamp() {
		return lastUpdatedTimeStamp;
	}
	/**
	 * Sets the lastUpdatedTimeStamp
	 * @param lastUpdatedTimeStamp.
	 */
	public void setLastUpdatedTimeStamp(java.util.Date lastUpdatedTimeStamp) {
		this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
	}
	/**
	 * Returns the lastUpdatedUser
	 * @return String lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * Sets the lastUpdatedUser
	 * @param lastUpdatedUser.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
}
