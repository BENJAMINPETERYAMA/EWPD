/*
 * <MappingRuleIdFacadeImpl.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.mapping.application;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.service.LockService;
import com.wellpoint.ets.bx.mapping.domain.vo.MappingResult;
import com.wellpoint.ets.bx.mapping.util.MassUpdateTracker;
import com.wellpoint.ets.ebx.mapping.domain.service.MappingService;

/**
 * @author UST-GLOBAL This is an implementation class for performing all the
 *         life cycle operations on Rule Id mapping
 */
public class MappingRuleIdFacadeImpl implements MappingFacade {
	
	private MappingService mappingRuleIdService;
	private LockService lockRuleIdService;
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
			
		MappingResult result = mappingRuleIdService.updateStatus(mapping, userComments, status);
	
		return result;
	}

	/**
	 * Creating a new Mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public MappingResult create(Mapping mapping, String userComments) {
		
		return mappingRuleIdService.create(mapping, userComments);
	}

	/**
	 * discarding changes to an already Published mapping
	 * @param userComments
	 * @param mapping
	 * @return MappingResult
	 */
	public MappingResult discardChanges(Mapping mapping, String userComments) {
		
		return mappingRuleIdService.discardChanges(mapping, userComments);
	}

	/**
	 * marking as not applicable a mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public MappingResult notApplicable(Mapping mapping, String userComments) {
		
		String status = DomainConstants.STATUS_NOT_APPLICABLE;
		
		MappingResult mappingResult = mappingRuleIdService.updateStatus(mapping, userComments, status);
		
		return mappingResult;
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
		
		MappingResult result = mappingRuleIdService.updateStatus(mapping, userComments, status);
		
		return result;
	}
	/**
	 * Updating the mapping and scheduling to production 
	 * @param mapping
	 * @param userComments
	 * @return
	 */
	public MappingResult updateAndApprove(Mapping mapping, String userComments) {
		
		MappingResult result = mappingRuleIdService.update(mapping, userComments);
		List warningMsgs = new ArrayList();
		if(null != result.getWarningMsgsList()){
			
			warningMsgs = result.getWarningMsgsList();
		}
		String status = DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION;
		
		mapping.setVariableMappingStatus(status);
		
		if(result.isStatus()){	
			
			result = mappingRuleIdService.updateStatus(mapping, userComments, status);
			result.setWarningMsgsList(warningMsgs);
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

		MappingResult result = mappingRuleIdService.update(mapping, userComments);
		List warningMsgs = new ArrayList();
		if(null != result.getWarningMsgsList()){
			
			warningMsgs = result.getWarningMsgsList();
		}
		String status = DomainConstants.STATUS_SCHEDULED_TO_TEST;
		
		mapping.setVariableMappingStatus(status);
		
		if(result.isStatus()){
			
			result = mappingRuleIdService.updateStatus(mapping, userComments, status);
			result.setWarningMsgsList(warningMsgs);
		}
		
		return result;
	}
	
	/**
	 * updating an existing mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public MappingResult update(Mapping mapping, String userComments) {
		
		return mappingRuleIdService.update(mapping, userComments);
	}
	
	/**
	 * Unlocks a mapping for another user to edit the mapping
	 * @param mapping
	 * @return boolean
	 */
	public boolean unlock(Mapping mapping) {
		boolean lockflag = lockRuleIdService.deleteLock(mapping);
		return lockflag;
	}
	
	public MappingService getMappingRuleIdService() {
		return mappingRuleIdService;
	}

	public void setMappingRuleIdService(MappingService mappingRuleIdService) {
		this.mappingRuleIdService = mappingRuleIdService;
	}

	/**
	 * An existing mapping of custom message is deleted and a datafeed entry is made
	 * @param Mapping
	 * @param userComments
	 * @return MappingResult
	 */
	 public MappingResult delete(Mapping mapping, String userComments) {
		return null;
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
		return mappingRuleIdService.updateStatus(massUpdateCriteria, userComments, DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION, massUpdateTracker);
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
		return mappingRuleIdService.updateStatus(massUpdateCriteria, userComments, DomainConstants.STATUS_NOT_APPLICABLE, massUpdateTracker);
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
		return mappingRuleIdService.updateStatus(massUpdateCriteria, userComments, DomainConstants.STATUS_SCHEDULED_TO_TEST, massUpdateTracker);
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
		return mappingRuleIdService.update(massUpdateCriteria, userComments, massUpdateTracker);
	}

	public LockService getLockRuleIdService() {
		return lockRuleIdService;
	}

	public void setLockRuleIdService(LockService lockRuleIdService) {
		this.lockRuleIdService = lockRuleIdService;
	}
}
