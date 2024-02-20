/*
 * SPSMappingCreateRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.blueexchange.request;


import java.util.List;

import com.wellpoint.wpd.common.blueexchange.vo.SPSMappingVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SPSMappingCreateRequest extends WPDRequest {

    private SPSMappingVO spsMappingVO;
	private List validationMessage;
	
	/**
	 * @return Returns the spsMappingVO.
	 */
	public SPSMappingVO getSpsMappingVO() {
		return spsMappingVO;
	}
	/**
	 * @param spsMappingVO The spsMappingVO to set.
	 */
	public void setSpsMappingVO(SPSMappingVO spsMappingVO) {
		this.spsMappingVO = spsMappingVO;
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
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

}
