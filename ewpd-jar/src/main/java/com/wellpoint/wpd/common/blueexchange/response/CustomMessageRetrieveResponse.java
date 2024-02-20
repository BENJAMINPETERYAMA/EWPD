/*
 * CustomMessageRetrieveResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.blueexchange.response;

import com.wellpoint.wpd.common.blueexchange.vo.CustomMessageTextVO;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * CustomMessageTextCreateResponse is the class for getting response from business layer 
 * 
 * @see this class extends (com.wellpoint.wpd.common.framework.response.WPDResponse)
 */
public class CustomMessageRetrieveResponse extends WPDResponse{

	private CustomMessageTextVO messageTextVO;

	/**
	 * @return Returns the messageTextVO.
	 */
	public CustomMessageTextVO getMessageTextVO() {
		return messageTextVO;
	}

	/**
	 * @param messageTextVO The messageTextVO to set.
	 */
	public void setMessageTextVO(CustomMessageTextVO messageTextVO) {
		this.messageTextVO = messageTextVO;
	}
}