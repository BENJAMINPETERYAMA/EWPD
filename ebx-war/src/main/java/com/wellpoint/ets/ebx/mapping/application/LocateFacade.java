/*
 * <LocateFacade.java>
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
/**
 * @author UST-GLOBAL
 * This is an interface class for locating or searching a sps id, header rule or custom message
 * or multiple based on certain search criteria
 */
public interface LocateFacade {
	/**
	 * For pagination in locate result page
	 * @param mapping
	 * @return int
	 */
	public int getRecordCount(Mapping mapping, List status) ;
	/**
	 * For getting exact match/retrieving records based on the status and logged in user
	 * @param Mapping
	 * @param status
	 * @param loggedInUser
	 * @return List
	 */
	List getRecords(Mapping mapping, List status, String loggedInUser,int noOfRecords, int auditTrailCount,  Page page) ;

	/**
	 * for retrieving hipaa code values starting with the searchString. 
	 * Flag can be used to indicate whether to retrieve all the records or limit it to 50
	 * (depends on how many records we need to display), format is to filter the EB01 popup based on SPS format
	 * @param catalog
	 * @param searchString
	 * @param flag
	 * @param format
	 * @return List
	 */
	List getItems(Catalog catalog, String searchString, boolean limitFlag, List formats);
	/**
	 * After the mapping is in published status, when the user clicks on edit, 
	 * this method will insert record in temp table and used for updating the temp
	 * @param mapping
	 * @return Mapping
	 */
	MappingResult getRecordsForUpdate(Mapping mapping);
	
	public MappingResult getRecordsForUpdate(Mapping mapping, List status, String loggedInUser,int noOfRecords, int auditTrailCount,  Page page);

	List getRecordsForReport(Mapping mapping, List status);
	/**
	 * 
	 * @param hippaSegment
	 * @param mapping
	 * @return mapping
	 */
	
	Mapping autopopulateBySPSFormat(HippaSegment hippaSegment, Mapping mapping);
	
	/**
     * @param hippaSegmentName
     * @return Map<String , String>-- HippaCodeValue and Description
     */
    public Map<String,String> fetchHippaSegmentDescription(String hippaSegmentName);
}
