/*
 * Created on May 10, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.blueexchange.request;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author U19103
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractVariableMappingRequest extends WPDRequest{

	private int action;
	private String Status;
	private List variableMappingList;
	private Date lastUpdatedTime;

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * @return Returns the action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return Status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		Status = status;
	}
	/**
	 * @return Returns the variableMappingList.
	 */
	public List getVariableMappingList() {
		return variableMappingList;
	}
	/**
	 * @param variableMappingList The variableMappingList to set.
	 */
	public void setVariableMappingList(List variableMappingList) {
		this.variableMappingList = variableMappingList;
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
