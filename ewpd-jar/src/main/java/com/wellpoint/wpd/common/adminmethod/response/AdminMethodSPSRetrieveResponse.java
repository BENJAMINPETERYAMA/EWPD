/*
 * Created on Jan 19, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author u18739
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodSPSRetrieveResponse extends WPDResponse{
	
	List adminMethodSPSParameterList= new ArrayList();
	  private List tierCriteriaList; /*CARS 23/3/2010*/
	  private Map AdminMethodSPSParameterMap;/*CARS 23/3/2010*/
	  
	  
	
	
	/**
	 * @return Returns the adminMethodSPSParameterMap.
	 */
	public Map getAdminMethodSPSParameterMap() {
		return AdminMethodSPSParameterMap;
	}
	/**
	 * @param adminMethodSPSParameterMap The adminMethodSPSParameterMap to set.
	 */
	public void setAdminMethodSPSParameterMap(Map adminMethodSPSParameterMap) {
		AdminMethodSPSParameterMap = adminMethodSPSParameterMap;
	}
	/**
	 * @return Returns the tierCriteriaList.
	 */
	public List getTierCriteriaList() {
		return tierCriteriaList;
	}
	/**
	 * @param tierCriteriaList The tierCriteriaList to set.
	 */
	public void setTierCriteriaList(List tierCriteriaList) {
		this.tierCriteriaList = tierCriteriaList;
	}
	/**
	 * @return Returns the adminMethodSPSParameterList.
	 */
	public List getAdminMethodSPSParameterList() {
		return adminMethodSPSParameterList;
	}
	/**
	 * @param adminMethodSPSParameterList The adminMethodSPSParameterList to set.
	 */
	public void setAdminMethodSPSParameterList(List adminMethodSPSParameterList) {
		this.adminMethodSPSParameterList = adminMethodSPSParameterList;
	}
}
