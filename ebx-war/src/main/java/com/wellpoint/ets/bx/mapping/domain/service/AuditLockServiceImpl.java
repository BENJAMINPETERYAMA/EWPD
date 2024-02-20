/*
 * <AuditLockServiceImpl.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/
package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.List;

import com.wellpoint.ets.bx.mapping.application.security.Lock;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.exception.MappingLockedByAnotherUserException;
import com.wellpoint.ets.bx.mapping.repository.AuditLockRepository;
import com.wellpoint.ets.bx.mapping.repository.LockRepository;
import com.wellpoint.ets.bx.mapping.repository.MappingRepository;

/**
 * @author UST-GLOBAL
 * 
 * Service class for audit lock and unlock.
 * 
 */
public class AuditLockServiceImpl implements AuditLockService {
	
	private AuditLockRepository auditLockRepository;
	private MappingRepository mappingRepository;
	private LockRepository lockRepository;
	
	/**
	 * Lock the variable for audit trail.
	 * @param mapping
	 * @param auditLockStatus
	 * @return
	 */
	public boolean auditLock(Mapping mapping, String system) {
		return auditLockRepository.updateAuditLock(mapping, system);
	}
	/**
	 * Unlock the variable for audit trail.
	 * @param mapping
	 * @param auditLockStatus
	 * @return
	 */
	public boolean auditUnLock(Mapping mapping, String system){
		return auditLockRepository.updateAuditLock(mapping, system);
	}
	public AuditLockRepository getAuditLockRepository() {
		return auditLockRepository;
	}
	public void setAuditLockRepository(AuditLockRepository auditLockRepository) {
		this.auditLockRepository = auditLockRepository;
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
	
	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.domain.service.AuditLockService#isAuditLock(java.lang.String)
	 * Checks whether the variable mapping is already locked or not
	 */
	public boolean isAuditLock(String variableId) {
		return auditLockRepository.isAuditLock(variableId);
	}
	
	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.domain.service.AuditLockService#isAuditUnLock(java.lang.String)
	 * Checks whether the variable mapping is already unlocked or not
	 */
	public boolean isAuditUnLock(String variableId) {
		return auditLockRepository.isAuditUnLock(variableId);
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.domain.service.AuditLockService#isMappingBeingModified(java.lang.String)
	 * checks whether the variable is already being modified or not
	 */
	public boolean isMappingBeingModified(String variableId) {
		if(mappingRepository.isMappingBeingModified(variableId)){
			return true;
		}else{
			return false;
		}
	}
	public boolean isAuditLockInTemp(String variableId) {
		return auditLockRepository.isAuditLockInTemp(variableId);
	}
	public boolean isAuditUnLockInTemp(String variableId) {
		return auditLockRepository.isAuditUnLockInTemp(variableId);
	}
	public boolean auditTempLock(Mapping mapping, String system) {
		return auditLockRepository.updateTempAuditLock(mapping, system);
	}
	public boolean auditTempUnLock(Mapping mapping, String system) {
		return auditLockRepository.updateTempAuditLock(mapping, system);
	}
	public List retrieveTransactionLock(String className, String variableId,String userId) {
		List mappingList=lockRepository.retrieveLock(className, variableId);
		String lockedByUser = "";
		Lock lockObj = null;
		boolean isLockedBySameUser=false;
		if(null != mappingList && !mappingList.isEmpty()){
			lockObj = (Lock)mappingList.get(0);
			lockedByUser = lockObj.getLockUserId();
			if(userId.equals(lockedByUser)){
				isLockedBySameUser=true;
			}
		}
		
		if ((mappingList == null || mappingList.size()==0 )|| isLockedBySameUser) {
			return mappingList;
		}else{
			throw new MappingLockedByAnotherUserException("The variable ID "+variableId+" is locked by the user '" +lockedByUser.toUpperCase()+ "' for editing.");
		}
		
	}
	public void setLockRepository(LockRepository lockRepository) {
		this.lockRepository = lockRepository;
	}
	public LockRepository getLockRepository() {
		return lockRepository;
	}
	
	/**
	 * 
	 * @param variableIds
	 * @param locked
	 * @return
	 */
	public List getAuditLockStatusOfVariable(List variableIds, boolean locked, boolean chektemptable){
		return auditLockRepository.getAuditLockStatusOfVariable(variableIds, locked,chektemptable);
	}
	
	/**
	 * 
	 * @param variableId
	 * @param locked
	 * @param chektemptable
	 * @return
	 */
	public List getAuditLockStatusOfVariable(String variableId, boolean locked, boolean chektemptable){
		return auditLockRepository.getAuditLockStatusOfVariable(variableId, locked,chektemptable);
	}

}