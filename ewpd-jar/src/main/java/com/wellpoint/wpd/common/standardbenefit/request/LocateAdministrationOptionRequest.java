/*
 * LocateAdministrationOptionRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LocateAdministrationOptionRequest extends WPDRequest {

	private int benefitAdministrationSystemId;
	private int benefitAdminSysId;
	private int maxSearchResultSize;
	private int entityId;
	private String entityType;    
	private int benefitComponentId;
	private boolean isPSorProductorBenefit;
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
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

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
	 * Returns the benefitAdminSysId
	 * @return int benefitAdminSysId.
	 */
	public int getBenefitAdminSysId() {
		return benefitAdminSysId;
	}
	/**
	 * Sets the benefitAdminSysId
	 * @param benefitAdminSysId.
	 */
	public void setBenefitAdminSysId(int benefitAdminSysId) {
		this.benefitAdminSysId = benefitAdminSysId;
	}
	/**
	 * @return Returns the maxSearchResultSize.
	 */
	public int getMaxSearchResultSize() {
		return maxSearchResultSize;
	}
	/**
	 * @param maxSearchResultSize The maxSearchResultSize to set.
	 */
	public void setMaxSearchResultSize(int maxSearchResultSize) {
		this.maxSearchResultSize = maxSearchResultSize;
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
    
    /**
     * @return isPSorProductorBenefit
     * 
     * Returns the isPSorProductorBenefit.
     */
    public boolean getPSorProductorBenefit() {
        return isPSorProductorBenefit;
    }
    /**
     * @param isPSorProductorBenefit
     * 
     * Sets the isPSorProductorBenefit.
     */
    public void setPSorProductorBenefit(boolean isPSorProductorBenefit) {
        this.isPSorProductorBenefit = isPSorProductorBenefit;
    }
    /**
     * @return benefitDefinitionKey
     * 
     * Returns the benefitDefinitionKey.
     */
    public int getBenefitDefinitionKey() {
        return benefitDefinitionKey;
    }
    /**
     * @param benefitDefinitionKey
     * 
     * Sets the benefitDefinitionKey.
     */
    public void setBenefitDefinitionKey(int benefitDefinitionKey) {
        this.benefitDefinitionKey = benefitDefinitionKey;
    }
}
