/*
 * ContractLock.java
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
public class ContractLock extends LegacyObject {
	private String contractId;
	private String lockedUser;
	private Date lockedTime;
    private String createdUser;
    private Date createdTimestamp;
    private String lastUpdatedUser;
    private Date lastUpdatedTimestamp;
    
    public ContractLock(String system) {
    	super(system);
    }
    
    public ContractLock() {
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
	 * Returns the lockedUser.
	 * @return String lockedUser.
	 */
	public String getLockedUser() {
		return lockedUser;
	}
	/**
	 * Sets the lockedUser.
	 * @param lockedUser.
	 */
	
	public void setLockedUser(String lockedUser) {
		this.lockedUser = lockedUser;
	}
	/**
	 * Returns the lockedTime.
	 * @return Date lockedTime.
	 */
	public Date getLockedTime() {
		return lockedTime;
	}
	/**
	 * Sets the lockedTime.
	 * @param lockedTime.
	 */
	
	public void setLockedTime(Date lockedTime) {
		this.lockedTime = lockedTime;
	}
	/**
	 * Returns the createdUser.
	 * @return String createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * Sets the createdUser.
	 * @param createdUser.
	 */
	
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * Returns the createdTimestamp.
	 * @return Date createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * Sets the createdTimestamp.
	 * @param createdTimestamp.
	 */
	
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	/**
	 * Returns the lastUpdatedUser.
	 * @return String lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * Sets the lastUpdatedUser.
	 * @param lastUpdatedUser.
	 */
	
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * Returns the lastUpdatedTimestamp.
	 * @return Date lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * Sets the lastUpdatedTimestamp.
	 * @param lastUpdatedTimestamp.
	 */
	
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
}
