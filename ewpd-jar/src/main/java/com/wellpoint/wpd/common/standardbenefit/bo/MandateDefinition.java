/*
 * Created on Feb 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

/**
 * @author u13259
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface MandateDefinition {
    public String getDescription();

    /**
     * @param description The description to set.
     */
    public void setDescription(String description);

    /**
     * @return Returns the effectiveDate.
     */
    public String getEffectiveDate();

    /**
     * @param effectiveDate The effectiveDate to set.
     */
    public void setEffectiveDate(String effectiveDate);

    /**
     * @return Returns the expiryDate.
     */
    public String getExpiryDate();

    /**
     * @param expiryDate The expiryDate to set.
     */
    public void setExpiryDate(String expiryDate);

    public String getMandate();

    /**
     * @param mandate The mandate to set.
     */
    public void setMandate(String mandate);

}
