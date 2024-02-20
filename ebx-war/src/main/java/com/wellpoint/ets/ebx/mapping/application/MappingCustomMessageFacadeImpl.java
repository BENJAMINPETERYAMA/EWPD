/*
 * <MappingCustomMessageFacadeImpl.java>
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
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.service.LockService;
import com.wellpoint.ets.bx.mapping.domain.vo.MappingResult;
import com.wellpoint.ets.bx.mapping.util.MassUpdateTracker;
import com.wellpoint.ets.ebx.mapping.domain.service.MappingService;
/**
 * @author UST-GLOBAL
 *This is an implementation class for performing all the
 *         life cycle operations on Custom Message mapping
 */
public class MappingCustomMessageFacadeImpl implements MappingFacade {
	
	private MappingService mappingCustomMessageService;
	private LockService lockCustomMessageService;
	/**
	 * scheduling to production a mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public MappingResult approve(Mapping mapping, String userComments) {	
		String status = DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION;		
		mapping.setVariableMappingStatus(status);		
		return mappingCustomMessageService.updateStatus(mapping, userComments, status);
	}
	/**
	 * Creating a new Mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public MappingResult create(Mapping mapping, String userComments) {
		return mappingCustomMessageService.create(mapping, userComments);
	}

	/**
	 * discarding changes to an already Published mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public MappingResult discardChanges(Mapping mapping, String userComments) {
		return mappingCustomMessageService.discardChanges(mapping, userComments);
	}

	/**
	 * marking as not applicable a mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public MappingResult notApplicable(Mapping mapping, String userComments) {
		return null;
	}

	/**
	 * scheduling to test a mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public MappingResult sendToTest(Mapping mapping, String userComments) {	
		String status = DomainConstants.STATUS_SCHEDULED_TO_TEST;		
		mapping.setVariableMappingStatus(status);	
		return mappingCustomMessageService.updateStatus(mapping, userComments, status);

	}
	/**
	 * updating an existing mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public MappingResult update(Mapping mapping, String userComments) {
		 return mappingCustomMessageService.update(mapping, userComments);
	}
	/**
	 * Updating the mapping and scheduling to production 
	 * @param mapping
	 * @param userComments
	 * @return
	 */
	public MappingResult updateAndApprove(Mapping mapping, String userComments) {
		MappingResult result = mappingCustomMessageService.update(mapping, userComments);
		String status = DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION;
		mapping.setVariableMappingStatus(status);
		if(result.isStatus()){
			result = mappingCustomMessageService.updateStatus(mapping, userComments, status);
		}
		return result;
	}
	/**
	 * Updating the mapping and scheduling to test
	 * @param mapping
	 * @param userComments
	 * @return
	 */
	public MappingResult updateAndSendToTest(Mapping mapping, String userComments) {
		MappingResult result = mappingCustomMessageService.update(mapping, userComments);
		String status = DomainConstants.STATUS_SCHEDULED_TO_TEST;
		mapping.setVariableMappingStatus(status);
		if(result.isStatus()){
			result = mappingCustomMessageService.updateStatus(mapping, userComments, status);
		}
		return result;
	}

	/**
	 * An existing mapping of custom message is deleted and a datafeed entry is made
	 * @param Mapping
	 * @param userComments
	 * @return MappingResult
	 */
	 public MappingResult delete(Mapping mapping, String userComments) {
		 return mappingCustomMessageService.delete(mapping, userComments);
	}
	 /**
	  * 
	  * @return mappingCustomMessageService
	  */
	 public MappingService getMappingCustomMessageService() {
		 return mappingCustomMessageService;
	 }
	
	 /**
	  * 
	  * @param mappingCustomMessageService
	  */
	 public void setMappingCustomMessageService(
			 MappingService mappingCustomMessageService) {
		 this.mappingCustomMessageService = mappingCustomMessageService;
	 }
	 
	 /**
	 * scheduling to production list of mapping -> Updating the status alone
	 * 
	 * @param mapping
	 * @param userComments
	 * @param massUpdateTracker
	 * @return MappingResult
	 */
	public List approve(List massUpdateCriteria, String userComments, MassUpdateTracker massUpdateTracker) {
		return mappingCustomMessageService.updateStatus(massUpdateCriteria, userComments, DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION, massUpdateTracker);
	}
	
	/**
	 * marking as not applicable array of mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @param massUpdateTracker
	 * @return MappingResult
	 */
	public List notApplicable(List massUpdateCriteria, String userComments, MassUpdateTracker massUpdateTracker) {
		return mappingCustomMessageService.updateStatus(massUpdateCriteria, userComments, DomainConstants.STATUS_NOT_APPLICABLE, massUpdateTracker);
	}
	
	/**
	 * scheduling to production array of mapping -> Updating the status alone
	 * 
	 * @param mapping
	 * @param userComments
	 * @param massUpdateTracker
	 * @return MappingResult
	 */
	public List sendToTest(List massUpdateCriteria, String userComments, MassUpdateTracker massUpdateTracker) {
		return mappingCustomMessageService.updateStatus(massUpdateCriteria, userComments, DomainConstants.STATUS_SCHEDULED_TO_TEST, massUpdateTracker);
	}
	
	 /**
	 * scheduling to test array of mapping -> Updating the status alone
	 * 
	 * @param mapping
	 * @param userComments
	 * @param massUpdateTracker
	 * @return MappingResult
	 */
	public List update(List massUpdateCriteria, String userComments, MassUpdateTracker massUpdateTracker) {
		return mappingCustomMessageService.update(massUpdateCriteria, userComments, massUpdateTracker);
	}
	
	public boolean unlock(Mapping mapping) {
		boolean lockflag = lockCustomMessageService.deleteLock(mapping);
		return lockflag;
	}
	
	public LockService getLockCustomMessageService() {
		return lockCustomMessageService;
	}
	
	public void setLockCustomMessageService(LockService lockCustomMessageService) {
		this.lockCustomMessageService = lockCustomMessageService;
	}

}
