/*
 * DeleteCustomMessageRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.blueexchange.request;

import com.wellpoint.wpd.common.blueexchange.vo.CustomMessageTextVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * DeleteCustomMessageRequest is the class for creating request to call business layer 
 * 
 * @see this class extends (com.wellpoint.wpd.common.framework.request.WPDRequest)
 */
public class DeleteCustomMessageRequest extends WPDRequest{
	
	private CustomMessageTextVO customMessageTextVO;
	
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return Returns the customMessageTextVO.
	 */
	public CustomMessageTextVO getCustomMessageTextVO() {
		return customMessageTextVO;
	}
	/**
	 * @param customMessageTextVO The customMessageTextVO to set.
	 */
	public void setCustomMessageTextVO(CustomMessageTextVO customMessageTextVO) {
		this.customMessageTextVO = customMessageTextVO;
	}
}
