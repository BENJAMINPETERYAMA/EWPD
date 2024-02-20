/*
 * <MappingRepository.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.mapping.repository;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSMappingRetrieveResult;
/**
 * @author UST-GLOBAL This is an interface class for performing all the life
 *         cycle operations on a mapping
 */
public interface MappingRepository {
	/**
	 * Creating a new Mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	boolean create (Mapping mapping,String userComments);
	/**
	 * updating an existing mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	boolean update (Mapping mapping,String userComments,String operation);
	/**
	 * The methods (sendToTest, approve,notApplicable) in façade will invoke this method for updating the status  of the mapping
	 * @param mapping
	 * @param userComments
	 * @param status
	 * @return MappingResult
	 */
	boolean updateStatus  (Mapping mapping, String userComments, String operation);
	/**
	 * discarding changes to an already Published mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	boolean discardChanges (Mapping mapping, String userComments);
	
	/**
	 * 
	 * @param mapping
	 * @param userComments
	 * @return
	 */
	boolean publish(Mapping mapping, String userComments);
	/**
	 * An existing mapping of custom message is deleted and a datafeed entry is made
	 * @param Mapping
	 * @param userComments
	 * @return MappingResult
	 */
	boolean delete (Mapping mapping, String userComments);
	/**
	 * This method updates the feed_run_flag as “T” after the datafeed to IMSN region is executed 
	 * and to “P” after the datafeed to IMSP region is executed.
	 * @param Mapping
	 * @return boolean
	 */
	boolean updateDatafeedStatus(Mapping mapping);
	
	
	
	SPSMappingRetrieveResult getSPSMappingMain(String spsId);
	
	
}
