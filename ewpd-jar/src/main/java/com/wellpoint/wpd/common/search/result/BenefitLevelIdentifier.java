/*
 * BenefitLevelIdentifier.java
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
public class BenefitLevelIdentifier extends ObjectIdentifier {
	private int identifier;
	
	private int benDefIdentifier;
	/**
	 * @return Returns the identifier.
	 */
	public int getIdentifier() {
		return identifier;
	}
	/**
	 * @param identifier The identifier to set.
	 */
	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}
	
    /**
     * Returns the benDefIdentifier
     * @return int benDefIdentifier.
     */
    public int getBenDefIdentifier() {
        return benDefIdentifier;
    }
    /**
     * Sets the benDefIdentifier
     * @param benDefIdentifier.
     */
    public void setBenDefIdentifier(int benDefIdentifier) {
        this.benDefIdentifier = benDefIdentifier;
    }
}
