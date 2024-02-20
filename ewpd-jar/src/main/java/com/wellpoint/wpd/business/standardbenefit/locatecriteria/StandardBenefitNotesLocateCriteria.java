/*
 * StandardBenefitNotesLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.standardbenefit.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class StandardBenefitNotesLocateCriteria extends LocateCriteria {
    
    private String entityType;

    private int entityId;
    
    //added 28thNov
    private String benefitDefinitionKey;


    /**
     * @return Returns the entityType.
     */
    public String getEntityType() {
        return entityType;
    }


    /**
     * @param entityType
     *            The entityType to set.
     */
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }


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
