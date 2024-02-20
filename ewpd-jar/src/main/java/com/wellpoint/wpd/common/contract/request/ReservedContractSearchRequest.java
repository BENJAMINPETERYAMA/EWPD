/*
 * ReservedContractSearchRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ReservedContractSearchRequest  extends WPDRequest {

    private String contractId;
    
    private List lob;
    
    private List businessEntity;
    
    private List businessGroup;
    
    private List marketBusinessUnit;
    
    /* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
	
	
	
    
    /**
     * Returns the businessGroup
     * @return List businessGroup.
     */
    public List getBusinessGroup() {
        return businessGroup;
    }
    /**
     * Sets the businessGroup
     * @param businessGroup.
     */
    public void setBusinessGroup(List businessGroup) {
        this.businessGroup = businessGroup;
    }
    /**
     * Returns the businessEntity
     * @return List businessEntity.
     */
    public List getBusinessEntity() {
        return businessEntity;
    }
    /**
     * Sets the businessEntity
     * @param businessEntity.
     */
    public void setBusinessEntity(List businessEntity) {
        this.businessEntity = businessEntity;
    }
    /**
     * Returns the contractId
     * @return String contractId.
     */
    public String getContractId() {
        return contractId;
    }
    /**
     * Sets the contractId
     * @param contractId.
     */
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    /**
     * Returns the lob
     * @return List lob.
     */
    public List getLob() {
        return lob;
    }
    /**
     * Sets the lob
     * @param lob.
     */
    public void setLob(List lob) {
        this.lob = lob;
    }
	/**
	 * @return Returns the marketBusinessUnit.
	 */
	public List getMarketBusinessUnit() {
		return marketBusinessUnit;
	}
	/**
	 * @param marketBusinessUnit The marketBusinessUnit to set.
	 */
	public void setMarketBusinessUnit(List marketBusinessUnit) {
		this.marketBusinessUnit = marketBusinessUnit;
	}
}
