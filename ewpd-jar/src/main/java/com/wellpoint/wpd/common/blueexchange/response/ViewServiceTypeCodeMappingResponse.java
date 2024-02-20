/*
 * Created on May 13, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.blueexchange.response;

import java.util.List;

import com.wellpoint.wpd.common.blueexchange.bo.ServiceTypeMapping;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U15427
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ViewServiceTypeCodeMappingResponse extends WPDResponse {
	
	private ServiceTypeMapping serviceTypeMapping;
	private List searchResultList;
	private List unMappedRules;
	private boolean dataFeedRunning;
	/**
	 * @return Returns the serviceTypeMapping.
	 */
	public ServiceTypeMapping getServiceTypeMapping() {
		return serviceTypeMapping;
	}
	/**
	 * @param serviceTypeMapping The serviceTypeMapping to set.
	 */
	public void setServiceTypeMapping(ServiceTypeMapping serviceTypeMapping) {
		this.serviceTypeMapping = serviceTypeMapping;
	}
	
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
	 * @return Returns the unMappedRules.
	 */
	public List getUnMappedRules() {
		return unMappedRules;
	}
	/**
	 * @param unMappedRules The unMappedRules to set.
	 */
	public void setUnMappedRules(List unMappedRules) {
		this.unMappedRules = unMappedRules;
	}
	
	/**
	 * @return Returns the dataFeedRunning.
	 */
	public boolean isDataFeedRunning() {
		return dataFeedRunning;
	}
	/**
	 * @param dataFeedRunning The dataFeedRunning to set.
	 */
	public void setDataFeedRunning(boolean dataFeedRunning) {
		this.dataFeedRunning = dataFeedRunning;
	}
}
