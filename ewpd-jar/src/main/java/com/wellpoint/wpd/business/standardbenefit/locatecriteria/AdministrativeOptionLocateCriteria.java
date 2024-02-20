/*
 * AdministrativeOptionLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.standardbenefit.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdministrativeOptionLocateCriteria extends LocateCriteria {
	private int benefitAdministrationSystemId;
	private int adminOptionId;
	private int adminLevelAssociationSysemId;
	private int entityId;
	private String entityType;    
	private int benefitComponentId;
	private boolean isPSorProduct;
	private int benefitDefinitionKey;
	private int dateSegmentId;
	
	
	/**
	 * @return Returns the dateSegmentId.
	 */
	public int getDateSegmentId() {
		return dateSegmentId;
	}
	/**
	 * @param dateSegmentId The dateSegmentId to set.
	 */
	public void setDateSegmentId(int dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}
	/**
	 * Returns the benefitAdministrationSystemId
	 * @return int benefitAdministrationSystemId.
	 */
	public int getBenefitAdministrationSystemId() {
		return benefitAdministrationSystemId;
	}
	/**
	 * Sets the benefitAdministrationSystemId
	 * @param benefitAdministrationSystemId.
	 */
	public void setBenefitAdministrationSystemId(
			int benefitAdministrationSystemId) {
		this.benefitAdministrationSystemId = benefitAdministrationSystemId;
	}
	/**
	 * Returns the adminOptionId
	 * @return int adminOptionId.
	 */
	public int getAdminOptionId() {
		return adminOptionId;
	}
	/**
	 * Sets the adminOptionId
	 * @param adminOptionId.
	 */
	public void setAdminOptionId(int adminOptionId) {
		this.adminOptionId = adminOptionId;
	}
	/**
	 * Returns the adminLevelAssociationSysemId
	 * @return int adminLevelAssociationSysemId.
	 */
	public int getAdminLevelAssociationSysemId() {
		return adminLevelAssociationSysemId;
	}
	/**
	 * Sets the adminLevelAssociationSysemId
	 * @param adminLevelAssociationSysemId.
	 */
	public void setAdminLevelAssociationSysemId(int adminLevelAssociationSysemId) {
		this.adminLevelAssociationSysemId = adminLevelAssociationSysemId;
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
	 * @return Returns the entityId.
	 */
	public int getEntityId() {
		return entityId;
	}
	/**
	 * @param entityId The entityId to set.
	 */
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	/**
	 * @return Returns the entityType.
	 */
	public String getEntityType() {
		return entityType;
	}
	/**
	 * @param entityType The entityType to set.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
    /**
     * @return isPSorProduct
     * 
     * Returns the isPSorProduct.
     */
    public boolean getPSorProduct() {
        return isPSorProduct;
    }
    /**
     * @param isPSorProduct
     * 
     * Sets the isPSorProduct.
     */
    public void setPSorProduct(boolean isPSorProduct) {
        this.isPSorProduct = isPSorProduct;
    }
	/**
	 * @return Returns the benefitDefinitionKey.
	 */
	public int getBenefitDefinitionKey() {
		return benefitDefinitionKey;
	}
	/**
	 * @param benefitDefinitionKey The benefitDefinitionKey to set.
	 */
	public void setBenefitDefinitionKey(int benefitDefinitionKey) {
		this.benefitDefinitionKey = benefitDefinitionKey;
	}
}

