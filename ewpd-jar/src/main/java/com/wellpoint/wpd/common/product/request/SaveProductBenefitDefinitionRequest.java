/*
 * SaveProductBenefitDefinitionRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.request;

import java.util.List;
import java.util.Map;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveProductBenefitDefinitionRequest extends ProductRequest {
    
    private List benefitLinesList;

    private List deleteBenefitLevelList;
    
    private int benefitComponentId;
    
    private int benefitId;
    
    private String benefitHideFlag;//for setting the status of benefit 
    	
	private int productId;
	
	private List changedIds;
	
	private boolean hiddenFlagChanged;
	
	private String benefitComponentName;
	
	private List benefitTierDefinitionList;
	
	private List benefitTierLevelList;
	
	private Map mapWithOldCriteriaValues;
	
	private Map mapWithNewCriteriaValues;	
	
	private Map mapWithOldValues;
	
	private Map mapWithNewValues;
    
    
	public List getBenefitTierDefinitionList() {
		return benefitTierDefinitionList;
	}
	public void setBenefitTierDefinitionList(List benefitTierDefinitionList) {
		this.benefitTierDefinitionList = benefitTierDefinitionList;
	}
	public List getBenefitTierLevelList() {
		return benefitTierLevelList;
	}
	public void setBenefitTierLevelList(List benefitTierLevelList) {
		this.benefitTierLevelList = benefitTierLevelList;
	}
	/**
	 * @return Returns the changedIds.
	 */
	public List getChangedIds() {
		return changedIds;
	}
	/**
	 * @param changedIds The changedIds to set.
	 */
	public void setChangedIds(List changedIds) {
		this.changedIds = changedIds;
	}
	/**
	 * @return Returns the hiddenFlagChanged.
	 */
	public boolean isHiddenFlagChanged() {
		return hiddenFlagChanged;
	}
	/**
	 * @param hiddenFlagChanged The hiddenFlagChanged to set.
	 */
	public void setHiddenFlagChanged(boolean hiddenFlagChanged) {
		this.hiddenFlagChanged = hiddenFlagChanged;
	}
	/**
	 * @return Returns the productId.
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param productId The productId to set.
	 */
	public void setProductId(int productId) {
		this.productId = productId;
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
	 * @return Returns the deleteBenefitLevelList.
	 */
	public List getDeleteBenefitLevelList() {
		return deleteBenefitLevelList;
	}
	/**
	 * @param deleteBenefitLevelList The deleteBenefitLevelList to set.
	 */
	public void setDeleteBenefitLevelList(List deleteBenefitLevelList) {
		this.deleteBenefitLevelList = deleteBenefitLevelList;
	}
    /**
     * Returns the benefitLinesList
     * @return List benefitLinesList.
     */
    public List getBenefitLinesList() {
        return benefitLinesList;
    }
    /**
     * Sets the benefitLinesList
     * @param benefitLinesList.
     */
    public void setBenefitLinesList(List benefitLinesList) {
        this.benefitLinesList = benefitLinesList;
    }
	/**
	 * @return Returns the benefitHideFlag.
	 */
	public String getBenefitHideFlag() {
		return benefitHideFlag;
	}
	/**
	 * @param benefitHideFlag The benefitHideFlag to set.
	 */
	public void setBenefitHideFlag(String benefitHideFlag) {
		this.benefitHideFlag = benefitHideFlag;
	}
	/**
	 * @return Returns the benefitId.
	 */
	public int getBenefitId() {
		return benefitId;
	}
	/**
	 * @param benefitId The benefitId to set.
	 */
	public void setBenefitId(int benefitId) {
		this.benefitId = benefitId;
	}
	/**
	 * @return Returns the benefitComponentName.
	 */
	public String getBenefitComponentName() {
		return benefitComponentName;
	}
	/**
	 * @param benefitComponentName The benefitComponentName to set.
	 */
	public void setBenefitComponentName(String benefitComponentName) {
		this.benefitComponentName = benefitComponentName;
	}
	/**
	 * @return Returns the mpaWithOldValues.
	 */
	public Map getMapWithOldValues() {
		return mapWithOldValues;
	}
	/**
	 * @param mpaWithOldValues The mpaWithOldValues to set.
	 */
	public void setMpaWithOldValues(Map mapWithOldValues) {
		this.mapWithOldValues = mapWithOldValues;
	}
	/**
	 * @return Returns the mpaWithNewValues.
	 */
	public Map getMapWithNewValues() {
		return mapWithNewValues;
	}
	/**
	 * @param mpaWithNewValues The mpaWithNewValues to set.
	 */
	public void setMapWithNewValues(Map mapWithNewValues) {
		this.mapWithNewValues = mapWithNewValues;
	}
	/**
	 * @return Returns the mapWithOldCriteriaValues.
	 */
	public Map getMapWithOldCriteriaValues() {
		return mapWithOldCriteriaValues;
	}
	/**
	 * @param mapWithOldCriteriaValues The mapWithOldCriteriaValues to set.
	 */
	public void setMapWithOldCriteriaValues(Map mapWithOldCriteriaValues) {
		this.mapWithOldCriteriaValues = mapWithOldCriteriaValues;
	}
	/**
	 * @return Returns the mapWithNewCriteriaValues.
	 */
	public Map getMapWithNewCriteriaValues() {
		return mapWithNewCriteriaValues;
	}
	/**
	 * @param mapWithNewCriteriaValues The mapWithNewCriteriaValues to set.
	 */
	public void setMapWithNewCriteriaValues(Map mapWithNewCriteriaValues) {
		this.mapWithNewCriteriaValues = mapWithNewCriteriaValues;
	}
}
