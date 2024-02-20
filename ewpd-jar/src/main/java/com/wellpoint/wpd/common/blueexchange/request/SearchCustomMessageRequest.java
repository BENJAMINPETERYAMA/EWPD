/*
 * SearchCustomMessageRequest.java
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
public class SearchCustomMessageRequest extends WPDRequest {
	
	private int action;
	
	private CustomMessageTextVO customMessageTextVO ;
	
	private String statusCd;

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}
	
	/**
	 * @return Returns the action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(int action) {
		this.action = action;
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
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
}
