/*
 * Created on May 7, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.repository;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.AuditTrail;

/**
 * @author u23641
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface AuditTrailRepository {
	
	List retrieveAuditTrail(String variableId, Integer noOfAuditInfo);
	List retrieveAllAuditTrail(String variableId);
	boolean insertAuditTrail(AuditTrail auditTrail);
	boolean batchInsertAuditTrail(int inClauseSize, int batchSize,List<AuditTrail> auditTrail);
	AuditTrail retrieveLatestAuditInfo(String mappingStatus, String varaiableId);
	
	List retrieveAuditInfo(String variableId, String auditStatus);
	boolean removeExistingISGAuditTrail(String variableId);
	boolean removeExistingLGAuditTrail(String variableId);
	boolean updateEBXAuditTrail(String variableId);
}
