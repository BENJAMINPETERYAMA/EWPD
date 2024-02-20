package com.wellpoint.wpd.common.blueexchange.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;

public class DeletedCustomMessageTextBO extends BusinessObject{
	
	private String headerRuleId;
	private String spsParameterId;
	private String messageText;
	private String createdUser;
	private String createdTimeStamp;
    private String updatedUser;
	private String UpdatedTimeStamp;
	private String feedRunFlag;
	
	public boolean equals(BusinessObject object) {
		
		return false;
	}

	public int hashCode() {
		
		return 0;
	}

	public String toString() {
	
		return null;
	}

	public String getFeedRunFlag() {
		return feedRunFlag;
	}

	public void setFeedRunFlag(String feedRunFlag) {
		this.feedRunFlag = feedRunFlag;
	}

	public String getHeaderRuleId() {
		return headerRuleId;
	}

	public void setHeaderRuleId(String headerRuleId) {
		this.headerRuleId = headerRuleId;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public String getSpsParameterId() {
		return spsParameterId;
	}

	public void setSpsParameterId(String spsParameterId) {
		this.spsParameterId = spsParameterId;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	public String getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	public void setCreatedTimeStamp(String createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

	public String getUpdatedTimeStamp() {
		return UpdatedTimeStamp;
	}
/**
 * 
 * @param updatedTimeStamp
 */
	public void setUpdatedTimeStamp(String updatedTimeStamp) {
		UpdatedTimeStamp = updatedTimeStamp;
	}

}
