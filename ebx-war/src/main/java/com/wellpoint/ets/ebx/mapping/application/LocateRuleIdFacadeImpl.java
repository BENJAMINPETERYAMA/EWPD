/*
 * <LocateRuleIdFacadeImpl.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/
package com.wellpoint.ets.ebx.mapping.application;

import java.util.List;
import java.util.Map;

import com.wellpoint.ets.bx.mapping.domain.entity.Catalog;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Page;
import com.wellpoint.ets.bx.mapping.domain.vo.MappingResult;
import com.wellpoint.ets.ebx.mapping.domain.service.LocateService;
/**
 * @author UST-GLOBAL
 * This is an implementation class for locating or searching header rule 
 * or multiple Header rule, based on certain search criteria
 */
public class LocateRuleIdFacadeImpl implements LocateFacade {
	
	private LocateService locateRuleIdService;
	
	/**
	 * for retrieving hipaa code values starting with the searchString. 
	 * Flag can be used to indicate whether to retrieve all the records or limit it to 50
	 * (depends on how many records we need to display)
	 * @param catalog
	 * @param searchString
	 * @param flag
	 * @return List
	 */
	public List getItems(Catalog catalog, String searchString, boolean limitFlag, List formats) {
		
		List items = locateRuleIdService.getItems(catalog, searchString, limitFlag, formats);
		
		return items;
	}
	/**
	 * For pagination in locate result page
	 * @param mapping
	 * @return int
	 */
	public int getRecordCount(Mapping mapping, List status) {
		
		return locateRuleIdService.getRecordCount(mapping, status);
	}
	/**
	 * For getting exact match/retrieving records based on the status and logged in user
	 * @param Mapping
	 * @param status
	 * @param loggedInUser
	 * @return List
	 */
	public List getRecords(Mapping mapping, List status, String loggedInUser,int noOfRecords, int auditTrailCount, Page page) {
		return locateRuleIdService.getRecords(mapping, status, loggedInUser, noOfRecords, auditTrailCount, page);
	}
	/**
	 * After the mapping is in published status, when the user clicks on edit, 
	 * this method will insert record in temp table and used for updating the temp
	 * @param mapping
	 * @return Mapping
	 */
	public MappingResult getRecordsForUpdate(Mapping mapping) {
		return locateRuleIdService.getRecordsForUpdate(mapping);
	}
	public LocateService getLocateRuleIdService() {
		return locateRuleIdService;
	}
	public void setLocateRuleIdService(LocateService locateRuleIdService) {
		this.locateRuleIdService = locateRuleIdService;
	}
	public MappingResult getRecordsForUpdate(Mapping mapping, List status, String loggedInUser, int noOfRecords, int auditTrailCount, Page page) {
		return locateRuleIdService.getRecordsForUpdate(mapping, status, loggedInUser, noOfRecords, auditTrailCount, page);
	}
	/**
	 * for generating reports from locate
	 * @param mapping
	 * @return List
	 */
	public List getRecordsForReport(Mapping mapping, List status) {
		return locateRuleIdService.getRecordsForReport(mapping, status);
	}
	
	public Mapping autopopulateBySPSFormat(HippaSegment hippaSegment, Mapping mapping){
		return locateRuleIdService.autopopulateBySPSFormat(hippaSegment, mapping);
	}
	
	public Map<String,String> fetchHippaSegmentDescription(String hippaSegmentName){
		return locateRuleIdService.fetchHippaSegmentDescription(hippaSegmentName);
	}
}
