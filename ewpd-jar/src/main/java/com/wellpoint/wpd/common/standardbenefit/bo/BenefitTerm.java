/*
 * Created on Feb 22, 2007
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
public interface BenefitTerm {
    public String getQualifier();

    /**
     * @param qualifier The qualifier to set.
     */
    public void setQualifier(String qualifier);

    /**
     * @return Returns the term.
     */
    public String getTerm();

    /**
     * @param term The term to set.
     */
    public void setTerm(String term);

}
