/*
 * SaveLegacyContractRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.request;

import java.util.List;



/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveLegacyContractRequest extends MigrationContractRequest {

    
    private List allDateSegments;
    private String option;
    private int latestDateSegmentCount;
    private String selectedDateSegmentForMigration;
    private String selectedNewDate;
    private boolean afterMigrationAddNewDateSegment = false;
    
	/**
	 * @return Returns the latestDateSegmentCount.
	 */
	public int getLatestDateSegmentCount() {
		return latestDateSegmentCount;
	}
	/**
	 * @param latestDateSegmentCount The latestDateSegmentCount to set.
	 */
	public void setLatestDateSegmentCount(int latestDateSegmentCount) {
		this.latestDateSegmentCount = latestDateSegmentCount;
	}
    /**
	 * @return Returns the option.
	 */
	public String getOption() {
		return option;
	}
	/**
	 * @param option The option to set.
	 */
	public void setOption(String option) {
		this.option = option;
	}
	/**
	 * @return Returns the allDateSegments.
	 */
	public List getAllDateSegments() {
		return allDateSegments;
	}
	/**
	 * @param allDateSegments The allDateSegments to set.
	 */
	public void setAllDateSegments(List allDateSegments) {
		this.allDateSegments = allDateSegments;
	}
	/**
	 * Returns the selectedDateSegmentForMigration
	 * @return String selectedDateSegmentForMigration.
	 */
	public String getSelectedDateSegmentForMigration() {
		return selectedDateSegmentForMigration;
	}
	/**
	 * Sets the selectedDateSegmentForMigration
	 * @param selectedDateSegmentForMigration.
	 */
	public void setSelectedDateSegmentForMigration(
			String selectedDateSegmentForMigration) {
		this.selectedDateSegmentForMigration = selectedDateSegmentForMigration;
	}
	/**
	 * @return Returns the selectedNewDate.
	 */
	public String getSelectedNewDate() {
		return selectedNewDate;
	}
	/**
	 * @param selectedNewDate The selectedNewDate to set.
	 */
	public void setSelectedNewDate(String selectedNewDate) {
		this.selectedNewDate = selectedNewDate;
	}
	/**
	 * Returns the afterMigrationAddNewDateSegment
	 * @return boolean afterMigrationAddNewDateSegment.
	 */
	public boolean isAfterMigrationAddNewDateSegment() {
		return afterMigrationAddNewDateSegment;
	}
	/**
	 * Sets the afterMigrationAddNewDateSegment
	 * @param afterMigrationAddNewDateSegment.
	 */
	public void setAfterMigrationAddNewDateSegment(
			boolean afterMigrationAddNewDateSegment) {
		this.afterMigrationAddNewDateSegment = afterMigrationAddNewDateSegment;
	}
}