/*
 * Created on Oct 4, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethodmapping.Response;

import java.util.List;
import java.util.Map;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U17066
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodMappingSearchResponse extends WPDResponse {
	private List searchResultList;

	private Map selectedQuestionAnswerMap;
	/**
	 * @return Returns the searchResultList.
	 */
	public List getSearchResultList() {
		return searchResultList;
	}
	/**
	 * @param searchResultList The searchResultList to set.
	 */
	public void setSearchResultList(List searchResultList) {
		this.searchResultList = searchResultList;
	}
	

	/**
	 * @return Returns the selectedQuestionAnswerMap.
	 */
	public Map getSelectedQuestionAnswerMap() {
		return selectedQuestionAnswerMap;
	}
	/**
	 * @param selectedQuestionAnswerMap The selectedQuestionAnswerMap to set.
	 */
	public void setSelectedQuestionAnswerMap(Map selectedQuestionAnswerMap) {
		this.selectedQuestionAnswerMap = selectedQuestionAnswerMap;
	}
}
