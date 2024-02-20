/*
 * ServiceClassExclusion.java
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
public class ServiceClassExclusion extends LegacyObject {
	String contractId;
	Date startDate;
	String srvClassFrom;
	String srClassThrough;
	
	public ServiceClassExclusion(){
		
	}
	public ServiceClassExclusion(String system) {
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
	 * Returns the srvClassFrom.
	 * @return String srvClassFrom.
	 */
	public String getSrvClassFrom() {
		return srvClassFrom;
	}
	/**
	 * Sets the srvClassFrom.
	 * @param srvClassFrom.
	 */
	
	public void setSrvClassFrom(String srvClassFrom) {
		this.srvClassFrom = srvClassFrom;
	}
	/**
	 * Returns the srClassThrough.
	 * @return String srClassThrough.
	 */
	public String getSrClassThrough() {
		return srClassThrough;
	}
	/**
	 * Sets the srClassThrough.
	 * @param srClassThrough.
	 */
	
	public void setSrClassThrough(String srClassThrough) {
		this.srClassThrough = srClassThrough;
	}
	
}
