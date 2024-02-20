/*
 * DomainDetail.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.domain.bo;

import java.util.Date;
import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DomainDetail {
// Valid fields    
    private String entityType;
    private String entityName;
    private int entityParentId;
    private List domainList;
    private String lastUpdatedUser;
    private Date lastUpdatedTimeStamp;
// Enhancement
    private int entityId;
// End - Enhancement    
//  Unused fields     
    private String createdUser;
    private Date createdTimeStamp;
    private int entitySystemId;
    private List lineOfBusiness;
    private List businessEntity;
    private List businessGroup;
    private List marketBusinessUnit;
    private List functionalDomainList;
    /**
     * Returns the businessEntity
     * @return List businessEntity.
     */
    public List getBusinessEntity() {
        return businessEntity;
    }
    /**
     * Sets the businessEntity
     * @param businessEntity.
     */
    public void setBusinessEntity(List businessEntity) {
        this.businessEntity = businessEntity;
    }
    /**
     * Returns the businessGroup
     * @return List businessGroup.
     */
    public List getBusinessGroup() {
        return businessGroup;
    }
    /**
     * Sets the businessGroup
     * @param businessGroup.
     */
    public void setBusinessGroup(List businessGroup) {
        this.businessGroup = businessGroup;
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
     * Returns the lineOfBusiness
     * @return List lineOfBusiness.
     */
    public List getLineOfBusiness() {
        return lineOfBusiness;
    }
    /**
     * Sets the lineOfBusiness
     * @param lineOfBusiness.
     */
    public void setLineOfBusiness(List lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
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
    /**
     * Returns the domainList
     * @return List domainList.
     */
    public List getDomainList() {
        return domainList;
    }
    /**
     * Sets the domainList
     * @param domainList.
     */
    public void setDomainList(List domainList) {
        this.domainList = domainList;
    }
    /**
     * Returns the entityParentId
     * @return int entityParentId.
     */
    public int getEntityParentId() {
        return entityParentId;
    }
    /**
     * Sets the entityParentId
     * @param entityParentId.
     */
    public void setEntityParentId(int entityParentId) {
        this.entityParentId = entityParentId;
    }
/**
 * @return Returns the entityId.
 */
public int getEntityId() {
	return entityId;
}
/**
 * @param entityId The entityId to set.
 */
public void setEntityId(int entityId) {
	this.entityId = entityId;
}
	/**
	 * @return Returns the functionalDomainList.
	 */
	public List getFunctionalDomainList() {
		return functionalDomainList;
	}
	/**
	 * @param functionalDomainList The functionalDomainList to set.
	 */
	public void setFunctionalDomainList(List functionalDomainList) {
		this.functionalDomainList = functionalDomainList;
	}
	/**
	 * @return Returns the marketBusinessUnit.
	 */
	public List getMarketBusinessUnit() {
		return marketBusinessUnit;
	}
	/**
	 * @param marketBusinessUnit The marketBusinessUnit to set.
	 */
	public void setMarketBusinessUnit(List marketBusinessUnit) {
		this.marketBusinessUnit = marketBusinessUnit;
	}
}
