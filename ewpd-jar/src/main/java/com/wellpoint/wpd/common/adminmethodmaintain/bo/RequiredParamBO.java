/*
 * Created on Oct 5, 2009
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
public class RequiredParamBO extends BusinessObject {

	private int adminMethodSysId;
	private int groupID;
	private List referenceGroupBo;
	private String createdUser;	
	private String lastUpdatedUser;
	private Date createdTime;
	private Date lastUpdatedTime;		
	private String referenceDesc;
	private String referenceID;	
	private String adminMethodNo;
	private String adminMethodDesc;
		
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
	/**
	 * @return Returns the referenceGroupBo.
	 */
	public List getReferenceGroupBo() {
		return referenceGroupBo;
	}
	/**
	 * @param referenceGroupBo The referenceGroupBo to set.
	 */
	public void setReferenceGroupBo(List referenceGroupBo) {
		this.referenceGroupBo = referenceGroupBo;
	}
	/**
	 * @return Returns the groupID.
	 */
	public int getGroupID() {
		return groupID;
	}
	/**
	 * @param groupID The groupID to set.
	 */
	public void setGroupID(int groupID) {
		this.groupID = groupID;
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
	 * @return Returns the referenceDesc.
	 */
	public String getReferenceDesc() {
		return referenceDesc;
	}
	/**
	 * @param referenceDesc The referenceDesc to set.
	 */
	public void setReferenceDesc(String referenceDesc) {
		this.referenceDesc = referenceDesc;
	}	/**
	 * @return Returns the referenceID.
	 */
	/**
	 * @return Returns the referenceID.
	 */
	public String getReferenceID() {
		return referenceID;
	}
	/**
	 * @param referenceID The referenceID to set.
	 */
	public void setReferenceID(String referenceID) {
		this.referenceID = referenceID;
	}
	/**
	 * @return Returns the adminMethodDesc.
	 */
	public String getAdminMethodDesc() {
		return adminMethodDesc;
	}
	/**
	 * @param adminMethodDesc The adminMethodDesc to set.
	 */
	public void setAdminMethodDesc(String adminMethodDesc) {
		this.adminMethodDesc = adminMethodDesc;
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
