/*
 * EntityDomainInfo.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.domain.bo;

import java.util.Date;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class EntityDomainInfo {
    private int domainId;
    private String entityName;
    private String entityType;
    private int entitySystemId;
    private String createdUser;
    private Date createdTimeStamp;
    private String lastUpdatedUser;
    private Date lastUpdatedTimeStamp;

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
     * Returns the domainId
     * @return int domainId.
     */
    public int getDomainId() {
        return domainId;
    }
    
    /**
     * Sets the domainId
     * @param domainId.
     */
    public void setDomainId(int domainId) {
        this.domainId = domainId;
    }
    
    /**
     * Returns the entityName
     * @return String entityName.
     */
    public String getEntityName() {
        return entityName;
    }
    
    /**
     * Sets the entityName
     * @param entityName.
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
    
    /**
     * Returns the entitySystemId
     * @return int entitySystemId.
     */
    public int getEntitySystemId() {
        return entitySystemId;
    }
    
    /**
     * Sets the entitySystemId
     * @param entitySystemId.
     */
    public void setEntitySystemId(int entitySystemId) {
        this.entitySystemId = entitySystemId;
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
