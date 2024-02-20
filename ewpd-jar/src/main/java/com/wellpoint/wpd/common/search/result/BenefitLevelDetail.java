/*
 * BenefitLevelDetail.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.search.result;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitLevelDetail extends ObjectDetail {

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getQualifier() {
        return qualifier;
    }
    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }
    private String name;
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	private String description;
	private String qualifier;
	private String term;

	

	private BenefitLevelIdentifier bli;
	private int version;

	/**
	 * @return Returns the version.
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version The version to set.
	 */
	public void setVersion(int version) {
		this.version = version;
	}
    public ObjectIdentifier getIdentifier() {
        return bli;
    }
    /**
     * @see com.wellpoint.wpd.common.search.result.ObjectDetail#setIdentifier(com.wellpoint.wpd.common.search.result.ObjectIdentifier)
     * @param identifier
     */
    public void setIdentifier(ObjectIdentifier identifier) {
        if(identifier != null && identifier instanceof BenefitLevelIdentifier){
            bli = (BenefitLevelIdentifier)identifier;
        }else{
            throw new IllegalArgumentException(
                    "setIdentifier method in BenefitLevelDetail.  Parameter is null or of incorrect type "
                            + identifier);
        }        
        
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
