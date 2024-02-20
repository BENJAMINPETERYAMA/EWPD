/*
 * SaveDateSegmentCommentRequest.java
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
public class SaveDateSegmentCommentRequest extends ContractRequest {
	
	private String newComments;
	private boolean commentUpdateReqd = true;
	
	public boolean isCommentUpdateReqd() {
		return commentUpdateReqd;
	}

	public void setCommentUpdateReqd(boolean commentUpdateReqd) {
		this.commentUpdateReqd = commentUpdateReqd;
	}

	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	/**
	 * @return Returns the newComments.
	 */
	public String getNewComments() {
		return newComments;
	}
	/**
	 * @param newComments The newComments to set.
	 */
	public void setNewComments(String newComments) {
		this.newComments = newComments;
	}

}
