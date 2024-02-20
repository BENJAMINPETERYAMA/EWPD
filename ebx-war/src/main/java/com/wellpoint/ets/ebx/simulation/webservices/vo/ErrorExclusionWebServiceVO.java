package com.wellpoint.ets.ebx.simulation.webservices.vo;

import java.util.Date;

public class ErrorExclusionWebServiceVO {
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

	public int getExclusionId() {
		return exclusionId;
	}

	public void setExclusionId(int exclusionId) {
		this.exclusionId = exclusionId;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getPrimaryExclusion() {
		return primaryExclusion;
	}

	public void setPrimaryExclusion(String primaryExclusion) {
		this.primaryExclusion = primaryExclusion;
	}

	public String getSecondaryExclusion() {
		return secondaryExclusion;
	}

	public void setSecondaryExclusion(String secondaryExclusion) {
		this.secondaryExclusion = secondaryExclusion;
	}

	public String getPrimaryValues() {
		return primaryValues;
	}

	public void setPrimaryValues(String primaryValues) {
		this.primaryValues = primaryValues;
	}

	public String getSecondaryValues() {
		return secondaryValues;
	}

	public void setSecondaryValues(String secondaryValues) {
		this.secondaryValues = secondaryValues;
	}

	public int getExclusionCount() {
		return exclusionCount;
	}

	public void setExclusionCount(int exclusionCount) {
		this.exclusionCount = exclusionCount;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}

	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
