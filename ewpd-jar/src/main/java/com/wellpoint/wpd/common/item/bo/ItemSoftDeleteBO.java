/*
 * Created on Jul 23, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.item.bo;

import java.util.Date;

public class ItemSoftDeleteBO {
    
    private String primaryCode;
    
    private int catalogId;
    
    private String status;
    
    private String createdUser;
    
    private Date createdTimestamp;
    
    private String lastUpdatedUser;
    
    private Date lastUpdatedTimestamp;

    /**
     * @return Returns the catalogId.
     * @return int catalogId
     */
    public int getCatalogId() {
        return catalogId;
    }
    /**
     * Sets the catalogId
     * @param catalogId
     */
    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }
    /**
     * @return Returns the primaryCode.
     * @return String primaryCode
     */
    public String getPrimaryCode() {
        return primaryCode;
    }
    /**
     * Sets the primaryCode
     * @param primaryCode
     */
    public void setPrimaryCode(String primaryCode) {
        this.primaryCode = primaryCode;
    }
    /**
     * @return Returns the status.
     * @return String status
     */
    public String getStatus() {
        return status;
    }
    /**
     * Sets the status
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    
    /**
     * @return Returns the createdTimestamp.
     * @return Date createdTimestamp
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }
    /**
     * Sets the createdTimestamp
     * @param createdTimestamp
     */
    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
    /**
     * @return Returns the createdUser.
     * @return String createdUser
     */
    public String getCreatedUser() {
        return createdUser;
    }
    /**
     * Sets the createdUser
     * @param createdUser
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
    /**
     * @return Returns the lastUpdatedTimestamp.
     * @return Date lastUpdatedTimestamp
     */
    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }
    /**
     * Sets the lastUpdatedTimestamp
     * @param lastUpdatedTimestamp
     */
    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }
    /**
     * @return Returns the lastUpdatedUser.
     * @return String lastUpdatedUser
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }
    /**
     * Sets the lastUpdatedUser
     * @param lastUpdatedUser
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }
}
