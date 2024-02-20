/*
 * <MappingSpsIdServiceImpl.java>
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


import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.exception.MappingLockedByAnotherUserException;
import com.wellpoint.ets.bx.mapping.domain.service.BXOutboundDataFeedHelperService;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.service.LockService;
import com.wellpoint.ets.bx.mapping.domain.service.ValidatorConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.MappingResult;
import com.wellpoint.ets.bx.mapping.domain.vo.MassUpdateCriteria;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.util.MappingUtil;
import com.wellpoint.ets.bx.mapping.util.MassUpdateTracker;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.web.BxResourceBundle;
import com.wellpoint.ets.ebx.mapping.repository.MappingRepository;



/**
 * @author UST-GLOBAL This is an implementation class for performing all the life
 *         cycle operations on Sps Id mapping
 */
public class MappingSpsIdServiceImpl implements MappingService {
	private MappingRepository mappingSpsIdRepository;
	private LocateSpsIdServiceImpl locateSpsIdService;
	private LockService lockSpsIdService;	
	private LocateService locateRuleIdService;
	private BXOutboundDataFeedHelperService bxoutboundDataFeedHelperService;
	/**
	 * Creating a new Mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public MappingResult create(Mapping mapping, String userComments) {

		MappingResult mappingResult = new MappingResult();
		boolean status = false;
		mapping.setVariableMappingStatus(DomainConstants.STATUS_BUILDING);
		mapping.setInTempTable(DomainConstants.IN_TEMP_TAB_FLAG_PERSIST);
		mappingResult.setMapping(mapping);
		String errorMessage = null;
		List errorMessagesList = new ArrayList();

		List mappings = locateSpsIdService.getRecords(mapping, null, null, -1, 21, null);
		
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
		mapping.setLocateRuleIdService(locateRuleIdService);
		List hippaSegmentValidationResultList = mapping.validateMappings();
		mappingResult = new MappingResult(hippaSegmentValidationResultList);
		//mappingResult = validator.validateSPSId(mapping);
		if (mappingResult.isStatus()) {
			
//			 check for lock
			try{
				lockSpsIdService.aquireLock(mapping);
			 }catch (MappingLockedByAnotherUserException e){
				 	//errorMessage = BxResourceBundle.getResourceBundle(ValidatorConstants.MAPPING_LOCKED_ANOTHER_USER,null);
		        	errorMessagesList.add(e.getMessage());
		        	mappingResult.setErrorMsgsList(errorMessagesList);
		        	mappingResult.setStatus(false);
		        	mappingResult.setMapping(mapping);
				    return mappingResult;
			 }
			status = mappingSpsIdRepository.create(mapping, userComments);
			
		}
		mappingResult.setStatus(status);

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
		mapping.setVariableMappingStatus(DomainConstants.STATUS_PUBLISHED);
		if(mappingSpsIdRepository.discardChanges(mapping, userComments)){
			 mappingResult.setStatus(true);
		}
		lockSpsIdService.deleteLock(mapping);
		return  mappingResult;
	
	}
	
	/**
	 * updating an existing mapping
	 * 
	 * @param mapping
	 * @param userComments
	 * @return MappingResult
	 */
	public MappingResult update(Mapping mapping, String userComments) {

		boolean updateStatus = false;
		//change in calling the validator framework for section3
		mapping.setLocateRuleIdService(locateRuleIdService);
		List hippaSegmentValidationResultList = mapping.validateMappings();
		MappingResult mappingResult = new MappingResult(hippaSegmentValidationResultList);
		
		//MappingResult mappingResult = validator.validateSPSId(mapping);
//		 if validation is successful , update the mapping
		if (mappingResult.isStatus()) {
			
//			 validate the user lock
			try{
				lockSpsIdService.validateUserLock(mapping);
			 }catch (MappingLockedByAnotherUserException e){
				 mappingResult.setStatus(false);
				 mappingResult.setMapping(mapping);
				 List list = new ArrayList(1);
				 list.add(e.getMessage());
				 mappingResult.setErrorMsgsList(list);			 
				 return mappingResult;
			 }

			//calling the getRecords() of the locateService and  check the the IN_TEMP_TAB and the status to decide the operation

			/*
			 * if the IN_TEMP_TAB is "N" and the current status is (BUILDING or SCHEDULED_TO_TEST or SCHEDULED_TO_PRODUCTION or OBJECT_TRANSFERRED)
			 * operation is UPDATE_MAIN
			 * if the IN_TEMP_TAB is "Y" and the current status is (BEING_MODIFIED or SCHEDULED_TO_TEST or SCHEDULED_TO_PRODUCTION or OBJECT_TRANSFERRED)
			 * operation is UPDATE_TEMP
			 * if the current status is PUBLISHED operation is INSERT_TEMP
			 */
			 
			 /*
			 * This part added for massupdate, when this method is get called from massupdate
			 * the mapping passed is taken from db. This is added to avoid refech.
			 */
			 
			List mappingList= null;			
			mappingList = (ArrayList)locateSpsIdService.getRecords(mapping, null, null, -1, 21, null);
			
			
			
			if((null != mappingList) && (null != mappingList.get(0))){
				Mapping currentMapping = (Mapping)mappingList.get(0);
				String currentStatus = currentMapping.getVariableMappingStatus();
				mappingResult.setPreviousVariableMappingStatus(currentStatus);
				String inTemp = currentMapping.getInTempTable();
				mapping.setInTempTable(inTemp);
				String operation = null;
			

				if((DomainConstants.STATUS_BUILDING.equals(currentStatus)|| DomainConstants.STATUS_SCHEDULED_TO_TEST.equals(currentStatus) || DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION .equals(currentStatus) ||
						DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(currentStatus))
						&& (!isInTemp(inTemp))){
					operation = DomainConstants.UPDATE_MAIN_OPERATION;
					mapping.setVariableMappingStatus(DomainConstants.STATUS_BUILDING);
				}
				else if((DomainConstants.STATUS_BEING_MODIFIED.equals(currentStatus)|| DomainConstants.STATUS_SCHEDULED_TO_TEST.equals(currentStatus) || DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION .equals(currentStatus) ||
						DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(currentStatus))
						&& (isInTemp(inTemp))){
					operation = DomainConstants.UPDATE_TEMP_OPERATION;
					mapping.setVariableMappingStatus(DomainConstants.STATUS_BEING_MODIFIED);
				}
				else if((DomainConstants.STATUS_PUBLISHED.equals(currentStatus))){
					operation = DomainConstants.INSERT_TEMP_OPERATION;
				}
				//When the current status is NOT_APPLICABLE on update the status will be BUILDING
				else if((DomainConstants.STATUS_NOT_APPLICABLE.equals(currentStatus))){
					mapping.setVariableMappingStatus(DomainConstants.STATUS_BUILDING);
					operation = DomainConstants.UPDATE_MAIN_OPERATION;
				}

				checkAndSetFinalizedFlagModified(currentMapping,mapping);
				updateStatus = mappingSpsIdRepository.update(mapping, userComments,
						operation);
			
			}
			manageDeltaNotApplicableDetails(mappingList, mapping);
		}
		if(updateStatus){
			
			mappingResult.setStatus(true);
		}
		else{
			
			mappingResult.setStatus(false);
		}
		mappingResult.setMapping(mapping);
		return  mappingResult;


	}
	
	private void manageDeltaNotApplicableDetails(List mappingList, Mapping updatedMapping) {
		
		Mapping existingMapping = null;
		
		if (null != mappingList && !mappingList.isEmpty()) {
			existingMapping = (Mapping)mappingList.get(0);
		}
		bxoutboundDataFeedHelperService.manageDeltaNotApplicableDetails(existingMapping, updatedMapping, DomainConstants.SPS_IDENTIFIER);
	
	}
	private Mapping checkAndSetFinalizedFlagModified(Mapping currentMapping,Mapping futureMapping){
		if((currentMapping.isFinalized()&& futureMapping.isFinalized())||(!(currentMapping.isFinalized())&&!(futureMapping.isFinalized()))){
			futureMapping.setFinalizedFlagModified(false);
		}else if((((currentMapping.isFinalized())&& !(futureMapping.isFinalized())))||(!(currentMapping.isFinalized())&& (futureMapping.isFinalized()))){
			futureMapping.setFinalizedFlagModified(true);
		}
		return futureMapping;
	}
	
	/**
	 * Creating the boolean data from the String values "y" or "N"
	 */
	private boolean isInTemp(String inTemp){
		return (inTemp.equals("Y")?true:false) ;
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
		
		MappingResult mappingResult = new MappingResult();
		try{
			lockSpsIdService.validateUserLock(mapping);
		 }catch (MappingLockedByAnotherUserException e){
			 mappingResult.setStatus(false);
			 List list = new ArrayList(1);
			 list.add(e.getMessage());
			 mappingResult.setErrorMsgsList(list);			 
			 return mappingResult;
		 }     
		lockSpsIdService.deleteLock(mapping);
		/*
		 * if the IN_TEMP_TAB is "N" and the current status is (BUILDING or SCHEDULED_TO_TEST or SCHEDULED_TO_PRODUCTION) operation is UPDATE_STATUS_MAIN
		 * if the IN_TEMP_TAB is "Y" and the status is (BEING_MODIFIED or SCHEDULED_TO_TEST or SCHEDULED_TO_PRODUCTION)operation is UPDATE_STATUS_TEMP
		 * if the future status is NOT_APPLICABLE and IN_TEMP_TAB is "Y" operation is DELETE_TEMP and UPDATE_MAIN (updating the status to NOT_APPLICABLE)
		 * if the status is NOT_APPLICABLE and IN_TEMP_TAB is "N" operation is UPDATE_STATUS_TEMP  with status NOT_APPLICABLE
		 * if there no mapping and the future status is NOT_APPLICABLE call CREATE with status as NOT_APPLICABLE
		 */
		// The status from getRecords() of the locateService
		
		/*
		 * This part added for massupdate, when this method is get called from massupdate
		 * the mapping passed is taken from db. This is added to avoid refech.
		 */
		List mappingList= null;			

		mappingList = (ArrayList)locateSpsIdService.getRecords(mapping, null, null, -1, 21, null);
		

		
		String operation = null;
		String currentStatus  = null;
		String inTemp = null;
		if((null != mappingList) && (! mappingList.isEmpty())) {
			Mapping currentMapping = (Mapping) mappingList.get(0);
			
		
			
			currentStatus = currentMapping.getVariableMappingStatus();
			inTemp = currentMapping.getInTempTable();

			mapping.setInTempTable(inTemp);
			
			if ((DomainConstants.STATUS_BUILDING.equals(currentStatus)
					|| DomainConstants.STATUS_OBJECT_TRANSFERRED
							.equals(currentStatus)
					|| DomainConstants.STATUS_SCHEDULED_TO_TEST
							.equals(currentStatus) || DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION
					.equals(currentStatus))
					&& (!isInTemp(inTemp))) {
				operation = DomainConstants.UPDATE_STATUS_MAIN_OPERATION;
			}
			else if ((DomainConstants.STATUS_BEING_MODIFIED
					.equals(currentStatus)
					|| DomainConstants.STATUS_OBJECT_TRANSFERRED
							.equals(currentStatus)
					|| DomainConstants.STATUS_SCHEDULED_TO_TEST
							.equals(currentStatus) || DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION
					.equals(currentStatus))
					&& (isInTemp(inTemp))
					&& (!DomainConstants.STATUS_NOT_APPLICABLE.equals(mapping
							.getVariableMappingStatus()))) {
				operation = DomainConstants.UPDATE_STATUS_TEMP_OPERATION;
			}

			// When the mapping is once published and edited is now made
			// NOT_APPLICABLE
			else if ((DomainConstants.STATUS_NOT_APPLICABLE.equals(mapping
					.getVariableMappingStatus()) && (isInTemp(inTemp)))) {
				operation = DomainConstants.DELETE_TEMP_OPERATION;
				
			} else if (!isInTemp(inTemp)
					&& (DomainConstants.STATUS_NOT_APPLICABLE.equals(mapping
							.getVariableMappingStatus()))){
				
				operation = DomainConstants.UPDATE_STATUS_MAIN_OPERATION;
			}
			
			// Condition added to prevent invalid scheduling as part of April Release
			
			if (DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION.equals(mapping.getVariableMappingStatus())
					&& DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(currentMapping.getVariableMappingStatus())) {
				mappingResult.setPreviousVariableMappingStatus(DomainConstants.STATUS_OBJECT_TRANSFERRED);
				mapping.setVariableMappingStatus(DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION);
				//mapping.setDataFeedInd("N");
			}
			else{
				BxUtil.doStatusChangeForApprove(mapping);
			}
			
			//April Release code ends here 
			
			if(mappingSpsIdRepository.updateStatus(mapping, userComments, operation)){
				 mappingResult.setStatus(true);
			}
			else{
				 mappingResult.setStatus(false);
			}

		}// When the mapping is not edited after published ,but is made not applicable 
		else if (mapping.getVariableMappingStatus().equals(
						DomainConstants.STATUS_NOT_APPLICABLE)) {
			
			mapping.setInTempTable(DomainConstants.IN_TEMP_TAB_FLAG_PERSIST);
			/* Defect ID PROD00613769 */
			Mapping blankMapping = new Mapping();
			blankMapping.setAuditTrails(mapping.getAuditTrails());
			blankMapping.setSpsId(mapping.getSpsId());
			blankMapping.setUser(mapping.getUser());
			blankMapping.setInTempTable(mapping.getInTempTable());
			blankMapping.setVariableMappingStatus(mapping.getVariableMappingStatus());
	
			/* PROD00613769 ends */
			if (mappingSpsIdRepository.create(blankMapping, userComments)) {
				mappingResult.setStatus(true);
			}
		}
		mappingResult.setMapping(mapping);
	
		manageDeltaNotApplicableDetails(mappingList, mapping);	
		
		return  mappingResult;
	}
	
	
	
	/**
	 * The method called by the Datafeed to change the status of the  the MAPPINGS IN 'SCHEDULED_TO_TEST'
	 */
	public void transferredToTestRegion(Mapping mapping) {
		MappingResult mappingResult = new MappingResult();
		List mappingList = (ArrayList)locateSpsIdService.getRecords(mapping, null, null, -1, 21, null);

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
			if (mappingSpsIdRepository.updateStatus(mapping, "datafeedUpdate",
					operation)) {
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
		
	
		List mappingList = (ArrayList) locateSpsIdService.getRecords(mapping,
				null, null, -1, 21, null);
		if (!mappingList.isEmpty()) {
			Mapping mappingToPersist = (Mapping) mappingList.get(0);
			
			mappingToPersist.setVariableMappingStatus(DomainConstants.STATUS_PUBLISHED);
			String username=mappingToPersist.getUser().getLastUpdatedUserName().toString();
			mappingToPersist.getUser().setLastUpdatedUserName(username);
			mappingToPersist.setDatafeedStatus(DomainConstants.DATAFEED_STATUS);
			mappingSpsIdRepository.publish(mappingToPersist,DomainConstants.DF_USER_COMMENTS_PUBLISH);
			
			/*if(status)
			{
				mappingSpsIdRepository.discardChanges(mapping,DomainConstants.DF_USER_COMMENTS_DELETE);
			}*/
	
		}

	}

	
	/**
	 * Getting MappingSpsIdRepository set from the Application context
	 * @return
	 */
	public MappingRepository getMappingSpsIdRepository() {
		return mappingSpsIdRepository;
	}
	/**
	 * Setting the  MappingSpsIdRepository by the Application Context
	 */
	public void setMappingSpsIdRepository(MappingRepository  mappingSpsIdRepository) {
		this.mappingSpsIdRepository = mappingSpsIdRepository;
	}
	
	/**
	 * Getting LocateSpsIdService set from the Application context
	 * @return
	 */
	public LocateSpsIdServiceImpl getLocateSpsIdService() {
		return locateSpsIdService;
	}
	
	/**
	 * Setting the  LocateSpsIdService by the Application Context
	 */
	public void setLocateSpsIdService(LocateSpsIdServiceImpl locateSpsIdService) {
		this.locateSpsIdService = locateSpsIdService;
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
	public LockService getLockSpsIdService() {
		return lockSpsIdService;
	}
	public void setLockSpsIdService(LockService lockSpsIdService) {
		this.lockSpsIdService = lockSpsIdService;
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
	public List update(List massUpdateCriteria, String userComments,MassUpdateTracker massUpdateTracker) {
		massUpdateTracker.setTotalCount(massUpdateCriteria.size());
		massUpdateTracker.setCompletedCount(0);
		Iterator itr = massUpdateCriteria.iterator();
		List mappingResultList = new ArrayList();
		while(itr.hasNext()){
			Mapping mapping = null;
			try{
				MassUpdateCriteria muc = (MassUpdateCriteria)itr.next();
				mapping = MappingUtil.findMapping(locateSpsIdService, muc);
				mapping = MappingUtil.mergeMapping(mapping, muc);
				mapping.setUtilizationMgmntRule(null);
				mapping.setEb03(null);
				if(null != mapping.getAccum()){
					mapping.getAccum().setName(DomainConstants.ACCUM_REF_NAME);
				}
				if(mapping.getVariableMappingStatus().trim().equals(DomainConstants.UNMAPPED_STATUS)){
					mappingResultList.add(create(mapping,userComments));
				}else{
					mappingResultList.add(update(mapping,userComments));
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
				mapping = MappingUtil.findMapping(locateSpsIdService, muc);
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
	public BXOutboundDataFeedHelperService getBxoutboundDataFeedHelperService() {
		return bxoutboundDataFeedHelperService;
	}
	public void setBxoutboundDataFeedHelperService(
			BXOutboundDataFeedHelperService bxoutboundDataFeedHelperService) {
		this.bxoutboundDataFeedHelperService = bxoutboundDataFeedHelperService;
	}

}