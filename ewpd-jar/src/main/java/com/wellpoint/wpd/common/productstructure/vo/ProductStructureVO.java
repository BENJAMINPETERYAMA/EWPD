/*
 * ProductStructureVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.vo;

import java.util.Date;
import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureVO {

    private String productStructureName;

    private int productStructureId;

    private int parentProductStructureId;

    private String description;

    private List lineOfBusiness;

    private List businessEntity;

    private List businessGroup;

    private List associatedBenefitComponentList;

    private Date effectiveDate;

    private Date expiryDate;

    private String state;

    private String status;

    private String structureType;

    private String mandateType;

    private String stateId;

    private String stateDesc;

    private int version;

    private List benefitComponentList;

    private List businessDomains;
    
    private int benefitComponentId;
    
    //September release change added new field 'Product Family'
    
    private String productFamilyId;
//CARS START
    private List marketBusinessUnit;
//CARS END

    /**
     * Returns the state
     * 
     * @return String state.
     */

    public String getState() {
        return state;
    }


    /**
     * Sets the state
     * 
     * @param state.
     */

    public void setState(String state) {
        this.state = state;
    }


    /**
     * Returns the status
     * 
     * @return String status.
     */

    public String getStatus() {
        return status;
    }


    /**
     * Sets the status
     * 
     * @param status.
     */

    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * Returns the associatedBenefitComponentList
     * 
     * @return List associatedBenefitComponentList.
     */

    public List getAssociatedBenefitComponentList() {
        return associatedBenefitComponentList;
    }


    /**
     * Sets the associatedBenefitComponentList
     * 
     * @param associatedBenefitComponentList.
     */

    public void setAssociatedBenefitComponentList(
            List associatedBenefitComponentList) {
        this.associatedBenefitComponentList = associatedBenefitComponentList;
    }


    /**
     * Returns the businessEntity
     * 
     * @return List businessEntity.
     */

    public List getBusinessEntity() {
        return businessEntity;
    }


    /**
     * Sets the businessEntity
     * 
     * @param businessEntity.
     */

    public void setBusinessEntity(List businessEntity) {
        this.businessEntity = businessEntity;
    }


    /**
     * Returns the businessGroup
     * 
     * @return List businessGroup.
     */

    public List getBusinessGroup() {
        return businessGroup;
    }


    /**
     * Sets the businessGroup
     * 
     * @param businessGroup.
     */

    public void setBusinessGroup(List businessGroup) {
        this.businessGroup = businessGroup;
    }


    /**
     * Returns the description
     * 
     * @return String description.
     */

    public String getDescription() {
        return description;
    }


    /**
     * Sets the description
     * 
     * @param description.
     */

    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Returns the effectiveDate
     * 
     * @return Date effectiveDate.
     */

    public Date getEffectiveDate() {
        return effectiveDate;
    }


    /**
     * Sets the effectiveDate
     * 
     * @param effectiveDate.
     */

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }


    /**
     * Returns the expiryDate
     * 
     * @return Date expiryDate.
     */

    public Date getExpiryDate() {
        return expiryDate;
    }


    /**
     * Sets the expiryDate
     * 
     * @param expiryDate.
     */

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }


    /**
     * Returns the lineOfBusiness
     * 
     * @return List lineOfBusiness.
     */

    public List getLineOfBusiness() {
        return lineOfBusiness;
    }


    /**
     * Sets the lineOfBusiness
     * 
     * @param lineOfBusiness.
     */

    public void setLineOfBusiness(List lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }


    /**
     * Returns the productStructureId
     * 
     * @return int productStructureId.
     */

    public int getProductStructureId() {
        return productStructureId;
    }


    /**
     * Sets the productStructureId
     * 
     * @param productStructureId.
     */

    public void setProductStructureId(int productStructureId) {
        this.productStructureId = productStructureId;
    }


    /**
     * Returns the productStructureName
     * 
     * @return String productStructureName.
     */

    public String getProductStructureName() {
        return productStructureName;
    }


    /**
     * Sets the productStructureName
     * 
     * @param productStructureName.
     */

    public void setProductStructureName(String productStructureName) {
        this.productStructureName = productStructureName;
    }

    /**
     * Returns the version
     * 
     * @return int version.
     */
    public int getVersion() {
        return version;
    }

    /**
     * Sets the version
     * 
     * @param version.
     */
    public void setVersion(int version) {
        this.version = version;
    }


    /**
     * Returns the parentProductStructureId
     * 
     * @return int parentProductStructureId.
     */

    public int getParentProductStructureId() {
        return parentProductStructureId;
    }


    /**
     * Sets the parentProductStructureId
     * 
     * @param parentProductStructureId.
     */

    public void setParentProductStructureId(int parentProductStructureId) {
        this.parentProductStructureId = parentProductStructureId;
    }


    /**
     * Returns the benefitComponentList
     * 
     * @return List benefitComponentList.
     */

    public List getBenefitComponentList() {
        return benefitComponentList;
    }


    /**
     * Sets the benefitComponentList
     * 
     * @param benefitComponentList.
     */

    public void setBenefitComponentList(List benefitComponentList) {
        this.benefitComponentList = benefitComponentList;
    }


    /**
     * Returns the businessDomains
     * 
     * @return List businessDomains.
     */

    public List getBusinessDomains() {
        return businessDomains;
    }


    /**
     * Sets the businessDomains
     * 
     * @param businessDomains.
     */

    public void setBusinessDomains(List businessDomains) {
        this.businessDomains = businessDomains;
    }


    /**
     * @return Returns the mandateType.
     */
    public String getMandateType() {
        return mandateType;
    }


    /**
     * @param mandateType
     *            The mandateType to set.
     */
    public void setMandateType(String mandateType) {
        this.mandateType = mandateType;
    }


    /**
     * @return Returns the structureType.
     */
    public String getStructureType() {
        return structureType;
    }


    /**
     * @param structureType
     *            The structureType to set.
     */
    public void setStructureType(String structureType) {
        this.structureType = structureType;
    }


    /**
     * @return stateDesc
     * 
     * Returns the stateDesc.
     */
    public String getStateDesc() {
        return stateDesc;
    }


    /**
     * @param stateDesc
     * 
     * Sets the stateDesc.
     */
    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }


    /**
     * @return stateId
     * 
     * Returns the stateId.
     */
    public String getStateId() {
        return stateId;
    }


    /**
     * @param stateId
     * 
     * Sets the stateId.
     */
    public void setStateId(String stateId) {
        this.stateId = stateId;
    }
	/**
	 * @return Returns the benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * @param benefitComponentId The benefitComponentId to set.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
	/**
	 * @return Returns the productFamilyId.
	 */
	public String getProductFamilyId() {
		return productFamilyId;
	}
	/**
	 * @param productFamilyId The productFamilyId to set.
	 */
	public void setProductFamilyId(String productFamilyId) {
		this.productFamilyId = productFamilyId;
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

