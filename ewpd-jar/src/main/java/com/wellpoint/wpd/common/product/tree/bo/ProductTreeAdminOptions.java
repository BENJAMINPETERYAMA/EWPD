/*
 * ProductTreeAdminOptions.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.tree.bo;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductTreeAdminOptions {

    
    private int benefitAdministrationId;
	
	private int adminOptionId;
	
	private int adminLevelType;
	
	private String adminOptionDesc;
	
	private List  adminOptionsForBenefit;
	   
	private List adminOptionsForBenefitLevel;
	
	private int adminLevelOptionAssnId;
	
	private int entityId;
	
	private String entityType;    
	
	private int benefitComponentId;
	
	private int benefitDfnid;
   
    /**
     * Returns the adminLevelType
     * @return int adminLevelType.
     */
    public int getAdminLevelType() {
        return adminLevelType;
    }
    
    /**
     * Sets the adminLevelType
     * @param adminLevelType.
     */
    public void setAdminLevelType(int adminLevelType) {
        this.adminLevelType = adminLevelType;
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
     * Returns the benefitAdministrationId
     * @return int benefitAdministrationId.
     */
    public int getBenefitAdministrationId() {
        return benefitAdministrationId;
    }
    
    /**
     * Sets the benefitAdministrationId
     * @param benefitAdministrationId.
     */
    public void setBenefitAdministrationId(int benefitAdministrationId) {
        this.benefitAdministrationId = benefitAdministrationId;
    }
    /**
     * Returns the adminOptionDesc
     * @return String adminOptionDesc.
     */
    public String getAdminOptionDesc() {
        return adminOptionDesc;
    }
    /**
     * Sets the adminOptionDesc
     * @param adminOptionDesc.
     */
    public void setAdminOptionDesc(String adminOptionDesc) {
        this.adminOptionDesc = adminOptionDesc;
    }
    /**
     * Returns the adminOptionsForBenefit
     * @return List adminOptionsForBenefit.
     */
    public List getAdminOptionsForBenefit() {
        return adminOptionsForBenefit;
    }
    /**
     * Sets the adminOptionsForBenefit
     * @param adminOptionsForBenefit.
     */
    public void setAdminOptionsForBenefit(List adminOptionsForBenefit) {
        this.adminOptionsForBenefit = adminOptionsForBenefit;
    }
    /**
     * Returns the adminOptionsForBenefitLevel
     * @return List adminOptionsForBenefitLevel.
     */
    public List getAdminOptionsForBenefitLevel() {
        return adminOptionsForBenefitLevel;
    }
    /**
     * Sets the adminOptionsForBenefitLevel
     * @param adminOptionsForBenefitLevel.
     */
    public void setAdminOptionsForBenefitLevel(List adminOptionsForBenefitLevel) {
        this.adminOptionsForBenefitLevel = adminOptionsForBenefitLevel;
    }
    /**
     * Returns the adminLevelOptionAssnId
     * @return int adminLevelOptionAssnId.
     */
    public int getAdminLevelOptionAssnId() {
        return adminLevelOptionAssnId;
    }
    /**
     * Sets the adminLevelOptionAssnId
     * @param adminLevelOptionAssnId.
     */
    public void setAdminLevelOptionAssnId(int adminLevelOptionAssnId) {
        this.adminLevelOptionAssnId = adminLevelOptionAssnId;
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
	 * @return Returns the benefitDfnid.
	 */
	public int getBenefitDfnid() {
		return benefitDfnid;
	}
	/**
	 * @param benefitDfnid The benefitDfnid to set.
	 */
	public void setBenefitDfnid(int benefitDfnid) {
		this.benefitDfnid = benefitDfnid;
	}
}
