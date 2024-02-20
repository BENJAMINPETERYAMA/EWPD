package com.wellpoint.ets.ebx.mapping.domain.service;

import java.util.List;

import com.wellpoint.ets.bx.mapping.application.security.Lock;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.exception.MappingLockedByAnotherUserException;
import com.wellpoint.ets.bx.mapping.domain.service.LockService;
import com.wellpoint.ets.bx.mapping.repository.LockRepository;

public class LockCustomMessageServiceImpl implements LockService  {
	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.application.security.LockDao#insertLock(com.wellpoint.ets.bx.mapping.application.security.Lock)
	 */
	private LockRepository lockCustomMessageRepository;
	
	private boolean insertLock(Lock lockObject) {
		return lockCustomMessageRepository.insertLock(lockObject);
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.application.security.LockDao#updateLock(com.wellpoint.ets.bx.mapping.application.security.Lock)
	 */
	private boolean updateLock(Lock lockObject) {
		return lockCustomMessageRepository.updateLock(lockObject);
	}


	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.domain.service.LockService#retrieveLock(com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
	 */
	public boolean aquireLock(Mapping mapping)  {
		String keyId=mapping.getRule().getHeaderRuleId()+ mapping.getSpsId().getSpsId();
		List lockList = lockCustomMessageRepository.retrieveLock(Mapping.class.getName(), keyId);
		String lockedByUser = "";
		if(null != lockList && !lockList.isEmpty()){
			Lock lockObj = (Lock)lockList.get(0);
			lockedByUser = lockObj.getLockUserId();
		}
		String createdUser = "";
		if (lockList == null || lockList.size()==0) {
			createdUser = mapping.getUser().getLastUpdatedUserName();
			if(createdUser.equals("")){
				createdUser = mapping.getUser().getCreatedUserName();
			}
			Lock lock = new Lock(Mapping.class.getName(),keyId ,createdUser);
			insertLock(lock);
		}else {
			throw new MappingLockedByAnotherUserException("The mapping with custom Message  "+keyId+" is locked by the user '" +lockedByUser.toUpperCase()+ "' for editing.");
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.domain.service.LockService#deleteLock(com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
	 */
	public boolean deleteLock(Mapping mapping)  {
		return lockCustomMessageRepository.deleteLock(mapping);
	}

	public boolean validateUserLock(Mapping mapping) {
		String keyId=mapping.getRule().getHeaderRuleId()+ mapping.getSpsId().getSpsId();
		List lockedByLoggedInUser=lockCustomMessageRepository.retrieveLock(Mapping.class.getName(), keyId, mapping.getUser().getLastUpdatedUserName());
		
		if (lockedByLoggedInUser != null && lockedByLoggedInUser.size()>0) {				
			Lock lock = new Lock(this.getClass().getName(),keyId,mapping.getUser().getLastUpdatedUserName());
			updateLock(lock);
		} else {
			aquireLock(mapping);
		}
		return true;
	}

	public LockRepository getLockCustomMessageRepository() {
		return lockCustomMessageRepository;
	}

	public void setLockCustomMessageRepository(
			LockRepository lockCustomMessageRepository) {
		this.lockCustomMessageRepository = lockCustomMessageRepository;
	}


	
}
