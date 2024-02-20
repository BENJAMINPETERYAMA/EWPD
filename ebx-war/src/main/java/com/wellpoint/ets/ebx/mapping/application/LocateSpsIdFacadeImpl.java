/*
 * <LocateSpsIdFacadeImpl.java>
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
 * This is an implementation class for locating or searching a SPS id 
 * or multiple SPS id, based on certain search criteria
 */
public class LocateSpsIdFacadeImpl implements LocateFacade {
	
	private LocateService locateSpsIdService;
	/**
	 * For pagination in locate result page
	 * @param mapping
	 * @return int
	 */
	public int getRecordCount(Mapping mapping, List status) {		
		return locateSpsIdService.getRecordCount(mapping, status);
	}
	/**
	 * For getting exact match/retrieving records based on the status and logged in user
	 * @param Mapping
	 * @param status
	 * @param loggedInUser
	 * @return List
	 */
	public List getRecords(Mapping mapping, List status, String loggedInUser,int noOfRecords, int auditTrailCount, Page page) {
		
		return locateSpsIdService.getRecords(mapping, status, loggedInUser, noOfRecords, auditTrailCount, page);
	}
	/**
	 * After the mapping is in published status, when the user clicks on edit, 
	 * this method will insert record in temp table and used for updating the temp
	 * @param mapping
	 * @return Mapping
	 */
	public MappingResult getRecordsForUpdate(Mapping mapping) {
		return locateSpsIdService.getRecordsForUpdate(mapping);
	}
	
	/**
	 * for retrieving hipaa code values starting with the searchString. 
	 * Flag can be used to indicate whether to retrieve all the records or limit it to 50
	 * (depends on how many records we need to display)
	 * @param catalog
	 * @param searchString
	 * @param flag
	 * @return List
	 */
	public List getItems(Catalog catalog, String searchString, boolean limitFlag,List formats){	
		return locateSpsIdService.getItems(catalog, searchString, limitFlag,formats);	
	}
	public LocateService getLocateSpsIdService() {
		return locateSpsIdService;
	}
	public void setLocateSpsIdService(LocateService locateSpsIdService) {
		this.locateSpsIdService = locateSpsIdService;
	}
	public MappingResult getRecordsForUpdate(Mapping mapping, List status, String loggedInUser, int noOfRecords, int auditTrailCount, Page page) {
		return locateSpsIdService.getRecordsForUpdate(mapping, status, loggedInUser, noOfRecords, auditTrailCount, page);
	}
	/**
	 * for generating reports from locate
	 * @param mapping
	 * @return List
	 */
	public List getRecordsForReport(Mapping mapping, List status) {
		return locateSpsIdService.getRecordsForReport(mapping, status);
	}
	
	public Mapping autopopulateBySPSFormat(HippaSegment hippaSegment, Mapping mapping){
		return locateSpsIdService.autopopulateBySPSFormat(hippaSegment, mapping);
	}
	
	public Map<String,String> fetchHippaSegmentDescription(String hippaSegmentName){
		return locateSpsIdService.fetchHippaSegmentDescription(hippaSegmentName);
	}
	
	
}