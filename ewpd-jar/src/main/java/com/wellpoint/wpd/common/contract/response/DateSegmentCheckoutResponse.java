/*
 * DateSegmentCheckoutResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * Response for Date Segment Checkout
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DateSegmentCheckoutResponse extends WPDResponse{
	
	private boolean isSuccess;
	private int dateSegmentId;
	

	/**
	 * @return Returns the isSuccess.
	 */
	public boolean isSuccess() {
		return isSuccess;
	}
	/**
	 * @param isSuccess The isSuccess to set.
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	/**
	 * @return Returns the dateSegmentId.
	 */
	public int getDateSegmentId() {
		return dateSegmentId;
	}
	/**
	 * @param dateSegmentId The dateSegmentId to set.
	 */
	public void setDateSegmentId(int dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}
}
