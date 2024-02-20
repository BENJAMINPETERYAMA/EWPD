/*
 * ContractComponentNotesLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.contract.locatecriteria;

import java.util.List;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractComponentNotesLocateCriteria  extends LocateCriteria {
    
    private int primaryEntityId;
    
    private String primaryEntityType;
    
    private int secondaryEntityId;
    
    private String secondaryEntityType;
    
    private int benefitComponentId;
    
    private List entityIdsList;
    
    private int action;

	/**
	 * @return Returns the action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	/**
	 * @return Returns the benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * @param benefitComponentId The benefitComponentId to set.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
	/**
	 * @return Returns the primaryEntityId.
	 */
	public int getPrimaryEntityId() {
		return primaryEntityId;
	}
	/**
	 * @param primaryEntityId The primaryEntityId to set.
	 */
	public void setPrimaryEntityId(int primaryEntityId) {
		this.primaryEntityId = primaryEntityId;
	}
	/**
	 * @return Returns the primaryEntityType.
	 */
	public String getPrimaryEntityType() {
		return primaryEntityType;
	}
	/**
	 * @param primaryEntityType The primaryEntityType to set.
	 */
	public void setPrimaryEntityType(String primaryEntityType) {
		this.primaryEntityType = primaryEntityType;
	}
	/**
	 * @return Returns the secondaryEntityId.
	 */
	public int getSecondaryEntityId() {
		return secondaryEntityId;
	}
	/**
	 * @param secondaryEntityId The secondaryEntityId to set.
	 */
	public void setSecondaryEntityId(int secondaryEntityId) {
		this.secondaryEntityId = secondaryEntityId;
	}
	/**
	 * @return Returns the secondaryEntityType.
	 */
	public String getSecondaryEntityType() {
		return secondaryEntityType;
	}
	/**
	 * @param secondaryEntityType The secondaryEntityType to set.
	 */
	public void setSecondaryEntityType(String secondaryEntityType) {
		this.secondaryEntityType = secondaryEntityType;
	}

	/**
	 * @return Returns the entityIdsList.
	 */
	public List getEntityIdsList() {
		return entityIdsList;
	}
	/**
	 * @param entityIdsList The entityIdsList to set.
	 */
	public void setEntityIdsList(List entityIdsList) {
		this.entityIdsList = entityIdsList;
	}
}
