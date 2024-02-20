/*
 * AdminOptionLocateResult.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.adminoption.locateresult;

import com.wellpoint.wpd.common.bo.LocateResult;

/**
 * Fields returned by admin option locate
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminOptionLocateResult extends LocateResult {

    private int adminOptionId;

    private String adminName;

    private String benefitTerm;

    private String benefitQualifier;

    private boolean versionFlag;


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
     * Returns the benefitQualifier
     * 
     * @return String benefitQualifier.
     */
    public String getBenefitQualifier() {
        return benefitQualifier;
    }


    /**
     * Sets the benefitQualifier
     * 
     * @param benefitQualifier.
     */
    public void setBenefitQualifier(String benefitQualifier) {
        this.benefitQualifier = benefitQualifier;
    }


    /**
     * Returns the benefitTerm
     * 
     * @return String benefitTerm.
     */
    public String getBenefitTerm() {
        return benefitTerm;
    }


    /**
     * Sets the benefitTerm
     * 
     * @param benefitTerm.
     */
    public void setBenefitTerm(String benefitTerm) {
        this.benefitTerm = benefitTerm;
    }


    /**
     * Returns the adminName
     * 
     * @return String adminName.
     */
    public String getAdminName() {
        return adminName;
    }


    /**
     * Sets the adminName
     * 
     * @param adminName.
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }


    /**
     * @return Returns the versionFlag.
     */
    public boolean isVersionFlag() {
        return versionFlag;
    }


    /**
     * The versionFlag to set.
     * 
     * @param versionFlag
     */
    public void setVersionFlag(boolean versionFlag) {
        this.versionFlag = versionFlag;
    }
}