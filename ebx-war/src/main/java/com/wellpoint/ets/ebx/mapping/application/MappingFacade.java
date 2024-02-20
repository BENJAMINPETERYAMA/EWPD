/*
 * <MappingFacade.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.mapping.application;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.vo.MappingResult;
import com.wellpoint.ets.bx.mapping.util.MassUpdateTracker;

/**
 * @author UST-GLOBAL This is an interface class for performing all the life
 *         cycle operations on a mapping
 */
public interface MappingFacade {
	/**
	 * Creating a new Mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	MappingResult create(Mapping mapping, String userComments);

	/**
	 * updating an existing mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	MappingResult update(Mapping mapping, String userComments);

	/**
	 * scheduling to test a mapping -> Updating the status alone
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	MappingResult sendToTest(Mapping mapping, String userComments);

	/**
	 * scheduling to production a mapping -> Updating the status alone
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	MappingResult approve(Mapping mapping, String userComments);

	/**
	 * marking as not applicable a mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	MappingResult notApplicable(Mapping mapping, String userComments);

	/**
	 * discarding changes to an already Published mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	MappingResult discardChanges(Mapping mapping, String userComments);
	
	/**
	 * Updating the mapping and scheduling to test
	 * @param mapping
	 * @param userComments
	 * @return
	 */
	MappingResult updateAndSendToTest(Mapping mapping, String userComments);
	
	/**
	 * Updating the mapping and scheduling to production 
	 * @param mapping
	 * @param userComments
	 * @return
	 */
	MappingResult updateAndApprove(Mapping mapping, String userComments);
	/**
	 * An existing mapping of custom message is deleted and a datafeed entry is made
	 * @param Mapping
	 * @param userComments
	 * @return MappingResult
	 */
	MappingResult delete (Mapping mapping, String userComments);
	
	/**
	 * mass updating existing mapping
	 * @param mapping
	 * @param userComments
	 * @param massUpdateTracker
	 * @return
	 */
	List update(List massUpdateCriteria, String userComments, MassUpdateTracker massUpdateTracker);

	/**
	 * scheduling to test list of mapping -> Updating the status alone
	 * 
	 * @param mapping
	 * @param userComments
	 * @param massUpdateTracker
	 * @return MappingResult
	 */
	List sendToTest(List massUpdateCriteria, String userComments, MassUpdateTracker massUpdateTracker);

	/**
	 * scheduling to production list of mapping -> Updating the status alone
	 * 
	 * @param mapping
	 * @param userComments
	 * @param massUpdateTracker
	 * @return MappingResult
	 */
	List approve(List massUpdateCriteria, String userComments, MassUpdateTracker massUpdateTracker);

	/**
	 * marking as not applicable list of mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @param massUpdateTracker
	 * @return MappingResult
	 */
	List notApplicable(List massUpdateCriteria, String userComments, MassUpdateTracker massUpdateTracker);
	/**
	 * Unlock a mapping - can be done only by admin user
	 * @param mapping
	 * @return
	 */
	boolean unlock (Mapping mapping);
}
