/*
 * SPSMappingCreateResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.blueexchange.response;


import java.util.List;

import com.wellpoint.wpd.common.blueexchange.bo.SPSMappingBO;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SPSMappingCreateResponse extends WPDResponse {
	
	private SPSMappingBO mappingBO;
	private List validationMessage;
	private boolean validationSuccess;
	

	/**
	 * @return Returns the mappingBO.
	 */
	public SPSMappingBO getMappingBO() {
		return mappingBO;
	}
	/**
	 * @param mappingBO The mappingBO to set.
	 */
	public void setMappingBO(SPSMappingBO mappingBO) {
		this.mappingBO = mappingBO;
	}
	/**
	 * @return Returns the validationMessage.
	 */
	public List getValidationMessage() {
		return validationMessage;
	}
	/**
	 * @param validationMessage The validationMessage to set.
	 */
	public void setValidationMessage(List validationMessage) {
		this.validationMessage = validationMessage;
	}
	/**
	 * @return Returns the validationSuccess.
	 */
	public boolean isValidationSuccess() {
		return validationSuccess;
	}
	/**
	 * @param validationSuccess The validationSuccess to set.
	 */
	public void setValidationSuccess(boolean validationSuccess) {
		this.validationSuccess = validationSuccess;
	}
}
