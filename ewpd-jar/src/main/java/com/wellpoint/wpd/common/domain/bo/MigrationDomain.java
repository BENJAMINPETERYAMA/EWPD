/*
 * MigrationDomain.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.domain.bo;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MigrationDomain {

    private int migContractSystemId;
    private List domainList;
    private List lineOfBusiness;
    private List businessEntity;
    private List businessGroup;
    
    
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
     * Returns the migContractSystemId
     * @return int migContractSystemId.
     */
    public int getMigContractSystemId() {
        return migContractSystemId;
    }
    /**
     * Sets the migContractSystemId
     * @param migContractSystemId.
     */
    public void setMigContractSystemId(int migContractSystemId) {
        this.migContractSystemId = migContractSystemId;
    }
}
