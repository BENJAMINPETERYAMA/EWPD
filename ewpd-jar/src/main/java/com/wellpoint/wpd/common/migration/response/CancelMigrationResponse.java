/*
 * CancelMigrationResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.response;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CancelMigrationResponse extends MigrationContractResponse{
    private boolean success;
   
    /**
     * Returns the success
     * @return boolean success.
     */
    public boolean isSuccess() {
        return success;
    }
    /**
     * Sets the success
     * @param success.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
