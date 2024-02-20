/*
 * RetrieveDateSegmentCommentRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author U16012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveDateSegmentCommentRequest extends ContractRequest {
	
	private String maxValue;
	
	
	
	/**
	 * @return Returns the maxValue.
	 */
	public String getMaxValue() {
		return maxValue;
	}
	/**
	 * @param maxValue The maxValue to set.
	 */
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

}
