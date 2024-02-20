/*
 * ProductStructureLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.product.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

import java.util.Date;
import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureLocateCriteria extends LocateCriteria{
    int productKey;
    List lineOfBusiness;
    List businessEntity;
    List businessGroup;
    List domains;
    Date effectiveDate;
    Date expiryDate;
//  new fields productType,mandateType,stateCode added for slecting product list
    String structureType;
    String mandateType;
    String stateId;
    //CARS START
    List marketBusinessUnit;
    //CARS END
    /**
     * Returns the businessEntity
     * @return List businessEntity.
     */
    public List getBusinessEntity() {
        return businessEntity;
    }
    /**
     * Sets the businessEntity
     * @param businessEntity.
     */
    public void setBusinessEntity(List businessEntity) {
        this.businessEntity = businessEntity;
    }
    /**
     * Returns the businessGroup
     * @return List businessGroup.
     */
    public List getBusinessGroup() {
        return businessGroup;
    }
    /**
     * Sets the businessGroup
     * @param businessGroup.
     */
    public void setBusinessGroup(List businessGroup) {
        this.businessGroup = businessGroup;
    }
    /**
     * Returns the domains
     * @return List domains.
     */
    public List getDomains() {
        return domains;
    }
    /**
     * Sets the domains
     * @param domains.
     */
    public void setDomains(List domains) {
        this.domains = domains;
    }
    /**
     * Returns the effectiveDate
     * @return Date effectiveDate.
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }
    /**
     * Sets the effectiveDate
     * @param effectiveDate.
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    /**
     * Returns the expiryDate
     * @return Date expiryDate.
     */
    public Date getExpiryDate() {
        return expiryDate;
    }
    /**
     * Sets the expiryDate
     * @param expiryDate.
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
    /**
     * Returns the lineOfBusiness
     * @return List lineOfBusiness.
     */
    public List getLineOfBusiness() {
        return lineOfBusiness;
    }
    /**
     * Sets the lineOfBusiness
     * @param lineOfBusiness.
     */
    public void setLineOfBusiness(List lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }
    /**
     * Returns the productKey
     * @return int productKey.
     */
    public int getProductKey() {
        return productKey;
    }
    /**
     * Sets the productKey
     * @param productKey.
     */
    public void setProductKey(int productKey) {
        this.productKey = productKey;
    }
	/**
	 * @return Returns the mandateType.
	 */
	public String getMandateType() {
		return mandateType;
	}
	/**
	 * @param mandateType The mandateType to set.
	 */
	public void setMandateType(String mandateType) {
		this.mandateType = mandateType;
	}

	/**
	 * @return Returns the stateId.
	 */
	public String getStateId() {
		return stateId;
	}
	/**
	 * @param stateId The stateId to set.
	 */
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	/**
	 * @return Returns the structureType.
	 */
	public String getStructureType() {
		return structureType;
	}
	/**
	 * @param structureType The structureType to set.
	 */
	public void setStructureType(String structureType) {
		this.structureType = structureType;
	}
	/**
	 * @return Returns the marketBusinessUnit.
	 */
	public List getMarketBusinessUnit() {
		return marketBusinessUnit;
	}
	/**
	 * @param marketBusinessUnit The marketBusinessUnit to set.
	 */
	public void setMarketBusinessUnit(List marketBusinessUnit) {
		this.marketBusinessUnit = marketBusinessUnit;
	}
}
