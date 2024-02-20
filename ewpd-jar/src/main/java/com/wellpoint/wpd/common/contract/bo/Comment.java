/*
 * Comment.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;

import java.sql.Timestamp;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class Comment {
	
	private int commentSysId; 
	
	private int dateSegmentSysId;
	
	private String commentText;
	
	private String createdUser;
	
	private Timestamp createdTimeStamp;
	
	private String lastUpdatedUser;
	
	private Timestamp lastUpdatedTimeStamp;
	
	
	
	/**
	 * @return Returns the commentSysId.
	 */
	public int getCommentSysId() {
		return commentSysId;
	}
	/**
	 * @param commentSysId The commentSysId to set.
	 */
	public void setCommentSysId(int commentSysId) {
		this.commentSysId = commentSysId;
	}
	
	/**
	 * Returns the createdUser
	 * @return String createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * Sets the createdUser
	 * @param createdUser.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	/**
	 * Returns the commentText
	 * @return String commentText.
	 */
	public String getCommentText() {
		return commentText;
	}
	/**
	 * Sets the commentText
	 * @param commentText.
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	/**
	 * Returns the dateSegmentSysId
	 * @return int dateSegmentSysId.
	 */
	public int getDateSegmentSysId() {
		return dateSegmentSysId;
	}
	/**
	 * Sets the dateSegmentSysId
	 * @param dateSegmentSysId.
	 */
	public void setDateSegmentSysId(int dateSegmentSysId) {
		this.dateSegmentSysId = dateSegmentSysId;
	}
	
	
	/**
	 * @return Returns the lastUpdatedTimeStamp.
	 */
	public Timestamp getLastUpdatedTimeStamp() {
		return lastUpdatedTimeStamp;
	}
	/**
	 * @param lastUpdatedTimeStamp The lastUpdatedTimeStamp to set.
	 */
	public void setLastUpdatedTimeStamp(Timestamp lastUpdatedTimeStamp) {
		this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
	}
	/**
	 * Returns the lastUpdatedUser
	 * @return String lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * Sets the lastUpdatedUser
	 * @param lastUpdatedUser.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * @return Returns the createdTimeStamp.
	 */
	public Timestamp getCreatedTimeStamp() {
		return createdTimeStamp;
	}
	/**
	 * @param createdTimeStamp The createdTimeStamp to set.
	 */
	public void setCreatedTimeStamp(Timestamp createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}
}
