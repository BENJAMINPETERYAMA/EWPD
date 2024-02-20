/*
 * PricingInfoItemVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.vo;


 
/*
 * ContractBusinessService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
public class PricingInfoItemVO implements java.io.Serializable {

    private String contractId;

    private String startDate;

    private String revisionDate;

    private String contractCoverage;

    private String contractPricing;

    private String contractNetwork;

    private String lastChangedUserId;

    

	/**
	 * @return Returns the contractCoverage.
	 */
	public String getContractCoverage() {
		return contractCoverage;
	}
	/**
	 * @param contractCoverage The contractCoverage to set.
	 */
	public void setContractCoverage(String contractCoverage) {
		this.contractCoverage = contractCoverage;
	}
	/**
	 * @return Returns the contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * @param contractId The contractId to set.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * @return Returns the contractNetwork.
	 */
	public String getContractNetwork() {
		return contractNetwork;
	}
	/**
	 * @param contractNetwork The contractNetwork to set.
	 */
	public void setContractNetwork(String contractNetwork) {
		this.contractNetwork = contractNetwork;
	}
	/**
	 * @return Returns the contractPricing.
	 */
	public String getContractPricing() {
		return contractPricing;
	}
	/**
	 * @param contractPricing The contractPricing to set.
	 */
	public void setContractPricing(String contractPricing) {
		this.contractPricing = contractPricing;
	}
	/**
	 * @return Returns the lastChangedUserId.
	 */
	public String getLastChangedUserId() {
		return lastChangedUserId;
	}
	/**
	 * @param lastChangedUserId The lastChangedUserId to set.
	 */
	public void setLastChangedUserId(String lastChangedUserId) {
		this.lastChangedUserId = lastChangedUserId;
	}
	/**
	 * @return Returns the revisionDate.
	 */
	public String getRevisionDate() {
		return revisionDate;
	}
	/**
	 * @param revisionDate The revisionDate to set.
	 */
	public void setRevisionDate(String revisionDate) {
		this.revisionDate = revisionDate;
	}
	/**
	 * @return Returns the startDate.
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("contractId = ").append(contractId).append(" ");
        buffer.append("startDate = ").append(startDate).append(" ");
        buffer.append("revisionDate = ").append(revisionDate).append(" ");
        buffer.append("contractCoverage = ").append(contractCoverage).append(" ");
        buffer.append("contractPricing = ").append(contractPricing).append(" ");
        buffer.append("contractNetwork = ").append(contractNetwork).append(" ");
		return buffer.toString();
	}
}
