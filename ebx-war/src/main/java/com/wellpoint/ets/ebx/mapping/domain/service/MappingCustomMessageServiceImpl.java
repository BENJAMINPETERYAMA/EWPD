/*
 * <MappingCustomMessageServiceImpl.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.mapping.domain.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSMappingRetrieveResult;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.exception.MappingLockedByAnotherUserException;
import com.wellpoint.ets.bx.mapping.domain.service.AuditTrailService;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.service.LockService;
import com.wellpoint.ets.bx.mapping.domain.service.NoteTypeIndValidator;
import com.wellpoint.ets.bx.mapping.domain.service.ValidatorConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.MappingResult;
import com.wellpoint.ets.bx.mapping.domain.vo.MassUpdateCriteria;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.util.MappingUtil;
import com.wellpoint.ets.bx.mapping.util.MassUpdateTracker;
import com.wellpoint.ets.bx.mapping.web.BxResourceBundle;
import com.wellpoint.ets.ebx.mapping.repository.MappingRepository;
/**
 * @author UST-GLOBAL This is an implementation class for performing all the life
 *         cycle operations on Custom message mapping
 */
public class MappingCustomMessageServiceImpl implements MappingService {
	private static Logger logger = Logger.getLogger(MappingCustomMessageServiceImpl.class);
	private MappingRepository mappingCustomMessageRepository;
	private LockService lockCustomMessageService;
	private LocateService locateCustomMessageService;
	private AuditTrailService auditTrailService;
	private LocateService locateRuleIdService;
	
	
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
		
		mapping.setVariableMappingStatus(DomainConstants.STATUS_BUILDING);
		mapping.setInTempTable(DomainConstants.IN_TEMP_TAB_FLAG_PERSIST);
		mappingResult.setMapping(mapping);
		List errorMessagesList = new ArrayList();
		//change in calling the validator framework for section3
		mapping.setLocateRuleIdService(locateRuleIdService);
		List hippaSegmentValidationResultList = mapping.validateMappings();
		//Added as part of SSCR 16332 
		validateNoteTypeForCustomMessage(hippaSegmentValidationResultList, mapping);
		mappingResult = new MappingResult(hippaSegmentValidationResultList);
		//mappingResult = validator.validateCustomMessage(mapping);

		try{
				if(mappingResult.isStatus()){
					// check for lock
				lockCustomMessageService.aquireLock(mapping);
					createStatus = mappingCustomMessageRepository.create(mapping, userComments);
					if(createStatus){			
						mappingResult.setStatus(true);
					}	 
				}
				else{
					mappingResult.setStatus(false);
				}
			 }
			catch (MappingLockedByAnotherUserException e){				
	        	errorMessagesList.add(e.getMessage());
	        	mappingResult.setErrorMsgsList(errorMessagesList);
				mappingResult.setStatus(false);
				mappingResult.setMapping(mapping);
				return mappingResult;
			}	
		return mappingResult;
	}
	/**
	 * discarding changes to an already Published mapping
	 * @param userComments
	 * @param mapping
	 * @return MappingResult
	 */
	public MappingResult discardChanges(Mapping mapping, String userComments) {
		MappingResult mappingResult = new MappingResult();
		boolean discardChangesStatus = false;
		mappingResult.setMapping(mapping);
		discardChangesStatus = mappingCustomMessageRepository.discardChanges(mapping, userComments);
		if(discardChangesStatus){			
			mappingResult.setStatus(true);
		}
		lockCustomMessageService.deleteLock(mapping);
		return mappingResult;
	}
	/**
	 * updating an existing mapping
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public MappingResult update(Mapping mapping, String userComments) {
		MappingResult mappingResult = new MappingResult();
		boolean updateStatus = false;
		String operation = null;		
		mappingResult.setMapping(mapping);
		List statusList = new ArrayList();
		List errorMessagesList = new ArrayList();
		statusList.add(DomainConstants.VIEW_STATUS);
		//change in calling the validator framework for section3
		mapping.setLocateRuleIdService(locateRuleIdService);
		List hippaSegmentValidationResultList = mapping.validateMappings();
		validateNoteTypeForCustomMessage(hippaSegmentValidationResultList, mapping);
		mappingResult = new MappingResult(hippaSegmentValidationResultList);
//		 if validation is successful , update the mapping
	//	mappingResult = validator.validateCustomMessage(mapping);
		if(mappingResult.isStatus()){
			//validate the user lock
			try{
		lockCustomMessageService.validateUserLock(mapping);
			}
			catch (MappingLockedByAnotherUserException e){
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
			List mappingList= null;
			
			mappingList = (ArrayList)locateCustomMessageService.getRecords(mapping,
						null,null, -1, 21, null);

				
		if(!mappingList.isEmpty()){
			
				Mapping currentMapping = (Mapping)mappingList.get(0);
				String currentStatus = currentMapping.getVariableMappingStatus();
				mappingResult.setPreviousVariableMappingStatus(currentStatus);
				String inTemp = currentMapping.getInTempTable();
				mapping.setInTempTable(inTemp);
				/*
				 * if the IN_TEMP_TAB is "N" and the current status is (BUILDING or SCHEDULED_TO_TEST or SCHEDULED_TO_PRODUCTION or OBJECT_TRANSFERRED)
				 * operation is UPDATE_MAIN
				 */
				if (DomainConstants.IN_TEMP_TAB_FLAG_PERSIST.equals(inTemp)
						&& (currentStatus.equals(DomainConstants.STATUS_BUILDING) 
								|| currentStatus.equals(DomainConstants.STATUS_SCHEDULED_TO_TEST)
								|| currentStatus.equals(DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION)
								|| currentStatus.equals(DomainConstants.STATUS_OBJECT_TRANSFERRED))) {

					mapping.setVariableMappingStatus(DomainConstants.STATUS_BUILDING);
					operation = DomainConstants.UPDATE_MAIN_OPERATION;				
				}
				/*
				 * if the IN_TEMP_TAB is "Y" and the current status is (BEING_MODIFIED or SCHEDULED_TO_TEST or SCHEDULED_TO_PRODUCTION or OBJECT_TRANSFERRED)
				 * operation is UPDATE_TEMP
				 */
				else if (DomainConstants.IN_TEMP_TAB_FLAG_UPDATE.equals(inTemp)
						&& (currentStatus.equals(DomainConstants.STATUS_BEING_MODIFIED) 
								|| currentStatus.equals(DomainConstants.STATUS_SCHEDULED_TO_TEST)
								|| currentStatus.equals(DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION)
								|| currentStatus.equals(DomainConstants.STATUS_OBJECT_TRANSFERRED))) {

					mapping.setVariableMappingStatus(DomainConstants.STATUS_BEING_MODIFIED);
					operation = DomainConstants.UPDATE_TEMP_OPERATION;
				}
				/* * if the current status is PUBLISHED operation is INSERT_TEMP
				 */
				else if (currentStatus.equals(DomainConstants.STATUS_PUBLISHED)) {
					operation = DomainConstants.INSERT_TEMP_OPERATION;
				}
				updateStatus = mappingCustomMessageRepository.update(mapping, userComments,operation);
		}
		}
		if(updateStatus){			
			mappingResult.setStatus(true);
		}	
		else{
			mappingResult.setStatus(false);
		} 
		 mappingResult.setMapping(mapping);
		return mappingResult;
	}
	/**
	 * The methods (sendToTest, approve,notApplicable) in façade will invoke this method for updating the status  of the mapping
	 * @param mapping
	 * @param userComments
	 * @param status
	 * @return MappingResult
	 */
	public MappingResult updateStatus(Mapping mapping, String userComments, String status) {
		MappingResult mappingResult = new MappingResult();
		boolean updateStatus = false;
		String operation = null;
		String currentStatus  = null;
		String inTemp = null;
		mappingResult.setMapping(mapping);
		mapping.setVariableMappingStatus(status);
	
		List errorMessagesList = new ArrayList();
		
		try{
			lockCustomMessageService.validateUserLock(mapping);
		}
		catch (MappingLockedByAnotherUserException e){
			errorMessagesList.add(e.getMessage());
			mappingResult.setErrorMsgsList(errorMessagesList);
			mappingResult.setStatus(false);
			mappingResult.setMapping(mapping);
			return mappingResult;
		}	
		lockCustomMessageService.deleteLock(mapping);
//		calling the getRecords() of the locateService and  check the the IN_TEMP_TAB and the status to decide the operation		
		/*
		 * This part added for massupdate, when this method is get called from massupdate
		 * the mapping passed is taken from db. This is added to avoid refech.
		 */
			List mappingList= null;
			
			mappingList = (ArrayList)locateCustomMessageService.getRecords(mapping,
						null,null, -1, 21, null);	
			
			if((null != mappingList)&&(!mappingList.isEmpty()))	{
				
				try{
					Mapping currentMapping = (Mapping) mappingList.get(0);
					currentStatus = currentMapping.getVariableMappingStatus();
					inTemp = currentMapping.getInTempTable();
					mapping.setInTempTable(inTemp);

					/*
					 * if the IN_TEMP_TAB is "N" and the status is (BUILDING or SCHEDULED_TO_TEST or SCHEDULED_TO_PRODUCTION) operation is 
					 * UPDATE_STATUS_MAIN
					 */
					if (DomainConstants.IN_TEMP_TAB_FLAG_PERSIST.equals(inTemp) 
							&& (DomainConstants.STATUS_BUILDING.equals(currentStatus) 
									|| DomainConstants.STATUS_SCHEDULED_TO_TEST.equals(currentStatus)
									|| DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION.equals(currentStatus)
									|| DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(currentStatus))) {
						operation = DomainConstants.UPDATE_STATUS_MAIN_OPERATION;				
					}
					
					/*
					 * if the IN_TEMP_TAB is "Y" and the current status is (BEING_MODIFIED or SCHEDULED_TO_TEST or SCHEDULED_TO_PRODUCTION or OBJECT_TRANSFERRED)
					 * operation is UPDATE_TEMP */
					
					if (DomainConstants.IN_TEMP_TAB_FLAG_UPDATE.equals(inTemp)
							&& (DomainConstants.STATUS_BEING_MODIFIED.equals(currentStatus) 
									|| DomainConstants.STATUS_SCHEDULED_TO_TEST.equals(currentStatus)
									|| DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION.equals(currentStatus)
									|| DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(currentStatus))) {
						operation = DomainConstants.UPDATE_STATUS_TEMP_OPERATION;	
					}

					/**	if (DomainConstants.IN_TEMP_TAB_FLAG_UPDATE.equals(inTemp) 
							&& DomainConstants.STATUS_NOT_APPLICABLE.equals(mapping.getVariableMappingStatus())) {
						operation = DomainConstants.DELETE_TEMP_OPERATION;
					} 
					/*
					 * if the future status is NOT_APPLICABLE and the current mapping's in temp flag is N then operation is UPDATE_STATUS_MAIN 
					 * with NOT_APPLICABLE
					
					if((DomainConstants.STATUS_NOT_APPLICABLE).equals(mapping.getVariableMappingStatus())
							&& ((DomainConstants.IN_TEMP_TAB_FLAG_PERSIST).equals(inTemp))){
						operation = DomainConstants.UPDATE_STATUS_MAIN_OPERATION;
					
					} */
					// Condition added to prevent invalid scheduling as part of April Release
					
					if (DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION.equals(mapping.getVariableMappingStatus())
							&& DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(currentMapping.getVariableMappingStatus())) {
						mappingResult.setPreviousVariableMappingStatus(DomainConstants.STATUS_OBJECT_TRANSFERRED);
						mapping.setVariableMappingStatus(DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION);
					}
					else{
						BxUtil.doStatusChangeForApprove(mapping);
					}
					updateStatus = mappingCustomMessageRepository.updateStatus(mapping, userComments, operation);
					if(updateStatus){			
						mappingResult.setStatus(true);
					}	
					else{
						mappingResult.setStatus(false);
					}
				}catch (MappingLockedByAnotherUserException e){
					 mappingResult.setStatus(false);
					 List list = new ArrayList(1);
					 list.add("MAPPING_LOCKED_ANOTHER_USER");
					 mappingResult.setErrorMsgsList(list);	
					 return mappingResult;

				}	
				
			}
				
			return mappingResult;
	}

	
	
	/**
	 *
	 * The method called by the Datafeed to change the status of the  the MAPPINGS IN 'SCHEDULED_TO_TEST'
	 *
	 */
	public void transferredToTestRegion(Mapping mapping) {
		MappingResult mappingResult = new MappingResult();
		Mapping existingMapping = (Mapping) locateCustomMessageService
		.getRecords(mapping,null,
				null, -1, 21, null)
				.get(0);
	
		String operation = null;
		if (null != existingMapping) {
			
			BxUtil.doStatusChangeAfterTest(existingMapping);

			if (existingMapping.getInTempTable().equals("N")) {

				operation = DomainConstants.UPDATE_MAIN_OPERATION;

			} else {
				operation = DomainConstants.UPDATE_TEMP_OPERATION;

			}
			User user=new User();
			String createdUser=existingMapping.getUser().getCreatedUserName().toString();
			String username=existingMapping.getUser().getLastUpdatedUserName().toString();
			user.setLastUpdatedUserName(username);
			user.setCreatedUserName(createdUser);
			existingMapping.setUser(user);
			existingMapping.setDatafeedStatus(DomainConstants.DATAFEED_STATUS);
			existingMapping.getUser().setLastUpdatedUserName(DomainConstants.DATAFEED_UPDTD_USER);

			if (mappingCustomMessageRepository.updateStatus(existingMapping,
					DomainConstants.DATAFEED_UPDATED, operation)) {
				mappingResult.setStatus(true);

			}
		} else {
			mappingResult.setStatus(false);
		}
	}

	/**
	 * The method called by the Datafeed to publish the MAPPINGS IN 'SCHEDULED_TO_PRODUCTION'
	 */ 
	
	public void publish(Mapping mapping) {
		List mappingList = (ArrayList) locateCustomMessageService.getRecords(mapping,
				null, null, -1, 21, null);
		if (!mappingList.isEmpty()) {
			Mapping mappingToPersist = (Mapping) mappingList.get(0);
			mappingToPersist.setVariableMappingStatus(DomainConstants.STATUS_PUBLISHED);
			String username=mappingToPersist.getUser().getLastUpdatedUserName().toString();
			String createdUser=mappingToPersist.getUser().getCreatedUserName().toString();
			mappingToPersist.getUser().setLastUpdatedUserName(username);
			mappingToPersist.getUser().setCreatedUserName(createdUser);
			mappingToPersist.setDatafeedStatus(DomainConstants.DATAFEED_STATUS);
			mappingCustomMessageRepository.publish(mappingToPersist,DomainConstants.DF_USER_COMMENTS_PUBLISH);
		}

	}
	
	/**
	 * An existing mapping of custom message is deleted and a datafeed entry is made
	 * @param Mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public MappingResult delete(Mapping mapping,String userComments) {
		boolean deletedStatus=false;
		//change in calling the validator framework for section3
		mapping.setLocateRuleIdService(locateRuleIdService);
		List hippaSegmentValidationResultList = mapping.validateMappings();
		//Added as part of SSCR 16332 
		validateNoteTypeForCustomMessage(hippaSegmentValidationResultList, mapping);
		MappingResult mappingResult = new MappingResult(hippaSegmentValidationResultList);
		//MappingResult mappingResult  = validator.validateCustomMessage(mapping);
		try {
			if(mappingResult.isStatus()){
				deletedStatus = mappingCustomMessageRepository.delete(mapping, userComments);
				if(deletedStatus){
					mappingResult.setStatus(true);
				}
				else{
					mappingResult.setStatus(false);
				}	
			}
			else{
				mappingResult.setStatus(false);
			}


		} catch (MappingLockedByAnotherUserException e){
			mappingResult.setStatus(false);
			List list = new ArrayList(1);
			list.add("MAPPING_LOCKED_ANOTHER_USER");
			mappingResult.setErrorMsgsList(list);	
			return mappingResult;
		}
		return mappingResult;

	}/**
		 *This  method updates the feed_run_flag as “T” after the datafeed to IMSN region is executed 
		 *	and to “P” after the datafeed to IMSP region is executed.
		 * @param Mapping
		 * @param status
		 * @return MappingResult
		 */
	public MappingResult updateDatafeedStatus(Mapping mapping,String status) {

		MappingResult result = new MappingResult();
		if(status.equals(DomainConstants.STATUS_SCHEDULED_TO_TEST)){
			mapping.setVariableMappingStatus(DomainConstants.FEED_RUN_FLAG_TEST);
		}
		else if(status.equals(DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION)){
			mapping.setVariableMappingStatus(DomainConstants.FEED_RUN_FLAG_PROD);
		}
		boolean checkDataFeedStatus = mappingCustomMessageRepository.updateDatafeedStatus(mapping);
		if(checkDataFeedStatus){
			result.setStatus(true);
		}
		else{
			result.setStatus(false);
		}
		return result;
	}
	/**
	 * 
	 * @return lockCustomMessageService
	 */
	public LockService getLockCustomMessageService() {
		return lockCustomMessageService;
	}
	/**
	 * 
	 * @param lockCustomMessageService
	 */
	public void setLockCustomMessageService(LockService lockCustomMessageService) {
		this.lockCustomMessageService = lockCustomMessageService;
	}
	/**
	 * @return MappingRepository
	 */
	public MappingRepository getMappingCustomMessageRepository() {
		return mappingCustomMessageRepository;
	}
	/**
	 * @param mappingRuleIdRepository
	 */
	public void setMappingCustomMessageRepository(
			MappingRepository mappingCustomMessageRepository) {
		this.mappingCustomMessageRepository = mappingCustomMessageRepository;
	}
	/**
	 * 
	 * @return LocateCustomMessageService
	 */
	public LocateService getLocateCustomMessageService() {
		return locateCustomMessageService;
	}
	/**
	 * 
	 * @param locateCustomMessageService
	 */
	public void setLocateCustomMessageService(
			LocateService locateCustomMessageService) {
		this.locateCustomMessageService = locateCustomMessageService;
	}
	/**
	 * 
	 * @return auditTrailService
	 */
	public AuditTrailService getAuditTrailService() {
		return auditTrailService;
	}
	/**
	 * 
	 * @param auditTrailService
	 */
	public void setAuditTrailService(AuditTrailService auditTrailService) {
		this.auditTrailService = auditTrailService;
	}
	public LocateService getLocateRuleIdService() {
		return locateRuleIdService;
	}
	public void setLocateRuleIdService(LocateService locateRuleIdService) {
		this.locateRuleIdService = locateRuleIdService;
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
				mapping = MappingUtil.findMapping(locateCustomMessageService, muc);
				/** SSCR 19537 April 04 - Mass update should be prevented if the user updating 
				 * EB03 associated values for a custom message with individual EB03 mapping. */
				if (DomainConstants.Y.equals(mapping.getIndvdlEb03AssnIndicator()) && MappingUtil.isEb03AssnValuesPresent(muc)) {
					MappingResult mappingResult = new MappingResult();
					List <String>errorMessagesList = new ArrayList <String>();
					List <String> parmList = new ArrayList<String>();
					parmList.add(mapping.getRule().getHeaderRuleId());
					parmList.add(mapping.getSpsId().getSpsId());
					String message = BxResourceBundle.getResourceBundle(ValidatorConstants.MASSUPDATE_ERROR_INVDL_EB03_ASSN_CMSG, parmList);
					errorMessagesList.add(message);
					mappingResult.setErrorMsgsList(errorMessagesList);
		        	mappingResult.setStatus(false);
		        	mappingResult.setMapping(mapping);
		        	mappingResultList.add(mappingResult);
				} else {
					mapping = MappingUtil.mergeMapping(mapping, muc);
					mapping.setEb01(null);
					mapping.setUtilizationMgmntRule(null);
					//mapping.setEb03(null);
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
					if(null != mapping.getNoteTypeCode()){
						// chnaged for reference data values
						mapping.getNoteTypeCode().setName(DomainConstants.NOTE_TYPE_CODE);
					}
					if(mapping.getVariableMappingStatus().trim().equals(DomainConstants.UNMAPPED_STATUS)){
						mappingResultList.add(create(mapping,userComments));
					}else{
						mappingResultList.add(update(mapping,userComments));
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
				mapping = MappingUtil.findMapping(locateCustomMessageService, muc);
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
					MappingResult mappingResult = create(mapping,userComments);
					if(null != mappingResult.getErrorMsgsList() && mappingResult.getErrorMsgsList().size() > 0){
						mappingResultList.add(mappingResult);
					}else{
						mapping.setVariableMappingStatus(status);
						mappingResultList.add(updateStatus(mapping, userComments, status));
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
	// New method added ofr Note Type Validation of Custom Message
	private void validateNoteTypeForCustomMessage(List hippaSegmentValidationResultList, Mapping mapping){
		String spsId = "";
		Mapping copyOfMapping = null;
		copyOfMapping = mapping;
		SPSMappingRetrieveResult spsMapping =  new SPSMappingRetrieveResult();
		if(null != mapping){
			if(null != mapping.getSpsId() && null != mapping.getSpsId().getSpsId() && null != mapping.getRule())
				spsId = mapping.getSpsId().getSpsId();
				Mapping mappingForSPS = new Mapping();
				spsMapping = mappingCustomMessageRepository.getSPSMappingMain(spsId);
				if(null != spsMapping){
					//System.out.println("SPS"+spsMapping);
					logger.info("SPS"+spsMapping);
					String eb01Value = (null != spsMapping.getEb01Value() ? spsMapping.getEb01Value() : "");
					HippaSegment eb01HippaSegement = new HippaSegment();
					HippaCodeValue eb01HippaCodeValue =  new HippaCodeValue();
					eb01HippaCodeValue.setValue(eb01Value);
					List hippaCodeSelectedValues =  new ArrayList();
					hippaCodeSelectedValues.add(eb01HippaCodeValue);
					eb01HippaSegement.setHippaCodeSelectedValues(hippaCodeSelectedValues);
					copyOfMapping.setEb01(eb01HippaSegement);
					NoteTypeIndValidator noteValidator = new NoteTypeIndValidator();
					noteValidator.validateNoteTypeBasedOnEB01(copyOfMapping,hippaSegmentValidationResultList);
				}
		}
	}
	
	
	
}
