/**
 * 
 */
package com.wellpoint.ets.ebx.ooamessage.util;

import java.util.Date;

/**
 * @author AF17775
 *
 */
public class MaintainOOAMessageRequest {

	private Date messageEffectiveDate=null;
	
	private Date messageExpiryDate=null;
	
	private String messageTextOne=null;

	private String messageTextTwo=null;
	
	private String messageTextThree=null;

	private String networkOrContractCode=null;
	
	private String exchangeIndicator=null;
	
	private boolean hasExchOrExplChanged;
	
	private String messageExempted=null;
	
	private String status=null;
	
	private String workFlowAssosciationStatus=null;
	
	private String Search = null;
	
	private String messageId;
	private String testInd;
	private String prodInd;
	private String userId;
	private String lstChgUserId;
	private Date lstChgTimestamp;
	private Date createdTimeStamp;
	
	
	private String currentStatus=null;

	private String comments=null;
	
	private String contractCdDisp = "none";
	
	private String exindOrMsgExmt;
	

	public String getExindOrMsgExmt() {
		return exindOrMsgExmt;
	}

	public void setExindOrMsgExmt(String exindOrMsgExmt) {
		this.exindOrMsgExmt = exindOrMsgExmt;
	}

	/**
	 * @return
	 */
	public String getSearch() {
		return Search;
	}

	/**
	 * @param search
	 */
	public void setSearch(String search) {
		Search = search;
	}
	
	/**
	 * @return
	 */
	public String getMessageId() {
		return messageId;
	}


	/**
	 * @param messageId
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return
	 */
	public String getWorkFlowAssosciationStatus() {
		return workFlowAssosciationStatus;
	}

	/**
	 * @param workFlowAssosciationStatus
	 */
	public void setWorkFlowAssosciationStatus(String workFlowAssosciationStatus) {
		this.workFlowAssosciationStatus = workFlowAssosciationStatus;
	}

	/**
	 * @return
	 */
	public String getCurrentStatus() {
		return currentStatus;
	}

	/**
	 * @param currentStatus
	 */
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	
	/**
	 * @return
	 */
	public String getMessageTextOne() {
		return messageTextOne;
	}

	/**
	 * @param messageTextOne
	 */
	public void setMessageTextOne(String messageTextOne) {
		this.messageTextOne = messageTextOne;
	}

	/**
	 * @return
	 */
	public String getMessageTextTwo() {
		return messageTextTwo;
	}

	/**
	 * @param messageTextTwo
	 */
	public void setMessageTextTwo(String messageTextTwo) {
		this.messageTextTwo = messageTextTwo;
	}

	/**
	 * @return
	 */
	public String getMessageTextThree() {
		return messageTextThree;
	}

	/**
	 * @param messageTextThree
	 */
	public void setMessageTextThree(String messageTextThree) {
		this.messageTextThree = messageTextThree;
	}	
	
	/**
	 * @return
	 */
	public String getMessageExempted() {
		return messageExempted;
	}

	/**
	 * @param messageExempted
	 */
	public void setMessageExempted(String messageExempted) {
		this.messageExempted = messageExempted;
	}

	/**
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 */
	public MaintainOOAMessageRequest(){
		
	}
	
	/**
	 * @param messageEffectiveDate
	 * @param messageExpiryDate
	 * @param messageTextOne
	 * @param messageTextTwo
	 * @param messageTextThree
	 * @param networkOrContractCode
	 * @param exchangeIndicator
	 * @param contractCdDisp
	 */
	public MaintainOOAMessageRequest(Date messageEffectiveDate,
			Date messageExpiryDate, String messageTextOne,
			String messageTextTwo, String messageTextThree,
			String networkOrContractCode, String exchangeIndicator,String contractCdDisp) {
		super();
		this.messageEffectiveDate = messageEffectiveDate;
		this.messageExpiryDate = messageExpiryDate;
		this.messageTextOne = messageTextOne;
		this.messageTextTwo = messageTextTwo;
		this.messageTextThree = messageTextThree;
		this.networkOrContractCode = networkOrContractCode;
		this.exchangeIndicator = exchangeIndicator;
		this.contractCdDisp = contractCdDisp;
	}

	/**
	 * @return the messageEffectiveDate
	 */
	public Date getMessageEffectiveDate() {
		return messageEffectiveDate;
	}

	/**
	 * @param messageEffectiveDate the messageEffectiveDate to set
	 */
	public void setMessageEffectiveDate(Date messageEffectiveDate) {
		this.messageEffectiveDate = messageEffectiveDate;
	}
	public String getTestInd() {
		return testInd;
	}

	public void setTestInd(String testInd) {
		this.testInd = testInd;
	}

	public String getProdInd() {
		return prodInd;
	}

	public void setProdInd(String prodInd) {
		this.prodInd = prodInd;
	}

	/**
	 * @return the messageExpiryDate
	 */
	public Date getMessageExpiryDate() {
		return messageExpiryDate;
	}

	/**
	 * @param messageExpiryDate the messageExpiryDate to set
	 */
	public void setMessageExpiryDate(Date messageExpiryDate) {
		this.messageExpiryDate = messageExpiryDate;
	}


	/**
	 * @return the networkOrContractCode
	 */
	public String getNetworkOrContractCode() {
		return networkOrContractCode;
	}

	/**
	 * @param networkOrContractCode the networkOrContractCode to set
	 */
	public void setNetworkOrContractCode(String networkOrContractCode) {
		this.networkOrContractCode = networkOrContractCode;
	}

	/**
	 * @return the exchangeIndicator
	 */
	public String getExchangeIndicator() {
		return exchangeIndicator;
	}

	/**
	 * @param exchangeIndicator the exchangeIndicator to set
	 */
	public void setExchangeIndicator(String exchangeIndicator) {
		this.exchangeIndicator = exchangeIndicator;
	}

	/**
	 * @return
	 */
	public String getContractCdDisp() {
		return contractCdDisp;
	}

	/**
	 * @param contractCdDisp
	 */
	public void setContractCdDisp(String contractCdDisp) {
		this.contractCdDisp = contractCdDisp;
	}

	/**
	 * @return the hasExchOrExplChanged
	 */
	public final boolean isHasExchOrExplChanged() {
		return hasExchOrExplChanged;
	}

	/**
	 * @param hasExchOrExplChanged the hasExchOrExplChanged to set
	 */
	public final void setHasExchOrExplChanged(boolean hasExchOrExplChanged) {
		this.hasExchOrExplChanged = hasExchOrExplChanged;
	}

	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}	
	
	
	public String getLstChgUserId() {
		return lstChgUserId;
	}

	public void setLstChgUserId(String lstChgUserId) {
		this.lstChgUserId = lstChgUserId;
	}

	public Date getLstChgTimestamp() {
		return lstChgTimestamp;
	}

	public void setLstChgTimestamp(Date lstChgTimestamp) {
		this.lstChgTimestamp = lstChgTimestamp;
	}

	public Date getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	public void setCreatedTimeStamp(Date createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

	
}
