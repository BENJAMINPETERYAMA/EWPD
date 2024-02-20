/*
 * <MappingRuleIdServiceImpl.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.mapping.domain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.owasp.esapi.ESAPI;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.exception.MappingLockedByAnotherUserException;
import com.wellpoint.ets.bx.mapping.domain.service.AuditTrailService;
import com.wellpoint.ets.bx.mapping.domain.service.BXOutboundDataFeedHelperService;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.service.HippaSegmentService;
import com.wellpoint.ets.bx.mapping.domain.service.LockService;
import com.wellpoint.ets.bx.mapping.domain.service.ValidatorConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.MappingResult;
import com.wellpoint.ets.bx.mapping.domain.vo.MassUpdateCriteria;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.util.MappingUtil;
import com.wellpoint.ets.bx.mapping.util.MassUpdateTracker;
import com.wellpoint.ets.bx.mapping.web.BxResourceBundle;
import com.wellpoint.ets.ebx.mapping.repository.MappingRepository;
import com.wellpoint.ets.ebx.simulation.util.SimulationResourceBundle;
/**
 * @author UST-GLOBAL This is an implementation class for performing all the life
 *         cycle operations on Rule Id mapping
 */
public class MappingRuleIdServiceImpl implements MappingService {
	
	private MappingRepository mappingRuleIdRepository;
	private AuditTrailService auditTrailService;
	private LockService lockRuleIdService;
	private LocateService locateRuleIdService;	
	private ValidationService ewpdValidationService;
	private HippaSegmentService hippaSegmentService;
	private BXOutboundDataFeedHelperService bxoutboundDataFeedHelperService;
	private static Logger log = Logger.getLogger(MappingRuleIdServiceImpl.class);
	/**
	 * Creating a new Mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public MappingResult create(Mapping mapping, String userComments) {
		
		MappingResult mappingResult = new MappingResult();
		boolean createStatus = false;
		String errorMessage = null;
		List errorMessagesList = new ArrayList();
		
		if (null != mapping) {
			mapping.setVariableMappingStatus(DomainConstants.STATUS_BUILDING);
			mapping.setInTempTable(DomainConstants.IN_TEMP_TAB_FLAG_PERSIST);
		}
		//Sort UM rules
		if(null != mapping && null != mapping.getUtilizationMgmntRule() && null != mapping.getUtilizationMgmntRule().getHippaCodeSelectedValues()){
			Collections.sort(mapping.getUtilizationMgmntRule().getHippaCodeSelectedValues());
		}
		mappingResult.setMapping(mapping);
		
		if (null != mapping) {
			mapping.setLocateRuleIdService(locateRuleIdService);			
			mapping.setEwpdValidationService(ewpdValidationService);
			mapping.setHippaSegmentService(hippaSegmentService);
		}
		
		List mappings = locateRuleIdService.getRecords(mapping, null, null, -1, 21, null);
		
		if((null != mappings) && (! mappings.isEmpty())){
			
			if(null != mappings.get(0)){
				
				Mapping retrievedMapping = (Mapping) mappings.get(0);
				if(null != retrievedMapping){
		        	errorMessage = BxResourceBundle.getResourceBundle(ValidatorConstants.MAPPING_EXISTS,null);
		        	errorMessagesList.add(errorMessage);
		        	mappingResult.setErrorMsgsList(errorMessagesList);
		        	mappingResult.setStatus(false);
		        	mappingResult.setMapping(mapping);
				    return mappingResult;
				}
			}
		}
		//change in calling the validator framework for section3
		List hippaSegmentValidationResultList = mapping.validateMappings();
		mappingResult = new MappingResult(hippaSegmentValidationResultList);
		//mappingResult = new EbxApplicationContext().getHeaderRuleValidator().validateRuleId(mapping);
		
		if(mappingResult.isStatus()){
			// check for lock
			try{
				lockRuleIdService.aquireLock(mapping);
			 }catch (MappingLockedByAnotherUserException e){
				 	//errorMessage = BxResourceBundle.getResourceBundle(ValidatorConstants.MAPPING_LOCKED_ANOTHER_USER,null);
		        	errorMessagesList.add(e.getMessage());
		        	mappingResult.setErrorMsgsList(errorMessagesList);
		        	mappingResult.setStatus(false);
		        	mappingResult.setMapping(mapping);
				    return mappingResult;
			 }
			
			createStatus = mappingRuleIdRepository.create(mapping, userComments);
			
			if(createStatus){			
				mappingResult.setStatus(true);
			}
		}	
		return mappingResult;
	}
	/**
	 * discarding changes to an already Published mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public MappingResult discardChanges(Mapping mapping, String userComments) {
		
		boolean discardedStatus = mappingRuleIdRepository.discardChanges(mapping, userComments);
		
		lockRuleIdService.deleteLock(mapping);
		MappingResult mappingResult = new MappingResult();
		
		if(discardedStatus){			
			mappingResult.setStatus(true);
		}	
		return mappingResult;		
		
	}
	/**
	 * updating an existing mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public MappingResult update(Mapping mapping, String userComments) {
		
		MappingResult mappingResult = new MappingResult();
		
		boolean updateStatus = false;
		List errorMessagesList = new ArrayList();
		//Sort UM rules
		if(null != mapping && null != mapping.getUtilizationMgmntRule() && null != mapping.getUtilizationMgmntRule().getHippaCodeSelectedValues()){
			Collections.sort(mapping.getUtilizationMgmntRule().getHippaCodeSelectedValues());
		}
		if(null != mapping){
			mapping.setLocateRuleIdService(locateRuleIdService);
			mapping.setHippaSegmentService(hippaSegmentService);	
			mapping.setEwpdValidationService(ewpdValidationService);
		}
		
		mappingResult.setMapping(mapping);
		String operation = null;
		// change in calling the validator framework for section3
		
		List hippaSegmentValidationResultList =null;
		if(null !=mapping){
			hippaSegmentValidationResultList = mapping.validateMappings();
		}
		
		mappingResult = new MappingResult(hippaSegmentValidationResultList);
		//mappingResult = new EbxApplicationContext().getHeaderRuleValidator().validateRuleId(mapping);
		
		// if validation is successful , update the mapping
		if(mappingResult.isStatus()){
//			 validate the user lock
			try{
				lockRuleIdService.validateUserLock(mapping);
			 }catch (MappingLockedByAnotherUserException e){
				 errorMessagesList.add(e.getMessage());
		        	mappingResult.setErrorMsgsList(errorMessagesList);
		        	mappingResult.setStatus(false);
		        	mappingResult.setMapping(mapping);
				    return mappingResult;
			 }
			
			//calling the getRecords() of the locateService and  check the the IN_TEMP_TAB and the status to decide the operation		
			 /*
			 * This part added for massupdate, when this method is get called from massupdate
			 * the mapping passed is taken from db. This is added to avoid refech.
			 */
			 List mappingsList= null;				

			 mappingsList = (ArrayList)locateRuleIdService.getRecords(mapping, null , null,	-1, 21, null);
			
			if((null != mappingsList) && !mappingsList.isEmpty() && (null != mappingsList.get(0))){
				
					Mapping retrievedMapping = (Mapping) mappingsList.get(0);								
					mappingResult.setPreviousVariableMappingStatus(retrievedMapping.getVariableMappingStatus());
						//if the IN_TEMP_TAB is "N" and the current status is (BUILDING or SCHEDULED_TO_TEST or SCHEDULED_TO_PRODUCTION or OBJECT_TRANSFERRED)
						// operation is UPDATE_MAIN
						if((DomainConstants.IN_TEMP_TAB_FLAG_PERSIST).equals(retrievedMapping.getInTempTable())
								&& ((DomainConstants.STATUS_BUILDING).equals(retrievedMapping.getVariableMappingStatus())
								|| (DomainConstants.STATUS_SCHEDULED_TO_TEST.equals(retrievedMapping.getVariableMappingStatus()))
								|| (DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(retrievedMapping.getVariableMappingStatus()))
								|| (DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION.equals(retrievedMapping.getVariableMappingStatus()))
								|| (DomainConstants.STATUS_NOT_APPLICABLE.equals(retrievedMapping.getVariableMappingStatus())))){
							
							operation = DomainConstants.UPDATE_MAIN_OPERATION;
							mapping.setVariableMappingStatus(DomainConstants.STATUS_BUILDING);
							mapping.setInTempTable(DomainConstants.IN_TEMP_TAB_FLAG_PERSIST);
							
						}
						/*
						 * if the IN_TEMP_TAB is "Y" and the current status is (BEING_MODIFIED or SCHEDULED_TO_TEST or SCHEDULED_TO_PRODUCTION or OBJECT_TRANSFERRED)
						 * operation is UPDATE_TEMP
						 */
						if((DomainConstants.IN_TEMP_TAB_FLAG_UPDATE).equals(retrievedMapping.getInTempTable())
								&& ((DomainConstants.STATUS_BEING_MODIFIED).equals(retrievedMapping.getVariableMappingStatus())
								|| (DomainConstants.STATUS_SCHEDULED_TO_TEST.equals(retrievedMapping.getVariableMappingStatus()))
								|| (DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(retrievedMapping.getVariableMappingStatus()))
								|| (DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION.equals(retrievedMapping.getVariableMappingStatus())))){								
							
							operation = DomainConstants.UPDATE_TEMP_OPERATION;
							mapping.setVariableMappingStatus(DomainConstants.STATUS_BEING_MODIFIED);
							mapping.setInTempTable(DomainConstants.IN_TEMP_TAB_FLAG_UPDATE);
							
						}
						/*
						 * if the current status is PUBLISHED operation is INSERT_TEMP, set the retrieved mapping's status to BEING_MODIFIED
						 */
						if(((DomainConstants.STATUS_PUBLISHED).equals(retrievedMapping.getVariableMappingStatus()))
								&& ((DomainConstants.IN_TEMP_TAB_FLAG_PERSIST).equals(retrievedMapping.getInTempTable()))){
													
							operation = DomainConstants.INSERT_TEMP_OPERATION;
						}
						/**
						 * editing a NOT_APPLICABLE mapping , update main table
						 */
						if((DomainConstants.STATUS_NOT_APPLICABLE).equals(mapping.getVariableMappingStatus())){
							
							mapping.setVariableMappingStatus(DomainConstants.STATUS_BUILDING);
							operation = DomainConstants.UPDATE_MAIN_OPERATION;
							
						}
						updateStatus = mappingRuleIdRepository.update(mapping, userComments, operation);
				}
			manageDeltaNotApplicableDetails(mappingsList, mapping);

		}
		if(updateStatus){
			
			mappingResult.setStatus(true);
		}
		else{
			
			mappingResult.setStatus(false);
		}
		return mappingResult;
	}
	/**
	 * The methods (sendToTest, approve,notApplicable) in façade will invoke this method for updating the status  of the mapping
	 * @param mapping
	 * @param userComments
	 * @param status
	 * @return MappingResult
	 */
	public MappingResult updateStatus(Mapping mapping, String userComments,
			String status) {
		List errorMessagesList = new ArrayList();
		MappingResult mappingResult = new MappingResult();
		
		mappingResult.setMapping(mapping);
		try{
			lockRuleIdService.validateUserLock(mapping);
		 }catch (MappingLockedByAnotherUserException e){
			 	errorMessagesList.add(e.getMessage());
	        	mappingResult.setErrorMsgsList(errorMessagesList);
	        	mappingResult.setStatus(false);
	        	mappingResult.setMapping(mapping);
			    return mappingResult;
		 }
		lockRuleIdService.deleteLock(mapping);
		
		boolean updateStatus = false;
		String operation = null;
		
		//calling the getRecords() of the locateService and  check the the IN_TEMP_TAB and the status to decide the operation		
		
		/*
		 * This part added for massupdate, when this method is get called from massupdate
		 * the mapping passed is taken from db. This is added to avoid refech.
		 */
		 List mappings= null;
			
		mappings = (ArrayList) locateRuleIdService.getRecords(mapping, null, mapping.getUser().getLastUpdatedUserName(), -1, 21, null);
		
		if((null != mappings) && (! mappings.isEmpty())){
			
			if(null != mappings.get(0)){
				
				Mapping retrievedMapping = (Mapping) mappings.get(0);			
					/*
					 * if the IN_TEMP_TAB is "N" and the status is (BUILDING or SCHEDULED_TO_TEST or SCHEDULED_TO_PRODUCTION) operation is 
					 * UPDATE_STATUS_MAIN
					 */
					if((DomainConstants.IN_TEMP_TAB_FLAG_PERSIST).equals(retrievedMapping.getInTempTable())
							&& ((DomainConstants.STATUS_BUILDING).equals(retrievedMapping.getVariableMappingStatus())
							|| (DomainConstants.STATUS_SCHEDULED_TO_TEST.equals(retrievedMapping.getVariableMappingStatus()))
							|| (DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(retrievedMapping.getVariableMappingStatus()))
							|| (DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION.equals(retrievedMapping.getVariableMappingStatus())))){
						operation = DomainConstants.UPDATE_STATUS_MAIN_OPERATION;
						
					}
					/*
					 * if the IN_TEMP_TAB is "Y" and the current status is (BEING_MODIFIED or SCHEDULED_TO_TEST or SCHEDULED_TO_PRODUCTION or OBJECT_TRANSFERRED)
					 * operation is UPDATE_TEMP
					 */
					if((DomainConstants.IN_TEMP_TAB_FLAG_UPDATE).equals(retrievedMapping.getInTempTable())
							&& ((DomainConstants.STATUS_BEING_MODIFIED).equals(retrievedMapping.getVariableMappingStatus())
							|| (DomainConstants.STATUS_SCHEDULED_TO_TEST.equals(retrievedMapping.getVariableMappingStatus()))
							|| (DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(retrievedMapping.getVariableMappingStatus()))
							|| (DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION.equals(retrievedMapping.getVariableMappingStatus())))){
						operation = DomainConstants.UPDATE_STATUS_TEMP_OPERATION;
						
					}
					/*
					 * if the future status is NOT_APPLICABLE  and the current mapping's in temp flag is Y then operation is DELETE_TEMP
					 * update the in temp tab flag to N in main and the status to NOT_APPLICABLE in main
					 */
					if((DomainConstants.STATUS_NOT_APPLICABLE).equals(mapping.getVariableMappingStatus())
							&& ((DomainConstants.IN_TEMP_TAB_FLAG_UPDATE).equals(retrievedMapping.getInTempTable()))){
						operation = DomainConstants.DELETE_TEMP_OPERATION;
						
					}
					/*
					 * if the future status is NOT_APPLICABLE and the current mapping's in temp flag is N then operation is UPDATE_STATUS_MAIN 
					 * with NOT_APPLICABLE
					 */
					if((DomainConstants.STATUS_NOT_APPLICABLE).equals(mapping.getVariableMappingStatus())
							&& ((DomainConstants.IN_TEMP_TAB_FLAG_PERSIST).equals(retrievedMapping.getInTempTable()))){
						operation = DomainConstants.UPDATE_STATUS_MAIN_OPERATION;
					
					}
					if (DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION.equals(mapping.getVariableMappingStatus())
							&& DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(retrievedMapping.getVariableMappingStatus())) {
						mappingResult.setPreviousVariableMappingStatus(DomainConstants.STATUS_OBJECT_TRANSFERRED);
						mapping.setVariableMappingStatus(DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION);
						//mapping.setDataFeedInd("N");
					}
					else{
						BxUtil.doStatusChangeForApprove(mapping);
					}
					updateStatus = mappingRuleIdRepository.updateStatus(mapping, userComments, 
							operation);		
			}	
		}
		/*
		 * if there is no mapping and the future status is NOT_APPLICABLE, create mapping with status as
		 NOT_APPLICABLE - set the status in controller to the mapping object
		 */ 
		else if((mappings == null || mappings.isEmpty()) && 
				(DomainConstants.STATUS_NOT_APPLICABLE.equals(mapping.getVariableMappingStatus()))){	
			
			mapping.setInTempTable(DomainConstants.IN_TEMP_TAB_FLAG_PERSIST);
			// not validation is called , the Eb03 list is cleared
			if(null != mapping.getEb03()){
				mapping.getEb03().getHippaCodeSelectedValues().clear();	
			}	
			if(null != mapping.getUtilizationMgmntRule() && null!=mapping.getUtilizationMgmntRule().getHippaCodeSelectedValues() && !mapping.getUtilizationMgmntRule().getHippaCodeSelectedValues().isEmpty()){
				mapping.getUtilizationMgmntRule().getHippaCodeSelectedValues().clear();
			}	
			updateStatus = mappingRuleIdRepository.create(mapping, userComments);
			
		}
		manageDeltaNotApplicableDetails(mappings, mapping);
		if(updateStatus){
			
			mappingResult.setStatus(updateStatus);
		}		
		return mappingResult;
	}
	
	/**
	 * The method called by the Datafeed to change the status of the MAPPINGS IN 'SCHEDULED_TO_TEST'
	 */
	public void transferredToTestRegion(Mapping mapping) {
		log.debug(ESAPI.encoder().encodeForHTML("Rule transferredToTestRegion "+mapping.getVariableMappingStatus()+" "+mapping.getDataFeedInd()+" "+mapping.getRule().getHeaderRuleId()));
		MappingResult mappingResult = new MappingResult();
		List mappingList = (ArrayList) locateRuleIdService.getRecords(mapping, null , null,	-1, 21, null);		
		String operation = null;
		if (!mappingList.isEmpty()) {
			Mapping mappingToPersist = (Mapping) mappingList.get(0);
			mapping.setDataFeedInd(mappingToPersist.getDataFeedInd());
			if (mappingToPersist.getInTempTable().equals("N")) {
				operation = DomainConstants.UPDATE_STATUS_MAIN_OPERATION;
			} else {
				operation = DomainConstants.UPDATE_STATUS_TEMP_OPERATION;
			}
			User user=new User();
			String username=mappingToPersist.getUser().getLastUpdatedUserName().toString();
			user.setLastUpdatedUserName(username);
			mapping.setUser(user);
			mapping.setDatafeedStatus(DomainConstants.DATAFEED_STATUS);
			BxUtil.doStatusChangeAfterTest(mapping);
			if (mappingRuleIdRepository.updateStatus(mapping, "datafeedUpdate", operation))
			{
				mappingResult.setStatus(true);
			}
		} else {
			mappingResult.setStatus(false);
		}
	}
	
	/**
	 * 
	 * The method called by the Datafeed to publish the MAPPINGS IN 'SCHEDULED_TO_PRODUCTION'
	 *
	 */
	public void publish(Mapping mapping) {		
		List mappingList = (ArrayList) locateRuleIdService.getRecords(mapping, null , null,	-1, 21, null);
		
		if (!mappingList.isEmpty()) {
			Mapping mappingToPersist = (Mapping) mappingList.get(0);
			mappingToPersist.setVariableMappingStatus(DomainConstants.STATUS_PUBLISHED);
			String username=mappingToPersist.getUser().getLastUpdatedUserName().toString();
			mappingToPersist.getUser().setLastUpdatedUserName(username);
			mappingToPersist.setDatafeedStatus(DomainConstants.DATAFEED_STATUS);
			mappingRuleIdRepository.publish(mappingToPersist,DomainConstants.DF_USER_COMMENTS_PUBLISH);
			
			
		}	
	}
	
	/**
	 * @return MappingRepository
	 */
	public MappingRepository getMappingRuleIdRepository() {
		return mappingRuleIdRepository;
	}
	
	/**
	 * @param mappingRuleIdRepository
	 */
	public void setMappingRuleIdRepository(MappingRepository mappingRuleIdRepository) {
		this.mappingRuleIdRepository = mappingRuleIdRepository;
	}
	/**
	 * @return AuditTrailService
	 */
	public AuditTrailService getAuditTrailService() {
		return auditTrailService;
	}
	/**
	 * @param auditTrailService
	 */
	public void setAuditTrailService(AuditTrailService auditTrailService) {
		this.auditTrailService = auditTrailService;
	}
	/**
	 * @return LocateService
	 */
	public LocateService getLocateRuleIdService() {
		return locateRuleIdService;
	}
	/**
	 * @param locateRuleIdService
	 */
	public void setLocateRuleIdService(LocateService locateRuleIdService) {
		this.locateRuleIdService = locateRuleIdService;
	}
	public LockService getLockRuleIdService() {
		return lockRuleIdService;
	}
	public void setLockRuleIdService(LockService lockRuleIdService) {
		this.lockRuleIdService = lockRuleIdService;
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
		 *This  method updates the feed_run_flag as “T” after the datafeed to IMSN region is executed 
		 *	and to “P” after the datafeed to IMSP region is executed.
		 * @param Mapping
		 * @param status
		 * @return MappingResult
		 */
	 public MappingResult updateDatafeedStatus(Mapping mapping,String status) {
			
			return null;
		}
	
	public ValidationService getEwpdValidationService() {
		return ewpdValidationService;
	}
	public void setEwpdValidationService(ValidationService ewpdValidationService) {
		this.ewpdValidationService = ewpdValidationService;
	}
	public HippaSegmentService getHippaSegmentService() {
		return hippaSegmentService;
	}
	public void setHippaSegmentService(HippaSegmentService hippaSegmentService) {
		this.hippaSegmentService = hippaSegmentService;
	}


	/**
	 * The methods (sendToTest, approve,notApplicable) in façade will invoke this method for updating the status  of arry of mapping
	 * @param mapping
	 * @param userComments
	 * @param status
	 * @param massUpdateTracker
	 * @return MappingResult
	 */
	public List updateStatus(List massUpdateCriteria, String userComments, String status, MassUpdateTracker massUpdateTracker) {
		massUpdateTracker.setTotalCount(massUpdateCriteria.size());
		massUpdateTracker.setCompletedCount(0);
		Iterator itr = massUpdateCriteria.iterator();
		List mappingResultList = new ArrayList();
		while(itr.hasNext()){
			Mapping mapping = null;
			try{
				MassUpdateCriteria muc = (MassUpdateCriteria)itr.next();
				mapping = MappingUtil.findMapping(locateRuleIdService, muc);
				boolean isScheduledToProduction = false;
				MappingUtil.checkStatusForStatusUpdate(status, mapping);
				if(!DomainConstants.UNMAPPED_STATUS.equals(mapping.getVariableMappingStatus().trim())){
					mapping.setVariableMappingStatus(status);
					/********Changes for 2011 September Release Starts ***********************/
					MappingResult mappingResult = updateStatus(mapping, userComments, status);
					if (null != mappingResult 
							&& DomainConstants.STATUS_OBJECT_TRANSFERRED
							.equals(mappingResult.getPreviousVariableMappingStatus())
							&& null != mappingResult.getMapping()
							&& DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION
							.equals(mappingResult.getMapping().getVariableMappingStatus())) {
						isScheduledToProduction = true;
					}
					if (isScheduledToProduction) {
						mappingResult.getWarningMsgsList().add("The mapping is scheduled to production");
					}
					else if (DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION.equals(status)) {
						mappingResult.getWarningMsgsList().add("The mapping is scheduled to test and will" +
								" be moved to production in the nightly batch");
					}
					if (isScheduledToProduction) {
						muc.setUpdatedStatus(DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION);
					}
					else {
						muc.setUpdatedStatus(status);
					}
					mappingResultList.add(mappingResult);
					/********Changes for 2011 September Release Ends ***********************/
				}else{
					if(DomainConstants.STATUS_NOT_APPLICABLE.equals(status)){
						mapping.setVariableMappingStatus(status);
						mappingResultList.add(updateStatus(mapping, userComments, status));
					}else{
						MappingResult mappingResult = create(mapping,userComments);
						if(null != mappingResult.getErrorMsgsList() && mappingResult.getErrorMsgsList().size() > 0){
							mappingResultList.add(mappingResult);
						}else{
							mapping.setVariableMappingStatus(status);
							mappingResultList.add(updateStatus(mapping, userComments, status));
						}
					}
					muc.setUpdatedStatus(status);
				}
				
			}catch(EBXException e){
				mappingResultList.add(BxUtil.getExceptionResult(e, mapping));
			}catch(Exception e){
				e.printStackTrace();
				mappingResultList.add(BxUtil.getExceptionResult(e, mapping));
			}
			massUpdateTracker.setCompletedCount(massUpdateTracker.getCompletedCount()+1);
		}
		return mappingResultList;
	}
	
	/**
	 * updating array of existing mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @param massUpdateTracker
	 * @return MappingResult
	 */
	public List update(List massUpdateCriteria, String userComments, MassUpdateTracker massUpdateTracker) {
		massUpdateTracker.setTotalCount(massUpdateCriteria.size());
		massUpdateTracker.setCompletedCount(0);
		Iterator itr = massUpdateCriteria.iterator();
		List mappingResultList = new ArrayList();
		while(itr.hasNext()){
			Mapping mapping = null;
			try{
				MassUpdateCriteria muc = (MassUpdateCriteria)itr.next();
				mapping = MappingUtil.findMapping(locateRuleIdService, muc);
				
				/** SSCR 19537 April 04 - Mass update should be prevented if the user updating 
				 * EB03 associated values for a custom message with individual EB03 mapping. */
				if (DomainConstants.Y.equals(mapping.getIndvdlEb03AssnIndicator()) && MappingUtil.isEb03AssnValuesPresent(muc)) {
					MappingResult mappingResult = new MappingResult();
					List <String>errorMessagesList = new ArrayList <String>();
					//String message = "Mass update cannot be done for this Header Rule "+mapping.getRule().getHeaderRuleId()+" as EB03 level mapping is present";
					List <String> parmList = new ArrayList<String>();
					parmList.add(mapping.getRule().getHeaderRuleId());
					String message = BxResourceBundle.getResourceBundle(ValidatorConstants.MASSUPDATE_ERROR_INVDL_EB03_ASSN_HDR, parmList);
					errorMessagesList.add(message);
					mappingResult.setErrorMsgsList(errorMessagesList);
		        	mappingResult.setStatus(false);
		        	mappingResult.setMapping(mapping);
		        	mappingResultList.add(mappingResult);
				} else {
					mapping = MappingUtil.mergeMapping(mapping, muc);
					MappingUtil.removeEmptyValues(mapping.getEb03());
					mapping.setEb01(null);
					mapping.setEb06(null);
					mapping.setEb09(null);
					mapping.setHsd01(null);
					mapping.setHsd02(null);
					mapping.setHsd03(null);
					mapping.setHsd04(null);
					mapping.setHsd05(null);
					mapping.setHsd06(null);
					mapping.setHsd07(null);
					mapping.setHsd08(null);
					
					//to populate the warning message for sensitive benefit indicator changed to Y warning message --BXNI June 2012 Release
					List warningMessageRuleSensitivePresent = SimulationResourceBundle.getResourceBundle(
							ValidatorConstants.WARNING_MESSAGE_RULE_SENSITIVE_IND_PRESENT,
							ValidatorConstants.PROPERTY_FILE_NAME);
					//to populate the warning message for sensitive benefit indicator changed to N warning message --BXNI June 2012 Release
					List warningMessageRuleSensitiveNotPresent = SimulationResourceBundle.getResourceBundle(
							ValidatorConstants.WARNING_MESSAGE_RULE_SENSITIVE_IND_NOT_PRESENT,
							ValidatorConstants.PROPERTY_FILE_NAME);
					
					boolean isSensitiveBenefitIndicatorChanged = MappingUtil.updateSensitiveBenefitIndicator(mapping);
					
					if(mapping.getVariableMappingStatus().trim().equals(DomainConstants.UNMAPPED_STATUS)){
						MappingResult resultObjectToAddSensitiveWarning = create(mapping,userComments);
						if(isSensitiveBenefitIndicatorChanged && ("Y").equals(mapping.getSensitiveBenefitIndicator())){
							resultObjectToAddSensitiveWarning.getWarningMsgsList().add(warningMessageRuleSensitivePresent);
						}else if(isSensitiveBenefitIndicatorChanged && ("N").equals(mapping.getSensitiveBenefitIndicator())){
							resultObjectToAddSensitiveWarning.getWarningMsgsList().add(warningMessageRuleSensitiveNotPresent);
						}
						mappingResultList.add(resultObjectToAddSensitiveWarning);
					}else{
						MappingResult resultObjectToAddSensitiveWarning = update(mapping,userComments);
						if(isSensitiveBenefitIndicatorChanged && ("Y").equals(mapping.getSensitiveBenefitIndicator())){
							resultObjectToAddSensitiveWarning.getWarningMsgsList().add(warningMessageRuleSensitivePresent);
						}else if(isSensitiveBenefitIndicatorChanged && ("N").equals(mapping.getSensitiveBenefitIndicator())){
							resultObjectToAddSensitiveWarning.getWarningMsgsList().add(warningMessageRuleSensitiveNotPresent);
						}
						mappingResultList.add(resultObjectToAddSensitiveWarning);
					}
				}
				
			}catch(EBXException e){
				mappingResultList.add(BxUtil.getExceptionResult(e, mapping));
			}catch(Exception e){
				e.printStackTrace();
				mappingResultList.add(BxUtil.getExceptionResult(e, mapping));
			}	
			massUpdateTracker.setCompletedCount(massUpdateTracker.getCompletedCount()+1);
		}
		return mappingResultList;
	}
	private void manageDeltaNotApplicableDetails(List mappingList, Mapping updatedMapping) {
		
		Mapping existingMapping = null;
		
		if (null != mappingList && !mappingList.isEmpty()) {
			existingMapping = (Mapping)mappingList.get(0);
		}
		bxoutboundDataFeedHelperService.manageDeltaNotApplicableDetails(existingMapping, updatedMapping, DomainConstants.HEADERRULE_IDENTIFIER);
	}
	public BXOutboundDataFeedHelperService getBxoutboundDataFeedHelperService() {
		return bxoutboundDataFeedHelperService;
	}
	public void setBxoutboundDataFeedHelperService(
			BXOutboundDataFeedHelperService bxoutboundDataFeedHelperService) {
		this.bxoutboundDataFeedHelperService = bxoutboundDataFeedHelperService;
	}
	
}
