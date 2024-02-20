package com.wellpoint.ets.bx.mapping.application;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.service.AuditLockService;
import com.wellpoint.ets.bx.mapping.domain.service.AuditTrailService;
import com.wellpoint.ets.bx.mapping.domain.service.LockService;
import com.wellpoint.ets.bx.mapping.domain.service.VariableMappingService;
import com.wellpoint.ets.bx.mapping.domain.vo.CreateOrEditMappingResult;
import com.wellpoint.ets.bx.mapping.util.MassUpdateTracker;

public class VariableMappingFacadeImpl implements VariableMappingFacade {

	private VariableMappingService variableMappingService;
	private LockService lockService;
	private AuditLockService auditLockService;
	private AuditTrailService auditTrailService;

	protected final Log log = LogFactory
			.getLog(VariableMappingFacadeImpl.class);

	public CreateOrEditMappingResult createOrEditVariableMapping(Mapping mapping, String userComments) {

		return variableMappingService.createOrEditVariableMapping(mapping, userComments);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.ets.bx.mapping.application.VariableMappingFacade#save(com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
	 */
	public CreateOrEditMappingResult save(Mapping mapping, String userCmnts) {
		// TODO Auto-generated method stub
		return variableMappingService.saveMapping(mapping, userCmnts);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.ets.bx.mapping.application.VariableMappingFacade#done(com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
	 */
	public CreateOrEditMappingResult done(Mapping mapping, String userCmnts) {
		// TODO Auto-generated method stub
		return variableMappingService.saveMapping(mapping, userCmnts);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.ets.bx.mapping.application.VariableMappingFacade#notApplicable(com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
	 */
	public CreateOrEditMappingResult notApplicable(Mapping mapping,String userCmnts ) {
		return variableMappingService.markAsNotApplicable(mapping,userCmnts);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.ets.bx.mapping.application.VariableMappingFacade#sendToTest(com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
	 */
	public CreateOrEditMappingResult sendToTest(Mapping mapping, String userCmnts) {
			
		return variableMappingService.sendToTest(mapping,userCmnts,null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.ets.bx.mapping.application.VariableMappingFacade#approve(com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
	 */
	public CreateOrEditMappingResult approve(Mapping mapping, String userCmnts) {
		return variableMappingService.approve(mapping,userCmnts,null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.ets.bx.mapping.application.VariableMappingFacade#discardChanges(com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
	 */
	public CreateOrEditMappingResult discardChanges(Mapping mapping,String userCmnts) {
		
		
		return variableMappingService.discardChanges(mapping ,userCmnts);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.ets.bx.mapping.application.VariableMappingFacade#viewHistory(com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
	 */
	public List viewHistory(String variableId, Integer auditInfoLimit) {
	
			return variableMappingService.viewHistory(variableId, auditInfoLimit);
	 }
	public Mapping viewMapping(String varaiableId, Integer auditInfoLimit) {

		return getVariableMappingService().retreiveMapping(varaiableId, auditInfoLimit);
	}
	
	public List viewVariableDetails(Variable variable){
		
		return variableMappingService.retrieveVariableInfo(variable);
	}
	
	public int isValidVariableIDStatus(String variableId){
		return variableMappingService.isValidVariableIDStatus(variableId);
	}

	public List viewAllAuditTrail(String variableId) {

		return getVariableMappingService().retrieveAllAuditTrail(variableId);
	}
	
	public CreateOrEditMappingResult editMapping(Mapping mapping){
	
		return  variableMappingService.editMapping(mapping);
	}
	public CreateOrEditMappingResult saveAndsendToTest(Mapping mapping, String userCmnts){
		
		CreateOrEditMappingResult savedResult = null;
		savedResult = variableMappingService.saveMapping(mapping, userCmnts);
		if(savedResult.isValidationSucess()){
			
			savedResult = variableMappingService.sendToTest(savedResult.getMapping(),userCmnts,savedResult);
		}
		return savedResult;
	}
	
	public CreateOrEditMappingResult saveAndApprove(Mapping mapping, String userCmnts){
		CreateOrEditMappingResult savedResult = null;
		savedResult= variableMappingService.saveMapping(mapping, userCmnts);
		if(savedResult.isValidationSucess()){
			savedResult = variableMappingService.approve(savedResult.getMapping(),userCmnts,savedResult);
		}
		return savedResult; 
	}
	
	public Mapping retrieveMapping(String variableId){
		
		return variableMappingService.retrieveMappingToCopyMapping(variableId);
	}
	/**
	 * @return Returns the variableMappingService.
	 */
	public VariableMappingService getVariableMappingService() {
		return variableMappingService;
	}

	/**
	 * @param variableMappingService
	 *            The variableMappingService to set.
	 */
	public void setVariableMappingService(
			VariableMappingService variableMappingService) {
		this.variableMappingService = variableMappingService;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.application.VariableMappingFacade#CopyVariableMapping(com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
	 */
	public CreateOrEditMappingResult CopyVariableMapping(String oldVariableId,Mapping mapping) {
	
			return variableMappingService.copyVariableMapping(oldVariableId,mapping);
	}

    /* (non-Javadoc)
     * @see com.wellpoint.ets.bx.mapping.application.VariableMappingFacade#retreiveAuditTrailInDetail(java.lang.String)
     */
    public List retreiveAuditTrailInDetail(String variableId) {
        List auditTrailListInDetail = variableMappingService.retreiveAuditTrailInDetail(variableId);
        return auditTrailListInDetail;
    }

    /* (non-Javadoc)
     * @see com.wellpoint.ets.bx.mapping.application.VariableMappingFacade#markVariableAsNotApplicable()
     */
    public CreateOrEditMappingResult markVariableAsNotApplicable(Mapping mapping, String userComments) {
        return variableMappingService.markVariableAsNotApplicable(mapping, userComments);
    }
    
    /**
	 * Sends a list of mapping filed to update
	 * @param massUpdateCriteria
	 * @param userComments
	 * @return
	 */
	public List save(List massUpdateCriteria, String userComments, MassUpdateTracker massUpdateTracker, boolean authorizedEditLockVar){
		return variableMappingService.save(massUpdateCriteria, userComments, massUpdateTracker, authorizedEditLockVar);
	}
	
	/**
	 *  Sends list of mapping to schedule to test
	 * @param massUpdateCriteria
	 * @param userCmnts
	 * @return
	 */
	public List sendToTest(List massUpdateCriteria, String userCmnts, MassUpdateTracker massUpdateTracker, boolean authorizedEditLockVar){
		return variableMappingService.sendToTest(massUpdateCriteria, userCmnts, massUpdateTracker, authorizedEditLockVar);
	}
	/**
	 * Sends list of mapping to set as not applicable
	 * @param massUpdateCriteria
	 * @param userCmnts
	 * @return
	 */
	public List notApplicable(List massUpdateCriteria, String userCmnts, MassUpdateTracker massUpdateTracker, boolean authorizedEditLockVar){
		return variableMappingService.notApplicable(massUpdateCriteria, userCmnts, massUpdateTracker, authorizedEditLockVar);
	}
	/**
	 * Sends list of mapping to schedule to production
	 * @param massUpdateCriteria
	 * @param userCmnts
	 * @return
	 */
	public List approve(List massUpdateCriteria, String userCmnts, MassUpdateTracker massUpdateTracker, boolean authorizedEditLockVar){
		return variableMappingService.approve(massUpdateCriteria, userCmnts, massUpdateTracker, authorizedEditLockVar);
	}

	/**
	 * Unlocks a mapping for another user to edit the mapping
	 * @param mapping
	 * @return boolean
	 */
	public boolean unlock(Mapping mapping) {
		boolean lockflag = lockService.deleteLock(mapping);
		return lockflag;
	}
	/**
	 * Lock the variable for audit trail.
	 */
	public boolean auditLock(Mapping mapping, String system) {
		boolean auditLock = auditLockService.auditLock(mapping, system);
		return auditLock;
	}
	/**
	 * Unlock the variable for audit trail.
	 */
	public boolean auditUnLock(Mapping mapping, String system) {
		boolean auditLock = auditLockService.auditUnLock(mapping, system);
		return auditLock;
	}

	public LockService getLockService() {
		return lockService;
	}

	public void setLockService(LockService lockService) {
		this.lockService = lockService;
	}

	public AuditLockService getAuditLockService() {
		return auditLockService;
	}

	public void setAuditLockService(AuditLockService auditLockService) {
		this.auditLockService = auditLockService;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.application.VariableMappingFacade#isAuditLock(java.lang.String)
	 * Checks whether the variable mapping is already locked or not
	 */
	public boolean isAuditLock(String variableId) {
		return auditLockService.isAuditLock(variableId);
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.application.VariableMappingFacade#isAuditUnLock(java.lang.String)
	 * Checks whether the variable mapping is already unlocked or not
	 */
	public boolean isAuditUnLock(String variableId) {
		return auditLockService.isAuditUnLock(variableId);
	}

	public boolean isMappingBeingModified(String variableId) {
		return auditLockService.isMappingBeingModified(variableId);
	}

	public boolean isAuditLockInTemp(String variableId) {
		return auditLockService.isAuditLockInTemp(variableId);
	}

	public boolean isAuditUnLockInTemp(String variableId) {
		return auditLockService.isAuditUnLockInTemp(variableId);
	}
	
	/**
	 * Lock the variable for audit trail.
	 */
	public boolean auditTempLock(Mapping mapping, String system) {
		boolean auditLock = auditLockService.auditTempLock(mapping, system);
		
		return auditLock;
	}
	/**
	 * Unlock the variable for audit trail.
	 */
	public boolean auditTempUnLock(Mapping mapping, String system) {
		boolean auditLock = auditLockService.auditTempUnLock(mapping, system);
		return auditLock;
	}

	public boolean logMapping(Mapping mapping, String userComments, String locked) {
		return auditTrailService.logMapping(mapping,userComments,locked);		
	}
	
	public boolean removeExistingAuditTrail(String variableId, String system) {
		return auditTrailService.removeExistingAuditTrail(variableId, system);
	}

	public void setAuditTrailService(AuditTrailService auditTrailService) {
		this.auditTrailService = auditTrailService;
	}

	public AuditTrailService getAuditTrailService() {
		return auditTrailService;
	}

	public List retrieveTransactionLock(String className, String keyId, String userId) {
		return auditLockService.retrieveTransactionLock(className,keyId,userId);
	}

	public List retrieveAuditTrail(String variableId, Integer rowNum) {
		return auditTrailService.retrieveAuditTrail(variableId, rowNum);
	}

	/**
	 * 
	 * @param massUpdateCriteria
	 * @param userCmnts
	 * @param massUpdateTracker
	 * @return
	 */
	public List lock(List massUpdateCriteria, String userCmnts, MassUpdateTracker massUpdateTracker){
		return variableMappingService.lock(massUpdateCriteria, userCmnts, massUpdateTracker);
	}
	
	/**
	 * 
	 * @param massUpdateCriteria
	 * @param userCmnts
	 * @param massUpdateTracker
	 * @return
	 */
	public List unlock(List massUpdateCriteria, String userCmnts, MassUpdateTracker massUpdateTracker){
		return variableMappingService.unlock(massUpdateCriteria, userCmnts, massUpdateTracker);
	}
	
	/**
	 * 
	 * @param variableIds
	 * @param locked
	 * @return
	 */
	public List getAuditLockStatusOfVariable(List variableIds, boolean locked, boolean checktemptable){
		return auditLockService.getAuditLockStatusOfVariable(variableIds, locked, checktemptable);
	}
	/**
	 * 
	 * @param variable
	 * @param mapping
	 * @param hippaSegmentName
	 * @return
	 */
	public Mapping autoPopulateByFormat(Variable variable, Mapping mapping, String hippaSegmentToPopulate, String eb09) { 
		return variableMappingService.autoPopulateByFormat(variable, mapping, hippaSegmentToPopulate, eb09);
		}


	
}