/*
 * LegacyPricing.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.legacycontract.bo;

import java.util.Date;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public class LegacyPricing extends LegacyObject {
	private String contractId;
	private Date startDate;
	private String coverage;
	private String pricing;	
	private String network;
	private String createdUser;
	private Date createdTimestamp;
	private String lastUpdatedUser;
	private Date lastUpdatedTimestamp;
	
	public LegacyPricing(){
		
	}
	public LegacyPricing(String system){
		super(system);
	}
	/**
	 * Returns the contractId.
	 * @return String contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * Sets the contractId.
	 * @param contractId.
	 */
	
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * Returns the startDate.
	 * @return Date startDate.
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * Sets the startDate.
	 * @param startDate.
	 */
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * Returns the coverage.
	 * @return String coverage.
	 */
	public String getCoverage() {
		return coverage;
	}
	/**
	 * Sets the coverage.
	 * @param coverage.
	 */
	
	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}
	/**
	 * Returns the pricing.
	 * @return String pricing.
	 */
	public String getPricing() {
		return pricing;
	}
	/**
	 * Sets the pricing.
	 * @param pricing.
	 */
	
	public void setPricing(String pricing) {
		this.pricing = pricing;
	}
	/**
	 * Returns the network.
	 * @return String network.
	 */
	public String getNetwork() {
		return network;
	}
	/**
	 * Sets the network.
	 * @param network.
	 */
	
	public void setNetwork(String network) {
		this.network = network;
	}
	/**
	 * Returns the createdUser.
	 * @return String createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * Sets the createdUser.
	 * @param createdUser.
	 */
	
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * Returns the createdTimestamp.
	 * @return Date createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * Sets the createdTimestamp.
	 * @param createdTimestamp.
	 */
	
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	/**
	 * Returns the lastUpdatedUser.
	 * @return String lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * Sets the lastUpdatedUser.
	 * @param lastUpdatedUser.
	 */
	
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * Returns the lastUpdatedTimestamp.
	 * @return Date lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * Sets the lastUpdatedTimestamp.
	 * @param lastUpdatedTimestamp.
	 */
	
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
}
