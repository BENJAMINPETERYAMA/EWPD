/*
 * Created on Feb 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.standardbenefit;

/**
 * @author u13259
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class StandardBenefitSearchImpl implements StandardBenefitSearch {
    private String minorHeading;

    private String businessDomain;

    private String versionNumber;

    private String updatedDate;

    private String updatedBy;

    /**
     * @return Returns the businessDomain.
     */
    public String getBusinessDomain() {
        return businessDomain;
    }

    /**
     * @param businessDomain The businessDomain to set.
     */
    public void setBusinessDomain(String businessDomain) {
        this.businessDomain = businessDomain;
    }

    /**
     * @return Returns the minorHeading.
     */
    public String getMinorHeading() {
        return minorHeading;
    }

    /**
     * @param minorHeading The minorHeading to set.
     */
    public void setMinorHeading(String minorHeading) {
        this.minorHeading = minorHeading;
    }

    /**
     * @return Returns the updatedBy.
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy The updatedBy to set.
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
     * @param updatedDate The updatedDate to set.
     */
    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * @return Returns the versionNumber.
     */
    public String getVersionNumber() {
        return versionNumber;
    }

    /**
     * @param versionNumber The versionNumber to set.
     */
    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }
}
