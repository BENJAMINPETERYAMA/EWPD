/*
 * SaveMigGeneralInfoResponse.java
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
public class SaveMigGeneralInfoResponse extends MigrationContractResponse{
    
    private MigrationDateSegment migDateSegment;
    private List productIdList;
    private boolean BYCYConflict = false;
    

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

    /**
     * @return productIdList
     * 
     * Returns the productIdList.
     */
    public List getProductIdList() {
        return productIdList;
    }
    /**
     * @param productIdList
     * 
     * Sets the productIdList.
     */
    public void setProductIdList(List productIdList) {
        this.productIdList = productIdList;
    }
	/**
	 * @return Returns the bYCYConflict.
	 */
	public boolean isBYCYConflict() {
		return BYCYConflict;
	}
	/**
	 * @param conflict The bYCYConflict to set.
	 */
	public void setBYCYConflict(boolean conflict) {
		BYCYConflict = conflict;
	}
}
