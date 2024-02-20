/*
 * LocateMandateRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.mandate.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.mandate.vo.MandateVO;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LocateMandateRequest extends WPDRequest{
	
	private MandateVO mandateVO;
	
	private int maxSize;
	
	public LocateMandateRequest(){
		mandateVO = new MandateVO();
	}
	
	
	
	/**
	 * Returns the mandateVO
	 * @return MadateVO mandateVO.
	 */
	public MandateVO getMandateVO() {
		return mandateVO;
	}
	/**
	 * Sets the mandateVO
	 * @param mandateVO.
	 */
	public void setMandateVO(MandateVO mandateVO) {
		this.mandateVO = mandateVO;
	}
	
	
	public void validate() throws ValidationException {

	}

	/**
	 * @return Returns the maxSize.
	 */
	public int getMaxSize() {
		return maxSize;
	}
	/**
	 * @param maxSize The maxSize to set.
	 */
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
}
