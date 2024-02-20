/*
 * <AuditLockService.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/
package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;

/**
 * @author UST-GLOBAL
 * 
 * Interface for audit lock and unlock service.
 * 
 */
public interface AuditLockService {
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
	 * @param mapping
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
	
	/**
	 * 
	 * @param className
	 * @param keyId
	 * @param userId
	 * @return
	 */
	public List retrieveTransactionLock(String className, String keyId, String userId);
	
	/**
	 * 
	 * @param variableIds
	 * @param locked
	 * @return
	 */
	public List getAuditLockStatusOfVariable(List variableIds, boolean locked, boolean chektemptable);
	
	/**
	 * 
	 * @param variableId
	 * @param locked
	 * @param chektemptable
	 * @return
	 */
	public List getAuditLockStatusOfVariable(String variableId, boolean locked, boolean chektemptable);

}