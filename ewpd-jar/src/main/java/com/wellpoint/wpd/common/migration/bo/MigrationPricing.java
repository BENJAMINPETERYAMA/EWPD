/*
 * MigrationPricing.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.bo;

import java.util.Date;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public class MigrationPricing {
	private int contractDateSegmentSysId;
	private String coverage;
	private String pricing;	
	private String network;
	
	private String coverageDesc;
	private String pricingDesc;
	private String networkDesc;

	private String lastUpdatedUser;
	private Date lastUpdatedTimestamp;
	private String createdUser;
	private Date createdTimestamp;
	/**
	 * Returns the contractDateSegmentSysId
	 * @return int contractDateSegmentSysId.
	 */
	public int getContractDateSegmentSysId() {
		return contractDateSegmentSysId;
	}
	/**
	 * Sets the contractDateSegmentSysId
	 * @param contractDateSegmentSysId.
	 */
	public void setContractDateSegmentSysId(int contractDateSegmentSysId) {
		this.contractDateSegmentSysId = contractDateSegmentSysId;
	}
	/**
	 * Returns the coverage
	 * @return String coverage.
	 */
	public String getCoverage() {
		return coverage;
	}
	/**
	 * Sets the coverage
	 * @param coverage.
	 */
	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}
	/**
	 * Returns the coverageDesc
	 * @return String coverageDesc.
	 */
	public String getCoverageDesc() {
		return coverageDesc;
	}
	/**
	 * Sets the coverageDesc
	 * @param coverageDesc.
	 */
	public void setCoverageDesc(String coverageDesc) {
		this.coverageDesc = coverageDesc;
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
	 * Returns the network
	 * @return String network.
	 */
	public String getNetwork() {
		return network;
	}
	/**
	 * Sets the network
	 * @param network.
	 */
	public void setNetwork(String network) {
		this.network = network;
	}
	/**
	 * Returns the networkDesc
	 * @return String networkDesc.
	 */
	public String getNetworkDesc() {
		return networkDesc;
	}
	/**
	 * Sets the networkDesc
	 * @param networkDesc.
	 */
	public void setNetworkDesc(String networkDesc) {
		this.networkDesc = networkDesc;
	}
	/**
	 * Returns the pricing
	 * @return String pricing.
	 */
	public String getPricing() {
		return pricing;
	}
	/**
	 * Sets the pricing
	 * @param pricing.
	 */
	public void setPricing(String pricing) {
		this.pricing = pricing;
	}
	/**
	 * Returns the pricingDesc
	 * @return String pricingDesc.
	 */
	public String getPricingDesc() {
		return pricingDesc;
	}
	/**
	 * Sets the pricingDesc
	 * @param pricingDesc.
	 */
	public void setPricingDesc(String pricingDesc) {
		this.pricingDesc = pricingDesc;
	}
}
