/*
 * <MappingService.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.mapping.domain.service;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.vo.MappingResult;
import com.wellpoint.ets.bx.mapping.util.MassUpdateTracker;

/**
 * @author UST-GLOBAL This is an interface class for performing all the life
 *         cycle operations on a mapping
 */
public interface MappingService {
	/**
	 * Creating a new Mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	MappingResult create (Mapping mapping, String userComments);
	/**
	 * updating an existing mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	MappingResult update (Mapping mapping, String userComments);
	/**
	 * The methods (sendToTest, approve,notApplicable) in façade will invoke this method for updating the status  of the mapping
	 * @param mapping
	 * @param userComments
	 * @param status
	 * @return MappingResult
	 */
	MappingResult updateStatus  (Mapping mapping, String userComments, String status);
	/**
	 * discarding changes to an already Published mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	MappingResult discardChanges (Mapping mapping, String userComments);
	/**
	 * 
	 * @param mapping
	 */
	void publish(Mapping mapping) ;
	
	/**
	 * 
	 * @param mapping
	 */
	void transferredToTestRegion(Mapping mapping);
	/**
	 * An existing mapping of custom message is deleted and a datafeed entry is made
	 * @param Mapping
	 * @param userComments
	 * @return MappingResult
	 */
	MappingResult delete (Mapping mapping, String userComments);
	/**
	 *This service layer method updates the feed_run_flag as “T” after the datafeed to IMSN region is executed 
	 *	and to “P” after the datafeed to IMSP region is executed.
	 * @param Mapping
	 * @param status
	 * @return MappingResult
	 */
	MappingResult updateDatafeedStatus(Mapping mapping ,String status);
	
	/**
	 * updating array of existing mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @param massUpdateTracker
	 * @return MappingResult
	 */
	List update (List massUpdateCriteria, String userComments, MassUpdateTracker massUpdateTracker);
	
	/**
	 * The methods (sendToTest, approve,notApplicable) in façade will invoke this method for updating the status  of list of mapping
	 * @param mapping
	 * @param userComments
	 * @param status
	 * @param massUpdateTracker
	 * @return MappingResult
	 */
	List updateStatus  (List massUpdateCriteria, String userComments, String status, MassUpdateTracker massUpdateTracker);
}
