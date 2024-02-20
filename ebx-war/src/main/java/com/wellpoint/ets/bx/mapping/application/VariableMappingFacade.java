package com.wellpoint.ets.bx.mapping.application;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.vo.CreateOrEditMappingResult;
import com.wellpoint.ets.bx.mapping.util.MassUpdateTracker;

public interface VariableMappingFacade {
    
	public CreateOrEditMappingResult createOrEditVariableMapping(Mapping mapping, String userComments);
	
	public CreateOrEditMappingResult save(Mapping mapping, String userComments);
	
	public CreateOrEditMappingResult done(Mapping mapping, String userComments);
	
	//public CreateOrEditMappingResult notApplicable(Mapping mapping);
	
	public CreateOrEditMappingResult sendToTest(Mapping mapping, String userCmnts);
	
	public CreateOrEditMappingResult notApplicable(Mapping mapping, String userCmnts);
	
	public CreateOrEditMappingResult approve(Mapping mapping, String userCmnts);
	
	public CreateOrEditMappingResult discardChanges(Mapping mapping, String userCmnts);
	
	public List viewHistory(String variableId, Integer noOfAuditInfo);
	
	public Mapping viewMapping(String variableId, Integer noOfAuditInfo);
	
	public CreateOrEditMappingResult editMapping(Mapping mapping);
	
	public List viewAllAuditTrail(String variableId);
	
	public List viewVariableDetails(Variable variable);
	
	public int isValidVariableIDStatus(String variableId);
	
	public CreateOrEditMappingResult saveAndsendToTest(Mapping mapping, String userCmnts);
	
	public CreateOrEditMappingResult saveAndApprove(Mapping mapping, String userCmnts);
	//copy to functionality
	public CreateOrEditMappingResult CopyVariableMapping(String oldVariableId, Mapping mapping);
	
	public Mapping retrieveMapping(String variableId);
	
	/**
	 * @param variableId
	 * The method will retrieve the audit trail information in detail of a mapping with status 'PUBLISHED'
	 * @return
	 */
	public List retreiveAuditTrailInDetail(String variableId);
	
	/**
	 * @param mapping
	 * This method will create  mapping for the variable and mark it as not applicable.
	 * No validation is done on the mapping.
	 * @return
	 */
	public CreateOrEditMappingResult markVariableAsNotApplicable(Mapping mapping, String userComments);
	/**
	 * Unlocks a mapping for another user to edit the mapping
	 * @param mapping
	 * @return boolean
	 */
	public boolean unlock (Mapping mapping);
	
	/**
	 * Sends a list of mapping filed to update
	 * @param massUpdateCriteria
	 * @param userComments
	 * @return
	 */
	public List save(List massUpdateCriteria, String userComments, MassUpdateTracker massUpdateTracker, boolean authorizedEditLockVar);
	
	/**
	 *  Sends list of mapping to schedule to test
	 * @param massUpdateCriteria
	 * @param userCmnts
	 * @return
	 */
	public List sendToTest(List massUpdateCriteria, String userCmnts, MassUpdateTracker massUpdateTracker,boolean authorizedEditLockVar);
	
	/**
	 * Sends list of mapping to set as not applicable
	 * @param massUpdateCriteria
	 * @param userCmnts
	 * @return
	 */
	public List notApplicable(List massUpdateCriteria, String userCmnts, MassUpdateTracker massUpdateTracker, boolean authorizedEditLockVar);
	
	/**
	 * Sends list of mapping to schedule to production
	 * @param massUpdateCriteria
	 * @param userCmnts
	 * @return
	 */
	public List approve(List massUpdateCriteria, String userCmnts, MassUpdateTracker massUpdateTracker, boolean authorizedEditLockVar);
	/**
	 * Lock the variable for audit trail.
	 * @param mapping
	 * @param system 
	 * @return
	 */
	public boolean auditLock(Mapping mapping, String system);
	/**
	 * Unlock the variable for audit trail.
	 * @param mapping
	 * @param system 
	 * @return
	 */
	public boolean auditUnLock(Mapping mapping, String system);
	
	/**
	 * Checking the variable is already unlocked or not.
	 * @param variableId
	 * @return
	 */
	public boolean isAuditUnLock(String variableId);
	
	/**
	 * Checking the variable is already locked or not.
	 * @param variableId
	 * @return
	 */
	public boolean isAuditLock(String variableId);
	
	/**
	 * Checking the variable is Being modified or not.
	 * @param variableId
	 * @return
	 */
	public boolean isMappingBeingModified(String variableId);
	
	/**
	 * Checking the variable is already unlocked or not in Temp table.
	 * @param variableId
	 * @return
	 */
	public boolean isAuditUnLockInTemp(String variableId);
	
	/**
	 * Checking the variable is already locked or not in Temp Table.
	 * @param variableId
	 * @return
	 */
	public boolean isAuditLockInTemp(String variableId);

	/**
	 * Lock the variable for audit trail in temp.
	 * @param mapping
	 * @param system 
	 * @return
	 */
	public boolean auditTempLock(Mapping mapping, String system);

	/**
	 * Unlock the variable for audit trail in temp.
	 * @param mapping
	 * @param system 
	 * @return
	 */
	public boolean auditTempUnLock(Mapping mapping, String system);
	
	public boolean logMapping(Mapping mapping,String userComments,String locked);
	
	public List retrieveTransactionLock(String className, String keyId,String userId);

	public boolean removeExistingAuditTrail(String variableId, String system);
	
	public List retrieveAuditTrail(String variableId, Integer rowNum);

	/**
	 * lock all the selected variables using mass lock.
	 * @param massUpdateCriteria
	 * @param userCmnts
	 * @param massUpdateTracker
	 * @return
	 */
	public List lock(List massUpdateCriteria, String userCmnts, MassUpdateTracker massUpdateTracker);
	
	/**
	 * unlock all the selected variables using mass unlock.
	 * @param massUpdateCriteria
	 * @param userCmnts
	 * @param massUpdateTracker
	 * @return
	 */
	public List unlock(List massUpdateCriteria, String userCmnts, MassUpdateTracker massUpdateTracker);
	
	/**
	 * get the variable of given lock status
	 * @param variableIds
	 * @param locked
	 * @return
	 */
	public List getAuditLockStatusOfVariable(List variableIds, boolean locked, boolean checktemptable);
	
	/**
	 * 
	 * @param Variable
	 * @param mapping
	 * @return Mapping
	 */
	public Mapping autoPopulateByFormat(Variable variable, Mapping mapping, String hippaSegmentToPopulate, String eb09);
}