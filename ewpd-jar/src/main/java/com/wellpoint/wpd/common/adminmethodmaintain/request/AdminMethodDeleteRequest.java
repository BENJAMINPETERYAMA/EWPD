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
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodDeleteRequest extends WPDRequest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	private String adminMethodNo;
	private String adminMethodDesc;
	private String processingMethodIds;



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
	/**
	 * @return Returns the processingMethodIds.
	 */
	public String getProcessingMethodIds() {
		return processingMethodIds;
	}
	/**
	 * @param processingMethodIds The processingMethodIds to set.
	 */
	public void setProcessingMethodIds(String processingMethodIds) {
		this.processingMethodIds = processingMethodIds;
	}
}
