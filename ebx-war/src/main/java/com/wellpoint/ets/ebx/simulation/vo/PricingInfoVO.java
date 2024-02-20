/*
 * <PricingInfoVO.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.simulation.vo;

/**
 * @author u23675
 *The object stores the pricing information of the contract
 *Introduced as part of SSCR 16331 Nov 2012 Release to get the pricing info 
 *to identify HMO Contracts for ISG/EWPD
 *
 */
public class PricingInfoVO {
	private String contractId;
	private String dateSegmentSysId;
	private String coverageCode;
	private String networkCode;
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getDateSegmentSysId() {
		return dateSegmentSysId;
	}
	public void setDateSegmentSysId(String dateSegmentSysId) {
		this.dateSegmentSysId = dateSegmentSysId;
	}
	public String getCoverageCode() {
		return coverageCode;
	}
	public void setCoverageCode(String coverageCode) {
		this.coverageCode = coverageCode;
	}
	public String getNetworkCode() {
		return networkCode;
	}
	public void setNetworkCode(String networkCode) {
		this.networkCode = networkCode;
	}
}
