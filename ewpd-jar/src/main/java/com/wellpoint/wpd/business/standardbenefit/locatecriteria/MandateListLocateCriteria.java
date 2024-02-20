/*
 * MandateListLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.standardbenefit.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MandateListLocateCriteria extends LocateCriteria{
	
	private String effDate;
	private String expDate;
	private int mandateId;
	
    
	/**
	 * @return Returns the effDate.
	 */
	public String getEffDate() {
		return effDate;
	}
	/**
	 * @param effDate The effDate to set.
	 */
	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}
	/**
	 * @return Returns the expDate.
	 */
	public String getExpDate() {
		return expDate;
	}
	/**
	 * @param expDate The expDate to set.
	 */
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	/**
	 * @return Returns the mandateId.
	 */
	public int getMandateId() {
		return mandateId;
	}
	/**
	 * @param mandateId The mandateId to set.
	 */
	public void setMandateId(int mandateId) {
		this.mandateId = mandateId;
	}
}
