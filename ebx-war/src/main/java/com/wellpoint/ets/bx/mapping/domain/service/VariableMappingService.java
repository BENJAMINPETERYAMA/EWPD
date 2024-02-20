package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.exception.DomainException;
import com.wellpoint.ets.bx.mapping.domain.vo.CreateOrEditMappingResult;
import com.wellpoint.ets.bx.mapping.util.MassUpdateTracker;

public interface VariableMappingService {

	CreateOrEditMappingResult createOrEditVariableMapping(Mapping mapping, String userComments) throws DomainException;
		
	CreateOrEditMappingResult saveMapping(Mapping mapping, String userCmnts) throws DomainException;
	
	List viewHistory(String variableId, Integer noOfAuditInfo);
	
	Mapping retreiveMapping(String variableId, Integer noOfAuditInfo);
	
	CreateOrEditMappingResult editMapping(Mapping mapping);
	
	List retrieveAllAuditTrail(String variableId);
	
	CreateOrEditMappingResult sendToTest(Mapping mapping, String userCmnts,CreateOrEditMappingResult savedResult);
	
	CreateOrEditMappingResult markAsNotApplicable(Mapping mapping, String userCmnts);
	
	CreateOrEditMappingResult discardChanges(Mapping mapping, String userCmnts );
	
	CreateOrEditMappingResult approve(Mapping mapping, String userCmnts,CreateOrEditMappingResult savedResult);
	
	List retrieveVariableInfo(Variable variable);
	
	int isValidVariableIDStatus(String variableId);

	CreateOrEditMappingResult publish(String variableId);
	
	CreateOrEditMappingResult publish(String varaiableId, String publishingUser);
	
	CreateOrEditMappingResult publishMapping(int inClauseSize, int batchSize,List<String> varaiableId,String publishingUser);
	
	CreateOrEditMappingResult copyVariableMapping(String oldVariableId, Mapping mapping) throws DomainException;
	
	Mapping retrieveMappingToCopyMapping(String variableId);
	
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
	
	public void transferredToTestRegion(int inClauseSize, int batchSize,String transferringUser);
	public void transferredToTestRegion(String variableId, String transferringUser);
	
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
	public List sendToTest(List massUpdateCriteria, String userCmnts, MassUpdateTracker massUpdateTracker, boolean authorizedEditLockVar);
	
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
	 * lock list of mapping 
	 * @param massUpdateCriteria
	 * @param userComments
	 * @param massUpdateTracker
	 * @return
	 */
	public List lock(List massUpdateCriteria, String userComments, MassUpdateTracker massUpdateTracker);
	
	/**
	 * mass unlock variable mapping.
	 * @param massUpdateCriteria
	 * @param userComments
	 * @param massUpdateTracker
	 * @return
	 */
	public List unlock(List massUpdateCriteria, String userComments,MassUpdateTracker massUpdateTracker);
	/**
	 * BXNI NOVEMEBER 2012
	 * @param variable
	 * @param mapping
	 * @return Mapping
	 */


	public Mapping autoPopulateByFormat(Variable variable, Mapping mapping, String hippaSegmentToPopulate, String eb09);
}