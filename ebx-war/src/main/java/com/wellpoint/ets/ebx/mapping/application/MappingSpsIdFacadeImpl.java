/*
 * <MappingSpsIdFacadeImpl.java>
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
 * @author UST-GLOBAL
 * This is an implementation class for performing all the
 *         life cycle operations on SPS Id mapping
 */
public class MappingSpsIdFacadeImpl implements MappingFacade {
	private MappingService mappingSpsIdService;
	private LockService lockSpsIdService;
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
			
		return mappingSpsIdService.updateStatus(mapping, userComments, status);
	}
	/**
	 * Creating a new Mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public MappingResult create(Mapping mapping, String userComments) {		
		return mappingSpsIdService.create(mapping, userComments);
	}

	/**
	 * discarding changes to an already Published mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public MappingResult discardChanges(Mapping mapping, String userComments) {
		return mappingSpsIdService.discardChanges(mapping, userComments);
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
		return mappingSpsIdService.updateStatus(mapping, userComments, status);		
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
		
		return mappingSpsIdService.updateStatus(mapping, userComments, status);
	}

	/**
	 * updating an existing mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public MappingResult update(Mapping mapping, String userComments) {
		return mappingSpsIdService.update(mapping, userComments);
	}
	/**
	 * Updating the mapping and scheduling to production 
	 * @param mapping
	 * @param userComments
	 * @return
	 */
	public MappingResult updateAndApprove(Mapping mapping, String userComments) {
		
		MappingResult result = mappingSpsIdService.update(mapping, userComments);
		List warningMsgs = new ArrayList();
		if(null != result.getWarningMsgsList()){
			
			warningMsgs = result.getWarningMsgsList();
		}
		String status = DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION;
		
		mapping.setVariableMappingStatus(status);
		
		if(result.isStatus()){			
			
			result = mappingSpsIdService.updateStatus(mapping, userComments, status);
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
	
		MappingResult result = mappingSpsIdService.update(mapping, userComments);
		List warningMsgs = new ArrayList();
		if(null != result.getWarningMsgsList()){
			
			warningMsgs = result.getWarningMsgsList();
		}
		String status = DomainConstants.STATUS_SCHEDULED_TO_TEST;
		
		mapping.setVariableMappingStatus(status);
		
		if(result.isStatus()){
			result = mappingSpsIdService.updateStatus(mapping, userComments, status);
			result.setWarningMsgsList(warningMsgs);
		}
		
		return result;
	}
	public MappingService getMappingSpsIdService() {
		return mappingSpsIdService;
	}
	public void setMappingSpsIdService(MappingService mappingSpsIdService) {
		this.mappingSpsIdService = mappingSpsIdService;
	}
	
	/**
	 * An existing mapping of custom message is deleted and a datafeed entry is made
	 * @param Mapping
	 * @param userComments
	 * @return MappingResult
	 */
	 public MappingResult delete(Mapping mapping,String userComments) {
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
		return mappingSpsIdService.updateStatus(massUpdateCriteria, userComments, DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION, massUpdateTracker);
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
		return mappingSpsIdService.updateStatus(massUpdateCriteria, userComments, DomainConstants.STATUS_NOT_APPLICABLE, massUpdateTracker);
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
		return mappingSpsIdService.updateStatus(massUpdateCriteria, userComments, DomainConstants.STATUS_SCHEDULED_TO_TEST, massUpdateTracker);
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
		return mappingSpsIdService.update(massUpdateCriteria, userComments, massUpdateTracker);
	}
	
	public boolean unlock(Mapping mapping) {
		boolean lockflag = lockSpsIdService.deleteLock(mapping);
		return lockflag;
	}
	
	public LockService getLockSpsIdService() {
		return lockSpsIdService;
	}
	
	public void setLockSpsIdService(LockService lockSpsIdService) {
		this.lockSpsIdService = lockSpsIdService;
	}
}
