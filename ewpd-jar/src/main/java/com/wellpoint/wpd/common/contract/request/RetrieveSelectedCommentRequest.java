/*
 * RetrieveSelectedCommentRequest.java
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
public class RetrieveSelectedCommentRequest extends ContractRequest {
	
	private int commentId;
	
	private int dateSegmentId;
	
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

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
	/**
	 * @return Returns the commentId.
	 */
	public int getCommentId() {
		return commentId;
	}
	/**
	 * @param commentId The commentId to set.
	 */
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
}
