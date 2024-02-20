/*
 * TreeAdminLevels.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.tree.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class TreeAdminLevels {

    private int benefitAdministrationId;

    private int adminOptionId;

    private int adminOptionAssnId;

    private int adminLevelType;

    private String adminOptionDesc;
    
    private int benefitComponentId;
    
    private int entitySysId;
    
    private int sequenceNumber;


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
	 * @return Returns the entitySysId.
	 */
	public int getEntitySysId() {
		return entitySysId;
	}
	/**
	 * @param entitySysId The entitySysId to set.
	 */
	public void setEntitySysId(int entitySysId) {
		this.entitySysId = entitySysId;
	}
    /**
     * Returns the adminLevelType
     * 
     * @return int adminLevelType.
     */
    public int getAdminLevelType() {
        return adminLevelType;
    }


    /**
     * Sets the adminLevelType
     * 
     * @param adminLevelType.
     */
    public void setAdminLevelType(int adminLevelType) {
        this.adminLevelType = adminLevelType;
    }


    /**
     * Returns the adminOptionId
     * 
     * @return int adminOptionId.
     */
    public int getAdminOptionId() {
        return adminOptionId;
    }


    /**
     * Sets the adminOptionId
     * 
     * @param adminOptionId.
     */
    public void setAdminOptionId(int adminOptionId) {
        this.adminOptionId = adminOptionId;
    }


    /**
     * Returns the benefitAdministrationId
     * 
     * @return int benefitAdministrationId.
     */
    public int getBenefitAdministrationId() {
        return benefitAdministrationId;
    }


    /**
     * Sets the benefitAdministrationId
     * 
     * @param benefitAdministrationId.
     */
    public void setBenefitAdministrationId(int benefitAdministrationId) {
        this.benefitAdministrationId = benefitAdministrationId;
    }


    /**
     * Returns the adminOptionDesc
     * 
     * @return String adminOptionDesc.
     */
    public String getAdminOptionDesc() {
        return adminOptionDesc;
    }


    /**
     * Sets the adminOptionDesc
     * 
     * @param adminOptionDesc.
     */
    public void setAdminOptionDesc(String adminOptionDesc) {
        this.adminOptionDesc = adminOptionDesc;
    }


    /**
     * Returns the adminOptionAssnId
     * 
     * @return int adminOptionAssnId.
     */
    public int getAdminOptionAssnId() {
        return adminOptionAssnId;
    }


    /**
     * Sets the adminOptionAssnId
     * 
     * @param adminOptionAssnId.
     */
    public void setAdminOptionAssnId(int adminOptionAssnId) {
        this.adminOptionAssnId = adminOptionAssnId;
    }
	/**
	 * @return Returns the sequenceNumber.
	 */
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	/**
	 * @param sequenceNumber The sequenceNumber to set.
	 */
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
}