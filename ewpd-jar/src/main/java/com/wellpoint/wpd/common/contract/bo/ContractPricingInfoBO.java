/*
 * ContractPricingInfoBO.java
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
public class ContractPricingInfoBO{
	
	private int contractDateSegmentSysId;
	private java.lang.String coverage;
	private java.lang.String pricing;	
	private java.lang.String network;
	
//	---------------------------------getters/setters-----------------------		
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
}
