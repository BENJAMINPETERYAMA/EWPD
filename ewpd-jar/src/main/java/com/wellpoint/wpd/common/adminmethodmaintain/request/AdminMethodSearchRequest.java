/*
 * Created on Oct 4, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethodmaintain.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author U17066
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodSearchRequest extends WPDRequest {
	private String adminMethodNo;

	private String description;

	private String processMethod;
	
	private boolean searchExisitng; 
	
	private String selectedProcessMethod;
	
	private String oldDescription;
	
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
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	/**
	 * @return Returns the selectedProcessMethod.
	 */
	public String getSelectedProcessMethod() {
		return selectedProcessMethod;
	}
	/**
	 * @param selectedProcessMethod The selectedProcessMethod to set.
	 */
	public void setSelectedProcessMethod(String selectedProcessMethod) {
		this.selectedProcessMethod = selectedProcessMethod;
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
}
