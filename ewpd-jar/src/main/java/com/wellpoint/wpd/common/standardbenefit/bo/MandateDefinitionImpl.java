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
public class MandateDefinitionImpl implements MandateDefinition {
    private String effectiveDate;

    private String expiryDate;

    private String mandate;

    private String description;

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Returns the effectiveDate.
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * @param effectiveDate The effectiveDate to set.
     */
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * @return Returns the expiryDate.
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param expiryDate The expiryDate to set.
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * @return Returns the mandate.
     */
    //	public String getMandate() {
    //		return mandate;
    //	}
    //	/**
    //	 * @param mandate The mandate to set.
    //	 */
    //	public void setMandate(String mandate) {
    //		this.mandate = mandate;
    //	}
    /**
     * @return Returns the mandate.
     */
    public String getMandate() {
        return mandate;
    }

    /**
     * @param mandate The mandate to set.
     */
    public void setMandate(String mandate) {
        this.mandate = mandate;
    }
}
