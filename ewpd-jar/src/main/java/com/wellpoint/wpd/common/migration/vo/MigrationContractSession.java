/*
 * MigrationContractSession.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.vo;

import java.util.Date;

import com.wellpoint.wpd.common.migration.bo.MigrationContract;
import com.wellpoint.wpd.common.migration.bo.NavigationInfo;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MigrationContractSession {
	
	private String dateSegmentId;
	private Date startDateEwpd;
	private Date endDate;
	private Date startDateLegacy;
	
	private NavigationInfo navigationInfo;
	private MigrationContract migrationContract;
	
	private int productId;
	
	/**
	 * @return Returns the endDate.
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return Returns the migrationContract.
	 */
	public MigrationContract getMigrationContract() {
		return migrationContract;
	}
	/**
	 * @param migrationContract The migrationContract to set.
	 */
	public void setMigrationContract(MigrationContract migrationContract) {
		this.migrationContract = migrationContract;
	}
	/**
	 * @return Returns the startDateEwpd.
	 */
	public Date getStartDateEwpd() {
		return startDateEwpd;
	}
	/**
	 * @param startDateEwpd The startDateEwpd to set.
	 */
	public void setStartDateEwpd(Date startDateEwpd) {
		this.startDateEwpd = startDateEwpd;
	}
	/**
	 * @return Returns the startDateLegacy.
	 */
	public Date getStartDateLegacy() {
		return startDateLegacy;
	}
	/**
	 * @param startDateLegacy The startDateLegacy to set.
	 */
	public void setStartDateLegacy(Date startDateLegacy) {
		this.startDateLegacy = startDateLegacy;
	}
	/**
	 * @return Returns the dateSegmentId.
	 */
	public String getDateSegmentId() {
		return dateSegmentId;
	}
	/**
	 * @param dateSegmentId The dateSegmentId to set.
	 */
	public void setDateSegmentId(String dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}
	/**
	 * Returns the navigationInfo
	 * @return NavigationInfo navigationInfo.
	 */
	public NavigationInfo getNavigationInfo() {
		return navigationInfo;
	}
	/**
	 * Sets the navigationInfo
	 * @param navigationInfo.
	 */
	public void setNavigationInfo(NavigationInfo navigationInfo) {
		this.navigationInfo = navigationInfo;
	}
	/**
	 * @return Returns the productId.
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param productId The productId to set.
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
}
