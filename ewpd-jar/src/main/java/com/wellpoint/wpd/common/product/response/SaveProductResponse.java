/*
 * SaveProductResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.response;

import java.util.List;

import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.product.bo.ProductBO;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveProductResponse extends WPDResponse {
    private boolean valid;
    private boolean success;
    private ProductBO productBO;
    private DomainDetail domainDetail;
    private boolean productStructureChanged;
    private boolean keyChanged;
    private int condition;
    public static final int CHECKIN_SUCCESS = 1;
    public static final int VALIDATION_WAIT = 2;
    public static final int VALIDATION_RESULTS = 3;
    public static final int SPS_VALIDATION_ERROR = 4;
    public static final int DO_EBX_WEBSERVICE_PROCESS = 60;
    public static final int DO_EBX_WEBSERVICE_PROCESS_FAILURE = 61;
    public static final int DO_CARVEDOUT_PROCESS = 62; // SSCR -17571 Tab impl
    private List deletedRulesList = null;
    private List unCodedRulesList =  null;

    /**
     * Returns the productBO
     * @return ProductBO productBO.
     */
    public ProductBO getProductBO() {
        return productBO;
    }
    
    /**
     * Sets the productBO
     * @param productBO.
     */
    public void setProductBO(ProductBO productBO) {
        this.productBO = productBO;
    }
    
    /**
     * Returns the success
     * @return boolean success.
     */
    public boolean isSuccess() {
        return success;
    }
    
    /**
     * Sets the success
     * @param success.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    /**
     * Returns the valid
     * @return boolean valid.
     */
    public boolean isValid() {
        return valid;
    }
    
    /**
     * Sets the valid
     * @param valid.
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }
    /**
     * Returns the domainDetail
     * @return DomainDetail domainDetail.
     */
    public DomainDetail getDomainDetail() {
        return domainDetail;
    }
    /**
     * Sets the domainDetail
     * @param domainDetail.
     */
    public void setDomainDetail(DomainDetail domainDetail) {
        this.domainDetail = domainDetail;
    }
    /**
     * Returns the productStructureChanged
     * @return boolean productStructureChanged.
     */
    public boolean isProductStructureChanged() {
        return productStructureChanged;
    }
    /**
     * Sets the productStructureChanged
     * @param productStructureChanged.
     */
    public void setProductStructureChanged(boolean productStructureChanged) {
        this.productStructureChanged = productStructureChanged;
    }
    /**
     * Returns the keyChanged
     * @return boolean keyChanged.
     */
    public boolean isKeyChanged() {
        return keyChanged;
    }
    /**
     * Sets the keyChanged
     * @param keyChanged.
     */
    public void setKeyChanged(boolean keyChanged) {
        this.keyChanged = keyChanged;
    }
	/**
	 * @return Returns the condition.
	 */
	public int getCondition() {
		return condition;
	}
	/**
	 * @param condition The condition to set.
	 */
	public void setCondition(int condition) {
		this.condition = condition;
	}
	/**
	 * @return Returns the deletedRulesList.
	 */
	public List getDeletedRulesList() {
		return deletedRulesList;
	}
	/**
	 * @param deletedRulesList The deletedRulesList to set.
	 */
	public void setDeletedRulesList(List deletedRulesList) {
		this.deletedRulesList = deletedRulesList;
	}
	/**
	 * @return Returns the unCodedRulesList.
	 */
	public List getUnCodedRulesList() {
		return unCodedRulesList;
	}
	/**
	 * @param unCodedRulesList The unCodedRulesList to set.
	 */
	public void setUnCodedRulesList(List unCodedRulesList) {
		this.unCodedRulesList = unCodedRulesList;
	}
}
