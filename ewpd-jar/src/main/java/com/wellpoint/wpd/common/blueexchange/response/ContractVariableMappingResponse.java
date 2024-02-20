/*
 * Created on May 10, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.blueexchange.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U19103
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractVariableMappingResponse extends WPDResponse{
	
	private List searchList ;
	
	private boolean success;
	
	/**
	 * @return Returns the success.
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success The success to set.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return Returns the searchList.
	 */
	public List getSearchList() {
		return searchList;
	}
	/**
	 * @param searchList The searchList to set.
	 */
	public void setSearchList(List searchList) {
		this.searchList = searchList;
	}
}
