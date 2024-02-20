/*
 * LimitClassExclusion.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.legacycontract.bo;

import java.util.Date;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public class LimitClassExclusion extends LegacyObject {
	private String contractId;
	private Date startDate;
	private String limitClass;
	
	public LimitClassExclusion(){
		
	}
	
	public LimitClassExclusion(String system) {
		super(system);
	}
	/**
	 * Returns the contractId.
	 * @return String contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * Sets the contractId.
	 * @param contractId.
	 */
	
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * Returns the startDate.
	 * @return Date startDate.
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * Sets the startDate.
	 * @param startDate.
	 */
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * Returns the limitClass.
	 * @return String limitClass.
	 */
	public String getLimitClass() {
		return limitClass;
	}
	/**
	 * Sets the limitClass.
	 * @param limitClass.
	 */
	
	public void setLimitClass(String limitClass) {
		this.limitClass = limitClass;
	}
}
