/*
 * Created on Oct 23, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.popup.request;

import java.util.HashMap;
import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author u14768
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PopupRequest extends WPDRequest {
	
	private int entityid;
	
	private String queryName;
	
	private String headerName;
	
	private String searchName;
	
	private int popAction;
	
	private int benefitLvlid;
	
	private String term;
	
	private String qualifier;
	
	HashMap hashMap = new HashMap();
	private String spsId;
	private List spsIdList;

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return Returns the entityid.
	 */
	public int getEntityid() {
		return entityid;
	}
	/**
	 * @param entityid The entityid to set.
	 */
	public void setEntityid(int entityid) {
		this.entityid = entityid;
	}
	/**
	 * @return Returns the hashMap.
	 */
	public HashMap getHashMap() {
		return hashMap;
	}
	/**
	 * @param hashMap The hashMap to set.
	 */
	public void setHashMap(HashMap hashMap) {
		this.hashMap = hashMap;
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
	/**
	 * @return Returns the searchName.
	 */
	public String getSearchName() {
		return searchName;
	}
	/**
	 * @param searchName The searchName to set.
	 */
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	/**
	 * @return Returns the popAction.
	 */
	public int getPopAction() {
		return popAction;
	}
	/**
	 * @param popAction The popAction to set.
	 */
	public void setPopAction(int popAction) {
		this.popAction = popAction;
	}
	/**
	 * @return Returns the benefitLvlid.
	 */
	public int getBenefitLvlid() {
		return benefitLvlid;
	}
	/**
	 * @param benefitLvlid The benefitLvlid to set.
	 */
	public void setBenefitLvlid(int benefitLvlid) {
		this.benefitLvlid = benefitLvlid;
	}
	/**
	 * @return Returns the qualifier.
	 */
	public String getQualifier() {
		return qualifier;
	}
	/**
	 * @param qualifier The qualifier to set.
	 */
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}
	/**
	 * @return Returns the term.
	 */
	public String getTerm() {
		return term;
	}
	/**
	 * @param term The term to set.
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	/**
	 * @return Returns the spsId.
	 */
	public String getSpsId() {
		return spsId;
	}
	/**
	 * @param spsId The spsId to set.
	 */
	public void setSpsId(String spsId) {
		this.spsId = spsId;
	}
	/**
	 * @return Returns the spsIdList.
	 */
	public List getSpsIdList() {
		return spsIdList;
	}
	/**
	 * @param spsIdList The spsIdList to set.
	 */
	public void setSpsIdList(List spsIdList) {
		this.spsIdList = spsIdList;
	}
}
