/*
 * Created on Oct 4, 2009
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
public class AdminMethodMaintainBO extends BusinessObject {

	private String adminMethodNo;
	private int adminMethodSysId;
	private String description;
	private String processMethod;	
	private String processMethodDesc;
	private List processMethodList;
	private List configurationList;
	private String comments;
	private String requiredParm;
	private List reqParamGroups; // List representing the required parameter group.
	private String currentSpsid;
	private List searchResults;
	private String createdUser;
	private String lastUpdatedUser;
	private Date createdDate;
	private Date lastUpdatedDate;	
	private List referenceIDList;	
	private String configuration;
	private boolean searchExisitng;
	private String adminMethodDesc;	
	public static int adminMethodCoded =1;
	public static int adminMethodMapped =2;
	public static int adminMethodCodedAndMapped =3;
	public List adminMethodSysIdList;
	public String adminMethodSysIdAll;
	 
	/**
	 * @return Returns the requiredParamsList.
	 */
	public List getRequiredParamsList() {
		return requiredParamsList;
	}
	/**
	 * @param requiredParamsList The requiredParamsList to set.
	 */
	public void setRequiredParamsList(List requiredParamsList) {
		this.requiredParamsList = requiredParamsList;
	}
	
	private List requiredParamsList;
	
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
	 * @return Returns the searchResults.
	 */
	public List getSearchResults() {
		return searchResults;
	}

	/**
	 * @param searchResults The searchResults to set.
	 */
	public void setSearchResults(List searchResults) {
		this.searchResults = searchResults;
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
	 * @return Returns the processMethodDesc.
	 */
	public String getProcessMethodDesc() {
		return processMethodDesc;
	}
	/**
	 * @param processMethodDesc The processMethodDesc to set.
	 */
	public void setProcessMethodDesc(String processMethodDesc) {
		this.processMethodDesc = processMethodDesc;
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
	 * @return Returns the requiredParm.
	 */
	public String getRequiredParm() {
		return requiredParm;
	}
	/**
	 * @param requiredParm The requiredParm to set.
	 */
	public void setRequiredParm(String requiredParm) {
		this.requiredParm = requiredParm;
	}
	/**
	 * @return Returns the createdDate.
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate The createdDate to set.
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
	
	/**
	 * @return Returns the searchExisitng.
	 */
	public boolean isSearchExisitng() {
		return searchExisitng;
	}
	/**
	 * @param searchExisitng The searchExisitng to set.
	 */
	public void setSearchExisitng(boolean searchExisitng) {
		this.searchExisitng = searchExisitng;
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
	 * @return Returns the adminMethodSysIdList.
	 */

	/**
	 * @return Returns the adminMethodSysIdList.
	 */
	public List getAdminMethodSysIdList() {
		return adminMethodSysIdList;
	}
	/**
	 * @param adminMethodSysIdList The adminMethodSysIdList to set.
	 */
	public void setAdminMethodSysIdList(List adminMethodSysIdList) {
		this.adminMethodSysIdList = adminMethodSysIdList;
	}
	/**
	 * @return Returns the processMethod.
	 */
	public String getProcessMethod() {
		return processMethod;
	}
	/**
	 * @param processMethod The processMethod to set.
	 */
	public void setProcessMethod(String processMethod) {
		this.processMethod = processMethod;
	}
	/**
	 * @return Returns the currentSpsid.
	 */
	public String getCurrentSpsid() {
		return currentSpsid;
	}
	/**
	 * @param currentSpsid The currentSpsid to set.
	 */
	public void setCurrentSpsid(String currentSpsid) {
		this.currentSpsid = currentSpsid;
	}
	/**
	 * @return Returns the processMethodList.
	 */
	public List getProcessMethodList() {
		return processMethodList;
	}
	/**
	 * @param processMethodList The processMethodList to set.
	 */
	public void setProcessMethodList(List processMethodList) {
		this.processMethodList = processMethodList;
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
}