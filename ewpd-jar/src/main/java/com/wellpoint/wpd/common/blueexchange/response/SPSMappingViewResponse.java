/*
 * SPSMappingViewResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.blueexchange.response;

import java.util.List;

import com.wellpoint.wpd.common.blueexchange.vo.SPSMappingVO;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SPSMappingViewResponse extends WPDResponse{
	
	private SPSMappingVO mappingVO;
	
	/**
	 * @return Returns the validationMessages.
	 */
	public List getValidationMessages() {
		return validationMessages;
	}
	/**
	 * @param validationMessages The validationMessages to set.
	 */
	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}
	private List validationMessages;

	
	/**
	 * @return Returns the mappingVO.
	 */
	public SPSMappingVO getMappingVO() {
		return mappingVO;
	}
	/**
	 * @param mappingVO The mappingVO to set.
	 */
	public void setMappingVO(SPSMappingVO mappingVO) {
		this.mappingVO = mappingVO;
	}
}
