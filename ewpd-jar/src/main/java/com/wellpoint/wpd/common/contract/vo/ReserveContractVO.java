/*
 * ReserveContractVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.vo;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ReserveContractVO {
    private String contractId;
    private String  numberOfIdToGenerate;
	private List businessDomains;
	private String startDate;
	private String endDate;
	
	private String reservePoolStatus;
	
	/**
	 * @return Returns the businessDomains.
	 */
	public List getBusinessDomains() {
		return businessDomains;
	}
	/**
	 * @param businessDomains The businessDomains to set.
	 */
	public void setBusinessDomains(List businessDomains) {
		this.businessDomains = businessDomains;
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
	 * @return Returns the endDate.
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return Returns the numberOfIdToGenerate.
	 */
	public String getNumberOfIdToGenerate() {
		return numberOfIdToGenerate;
	}
	/**
	 * @param numberOfIdToGenerate The numberOfIdToGenerate to set.
	 */
	public void setNumberOfIdToGenerate(String numberOfIdToGenerate) {
		this.numberOfIdToGenerate = numberOfIdToGenerate;
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
	
	/**
	 * @return Returns the reservePoolStatus.
	 */
	public String getReservePoolStatus() {
		return reservePoolStatus;
	}
	/**
	 * @param reservePoolStatus The reservePoolStatus to set.
	 */
	public void setReservePoolStatus(String reservePoolStatus) {
		this.reservePoolStatus = reservePoolStatus;
	}
}
