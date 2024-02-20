/*
 * MigrationDomainInfo.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.bo;

import java.util.Date;

/**
 * Class to Persist and Retrieve Migration Contract Domain information. 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public class MigrationDomainInfo implements Comparable{
	private int contractDateSegmentSysId;
	
	private int contractSysId;
	
	private String lobId;
	private String businessEntityId;	
	private String businessGroupId;
    /*START CARS*/
	private String marketBusinessUnitId;
    /*END CARS*/
	
	private String lobDesc;
	private String businessEntityDesc;	
	private String businessGroupDesc;
    /*START CARS*/
	private String marketBusinessUnitDesc;
    /*END CARS*/

	private String lastUpdatedUser;
	private Date lastUpdatedTimestamp;
	private String createdUser;
	private Date createdTimestamp;
	
	
	
    /**
     * Returns the contractSysId
     * @return int contractSysId.
     */
    public int getContractSysId() {
        return contractSysId;
    }
    /**
     * Sets the contractSysId
     * @param contractSysId.
     */
    public void setContractSysId(int contractSysId) {
        this.contractSysId = contractSysId;
    }
    
    /**
	 * @return Returns the businessEntityDesc.
	 */
	public String getBusinessEntityDesc() {
		return businessEntityDesc;
	}
	/**
	 * @param businessEntityDesc The businessEntityDesc to set.
	 */
	public void setBusinessEntityDesc(String businessEntityDesc) {
		this.businessEntityDesc = businessEntityDesc;
	}
	/**
	 * @return Returns the businessEntityId.
	 */
	public String getBusinessEntityId() {
		return businessEntityId;
	}
	/**
	 * @param businessEntityId The businessEntityId to set.
	 */
	public void setBusinessEntityId(String businessEntityId) {
		this.businessEntityId = businessEntityId;
	}
	/**
	 * @return Returns the businessGroupDesc.
	 */
	public String getBusinessGroupDesc() {
		return businessGroupDesc;
	}
	/**
	 * @param businessGroupDesc The businessGroupDesc to set.
	 */
	public void setBusinessGroupDesc(String businessGroupDesc) {
		this.businessGroupDesc = businessGroupDesc;
	}
	/**
	 * @return Returns the businessGroupId.
	 */
	public String getBusinessGroupId() {
		return businessGroupId;
	}
	/**
	 * @param businessGroupId The businessGroupId to set.
	 */
	public void setBusinessGroupId(String businessGroupId) {
		this.businessGroupId = businessGroupId;
	}
	/**
	 * @return Returns the contractDateSegmentSysId.
	 */
	public int getContractDateSegmentSysId() {
		return contractDateSegmentSysId;
	}
	/**
	 * @param contractDateSegmentSysId The contractDateSegmentSysId to set.
	 */
	public void setContractDateSegmentSysId(int contractDateSegmentSysId) {
		this.contractDateSegmentSysId = contractDateSegmentSysId;
	}
	/**
	 * @return Returns the createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * @param createdTimestamp The createdTimestamp to set.
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	/**
	 * @return Returns the createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * @param createdUser The createdUser to set.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * @return Returns the lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * @param lastUpdatedTimestamp The lastUpdatedTimestamp to set.
	 */
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
	/**
	 * @return Returns the lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * @param lastUpdatedUser The lastUpdatedUser to set.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * @return Returns the lobDesc.
	 */
	public String getLobDesc() {
		return lobDesc;
	}
	/**
	 * @param lobDesc The lobDesc to set.
	 */
	public void setLobDesc(String lobDesc) {
		this.lobDesc = lobDesc;
	}
	/**
	 * @return Returns the lobId.
	 */
	public String getLobId() {
		return lobId;
	}
	/**
	 * @param lobId The lobId to set.
	 */
	public void setLobId(String lobId) {
		this.lobId = lobId;
	}
    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     * @param o
     * @return
     */
    public int compareTo(Object o) {
        return 0;
    }
	/**
	 * @return Returns the marketBusinessUnitId.
	 */
	public String getMarketBusinessUnitId() {
		return marketBusinessUnitId;
	}
	/**
	 * @param marketBusinessUnitId The marketBusinessUnitId to set.
	 */
	public void setMarketBusinessUnitId(String marketBusinessUnitId) {
		this.marketBusinessUnitId = marketBusinessUnitId;
	}
	/**
	 * @return Returns the marketBusinessUnitDesc.
	 */
	public String getMarketBusinessUnitDesc() {
		return marketBusinessUnitDesc;
	}
	/**
	 * @param marketBusinessUnitDesc The marketBusinessUnitDesc to set.
	 */
	public void setMarketBusinessUnitDesc(String marketBusinessUnitDesc) {
		this.marketBusinessUnitDesc = marketBusinessUnitDesc;
	}
}
