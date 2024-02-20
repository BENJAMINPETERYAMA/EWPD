/*
 * DateSegmentSearchCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.contract.locatecriteria;

import java.util.Date;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * DateSegmentSearchCriteria business class
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DateSegmentLocateCriteria extends LocateCriteria{

    private String contractId;
    
    private int contractSysId;
    
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
     * Overriding toString method
     * @return String.
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("contractId = ").append(contractId).append(" ");
        return buffer.toString();
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
	
	//Added to fetch older versions of a Date Segment-- SSCR 21516
	 private boolean isFetchOlderVersion;
	    
	 private Date effectiveDate;
	    
	// private Date expiryDate;
	    
	 private int version;

	public boolean isFetchOlderVersion() {
		return isFetchOlderVersion;
	}

	public void setFetchOlderVersion(boolean isFetchOlderVersion) {
		this.isFetchOlderVersion = isFetchOlderVersion;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

/*	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
*/
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}