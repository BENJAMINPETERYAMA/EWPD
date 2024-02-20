/*
 * CustomMessageRetrieveRequest.java
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
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CustomMessageRetrieveRequest extends WPDRequest {

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

	/**
	 * Overriding validate() method
	 */
	public void validate() throws ValidationException {

	}
}