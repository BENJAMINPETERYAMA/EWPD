/*
 * AdaptedInfoBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;

import java.util.Date;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdaptedInfoBO {
	
	private String regulatoryAgency;
	
	private String regulatoryAgencyDesc;
	
	private String complianceStatus;
	
	private String complianceStatusDesc;
	
	private String prodProjNameCode;
	
	private String prodProjNameCodeDesc;
	
	private Date contractTermDate;
	
	private String multiplanIndicator;
	
	private String performanceGuarantee;
	
	private Date salesMarketDate;
	
	private int dateSegmentSysId;
	
	private String LastUpdatedUser;
	
	private Date LastUpdatedTimeStamp;
	
	/**
	 * @return Returns the lastUpdatedTimeStamp.
	 */
	public Date getLastUpdatedTimeStamp() {
		return LastUpdatedTimeStamp;
	}
	/**
	 * @param lastUpdatedTimeStamp The lastUpdatedTimeStamp to set.
	 */
	public void setLastUpdatedTimeStamp(Date lastUpdatedTimeStamp) {
		LastUpdatedTimeStamp = lastUpdatedTimeStamp;
	}
	/**
	 * @return Returns the lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return LastUpdatedUser;
	}
	/**
	 * @param lastUpdatedUser The lastUpdatedUser to set.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		LastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * @return Returns the regulatoryAgency.
	 */
	public String getRegulatoryAgency() {
		return regulatoryAgency;
	}
	/**
	 * @param regulatoryAgency The regulatoryAgency to set.
	 */
	public void setRegulatoryAgency(String regulatoryAgency) {
		this.regulatoryAgency = regulatoryAgency;
	}
	/**
	 * @return Returns the regulatoryAgencyDesc.
	 */
	public String getRegulatoryAgencyDesc() {
		return regulatoryAgencyDesc;
	}
	/**
	 * @param regulatoryAgencyDesc The regulatoryAgencyDesc to set.
	 */
	public void setRegulatoryAgencyDesc(String regulatoryAgencyDesc) {
		this.regulatoryAgencyDesc = regulatoryAgencyDesc;
	}
	
	/**
	 * @return Returns the complianceStatus.
	 */
	public String getComplianceStatus() {
		return complianceStatus;
	}
	/**
	 * @param complianceStatus The complianceStatus to set.
	 */
	public void setComplianceStatus(String complianceStatus) {
		this.complianceStatus = complianceStatus;
	}
	/**
	 * @return Returns the prodProjNameCode.
	 */
	public String getProdProjNameCode() {
		return prodProjNameCode;
	}
	/**
	 * @param prodProjNameCode The prodProjNameCode to set.
	 */
	public void setProdProjNameCode(String prodProjNameCode) {
		this.prodProjNameCode = prodProjNameCode;
	}
	/**
	 * @return Returns the prodProjNameCodeDesc.
	 */
	public String getProdProjNameCodeDesc() {
		return prodProjNameCodeDesc;
	}
	/**
	 * @param prodProjNameCodeDesc The prodProjNameCodeDesc to set.
	 */
	public void setProdProjNameCodeDesc(String prodProjNameCodeDesc) {
		this.prodProjNameCodeDesc = prodProjNameCodeDesc;
	}
	/**
	 * @return Returns the multiplanIndicator.
	 */
	public String getMultiplanIndicator() {
		return multiplanIndicator;
	}
	/**
	 * @param multiplanIndicator The multiplanIndicator to set.
	 */
	public void setMultiplanIndicator(String multiplanIndicator) {
		this.multiplanIndicator = multiplanIndicator;
	}
	/**
	 * @return Returns the performanceGuarantee.
	 */
	public String getPerformanceGuarantee() {
		return performanceGuarantee;
	}
	/**
	 * @param performanceGuarantee The performanceGuarantee to set.
	 */
	public void setPerformanceGuarantee(String performanceGuarantee) {
		this.performanceGuarantee = performanceGuarantee;
	}
	/**
	 * Returns the contractTermDate
	 * @return Date contractTermDate.
	 */
	public Date getContractTermDate() {
		return contractTermDate;
	}
	/**
	 * Sets the contractTermDate
	 * @param contractTermDate.
	 */
	public void setContractTermDate(Date contractTermDate) {
		this.contractTermDate = contractTermDate;
	}
	/**
	 * Returns the salesMarketDate
	 * @return Date salesMarketDate.
	 */
	public Date getSalesMarketDate() {
		return salesMarketDate;
	}
	/**
	 * Sets the salesMarketDate
	 * @param salesMarketDate.
	 */
	public void setSalesMarketDate(Date salesMarketDate) {
		this.salesMarketDate = salesMarketDate;
	}
	/**
	 * Returns the dateSegmentSysId
	 * @return int dateSegmentSysId.
	 */
	public int getDateSegmentSysId() {
		return dateSegmentSysId;
	}
	/**
	 * Sets the dateSegmentSysId
	 * @param dateSegmentSysId.
	 */
	public void setDateSegmentSysId(int dateSegmentSysId) {
		this.dateSegmentSysId = dateSegmentSysId;
	}	
	/**
	 * @return Returns the complianceStatusDesc.
	 */
	public String getComplianceStatusDesc() {
		return complianceStatusDesc;
	}
	/**
	 * @param complianceStatusDesc The complianceStatusDesc to set.
	 */
	public void setComplianceStatusDesc(String complianceStatusDesc) {
		this.complianceStatusDesc = complianceStatusDesc;
	}
}
