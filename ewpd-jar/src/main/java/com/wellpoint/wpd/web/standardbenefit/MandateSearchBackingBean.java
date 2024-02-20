/*
 * Created on Feb 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.standardbenefit;

import com.wellpoint.wpd.web.framework.WPDBackingBean;

import java.util.List;


/**
 * @author u14647
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MandateSearchBackingBean extends WPDBackingBean {
	
	private String businessDomain;
	
	private String jurisdiction;
	
	private String Version;
	
	private String updatedDate;
	
	private String updatedBy;
	
	private List searchResult;
	

	/**
	 * @return Returns the businessDomain.
	 */
	public String getBusinessDomain() {
		return businessDomain;
	}
	/**
	 * @param businessDomain The businessDomain to set.
	 */
	public void setBusinessDomain(String businessDomain) {
		this.businessDomain = businessDomain;
	}
	/**
	 * @return Returns the jurisdiction.
	 */
	public String getJurisdiction() {
		return jurisdiction;
	}
	/**
	 * @param jurisdiction The jurisdiction to set.
	 */
	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	/**
	 * @return Returns the updatedBy.
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy The updatedBy to set.
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @return Returns the updatedDate.
	 */
	public String getUpdatedDate() {
		return updatedDate;
	}
	/**
	 * @param updatedDate The updatedDate to set.
	 */
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	/**
	 * @return Returns the version.
	 */
	public String getVersion() {
		return Version;
	}
	/**
	 * @param version The version to set.
	 */
	public void setVersion(String version) {
		Version = version;
	}
	
	/**
	 * @return Returns the searchResult.
	 */
	public List getSearchResult() {
		return searchResult;
	}
	/**
	 * @param searchResult The searchResult to set.
	 */
	public void setSearchResult(List searchResult) {
		this.searchResult = searchResult;
	}
	public void search(){
		
	}
}
