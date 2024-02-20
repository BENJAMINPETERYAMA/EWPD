/*
 * Created on Sep 13, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.adminmethod.locatecriteria;

import java.util.List;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminMethodLocateCriteria extends LocateCriteria {

    private String spsName;

    private String reference;
    
    private String adminMethod;
    
    private int entityId;
    
    private String entityType ;
    
    private int administrationId; 
    
    private int benefitComponentId;
    
    private int adminMethodId;
    
    private int spsId;
    
    private List adminIdList;
    
    private List spsIdList;
    
    private String mode;
    
    private String benefitComponentName;
    
    private int benefitDefenitionId;    
    //CARS START
    private boolean datafeedFlag = false;
    //CARS END
    private int benefitSysId;

	/**
	 * @return Returns the benefitSysId.
	 */
	public int getBenefitSysId() {
		return benefitSysId;
	}
	/**
	 * @param benefitSysId The benefitSysId to set.
	 */
	public void setBenefitSysId(int benefitSysId) {
		this.benefitSysId = benefitSysId;
	}
    /**
     * Returns the reference
     * 
     * @return String reference.
     */

    public String getReference() {
        return reference;
    }

    /**
     * Sets the reference
     * 
     * @param reference.
     */

    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * Returns the spsName
     * 
     * @return String spsName.
     */

    public String getSpsName() {
        return spsName;
    }

    /**
     * Sets the spsName
     * 
     * @param spsName.
     */

    public void setSpsName(String spsName) {
        this.spsName = spsName;
    }
    /**
     * Returns the adminMethod
     * @return String adminMethod.
     */

    public String getAdminMethod() {
        return adminMethod;
    }
    /**
     * Sets the adminMethod
     * @param adminMethod.
     */

    public void setAdminMethod(String adminMethod) {
        this.adminMethod = adminMethod;
    }
    /**
     * Returns the entityType
     * @return String entityType.
     */

    public String getEntityType() {
        return entityType;
    }
    /**
     * Sets the entityType
     * @param entityType.
     */

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
    /**
     * Returns the entityId
     * @return int entityId.
     */

    public int getEntityId() {
        return entityId;
    }
    /**
     * Sets the entityId
     * @param entityId.
     */

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }
    /**
     * Returns the administrationId
     * @return int administrationId.
     */

    public int getAdministrationId() {
        return administrationId;
    }
    /**
     * Sets the administrationId
     * @param administrationId.
     */

    public void setAdministrationId(int administrationId) {
        this.administrationId = administrationId;
    }
    /**
     * Returns the adminMethodId
     * @return int adminMethodId.
     */

    public int getAdminMethodId() {
        return adminMethodId;
    }
    /**
     * Sets the adminMethodId
     * @param adminMethodId.
     */

    public void setAdminMethodId(int adminMethodId) {
        this.adminMethodId = adminMethodId;
    }
    /**
     * Returns the spsId
     * @return int spsId.
     */

    public int getSpsId() {
        return spsId;
    }
    /**
     * Sets the spsId
     * @param spsId.
     */

    public void setSpsId(int spsId) {
        this.spsId = spsId;
    }
    /**
     * Returns the adminIdList
     * @return List adminIdList.
     */

    public List getAdminIdList() {
        return adminIdList;
    }
    /**
     * Sets the adminIdList
     * @param adminIdList.
     */

    public void setAdminIdList(List adminIdList) {
        this.adminIdList = adminIdList;
    }
    /**
     * Returns the spsIdList
     * @return List spsIdList.
     */

    public List getSpsIdList() {
        return spsIdList;
    }
    /**
     * Sets the spsIdList
     * @param spsIdList.
     */

    public void setSpsIdList(List spsIdList) {
        this.spsIdList = spsIdList;
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
     * Returns the mode
     * @return String mode.
     */

    public String getMode() {
        return mode;
    }
    /**
     * Sets the mode
     * @param mode.
     */

    public void setMode(String mode) {
        this.mode = mode;
    }
    /**
     * Returns the benefitComponentName
     * @return String benefitComponentName.
     */

    public String getBenefitComponentName() {
        return benefitComponentName;
    }
    /**
     * Sets the benefitComponentName
     * @param benefitComponentName.
     */

    public void setBenefitComponentName(String benefitComponentName) {
        this.benefitComponentName = benefitComponentName;
    }
    /**
     * Returns the benefitDefenitionId
     * @return int benefitDefenitionId.
     */

    public int getBenefitDefenitionId() {
        return benefitDefenitionId;
    }
    /**
     * Sets the benefitDefenitionId
     * @param benefitDefenitionId.
     */

    public void setBenefitDefenitionId(int benefitDefenitionId) {
        this.benefitDefenitionId = benefitDefenitionId;
    }
	/**
	 * @return Returns the datafeedFlag.
	 */
	public boolean isDatafeedFlag() {
		return datafeedFlag;
	}
	/**
	 * @param datafeedFlag The datafeedFlag to set.
	 */
	public void setDatafeedFlag(boolean datafeedFlag) {
		this.datafeedFlag = datafeedFlag;
	}
 }