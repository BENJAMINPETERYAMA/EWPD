/*
 * MigrationGenerateReportRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.request;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MigrationGenerateReportRequest extends MigrationContractRequest{

	private String ContractId;
	private String startDate;
	private String dateSegmentId;
	private int action;
	public static final int MIGRATION_RETRIEVE_UNMAPPED_VARIALBES = 1;
	public static final int UPDATE_STEP_COMPLETED = 2;
	
	/**
	 * Returns the contractId
	 * @return String contractId.
	 */
	public String getContractId() {
		return ContractId;
	}
	/**
	 * Sets the contractId
	 * @param contractId.
	 */
	public void setContractId(String contractId) {
		ContractId = contractId;
	}
	/**
	 * Returns the action
	 * @return int action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * Sets the action
	 * @param action.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	/**
	 * Returns the startDate
	 * @return Date startDate.
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * Sets the startDate
	 * @param startDate.
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * Returns the dateSegmentId
	 * @return String dateSegmentId.
	 */
	public String getDateSegmentId() {
		return dateSegmentId;
	}
	/**
	 * Sets the dateSegmentId
	 * @param dateSegmentId.
	 */
	public void setDateSegmentId(String dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}
}
