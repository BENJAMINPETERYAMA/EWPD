/*
 * PricingInfoBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractPricingInfo {
	
	private int contractDateSegmentSysId;
	private java.lang.String coverage;
	private java.lang.String pricing;	
	private java.lang.String network;
	
	private java.lang.String coverageDesc;
	private java.lang.String pricingDesc;
	private java.lang.String networkDesc;

	private java.lang.String lastUpdatedUser;
	private java.util.Date lastUpdatedTimestamp;
	private java.lang.String createdUser;
	private java.util.Date createdTimestamp;
	
//---------------------------------getters/setters-----------------------		
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
	 * @return java.lang.String coverage.
	 */
	public java.lang.String getCoverage() {
		return coverage;
	}
	/**
	 * Sets the coverage
	 * @param coverage.
	 */
	public void setCoverage(java.lang.String coverage) {
		this.coverage = coverage;
	}
	/**
	 * Returns the coverageDesc
	 * @return java.lang.String coverageDesc.
	 */
	public java.lang.String getCoverageDesc() {
		return coverageDesc;
	}
	/**
	 * Sets the coverageDesc
	 * @param coverageDesc.
	 */
	public void setCoverageDesc(java.lang.String coverageDesc) {
		this.coverageDesc = coverageDesc;
	}
	/**
	 * Returns the createdTimestamp
	 * @return java.util.Date createdTimestamp.
	 */
	public java.util.Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * Sets the createdTimestamp
	 * @param createdTimestamp.
	 */
	public void setCreatedTimestamp(java.util.Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	/**
	 * Returns the createdUser
	 * @return java.lang.String createdUser.
	 */
	public java.lang.String getCreatedUser() {
		return createdUser;
	}
	/**
	 * Sets the createdUser
	 * @param createdUser.
	 */
	public void setCreatedUser(java.lang.String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * Returns the lastUpdatedTimestamp
	 * @return java.util.Date lastUpdatedTimestamp.
	 */
	public java.util.Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * Sets the lastUpdatedTimestamp
	 * @param lastUpdatedTimestamp.
	 */
	public void setLastUpdatedTimestamp(java.util.Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
	/**
	 * Returns the lastUpdatedUser
	 * @return java.lang.String lastUpdatedUser.
	 */
	public java.lang.String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * Sets the lastUpdatedUser
	 * @param lastUpdatedUser.
	 */
	public void setLastUpdatedUser(java.lang.String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * Returns the network
	 * @return java.lang.String network.
	 */
	public java.lang.String getNetwork() {
		return network;
	}
	/**
	 * Sets the network
	 * @param network.
	 */
	public void setNetwork(java.lang.String network) {
		this.network = network;
	}
	/**
	 * Returns the networkDesc
	 * @return java.lang.String networkDesc.
	 */
	public java.lang.String getNetworkDesc() {
		return networkDesc;
	}
	/**
	 * Sets the networkDesc
	 * @param networkDesc.
	 */
	public void setNetworkDesc(java.lang.String networkDesc) {
		this.networkDesc = networkDesc;
	}
	/**
	 * Returns the pricing
	 * @return java.lang.String pricing.
	 */
	public java.lang.String getPricing() {
		return pricing;
	}
	/**
	 * Sets the pricing
	 * @param pricing.
	 */
	public void setPricing(java.lang.String pricing) {
		this.pricing = pricing;
	}
	/**
	 * Returns the pricingDesc
	 * @return java.lang.String pricingDesc.
	 */
	public java.lang.String getPricingDesc() {
		return pricingDesc;
	}
	/**
	 * Sets the pricingDesc
	 * @param pricingDesc.
	 */
	public void setPricingDesc(java.lang.String pricingDesc) {
		this.pricingDesc = pricingDesc;
	}
}
