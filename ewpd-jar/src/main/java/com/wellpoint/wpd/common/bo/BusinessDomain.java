/*
 * BusinessDomain.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BusinessDomain {
    
    private String lineOfBusiness;
	private String businessEntity;
	private String businessGroup;

    /**
     * @return Returns the businessEntity.
     */
    public String getBusinessEntity() {
        return businessEntity;
    }
    /**
     * @param businessEntity The businessEntity to set.
     */
    public void setBusinessEntity(String businessEntity) {
        this.businessEntity = businessEntity;
    }
    /**
     * @return Returns the businessGroup.
     */
    public String getBusinessGroup() {
        return businessGroup;
    }
    /**
     * @param businessGroup The businessGroup to set.
     */
    public void setBusinessGroup(String businessGroup) {
        this.businessGroup = businessGroup;
    }
    /**
     * @return Returns the lineOfBusiness.
     */
    public String getLineOfBusiness() {
        return lineOfBusiness;
    }
    /**
     * @param lineOfBusiness The lineOfBusiness to set.
     */
    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }
}
