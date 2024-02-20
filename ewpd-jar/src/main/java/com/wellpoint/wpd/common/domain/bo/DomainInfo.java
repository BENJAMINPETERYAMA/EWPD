/*
 * DomainInfo.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.domain.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DomainInfo {
    private int domainId;
    private String lineOfBusinesId;
    private String businessEntityId;
    private String businessGroupId;
    private String marketBusinessUnit;
    
    /**
     * Returns the businessEntityId
     * @return String businessEntityId.
     */
    public String getBusinessEntityId() {
        return businessEntityId;
    }
    
    /**
     * Sets the businessEntityId
     * @param businessEntityId.
     */
    public void setBusinessEntityId(String businessEntityId) {
        this.businessEntityId = businessEntityId;
    }
    
    /**
     * Returns the businessGroupId
     * @return String businessGroupId.
     */
    public String getBusinessGroupId() {
        return businessGroupId;
    }
    
    /**
     * Sets the businessGroupId
     * @param businessGroupId.
     */
    public void setBusinessGroupId(String businessGroupId) {
        this.businessGroupId = businessGroupId;
    }
    
    /**
     * Returns the domainId
     * @return int domainId.
     */
    public int getDomainId() {
        return domainId;
    }
    
    /**
     * Sets the domainId
     * @param domainId.
     */
    public void setDomainId(int domainId) {
        this.domainId = domainId;
    }
    
    /**
     * Returns the lineOfBusinesId
     * @return String lineOfBusinesId.
     */
    public String getLineOfBusinesId() {
        return lineOfBusinesId;
    }
    
    /**
     * Sets the lineOfBusinesId
     * @param lineOfBusinesId.
     */
    public void setLineOfBusinesId(String lineOfBusinesId) {
        this.lineOfBusinesId = lineOfBusinesId;
    }
	/**
	 * @return Returns the marketBusinessUnit.
	 */
	public String getMarketBusinessUnit() {
		return marketBusinessUnit;
	}
	/**
	 * @param marketBusinessUnit The marketBusinessUnit to set.
	 */
	public void setMarketBusinessUnit(String marketBusinessUnit) {
		this.marketBusinessUnit = marketBusinessUnit;
	}
}
