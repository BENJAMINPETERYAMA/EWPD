/*
 * RetrieveMigGeneralInfoResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.response;

import java.util.List;

import com.wellpoint.wpd.common.migration.bo.MigrationDateSegment;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveMigGeneralInfoResponse extends MigrationContractResponse{
    
    private MigrationDateSegment migDateSegment;
    private List migrationDomainInfoList;
   

   
    /**
     * Returns the migrationDomainInfoList
     * @return List migrationDomainInfoList.
     */
    public List getMigrationDomainInfoList() {
        return migrationDomainInfoList;
    }
    /**
     * Sets the migrationDomainInfoList
     * @param migrationDomainInfoList.
     */
    public void setMigrationDomainInfoList(List migrationDomainInfoList) {
        this.migrationDomainInfoList = migrationDomainInfoList;
    }
    
    /**
     * Returns the migDateSegment
     * @return MigrationDateSegment migDateSegment.
     */
    public MigrationDateSegment getMigDateSegment() {
        return migDateSegment;
    }
    /**
     * Sets the migDateSegment
     * @param migDateSegment.
     */
    public void setMigDateSegment(MigrationDateSegment migDateSegment) {
        this.migDateSegment = migDateSegment;
    }
}
