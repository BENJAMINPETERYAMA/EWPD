/*
 * ProdStructureTreeBenefitDate.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.tree.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProdStructureTreeBenefitDate {

    private int standardBenefitId;

    private int bnftCmpntId;

    private String effectiveDate;

    private String expiryeDate;

    private int adminId;


    /**
     * @return Returns the effectiveDate.
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }


    /**
     * @param effectiveDate
     *            The effectiveDate to set.
     */
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }


    /**
     * @return Returns the expiryeDate.
     */
    public String getExpiryeDate() {
        return expiryeDate;
    }


    /**
     * @param expiryeDate
     *            The expiryeDate to set.
     */
    public void setExpiryeDate(String expiryeDate) {
        this.expiryeDate = expiryeDate;
    }


    /**
     * @return Returns the standardBenefitId.
     */
    public int getStandardBenefitId() {
        return standardBenefitId;
    }


    /**
     * @param standardBenefitId
     *            The standardBenefitId to set.
     */
    public void setStandardBenefitId(int standardBenefitId) {
        this.standardBenefitId = standardBenefitId;
    }


    /**
     * @return Returns the adminId.
     */
    public int getAdminId() {
        return adminId;
    }


    /**
     * @param adminId
     *            The adminId to set.
     */
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }


    /**
     * @return Returns the bnftCmpntId.
     */
    public int getBnftCmpntId() {
        return bnftCmpntId;
    }


    /**
     * @param bnftCmpntId
     *            The bnftCmpntId to set.
     */
    public void setBnftCmpntId(int bnftCmpntId) {
        this.bnftCmpntId = bnftCmpntId;
    }
}