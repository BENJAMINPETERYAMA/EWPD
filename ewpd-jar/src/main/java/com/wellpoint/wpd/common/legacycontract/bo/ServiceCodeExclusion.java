/*
 * ServiceCodeExclusion.java
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
public class ServiceCodeExclusion extends LegacyObject {
	String contractId;
	Date startDate;
	String serviceCode;
	
	public ServiceCodeExclusion(){
		
	}
	
	public ServiceCodeExclusion(String system) {
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
	 * Returns the serviceCode.
	 * @return String serviceCode.
	 */
	public String getServiceCode() {
		return serviceCode;
	}
	/**
	 * Sets the serviceCode.
	 * @param serviceCode.
	 */
	
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
}
