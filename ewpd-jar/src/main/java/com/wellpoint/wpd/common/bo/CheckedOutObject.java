/*
 * CheckedOutObject.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.bo;

import java.util.Date;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: CheckedOutObject.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class CheckedOutObject {

	private String name;

	private Date checkoutDate;

	private int duration;
	
	private String key;
	
	private String userId;
	
	private Date expiryDate;
	
	/**
	 * @return Returns the checkoutDate.
	 */
	public Date getCheckoutDate() {
		return checkoutDate;
	}
	/**
	 * @param checkoutDate The checkoutDate to set.
	 */
	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	/**
	 * @return Returns the duration.
	 */
	public int getDuration() {
		return duration;
	}
	/**
	 * @param duration The duration to set.
	 */
	public void setDuration(int duration) {
		this.duration = duration;
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
	/**
	 * @return Returns the key.
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key The key to set.
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId The userId to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return Returns the expiryDate.
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate The expiryDate to set.
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
}