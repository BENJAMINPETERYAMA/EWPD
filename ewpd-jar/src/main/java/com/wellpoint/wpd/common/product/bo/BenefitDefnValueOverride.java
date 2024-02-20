/*
 * BenefitDefnValueOverride.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.bo;

import java.util.Date;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitDefnValueOverride {
    private int productSysId;
    private int prodStructureSysId;
    private int componentSysId;
    private String createdUser;
    private Date createdTimestamp;
    private String lastUpdatedUser;
    private Date lastUpdatedTimestamp;
    
    /**
     * Returns the componentSysId
     * @return int componentSysId.
     */
    public int getComponentSysId() {
        return componentSysId;
    }
    /**
     * Sets the componentSysId
     * @param componentSysId.
     */
    public void setComponentSysId(int componentSysId) {
        this.componentSysId = componentSysId;
    }
    /**
     * Returns the createdTimestamp
     * @return Date createdTimestamp.
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }
    /**
     * Sets the createdTimestamp
     * @param createdTimestamp.
     */
    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
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
     * Returns the lastUpdatedTimestamp
     * @return Date lastUpdatedTimestamp.
     */
    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }
    /**
     * Sets the lastUpdatedTimestamp
     * @param lastUpdatedTimestamp.
     */
    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
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
     * Returns the prodStructureSysId
     * @return int prodStructureSysId.
     */
    public int getProdStructureSysId() {
        return prodStructureSysId;
    }
    /**
     * Sets the prodStructureSysId
     * @param prodStructureSysId.
     */
    public void setProdStructureSysId(int prodStructureSysId) {
        this.prodStructureSysId = prodStructureSysId;
    }
    /**
     * Returns the productSysId
     * @return int productSysId.
     */
    public int getProductSysId() {
        return productSysId;
    }
    /**
     * Sets the productSysId
     * @param productSysId.
     */
    public void setProductSysId(int productSysId) {
        this.productSysId = productSysId;
    }
}
