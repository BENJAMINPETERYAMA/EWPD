/*
 * LocateResults.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.bo;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: LocateResults.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class LocateResults {
    private int totalResultsCount;
	private List locateResults;
	private boolean latestVersion;

	/**
	 * @return
	 */
	public int getTotalResultsCount(){
	    return totalResultsCount;
	}
	
	/**
	 * @param totalResultsCount
	 */
	public void setTotalResultsCount(int totalResultsCount){
	    this.totalResultsCount = totalResultsCount;
	}
	
	/**
	 * @return
	 */
	public List getLocateResults() {
		return locateResults;
	}

	/**
	 * @param locateResults
	 */
	public void setLocateResults(List locateResults) {
		this.locateResults = locateResults;
	}
	
	/**
	 * @return Returns the latestVersion.
	 */
	public boolean isLatestVersion() {
		return latestVersion;
	}
	/**
	 * @param latestVersion The latestVersion to set.
	 */
	public void setLatestVersion(boolean latestVersion) {
		this.latestVersion = latestVersion;
	}
}
