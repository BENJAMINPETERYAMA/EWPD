/*
 * Created on Oct 17, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethodmapping.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author U17066
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templatesboolean
 */
public class QuestionAnswerLookupRequest extends WPDRequest {

	
	private String SearchString;
	private boolean recordsGrtThanMaxSize;
	private boolean searchCriteriaEntered;
	
	

	/**
	
	 * @return Returns the searchString.
	 */
	public String getSearchString() {
		return SearchString;
	}
	/**
	 * @param searchString The searchString to set.
	 */
	public void setSearchString(String searchString) {
		SearchString = searchString;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	/**
	 * @return Returns the recordsGrtThanMaxSize.
	 */
	public boolean isRecordsGrtThanMaxSize() {
		return recordsGrtThanMaxSize;
	}
	/**
	 * @param recordsGrtThanMaxSize The recordsGrtThanMaxSize to set.
	 */
	public void setRecordsGrtThanMaxSize(boolean recordsGrtThanMaxSize) {
		this.recordsGrtThanMaxSize = recordsGrtThanMaxSize;
	}
	/**
	 * @return Returns the searchCriteriaEntered.
	 */
	public boolean isSearchCriteriaEntered() {
		return searchCriteriaEntered;
	}
	/**
	 * @param searchCriteriaEntered The searchCriteriaEntered to set.
	 */
	public void setSearchCriteriaEntered(boolean searchCriteriaEntered) {
		this.searchCriteriaEntered = searchCriteriaEntered;
	}
}
