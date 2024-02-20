/*
 * AdminOptionLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.adminoption.locatecriteria;

import java.util.List;
import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * Locate Criteria class for Admin Option
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminOptionLocateCriteria extends LocateCriteria {

    private String adminNameCriteria;

    private List benefitTermList;

    private List benefitQualifierList;

    private int adminOptionSysId;

    private String action;

    private int adminOptSysIdForUpdate;

    private int adminLevelOptionAssnSysId;
    
    private int adminOptionParentSysId;
    


    /**
     * Returns the adminOptionSysId.
     * 
     * @return adminOptionSysId
     */
    public int getAdminOptionSysId() {
        return adminOptionSysId;
    }


    /**
     * The adminOptionSysId to set.
     * 
     * @param adminOptionSysId
     */
    public void setAdminOptionSysId(int adminOptionSysId) {
        this.adminOptionSysId = adminOptionSysId;
    }


    /**
     * Returns the adminNameCriteria.
     * 
     * @return adminNameCriteria
     */
    public String getAdminNameCriteria() {
        return adminNameCriteria;
    }


    /**
     * The adminNameCriteria to set.
     * 
     * @param adminNameCriteria
     */
    public void setAdminNameCriteria(String adminNameCriteria) {
        this.adminNameCriteria = adminNameCriteria;
    }


    /**
     * Returns the benefitQualifierList
     * 
     * @return benefitQualifierList.
     */
    public List getBenefitQualifierList() {
        return benefitQualifierList;
    }


    /**
     * Sets the benefitQualifierList
     * 
     * @param benefitQualifierList.
     */
    public void setBenefitQualifierList(List benefitQualifierList) {
        this.benefitQualifierList = benefitQualifierList;
    }


    /**
     * Returns the benefitTermList
     * 
     * @return benefitTermList.
     */
    public List getBenefitTermList() {
        return benefitTermList;
    }


    /**
     * Sets the benefitTermList
     * 
     * @param benefitTermList.
     */
    public void setBenefitTermList(List benefitTermList) {
        this.benefitTermList = benefitTermList;
    }


    /**
     * Returns the action.
     * 
     * @return action
     */
    public String getAction() {
        return action;
    }


    /**
     * Sets the action
     * 
     * @param action
     */
    public void setAction(String action) {
        this.action = action;
    }


    /**
     * Returns the adminOptSysIdForUpdate.
     * 
     * @return adminOptSysIdForUpdate
     */
    public int getAdminOptSysIdForUpdate() {
        return adminOptSysIdForUpdate;
    }


    /**
     * The adminOptSysIdForUpdate to set.
     * 
     * @param adminOptSysIdForUpdate
     */
    public void setAdminOptSysIdForUpdate(int adminOptSysIdForUpdate) {
        this.adminOptSysIdForUpdate = adminOptSysIdForUpdate;
    }


    /**
     * Returns the adminLevelOptionAssnSysId.
     * 
     * @return adminLevelOptionAssnSysId
     */
    public int getAdminLevelOptionAssnSysId() {
        return adminLevelOptionAssnSysId;
    }


    /**
     * The adminLevelOptionAssnSysId to set.
     * 
     * @param adminLevelOptionAssnSysId
     */
    public void setAdminLevelOptionAssnSysId(int adminLevelOptionAssnSysId) {
        this.adminLevelOptionAssnSysId = adminLevelOptionAssnSysId;
    }
	/**
	 * @return Returns the adminOptionParentSysId.
	 */
	public int getAdminOptionParentSysId() {
		return adminOptionParentSysId;
	}
	/**
	 * @param adminOptionParentSysId The adminOptionParentSysId to set.
	 */
	public void setAdminOptionParentSysId(int adminOptionParentSysId) {
		this.adminOptionParentSysId = adminOptionParentSysId;
	}
   
}