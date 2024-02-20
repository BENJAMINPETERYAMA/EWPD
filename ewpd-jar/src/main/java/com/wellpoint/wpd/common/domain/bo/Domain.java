/*
 * Domain.java
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
public class Domain implements Comparable{
    private int domainSysId;
    private String lineOfBusiness;
    private String businessEntity;
    private String businessGroup;
    private String marketBusinessUnit;
    private String lineOfBusinessDesc;
    private String businessEntityDesc;
    private String businessGroupDesc;
    private String marketBusinessUnitDesc;
    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     * @param obj
     * @return
     */
    public int compareTo(Object obj) {
        if (!(obj instanceof Domain))
            throw new ClassCastException("Domain object Expected.");
        
        Domain otherDomain = (Domain) obj;
        int compareValue = this.lineOfBusiness.compareTo(otherDomain.lineOfBusiness);
        if(compareValue != 0)
            return compareValue;
        compareValue = this.businessEntity.compareTo(otherDomain.businessEntity);
        if(compareValue != 0)
            return compareValue;
        compareValue = this.businessGroup.compareTo(otherDomain.businessGroup);
        if(compareValue != 0)
            return compareValue;
        compareValue = this.marketBusinessUnit.compareTo(otherDomain.marketBusinessUnit);
        if(compareValue != 0)
            return compareValue;
        return 0;
    }
    
    /**
     * @see java.lang.Object#toString()
     * @return
     */
    public String toString() {
        final String SEPERATOR = ":";
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.lineOfBusiness);
        buffer.append(SEPERATOR).append(this.businessEntity);
        buffer.append(SEPERATOR).append(this.businessGroup);
        buffer.append(SEPERATOR).append(this.marketBusinessUnit);
        return buffer.toString();
    }
    
    /**
     * Returns the businessEntity
     * @return String businessEntity.
     */
    public String getBusinessEntity() {
        return businessEntity;
    }
    /**
     * Sets the businessEntity
     * @param businessEntity.
     */
    public void setBusinessEntity(String businessEntity) {
        this.businessEntity = businessEntity;
    }
    /**
     * Returns the businessGroup
     * @return String businessGroup.
     */
    public String getBusinessGroup() {
        return businessGroup;
    }
    /**
     * Sets the businessGroup
     * @param businessGroup.
     */
    public void setBusinessGroup(String businessGroup) {
        this.businessGroup = businessGroup;
    }
    /**
     * Returns the domainSysId
     * @return int domainSysId.
     */
    public int getDomainSysId() {
        return domainSysId;
    }
    /**
     * Sets the domainSysId
     * @param domainSysId.
     */
    public void setDomainSysId(int domainSysId) {
        this.domainSysId = domainSysId;
    }
    /**
     * Returns the lineOfBusiness
     * @return String lineOfBusiness.
     */
    public String getLineOfBusiness() {
        return lineOfBusiness;
    }
    /**
     * Sets the lineOfBusiness
     * @param lineOfBusiness.
     */
    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }
	/**
	 * @return Returns the businessEntityDesc.
	 */
	public String getBusinessEntityDesc() {
		return businessEntityDesc;
	}
	/**
	 * @param businessEntityDesc The businessEntityDesc to set.
	 */
	public void setBusinessEntityDesc(String businessEntityDesc) {
		this.businessEntityDesc = businessEntityDesc;
	}
	/**
	 * @return Returns the businessGroupDesc.
	 */
	public String getBusinessGroupDesc() {
		return businessGroupDesc;
	}
	/**
	 * @param businessGroupDesc The businessGroupDesc to set.
	 */
	public void setBusinessGroupDesc(String businessGroupDesc) {
		this.businessGroupDesc = businessGroupDesc;
	}
	/**
	 * @return Returns the lineOfBusinessDesc.
	 */
	public String getLineOfBusinessDesc() {
		return lineOfBusinessDesc;
	}
	/**
	 * @param lineOfBusinessDesc The lineOfBusinessDesc to set.
	 */
	public void setLineOfBusinessDesc(String lineOfBusinessDesc) {
		this.lineOfBusinessDesc = lineOfBusinessDesc;
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
	/**
	 * @return Returns the marketBusinessUnitDesc.
	 */
	public String getMarketBusinessUnitDesc() {
		return marketBusinessUnitDesc;
	}
	/**
	 * @param marketBusinessUnitDesc The marketBusinessUnitDesc to set.
	 */
	public void setMarketBusinessUnitDesc(String marketBusinessUnitDesc) {
		this.marketBusinessUnitDesc = marketBusinessUnitDesc;
	}
}
