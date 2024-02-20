/*
 * <ErrorCodeVO.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.referencedata.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author UST-GLOBAL ErrorCodeVO
 * 
 */
public class ErrorCodeVO {

	/**
	 * Comment for <code>errorCode</code>
	 */
	private String errorCode;

	/**
	 * Comment for <code>auditTrail</code>
	 */
	private String auditTrail;

	/**
	 * List of ErrorExclusionVOs.
	 */
	private List exclusionList = new ArrayList();

	/**
	 * Comment for <code>totalExclusionCount</code>
	 */
	private int totalExclusionCount;

	/**
	 * Comment for <code>createdUser</code>
	 */
	private String createdUser;

	/**
	 * Comment for <code>lastUpdatedUser</code>
	 */
	private String lastUpdatedUser;

	/**
	 * Comment for <code>lastUpdatedDate</code>
	 */
	private Date lastUpdatedDate;

	/**
	 * Comment for <code>createdDate</code>
	 */
	private Date createdDate;

	/**
	 * Comment for <code>operationMessages</code> Operation messages.
	 */
	private String operationMessages;

	/**
	 * @return Audit trail.
	 */
	public String getAuditTrail() {
		return auditTrail;
	}

	/**
	 * @param auditTrail
	 *            Audit trail.
	 */
	public void setAuditTrail(String auditTrail) {
		this.auditTrail = auditTrail;
	}

	/**
	 * @return Error code.
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            Error code.
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return List of exclusions.
	 */
	public List getExclusionList() {
		return exclusionList;
	}

	/**
	 * @param exclusionList
	 *            Exclusions list.
	 */
	public void setExclusionList(List exclusionList) {
		this.exclusionList = exclusionList;
	}

	/**
	 * @return
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return
	 */
	public String getCreatedUser() {
		return createdUser;
	}

	/**
	 * @param createdUser
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	/**
	 * @return
	 */
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	/**
	 * @param lastUpdatedDate
	 */
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	/**
	 * @return
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}

	/**
	 * @param lastUpdatedUser
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}

	/**
	 * @return
	 */
	public int getTotalExclusionCount() {
		return totalExclusionCount;
	}

	/**
	 * @param totalExclusionCount
	 */
	public void setTotalExclusionCount(int totalExclusionCount) {
		this.totalExclusionCount = totalExclusionCount;
	}

	/**
	 * @return
	 */
	public String getOperationMessages() {
		return operationMessages;
	}

	/**
	 * @param operationMessages
	 */
	public void setOperationMessages(String operationMessages) {
		this.operationMessages = operationMessages;
	}

}
