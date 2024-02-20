/*
 * ProductComponentPopupBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.product;

import com.wellpoint.wpd.common.product.request.RetrieveProductBenefitComponentRequest;
import com.wellpoint.wpd.common.product.response.ProductBenefitComponentResponse;

import java.util.List;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductComponentPopupBackingBean extends ProductBackingBean{
    
    private List productComponentList;
    
    private boolean componentsRetrieved;
    
    private String retrieveProductComponentRecords;

    
	/**
	 * @return Returns the retrieveProductComponentRecords.
	 */
	public String getRetrieveProductComponentRecords() {
		
		 if(!componentsRetrieved) {
	    	RetrieveProductBenefitComponentRequest productBenefitComponentRequest = new RetrieveProductBenefitComponentRequest();
	    	productBenefitComponentRequest.setAction(RetrieveProductBenefitComponentRequest.PRODUCT_BENEFIT_POPUP);
	    	ProductBenefitComponentResponse productBenefitComponentResponse = 
	        	(ProductBenefitComponentResponse)executeService(productBenefitComponentRequest);
	    	if(null!=productBenefitComponentResponse){
	    		this.productComponentList = productBenefitComponentResponse.getBenefitComponentList();
	    		componentsRetrieved = true;
	    	}
	    	else productComponentList = null;
	    	
	    	this.setProductComponentList(productComponentList);
	    		
        }
		return retrieveProductComponentRecords;
	}
	/**
	 * @param retrieveProductComponentRecords The retrieveProductComponentRecords to set.
	 */
	public void setRetrieveProductComponentRecords(
			String retrieveProductComponentRecords) {
		this.retrieveProductComponentRecords = retrieveProductComponentRecords;
	}
    /**
     * Returns the productComponentList
     * @return List 
     */

    public List getProductComponentList() {
       
        return this.productComponentList;
    }
    /**
     * Sets the productComponent
     * @param productComponent.
     */

    public void setProductComponentList(List productComponentList) {
    	this.productComponentList = productComponentList;
    }
    
}
