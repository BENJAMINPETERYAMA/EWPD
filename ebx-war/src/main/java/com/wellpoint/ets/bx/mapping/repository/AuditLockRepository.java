/*
 * <AuditLockRepository.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/
package com.wellpoint.ets.bx.mapping.repository;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;

/**
 * @author UST-GLOBAL
 * 
 * Interface for audit lock and unlock repository. 
 */
public interface AuditLockRepository {
	
	/**
	 *  Update the audit lock based on the audit lock status.
	 * @param mapping
	 * @param system 
	 * @return
	 */
	public boolean updateAuditLock(Mapping mapping, String system);
	
	/**
	 * Checking the variable is already unlocked or not.
	 * @param mapping
	 * @param system 
	 * @return
	 */
	public boolean isAuditUnLock(String variableId);
	
	/**
	 * Checking the variable is already locked or not.
	 * @param mapping
	 * @param system 
	 * @return
	 */
	public boolean isAuditLock(String variableId);
	
	/**
	 * 
	 * @param variableId
	 * @return
	 */
	public boolean isAuditUnLockInTemp(String variableId);
	
	/**
	 * 
	 * @param variableId
	 * @return
	 */
	public boolean isAuditLockInTemp(String variableId);
	
	/**
	 * 
	 * @param mapping
	 * @param system
	 * @return
	 */
	public boolean updateTempAuditLock(Mapping mapping, String system);
	/**
	 * 
	 * @param variableIds
	 * @param locked
	 * @return
	 */
	public List getAuditLockStatusOfVariable(List variableIds, boolean locked, boolean checktemptable);
	
	/**
	 * 
	 * @param variableId
	 * @param locked
	 * @param checktemptable
	 * @return
	 */
	public List getAuditLockStatusOfVariable(String variableId, boolean locked, boolean checktemptable);


}