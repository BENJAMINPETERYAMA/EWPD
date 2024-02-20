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
public class BenefitTermImpl implements BenefitTerm {
    private String qualifier;

    private String term;

    /**
     * @return Returns the qualifier.
     */
    public String getQualifier() {
        return qualifier;
    }

    /**
     * @param qualifier The qualifier to set.
     */
    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    /**
     * @return Returns the term.
     */
    public String getTerm() {
        return term;
    }

    /**
     * @param term The term to set.
     */
    public void setTerm(String term) {
        this.term = term;
    }
}
