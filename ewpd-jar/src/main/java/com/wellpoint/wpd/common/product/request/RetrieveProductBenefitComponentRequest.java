/*
 * RetrieveProductBenefitComponentRequest.java
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
//import java.util.Map;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */


public class RetrieveProductBenefitComponentRequest extends ProductRequest{
	
	public static final int PRODUCT_BENEFIT_ADDED = 1;
    
    public static final int PRODUCT_BENEFIT_POPUP = 2;
    
    public static final int PRODUCT_BENEFIT_COMPONENT_RETRIEVE = 3;
    
    public static final int PRODUCT_BENEFIT_RETRIEVE = 4;
    
    public static final int PRODUCT_BENEFIT_DETAILS_UPDATE = 5;
    
    public static final int PRODUCT_BENEFIT_RETRIEVE_ALL_DETAILS = 6;
    
    private int action;
    
    private int benefitComponentId;
    
    private List benefitDetailsList;
    
    private int productId;
    
    private String productType;
    
    private boolean showHiddenStatus;
    
    private boolean changed;
    
    private List changedIds;
    
    private String bcompName;
	
    /** Object for setting the flag for hide unhide benefits :: eWPD Stabilization 2011 **/
    private Map benefitHideUnhideFlagMap;
    
	/**
	 * @return Returns the changed.
	 */
	public boolean isChanged() {
		return changed;
	}
	/**
	 * @param changed The changed to set.
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;
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
	 * @return Returns the benefitDetailsList.
	 */
	public List getBenefitDetailsList() {
		return benefitDetailsList;
	}
	/**
	 * @param benefitDetailsList The benefitDetailsList to set.
	 */
	public void setBenefitDetailsList(List benefitDetailsList) {
		this.benefitDetailsList = benefitDetailsList;
	}
	  /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
    }
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
     * Returns the benefitComponentId
     * @return int benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }
    
    /**
     * Sets the benefitComponentId
     * @param benefitComponentId.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }
    
	/**
	 * @return Returns the showHiddenStatus.
	 */
	public boolean isShowHiddenStatus() {
		return showHiddenStatus;
	}
	/**
	 * @param showHiddenStatus The showHiddenStatus to set.
	 */
	public void setShowHiddenStatus(boolean showHiddenStatus) {
		this.showHiddenStatus = showHiddenStatus;
	}
	/**
	 * @return Returns the productType.
	 */
	public String getProductType() {
		return productType;
	}
	/**
	 * @param productType The productType to set.
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * @return Returns the bcompName.
	 */
	public String getBcompName() {
		return bcompName;
	}
	/**
	 * @param bcompName The bcompName to set.
	 */
	public void setBcompName(String bcompName) {
		this.bcompName = bcompName;
	}
	/**Getter and Setter method for Benefit Hide Unhide Map :: eWPD Stabilization 2011**/
	public Map getBenefitHideUnhideFlagMap() {
		return benefitHideUnhideFlagMap;
	}
	
	public void setBenefitHideUnhideFlagMap(Map benefitHideUnhideFlagMap) {
		this.benefitHideUnhideFlagMap = benefitHideUnhideFlagMap;
	}
	/** end :: by eWPD Stabilization 2011**/	
}
