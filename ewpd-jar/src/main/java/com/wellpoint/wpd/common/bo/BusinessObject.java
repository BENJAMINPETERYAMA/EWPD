/*
 * BusinessObject.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.bo;

import java.util.Date;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: BusinessObject.java 19073 2007-04-13 05:20:29Z U14231 $
 */
public abstract class BusinessObject {
    
    public static final String BUSINESS_OBJECT_VERSION_FIELD_NAME = "version";
    
    public static final String BUSINESS_OBJECT_STATUS_FIELD_NAME = "status";

    private Object systemDomain;

    private MetaObject metaObject;

    private State state;

    private int version = -1 ;

    private String status;

    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;

    public abstract boolean equals(BusinessObject object);

    public abstract int hashCode();

    public abstract String toString();
    
    public int getVersion(){
        return version;
    }
    
    public void setVersion(int version){
        this.version = version;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    /**
     * @return Returns the createdTimestamp.
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }
    /**
     * @return Returns the createdUser.
     */
    public String getCreatedUser() {
        return createdUser;
    }
    /**
     * @return Returns the lastUpdatedTimestamp.
     */
    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }
    /**
     * @return Returns the lastUpdatedUser.
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }
    /**
     * @return Returns the metaObject.
     */
    public MetaObject getMetaObject() {
        return metaObject;
    }
    /**
     * @return Returns the state.
     */
    public State getState() {
        return state;
    }
    /**
     * @return Returns the systemDomain.
     */
    public Object getSystemDomain() {
        return systemDomain;
    }
    /**
     * @param createdTimestamp The createdTimestamp to set.
     */
    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
    /**
     * @param createdUser The createdUser to set.
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
    /**
     * @param lastUpdatedTimestamp The lastUpdatedTimestamp to set.
     */
    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }
    /**
     * @param lastUpdatedUser The lastUpdatedUser to set.
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }
    /**
     * @param metaObject The metaObject to set.
     */
    public void setMetaObject(MetaObject metaObject) {
        this.metaObject = metaObject;
    }
    /**
     * @param state The state to set.
     */
    public void setState(State state) {
        this.state = state;
    }
    /**
     * @param systemDomain The systemDomain to set.
     */
    public void setSystemDomain(Object systemDomain) {
        this.systemDomain = systemDomain;
    }
    
}