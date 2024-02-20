/*
 * DomainItem.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.domain.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DomainItem {
    private int entitySystemId;
    private String itemId;
    private String itemDesc;
    private int questionnaireHrchySysId;
    private String functionalDomain;
    
    /**
     * Returns the entitySystemId
     * @return int entitySystemId.
     */
    public int getEntitySystemId() {
        return entitySystemId;
    }
    /**
     * Sets the entitySystemId
     * @param entitySystemId.
     */
    public void setEntitySystemId(int entitySystemId) {
        this.entitySystemId = entitySystemId;
    }
    /**
     * Returns the itemDesc
     * @return String itemDesc.
     */
    public String getItemDesc() {
        return itemDesc;
    }
    /**
     * Sets the itemDesc
     * @param itemDesc.
     */
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
    /**
     * Returns the itemId
     * @return String itemId.
     */
    public String getItemId() {
        return itemId;
    }
    /**
     * Sets the itemId
     * @param itemId.
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
	/**
	 * @return Returns the questionnaireHrchySysId.
	 */
	public int getQuestionnaireHrchySysId() {
		return questionnaireHrchySysId;
	}
	/**
	 * @param questionnaireHrchySysId The questionnaireHrchySysId to set.
	 */
	public void setQuestionnaireHrchySysId(int questionnaireHrchySysId) {
		this.questionnaireHrchySysId = questionnaireHrchySysId;
	}
	/**
	 * @return Returns the functionalDomain.
	 */
	public String getFunctionalDomain() {
		return functionalDomain;
	}
	/**
	 * @param functionalDomain The functionalDomain to set.
	 */
	public void setFunctionalDomain(String functionalDomain) {
		this.functionalDomain = functionalDomain;
	}
}
