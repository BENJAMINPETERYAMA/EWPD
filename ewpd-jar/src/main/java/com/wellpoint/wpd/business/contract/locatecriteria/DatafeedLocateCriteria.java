/*
 * DatafeedLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.contract.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DatafeedLocateCriteria extends LocateCriteria{
	private int contractSysId;
	private String contractId;
	private String contractDatafeedInd;
	private String status;
	private int contractType;

    
    
    /**
     * @return Returns the contractType.
     */
    public int getContractType() {
        return contractType;
    }
    /**
     * @param contractType The contractType to set.
     */
    public void setContractType(int contractType) {
        this.contractType = contractType;
    }
	/**
	 * Returns the contractDatafeedInd
	 * @return String contractDatafeedInd.
	 */
	public String getContractDatafeedInd() {
		return contractDatafeedInd;
	}
	/**
	 * Sets the contractDatafeedInd
	 * @param contractDatafeedInd.
	 */
	public void setContractDatafeedInd(String contractDatafeedInd) {
		this.contractDatafeedInd = contractDatafeedInd;
	}
	/**
	 * Returns the contractId
	 * @return String contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * Sets the contractId
	 * @param contractId.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	
  
    /**
     * @return Returns the contractSysId.
     */
    public int getContractSysId() {
        return contractSysId;
    }
    /**
     * @param contractSysId The contractSysId to set.
     */
    public void setContractSysId(int contractSysId) {
        this.contractSysId = contractSysId;
    }
    
	/**
	 * Returns the status
	 * @return String status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * Sets the status
	 * @param status.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
