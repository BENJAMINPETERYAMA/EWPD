/*
 * AdminMethodSPSValidationRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.adminmethod.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminMethodSPSValidationRequest extends WPDRequest{

	
	private int contractSysId;
	

	
	/**
	 * @return Returns the contractSysId.
	 */
	public int getContractSysId() {
		return contractSysId;
	}
	/**
	 * @param contractSysId The contractSysId to set.
	 */
	public void setContractSysId(int contractSysId) {
		this.contractSysId = contractSysId;
	}
	/**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
