/*
 * Created on Jul 20, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.referencemapping.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SearchReferenceMappingResponse extends WPDResponse{
	
	List searchList;
	private String lastUpdatedUser;		
	private String createdUser;
	private String createdDate;
	private String changeDate;
	
	/**
	 * @return Returns the changeDate.
	 */
	public String getChangeDate() {
		return changeDate;
	}
	/**
	 * @param changeDate The changeDate to set.
	 */
	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}
	/**
	 * @return Returns the createdDate.
	 */
	public String getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate The createdDate to set.
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
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
	 * @return Returns the searchResults.
	 */
	public List getSearchList() {
		return searchList;
	}
	/**
	 * @param searchResults The searchResults to set.
	 */
	public void setSearchList(List searchList) {
		this.searchList = searchList;
	}
}
