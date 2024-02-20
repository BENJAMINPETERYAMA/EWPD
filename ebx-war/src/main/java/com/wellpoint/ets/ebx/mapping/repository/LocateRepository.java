/*
 * <LocateRepository.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/
package com.wellpoint.ets.ebx.mapping.repository;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Page;
/**
 * @author UST-GLOBAL
 * This is an interface class for locating or searching a sps id, header rule or custom message
 * or multiple based on certain search criteria
 */
public interface LocateRepository {
	/**
	 * For pagination in locate result page
	 * @param mapping
	 * @return int
	 */
	int getRecordCount(Mapping mapping, List status) ;
	/**
	 * For getting exact match/retrieving records based on the status and logged in user
	 * @param Mapping
	 * @param status
	 * @param loggedInUser
	 * @return List
	 */
	List getRecords(Mapping mapping, List status, String loggedInUser,int noOfRecords, int auditTrailCount, Page page) ;
	List getRecordsForReport(Mapping mapping, List status);
}
