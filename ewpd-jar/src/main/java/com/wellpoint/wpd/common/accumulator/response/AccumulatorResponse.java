package com.wellpoint.wpd.common.accumulator.response;

import java.util.List;

import com.wellpoint.wpd.common.accumulator.bo.Accumulator;
import com.wellpoint.wpd.common.accumulator.bo.SearchResultSet;
import com.wellpoint.wpd.common.accumulator.bo.StandardAccumulator;
import com.wellpoint.wpd.common.framework.request.WPDRequestFactory;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

public class AccumulatorResponse extends WPDResponse {

	Accumulator accumulatorSet = null;
	private SearchResultSet searchResultSet;
	List searchResultsStandardAccum;
	StandardAccumulator standardAccumulatorSet = null;
	private List searchResultList;
	 boolean success = false;

	public Accumulator getAccumulatorSet() {
		return accumulatorSet;
	}

	public void setAccumulatorSet(Accumulator accumulatorSet) {
		this.accumulatorSet = accumulatorSet;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public SearchResultSet getSearchResultSet() {
		return searchResultSet;
	}

	public void setSearchResultSet(SearchResultSet searchResultSet) {
		this.searchResultSet = searchResultSet;
	}

	public List getSearchResultsStandardAccum() {
		return searchResultsStandardAccum;
	}

	public void setSearchResultsStandardAccum(List searchResultsStandardAccum) {
		this.searchResultsStandardAccum = searchResultsStandardAccum;
	}

	public StandardAccumulator getStandardAccumulatorSet() {
		return standardAccumulatorSet;
	}

	public void setStandardAccumulatorSet(StandardAccumulator standardAccumulatorSet) {
		this.standardAccumulatorSet = standardAccumulatorSet;
	}

	public List getSearchResultList() {
		return searchResultList;
	}

	public void setSearchResultList(List searchResultList) {
		this.searchResultList = searchResultList;
	}


	
	
	
}
