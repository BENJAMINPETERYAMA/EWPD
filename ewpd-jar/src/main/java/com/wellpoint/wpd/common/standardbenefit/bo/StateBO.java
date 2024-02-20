/*
 * StateBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

import java.util.Date;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class StateBO {
	
	private int benefitMandateId;
	private String benefitStateCode;
	private String flag;
	private String benefitStateDesc;
	private String createdUser;
	private String lastUpdatedUser;
	private Date createdTimestamp;
	private Date lastUpdatedTimestamp;
	/**
	 * Returns the benefitStateCode
	 * @return String benefitStateCode.
	 */
	public String getBenefitStateCode() {
		return benefitStateCode;
	}
	/**
	 * Sets the benefitStateCode
	 * @param benefitStateCode.
	 */
	public void setBenefitStateCode(String benefitStateCode) {
		this.benefitStateCode = benefitStateCode;
	}
	/**
	 * Returns the benefitMandateId
	 * @return int benefitMandateId.
	 */
	public int getBenefitMandateId() {
		return benefitMandateId;
	}
	/**
	 * Sets the benefitMandateId
	 * @param benefitMandateId.
	 */
	public void setBenefitMandateId(int benefitMandateId) {
		this.benefitMandateId = benefitMandateId;
	}

	/**
	 * Returns the createdTimestamp
	 * @return Date createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * Sets the createdTimestamp
	 * @param createdTimestamp.
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
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
	 * Returns the lastUpdatedTimestamp
	 * @return Date lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * Sets the lastUpdatedTimestamp
	 * @param lastUpdatedTimestamp.
	 */
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
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
	 * Returns the flag
	 * @return String flag.
	 */
	public String getFlag() {
		return flag;
	}
	/**
	 * Sets the flag
	 * @param flag.
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	/**
	 * Returns the benefitStateDesc
	 * @return String benefitStateDesc.
	 */
	public String getBenefitStateDesc() {
		return benefitStateDesc;
	}
	/**
	 * Sets the benefitStateDesc
	 * @param benefitStateDesc.
	 */
	public void setBenefitStateDesc(String benefitStateDesc) {
		this.benefitStateDesc = benefitStateDesc;
	}
}
