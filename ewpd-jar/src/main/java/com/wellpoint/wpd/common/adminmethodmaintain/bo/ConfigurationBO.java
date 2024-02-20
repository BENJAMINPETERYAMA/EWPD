/*
 * Created on Oct 7, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethodmaintain.bo;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author U17066
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ConfigurationBO extends BusinessObject {



	private int adminMethodSysId;
	private List referenceIDList;
	private String createdUser;	
	private String lastUpdatedUser;
	private Date createdTime;
	private Date lastUpdatedTime;	
	private String configDesc;	
	private String configID;
	private String adminMethodNo;
	private String adminMethodDescription;
	
	
	
	
	/**
	 * @return Returns the configDesc.
	 */
	public String getConfigDesc() {
		return configDesc;
	}
	/**
	 * @param configDesc The configDesc to set.
	 */
	public void setConfigDesc(String configDesc) {
		this.configDesc = configDesc;
	}
	/**
	 * @return Returns the configID.
	 */
	public String getConfigID() {
		return configID;
	}
	/**
	 * @param configID The configID to set.
	 */
	public void setConfigID(String configID) {
		this.configID = configID;
	}
	/**
	 * @return Returns the adminMethodSysId.
	 */
	public int getAdminMethodSysId() {
		return adminMethodSysId;
	}
	/**
	 * @param adminMethodSysId The adminMethodSysId to set.
	 */
	public void setAdminMethodSysId(int adminMethodSysId) {
		this.adminMethodSysId = adminMethodSysId;
	}
	/**
	 * @return Returns the createdTime.
	 */
	public Date getCreatedTime() {
		return createdTime;
	}
	/**
	 * @param createdTime The createdTime to set.
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
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
	/**
	 * @return Returns the lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * @param lastUpdatedUser The lastUpdatedUser to set.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	
	
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 */
	
	
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	/**
	 * @return Returns the createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * @param createdUser The createdUser to set.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * @return Returns the referenceIDList.
	 */
	public List getReferenceIDList() {
		return referenceIDList;
	}
	/**
	 * @param referenceIDList The referenceIDList to set.
	 */
	public void setReferenceIDList(List referenceIDList) {
		this.referenceIDList = referenceIDList;
	}
	/**
	 * @return Returns the adminMethodDescription.
	 */
	public String getAdminMethodDescription() {
		return adminMethodDescription;
	}
	/**
	 * @param adminMethodDescription The adminMethodDescription to set.
	 */
	public void setAdminMethodDescription(String adminMethodDescription) {
		this.adminMethodDescription = adminMethodDescription;
	}
	/**
	 * @return Returns the adminMethodNo.
	 */
	public String getAdminMethodNo() {
		return adminMethodNo;
	}
	/**
	 * @param adminMethodNo The adminMethodNo to set.
	 */
	public void setAdminMethodNo(String adminMethodNo) {
		this.adminMethodNo = adminMethodNo;
	}
}
