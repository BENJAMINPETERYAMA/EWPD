/*
 * Created on Oct 5, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author u18739
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminmethodViewAllRequest extends WPDRequest {

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	private String queryName;
	
	private int popAction;
	
	private String adminMethodNo;
	
	private String adminMethodDescription;
	
	private String processingMethods;
	
	
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}
	

	/**
	 * @return Returns the popAction.
	 */
	public int getPopAction() {
		return popAction;
	}
	/**
	 * @param popAction The popAction to set.
	 */
	public void setPopAction(int popAction) {
		this.popAction = popAction;
	}
	/**
	 * @return Returns the queryName.
	 */
	public String getQueryName() {
		return queryName;
	}
	/**
	 * @param queryName The queryName to set.
	 */
	public void setQueryName(String queryName) {
		this.queryName = queryName;
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
	/**
	 * @return Returns the processingMethods.
	 */
	public String getProcessingMethods() {
		return processingMethods;
	}
	/**
	 * @param processingMethods The processingMethods to set.
	 */
	public void setProcessingMethods(String processingMethods) {
		this.processingMethods = processingMethods;
	}
}
