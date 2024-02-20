/*
 * SearchSPSMappingRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.blueexchange.request;


import com.wellpoint.wpd.common.blueexchange.vo.SPSMappingSearchVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SearchSPSMappingRequest extends WPDRequest {

	
	private SPSMappingSearchVO sPSMappingSearchVO;
	
	private int action;
	private String status;
	
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	
	
	/**
	 * @return Returns the sPSMappingSearchVO.
	 */
	public SPSMappingSearchVO getSPSMappingSearchVO() {
		return sPSMappingSearchVO;
	}
	/**
	 * @param mappingSearchVO The sPSMappingSearchVO to set.
	 */
	public void setSPSMappingSearchVO(SPSMappingSearchVO mappingSearchVO) {
		sPSMappingSearchVO = mappingSearchVO;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
