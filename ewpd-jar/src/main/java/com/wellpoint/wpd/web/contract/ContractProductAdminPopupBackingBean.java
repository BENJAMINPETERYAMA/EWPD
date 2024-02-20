/*
 * ProductComponentPopupBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;

import java.util.List;

import com.wellpoint.wpd.common.contract.request.RetrieveContractProductAdminRequest;
import com.wellpoint.wpd.common.contract.response.RetrieveContractProductAdminResponse;
import com.wellpoint.wpd.common.product.request.RetrieveProductAdminRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 *         j
 * @version $Id: $
 */
public class ContractProductAdminPopupBackingBean extends ContractBackingBean {

    private List productAdminList;

    private boolean adminRetrieved;
    
    private String retrieveAllAdminRecords;

    
	/**
	 * @return Returns the retrieveAllAdminRecords.
	 */
	public String getRetrieveAllAdminRecords() {
		
		  if (!adminRetrieved) {
        	RetrieveContractProductAdminRequest retrieveContractProductAdminRequest = new RetrieveContractProductAdminRequest();
        	retrieveContractProductAdminRequest.setAction(RetrieveProductAdminRequest.PRODUCT_ADMIN_POPUP);
        	retrieveContractProductAdminRequest.setProductKey(super.getContractSession().getContractKeyObject().getProductId());
            RetrieveContractProductAdminResponse productAdminResponse = (RetrieveContractProductAdminResponse) executeService(retrieveContractProductAdminRequest);
            if (null != productAdminResponse) {
                this.productAdminList = productAdminResponse.getAdminList();
                adminRetrieved = true;
            }
            else 
            	this.productAdminList = null;
        }	
        	
		  this.setProductAdminList(productAdminList);
		return retrieveAllAdminRecords;
	}
	/**
	 * @param retrieveAllAdminRecords The retrieveAllAdminRecords to set.
	 */
	public void setRetrieveAllAdminRecords(String retrieveAllAdminRecords) {
		this.retrieveAllAdminRecords = retrieveAllAdminRecords;
	}
    /**
     * Returns the productAdmin
     * 
     * @return List productAdmin.
     */

    public List getProductAdminList() {
      
        return this.productAdminList;
    }


    /**
     * @param productAdminList
     *            The productAdminList to set.
     */
    public void setProductAdminList(List productAdminList) {
        this.productAdminList = productAdminList;
    }
}