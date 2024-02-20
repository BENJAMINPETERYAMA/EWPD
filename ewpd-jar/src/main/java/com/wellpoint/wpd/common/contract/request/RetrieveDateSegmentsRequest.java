/*
 * RetrieveDateSegmentsRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;



import java.util.Date;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * Request class for retrieving Date Segment.
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveDateSegmentsRequest extends WPDRequest {

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

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
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
	
	//Added as part of SSCR 21516 to fetch older versions of as Date Segment
	
	public static final int FETCH_OLDER_VERSIONS = 010;
	
	private int action;

	 private Date effectiveDate;
	    
	// private Date expiryDate;
	    
	 private int version;

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
/*
	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}*/

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}
	
}