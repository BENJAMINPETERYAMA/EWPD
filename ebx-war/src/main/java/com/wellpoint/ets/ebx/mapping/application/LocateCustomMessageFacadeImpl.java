/*
 * <LocateCustomMessageFacadeImpl.java>
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
 * This is an implementation class for locating or searching a custom message
 * or multiple custom message, based on certain search criteria
 */
public class LocateCustomMessageFacadeImpl implements LocateFacade {
	
	private LocateService locateCustomMessageService;
	
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
		List items = locateCustomMessageService.getItems(catalog, searchString, limitFlag, formats);

		return items;
	}
	/**
	 * For pagination in locate result page
	 * @param mapping
	 * @return int
	 */
	public int getRecordCount(Mapping mapping, List status) {
		return locateCustomMessageService.getRecordCount(mapping, status);
	}
	/**
	 * For getting exact match/retrieving records based on the status and logged in user
	 * @param Mapping
	 * @param status
	 * @param loggedInUser
	 * @return List
	 */
	public List getRecords(Mapping mapping, List status, String loggedInUser,int noOfRecords, int auditTrailCount,  Page page) {

		
		return locateCustomMessageService.getRecords(mapping, status, loggedInUser, noOfRecords, auditTrailCount, page);

	}
	/**
	 * After the mapping is in published status, when the user clicks on edit, 
	 * this method will insert record in temp table and used for updating the temp
	 * @param mapping
	 * @return Mapping
	 */
	public MappingResult getRecordsForUpdate(Mapping mapping) {
		return locateCustomMessageService.getRecordsForUpdate(mapping);
	}
	/**
	 * 
	 * @return locateCustomMessageService
	 */
	public LocateService getLocateCustomMessageService() {
		return locateCustomMessageService;
	}
	/**
	 * 
	 * @param locateCustomMessageService
	 */
	public void setLocateCustomMessageService(
			LocateService locateCustomMessageService) {
		this.locateCustomMessageService = locateCustomMessageService;
	}
	public MappingResult getRecordsForUpdate(Mapping mapping, List status, String loggedInUser, int noOfRecords, int auditTrailCount, Page page) {
		return locateCustomMessageService.getRecordsForUpdate(mapping, status, loggedInUser, noOfRecords, auditTrailCount, page);
	}
	public List getRecordsForReport(Mapping mapping, List status) {
		return locateCustomMessageService.getRecordsForReport(mapping, status);
	}
	public Mapping autopopulateBySPSFormat(HippaSegment hippaSegment, Mapping mapping){
		return locateCustomMessageService.autopopulateBySPSFormat(hippaSegment, mapping);
	}
	public Map<String,String> fetchHippaSegmentDescription(String hippaSegmentName){
		return locateCustomMessageService.fetchHippaSegmentDescription(hippaSegmentName);
	}
}
