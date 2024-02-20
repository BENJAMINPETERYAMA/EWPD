/*
 * LookupAdminQuestionRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.lookup.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.lookup.vo.LookupAdminQuestionVO;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LookupAdminQuestionRequest extends WPDRequest{
	
	private LookupAdminQuestionVO lookupAdminQuestionVO;
	
	public LookupAdminQuestionRequest(){
		lookupAdminQuestionVO=new LookupAdminQuestionVO();
	}
	
	public void validate() throws ValidationException {
	    
	}
	
	

	/**
	 * Returns the lookupAdminQuestionVO
	 * @return LookupAdminQuestionVO lookupAdminQuestionVO.
	 */
	public LookupAdminQuestionVO getLookupAdminQuestionVO() {
		return lookupAdminQuestionVO;
	}
	/**
	 * Sets the lookupAdminQuestionVO
	 * @param lookupAdminQuestionVO.
	 */
	public void setLookupAdminQuestionVO(
			LookupAdminQuestionVO lookupAdminQuestionVO) {
		this.lookupAdminQuestionVO = lookupAdminQuestionVO;
	}
}
