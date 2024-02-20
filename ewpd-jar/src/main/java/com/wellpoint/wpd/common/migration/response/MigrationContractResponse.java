/*
 * Created on Aug 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.migration.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.migration.vo.MigrationContractSession;

/**
 * @author U11085
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class MigrationContractResponse extends WPDResponse {

    private boolean valid;

    private boolean success;

    private java.util.List validateRulesList;

    private MigrationContractSession migrationContractSession = new MigrationContractSession();


    /**
     * Returns the success
     * 
     * @return boolean success.
     */
    public boolean isSuccess() {
        return success;
    }


    /**
     * Sets the success
     * 
     * @param success.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }


    /**
     * Returns the valid
     * 
     * @return boolean valid.
     */
    public boolean isValid() {
        return valid;
    }


    /**
     * Sets the valid
     * 
     * @param valid.
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }


    /**
     * Returns the validateRulesList
     * 
     * @return java.util.List validateRulesList.
     */
    public java.util.List getValidateRulesList() {
        return validateRulesList;
    }


    /**
     * Sets the validateRulesList
     * 
     * @param validateRulesList.
     */
    public void setValidateRulesList(java.util.List validateRulesList) {
        this.validateRulesList = validateRulesList;
    }


    /**
     * @return Returns the migrationContractSession.
     */
    public MigrationContractSession getMigrationContractSession() {
        return migrationContractSession;
    }


    /**
     * @param migrationContractSession
     *            The migrationContractSession to set.
     */
    public void setMigrationContractSession(
            MigrationContractSession migrationContractSession) {
        this.migrationContractSession = migrationContractSession;
    }
}