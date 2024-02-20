/*
 * Created on Oct 24, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.popup.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author u14768
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PopupResponse extends WPDResponse{
	
	private List resultList;
	
	private int entitySysId;
	
	private String queryName;
	
	private String headerName;
	/**
	 * @return Returns the resultList.
	 */
	public List getResultList() {
		return resultList;
	}
	/**
	 * @param resultList The resultList to set.
	 */
	public void setResultList(List resultList) {
		this.resultList = resultList;
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
	 * @return Returns the entitySysId.
	 */
	public int getEntitySysId() {
		return entitySysId;
	}
	/**
	 * @param entitySysId The entitySysId to set.
	 */
	public void setEntitySysId(int entitySysId) {
		this.entitySysId = entitySysId;
	}
	/**
	 * @return Returns the headerName.
	 */
	public String getHeaderName() {
		return headerName;
	}
	/**
	 * @param headerName The headerName to set.
	 */
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
}
