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
public interface StandardBenefitSearch {
    public String getBusinessDomain();

    /**
     * @param businessDomain The businessDomain to set.
     */
    public void setBusinessDomain(String businessDomain);

    /**
     * @return Returns the minorHeading.
     */
    public String getMinorHeading();

    /**
     * @param minorHeading The minorHeading to set.
     */
    public void setMinorHeading(String minorHeading);

    /**
     * @return Returns the updatedBy.
     */
    public String getUpdatedBy();

    /**
     * @param updatedBy The updatedBy to set.
     */
    public void setUpdatedBy(String updatedBy);

    /**
     * @return Returns the updatedDate.
     */
    public String getUpdatedDate();

    /**
     * @param updatedDate The updatedDate to set.
     */
    public void setUpdatedDate(String updatedDate);

    /**
     * @return Returns the versionNumber.
     */
    public String getVersionNumber();

    /**
     * @param versionNumber The versionNumber to set.
     */
    public void setVersionNumber(String versionNumber);

}
