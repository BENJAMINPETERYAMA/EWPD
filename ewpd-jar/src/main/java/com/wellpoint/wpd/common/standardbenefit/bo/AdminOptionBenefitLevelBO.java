/*
 * AdminOptionBenefitLevelBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminOptionBenefitLevelBO {
    
    private int benefitLevelId;
    private int adminId;

    /**
     * @return benefitLevelId
     * 
     * Returns the benefitLevelId.
     */
    public int getBenefitLevelId() {
        return benefitLevelId;
    }
    /**
     * @param benefitLevelId
     * 
     * Sets the benefitLevelId.
     */
    public void setBenefitLevelId(int benefitLevelId) {
        this.benefitLevelId = benefitLevelId;
    }
    /**
     * @return adminId
     * 
     * Returns the adminId.
     */
    public int getAdminId() {
        return adminId;
    }
    /**
     * @param adminId
     * 
     * Sets the adminId.
     */
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}
