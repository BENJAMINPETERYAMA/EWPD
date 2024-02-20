/*
 * Created on Jun 26, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.model;

import java.io.Serializable;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PricingInformation implements Serializable{
	
	private String pricing;
	
	private String coverage;
	
	private String network;

	/**
	 * @return Returns the coverage.
	 */
	public String getCoverage() {
		return coverage;
	}
	/**
	 * @param coverage The coverage to set.
	 */
	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}
	/**
	 * @return Returns the network.
	 */
	public String getNetwork() {
		return network;
	}
	/**
	 * @param network The network to set.
	 */
	public void setNetwork(String network) {
		this.network = network;
	}
	/**
	 * @return Returns the pricing.
	 */
	public String getPricing() {
		return pricing;
	}
	/**
	 * @param pricing The pricing to set.
	 */
	public void setPricing(String pricing) {
		this.pricing = pricing;
	}
}
