/*
 * CustomMessageTextCreateResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.blueexchange.response;

import java.util.List;

import com.wellpoint.wpd.common.blueexchange.vo.CustomMessageTextVO;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * CustomMessageTextCreateResponse is the class for getting response from business layer 
 * 
 * @see this class extends (com.wellpoint.wpd.common.framework.response.WPDResponse)
 *
 * 
 */
public class CustomMessageTextCreateResponse extends WPDResponse {

	private CustomMessageTextVO customMessageTextVO;

	private List validationMessages;

	private boolean validationSuccessFlag = true;

	/**
	 * @return Returns the validationMessages.
	 */
	public List getValidationMessages() {
		return validationMessages;
	}

	/**
	 * @param validationMessages
	 *            The validationMessages to set.
	 */
	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}

	/**
	 * @return Returns the customMessageTextVO.
	 */
	public CustomMessageTextVO getCustomMessageTextVO() {
		return customMessageTextVO;
	}

	/**
	 * @param customMessageTextVO
	 *            The customMessageTextVO to set.
	 */
	public void setCustomMessageTextVO(CustomMessageTextVO customMessageTextVO) {
		this.customMessageTextVO = customMessageTextVO;
	}

	/**
	 * @return Returns the validationSuccessFlag.
	 */
	public boolean isValidationSuccessFlag() {
		return validationSuccessFlag;
	}

	/**
	 * @param validationSuccessFlag
	 *            The validationSuccessFlag to set.
	 */
	public void setValidationSuccessFlag(boolean validationSuccessFlag) {
		this.validationSuccessFlag = validationSuccessFlag;
	}
}