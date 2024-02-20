package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.owasp.esapi.ESAPI;

import com.wellpoint.ets.bx.mapping.domain.entity.AuditTrail;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.exception.DomainException;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.exception.MappingLockedByAnotherUserException;
import com.wellpoint.ets.bx.mapping.domain.vo.CreateOrEditMappingResult;
import com.wellpoint.ets.bx.mapping.domain.vo.MappingResult;
import com.wellpoint.ets.bx.mapping.domain.vo.MassUpdateCriteria;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.repository.MappingRepository;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.util.MappingUtil;
import com.wellpoint.ets.bx.mapping.util.MassUpdateTracker;
import com.wellpoint.ets.bx.mapping.web.BxResourceBundle;
import com.wellpoint.ets.bx.mapping.web.WebConstants;
import com.wellpoint.ets.ebx.mapping.domain.service.ValidationService;
import com.wellpoint.ets.ebx.simulation.util.SimulationResourceBundle;

public class VariableMappingServiceImpl implements VariableMappingService {

	private MappingRepository mappingRepository;	
	private StateTransitionService stateTransitionService;
	private HippaSegmentService hippaSegmentService;
	private AuditTrailService auditTrailService;
	private LockService lockService;
	private ValidationService wpdValidationService;
	private AuditLockService auditLockService;
	

	private BXOutboundDataFeedHelperService bxoutboundDataFeedHelperService;
	private static Logger log = Logger.getLogger(VariableMappingServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.domain.service.VariableMappingService#copyVariableMapping(com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
	 */
	public CreateOrEditMappingResult copyVariableMapping(String oldVariableId, Mapping mapping) {
		
		Mapping retrieveMapping = null;
		CreateOrEditMappingResult createOrEditMappingResult = null;
		Variable variable = new Variable();
		User user = new User();
		
		String userComments ="";
		retrieveMapping = retrieveMapping(oldVariableId);
		user.setCreatedUserName(mapping.getUser().getLastUpdatedUserName());
		retrieveMapping.setUser(user);
		variable.setVariableId(mapping.getVariable().getVariableId());
		variable.setDescription(mapping.getVariable().getDescription());
		variable.setVariableFormat(mapping.getVariable().getVariableFormat());
		retrieveMapping.setVariable(variable);
		
		createOrEditMappingResult = this.createOrEditVariableMapping(retrieveMapping,userComments);
		
		return createOrEditMappingResult;
	}
	
	public CreateOrEditMappingResult createOrEditVariableMapping(Mapping mapping, String userComments) throws DomainException {
		CreateOrEditMappingResult createOrEditMappingResult = null;
		Mapping retrieveMapping = null;
		 if(mappingRepository.isMappingAlreadyCreated(mapping)) {
		 createOrEditMappingResult = new CreateOrEditMappingResult(mapping);
		 createOrEditMappingResult.getMapping().setVariableList(retrieveVariableInfo(mapping.getVariable()));
		 createOrEditMappingResult.addStatusMappingExists();
		 createOrEditMappingResult.setStatus(DomainConstants.FAILURE_STATUS);
		 return createOrEditMappingResult;
		 }
		 mapping.setHippaSegmentService(hippaSegmentService);
		 mapping.setWpdValidationService(wpdValidationService);
		 
		 List hippaSegmentValidationResultList = mapping.validateMappings();
		 
		 createOrEditMappingResult = new CreateOrEditMappingResult(hippaSegmentValidationResultList);
		 
		 if(createOrEditMappingResult.isAutoPopulateSensitiveBenefitIndicator()){
			    mapping.setSensitiveBenefitIndicator("Y");
		 }
		 createOrEditMappingResult.getMapping().setVariableList(retrieveVariableInfo(mapping.getVariable()));
		 createOrEditMappingResult.setUserComments(userComments);
		 if(createOrEditMappingResult.isValidationSucess()){
			 try{
				 lockService.aquireLock(mapping);
			 }catch (MappingLockedByAnotherUserException e){
				 createOrEditMappingResult.setStatus(DomainConstants.LOCKED_STATUS);
				 List list = new ArrayList();
				 list.add(e.getMessage());
				 createOrEditMappingResult.setStatusCodes(list);
				 createOrEditMappingResult.setMapping(mapping);
				 return createOrEditMappingResult;
			 }
			
		 mapping.setVariableMappingStatus(DomainConstants.STATUS_BUILDING);
		 mappingRepository.persistNewVariableMapping(mapping);
		 /**
		  * MTM Code change
		  */
		 mapping.setFinalizedFlagModified(true);
		 /**
		  * end
		  */
		 auditTrailService.logMapping(mapping, userComments);
		 retrieveMapping = retrieveMapping(mapping.getVariable().getVariableId());
		 createOrEditMappingResult.setMapping(retrieveMapping);
		 }
		return createOrEditMappingResult;
	}
	public CreateOrEditMappingResult saveMapping(Mapping mapping, String userCmnts) throws DomainException {
		
		boolean mappingResult = true;
		CreateOrEditMappingResult createOrEditMappingResult = new CreateOrEditMappingResult();
		AuditTrail auditTrail = null;
		Mapping retrieveMapping = new Mapping();
		Variable variable = new Variable();
		
		String varaiableId = mapping.getVariable().getVariableId();
		
		variable.setVariableId(varaiableId);

		retrieveMapping.setUser(mapping.getUser());
		int variableStatusForAuditTrial=mapping.getVariableStatusForAuditTrail();
		
		mapping.setHippaSegmentService(hippaSegmentService);
		mapping.setWpdValidationService(wpdValidationService);
		
		try{
			lockService.validateUserLock(mapping);
		 }catch (MappingLockedByAnotherUserException e){
			 createOrEditMappingResult.setStatus(DomainConstants.LOCKED_STATUS);
			 List list = new ArrayList(1);
			 list.add(e.getMessage());
			 createOrEditMappingResult.setStatusCodes(list);
			 List variableList = retrieveVariableInfo(variable);
			 mapping.setVariableList(variableList);
			 createOrEditMappingResult.setMapping(mapping);
			 return createOrEditMappingResult;
		 }
		
		
		List hippaSegmentValidationResultList = mapping.validateMappings();
		createOrEditMappingResult = new CreateOrEditMappingResult(
				hippaSegmentValidationResultList);
		if(createOrEditMappingResult.isAutoPopulateSensitiveBenefitIndicator()){
		    mapping.setSensitiveBenefitIndicator("Y");
		}
		if(createOrEditMappingResult.isValidationSucess()){
			/**
			 * MTM CODE CHANGES
			 */
			retrieveMapping=retrieveMapping(varaiableId);
			if(retrieveMapping.isFinalized()!= mapping.isFinalized()){
				mapping.setFinalizedFlagModified(true);
			}
			
			/**
			 * end
			 */
		createOrEditMappingResult.setPreviousVariableMappingStatus(retrieveMapping.getVariableMappingStatus());	
		mappingResult	= stateTransitionService.transformToEditableState(mapping,retrieveMapping);
		manageDeltaNotApplicableDetails(retrieveMapping, mapping);
		retrieveMapping =  retrieveMapping(varaiableId);
		
		if(!retrieveMapping.getAuditLock().equals(mapping.getAuditLock())){
			if (null !=retrieveMapping.getAuditLock() && !"".equalsIgnoreCase(retrieveMapping.getAuditLock())) {
				if("N".equalsIgnoreCase(retrieveMapping.getAuditLock().trim())) {
					retrieveMapping.setVariableStatusForAuditTrail(0);
				} else if("Y".equalsIgnoreCase(retrieveMapping.getAuditLock().trim())) {
					retrieveMapping.setVariableStatusForAuditTrail(1);
				}
			}
		}else{
			retrieveMapping.setVariableStatusForAuditTrail(variableStatusForAuditTrial);	
		}
		/**
		 * mtm code change
		 */

		if(!mapping.isFinalizedFlagModified())
		{
			retrieveMapping.setFinalizedFlagModified(false);
		}
		else
		{
			retrieveMapping.setFinalizedFlagModified(true);
		}
		/**
		 * end
		 */
		 if(mappingResult){

		 	createOrEditMappingResult.setStatus(DomainConstants.SUCCESS_STATUS);
		 	if(retrieveMapping.getVariableMappingStatus().equals(DomainConstants.STATUS_BUILDING)){
		 		
		 		auditTrailService.logMapping(retrieveMapping, userCmnts, DomainConstants.AUDIT_STATUS_ADDED);
		 	}
		 	if(retrieveMapping.getVariableMappingStatus().equals(DomainConstants.STATUS_BEING_MODIFIED)){
		 		auditTrailService.logMapping(retrieveMapping, userCmnts, DomainConstants.AUDIT_STATUS_BEING_MODIFIED);
		 	}
		 }
		 else{
		 	
		 	createOrEditMappingResult.setStatus(DomainConstants.FAILURE_STATUS);
		 }
				 
		 createOrEditMappingResult.setMapping(retrieveMapping);
		 auditTrail = auditTrailService.retrieveLatestAuditInfo(statusInAuditTrail(retrieveMapping.getVariableMappingStatus()), varaiableId);
		 if(auditTrail == null ){
	 		
	 		createOrEditMappingResult.setUserComments("");
	 	}
	 	else{
	 		
	 		createOrEditMappingResult.setUserComments(auditTrail.getUserComments());
	 	}
	
		 return createOrEditMappingResult;
	 }
	
		else{
		 	List variableList = retrieveVariableInfo(variable);
		 	retrieveMapping =  retrieveMapping(varaiableId);
		 	mapping.setVariableList(variableList);
		 	createOrEditMappingResult.setMapping(mapping);
		 	auditTrail = auditTrailService.retrieveLatestAuditInfo(statusInAuditTrail(retrieveMapping.getVariableMappingStatus()), varaiableId);
		 	if(auditTrail == null ){
		 		
		 		createOrEditMappingResult.setUserComments("");
		 	}
		 	else{
		 		
		 		createOrEditMappingResult.setUserComments(auditTrail.getUserComments());
		 	}
			
		 	createOrEditMappingResult.setUserComments(userCmnts);
		 	return createOrEditMappingResult;
		 }
	
	}
	
	public List viewHistory(String variableId, Integer noOfAuditInfo){
		
		List auditTrailList = auditTrailService.retrieveAuditTrail(variableId,noOfAuditInfo);		
		if(null == auditTrailList || (auditTrailList.size() == 0) || (auditTrailList.isEmpty())){
			
			log.debug("No audit trail found for variable "+ESAPI.encoder().encodeForHTML(variableId));
			return null;
		}
		else{			
			return auditTrailList;
		}
		
	}
	public Mapping retreiveMapping(String variableId, Integer numOfTrailRecords){
		
		Mapping mappingResult = retrieveMapping(variableId);
		if(null != mappingResult && null != mappingResult.getVariable()) {
			List auditTrailList = auditTrailService.retrieveAuditTrail(mappingResult.getVariable().getVariableId(),numOfTrailRecords);			
			mappingResult.setAuditTrails(auditTrailList);
		}
		return mappingResult;
	}
	
	public List retrieveVariableInfo(Variable variable){
		return mappingRepository.retrieveVariableInfo(variable);
	} 
	
	public int isValidVariableIDStatus(String variableId){
		return mappingRepository.isValidVariableID(variableId);
	}
	
	public CreateOrEditMappingResult editMapping(Mapping mapping){
		
		CreateOrEditMappingResult createOrEditMappingResult = new CreateOrEditMappingResult();
		Mapping retrieveMapping = null;
		String varaiableId = mapping.getVariable().getVariableId();
		log.info("before retrieveStatus...");
		String mappingStatus = mappingRepository.retrieveStatus(varaiableId);
		log.info("inside serviceimpl...");

		//insert into temp only when the mapping is send to production.
		if(mappingStatus.equals(DomainConstants.STATUS_PUBLISHED)){			
			// chnage for may release - acquiring lock when a published mapping is edited			
			try{
				 lockService.aquireLock(mapping);
			 }catch (MappingLockedByAnotherUserException e){
				 createOrEditMappingResult.setStatus(DomainConstants.LOCKED_STATUS);
				 List list = new ArrayList();
				 list.add(e.getMessage());
				 createOrEditMappingResult.setStatusCodes(list);
				 createOrEditMappingResult.setMapping(mapping);
				 return createOrEditMappingResult;
			 }		
			 mapping.setVariableMappingStatus(DomainConstants.STATUS_BEING_MODIFIED);
			 stateTransitionService.transformToBeingModified(mapping);
		}
		else {
			try{
				 lockService.validateUserLock(mapping);
			 }catch (MappingLockedByAnotherUserException e){
				 createOrEditMappingResult.setStatus(DomainConstants.LOCKED_STATUS);
				 List list = new ArrayList();
				 list.add(e.getMessage());
				 createOrEditMappingResult.setStatusCodes(list);
				 createOrEditMappingResult.setMapping(mapping);
				 return createOrEditMappingResult;
			 }		
		}
		retrieveMapping = retrieveMapping(varaiableId);
		
				
		AuditTrail auditTrail = auditTrailService.
			retrieveLatestAuditInfo(statusInAuditTrail(retrieveMapping.getVariableMappingStatus()), varaiableId);
		
		if(null == auditTrail){
			
			createOrEditMappingResult.setUserComments("");
		}
		else{
			
			createOrEditMappingResult.setUserComments(auditTrail.getUserComments());
		}
		
		createOrEditMappingResult.setMapping(retrieveMapping);
		return createOrEditMappingResult;
	}	
	private String statusInAuditTrail(String status){
		
		String mappingStatus = "";
		if(status.equals(DomainConstants.STATUS_BUILDING)){
			
			mappingStatus = DomainConstants.AUDIT_STATUS_ADDED;
		}
		else if(status.equals(DomainConstants.STATUS_BEING_MODIFIED)){
			
			mappingStatus =DomainConstants.AUDIT_STATUS_BEING_MODIFIED;
		}
		else if(status.equals(DomainConstants.STATUS_SCHEDULED_TO_TEST)){
			
			mappingStatus =DomainConstants.AUDIT_STATUS_SEND_TO_TEST;
		}
		else if(status.equals(DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION)){
			
			mappingStatus =DomainConstants.AUDIT_STATUS_APPROVE;
		}
		else if(status.equals(DomainConstants.STATUS_PUBLISHED)){
			
			mappingStatus =DomainConstants.AUDIT_STATUS_PUBLISHED;
		}
		else if(status.equals(DomainConstants.STATUS_NOT_APPLICABLE)){
			
			mappingStatus =DomainConstants.AUDIT_STATUS_NOT_APPLICABLE;
		}
		return mappingStatus;
	}
	private Mapping retrieveMapping(String variableId) {

		Mapping retrievedMapping = null;
		if(mappingRepository.isMappingBeingModified(variableId)){		
			retrievedMapping = mappingRepository.retrieveBeingModified(variableId);
		}
		else{			
			retrievedMapping = mappingRepository.retrieveMapping(variableId);	
		}
		
		if(null != retrievedMapping && null != retrievedMapping.getUtilizationMgmntRule()){
			Collections.sort(retrievedMapping.getUtilizationMgmntRule().getHippaCodeSelectedValues());
		}
		
		return retrievedMapping;
	}
	public List retrieveAllAuditTrail(String variableId){
		
		List auditTrailList = auditTrailService.retrieveAllAuditTrail(variableId);
		return auditTrailList;
	}
	
	public CreateOrEditMappingResult sendToTest(Mapping mapping, String userCmnts,CreateOrEditMappingResult savedResult){
		if(savedResult == null){
		    savedResult = new CreateOrEditMappingResult();
		}
		String variableId = mapping.getVariable().getVariableId();
		 		
		try{
			 lockService.validateUserLock(mapping);
		 }catch (MappingLockedByAnotherUserException e){
			 savedResult.setStatus(DomainConstants.LOCKED_STATUS);
			 List list = new ArrayList();
			 list.add(e.getMessage());
			 savedResult.setStatusCodes(list);
			 savedResult.setMapping(mapping);
			 return savedResult;
		 }	
		 	lockService.deleteLock(mapping);
			Mapping retrievedMapping = null;
			
			if(mappingRepository.isMappingBeingModified(variableId)){		
				retrievedMapping = mappingRepository.retrieveBeingModified(variableId);
			}
			else{
				retrievedMapping = mappingRepository.retrieveMapping(variableId);
			}
			retrievedMapping.setUser(mapping.getUser());
			int sendToTestStatus = stateTransitionService.transformToScheduledToTest(retrievedMapping);
			
			if(sendToTestStatus==1){
				auditTrailService.logMapping(retrievedMapping, userCmnts);
			}
			savedResult.setStatus(sendToTestStatus);
			
			AuditTrail auditTrail = auditTrailService.retrieveLatestAuditInfo(
					statusInAuditTrail(retrievedMapping.getVariableMappingStatus()),
					retrievedMapping.getVariable().getVariableId()); 
			retrievedMapping.addAuditTrail(auditTrail);
			savedResult.setMapping(retrievedMapping);
			
			return savedResult;
		
	}
	
	
	public CreateOrEditMappingResult markAsNotApplicable(Mapping mapping, String userCmnts){
		CreateOrEditMappingResult createOrEditMappingResult = new CreateOrEditMappingResult();
		Mapping retrivedVariablemapping = new Mapping();
		String varaiableId = mapping.getVariable().getVariableId();
		
		if(mappingRepository.isMappingBeingModified(varaiableId)){
			retrivedVariablemapping = mappingRepository.retrieveBeingModified(varaiableId);
		}
		else{
			retrivedVariablemapping= mappingRepository.retrieveMapping(varaiableId);
		}
		retrivedVariablemapping.setUser(mapping.getUser());
		mapping.setVariableMappingStatus(DomainConstants.STATUS_NOT_APPLICABLE);
		manageDeltaNotApplicableDetails(retrivedVariablemapping, mapping);	

		//added for getting in audit trail
		String auditStatus = retrivedVariablemapping.getAuditLock();
		if (null !=auditStatus && !"".equalsIgnoreCase(auditStatus)) {
			if("N".equalsIgnoreCase(auditStatus.trim())) {
				retrivedVariablemapping.setVariableStatusForAuditTrail(0);
			} else if("Y".equalsIgnoreCase(auditStatus.trim())) {
				retrivedVariablemapping.setVariableStatusForAuditTrail(1);
			}
		}
		
		int notApplicable = stateTransitionService.notApplicable(retrivedVariablemapping);
		

		
		lockService.deleteLock(mapping);
			
		auditTrailService.logMapping(retrivedVariablemapping, userCmnts);
		createOrEditMappingResult.setStatus(notApplicable);
		return createOrEditMappingResult;
		
	}
	
	
	public CreateOrEditMappingResult approve(Mapping mapping, String userCmnts,CreateOrEditMappingResult savedResult) {
		if(savedResult == null){
		    savedResult = new CreateOrEditMappingResult();
		}

		Mapping retriveMapping = null;
		int approveStatus = 0;
		
		try{
			 lockService.validateUserLock(mapping);
		 }catch (MappingLockedByAnotherUserException e){
			 savedResult.setStatus(DomainConstants.LOCKED_STATUS);
			 List list = new ArrayList();
			 list.add(e.getMessage());
			 savedResult.setStatusCodes(list);
			 savedResult.setMapping(mapping);
			 return savedResult;

		 }	
		
		String varaiableId = mapping.getVariable().getVariableId();	
		
		lockService.deleteLock(mapping);

		if(mappingRepository.isMappingBeingModified(varaiableId)){
			retriveMapping = mappingRepository.retrieveBeingModified(mapping.getVariable().getVariableId());
			retriveMapping.setInTempTable("Y");
		}
		else{		
			retriveMapping = mappingRepository.retrieveMapping(mapping.getVariable().getVariableId());			
		}
		retriveMapping.setUser(mapping.getUser());
		if (DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(retriveMapping.getVariableMappingStatus())) {
			savedResult.setPreviousVariableMappingStatus(DomainConstants.STATUS_OBJECT_TRANSFERRED);
			retriveMapping.setVariableMappingStatus(DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION);
		}
		else {
			retriveMapping.setVariableMappingStatus(DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION);
			BxUtil.doStatusChangeForApprove(retriveMapping);
		}
		approveStatus = stateTransitionService.approve(retriveMapping);
		
		if(approveStatus==1){
			auditTrailService.logMapping(retriveMapping, userCmnts);
			savedResult.setStatus(approveStatus);
		}	
		AuditTrail auditTrail = auditTrailService.retrieveLatestAuditInfo(
				statusInAuditTrail(retriveMapping.getVariableMappingStatus()),
				retriveMapping.getVariable().getVariableId()); 
		retriveMapping.addAuditTrail(auditTrail);
		
		savedResult.setMapping(retriveMapping);
		
		return savedResult;
		
	}

	public CreateOrEditMappingResult discardChanges(Mapping mapping ,String userCmnts){		
		CreateOrEditMappingResult createOrEditMappingResult = new CreateOrEditMappingResult();
		// delete lock when currently edited mapping is discarded - May release change
		lockService.deleteLock(mapping);
		
		Mapping previousPublishedMapping = new Mapping();
		int copyDeleteStatus = stateTransitionService.transformToPreviousPublishedMapping(mapping);
		if(copyDeleteStatus == 1){
			String varaiableId = mapping.getVariable().getVariableId();
			previousPublishedMapping = mappingRepository.retrieveMapping(varaiableId);
		}
		createOrEditMappingResult.setStatus(copyDeleteStatus);
		createOrEditMappingResult.setMapping(previousPublishedMapping);
		String auditStatus = DomainConstants.AUDIT_STATUS_DISCARD_CHANGES;
		auditTrailService.logMapping(previousPublishedMapping, userCmnts, auditStatus);
		return createOrEditMappingResult;
	}
	/***
	 * 
	 */
	public CreateOrEditMappingResult publish(String varaiableId) {
		
		return publish(varaiableId, null);
	}
	
	/***
	 * 
	 */
	public CreateOrEditMappingResult publish(String varaiableId, String publishingUser) {
		
		CreateOrEditMappingResult createOrEditMappingResult = new CreateOrEditMappingResult();
		Mapping mapping = null;
		mapping = mappingRepository.retrieveMapping(varaiableId);
		String userCmnts = "";		
		mapping = stateTransitionService.publishMapping(mapping,varaiableId);
		if(publishingUser != null) {
			mapping.getUser().setLastUpdatedUserName(publishingUser);
		}
		auditTrailService.logMappingInDetail(mapping, userCmnts);
		return createOrEditMappingResult;
	}
	
	public CreateOrEditMappingResult publishMapping(int inClauseSize, int batchSize,
			List<String> variableList,String publishingUser) {
		
		CreateOrEditMappingResult createOrEditMappingResult = new CreateOrEditMappingResult();
		List<Mapping> mappingList = mappingRepository.retrieveMappingForProdDataFeed(inClauseSize,variableList);
		List<Mapping> mainMappingList = new ArrayList<Mapping>();
		String userCmnts = "";
		List<String> tempVariableList = new ArrayList<String>(); 
		if(null != mappingList && mappingList.size() != 0) {
			for(Mapping mapping : mappingList) {
				if(("Y").equalsIgnoreCase(mapping.getInTempTable())) {
					tempVariableList.add(mapping.getVariable().getVariableId());
				}else {
					mainMappingList.add(mapping);
				}
			}
		}
		mappingList = stateTransitionService.publishMapping(inClauseSize,batchSize,tempVariableList,mainMappingList);
		if(publishingUser != null) {
			if(null != mappingList) {
				for(Mapping mapping : mappingList) {
					mapping.getUser().setLastUpdatedUserName(publishingUser);
				}
			}
		}
		auditTrailService.logMappingInDetail(inClauseSize, batchSize, mappingList, userCmnts);
		return createOrEditMappingResult;
	}
	
	/* (non-Javadoc)
     * @see com.wellpoint.ets.bx.mapping.domain.service.VariableMappingService#retreiveAuditTrailInDetail(java.lang.String)
     */
    public List retreiveAuditTrailInDetail(String variableId) {
        List auditTrailListInDetail = auditTrailService.retreiveAuditTrailInDetail(variableId);
        return auditTrailListInDetail;
    }
    
    /* (non-Javadoc)
     * @see com.wellpoint.ets.bx.mapping.domain.service.VariableMappingService#markVariableAsNotApplicable()
     */
    public CreateOrEditMappingResult markVariableAsNotApplicable(Mapping mapping, String userComments) {
    	String mappingStatus = mappingRepository.retrieveStatus(mapping.getVariable().getVariableId());
    	CreateOrEditMappingResult createOrEditMappingResult = new CreateOrEditMappingResult();
    	List list = new ArrayList();
    	if(mappingStatus.equals(DomainConstants.STATUS_NOT_APPLICABLE)){
    		list.add("MAPPING_ALREADY_NOT_APPLiCABLE");
    		createOrEditMappingResult.setStatusCodes(list);
			createOrEditMappingResult.setMapping(mapping);
			createOrEditMappingResult.setStatus(DomainConstants.FAILURE_STATUS);
			return createOrEditMappingResult;
    		
    	}
        try{
			 lockService.aquireLock(mapping);
		 }catch (MappingLockedByAnotherUserException e){
			 list.add("MAPPING_LOCKED_ANOTHER_USER");
			 createOrEditMappingResult.setStatusCodes(list);
			 createOrEditMappingResult.setMapping(mapping);
			 createOrEditMappingResult.setStatus(DomainConstants.FAILURE_STATUS);
			 return createOrEditMappingResult;
		 }
		 
		 manageDeltaNotApplicableDetails(null, mapping);	
		 
     lockService.deleteLock(mapping);
     
	 boolean persistStatus = mappingRepository.persistNewVariableMapping(mapping);
	 
	 auditTrailService.logMapping(mapping, userComments);
	 Mapping retrieveMapping = retrieveMapping(mapping.getVariable().getVariableId());
	 createOrEditMappingResult.setMapping(retrieveMapping);
	 if(persistStatus){
	 	
	 	createOrEditMappingResult.setStatus(DomainConstants.SUCCESS_STATUS);
	 }
	 else{
	 	
	 	createOrEditMappingResult.setStatus(DomainConstants.FAILURE_STATUS);
	 }
     return createOrEditMappingResult;
    }
    
	public Mapping retrieveMappingToCopyMapping(String variableId){
		
		Mapping mapping = retrieveMapping(variableId);
		return mapping;
	}
    
	public void transferredToTestRegion(int inClauseSize, int batchSize, String transferringUser){
		
		List<Mapping> mappingList = mappingRepository.retrieveMappingForTestDataFeed();	
		log.debug("mappingList size = "+ mappingList.size());
		//log.debug("transferredToTestRegion callled "+mapping.getDataFeedInd()+" "+mapping.getVariableMappingStatus()+" variable Id "+mapping.getVariable().getVariableId());
		int updateStatus = stateTransitionService.transferMappingToTestRegion();	
		log.debug("Update Count : "+ updateStatus);
		if(null != mappingList && mappingList.size() != 0) {
			for(Mapping mapping : mappingList) {
				if(transferringUser != null) {
					mapping.getUser().setLastUpdatedUserName(transferringUser);
				}
				BxUtil.doStatusChangeAfterTest(mapping);
			}
		}
		if(updateStatus != 0){
			auditTrailService.logMapping(inClauseSize,batchSize,mappingList, null, null);
		}
	}
	public void transferredToTestRegion(String variableId, String transferringUser){
		
		Mapping mapping = mappingRepository.retrieveMapping(variableId);		
		log.debug(ESAPI.encoder().encodeForHTML("transferredToTestRegion callled "+mapping.getDataFeedInd()+" "+mapping.getVariableMappingStatus()+" variable Id "+mapping.getVariable().getVariableId()));
		if(mapping.getInTempTable().equals("Y")){
			Mapping tempMapping = mappingRepository.retrieveBeingModified(variableId);
			mapping.setVariableMappingStatus(tempMapping.getVariableMappingStatus());
			mapping.setDataFeedInd(tempMapping.getDataFeedInd());
		}
		BxUtil.doStatusChangeAfterTest(mapping);
		int updateStatus = stateTransitionService.transferMappingToTestRegion(mapping);	
		if(transferringUser != null) {
			mapping.getUser().setLastUpdatedUserName(transferringUser);
		}
		if(updateStatus == 1){
			
			auditTrailService.logMapping(mapping, null);
		}
		
	}
	
	/**
	 * @return Returns the mappingRepository.
	 */
	public MappingRepository getMappingRepository() {
		return mappingRepository;
	}

	/**
	 * @param mappingRepository The mappingRepository to set.
	 */
	public void setMappingRepository(MappingRepository mappingRepository) {
		this.mappingRepository = mappingRepository;
	}	
	/**
	 * @return Returns the stateTransitionService.
	 */
	public StateTransitionService getStateTransitionService() {
		return stateTransitionService;
	}
	/**
	 * @param stateTransitionService The stateTransitionService to set.
	 */
	public void setStateTransitionService(
			StateTransitionService stateTransitionService) {
		this.stateTransitionService = stateTransitionService;
	}
	/**
	 * @return Returns the auditTrailService.
	 */
	public AuditTrailService getAuditTrailService() {
		return auditTrailService;
	}
	/**
	 * @param auditTrailService The auditTrailService to set.
	 */
	public void setAuditTrailService(AuditTrailService auditTrailService) {
		this.auditTrailService = auditTrailService;
	}

	/**
	 * @return Returns the hippaSegmentService.
	 */
	public HippaSegmentService getHippaSegmentService() {
		return hippaSegmentService;
	}
	/**
	 * @param hippaSegmentService The hippaSegmentService to set.
	 */
	public void setHippaSegmentService(HippaSegmentService hippaSegmentService) {
		this.hippaSegmentService = hippaSegmentService;
	}
	/**
	 * @return Returns the lockService.
	 */
	public LockService getLockService() {
		return lockService;
	}
	/**
	 * @param lockService The lockService to set.
	 */
	public void setLockService(LockService lockService) {
		this.lockService = lockService;
	}

	public ValidationService getWpdValidationService() {
		return wpdValidationService;
	}

	public void setWpdValidationService(ValidationService wpdValidationService) {
		this.wpdValidationService = wpdValidationService;
	}
	
	/**
	 * Sends a list of mapping filed to update
	 * @param massUpdateCriteria
	 * @param userComments
	 * @return
	 */
	public List save(List massUpdateCriteria, String userComments, MassUpdateTracker massUpdateTracker, boolean authorizedEditLockVar){
		massUpdateTracker.setTotalCount(massUpdateCriteria.size());
		massUpdateTracker.setCompletedCount(0);
		Iterator itr = massUpdateCriteria.iterator();
		List mappingResultList = new ArrayList();
		
		//to populate the warning message for sensitive benefit indicator changed to Y warning message --BXNI June 2012 Release
		List warningMessageVarSensitivePresent = SimulationResourceBundle.getResourceBundle(
				ValidatorConstants.WARNING_MESSAGE_VAR_SENSITIVE_IND_PRESENT,
				ValidatorConstants.PROPERTY_FILE_NAME);
		//to populate the warning message for sensitive benefit indicator changed to N warning message --BXNI June 2012 Release
		List warningMessageVarSensitiveNotPresent = SimulationResourceBundle.getResourceBundle(
				ValidatorConstants.WARNING_MESSAGE_VAR_SENSITIVE_IND_NOT_PRESENT,
				ValidatorConstants.PROPERTY_FILE_NAME);
		
		while(itr.hasNext()){
			Mapping mapping = null;
			try{
				boolean isLockAvailable = true;
				MassUpdateCriteria muc = (MassUpdateCriteria)itr.next();
				mapping = MappingUtil.findMapping(this, muc);
				
				String varaiableId = mapping.getVariable().getVariableId();
				CreateOrEditMappingResult createOrEditMappingResult = new CreateOrEditMappingResult();
				Variable variable = new Variable();
				variable.setVariableId(varaiableId);
				/** SSCR 19537 April 04 - Mass update should be prevented if the user updating 
				 * EB03 associated values for a custom message with individual EB03 mapping. */
				if (DomainConstants.Y.equals(mapping.getIndvdlEb03AssnIndicator()) && MappingUtil.isEb03AssnValuesPresent(muc)) {
						List<String> list = new ArrayList<String>();
						List <String> parmList = new ArrayList<String>();
						parmList.add(mapping.getVariable().getVariableId());
						String message = BxResourceBundle.getResourceBundle(ValidatorConstants.MASSUPDATE_ERROR_INVDL_EB03_ASSN_VAR, parmList);
						list.add(message);
						List variableList = retrieveVariableInfo(variable);
						mapping.setVariableList(variableList);
						createOrEditMappingResult.setMapping(mapping);
						createOrEditMappingResult.setStatus(DomainConstants.FAILURE_STATUS);
						createOrEditMappingResult.setStatusCodes(list);
						mappingResultList.add(createOrEditMappingResult);
				} else {
					mapping = MappingUtil.mergeMapping(mapping, muc);
					if(null != mapping.getNoteTypeCode()){
						mapping.getNoteTypeCode().setName(DomainConstants.NOTE_TYPE_CODE);
					}
					
					
					if(!authorizedEditLockVar){
						List unlockedVarList = auditLockService.getAuditLockStatusOfVariable(varaiableId, true, true);
						if(unlockedVarList.size() > 0){
							throw new EBXException("Variable is audit locked. It cannot be updated");
						}
					}
					
					
					//added for getting in audit trail
					String auditStatus = mapping.getAuditLock();
					if (null !=auditStatus && !"".equalsIgnoreCase(auditStatus)) {
						if("N".equalsIgnoreCase(auditStatus.trim())) {
							mapping.setVariableStatusForAuditTrail(0);
						} else if("Y".equalsIgnoreCase(auditStatus.trim())) {
							mapping.setVariableStatusForAuditTrail(1);
						}
					}
					
					if(!DomainConstants.UNMAPPED_STATUS.equals(mapping.getVariableMappingStatus().trim())){
						String mappingStatus = mappingRepository.retrieveStatus(varaiableId);
						log.info("inside serviceimpl...");

						//insert into temp only when the mapping is send to production.
						if(mappingStatus.equals(DomainConstants.STATUS_PUBLISHED)){			
							// chnage for may release - acquiring lock when a published mapping is edited			
							try{
								 lockService.aquireLock(mapping);
							 }catch (MappingLockedByAnotherUserException e){
								 isLockAvailable = false;
								 createOrEditMappingResult.setStatus(DomainConstants.LOCKED_STATUS);
								 List list = new ArrayList(1);
								 list.add(e.getMessage());
								 createOrEditMappingResult.setStatusCodes(list);
								 List variableList = retrieveVariableInfo(variable);
								 mapping.setVariableList(variableList);
								 createOrEditMappingResult.setMapping(mapping);
								 mappingResultList.add(createOrEditMappingResult);
							 }		
							 mapping.setVariableMappingStatus(DomainConstants.STATUS_BEING_MODIFIED);
							 stateTransitionService.transformToBeingModified(mapping);
						}
						else {
							try{
								 lockService.validateUserLock(mapping);
							 }catch (MappingLockedByAnotherUserException e){
								 isLockAvailable = false;
								 createOrEditMappingResult.setStatus(DomainConstants.LOCKED_STATUS);
								 List list = new ArrayList(1);
								 list.add(e.getMessage());
								 createOrEditMappingResult.setStatusCodes(list);
								 List variableList = retrieveVariableInfo(variable);
								 mapping.setVariableList(variableList);
								 createOrEditMappingResult.setMapping(mapping);
								 mappingResultList.add(createOrEditMappingResult);
							 }		
						}
						if(isLockAvailable){
							/**
							 * Sensitive Benefit Indicator is automatically changed based on the EB03 values --BXNI June Release
							 * If Variable/Header Rule contains a sensitive EB03, then indicator is set to Y
							 * If variable/Header Rule contains no sensitive EB03, then the indicator is set to N
							 * BXNI June 2012 Release
							 */
							boolean isSensitiveBenefitIndicatorChanged = MappingUtil.updateSensitiveBenefitIndicator(mapping);
							CreateOrEditMappingResult resultObjectToAddSensitiveWarning = saveMapping(mapping,userComments);
							if(isSensitiveBenefitIndicatorChanged && ("Y").equals(mapping.getSensitiveBenefitIndicator())){
								resultObjectToAddSensitiveWarning.getWarningMsgsList().add(warningMessageVarSensitivePresent);
							}else if(isSensitiveBenefitIndicatorChanged && ("N").equals(mapping.getSensitiveBenefitIndicator())){
								resultObjectToAddSensitiveWarning.getWarningMsgsList().add(warningMessageVarSensitiveNotPresent);
							}
							mappingResultList.add(resultObjectToAddSensitiveWarning);
						}
					}else{
						/**
						 * Sensitive Benefit Indicator is automatically changed based on the EB03 values --BXNI June Release
						 * If Variable/Header Rule contains a sensitive EB03, then indicator is set to Y
						 * If variable/Header Rule contains no sensitive EB03, then the indicator is set to N
						 * BXNI June 2012 Release
						 */
						boolean isSensitiveBenefitIndicatorChanged = MappingUtil.updateSensitiveBenefitIndicator(mapping);
						CreateOrEditMappingResult resultObjectToAddSensitiveWarning = createOrEditVariableMapping(mapping,userComments);
						if(isSensitiveBenefitIndicatorChanged && ("Y").equals(mapping.getSensitiveBenefitIndicator())){
							resultObjectToAddSensitiveWarning.getWarningMsgsList().add(warningMessageVarSensitivePresent);
						}else if(isSensitiveBenefitIndicatorChanged && ("N").equals(mapping.getSensitiveBenefitIndicator())){
							resultObjectToAddSensitiveWarning.getWarningMsgsList().add(warningMessageVarSensitiveNotPresent);
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
	

	/**
	 *  Sends list of mapping to schedule to test
	 * @param massUpdateCriteria
	 * @param userCmnts
	 * @return
	 */
	public List sendToTest(List massUpdateCriteria, String userCmnts, MassUpdateTracker massUpdateTracker, boolean authorizedEditLockVar){
		massUpdateTracker.setTotalCount(massUpdateCriteria.size());
		massUpdateTracker.setCompletedCount(0);
		Iterator itr = massUpdateCriteria.iterator();
		List mappingResultList = new ArrayList();
		while(itr.hasNext()){
			Mapping mapping = null;
			try{
				MassUpdateCriteria muc = (MassUpdateCriteria)itr.next();
				mapping = MappingUtil.findMapping(this, muc);
				MappingUtil.checkStatusForStatusUpdate(DomainConstants.STATUS_SCHEDULED_TO_TEST, mapping);
				/*if(!authorizedEditLockVar){
					List unlockedVarList = auditLockService.getAuditLockStatusOfVariable(muc.getVariableId().trim(), true, true);
					if(unlockedVarList.size() > 0){
						throw new EBXException("Variable is audit locked. It cannot be send to test");
					}
				}*/
				if(!DomainConstants.UNMAPPED_STATUS.equals(mapping.getVariableMappingStatus().trim())){
					mappingResultList.add(getMappingResult(sendToTest(mapping, userCmnts, null), mapping));
				}else{
					CreateOrEditMappingResult mappingResult = createOrEditVariableMapping(mapping,userCmnts);
					if((null != mappingResult.getErrorMsgsList() && mappingResult.getErrorMsgsList().size() > 0) || mappingResult.isValidationSucess()){
						mappingResultList.add(getMappingResult(mappingResult, mapping));
					}else{
						mappingResultList.add(getMappingResult(sendToTest(mapping, userCmnts, null), mapping));
					}
				}
				muc.setUpdatedStatus(DomainConstants.STATUS_SCHEDULED_TO_TEST);
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
	 * Sends list of mapping to set as not applicable
	 * @param massUpdateCriteria
	 * @param userCmnts
	 * @return
	 */
	public List notApplicable(List massUpdateCriteria, String userCmnts, MassUpdateTracker massUpdateTracker, boolean authorizedEditLockVar ){
		massUpdateTracker.setTotalCount(massUpdateCriteria.size());
		massUpdateTracker.setCompletedCount(0);
		Iterator itr = massUpdateCriteria.iterator();
		List mappingResultList = new ArrayList();
		while(itr.hasNext()){
			Mapping mapping = null;
			try{
				MassUpdateCriteria muc = (MassUpdateCriteria)itr.next();
				mapping = MappingUtil.findMapping(this, muc);
				if(!authorizedEditLockVar){
					List unlockedVarList = auditLockService.getAuditLockStatusOfVariable(muc.getVariableId().trim(), true, true);
					if(unlockedVarList.size() > 0){
						throw new EBXException("Variable is audit locked. It cannot be mark as NOT APPLILCABLE");
					}
				}
				MappingUtil.checkStatusForStatusUpdate(DomainConstants.STATUS_NOT_APPLICABLE, mapping);
				if(DomainConstants.STATUS_NOT_APPLICABLE.equals(mapping.getVariableMappingStatus().trim())){
					throw new EBXException(new StringBuffer("This mapping is already in NOT APPLICABLE status").toString());
				}
				if(!DomainConstants.UNMAPPED_STATUS.equals(mapping.getVariableMappingStatus().trim())){
					mappingResultList.add(getMappingResult(markAsNotApplicable(mapping, userCmnts), mapping));
				}else{
					mapping.setVariableMappingStatus(DomainConstants.STATUS_NOT_APPLICABLE);
					if(!BxUtil.hasText(mapping.getVariable().getDescription())){
						throw new EBXException("Can not persist mapping with Descriptn empty.");
					}
					mappingResultList.add(getMappingResult(markVariableAsNotApplicable(mapping, userCmnts), mapping));
				}
				muc.setUpdatedStatus(DomainConstants.STATUS_NOT_APPLICABLE);
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
	 * Sends list of mapping to schedule to production
	 * @param massUpdateCriteria
	 * @param userCmnts
	 * @return
	 */
	public List approve(List massUpdateCriteria, String userCmnts, MassUpdateTracker massUpdateTracker, boolean authorizedEditLockVar){
		massUpdateTracker.setTotalCount(massUpdateCriteria.size());
		massUpdateTracker.setCompletedCount(0);
		Iterator itr = massUpdateCriteria.iterator();
		List mappingResultList = new ArrayList();
		while(itr.hasNext()){
			Mapping mapping = null;
			try{
				MassUpdateCriteria muc = (MassUpdateCriteria)itr.next();
				mapping = MappingUtil.findMapping(this, muc);
				CreateOrEditMappingResult approveResult = null;
				boolean isScheduledToProduction = false;
				MappingUtil.checkStatusForStatusUpdate(DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION, mapping);
				/*if(!authorizedEditLockVar){
					List unlockedVarList = auditLockService.getAuditLockStatusOfVariable(muc.getVariableId().trim(), true, true);
					if(unlockedVarList.size() > 0){
						throw new EBXException("Variable is audit locked. It cannot update");
					}
				}*/
				if(!DomainConstants.UNMAPPED_STATUS.equals(mapping.getVariableMappingStatus().trim())){
										
					/********Changes for 2011 September Release Starts ***********************/
					approveResult = approve(mapping, userCmnts, null);
					if (null != approveResult 
							&& DomainConstants.STATUS_OBJECT_TRANSFERRED
							.equals(approveResult.getPreviousVariableMappingStatus())
							&& null != approveResult.getMapping()
							&& DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION
							.equals(approveResult.getMapping().getVariableMappingStatus())) {
						isScheduledToProduction = true;
					}
					CreateOrEditMappingResult mappingResult = getMappingResult(approveResult, mapping);
					if (isScheduledToProduction) {
						mappingResult.getWarningMsgsList().add("The mapping is scheduled to production");
					}
					else {
						mappingResult.getWarningMsgsList().add("The mapping is scheduled to test and will" +
								" be moved to production in the nightly batch");
					}
					mappingResultList.add(mappingResult);
					/********Changes for 2011 September Release Ends ***********************/
					
				}else{
					CreateOrEditMappingResult mappingResult = createOrEditVariableMapping(mapping,userCmnts);
					if((null != mappingResult.getErrorMsgsList() && mappingResult.getErrorMsgsList().size() > 0) || mappingResult.isValidationSucess()){
						mappingResultList.add(getMappingResult(mappingResult,mapping));
					}else{
						mappingResultList.add(getMappingResult(approve(mapping, userCmnts, null),mapping));
					}  
				}
				if (isScheduledToProduction) {
					muc.setUpdatedStatus(DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION);
				}
				else {
					muc.setUpdatedStatus(DomainConstants.STATUS_SCHEDULED_TO_TEST);
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
	
	private CreateOrEditMappingResult getMappingResult(CreateOrEditMappingResult result, Mapping mapping){
		result.setMapping(mapping);
		return result;
	}
	private void manageDeltaNotApplicableDetails(Mapping existingMapping, Mapping updatedMapping) {

		bxoutboundDataFeedHelperService.manageDeltaNotApplicableDetails(existingMapping, updatedMapping, DomainConstants.VARIABLE_IDENTIFIER);
	}

	public BXOutboundDataFeedHelperService getBxoutboundDataFeedHelperService() {
		return bxoutboundDataFeedHelperService;
	}

	public void setBxoutboundDataFeedHelperService(
			BXOutboundDataFeedHelperService bxoutboundDataFeedHelperService) {
		this.bxoutboundDataFeedHelperService = bxoutboundDataFeedHelperService;
	}

	/**
	 * 
	 * @return
	 */
	public AuditLockService getAuditLockService() {
		return auditLockService;
	}

	/**
	 * 
	 * @param auditLockService
	 */
	public void setAuditLockService(AuditLockService auditLockService) {
		this.auditLockService = auditLockService;
	}
	/**
	 * lock all the mapping in the list.
	 * @param massUpdateCriteria
	 * @param userComments
	 * @param massUpdateTracker
	 * @return
	 */
	public List lock(List massUpdateCriteria, String userComments,
			MassUpdateTracker massUpdateTracker) {
		massUpdateTracker.setTotalCount(massUpdateCriteria.size());
		massUpdateTracker.setCompletedCount(0);
		Iterator itr = massUpdateCriteria.iterator();
		List mappingResultList = new ArrayList();
		while (itr.hasNext()) {
			Mapping mapping = null;
			try {
				MassUpdateCriteria muc = (MassUpdateCriteria) itr.next();
				mapping = MappingUtil.findMapping(this, muc);
				String mappingStatus = mappingRepository.retrieveStatus(muc
						.getVariableId().trim());
				if (DomainConstants.STATUS_PUBLISHED
						.equalsIgnoreCase(mappingStatus)
						|| DomainConstants.STATUS_NOT_APPLICABLE
								.equalsIgnoreCase(mappingStatus)) {
					auditLockService.retrieveTransactionLock(Mapping.class
							.getName(), muc.getVariableId().trim(), muc
							.getUserId());

					if (auditLockService
							.isAuditLock(muc.getVariableId().trim())) {
						mappingResultList.add(getMappingResult(mapping, false,
								DomainConstants.LOCK_ERROR_MSG, null));
					} else {
						mapping.getVariable().setAuditLock(true);
						boolean lockSucess = auditLockService.auditLock(
								mapping, mapping.getVariable()
										.getVariableSystem());
						auditTrailService.logMapping(mapping, userComments,
								DomainConstants.LOCKED);
						mappingResultList.add(getMappingResult(mapping,
								lockSucess, null, DomainConstants.LOCK_SUCESS_MSG));

					}
				} else {
					mappingResultList.add(getMappingResult(mapping, false,
					"Mapping is not PUBLISHED or NOT APPLICABLE", null));
				}

			} catch (MappingLockedByAnotherUserException e) {
				mappingResultList.add(BxUtil.getExceptionResult(e, mapping));
			} catch (EBXException e) {
				mappingResultList.add(BxUtil.getExceptionResult(e, mapping));
			} catch (Exception e) {
				e.printStackTrace();
				mappingResultList.add(BxUtil.getExceptionResult(e, mapping));
			}
			massUpdateTracker.setCompletedCount(massUpdateTracker
					.getCompletedCount() + 1);
		}
		return mappingResultList;
	}
	
	
	public List unlock(List massUpdateCriteria, String userComments,
			MassUpdateTracker massUpdateTracker) {
		massUpdateTracker.setTotalCount(massUpdateCriteria.size());
		massUpdateTracker.setCompletedCount(0);
		Iterator itr = massUpdateCriteria.iterator();
		List mappingResultList = new ArrayList();
		while (itr.hasNext()) {
			Mapping mapping = null;
			try {
				MassUpdateCriteria muc = (MassUpdateCriteria) itr.next();
				mapping = MappingUtil.findMapping(this, muc);
				auditLockService
						.retrieveTransactionLock(Mapping.class.getName(), muc
								.getVariableId().trim(), muc.getUserId());
				boolean isMappingBeingModified = auditLockService
						.isMappingBeingModified(muc.getVariableId().trim());
				if (isMappingBeingModified) {
					if (auditLockService.isAuditUnLockInTemp(muc.getVariableId()
							.trim())) {
						mappingResultList.add(getMappingResult(mapping, false,
								DomainConstants.UNLOCK_ERROR_MSG, null));
					} else {
						mapping.getVariable().setAuditLock(false);
						boolean lockSucess = auditLockService.auditTempUnLock(
								mapping, mapping.getVariable()
										.getVariableSystem());
						auditTrailService.logMapping(mapping, userComments,
								DomainConstants.UNLOCKED);
						mappingResultList.add(getMappingResult(mapping,
								lockSucess, null, DomainConstants.UNLOCK_SUCESS_MSG));

					}
				} else {
					if (auditLockService.isAuditUnLock(muc.getVariableId()
							.trim())) {
						mappingResultList.add(getMappingResult(mapping, false,
								DomainConstants.UNLOCK_ERROR_MSG, null));
					} else {
						mapping.getVariable().setAuditLock(false);
						boolean lockSucess = auditLockService.auditUnLock(
								mapping, mapping.getVariable()
										.getVariableSystem());
						auditTrailService.logMapping(mapping, userComments,
								DomainConstants.UNLOCKED);
						mappingResultList.add(getMappingResult(mapping,
								lockSucess, null, DomainConstants.UNLOCK_SUCESS_MSG));

					}
				}

			} catch (MappingLockedByAnotherUserException e) {
				mappingResultList.add(BxUtil.getExceptionResult(e, mapping));
			} catch (EBXException e) {
				mappingResultList.add(BxUtil.getExceptionResult(e, mapping));
			} catch (Exception e) {
				e.printStackTrace();
				mappingResultList.add(BxUtil.getExceptionResult(e, mapping));
			}
			massUpdateTracker.setCompletedCount(massUpdateTracker
					.getCompletedCount() + 1);
		}
		return mappingResultList;
	}
	
	private MappingResult getMappingResult(Mapping mapping, boolean sucess, String errorMessage, String infomationMessage ) {
		 MappingResult mappingResult = new MappingResult();
		 mappingResult.setStatus(sucess);
		 if(null != errorMessage && errorMessage.trim().length() > 0){
			 List errorList = new ArrayList(1);
			 errorList.add(errorMessage);
			 mappingResult.setErrorMsgsList(errorList);
		 }
		 if(null != infomationMessage && infomationMessage.trim().length() > 0){
			 List warningList = new ArrayList(1);
			 warningList.add(infomationMessage);
			 mappingResult.setWarningMsgsList(warningList);
		 }
		 mappingResult.setMapping(mapping);
		 return mappingResult;
	}
	
	@Override

	public Mapping autoPopulateByFormat(Variable variable, Mapping mapping,
			String hippaSegmentToPopulate, String eb09Value) {

		HippaSegment hippaSegment = null;
		if (null != variable
				&& null != variable.getVariableFormat()
				&& null != hippaSegmentToPopulate
				&& !hippaSegmentToPopulate.equalsIgnoreCase("")
				&& (hippaSegmentToPopulate
						.equalsIgnoreCase(DomainConstants.EB09_NAME) || hippaSegmentToPopulate
						.equalsIgnoreCase(DomainConstants.HSD01_NAME))) {
			String varFormat = variable.getVariableFormat();
			if (varFormat.equalsIgnoreCase(DomainConstants.VST)
					|| varFormat.equalsIgnoreCase(DomainConstants.VISIT)
					|| varFormat.equalsIgnoreCase(DomainConstants.VISITS)) {
				hippaSegment = hippaSegmentService
						.getHippaSegmentsForAutocomplete(
								hippaSegmentToPopulate, DomainConstants.VS,
								variable, 10);
			} else if (varFormat.equalsIgnoreCase(DomainConstants.HOUR_HR)
					|| varFormat.equalsIgnoreCase(DomainConstants.HRS)
					|| varFormat.equalsIgnoreCase(DomainConstants.HOUR)
					|| varFormat.equalsIgnoreCase(DomainConstants.HOURS)) {
				hippaSegment = hippaSegmentService
						.getHippaSegmentsForAutocomplete(
								hippaSegmentToPopulate, DomainConstants.HS,
								variable, 10);
			} else if (varFormat.equalsIgnoreCase(DomainConstants.DAY)
					|| varFormat.equalsIgnoreCase(DomainConstants.DAYS)
					|| varFormat.equalsIgnoreCase(DomainConstants.LEN)
					|| varFormat.equalsIgnoreCase(DomainConstants.DAY_DYS)
					|| varFormat.equalsIgnoreCase(DomainConstants.DAY_DY)) {
				hippaSegment = hippaSegmentService
						.getHippaSegmentsForAutocomplete(
								hippaSegmentToPopulate, DomainConstants.DY,
								variable, 10);
			} else if (varFormat.equalsIgnoreCase(DomainConstants.MTH)
					|| varFormat.equalsIgnoreCase(DomainConstants.MTHS)
					|| varFormat.equalsIgnoreCase(DomainConstants.MONTH)
					|| varFormat.equalsIgnoreCase(DomainConstants.MONTHS)) {
				hippaSegment = hippaSegmentService
						.getHippaSegmentsForAutocomplete(
								hippaSegmentToPopulate, DomainConstants.MN,
								variable, 10);
			} else if (!hippaSegmentToPopulate
					.equalsIgnoreCase(DomainConstants.HSD01_NAME)
					&& (varFormat.equalsIgnoreCase(DomainConstants.YR) || varFormat
							.equalsIgnoreCase(DomainConstants.YRS))) {
				hippaSegment = hippaSegmentService
						.getHippaSegmentsForAutocomplete(
								hippaSegmentToPopulate, DomainConstants.YY,
								variable, 10);
			}else if(hippaSegmentToPopulate
					.equalsIgnoreCase(DomainConstants.HSD01_NAME)
					&& (varFormat.equalsIgnoreCase(DomainConstants.OCC) 
							||	varFormat.equalsIgnoreCase(DomainConstants.OCRS)
							||	varFormat.equalsIgnoreCase(DomainConstants.OCR))){
								
				if(!StringUtils.isBlank(eb09Value) && eb09Value.equals(DomainConstants.VS)){
					hippaSegment = hippaSegmentService
					.getHippaSegmentsForAutocomplete(
							hippaSegmentToPopulate, DomainConstants.VS,
							variable, 10);
				}else{
					hippaSegment = hippaSegmentService
					.getHippaSegmentsForAutocomplete(
							hippaSegmentToPopulate, DomainConstants.FL,
							variable, 10);
				}
			}
						
			if (null != hippaSegmentToPopulate
					&& hippaSegmentToPopulate
							.equalsIgnoreCase(DomainConstants.EB09_NAME)
					&& null != hippaSegment
					&& null != hippaSegment.getHippaCodePossibleValues()) {
				HippaSegment eb09 = new HippaSegment();
				eb09.setHippaCodeSelectedValues(hippaSegment
						.getHippaCodePossibleValues());
				mapping.setEb09(eb09);
				return mapping;
			}
			else if (null != hippaSegmentToPopulate
					&& hippaSegmentToPopulate
					.equalsIgnoreCase(DomainConstants.HSD01_NAME)
					&& null != hippaSegment
					&& null != hippaSegment.getHippaCodePossibleValues()) {
				HippaSegment hsd01 = new HippaSegment();
				hsd01.setHippaCodeSelectedValues(hippaSegment
						.getHippaCodePossibleValues());
				mapping.setHsd01(hsd01);
				return mapping;
			}

		}
		return mapping;
	}
}