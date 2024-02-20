/*
 * Created on Oct 4, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethodmaintain.request;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author U17066
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodEditRequest extends WPDRequest {
	private int adminMethodSysId;
	private String description; // changed description for update 
	private String configuration;
	private List requiredParamList;
	private List configurationList;
	private String spsIdList;
	private String adminMethodNo;
	private String oldDescription; // old description to get the admin method sys id
	private String adminMethodSysIdAll;
	private Date lastUpdatedDate;

	private String comments;
	private List reqParamGroups;

	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return Returns the configuration.
	 */
	public String getConfiguration() {
		return configuration;
	}
	/**
	 * @param configuration The configuration to set.
	 */
	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the reqParamGroups.
	 */
	public List getReqParamGroups() {
		return reqParamGroups;
	}
	/**
	 * @param reqParamGroups The reqParamGroups to set.
	 */
	public void setReqParamGroups(List reqParamGroups) {
		this.reqParamGroups = reqParamGroups;
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
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	/**
	 * @return Returns the requiredParamList.
	 */
	public List getRequiredParamList() {
		return requiredParamList;
	}
	/**
	 * @param requiredParamList The requiredParamList to set.
	 */
	public void setRequiredParamList(List requiredParamList) {
		this.requiredParamList = requiredParamList;
	}
	/**
	 * @return Returns the configurationList.
	 */
	public List getConfigurationList() {
		return configurationList;
	}
	/**
	 * @param configurationList The configurationList to set.
	 */
	public void setConfigurationList(List configurationList) {
		this.configurationList = configurationList;
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
	/**
	 * @return Returns the oldDescription.
	 */
	public String getOldDescription() {
		return oldDescription;
	}
	/**
	 * @param oldDescription The oldDescription to set.
	 */
	public void setOldDescription(String oldDescription) {
		this.oldDescription = oldDescription;
	}
	/**
	 * @return Returns the adminMethodSysIdAll.
	 */
	public String getAdminMethodSysIdAll() {
		return adminMethodSysIdAll;
	}
	/**
	 * @param adminMethodSysIdAll The adminMethodSysIdAll to set.
	 */
	public void setAdminMethodSysIdAll(String adminMethodSysIdAll) {
		this.adminMethodSysIdAll = adminMethodSysIdAll;
	}
	/**
	 * @return Returns the spsIdList.
	 */
	public String getSpsIdList() {
		return spsIdList;
	}
	/**
	 * @param spsIdList The spsIdList to set.
	 */
	public void setSpsIdList(String spsIdList) {
		this.spsIdList = spsIdList;
	}
	/**
	 * @return Returns the lastUpdatedDate.
	 */
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	/**
	 * @param lastUpdatedDate The lastUpdatedDate to set.
	 */
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
}
