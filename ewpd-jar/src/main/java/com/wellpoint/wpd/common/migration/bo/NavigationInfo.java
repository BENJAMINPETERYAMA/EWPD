/*
 * Created on Sep 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.migration.bo;

import java.util.Date;


public class NavigationInfo {
	int migContSysId;
	int dateSegmentSysId;
	int stepCompleted;
	Date dateSegmentEffectiveDate;
	String lastAccessedPage;
	String lastUpdatedUser;
	Date lastUpdatedTimeStamp;
	boolean UpdateLastAccessedPageOnly;
	boolean navigationFlag; 
	/**
	 * Returns the dateSegmentSysId.
	 * @return int dateSegmentSysId.
	 */
	public int getDateSegmentSysId() {
		return dateSegmentSysId;
	}
	/**
	 * Sets the dateSegmentSysId.
	 * @param dateSegmentSysId.
	 */

	public void setDateSegmentSysId(int dateSegmentSysId) {
		this.dateSegmentSysId = dateSegmentSysId;
	}
	/**
	 * Returns the lastAccessedPage.
	 * @return String lastAccessedPage.
	 */
	public String getLastAccessedPage() {
		return lastAccessedPage;
	}
	/**
	 * Sets the lastAccessedPage.
	 * @param lastAccessedPage.
	 */

	public void setLastAccessedPage(String lastAccessedPage) {
		this.lastAccessedPage = lastAccessedPage;
	}
	/**
	 * Returns the lastUpdatedTimeStamp.
	 * @return Date lastUpdatedTimeStamp.
	 */
	public Date getLastUpdatedTimeStamp() {
		return lastUpdatedTimeStamp;
	}
	/**
	 * Sets the lastUpdatedTimeStamp.
	 * @param lastUpdatedTimeStamp.
	 */

	public void setLastUpdatedTimeStamp(Date lastUpdatedTimeStamp) {
		this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
	}
	/**
	 * Returns the lastUpdatedUser.
	 * @return String lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * Sets the lastUpdatedUser.
	 * @param lastUpdatedUser.
	 */

	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * Returns the migContSysId.
	 * @return int migContSysId.
	 */
	public int getMigContSysId() {
		return migContSysId;
	}
	/**
	 * Sets the migContSysId.
	 * @param migContSysId.
	 */

	public void setMigContSysId(int migContSysId) {
		this.migContSysId = migContSysId;
	}
	/**
	 * Returns the stepCompleted.
	 * @return int stepCompleted.
	 */
	public int getStepCompleted() {
		return stepCompleted;
	}
	/**
	 * Sets the stepCompleted.
	 * @param stepCompleted.
	 */

	public void setStepCompleted(int stepCompleted) {
		this.stepCompleted = stepCompleted;
	}
	/**
	 * Sets the navigationFlag
	 * @param navigationFlag.
	 */
	public void setNavigationFlag(boolean navigationFlag) {
		this.navigationFlag = navigationFlag;
	}
	/**
	 * Returns the navigationFlag
	 * @return boolean navigationFlag.
	 */
	public boolean isNavigationFlag() {
		return navigationFlag;
	}
	/**
	 * Returns the updateLastAccessedPageOnly
	 * @return boolean updateLastAccessedPageOnly.
	 */
	public boolean isUpdateLastAccessedPageOnly() {
		return UpdateLastAccessedPageOnly;
	}
	/**
	 * Sets the updateLastAccessedPageOnly
	 * @param updateLastAccessedPageOnly.
	 */
	public void setUpdateLastAccessedPageOnly(boolean updateLastAccessedPageOnly) {
		UpdateLastAccessedPageOnly = updateLastAccessedPageOnly;
	}
	/**
	 * Returns the dateSegmentEffectiveDate
	 * @return Date dateSegmentEffectiveDate.
	 */
	public Date getDateSegmentEffectiveDate() {
		return dateSegmentEffectiveDate;
	}
	/**
	 * Sets the dateSegmentEffectiveDate
	 * @param dateSegmentEffectiveDate.
	 */
	public void setDateSegmentEffectiveDate(Date dateSegmentEffectiveDate) {
		this.dateSegmentEffectiveDate = dateSegmentEffectiveDate;
	}
}
