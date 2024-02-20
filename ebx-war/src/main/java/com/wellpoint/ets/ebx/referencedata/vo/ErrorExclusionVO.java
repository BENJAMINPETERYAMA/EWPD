/*
 * <ErrorExclusionVO.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.referencedata.vo;

import java.util.Date;

/**
 * @author UST-GLOBAL ErrorExclusionVO
 * 
 */
public class ErrorExclusionVO {
	/**
	 * Exclusion ID
	 */
	private int exclusionId;

	/**
	 * Error code
	 */
	private String errorCode;

	/**
	 * Primary exclusion
	 */
	private String primaryExclusion;

	/**
	 * Secondaty exclusion.
	 */
	private String secondaryExclusion;

	/**
	 * Comment for <code>primaryValues</code> Primary exclusion values.
	 */
	private String primaryValues;

	/**
	 * Comment for <code>secondaryValues</code> Secondary exclusion values.
	 */
	private String secondaryValues;

	/**
	 * Comment for <code>exclusionCount</code> Count of exclusions in a single
	 * exclusion.
	 */
	private int exclusionCount;

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
	 * @return Exclusion count.
	 */
	public int getExclusionCount() {
		return exclusionCount;
	}

	/**
	 * @param exclusionCount
	 *            Exclusion count for an exclusion.
	 */
	public void setExclusionCount(int exclusionCount) {
		this.exclusionCount = exclusionCount;
	}

	/**
	 * @return Integer
	 */
	public int getExclusionId() {
		return exclusionId;
	}

	/**
	 * @param exclusionId
	 *            Exclusion ID
	 */
	public void setExclusionId(int exclusionId) {
		this.exclusionId = exclusionId;
	}

	/**
	 * @return Primary exclusion.
	 */
	public String getPrimaryExclusion() {
		return primaryExclusion;
	}

	/**
	 * @param primaryExclusion
	 *            Primary exclusion.
	 */
	public void setPrimaryExclusion(String primaryExclusion) {
		this.primaryExclusion = primaryExclusion;
	}

	/**
	 * @return Primary values
	 */
	public String getPrimaryValues() {
		return primaryValues;
	}

	/**
	 * @param primaryValues
	 *            Primary value
	 */
	public void setPrimaryValues(String primaryValues) {
		this.primaryValues = primaryValues;
	}

	/**
	 * @return Secondary Exclusion.
	 */
	public String getSecondaryExclusion() {
		return secondaryExclusion;
	}

	/**
	 * @param secondaryExclusion
	 *            Secondary exclusion.
	 */
	public void setSecondaryExclusion(String secondaryExclusion) {
		this.secondaryExclusion = secondaryExclusion;
	}

	/**
	 * @return Secondary values.
	 */
	public String getSecondaryValues() {
		return secondaryValues;
	}

	/**
	 * @param secondaryValues
	 *            Secondary values.
	 */
	public void setSecondaryValues(String secondaryValues) {
		this.secondaryValues = secondaryValues;
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

}
