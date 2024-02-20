/*
 * ContractIdInfo.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;

import java.util.Date;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SystemContractId {
	
    private String contractId;
    
    private int contractSequence;
    
    private String createdUser;
    
    private Date createdTimeStamp;
    
    private String lastUpdatedUser;
    
    private Date lastUpdatedTimeStamp; 
    

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
     * Returns the contractSequence
     * @return int contractSequence.
     */
    public int getContractSequence() {
        return contractSequence;
    }
    /**
     * Sets the contractSequence
     * @param contractSequence.
     */
    public void setContractSequence(int contractSequence) {
        this.contractSequence = contractSequence;
    }

	/**
	 * Returns the createdTimeStamp
	 * @return Date createdTimeStamp.
	 */
	public Date getCreatedTimeStamp() {
		return createdTimeStamp;
	}
	/**
	 * Sets the createdTimeStamp
	 * @param createdTimeStamp.
	 */
	public void setCreatedTimeStamp(Date createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}
	/**
	 * Returns the createdUser
	 * @return String createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * Sets the createdUser
	 * @param createdUser.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * Returns the lastUpdatedTimeStamp
	 * @return Date lastUpdatedTimeStamp.
	 */
	public Date getLastUpdatedTimeStamp() {
		return lastUpdatedTimeStamp;
	}
	/**
	 * Sets the lastUpdatedTimeStamp
	 * @param lastUpdatedTimeStamp.
	 */
	public void setLastUpdatedTimeStamp(Date lastUpdatedTimeStamp) {
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
