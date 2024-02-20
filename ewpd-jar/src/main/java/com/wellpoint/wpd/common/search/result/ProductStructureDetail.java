/*
 * ProductStructureDetail.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.search.result;

import java.util.Date;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: ProductStructureDetail.java 24880 2007-06-22 05:49:55Z u12046 $
 */
public class ProductStructureDetail extends ObjectDetail {
    private String name;

    private Date effectiveDate;

    private int version;

    private Date expiryDate;
    
    private String status;

    ProductStructureIdentifier psi;



	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return Returns the effectiveDate.
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * @param effectiveDate The effectiveDate to set.
	 */
	public void setEffectiveDate(Date effeciveDate) {
		this.effectiveDate = effeciveDate;
	}
	/**
	 * @return Returns the expiryDate.
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate The expiryDate to set.
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the version.
     */
    public int getVersion() {
        return version;
    }

    /**
     * @param version
     *            The version to set.
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * @see com.wellpoint.wpd.common.search.result.ObjectDetail#getIdentifier()
     * @return
     */
    public ObjectIdentifier getIdentifier() {
        return psi;
    }

    /**
     * @see com.wellpoint.wpd.common.search.result.ObjectDetail#setIdentifier(com.wellpoint.wpd.common.search.result.ObjectIdentifier)
     * @param identifier
     */
    public void setIdentifier(ObjectIdentifier identifier) {
        if (identifier != null
                && identifier instanceof ProductStructureIdentifier) {
            psi = (ProductStructureIdentifier) identifier;
        } else {
            throw new IllegalArgumentException(
                    "setIdentifier method in ProductStructureDetail.  Parameter is null or of incorrect type "
                            + identifier);
        }

    }
}