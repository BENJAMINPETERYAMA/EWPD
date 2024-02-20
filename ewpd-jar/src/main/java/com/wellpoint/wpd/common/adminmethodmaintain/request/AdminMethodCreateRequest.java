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
public class AdminMethodCreateRequest extends WPDRequest {

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	
	private String adminMethodNo;

	private String description;

	private String processMethod;

	private String configuration;

	private String comments;

	private List configurationList;
	
	private List requiredParamList;
	
	private Date lastUpdatedDate;
	
	private Date createdDate;
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
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

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
}
