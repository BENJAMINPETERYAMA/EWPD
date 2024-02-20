/*
 * MigrationVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.legacycontract.vo;



/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LegacyContractVO {
    
    private String contractId;

    private String sourceSystem;

    private String option;
    
    /**
     * @return contractId
     * 
     * Returns the contractId.
     */
    public String getContractId() {
        return contractId;
    }
    /**
     * @param contractId
     * 
     * Sets the contractId.
     */
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    /**
     * @return option
     * 
     * Returns the option.
     */
    public String getOption() {
        return option;
    }
    /**
     * @param option
     * 
     * Sets the option.
     */
    public void setOption(String option) {
        this.option = option;
    }
    /**
     * @return system
     * 
     * Returns the system.
     */
    public String getSourceSystem() {
        return sourceSystem;
    }
    /**
     * @param sourceSystem
     * 
     * Sets the system.
     */
    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }
}
