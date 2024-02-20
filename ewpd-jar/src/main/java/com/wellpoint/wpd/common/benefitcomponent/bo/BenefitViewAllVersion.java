/*
 * BenefitViewAllVersion.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.bo;

/**
 * Business Object for benefit
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitViewAllVersion {
    private String minorHeading;

    private String versionNo;

    private String updatedDate;

    private String updatedBy;


    /**
     * @return Returns the minorHeading.
     */
    public String getMinorHeading() {
        return minorHeading;
    }


    /**
     * @param minorHeading
     *            The minorHeading to set.
     */
    public void setMinorHeading(String minorHeading) {
        this.minorHeading = minorHeading;
    }


    /**
     * @return Returns the versionNo.
     */
    public String getVersionNo() {
        return versionNo;
    }


    /**
     * @param versionNo
     *            The versionNo to set.
     */
    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }


    /**
     * @return Returns the updatedBy.
     */
    public String getUpdatedBy() {
        return updatedBy;
    }


    /**
     * @param updatedBy
     *            The updatedBy to set.
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }


    /**
     * @return Returns the updatedDate.
     */
    public String getUpdatedDate() {
        return updatedDate;
    }


    /**
     * @param updatedDate
     *            The updatedDate to set.
     */
    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

}