/*
 * Created on May 17, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.bo;

import java.util.Date;

/**
 * @author U12238
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AttachedAdminBO{

	/**
	 * @return Returns the adminId.
	 */
	public String getAdminId() {
		return adminId;
	}
	/**
	 * @param adminId The adminId to set.
	 */
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	/**
	 * @param adminName The adminName to set.
	 */
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
    private String adminId;

    private String adminName;

    private int entityId;

    private String entityType;
    
    private String createdUser;
	private String lastUpdatedUser;
	private Date createdTimestamp;
	private Date lastUpdatedTimestamp; 

    /**
     * @return entityId
     * 
     * Returns the entityId.
     */
    public int getEntityId() {
        return entityId;
    }


    /**
     * @param entityId
     * 
     * Sets the entityId.
     */
    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }


    /**
     * @return entityType
     * 
     * Returns the entityType.
     */
    public String getEntityType() {
        return entityType;
    }


    /**
     * @param entityType
     * 
     * Sets the entityType.
     */
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
   

    /**
     * @returnadminName
     * 
     * Returns theadminName.
     */
    public String getAdminName() {
        return adminName;
    }


    /**
     * @paramadminName
     * 
     * Sets theadminName.
     */
    public void setNoteName(String adminName) {
        this.adminName =adminName;
    }

    /**
     * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
     * @return
     */
    public int hashCode() {
        // TODO Auto-generated method stub
        return 0;
    }


    /**
     * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
     * @return
     */
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }
    /**
     * @return createdTimestamp
     * 
     * Returns the createdTimestamp.
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }
    /**
     * @param createdTimestamp
     * 
     * Sets the createdTimestamp.
     */
    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
    /**
     * @return createdUser
     * 
     * Returns the createdUser.
     */
    public String getCreatedUser() {
        return createdUser;
    }
    /**
     * @param createdUser
     * 
     * Sets the createdUser.
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
    /**
     * @return lastUpdatedTimestamp
     * 
     * Returns the lastUpdatedTimestamp.
     */
    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }
    /**
     * @param lastUpdatedTimestamp
     * 
     * Sets the lastUpdatedTimestamp.
     */
    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }
    /**
     * @return lastUpdatedUser
     * 
     * Returns the lastUpdatedUser.
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }
    /**
     * @param lastUpdatedUser
     * 
     * Sets the lastUpdatedUser.
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }
}