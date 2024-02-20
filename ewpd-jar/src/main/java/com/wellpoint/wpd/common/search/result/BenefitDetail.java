/*
 * BenefitDetail.java
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
public class BenefitDetail extends ObjectDetail {
	private String name;
	private int version;
	
	
	private BenefitIdentifier bi;
	private String status;
	private String description;

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
    /**
     * @see com.wellpoint.wpd.common.search.result.ObjectDetail#getIdentifier()
     * @return
     */
    public ObjectIdentifier getIdentifier() {
        return bi;
    }
    /**
     * @see com.wellpoint.wpd.common.search.result.ObjectDetail#setIdentifier(com.wellpoint.wpd.common.search.result.ObjectIdentifier)
     * @param identifier
     */
    public void setIdentifier(ObjectIdentifier identifier) {
        if(identifier != null && identifier instanceof BenefitIdentifier){
            bi = (BenefitIdentifier)identifier;
        }else{
            throw new IllegalArgumentException(
                    "setIdentifier method in BenefitDetail.  Parameter is null or of incorrect type "
                            + identifier);
        }        
        
    }
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
}
