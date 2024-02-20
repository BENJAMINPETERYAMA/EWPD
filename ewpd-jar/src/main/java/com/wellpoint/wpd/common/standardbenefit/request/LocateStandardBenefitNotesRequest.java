/*
 * LocateStandardBenefitNotesRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LocateStandardBenefitNotesRequest extends WPDRequest {

    private int entityId;
	private String entityType;
	private int maxResultSize;
	//added 28thNov
	private String benefitDefinitionKey;
	
	
	
    /**
     * @return entityId
     * 
     * Returns the entityId.
     */
    public int getEntityId() {
        return entityId;
    }
    /**
     * @param entityId
     * 
     * Sets the entityId.
     */
    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }
	/**
	 * @return Returns the maxResultSize.
	 */
	public int getMaxResultSize() {
		return maxResultSize;
	}
	/**
	 * @param maxResultSize The maxResultSize to set.
	 */
	public void setMaxResultSize(int maxResultSize) {
		this.maxResultSize = maxResultSize;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return Returns the entityType.
	 */
	public String getEntityType() {
		return entityType;
	}
	/**
	 * @param entityType The entityType to set.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	/**
	 * @return Returns the benefitDefinitionKey.
	 */
	public String getBenefitDefinitionKey() {
		return benefitDefinitionKey;
	}
	/**
	 * @param benefitDefinitionKey The benefitDefinitionKey to set.
	 */
	public void setBenefitDefinitionKey(String benefitDefinitionKey) {
		this.benefitDefinitionKey = benefitDefinitionKey;
	}
}



