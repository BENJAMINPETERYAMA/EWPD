/*
 * ProductComponentBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;

import java.util.Date;
import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractProductAdminBO {
	
	private int contractDateSegmentSysKey;
	
    private int productKey;

    private int sequence;

    private String entityType;

    private int adminKey;

    private int adminVersion;

    private String adminDesc;

    private String lastUpdatedUser;

    private String createdUser;

    private Date createdTimestamp;

    private Date lastUpdatedTimestamp;
    
    private List adminList;


    /**
     * @see java.lang.Object#toString()
     * @return
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("contractDateSegmentSysKey = ").append(this.getContractDateSegmentSysKey());
        buffer.append(", productKey = ").append(this.getProductKey());
        buffer.append(", sequence = ").append(sequence);
        buffer.append(", entityType = ").append(entityType);
        buffer.append(", adminKey = ").append(adminKey);
        buffer.append(", adminVersion = ").append(adminVersion);
        buffer.append(", adminDesc = ").append(adminDesc);
        buffer.append(", lastUpdatedUser = ").append(lastUpdatedUser);
        buffer.append(", createdUser = ").append(createdUser);
        buffer.append(", createdTimestamp = ").append(createdTimestamp);
        buffer.append(", lastUpdatedTimestamp = ").append(lastUpdatedTimestamp);
        return buffer.toString();
    }

//  --------------------------------- getters/setters -----------------------	
	/**
	 * Returns the contractDateSegmentSysKey
	 * @return int contractDateSegmentSysKey.
	 */
	public int getContractDateSegmentSysKey() {
		return contractDateSegmentSysKey;
	}
	/**
	 * Sets the contractDateSegmentSysKey
	 * @param contractDateSegmentSysKey.
	 */
	public void setContractDateSegmentSysKey(int contractDateSegmentSysKey) {
		this.contractDateSegmentSysKey = contractDateSegmentSysKey;
	}
    /**
     * @return Returns the lastUpdatedTimestamp.
     */
    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }
    /**
     * @param lastUpdatedTimestamp
     *            The lastUpdatedTimestamp to set.
     */
    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }
    /**
     * Returns the productKey
     * 
     * @return int productKey.
     */
    public int getProductKey() {
        return productKey;
    }
    /**
     * Sets the productKey
     * 
     * @param productKey.
     */
    public void setProductKey(int productKey) {
        this.productKey = productKey;
    }
    /**
     * Returns the sequence
     * 
     * @return int sequence.
     */
    public int getSequence() {
        return sequence;
    }
    /**
     * Sets the sequence
     * 
     * @param sequence.
     */
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
    /**
     * @return Returns the createdUser.
     */
    public String getCreatedUser() {
        return createdUser;
    }
    /**
     * @param createdUser
     *            The createdUser to set.
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
    /**
     * @return Returns the lastUpdatedUser.
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }
    /**
     * @param lastUpdatedUser
     *            The lastUpdatedUser to set.
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }
    /**
     * @return Returns the createdTimestamp.
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }
    /**
     * @param createdTimestamp
     *            The createdTimestamp to set.
     */
    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
    /**
     * @return Returns the adminDesc.
     */
    public String getAdminDesc() {
        return adminDesc;
    }
    /**
     * @param adminDesc
     *            The adminDesc to set.
     */
    public void setAdminDesc(String adminDesc) {
        this.adminDesc = adminDesc;
    }
    /**
     * @return Returns the adminKey.
     */
    public int getAdminKey() {
        return adminKey;
    }
    /**
     * @param adminKey
     *            The adminKey to set.
     */
    public void setAdminKey(int adminKey) {
        this.adminKey = adminKey;
    }
    /**
     * @return Returns the adminVersion.
     */
    public int getAdminVersion() {
        return adminVersion;
    }
    /**
     * @param adminVersion The adminVersion to set.
     */
    public void setAdminVersion(int adminVersion) {
        this.adminVersion = adminVersion;
    }
	/**
	 * Returns the entityType
	 * @return String entityType.
	 */
	public String getEntityType() {
		return entityType;
	}
	/**
	 * Sets the entityType
	 * @param entityType.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
    public List getAdminList() {
        return adminList;
    }
    public void setAdminList(List adminList) {
        this.adminList = adminList;
    }
}