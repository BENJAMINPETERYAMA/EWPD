/*
 * Created on May 20, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.AuditTrail;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.exception.DomainException;

/**
 * @author u22093
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface AuditTrailService {
	
	List retrieveAuditTrail(String variableId, Integer rowNum);
	List retrieveAllAuditTrail(String variableId);
	boolean logMapping(Mapping mapping, String userComments) throws DomainException;
	AuditTrail retrieveLatestAuditInfo(String mappingStatus, String varaiableId);
	
	boolean logMapping(int inClauseSize, int batchSize,List<Mapping> mappingList, String userComments,
			String auditStatus);
	boolean logMappingInDetail(Mapping mapping, String userComments);
	boolean logMappingInDetail(int inClauseSize, int batchSize, List<Mapping> mappingList, String userComments);
	boolean logMapping(Mapping mapping, String userComments,
			String auditStatus);
	/**
	 * Return a list a HippaSegmentMappingVO objects.
	 * @param variableId
	 * @return HippaSegmentMappingVO List
	 */
	List retreiveAuditTrailInDetail(String variableId);
	boolean removeExistingAuditTrail(String variableId, String system);
}
