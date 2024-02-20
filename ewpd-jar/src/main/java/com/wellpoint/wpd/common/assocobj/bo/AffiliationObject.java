/*
 * AffiliationObject.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.assocobj.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AffiliationObject {
    int parentId;
    int catlogId;
    String code;
    String description;
    
    /**
     * Returns the code
     * @return String code.
     */
    public String getCode() {
        return code;
    }
    /**
     * Sets the code
     * @param code.
     */
    public void setCode(String code) {
        this.code = code;
    }
    /**
     * Returns the description
     * @return String description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description
     * @param description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Returns the parentId
     * @return int parentId.
     */
    public int getParentId() {
        return parentId;
    }
    /**
     * Sets the parentId
     * @param parentId.
     */
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
    /**
     * Returns the catlogId
     * @return int catlogId.
     */
    public int getCatlogId() {
        return catlogId;
    }
    /**
     * Sets the catlogId
     * @param catlogId.
     */
    public void setCatlogId(int catlogId) {
        this.catlogId = catlogId;
    }
}
