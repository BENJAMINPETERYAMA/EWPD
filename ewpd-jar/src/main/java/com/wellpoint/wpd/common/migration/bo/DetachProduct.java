/*
 * Created on Aug 28, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.migration.bo;

import java.util.Date;

/**
 * @author u16223
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DetachProduct {
	
	private int migDateSegmentSysId;
    
    private int migContractSysId;
    
    private String lastChangedUser;
    
    private Date lastUpdatedTime;
    
    
    

	/**
	 * @return Returns the migContractSysId.
	 */
	public int getMigContractSysId() {
		return migContractSysId;
	}
	/**
	 * @param migContractSysId The migContractSysId to set.
	 */
	public void setMigContractSysId(int migContractSysId) {
		this.migContractSysId = migContractSysId;
	}
	/**
	 * @return Returns the migDateSegmentSysId.
	 */
	public int getMigDateSegmentSysId() {
		return migDateSegmentSysId;
	}
	/**
	 * @param migDateSegmentSysId The migDateSegmentSysId to set.
	 */
	public void setMigDateSegmentSysId(int migDateSegmentSysId) {
		this.migDateSegmentSysId = migDateSegmentSysId;
	}
	
	/**
	 * @return Returns the lastChangedUser.
	 */
	public String getLastChangedUser() {
		return lastChangedUser;
	}
	/**
	 * @param lastChangedUser The lastChangedUser to set.
	 */
	public void setLastChangedUser(String lastChangedUser) {
		this.lastChangedUser = lastChangedUser;
	}
	/**
	 * @return Returns the lastUpdatedTime.
	 */
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	/**
	 * @param lastUpdatedTime The lastUpdatedTime to set.
	 */
	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
}
